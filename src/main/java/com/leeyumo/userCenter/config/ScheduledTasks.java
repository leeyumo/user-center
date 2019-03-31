package com.leeyumo.userCenter.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

@Component
@Configurable
@EnableScheduling
public class ScheduledTasks {
    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledTasks.class);

    //每天7-22整点执行
//    @Scheduled(cron = "0 0 7-22  * * ? ")
//    public void operateDisableLog(){
//        LOGGER.info("====> execute operateDisableLog");
//    }

//    @Scheduled(fixedRate = 1000 * 3)
//    public void reportCurrentTime(){
//        System.out.println ("Scheduling Tasks Examples: The time is now " + dateFormat ().format (new Date()));
//    }

    //每天7-22整点执行
//    @Scheduled(cron = "0 0 7-22  * * ? ")
//    public void reportCurrentByCron(){
//        System.out.println ("Scheduling Tasks Examples By Cron: The time is now " + dateFormat ().format (new Date()));
//    }


}
