package cool.delete.order.service.impl;

import cool.delete.order.entity.PayLog;
import cool.delete.order.mapper.PayLogMapper;
import cool.delete.order.service.PayLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 支付日志表 服务实现类
 * </p>
 *
 * @author Tiger
 * @since 2020-06-07
 */
@Service
public class PayLogServiceImpl extends ServiceImpl<PayLogMapper, PayLog> implements PayLogService {

}
