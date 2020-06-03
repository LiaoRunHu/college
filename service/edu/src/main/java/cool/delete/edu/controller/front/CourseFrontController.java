package cool.delete.edu.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cool.delete.commonutils.Result;
import cool.delete.edu.entity.Course;
import cool.delete.edu.entity.vo.CourseFrontVo;
import cool.delete.edu.entity.vo.CourseWebVo;
import cool.delete.edu.entity.vo.TreeCollectionVo;
import cool.delete.edu.service.ChapterService;
import cool.delete.edu.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author Tiger
 * @Description
 * @create 2020-06-03 14:02
 */
@RestController
@RequestMapping("/edu/course")
@CrossOrigin
public class CourseFrontController {
    @Autowired
    CourseService courseService;

    @Autowired
    ChapterService chapterService;

    @PostMapping("getCourseList/{page}/{limit}")
    public Result getCourseList(@PathVariable long page, @PathVariable long limit,
                                @RequestBody(required =false) CourseFrontVo courseFrontVo){
        Page<Course> coursePage=new Page<>(page,limit);
        Map<String,Object> map=courseService.getCourseList(coursePage,courseFrontVo);
        return Result.ok().data(map);
    }

    @GetMapping("{id}")
    public Result getCourseById(@PathVariable long id){
        CourseWebVo courseWebVo=courseService.getBaseCourseInfo(id);
        List<TreeCollectionVo> list = chapterService.getChapterByAssembled(Long.toString(id));
        return Result.ok().data("courseWebVo",courseWebVo).data("chapterList",list);
    }
}
