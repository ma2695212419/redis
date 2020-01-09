package co.laomag.redislogindemo.utils;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

/**
 * @author 马泽朋
 * @version 1.0
 * @date 2020/1/7 上午 9:48
 */
public class MyJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("我是简单的任务"+new Date());
    }
}
