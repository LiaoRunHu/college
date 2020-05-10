package cool.delete.sms.serive;

import com.qiniu.common.QiniuException;

/**
 * @author Tiger
 * @Description
 * @create 2020-05-09 20:52
 */
public interface SmsService {

    /**
     * 发送注册验证码
     * @param number
     * @return
     * @throws QiniuException
     */
    boolean sendRegisterCode(String number) throws QiniuException;
}
