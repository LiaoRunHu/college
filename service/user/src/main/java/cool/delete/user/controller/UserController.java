package cool.delete.user.controller;


import cool.delete.commonutils.JwtUtils;
import cool.delete.commonutils.UserOrderVo;
import cool.delete.commonutils.Result;
import cool.delete.user.entity.User;
import cool.delete.user.entity.vo.RegisterVo;
import cool.delete.user.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author Tiger
 * @since 2020-05-10
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        String token=userService.login(user);
        return Result.ok().data("token",token);
    }

    @PostMapping("/register")
    public Result register(@RequestBody RegisterVo registerVo) {
        userService.register(registerVo);
        return Result.ok();
    }

    @GetMapping("/getUserInfo")
    public Result getUserInfo(HttpServletRequest request) {
        String userId = JwtUtils.getUserIdByJwtToken(request);
        User user = userService.getById(userId);
        return Result.ok().data("user",user);
    }

    @GetMapping("{id}")
    public UserOrderVo getUserInfoById(@PathVariable String id){
        User user = userService.getById(id);
        UserOrderVo userOrderVo =new UserOrderVo();
        BeanUtils.copyProperties(user, userOrderVo);
        return userOrderVo;
    }

    @PostMapping("countRegister/{day}")
    public int countRegister(@PathVariable String day){
        int count = userService.countRegister(day);
        return count;
    }
}

