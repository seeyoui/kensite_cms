package com.seeyoui.kensite.common.listener;

import java.util.List;

import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import com.seeyoui.kensite.common.util.DateUtils;
import com.seeyoui.kensite.common.util.StringUtils;
import com.seeyoui.kensite.framework.quartz.constants.QuartzConstants;
import com.seeyoui.kensite.framework.quartz.quartzConf.domain.QuartzConf;
import com.seeyoui.kensite.framework.quartz.quartzConf.service.QuartzConfService;
import com.seeyoui.kensite.framework.quartz.util.ScheduleUtils;

/**
 * 启动监听器
 *
 * @author ken
 */
@Service
public class StartupListener implements ApplicationListener<ContextRefreshedEvent> {
	
	@Autowired
    private Scheduler scheduler;
	
	@Autowired
	private QuartzConfService quartzConfService;
	
    @Override
    public void onApplicationEvent(ContextRefreshedEvent evt) {
    	try {
			scheduler.start();
			System.err.println("******************");
	    	System.err.println("==============");
	    	System.err.println("定时任务计划scheduler开始工作："+DateUtils.getDateTime()+"["+scheduler+"]");
	    	List<QuartzConf> quartzList = quartzConfService.findAll(new QuartzConf());
	    	for(QuartzConf quartz : quartzList) {
	    		CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(scheduler, quartz.getJobName(),
	    				quartz.getJobGroup());
	    		//不存在，创建一个
	            if (cronTrigger == null) {
	                ScheduleUtils.createScheduleJob(scheduler, quartz);
	    	    	System.err.println("创建定时任务计划成功"+DateUtils.getDateTime()+"["+quartz.getJobName()+","+quartz.getJobGroup()+"]");
	            } else {
	                //已存在，那么更新相应的定时设置
	                ScheduleUtils.updateScheduleJob(scheduler, quartz);
	    	    	System.err.println("更新定时任务计划成功"+DateUtils.getDateTime()+"["+quartz.getJobName()+","+quartz.getJobGroup()+"]");
	            }
	            if(StringUtils.isNoneBlank(quartz.getStatus()) && QuartzConstants.PAUSED.equals(quartz.getStatus())) {
	            	ScheduleUtils.pauseJob(scheduler, quartz.getJobName(), quartz.getJobGroup());
	    	    	System.err.println("停止任务计划成功"+DateUtils.getDateTime()+"["+quartz.getJobName()+","+quartz.getJobGroup()+"]");
	            }
	    	}
	    	System.err.println("==============");
	    	System.err.println("******************");
		} catch (SchedulerException e) {
			e.printStackTrace();
			System.err.println("******************");
	    	System.err.println("==============");
	    	System.err.println("定时任务计划scheduler异常："+DateUtils.getDateTime());
	    	System.err.println("==============");
	    	System.err.println("******************");
		}
    	
    	
    }
}
