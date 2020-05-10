package cool.delete.edu.controller.admin;

import cool.delete.commonutils.Result;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Tiger
 * @Description
 * @create 2020-05-04 0:38
 */

@RestController
@RequestMapping("edu/admin/user")
@CrossOrigin
public class LoginController {

    @PostMapping("login")
    public Result login(){
        return Result.ok().data("token","admin");
    }

    @PostMapping("info")
    public Result info(){
        Map resultMap=new HashMap(3);
        resultMap.put("roles","[admin]");
        resultMap.put("name","admin");
        resultMap.put("avatar","https://www.baidu.com/img/dong_f6764cd1911fae7d460b25e31c7e342c.gif");
        return Result.ok().data(resultMap);
    }
}
