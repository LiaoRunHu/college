package cool.delete.sms.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Tiger
 * @Description
 * @create 2020-05-09 20:56
 */
@Component
public class ConstantUtils implements InitializingBean {
    @Value("${qiniu.AccessKey.AccessKeyID}")
    private String accessKeyId;
    @Value("${qiniu.AccessKey.AccessKeySecret}")
    private String accessKeySecret;

    @Value("${qiniu.TemplateId}")
    private String templateId;

    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;
    public static String TEMPLATEID;

    /**
     * 定义公开常量
     *
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet(){
        ACCESS_KEY_ID = accessKeyId;
        ACCESS_KEY_SECRET = accessKeySecret;
        TEMPLATEID=templateId;
    }
}

