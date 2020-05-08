package cool.delete.oss.controller;

import cool.delete.commonutils.Result;
import cool.delete.oss.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author Tiger
 * @Description
 * @create 2020-05-04 22:17
 */
@RestController
@RequestMapping("/edu/oss")
@CrossOrigin
public class OssController {

    @Autowired
    OssService ossService;

    @PostMapping("upload")
    public Result uploadOssFile(@RequestParam("file") MultipartFile multipartFile){
        String url = ossService.uploadFile(multipartFile);
        return Result.ok().data("url",url);
    }
}
