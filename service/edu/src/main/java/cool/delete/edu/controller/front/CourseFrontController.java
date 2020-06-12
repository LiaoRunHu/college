package cool.delete.edu.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cool.delete.commonutils.CourseOrderVo;
import cool.delete.commonutils.JwtUtils;
import cool.delete.commonutils.Result;
import cool.delete.edu.client.OrderClient;
import cool.delete.edu.entity.Course;
import cool.delete.edu.entity.vo.CourseFrontVo;
import cool.delete.edu.entity.vo.CourseWebVo;
import cool.delete.edu.entity.vo.TreeCollectionVo;
import cool.delete.edu.service.ChapterService;
import cool.delete.edu.service.CourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author Tiger
 * @Description
 * @create 2020-06-03 14:02
 */
@RestController
@RequestMapping("/edu/course")
public class CourseFrontController {
    @Autowired
    CourseService courseService;

    @Autowired
    ChapterService chapterService;

    @Autowired
    OrderClient orderClient;

    @PostMapping("getCourseList/{page}/{limit}")
    public Result getCourseList(@PathVariable long page, @PathVariable long limit,
                                @RequestBody(required =false) CourseFrontVo courseFrontVo){
        Page<Course> coursePage=new Page<>(page,limit);
        Map<String,Object> map=courseService.getCourseList(coursePage,courseFrontVo);
        return Result.ok().data(map);
    }

    @GetMapping("{id}")
    public Result getCourseById(@PathVariable String id, HttpServletRequest request){
        CourseWebVo courseWebVo=courseService.getBaseCourseInfo(id);
        List<TreeCollectionVo> list = chapterService.getChapterByAssembled(id);
        String userId = JwtUtils.getUserIdByJwtToken(request);
        if (userId == null) {
            return Result.ok().data("courseWebVo",courseWebVo).data("chapterList",list).data("isBuy",false);
        }
        boolean isBuy = orderClient.isBuyCourse(id, userId);
        return Result.ok().data("courseWebVo",courseWebVo).data("chapterList",list).data("isBuy",isBuy);
    }
    @GetMapping("order/{id}")
    public CourseOrderVo getCourseOrderVoById(@PathVariable String id){
        CourseWebVo courseWebVo = courseService.getBaseCourseInfo(id);
        CourseOrderVo courseOrderVo=new CourseOrderVo();
        BeanUtils.copyProperties(courseWebVo,courseOrderVo);
        return courseOrderVo;
    }
}
