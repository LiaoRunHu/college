package cool.delete.edu.client;

import cool.delete.commonutils.Result;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Tiger
 * @Description
 * @create 2020-05-08 16:36
 */
@Component
public class VodFileDegradeFeignClient implements VodClient{
    @Override
    public Result deleteVideoById(String id) {
        return Result.error().message("删除视频出错了");
    }

    @Override
    public Result deleteVideos(List<String> videoIdList) {
        return Result.error().message("删除多个视频出错了");
    }
}
