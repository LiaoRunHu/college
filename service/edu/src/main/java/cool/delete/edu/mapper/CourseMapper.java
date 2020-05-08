package cool.delete.edu.mapper;

import cool.delete.edu.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cool.delete.edu.entity.vo.CoursePublishVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author Tiger
 * @since 2020-05-06
 */
public interface CourseMapper extends BaseMapper<Course> {
    CoursePublishVo getCoursePublishInfo(String courseId);
}
