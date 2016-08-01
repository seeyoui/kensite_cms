/*
 * Powered By cuichen
 * Since 2014 - 2016
 */
package com.seeyoui.kensite.framework.quartz.quartzConf.persistence;

import java.util.List;

import com.seeyoui.kensite.framework.quartz.quartzConf.domain.QuartzConf;

/**
 * 定时任务计划配置
 * @author cuichen
 * @version 1.0
 * @since 1.0
 * @date 2016-04-08
 */
public interface QuartzConfMapper {

	/**
	 * 根据ID查询单条数据
	 * @param id
	 * @return
	 */
	public QuartzConf findOne(String id);
	
	/**
	 * 查询数据集合
	 * @param quartzConf
	 * @return
	 */
	public List<QuartzConf> findList(QuartzConf quartzConf);
	
	/**
	 * 查询所有数据集合
	 * @param quartzConf
	 * @return
	 */
	public List<QuartzConf> findAll(QuartzConf quartzConf);
	
	/**
	 * 查询数据总数
	 * @param quartzConf
	 * @return
	 */
	public int findTotal(QuartzConf quartzConf);

	/**
	 * 查询数据总数排除当前数据
	 * @param quartzConf
	 * @return
	 */
	public int findExTotal(QuartzConf quartzConf);
	
	/**
	 * 数据新增
	 * @param quartzConf
	 */
	public void save(QuartzConf quartzConf);
	
	/**
	 * 数据修改
	 * @param quartzConf
	 */
	public void update(QuartzConf quartzConf);
	
	/**
	 * 数据删除
	 * @param listId
	 */
	public void delete(List<String> listId);
}