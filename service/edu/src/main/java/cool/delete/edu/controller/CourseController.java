package cool.delete.edu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cool.delete.commonutils.Result;
import cool.delete.edu.entity.Course;
import cool.delete.edu.entity.Teacher;
import cool.delete.edu.entity.vo.CoursePublishVo;
import cool.delete.edu.entity.vo.CourseQuery;
import cool.delete.edu.entity.vo.CourseVo;
import cool.delete.edu.service.CourseService;
import cool.delete.edu.service.TeacherService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author Tiger
 * @since 2020-05-06
 */
@RestController
@RequestMapping("/edu/course")
@CrossOrigin
public class CourseController {

    @Autowired
    CourseService courseService;

    @Autowired
    TeacherService teacherService;
    @PostMapping
    public Result addCourseInfo(@RequestBody CourseVo courseVo) {
        String id = courseService.addCourseInfo(courseVo);
        return Result.ok().data("courseId", id);
    }

    @GetMapping("{id}")
    public Result getCourseInfo(@PathVariable String id) {
        CourseVo courseVo = courseService.getCourseVoById(id);
        return Result.ok().data("data", courseVo);
    }

    @PutMapping("{id}")
    public Result updateCourseInfo(@PathVariable String id, @RequestBody CourseVo courseVo) {
        courseVo.setId(id);
        courseService.updateCourseInfo(courseVo);
        return Result.ok().data("courseId", id);
    }

    @GetMapping("publish/{id}")
    public Result getPublishCourseInfo(@PathVariable String id) {
        CoursePublishVo publishCourseInfo = courseService.getPublishCourseInfo(id);
        return Result.ok().data("data", publishCourseInfo);
    }

    @PutMapping("publish/{id}&status={status}")
    public Result publish(@PathVariable String id, @PathVariable String status) {
        Course course = new Course();
        course.setId(id);
        course.setStatus(status);
        courseService.updateById(course);
        return Result.ok();
    }
    @DeleteMapping("{id}")
    public Result deleteCourse(@PathVariable String id) {
        courseService.removeCourse(id);
        return Result.ok();
    }
    @GetMapping("Search/page={page}&limit={limit}")
    public Result pageTeacherCondition(@PathVariable long page, @PathVariable long limit, CourseQuery courseQuery) {
        Page<Course> coursePage = new Page<>(page, limit);
        String title = courseQuery.getTitle();
        String status = courseQuery.getStatus();
        String teacherId = courseQuery.getTeacherId();
        String oneLevelId = courseQuery.getOneLevelId();
        String twoLevelId = courseQuery.getTwoLevelId();
        String begin = courseQuery.getBegin();
        String end = courseQuery.getEnd();

        if (page == 0 && limit == 0) {
            List<Course> courses = courseService.list();
            courses.forEach(course -> {
                Teacher teacher = teacherService.getById(course.getTeacherId());
                course.setTeacherId(teacher.getName());
            });
            return Result.ok().data("data", courses);
        }
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        //判断条件是否为空,构建条件查询
        if (StringUtils.isNotBlank(title)) {
            wrapper.like("title", title);
        }
        if (StringUtils.isNotBlank(status)){
            wrapper.eq("status", status);
        }
        if (StringUtils.isNotBlank(oneLevelId)) {
            wrapper.like("subject_parent_id", oneLevelId);
        }
        if (StringUtils.isNotBlank(twoLevelId)) {
            wrapper.like("subject_id", twoLevelId);
        }
        if (StringUtils.isNotBlank(teacherId)) {
            wrapper.like("teacher_id", teacherId);
        }
        if (StringUtils.isNotBlank(begin)) {
            wrapper.ge("gmt_create", begin);
        }
        if (StringUtils.isNotBlank(end)) {
            wrapper.le("gmt_create", end);
        }
        wrapper.orderByDesc("gmt_create");
        courseService.page(coursePage, wrapper);

        coursePage.getRecords().forEach(course -> {
            if(StringUtils.isNotBlank(course.getTeacherId())){
                Teacher teacher = teacherService.getById(course.getTeacherId());
                if (teacher != null) {
                    course.setTeacherId(teacher.getName());
                }else {
                    course.setTeacherId("");
                }
            }
        });
        return Result.ok().data("data", coursePage);
    }
}

