package cool.delete.statistics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cool.delete.statistics.client.UserClient;
import cool.delete.statistics.entity.Daily;
import cool.delete.statistics.mapper.DailyMapper;
import cool.delete.statistics.service.DailyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cool.delete.statistics.utils.Const;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author Tiger
 * @since 2020-06-08
 */
@Service
public class DailyServiceImpl extends ServiceImpl<DailyMapper, Daily> implements DailyService {

    @Autowired
    UserClient userClient;

    /**
     * 统计某一天注册的人数
     *
     * @param day
     */
    @Override
    public void registerCount(String day) {
        QueryWrapper<Daily> dailyQueryWrapper = new QueryWrapper<>();
        dailyQueryWrapper.eq("date_calculated", day);
        baseMapper.delete(dailyQueryWrapper);
        Daily daily = new Daily();
        int countRegister = userClient.countRegister(day);
        daily.setRegisterNum(countRegister);
        daily.setDateCalculated(day);
        daily.setLoginNum(RandomUtils.nextInt(100, 200));
        daily.setVideoViewNum(RandomUtils.nextInt(100, 200));
        daily.setCourseNum(RandomUtils.nextInt(100, 200));
        baseMapper.insert(daily);
    }

    /**
     * 获取图表显示内容
     * @param type
     * @param begin
     * @param end
     * @return
     */
    @Override
    public Map<String, Object> showData(String type, String begin, String end) {
        QueryWrapper<Daily> queryWrapper=new QueryWrapper<>();
        queryWrapper.between("date_calculated",begin,end);
        queryWrapper.select("date_calculated",type);
        List<Daily> dailyList = baseMapper.selectList(queryWrapper);
        List<String> dataList=new ArrayList<>();
        List<Integer> numList=new ArrayList<>();

        dailyList.forEach(item->{
            dataList.add(item.getDateCalculated());
            switch (type){
                case Const.DailyType.LOGIN:
                    numList.add(item.getLoginNum());
                    break;
                case Const.DailyType.COURSE:
                    numList.add(item.getCourseNum());
                    break;
                case Const.DailyType.REGISTER:
                    numList.add(item.getRegisterNum());
                    break;
                case Const.DailyType.VIDEO:
                    numList.add(item.getVideoViewNum());
                    break;
                default:
                    break;
            }
        });
        Map<String, Object> map=new HashMap<>(16);
        map.put("dataList",dataList);
        map.put("numList",numList);
        return map;
    }
}
