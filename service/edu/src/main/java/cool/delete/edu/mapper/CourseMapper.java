package cool.delete.edu.mapper;

import cool.delete.edu.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cool.delete.edu.entity.vo.CoursePublishVo;
import cool.delete.edu.entity.vo.CourseWebVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author Tiger
 * @since 2020-05-06
 */
public interface CourseMapper extends BaseMapper<Course> {
    /**
     * 获取课程详细发布信息
     * @param courseId
     * @return
     */
    CoursePublishVo getCoursePublishInfo(String courseId);

    /**
     * 获取课程基本下信息
     * @param id
     * @return
     */
    CourseWebVo getBaseCourseInfo(String id);
}
