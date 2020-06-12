package cool.delete.edu.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cool.delete.commonutils.Result;
import cool.delete.edu.entity.Course;
import cool.delete.edu.entity.Teacher;
import cool.delete.edu.service.CourseService;
import cool.delete.edu.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author Tiger
 * @Description
 * @create 2020-06-01 14:06
 */
@RestController
@RequestMapping("/edu/teacher")
public class TeacherFrontController {
    @Autowired
    TeacherService teacherService;

    @Autowired
    CourseService courseService;

    @GetMapping("getTeacherList/{page}/{limit}")
    public Result getTeacherList(@PathVariable long page,@PathVariable long limit){
        Page<Teacher> teacherPage=new Page<>(page,limit);
        Map<String,Object> map=teacherService.getTeacherList(teacherPage);
        return Result.ok().data(map);
    }

    @GetMapping("{id}")
    public Result getTeacherById(@PathVariable long id){
        //查询讲师基本信息
        Teacher teacher = teacherService.getById(id);
        //查询讲师拥有的课程
        QueryWrapper<Course> wrapper=new QueryWrapper<>();
        wrapper.eq("teacher_id",id);
        List<Course> list = courseService.list(wrapper);
        return Result.ok().data("teacher",teacher).data("courseList",list);
    }
}
