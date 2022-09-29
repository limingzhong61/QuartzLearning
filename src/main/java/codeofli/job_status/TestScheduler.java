package codeofli.case2;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class TestScheduler {
    public static void main(String[] args) throws Exception {
        // 获取任务调度的实例
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        // 定义任务调度实例, 并与TestJob绑定
        JobDetail job = JobBuilder.newJob(TestJob.class)
                .usingJobData("testInfo", "jobDetail数据存放")
                .usingJobData("testJobDetail", "jobDetail数据存放")
                .withIdentity("testJob", "testJobGroup")
                .build();
        // 定义触发器, 会马上执行一次, 接着5秒执行一次
        Trigger trigger = TriggerBuilder.newTrigger()
                .usingJobData("testInfo", "jobDetail数据存放")
                .usingJobData("testTrigger", "trigger数据存放")
                .withIdentity("testTrigger", "testTriggerGroup")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(5))
                .build();
        // 使用触发器调度任务的执行
        scheduler.scheduleJob(job, trigger);
        // 开启任务
        scheduler.start();
    }
}
