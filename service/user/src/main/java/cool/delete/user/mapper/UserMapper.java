package cool.delete.user.mapper;

import cool.delete.user.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author Tiger
 * @since 2020-05-10
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 查询某天注册人数
     * @param day
     * @return
     */
    int countRegister(String day);
}
