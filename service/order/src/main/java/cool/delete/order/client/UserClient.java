package cool.delete.order.client;

import cool.delete.commonutils.UserOrderVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Tiger
 * @Description
 * @create 2020-06-07 16:59
 */
@Component
@FeignClient("service-user")
public interface UserClient {
    @GetMapping("/user/{id}")
    UserOrderVo getUserInfoById(@PathVariable("id") String id);
}
