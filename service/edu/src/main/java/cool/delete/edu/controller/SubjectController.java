package cool.delete.edu.controller;


import cool.delete.commonutils.Result;
import cool.delete.edu.entity.vo.TreeCollectionVo;
import cool.delete.edu.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author Tiger
 * @since 2020-05-05
 */
@RestController
@RequestMapping("/edu/subject")
@CrossOrigin
public class SubjectController {
    @Autowired
    SubjectService subjectService;

    @PostMapping("upload")
    public Result addSubject(@RequestParam("file") MultipartFile multipartFile){
        subjectService.saveSubject(multipartFile,subjectService);
        return Result.ok();
    }

    @GetMapping("")
    public Result getAllSubject(){
        List<TreeCollectionVo> subjectCollectionVos=subjectService.getAllSubjectByAssembled();
        return Result.ok().data("data",subjectCollectionVos);
    }
}

