/*
 * Powered By cuichen
 * Since 2014 - 2015
 */
package com.seeyoui.kensite.framework.quartz.controller;  
 
import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.Date;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.seeyoui.kensite.common.base.controller.BaseController;
import com.seeyoui.kensite.common.util.DateUtils;
/**
 * 定时任务计划
 * @author cuichen
 * @version 1.0
 * @since 1.0
 * @date 2015-10-20
 */
@Controller
@RequestMapping(value = "quartz")
public class QuartzController extends BaseController {
	
    @Autowired
    private Scheduler scheduler;
	
	/**
	 * 展示列表页面
	 * @param modelMap
	 * @param module
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/{page}")
	public ModelAndView view(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, @PathVariable String page) throws Exception {
		return new ModelAndView("framework/plugin/db/"+page, modelMap);
	}
	
	/**
	 * 获取当前存在的任务
	 * @param session
	 * @param response
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/job/executing")
	@ResponseBody
	public Object executing(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap) throws Exception{
//		List<ScheduleJobVo> executingJobList = scheduleJobService.queryExecutingJobList();
//        return executingJobList;
		
//		GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
//		Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
//		System.out.println(jobKeys);
		
		
//		JobDetail job = newJob(SimpleJob.class).withIdentity("job1", "group1").build();
//	    scheduler.start();
		System.out.println("=========");
		Class jobClass = Class.forName("com.seeyoui.kensite.framework.quartz.job.SimpleJob");
		JobDetail job = newJob(jobClass).withIdentity("job1", "group1").build();
	    CronTrigger trigger = newTrigger().withIdentity("trigger1", "group1").withSchedule(cronSchedule("0/10 * * * * ?"))
	        .build();
	    Date ft = scheduler.scheduleJob(job, trigger);
	    System.out.println(job.getKey() + " has been scheduled to run at: " + ft + " and repeat based on expression: "
	             + trigger.getCronExpression());
	    
		GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
		Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
		System.out.println(jobKeys);
		return jobKeys;
	}
	
	@RequestMapping(value = "/job/shutdown")
	@ResponseBody
	public Object shutdown(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap) throws Exception{
	    System.out.println("scheduler has been scheduled to shutdown at: " + DateUtils.getDate());
	    scheduler.shutdown();
		return null;
	}
	
	@RequestMapping(value = "/job/runOnce")
	@ResponseBody
	public Object runOnce(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap) throws Exception{
		System.out.println("立即执行开始");
		JobKey jobKey = JobKey.jobKey("job1", "group1");
		System.out.println("立即执行完毕");
        try {
            scheduler.triggerJob(jobKey);
        } catch (SchedulerException e) {
        	System.err.println("运行一次定时任务失败");
            throw new SchedulerException("运行一次定时任务失败");
        }
		return null;
	}
}