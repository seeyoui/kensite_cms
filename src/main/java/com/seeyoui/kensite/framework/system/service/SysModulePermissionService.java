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
import com.seeyoui.kensite.framework.system.domain.SysModulePermission;
import com.seeyoui.kensite.framework.system.domain.SysPermission;
import com.seeyoui.kensite.framework.system.persistence.SysModulePermissionMapper;

/**
 * @author cuichen
 * @version 1.0
 * @since 1.0
 */
@Service
public class SysModulePermissionService extends BaseService {
	
	@Autowired
	private SysModulePermissionMapper sysModulePermissionMapper;

	/**
	 * 查询数据TREE
	 * @param username
	 * @return
	 * @throws CRUDException
	 */
	public List<TreeJson> tree(SysModulePermission sysModulePermission) throws CRUDException {
		List<SysPermission> mList = sysModulePermissionMapper.tree(sysModulePermission);
		List<TreeJson> tList = new ArrayList<TreeJson>();
		for(int i=0; i<mList.size(); i++) {
			TreeJson tj = new TreeJson();
			tj.setId(mList.get(i).getId());
			tj.setText(mList.get(i).getName());
			tj.setPid(StringConstant.ROOT_ID_32);
			tj.setChecked(mList.get(i).getChecked());
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
	 * @param sysModulePermission
	 * @throws CRUDException
	 */
	public void save(SysModulePermission sysModulePermission) throws CRUDException {
		sysModulePermissionMapper.delete(sysModulePermission.getModuleId());
		String mpid = sysModulePermission.getPermissionId();
		if(StringUtils.isNotBlank(mpid)) {
			List<String> listId = Arrays.asList(mpid.split(","));
			for(int i=0; i<listId.size(); i++) {
				sysModulePermission.setPermissionId(listId.get(i));
				sysModulePermissionMapper.save(sysModulePermission);
			}
		}
	}
	
}