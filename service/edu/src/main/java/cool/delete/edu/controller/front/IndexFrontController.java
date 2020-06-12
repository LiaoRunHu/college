package cool.delete.edu.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cool.delete.commonutils.Result;
import cool.delete.edu.entity.Course;
import cool.delete.edu.entity.Teacher;
import cool.delete.edu.service.CourseService;
import cool.delete.edu.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Tiger
 * @Description
 * @create 2020-05-09 0:52
 */
@RestController
@RequestMapping("/edu/course")
public class IndexFrontController {
    @Autowired
    CourseService courseService;

    @Autowired
    TeacherService teacherService;



    @GetMapping("index")
    public Result initIndexData(){
        QueryWrapper<Teacher> teacherQueryWrapper=new QueryWrapper<>();
        QueryWrapper<Course> courseQueryWrapper=new QueryWrapper<>();

        courseQueryWrapper.orderByDesc("gmt_modified");
        courseQueryWrapper.eq("status","Normal");
        courseQueryWrapper.last("limit 8");
        List<Course> courseList = courseService.list(courseQueryWrapper);


        teacherQueryWrapper.orderByDesc("gmt_modified");
        teacherQueryWrapper.last("limit 4");
        List<Teacher> teacherList = teacherService.list(teacherQueryWrapper);
        return Result.ok().data("courseList",courseList).data("teacherList",teacherList);
    }

}
