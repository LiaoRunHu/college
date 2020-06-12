package cool.delete.order.service.impl;

import cool.delete.commonutils.CourseOrderVo;
import cool.delete.commonutils.UserOrderVo;
import cool.delete.order.client.CourseClient;
import cool.delete.order.client.UserClient;
import cool.delete.order.entity.Order;
import cool.delete.order.mapper.OrderMapper;
import cool.delete.order.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cool.delete.order.utils.OrderNoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author Tiger
 * @since 2020-06-07
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private CourseClient courseClient;

    @Autowired
    private UserClient userClient;

    /**
     * 根据商品id和用户id创建订单
     *
     * @param courseId
     * @param userId
     * @return
     */
    @Override
    public String createOrder(String courseId, String userId) {
        UserOrderVo user = userClient.getUserInfoById(userId);
        CourseOrderVo courseOrderVo = courseClient.getCourseOrderVoById(courseId);

        Order order = new Order();
        order.setOrderNo(OrderNoUtil.getOrderNo());
        order.setCourseId(courseId);
        order.setCourseTitle(courseOrderVo.getTitle());
        order.setCourseCover(courseOrderVo.getCover());
        order.setTeacherName(courseOrderVo.getTeacherName());
        order.setTotalFee(courseOrderVo.getPrice());
        order.setMemberId(user.getId());
        order.setMobile(user.getMobile());
        order.setNickname(user.getNickname());
        order.setStatus(0);
        order.setPayType(1);
        baseMapper.insert(order);
        return order.getOrderNo();
    }
}
