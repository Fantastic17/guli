package com.atguigu.guli.service.statistics.task;

import com.atguigu.guli.service.statistics.service.DailyService;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author wind
 * @create 2020-08-09 3:59
 */
@Component
@Slf4j
public class ScheduledTask {

    @Autowired
    private DailyService dailyService;

//    @Scheduled(cron = "0/3 * * * * ? ")
//    public void task1(){
//        log.info("task1 在执行......");
//    }


    @Scheduled(cron = "0 0 1 * * ?")
    public void testGenStatisticsData(){
        log.info("testGenStatisticsData ....");
        String day = new DateTime().minusDays(1).toString("yyyy-MM-dd");
        dailyService.createStatisticsByDay(day);
    }

}
