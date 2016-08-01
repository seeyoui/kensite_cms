/*
 * Powered By cuichen
 * Since 2014 - 2015
 */package com.seeyoui.kensite.framework.system.service;  
 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seeyoui.kensite.common.base.domain.Attributes;
import com.seeyoui.kensite.common.base.domain.TreeJson;
import com.seeyoui.kensite.common.base.service.BaseService;
import com.seeyoui.kensite.common.constants.StringConstant;
import com.seeyoui.kensite.common.exception.CRUDException;
import com.seeyoui.kensite.common.util.StringUtils;
import com.seeyoui.kensite.framework.system.domain.SysModule;
import com.seeyoui.kensite.framework.system.domain.SysRoleModule;
import com.seeyoui.kensite.framework.system.persistence.SysRoleModuleMapper;

/**
 * @author cuichen
 * @version 1.0
 * @since 1.0
 */
@Service
public class SysRoleModuleService extends BaseService {
	
	@Autowired
	private SysRoleModuleMapper sysRoleModuleMapper;

	/**
	 * 查询数据TREE
	 * @param username
	 * @return
	 * @throws CRUDException
	 */
	public List<TreeJson> tree(SysRoleModule sysRoleModule) throws CRUDException {
		List<SysModule> mList = sysRoleModuleMapper.tree(sysRoleModule);
		List<TreeJson> tList = new ArrayList<TreeJson>();
		for(int i=0; i<mList.size(); i++) {
			TreeJson tj = new TreeJson();
			tj.setId(mList.get(i).getId());
			tj.setText(mList.get(i).getName());
			tj.setPid(StringConstant.ROOT_ID_32);
			tj.setChecked(mList.get(i).getShiro());
			Attributes attributes = new Attributes();
			tj.setAttributes(attributes);
			tList.add(tj);
		}
		TreeJson root = new TreeJson();
		root.setId(StringConstant.ROOT_ID_32);
		TreeJson.getTree(tList, root);
		return root.getChildren();
	}
	
	/**
	 * 数据新增
	 * @param sysRoleModule
	 * @throws CRUDException
	 */
	public void save(SysRoleModule sysRoleModule) throws CRUDException {
		sysRoleModuleMapper.delete(sysRoleModule.getRoleId());
		if(sysRoleModule.getModuleId() == null || StringUtils.isBlank(sysRoleModule.getModuleId())) {
			return;
		}
		List<String> listId = Arrays.asList(sysRoleModule.getModuleId().split(","));
		for(int i=0; i<listId.size(); i++) {
			sysRoleModule.setModuleId(listId.get(i));
			sysRoleModuleMapper.save(sysRoleModule);
		}
	}
}