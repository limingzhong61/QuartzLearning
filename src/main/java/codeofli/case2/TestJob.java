package codeofli.case1;

import org.quartz.Job;
import org.quartz.JobExecutionContext;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        String data = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println("START DATA BACKUP, current time ：" + data);
        System.out.println(jobExecutionContext.getJobDetail()
                .getJobDataMap().get("testJobDetail"));
        System.out.println(jobExecutionContext.getTrigger()
                .getJobDataMap().get("testTrigger"));
    }
}
