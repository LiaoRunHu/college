package cool.delete.vod.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Tiger
 * @Description
 * @create 2020-05-04 22:03
 */
@Component
public class ConstantUtils implements InitializingBean {
    @Value("${aliyun.AccessKey.AccessKeyID}")
    private String accessKeyId;
    @Value("${aliyun.AccessKey.AccessKeySecret}")
    private String accessKeySecret;


    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;

    /**
     * 定义公开常量
     *
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet(){
        ACCESS_KEY_ID = accessKeyId;
        ACCESS_KEY_SECRET = accessKeySecret;
    }
}
