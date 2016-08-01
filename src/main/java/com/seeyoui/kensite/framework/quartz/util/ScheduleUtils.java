package com.seeyoui.kensite.framework.quartz.util;

import static org.quartz.DateBuilder.evenMinuteDate;

import java.util.Date;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.seeyoui.kensite.framework.quartz.quartzConf.domain.QuartzConf;

/**
 * 定时任务辅助类
 * 
 * Created by liyd on 12/19/14.
 */
public class ScheduleUtils {

    /** 日志对象 */
    private static final Logger LOG = LoggerFactory.getLogger(ScheduleUtils.class);

    /**
     * 获取触发器key
     * 
     * @param jobName
     * @param jobGroup
     * @return
     */
    public static TriggerKey getTriggerKey(String jobName, String jobGroup) {

        return TriggerKey.triggerKey(jobName, jobGroup);
    }

    /**
     * 获取表达式触发器
     *
     * @param scheduler the scheduler
     * @param jobName the job name
     * @param jobGroup the job group
     * @return cron trigger
     */
    public static CronTrigger getCronTrigger(Scheduler scheduler, String jobName, String jobGroup) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
            return (CronTrigger) scheduler.getTrigger(triggerKey);
        } catch (SchedulerException e) {
            LOG.error("获取定时任务CronTrigger出现异常", e);
        }
        return null;
    }

    /**
     * 创建任务
     *
     * @param scheduler the scheduler
     * @param scheduleJob the schedule job
     */
    public static void createScheduleJob(Scheduler scheduler, QuartzConf quartzConf) {
        createScheduleJob(scheduler, quartzConf.getJobName(), quartzConf.getJobGroup(),
        		quartzConf.getCronExpression(), quartzConf.getStartAt(), quartzConf.getEndAt(), quartzConf);
    }

    /**
     * 创建定时任务
     *
     * @param scheduler the scheduler
     * @param jobName the job name
     * @param jobGroup the job group
     * @param cronExpression the cron expression
     * @param isSync the is sync
     * @param param the param
     */
    public static void createScheduleJob(Scheduler scheduler, String jobName, String jobGroup,
                                         String cronExpression, Date startAt, Date endAt, QuartzConf quartzConf) {
    	Class jobClass = null;
		try {
			jobClass = Class.forName(quartzConf.getJobClass());
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
        //构建job信息
        JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroup).build();

        //放入参数，运行时的方法可以获取
//        jobDetail.getJobDataMap().put("", "");

        //表达式调度构建器
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

        //按新的cronExpression表达式构建一个新的trigger
        Date startAtTime = evenMinuteDate(new Date());
        if(startAt != null) {
        	startAtTime = evenMinuteDate(startAt);
        }
        Date endAtTime = evenMinuteDate(new Date(25000000000000l));
        if(endAt != null) {
        	endAtTime = evenMinuteDate(endAt);
        }
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName, jobGroup)
            .withSchedule(scheduleBuilder).startAt(startAtTime).endAt(endAtTime).build();

        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            LOG.error("创建定时任务失败", e);
        }
    }

    /**
     * 运行一次任务
     * 
     * @param scheduler
     * @param jobName
     * @param jobGroup
     */
    public static void runOnce(Scheduler scheduler, String jobName, String jobGroup) {
        JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
        try {
            scheduler.triggerJob(jobKey);
        } catch (SchedulerException e) {
            LOG.error("运行一次定时任务失败", e);
        }
    }

    /**
     * 暂停任务
     * 
     * @param scheduler
     * @param jobName
     * @param jobGroup
     */
    public static void pauseJob(Scheduler scheduler, String jobName, String jobGroup) {

        JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
        try {
            scheduler.pauseJob(jobKey);
        } catch (SchedulerException e) {
            LOG.error("暂停定时任务失败", e);
        }
    }

    /**
     * 恢复任务
     *
     * @param scheduler
     * @param jobName
     * @param jobGroup
     */
    public static void resumeJob(Scheduler scheduler, String jobName, String jobGroup) {

        JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
        try {
            scheduler.resumeJob(jobKey);
        } catch (SchedulerException e) {
            LOG.error("暂停定时任务失败", e);
        }
    }

    /**
     * 获取jobKey
     *
     * @param jobName the job name
     * @param jobGroup the job group
     * @return the job key
     */
    public static JobKey getJobKey(String jobName, String jobGroup) {
        return JobKey.jobKey(jobName, jobGroup);
    }

    /**
     * 更新定时任务
     *
     * @param scheduler the scheduler
     * @param scheduleJob the schedule job
     */
    public static void updateScheduleJob(Scheduler scheduler, QuartzConf quartzConf) {
        updateScheduleJob(scheduler, quartzConf.getJobName(), quartzConf.getJobGroup(),
        		quartzConf.getCronExpression(), quartzConf.getStartAt(), quartzConf.getEndAt(), quartzConf);
    }

    /**
     * 更新定时任务
     *
     * @param scheduler the scheduler
     * @param jobName the job name
     * @param jobGroup the job group
     * @param cronExpression the cron expression
     * @param isSync the is sync
     * @param param the param
     */
    public static void updateScheduleJob(Scheduler scheduler, String jobName, String jobGroup,
                                         String cronExpression, Date startAt, Date endAt, QuartzConf quartzConf) {
        try {
//            JobDetail jobDetail = scheduler.getJobDetail(getJobKey(jobName, jobGroup));
//            jobDetail = jobDetail.getJobBuilder().ofType(jobClass).build();
            //更新参数 实际测试中发现无法更新
//            JobDataMap jobDataMap = jobDetail.getJobDataMap();
//            jobDataMap.put(ScheduleJobVo.JOB_PARAM_KEY, param);
//            jobDetail.getJobBuilder().usingJobData(jobDataMap);

            TriggerKey triggerKey = ScheduleUtils.getTriggerKey(jobName, jobGroup);
            //表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            //按新的cronExpression表达式重新构建trigger
            Date startAtTime = evenMinuteDate(new Date());
            if(startAt != null) {
            	startAtTime = evenMinuteDate(startAt);
            }
            Date endAtTime = evenMinuteDate(new Date(25000000000000l));
            if(endAt != null) {
            	endAtTime = evenMinuteDate(endAt);
            }
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).startAt(startAtTime).endAt(endAtTime)
                .build();
            //按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (SchedulerException e) {
            LOG.error("更新定时任务失败", e);
        }
    }

    /**
     * 删除定时任务
     *
     * @param scheduler
     * @param jobName
     * @param jobGroup
     */
    public static void deleteScheduleJob(Scheduler scheduler, String jobName, String jobGroup) {
        try {
            scheduler.deleteJob(getJobKey(jobName, jobGroup));
        } catch (SchedulerException e) {
            LOG.error("删除定时任务失败", e);
        }
    }
}
