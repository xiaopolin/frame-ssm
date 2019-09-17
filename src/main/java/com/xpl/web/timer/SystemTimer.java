package com.xpl.web.timer;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SystemTimer {

//    @Scheduled(cron = "0/5 * * * * ? ")
    public void speak(){
        System.out.println("定时任务执行时间：" + System.currentTimeMillis());
    }
}
