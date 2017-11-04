package com.hiekn.demo.quartz;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MyJob {
	
    @Scheduled(cron = "0 23 11 * * ?")
	public void work() {  
        System.out.println(new Date());  
    } 
}
