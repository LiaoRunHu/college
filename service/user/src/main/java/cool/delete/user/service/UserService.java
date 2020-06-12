package cool.delete.user.service;

import cool.delete.user.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import cool.delete.user.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author Tiger
 * @since 2020-05-10
 */
public interface UserService extends IService<User> {

    /**
     * 用户登录方法(手机号&密码)
     * @param user
     * @return
     */
    String login(User user);

    /**
     * 用户注册
     * @param registerVo
     */
    void register(RegisterVo registerVo);

    /**
     * 统计注册人数
     * @param day
     * @return
     */
    int countRegister(String day);
}
