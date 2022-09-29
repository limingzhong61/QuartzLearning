package codeofli.trigger;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

public class TestTriggerScheduler {
    public static void main(String[] args) throws Exception {

        // 获取任务调度的实例
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        // 我们在 JobDataMap 中定义了一个值为 0 的初始值
        JobDetail jobDetail = JobBuilder.newJob(TestTriggerJob.class)
                .usingJobData("executeCount", 0)
                .withIdentity("testJob", "testJobGroup")
                .build();

        // 定义触发器, 会马上执行一次, 接着5秒执行一次
        Date startTime = new Date();
        startTime.setTime(startTime.getTime() + 5000);
        Date endTime = new Date();
        endTime.setTime(startTime.getTime() + 10000);
        Trigger trigger = TriggerBuilder.newTrigger()
                .usingJobData("testInfo", "trigger数据存放")
                .withIdentity("testTrigger", "testTriggerGroup")
                .startNow()
                .startAt(startTime)
                .endAt(endTime)
                .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(5))
                .build();
        // 使用触发器调度任务的执行
        scheduler.scheduleJob(jobDetail, trigger);
        // 开启任务
        scheduler.start();
    }
}
