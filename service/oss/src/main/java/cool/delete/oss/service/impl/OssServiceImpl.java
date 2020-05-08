package cool.delete.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import cool.delete.oss.service.OssService;
import cool.delete.oss.utils.ConstantUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author Tiger
 * @Description
 * @create 2020-05-04 22:16
 */
@Service
public class OssServiceImpl implements OssService {
    /**
     * 上传文件方法
     *
     * @param multipartFile
     * @return
     */
    @Override
    public String uploadFile(MultipartFile multipartFile){
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = ConstantUtils.END_POINT;
        // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
        String accessKeyId = ConstantUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantUtils.ACCESS_KEY_SECRET;
        String bucketName=ConstantUtils.BUCKET_NAME;
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);


        // 上传文件流。
        InputStream inputStream = null;
        try {
            String fileName = multipartFile.getOriginalFilename();
            inputStream = multipartFile.getInputStream();
            String fileExtensionName=fileName.substring(fileName.lastIndexOf("."));
            String uploadFileName= UUID.randomUUID().toString().replaceAll("-","")+fileExtensionName;
            ossClient.putObject(bucketName, uploadFileName, inputStream);
            // 关闭OSSClient。
            ossClient.shutdown();

            String url="https://"+bucketName+"."+endpoint+"/"+uploadFileName;
            return url;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }


}
