package cool.delete.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cool.delete.edu.entity.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author Tiger
 * @since 2020-05-01
 */
public interface TeacherService extends IService<Teacher> {

    /**
     * 分页查询讲师的方法
     * @param teacherPage
     * @return
     */
    Map<String, Object> getTeacherList(Page<Teacher> teacherPage);
}
