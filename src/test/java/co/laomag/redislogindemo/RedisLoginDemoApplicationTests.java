package co.laomag.redislogindemo;

import cn.hutool.core.date.DateTime;
import co.laomag.redislogindemo.dao.StudentDao;
import co.laomag.redislogindemo.pojo.Student;
import co.laomag.redislogindemo.utils.MD5;
import co.laomag.redislogindemo.utils.MyJob;
import co.laomag.redislogindemo.utils.RedisUtils;
import org.assertj.core.internal.bytebuddy.agent.builder.AgentBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.*;
import org.quartz.core.jmx.SimpleTriggerSupport;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.quartz.impl.triggers.SimpleTriggerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisLoginDemoApplicationTests {
    @Resource
    private StudentDao studentDao;

    @Resource
    private RedisUtils redisUtils;

    @Test
    public void contextLoads() throws InterruptedException {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");//设置日期格式
        while(true){
            Thread.sleep(1000);
            System.out.println(df.format(new Date()));
            if (df.format(new Date()).equals("08:52:10")){
                System.out.println("——————————");
            }
        }
    }

    @Test
    public void setAndGet() {
        redisUtils.set("1", "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoiMSIsImlhdCI6MTU3ODI5MDY1NH0.fQJh3lp-kdF5qjfm5k50Q4OHmMxRfU1K2ZQ4VNEEyQQ");
        System.out.println(redisUtils.get("1"));
    }

    @Test
    public void weeklyPlan() throws InterruptedException {
        SimpleDateFormat week = new SimpleDateFormat("EEEE");//设置日期格式
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");//设置日期格式
        String weekFormat = week.format(new Date());
        while(true){
            Thread.sleep(1000);
            if (weekFormat.equals("星期二")){

                if (df.format(new Date()).equals("09:09:10")){
                    System.out.println("——————————");
                }
            }
        }
    }
    @Test
    public void testQuarts() throws SchedulerException {
        //工作详细描述
        JobDetail jobDetail = new JobDetailImpl("t1", MyJob.class);

        Trigger trigger = new SimpleTriggerImpl("s1",SimpleTriggerImpl.REPEAT_INDEFINITELY ,2000);

        StdSchedulerFactory stdSchedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = stdSchedulerFactory.getScheduler();
        scheduler.scheduleJob(jobDetail, trigger);

        scheduler.start();



    }

    public static void main(String[] args) throws SchedulerException, ParseException {
        JobDetail jobDetail = new JobDetailImpl("t1", MyJob.class);

        //SimpleTriggerImpl trigger = new SimpleTriggerImpl("s1",SimpleTriggerImpl.REPEAT_INDEFINITELY ,2000);
        CronTriggerImpl trigger = new CronTriggerImpl("cron", "1", "*/10 39 * * * ?");
        StdSchedulerFactory stdSchedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = stdSchedulerFactory.getScheduler();
        scheduler.scheduleJob(jobDetail, trigger);


        scheduler.start();
    }
}
