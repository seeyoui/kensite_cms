/*
 * Powered By cuichen
 * Since 2014 - 2016
 */package com.seeyoui.kensite.framework.quartz.quartzConf.service;
 
 import java.util.List;

import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seeyoui.kensite.common.base.service.BaseService;
import com.seeyoui.kensite.common.exception.CRUDException;
import com.seeyoui.kensite.framework.quartz.constants.QuartzConstants;
import com.seeyoui.kensite.framework.quartz.quartzConf.domain.QuartzConf;
import com.seeyoui.kensite.framework.quartz.quartzConf.persistence.QuartzConfMapper;
import com.seeyoui.kensite.framework.quartz.util.ScheduleUtils;

/**
 * 定时任务计划配置
 * @author cuichen
 * @version 1.0
 * @since 1.0
 * @date 2016-04-08
 */
@Service
public class QuartzConfService extends BaseService {
	
	@Autowired
	private QuartzConfMapper quartzConfMapper;
	
    @Autowired
    private Scheduler scheduler;

	/**
	 * 根据ID查询单条数据
	 * @param id
	 * @return
	 * @throws CRUDException
	 */
	public QuartzConf findOne(String id) throws CRUDException{
		return quartzConfMapper.findOne(id);
	}
	
	/**
	 * 查询数据集合
	 * @param quartzConf
	 * @return
	 * @throws CRUDException
	 */
	public List<QuartzConf> findList(QuartzConf quartzConf) throws CRUDException {
		return quartzConfMapper.findList(quartzConf);
	}
	
	/**
	 * 查询所有数据集合
	 * @param quartzConf
	 * @return
	 * @throws CRUDException
	 */
	public List<QuartzConf> findAll(QuartzConf quartzConf) throws CRUDException {
		return quartzConfMapper.findAll(quartzConf);
	}
	
	/**
	 * 查询数据总数
	 * @param quartzConf
	 * @return
	 * @throws CRUDException
	 */
	public int findTotal(QuartzConf quartzConf) throws CRUDException {
		return quartzConfMapper.findTotal(quartzConf);
	}
	
	/**
	 * 查询数据总数排除当前数据
	 * @param quartzConf
	 * @return
	 * @throws CRUDException
	 */
	public int findExTotal(QuartzConf quartzConf) throws CRUDException {
		return quartzConfMapper.findExTotal(quartzConf);
	}
	
	/**
	 * 数据新增
	 * @param quartzConf
	 * @throws CRUDException
	 */
	public void save(QuartzConf quartzConf) throws CRUDException, Exception{
		quartzConf.preInsert();
		quartzConf.setJobTrigger("trigger"+quartzConf.getJobName());
		quartzConf.setStatus(QuartzConstants.NORMAL);
		ScheduleUtils.createScheduleJob(scheduler, quartzConf);
		quartzConfMapper.save(quartzConf);
	}
	
	/**
	 * 数据修改
	 * @param quartzConf
	 * @throws CRUDException
	 */
	public void update(QuartzConf quartzConf) throws CRUDException{
		quartzConf.preUpdate();
		quartzConf.setJobTrigger("trigger"+quartzConf.getJobName());
		ScheduleUtils.updateScheduleJob(scheduler, quartzConf);
		quartzConfMapper.update(quartzConf);			
	}
	
	/**
	 * 数据删除
	 * @param listId
	 * @throws CRUDException
	 */
	public void delete(List<String> listId) throws CRUDException {
		for(String id : listId) {
			QuartzConf quartzConf = quartzConfMapper.findOne(id);
			ScheduleUtils.deleteScheduleJob(scheduler, quartzConf.getJobName(), quartzConf.getJobGroup());
		}
		quartzConfMapper.delete(listId);
	}
	
	/**
	 * 暂停任务
	 * @param id
	 * @throws CRUDException
	 */
	public void pause(String id) throws CRUDException {
		QuartzConf quartzConf = quartzConfMapper.findOne(id);
		ScheduleUtils.pauseJob(scheduler, quartzConf.getJobName(), quartzConf.getJobGroup());
		quartzConf.setStatus(QuartzConstants.PAUSED);
		quartzConf.preUpdate();
		quartzConfMapper.update(quartzConf);
	}
	
	/**
	 * 恢复任务
	 * @param id
	 * @throws CRUDException
	 */
	public void resume(String id) throws CRUDException {
		QuartzConf quartzConf = quartzConfMapper.findOne(id);
		ScheduleUtils.resumeJob(scheduler, quartzConf.getJobName(), quartzConf.getJobGroup());
		quartzConf.setStatus(QuartzConstants.NORMAL);
		quartzConf.preUpdate();
		quartzConfMapper.update(quartzConf);
	}
	
	/**
	 * 运行一次任务
	 * @param id
	 * @throws CRUDException
	 */
	public void runOnce(String id) throws CRUDException {
		QuartzConf quartzConf = quartzConfMapper.findOne(id);
		ScheduleUtils.runOnce(scheduler, quartzConf.getJobName(), quartzConf.getJobGroup());
		quartzConf.setStatus(QuartzConstants.NORMAL);
	}
	
}