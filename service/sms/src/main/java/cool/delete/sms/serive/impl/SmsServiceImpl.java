package cool.delete.sms.serive.impl;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.sms.SmsManager;
import com.qiniu.util.Auth;
import cool.delete.commonutils.Result;
import cool.delete.sms.serive.SmsService;
import cool.delete.sms.utils.ConstantUtils;
import cool.delete.sms.utils.RandomUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Tiger
 * @Description
 * @create 2020-05-09 20:52
 */
@Service
public class SmsServiceImpl implements SmsService {



    @Autowired
    RedisTemplate<String,String> redisTemplate;

    /**
     * 发送注册验证码
     *
     * @param number
     * @return
     */
    @Override
    public boolean sendRegisterCode(String number) throws QiniuException {
        String code = redisTemplate.opsForValue().get(number);
        if (StringUtils.isNotBlank(code)){
            return true;
        }
        code = RandomUtil.getSixBitRandom();
        Auth auth = Auth.create(ConstantUtils.ACCESS_KEY_ID, ConstantUtils.ACCESS_KEY_SECRET);
        // 实例化一个SmsManager对象
        SmsManager smsManager = new SmsManager(auth);
        Map<String, String> map = new HashMap<>(10);
        map.put("code", code);
        Response resp = smsManager.sendSingleMessage(ConstantUtils.TEMPLATEID, number, map);
        if (resp.isOK()){
            redisTemplate.opsForValue().set(number,code,30, TimeUnit.MINUTES);
        }
        return resp.isOK();
    }
}
