package cool.delete.sms.controller;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.sms.SmsManager;
import com.qiniu.util.Auth;
import cool.delete.commonutils.Result;
import cool.delete.sms.serive.SmsService;
import cool.delete.sms.utils.ConstantUtils;
import cool.delete.sms.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Tiger
 * @Description
 * @create 2020-05-09 20:52
 */
@RestController
@RequestMapping("sms")
@CrossOrigin
public class SmsController {

    @Autowired
    SmsService smsService;

    @GetMapping("/{number}")
    public Result sendSMS(@PathVariable String number) throws QiniuException {

        boolean flag=smsService.sendRegisterCode(number);
        if (flag){
            return Result.ok();
        }
        return Result.error();

    }
}
