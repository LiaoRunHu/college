package cool.delete.edu.service;

import cool.delete.edu.entity.Subject;
import com.baomidou.mybatisplus.extension.service.IService;
import cool.delete.edu.entity.vo.TreeCollectionVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author Tiger
 * @since 2020-05-05
 */
public interface SubjectService extends IService<Subject> {

    /**
     * 添加课程分类
     * @param multipartFile
     * @param subjectService
     */
    void saveSubject(MultipartFile multipartFile,SubjectService subjectService);

    /**
     * 查询课程分类并组装成前端需要的格式
     * @return
     */
    List<TreeCollectionVo> getAllSubjectByAssembled();
}
