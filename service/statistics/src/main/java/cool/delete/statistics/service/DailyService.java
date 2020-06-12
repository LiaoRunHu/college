package cool.delete.statistics.service;

import cool.delete.statistics.entity.Daily;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author Tiger
 * @since 2020-06-08
 */
public interface DailyService extends IService<Daily> {

    /**
     * 统计某一天注册的人数
     * @param day
     */
    void registerCount(String day);

    /**
     * 获取图表显示内容
     * @param type
     * @param begin
     * @param end
     * @return
     */
    Map<String, Object> showData(String type, String begin, String end);
}
