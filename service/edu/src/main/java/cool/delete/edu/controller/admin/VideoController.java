package cool.delete.edu.controller.admin;


import cool.delete.commonutils.Result;
import cool.delete.edu.client.VodClient;
import cool.delete.edu.entity.Chapter;
import cool.delete.edu.entity.Video;
import cool.delete.edu.service.VideoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author Tiger
 * @since 2020-05-06
 */
@RestController
@RequestMapping("/edu/admin/video")
@CrossOrigin
public class VideoController {
    @Autowired
    private VideoService videoService;

    @Autowired
    private VodClient vodClient;

    @PostMapping()
    public Result addChapter(@RequestBody Video video){
        videoService.save(video);
        return Result.ok().data("data",video);
    }

    @GetMapping("{videoId}")
    public Result getChapter(@PathVariable String videoId){
        Video video = videoService.getById(videoId);
        return Result.ok().data("data",video);
    }

    @PutMapping("{videoId}")
    public Result addChapter(@RequestBody Video video, @PathVariable String videoId){
        video.setId(videoId);
        videoService.updateById(video);
        return Result.ok().data("data",video);
    }

    @DeleteMapping("/{videoId}")
    public Result deleteChapter(@PathVariable String videoId){
        Video video = videoService.getById(videoId);
        System.out.println(video);
        if (!StringUtils.isEmpty(video.getVideoSourceId())){
            vodClient.deleteVideoById(video.getVideoSourceId());
        }
        videoService.removeById(videoId);
        return Result.ok();
    }
}

