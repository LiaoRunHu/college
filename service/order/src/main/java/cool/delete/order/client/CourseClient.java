package cool.delete.order.client;

import cool.delete.commonutils.CourseOrderVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Tiger
 * @Description
 * @create 2020-06-07 17:00
 */
@Component
@FeignClient("service-edu")
public interface CourseClient {
    @GetMapping("/edu/course/order/{id}")
    CourseOrderVo getCourseOrderVoById(@PathVariable("id") String id);
}
