package cool.delete.edu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cool.delete.edu.entity.Subject;
import cool.delete.edu.entity.vo.SubjectData;
import cool.delete.edu.service.SubjectService;
import cool.delete.servicebase.handler.CollegeException;

/**
 * @author Tiger
 * @Description
 * @create 2020-05-05 16:22
 */
public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {


    //因为SubjectExcelListener不能交给spring管理 需要自己new 不能注入其他对象

    public  SubjectService service;

    public SubjectExcelListener(SubjectService service) {
        this.service = service;
    }

    public SubjectExcelListener() { }

    /**
     *逐行读取excel内容
     */
    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if (subjectData==null){
            throw new CollegeException(500,"文件为空");
        }
        System.out.println(subjectData);
        Subject existOneSubject = this.existSubject(service, subjectData.getOneLevelSubjectName(), "0");
        if (existOneSubject==null){
            //没有相同的一级分类
            existOneSubject=new Subject();
            existOneSubject.setParentId("0");
            existOneSubject.setTitle(subjectData.getOneLevelSubjectName());
            boolean save = service.save(existOneSubject);
        }
        //mybatis-plus 会自动将保存后的id赋给对象
        String subjectId=existOneSubject.getId();
        Subject existTwoSubject = this.existSubject(service, subjectData.getTwoLevelSubjectName(), subjectId);
        if (existTwoSubject==null){
            //一级分类下没有相同的二级分类
            Subject twoLevelSubject=new Subject();
            twoLevelSubject.setParentId(subjectId);
            twoLevelSubject.setTitle(subjectData.getTwoLevelSubjectName());
            service.save(twoLevelSubject);
        }
    }

    /**
     * 检测是否存在分类数据
     * @param service
     * @param name
     * @param parentId
     * @return
     */
    public Subject existSubject(SubjectService service,String name,String parentId){
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("title",name);
        queryWrapper.eq("parent_id",parentId);
        Subject subject = service.getOne(queryWrapper);
        return subject;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
