package cool.delete.edu.controller;


import cool.delete.commonutils.Result;
import cool.delete.edu.entity.Chapter;
import cool.delete.edu.entity.vo.TreeCollectionVo;
import cool.delete.edu.service.ChapterService;
import org.apache.ibatis.annotations.Delete;
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
@RequestMapping("/edu/chapter")
@CrossOrigin
public class ChapterController {

    @Autowired
    ChapterService chapterService;

    @GetMapping("/{courseId}")
    public Result getAllChapter(@PathVariable String courseId){
        List<TreeCollectionVo> subjectCollectionVos=chapterService.getChapterByAssembled(courseId);
        return Result.ok().data("data",subjectCollectionVos);
    }

    @PostMapping()
    public Result addChapter(@RequestBody Chapter chapter){
        chapterService.save(chapter);
        return Result.ok().data("data",chapter);
    }

    @GetMapping("chapter/{chapterId}")
    public Result getChapter(@PathVariable String chapterId){
        Chapter chapter = chapterService.getById(chapterId);
        return Result.ok().data("data",chapter);
    }

    @PutMapping("{chapterId}")
    public Result addChapter(@RequestBody Chapter chapter, @PathVariable String chapterId){
        chapter.setId(chapterId);
        chapterService.updateById(chapter);
        return Result.ok().data("data",chapter);
    }

    @DeleteMapping("/{chapterId}")
    public Result deleteChapter(@PathVariable String chapterId){
        chapterService.deleteChapter(chapterId);
        return Result.ok();
    }
}

