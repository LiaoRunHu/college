package cool.delete.edu.controller.admin;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cool.delete.commonutils.Result;
import cool.delete.edu.entity.Teacher;
import cool.delete.edu.entity.vo.TeacherQuery;
import cool.delete.edu.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author Tiger
 * @since 2020-05-01
 */
@RestController
@RequestMapping("/edu/admin/teacher")
@Slf4j
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    /**
     * 通过路径接收需要删除的教师id
     *
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    public Result delete(@PathVariable String id) {
        if (teacherService.removeById(id)) {
            return Result.ok();
        } else {
            return Result.error();
        }
    }

    @GetMapping("Search/page={page}&limit={limit}")
    public Result pageTeacherCondition(@PathVariable long page, @PathVariable long limit, TeacherQuery teacherQuery) {
        Page<Teacher> pageTeacher = new Page<>(page, limit);
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();

        if (page==0&&limit==0){
            List<Teacher> teachers = teacherService.list();
            return Result.ok().data("data",teachers);
        }
        QueryWrapper<Teacher> wrapper=new QueryWrapper<>();
        //判断条件是否为空,构建条件查询
        if (StringUtils.isNotBlank(name)){
            wrapper.like("name",name);
        }
        if (level!=null){
            wrapper.eq("level",level);
        }
        if(StringUtils.isNotBlank(begin)){
            wrapper.ge("gmt_create",begin);
        }
        if(StringUtils.isNotBlank(end)){
            wrapper.le("gmt_create",end);
        }
        wrapper.orderByDesc("gmt_create");
        teacherService.page(pageTeacher, wrapper);
        return Result.ok().data("data",pageTeacher);
    }

    @PostMapping("")
    public Result addTeacher(@RequestBody Teacher teacher){
        boolean save = teacherService.save(teacher);
        if (save){
            return Result.ok();
        }else {
            return Result.error();
        }
    }
    @GetMapping("{id}")
    public Result getTeacherById(@PathVariable String id){
        Teacher teacher = teacherService.getById(id);
        return Result.ok().data("data",teacher);
    }

    @PutMapping("{id}")
    public Result updateTeacherById(@PathVariable String id, @RequestBody Teacher teacher){
        teacher.setId(id);
        boolean update = teacherService.updateById(teacher);
        if (update){
            return Result.ok().data("data",teacher);
        }else {
            return Result.error();
        }
    }
}

