package cool.delete.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cool.delete.commonutils.Const;
import cool.delete.commonutils.JwtUtils;
import cool.delete.servicebase.handler.CollegeException;
import cool.delete.user.entity.User;
import cool.delete.user.entity.vo.RegisterVo;
import cool.delete.user.mapper.UserMapper;
import cool.delete.user.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cool.delete.user.utils.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务实现类。
 * </p>
 *
 * @author Tiger
 * @since 2020-05-10
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 用户登录方法。
     *
     * @param user
     * @return
     */
    @Override
    public String login(User user) {
        String mobile = user.getMobile();
        String password = user.getPassword();
        if (StringUtils.isBlank(mobile) || StringUtils.isBlank(password)) {
            throw new CollegeException(Const.ResponseCode.ERROR, "账号或密码不能为空");
        }
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("mobile", mobile);
        User userSelect = baseMapper.selectOne(userQueryWrapper);
        if (userSelect == null) {
            throw new CollegeException(Const.ResponseCode.ERROR, "手机号未注册");
        }
        password = MD5Util.MD5EncodeUtf8(password);
        if (!password.equals(userSelect.getPassword())) {
            throw new CollegeException(Const.ResponseCode.ERROR, "密码错误");
        }
        if (userSelect.getIsDisabled()) {
            throw new CollegeException(Const.ResponseCode.ERROR, "账户被禁用");
        }
        String jwtToken = JwtUtils.getJwtToken(userSelect.getId(), userSelect.getNickname());
        return jwtToken;
    }

    /**
     * 用户注册
     *
     * @param registerVo
     */
    @Override
    public void register(RegisterVo registerVo) {
        String code = registerVo.getCode();
        String mobile = registerVo.getMobile();
        String nickname = registerVo.getNickname();
        String password = registerVo.getPassword();

        if (StringUtils.isBlank(mobile)) {
            throw new CollegeException(Const.ResponseCode.ERROR, "手机号不能为空");
        }
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("mobile", mobile);
        Integer count = baseMapper.selectCount(userQueryWrapper);
        if (count > 0) {
            throw new CollegeException(Const.ResponseCode.ERROR, "手机号已注册，请直接登录");
        }
        if (StringUtils.isBlank(code)) {
            throw new CollegeException(Const.ResponseCode.ERROR, "验证码不能为空");
        }
        String codeRedis = redisTemplate.opsForValue().get(mobile);
        if (!code.equals(codeRedis)) {
            throw new CollegeException(Const.ResponseCode.ERROR, "验证码错误");
        }
        if (StringUtils.isBlank(nickname)) {
            throw new CollegeException(Const.ResponseCode.ERROR, "用户名不能为空");
        }
        if (StringUtils.isBlank(password)) {
            throw new CollegeException(Const.ResponseCode.ERROR, "密码不能为空");
        }
        registerVo.setPassword(MD5Util.MD5EncodeUtf8(password));

        User user = new User();
        user.setMobile(registerVo.getMobile());
        user.setNickname(registerVo.getNickname());
        user.setPassword(registerVo.getPassword());
        user.setAvatar("https://file-online.oss-cn-shenzhen.aliyuncs.com/default_avatar.jpg");
        baseMapper.insert(user);
    }

    /**
     * 统计注册人数
     *
     * @param day
     * @return
     */
    @Override
    public int countRegister(String day) {
        return baseMapper.countRegister(day);
    }
}
