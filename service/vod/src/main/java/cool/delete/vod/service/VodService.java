package cool.delete.vod.service;

import com.aliyuncs.exceptions.ClientException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author Tiger
 * @Description
 * @create 2020-05-08 1:47
 */
public interface VodService {

    /**
     * 上传视频
     * @param multipartFile
     * @return
     * @throws IOException
     */
    String upload(MultipartFile multipartFile) throws IOException;

    /**
     * 根据阿里云视频id删除视频
     * @param id
     * @throws ClientException
     */
    void removeVideoById(String id) throws ClientException;

    /**
     * 删除多个视频
     * @param videoIdList
     * @throws ClientException
     */
    void removeVideos(List<String> videoIdList) throws ClientException;
}
