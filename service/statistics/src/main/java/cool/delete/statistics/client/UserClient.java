package cool.delete.statistics.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Tiger
 * @Description
 * @create 2020-06-08 21:35
 */
@Component
@FeignClient("service-user")
public interface UserClient {

    /**
     * 查询某日注册人数
     * @param day
     * @return
     */
    @PostMapping("/user/countRegister/{day}")
    int countRegister(@PathVariable("day") String day);
}
