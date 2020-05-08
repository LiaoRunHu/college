package cool.delete.vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import cool.delete.servicebase.handler.CollegeException;
import cool.delete.vod.service.VodService;
import cool.delete.vod.utils.ConstantUtils;
import cool.delete.vod.utils.InitUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tiger
 * @Description
 * @create 2020-05-08 1:48
 */
@Service
public class VodServiceImpl implements VodService {
    /**
     * 上传视频
     *
     * @param multipartFile
     * @return
     */
    @Override
    public String upload(MultipartFile multipartFile) throws IOException {

        String fileName=multipartFile.getOriginalFilename();
        InputStream inputStream = multipartFile.getInputStream();
        UploadStreamRequest request = new UploadStreamRequest(ConstantUtils.ACCESS_KEY_ID, ConstantUtils.ACCESS_KEY_SECRET, fileName, fileName, inputStream);

        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadStreamResponse response = uploader.uploadStream(request);
        String videoId="";
        if (response.isSuccess()) {
            videoId=response.getVideoId();
        } else { //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
            videoId=response.getVideoId();
            throw new CollegeException(Integer.parseInt(response.getCode()),response.getMessage());
        }
        return videoId;
    }

    /**
     * 根据阿里云视频id删除视频
     * @param id
     * @return
     * @throws ClientException
     */
    @Override
    public void removeVideoById(String id) throws ClientException {
        DefaultAcsClient client = InitUtils.initVodClient(ConstantUtils.ACCESS_KEY_ID, ConstantUtils.ACCESS_KEY_SECRET);
        DeleteVideoRequest request=new DeleteVideoRequest();
        request.setVideoIds(id);
        client.getAcsResponse(request);
    }

    /**
     * 删除多个视频
     *
     * @param videoIdList
     */
    @Override
    public void removeVideos(List<String> videoIdList) throws ClientException {
        DefaultAcsClient client = InitUtils.initVodClient(ConstantUtils.ACCESS_KEY_ID, ConstantUtils.ACCESS_KEY_SECRET);
        DeleteVideoRequest request=new DeleteVideoRequest();
        request.setVideoIds(StringUtils.join(videoIdList, ","));
        DeleteVideoResponse response = client.getAcsResponse(request);
        List<String> nonExistVideoIds = response.getNonExistVideoIds();
    }

}
