package cool.delete.vod.controller;

import com.aliyuncs.exceptions.ClientException;
import cool.delete.commonutils.Result;
import cool.delete.servicebase.handler.CollegeException;
import cool.delete.vod.service.VodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author Tiger
 * @Description
 * @create 2020-05-08 1:47
 */
@RestController
@RequestMapping("/edu/vod")
@CrossOrigin
public class VodController {

    @Autowired
    VodService vodService;

    @PostMapping("/upload")
    public Result upload(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        String vodId=vodService.upload(multipartFile);
        return Result.ok().data("data",vodId);
    }

    @DeleteMapping("{id}")
    public Result deleteVideoById(@PathVariable String id)  {
        try {
            vodService.removeVideoById(id);
        } catch (ClientException e) {
            e.printStackTrace();
            throw new CollegeException(400,e.getErrMsg());
        }
        return Result.ok();
    }

    /**
     * 删除多个视频
     * @param videoIdList
     * @return
     */
    @DeleteMapping("/batch")
    public Result deleteVideos(@RequestParam("videoIdList") List<String> videoIdList)  {
        try {
            vodService.removeVideos(videoIdList);
        } catch (ClientException e) {
            e.printStackTrace();
            throw new CollegeException(400,e.getErrMsg());
        }
        return Result.ok();
    }
}
