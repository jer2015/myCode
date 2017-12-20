package com.tom.business.quartz;


import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author lxl
 */
public class QuartzJobFactory implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

    }

    // private static final Logger LOGGER = LoggerFactory.getLogger(QuartzJobFactory.class);
    // private final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    //
    // @Autowired
    // private IUserService userService;
    //
    // @Autowired
    // private ScheduleJobMapper scheduleJobMapper;
    //
    // @Override
    // public void execute(JobExecutionContext context) throws JobExecutionException {
    //     //获取job信息
    //     ScheduleJob scheduleJob = (ScheduleJob) context.getMergedJobDataMap().get("scheduleJob");
    //     //根据job名称指定对应任务(service)
    //     QuartzJobEnum quartzJobEnum = QuartzJobEnum.valueOf(scheduleJob.getJobName());
    //     switch (quartzJobEnum) {
    //         case TEST_JOB:
    //             User user = userService.selectUser(1);
    //             user.setUpdateTime(new Date());
    //             Integer integer = userService.updateUser(user);
    //             User userNew = userService.selectUser(1);
    //             LOGGER.info("任务名称[{}], cron:{}, updateTime:{}, 运行成功:{}", scheduleJob.getJobName(), scheduleJob
    //                     .getJobCron(), format.format(userNew.getUpdateTime()), integer > 0);
    //             break;
    //         default:
    //             LOGGER.info("任务名称[{}],相关任务未定义", scheduleJob.getJobName());
    //             break;
    //     }
    // }
}
