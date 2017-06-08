package com.seeyoui.kensite.framework.oa.leave.persistence;

import java.util.List;

import com.seeyoui.kensite.common.base.domain.EasyUIDataGrid;
import com.seeyoui.kensite.common.exception.CRUDException;
import com.seeyoui.kensite.framework.oa.leave.domain.Leave;

public interface LeaveMapper {
	
	/**
	 * 根据ID查询单条数据
	 * @param id
	 * @return
	 */
	public Leave findLeaveById(String id);
	
	/**
	 * 查询数据集合
	 * @param leave
	 * @return
	 */
	public List<Leave> findLeaveList(Leave leave);
	
	/**
	 * 查询数据总数
	 * @param userinfo
	 * @return
	 */
	public EasyUIDataGrid findLeaveListTotal(Leave leave);
	
	/**
	 * 数据新增
	 * @param leave
	 */
	public void saveLeave(Leave leave);
	
	/**
	 * 数据修改
	 * @param leave
	 */
	public void updateLeave(Leave leave);
	
	/**
	 * 数据删除
	 * @param listId
	 */
	public void deleteLeave(List<String> listId);
	
	/**
	 * 更新流程实例ID
	 * @param leave
	 * @return
	 */
	public int updateBindid(Leave leave);
	
}
