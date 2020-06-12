package cool.delete.edu.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Tiger
 * @Description
 * @create 2020-06-08 17:25
 */
@FeignClient(value = "service-order")
@Component
public interface OrderClient {
    /**
     * 检测用户是否购买
     * @param courseId
     * @param userId
     * @return
     */
    @GetMapping("/edu/order/isBuy/{courseId}/{userId}")
    boolean isBuyCourse(@PathVariable("courseId") String courseId, @PathVariable("userId") String userId);
}
