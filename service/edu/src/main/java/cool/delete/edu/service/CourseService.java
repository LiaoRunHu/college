package cool.delete.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cool.delete.edu.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import cool.delete.edu.entity.vo.CourseFrontVo;
import cool.delete.edu.entity.vo.CoursePublishVo;
import cool.delete.edu.entity.vo.CourseVo;
import cool.delete.edu.entity.vo.CourseWebVo;

import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author Tiger
 * @since 2020-05-06
 */
public interface CourseService extends IService<Course> {

    /**
     * 添加课程
     * @param courseVo
     * @return
     */
    String addCourseInfo(CourseVo courseVo);

    /**
     * 根据id查询courseVo
     * @param id
     * @return
     */
    CourseVo getCourseVoById(String id);

    /**
     * 更新CourseVo
     * @param courseVo
     */
    void updateCourseInfo(CourseVo courseVo);

    /**
     * 获取课程发布总信息
     * @param id
     * @return
     */
    CoursePublishVo getPublishCourseInfo(String id);

    /**
     * 删除课程及相关资源
     * @param id
     * @return
     */
    boolean removeCourse(String id);

    /**
     * 根据条件分页查询课程
     * @param coursePage
     * @param courseFrontVo
     * @return
     */
    Map<String, Object> getCourseList(Page<Course> coursePage, CourseFrontVo courseFrontVo);

    /**
     * 课程详情类Vo
     * @param id
     * @return
     */
    CourseWebVo getBaseCourseInfo(long id);
}
