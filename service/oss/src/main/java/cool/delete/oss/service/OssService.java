package cool.delete.oss.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;

/**
 * @author Tiger
 * @Description
 * @create 2020-05-04 22:16
 */
public interface OssService {

    /**
     * 上传文件方法
     * @param multipartFile
     * @return
     */
    String uploadFile(MultipartFile multipartFile) ;
}
