package cool.delete.statistics.controller;


import cool.delete.commonutils.Result;
import cool.delete.statistics.service.DailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author Tiger
 * @since 2020-06-08
 */
@RestController
@RequestMapping("/statistics")
public class DailyController {
    @Autowired
    DailyService dailyService;

    /**
     * 统计某一天注册的人数
     * @param day
     * @return
     */
    @PostMapping("registerCount/{day}")
    public Result getRegisterCount(@PathVariable String day){
        dailyService.registerCount(day);
        return Result.ok();
    }

    @GetMapping("/{type}/{begin}/{end}")
    public Result showData(@PathVariable String type, @PathVariable String begin, @PathVariable String end){
        Map<String,Object> map =dailyService.showData(type,begin,end);
        return Result.ok().data(map);
    }

}

