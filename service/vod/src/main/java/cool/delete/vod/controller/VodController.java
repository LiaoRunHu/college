package cool.delete.vod.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import cool.delete.commonutils.Result;
import cool.delete.servicebase.handler.CollegeException;
import cool.delete.vod.service.VodService;
import cool.delete.vod.utils.ConstantUtils;
import cool.delete.vod.utils.InitUtils;
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
@RequestMapping("/vod")
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

    @GetMapping("getPlayAuth/{id}")
    public Result getPlayAuth(@PathVariable String id){
        try {
            //创建初始化对象
            DefaultAcsClient client = InitUtils.initVodClient(ConstantUtils.ACCESS_KEY_ID, ConstantUtils.ACCESS_KEY_SECRET);
            //创建获取凭证request和response对象
            GetVideoPlayAuthRequest request=new GetVideoPlayAuthRequest();
            //设置videoId
            request.setVideoId(id);
            GetVideoPlayAuthResponse response = client.getAcsResponse(request);
            String playAuth = response.getPlayAuth();
            return Result.ok().data("playAuth",playAuth);
        } catch (Exception e) {
            throw new CollegeException(400,"获取视频凭证失败");
        }

    }
}
