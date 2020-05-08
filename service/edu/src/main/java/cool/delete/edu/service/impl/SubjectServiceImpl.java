package cool.delete.edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cool.delete.edu.entity.Subject;
import cool.delete.edu.entity.vo.SubjectData;
import cool.delete.edu.entity.vo.TreeCollectionVo;
import cool.delete.edu.listener.SubjectExcelListener;
import cool.delete.edu.mapper.SubjectMapper;
import cool.delete.edu.service.SubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author Tiger
 * @since 2020-05-05
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

    /**
     * 添加课程分类
     * @param multipartFile
     * @param subjectService
     */
    @Override
    public void saveSubject(MultipartFile multipartFile,SubjectService subjectService) {
        try {
            InputStream inputStream = multipartFile.getInputStream();
            EasyExcel.read(inputStream, SubjectData.class,new SubjectExcelListener(subjectService)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 查询课程分类并组装成前端需要的格式
     *
     * @return
     */
    @Override
    public List<TreeCollectionVo> getAllSubjectByAssembled() {
        QueryWrapper<Subject> queryWrapper=new QueryWrapper();
        queryWrapper.eq("parent_id","0");
        List<Subject> oneLevelSubjectList = baseMapper.selectList(queryWrapper);
        //返回用数组
        List<TreeCollectionVo> subjectCollectionVoList=new ArrayList<>();
        //存储二级元素用数组
        List<TreeCollectionVo> childrenList=new ArrayList<>();
        TreeCollectionVo subjectCollectionVo;

        for (Subject subject : oneLevelSubjectList) {
            subjectCollectionVo=new TreeCollectionVo();
            queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("parent_id",subject.getId());
            childrenList.clear();

            List<Subject> twoLevelSubjectList = baseMapper.selectList(queryWrapper);

            twoLevelSubjectList.forEach(twoSubject -> {
                childrenList.add(new TreeCollectionVo(twoSubject.getId(),twoSubject.getTitle()));
            });
            subjectCollectionVo.setId(subject.getId());
            subjectCollectionVo.setTitle(subject.getTitle());
            List tempList=new ArrayList();
            tempList.addAll(childrenList);
            subjectCollectionVo.setChildren(tempList);

            subjectCollectionVoList.add(subjectCollectionVo);
        }
        return subjectCollectionVoList;
    }
}
