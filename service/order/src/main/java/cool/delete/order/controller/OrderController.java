package cool.delete.order.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cool.delete.commonutils.JwtUtils;
import cool.delete.commonutils.Result;
import cool.delete.order.entity.Order;
import cool.delete.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author Tiger
 * @since 2020-06-07
 */
@RestController
@RequestMapping("/edu/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/{courseId}")
    public Result createOrder(@PathVariable String courseId, HttpServletRequest request){
        String userId = JwtUtils.getUserIdByJwtToken(request);
        String orderId=orderService.createOrder(courseId,userId);
        return Result.ok().data("orderId",orderId);
    }

    @GetMapping("/{orderId}")
    public Result getOrderId(@PathVariable String orderId){
        QueryWrapper<Order> wrapper=new QueryWrapper<>();
        wrapper.eq("order_no",orderId);
        Order order = orderService.getOne(wrapper);
        return Result.ok().data("item",order);
    }

    @GetMapping("isBuy/{courseId}/{userId}")
    public boolean isBuyCourse(@PathVariable("courseId") String courseId, @PathVariable("userId") String userId){
        QueryWrapper<Order> wrapper=new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        wrapper.eq("member_id",userId);
        //1代表已支付
        wrapper.eq("status",1);
        int count = orderService.count(wrapper);
        return count>0;
    }
}

