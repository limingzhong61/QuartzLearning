package codeofli.trigger;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;

@Slf4j
@PersistJobDataAfterExecution
public class TestTriggerJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Trigger trigger = jobExecutionContext.getTrigger();
        log.info("start time : {}, end time: {}",
                trigger.getStartTime(), trigger.getEndTime());
    }
}
/** OUTPUT:
 execute count: 1, current time: 2020-11-17 22:28:48
 execute count: 2, current time: 2020-11-17 22:28:52
 execute count: 3, current time: 2020-11-17 22:28:57
 **/
