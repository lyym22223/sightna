package com.sweblim.schedule;

import com.sweblim.entity.Agent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ScheduleSet{
    @Autowired
    Agent agent;
    @Scheduled(cron = "*/5 * * * * ?")
    public void scheduleAgentRun1(){
        agent.run1();
        System.out.println("=======执行了run1=====" + new Date());
    }
    @Scheduled(cron = "* */60 * * * ?")
    public void scheduleAgentRun2(){
        agent.run2();
    }

}
