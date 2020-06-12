package cool.delete.order.service;

import cool.delete.order.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author Tiger
 * @since 2020-06-07
 */
public interface OrderService extends IService<Order> {

    /**
     * 根据商品id和用户id创建订单
     * @param courseId
     * @param userId
     * @return
     */
    String createOrder(String courseId, String userId);
}
