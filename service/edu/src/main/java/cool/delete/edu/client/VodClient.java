package cool.delete.edu.client;

import cool.delete.commonutils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Tiger
 * @Description
 * @create 2020-05-08 14:03
 * @FeignClient 使用Feign转换请求
 */
@FeignClient(value = "service-vod",fallback = VodFileDegradeFeignClient.class)
@Component
public interface VodClient {
    @DeleteMapping("/edu/vod/{id}")
    Result deleteVideoById(@PathVariable("id") String id);

    @DeleteMapping("/edu/vod/batch")
    Result deleteVideos(@RequestParam("videoIdList") List<String> videoIdList);
}
