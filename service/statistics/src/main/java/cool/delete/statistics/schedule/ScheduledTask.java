package cool.delete.statistics.schedule;

import cool.delete.statistics.service.DailyService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;

/**
 * @author Tiger
 * @Description
 * @create 2020-06-08 23:55
 */
@Component
public class ScheduledTask {

    @Autowired
    private DailyService dailyService;

    @Scheduled(cron = "0 0 0 * * ?")
    public void task(){
        LocalDate localDate = LocalDate.now();
        localDate=localDate.plusDays(-1);
        dailyService.registerCount(localDate.toString());
    }
}
