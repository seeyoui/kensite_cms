package com.seeyoui.kensite.framework.system.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.seeyoui.kensite.common.base.domain.Attributes;
import com.seeyoui.kensite.common.base.domain.TreeJson;
import com.seeyoui.kensite.common.constants.StringConstant;
import com.seeyoui.kensite.common.util.CacheUtils;
import com.seeyoui.kensite.common.util.SpringContextHolder;
import com.seeyoui.kensite.framework.system.domain.SysDepartment;
import com.seeyoui.kensite.framework.system.domain.SysMenu;
import com.seeyoui.kensite.framework.system.domain.SysPermission;
import com.seeyoui.kensite.framework.system.domain.SysRole;
import com.seeyoui.kensite.framework.system.domain.SysUser;
import com.seeyoui.kensite.framework.system.persistence.SysDepartmentMapper;
import com.seeyoui.kensite.framework.system.persistence.SysMenuMapper;
import com.seeyoui.kensite.framework.system.persistence.SysPermissionMapper;
import com.seeyoui.kensite.framework.system.persistence.SysRoleMapper;
import com.seeyoui.kensite.framework.system.persistence.SysUserMapper;

public class UserUtils {

	private static SysUserMapper sysUserMapper = SpringContextHolder.getBean(SysUserMapper.class);
	private static SysRoleMapper sysRoleMapper = SpringContextHolder.getBean(SysRoleMapper.class);
	private static SysMenuMapper sysMenuMapper = SpringContextHolder.getBean(SysMenuMapper.class);
	private static SysPermissionMapper sysPermissionMapper = SpringContextHolder.getBean(SysPermissionMapper.class);
	private static SysDepartmentMapper sysDepartmentMapper = SpringContextHolder.getBean(SysDepartmentMapper.class);

	public static final String USER_CACHE = "userCache";
	public static final String USER_CACHE_ID_ = "id_";
	public static final String USER_CACHE_LOGIN_NAME_ = "ln";
	public static final String USER_CACHE_LIST_BY_DEPARTMENT_ID_ = "deptid_";

	public static final String CACHE_ROLE_LIST = "roleList";
	public static final String CACHE_PERMISSION_LIST = "permissionList";
	public static final String CACHE_MENU_LIST = "menuList";
	public static final String CACHE_MENU_TREE = "menuTree";
	public static final String CACHE_DEPARTMENT_LIST = "deptList";
	
	/**
	 * 根据ID获取用户
	 * @param id
	 * @return 取不到返回null
	 */
	public static SysUser getById(String id){
		SysUser sysUser = (SysUser)CacheUtils.get(USER_CACHE, USER_CACHE_ID_ + id);
		if (sysUser ==  null){
			sysUser = sysUserMapper.findOne(id);
			if (sysUser == null){
				return null;
			}
			sysUser.setRoleList(sysRoleMapper.findSysUserList(sysUser));
			CacheUtils.put(USER_CACHE, USER_CACHE_ID_ + sysUser.getId(), sysUser);
			CacheUtils.put(USER_CACHE, USER_CACHE_LOGIN_NAME_ + sysUser.getUserName(), sysUser);
		}
		return sysUser;
	}
	
	/**
	 * 根据登录名获取用户
	 * @param loginName
	 * @return 取不到返回null
	 */
	public static SysUser getByLoginName(String loginName){
		SysUser sysUser = (SysUser)CacheUtils.get(USER_CACHE, USER_CACHE_LOGIN_NAME_ + loginName);
		if (sysUser == null){
			sysUser = sysUserMapper.findByUserName(loginName);
			if (sysUser == null){
				return null;
			}
			sysUser.setRoleList(sysRoleMapper.findSysUserList(sysUser));
			CacheUtils.put(USER_CACHE, USER_CACHE_ID_ + sysUser.getId(), sysUser);
			CacheUtils.put(USER_CACHE, USER_CACHE_LOGIN_NAME_ + sysUser.getUserName(), sysUser);
		}
		return sysUser;
	}
	
	/**
	 * 清除当前用户缓存
	 */
	public static void clearCache(){
		UserUtils.clearCache(getUser());
	}
	
	/**
	 * 清除指定用户缓存
	 * @param sysUser
	 */
	public static void clearCache(SysUser sysUser){
		CacheUtils.remove(USER_CACHE, USER_CACHE_ID_ + sysUser.getId());
		CacheUtils.remove(USER_CACHE, USER_CACHE_LOGIN_NAME_ + sysUser.getUserName());
		removeCache(CACHE_ROLE_LIST + sysUser.getId());
		removeCache(CACHE_MENU_LIST + sysUser.getId());
		removeCache(CACHE_DEPARTMENT_LIST + sysUser.getId());
		removeCache(CACHE_ROLE_LIST + sysUser.getUserName());
		removeCache(CACHE_MENU_LIST + sysUser.getUserName());
		removeCache(CACHE_DEPARTMENT_LIST + sysUser.getUserName());
		if (sysUser.getDepartmentId() != null){
			CacheUtils.remove(USER_CACHE, USER_CACHE_LIST_BY_DEPARTMENT_ID_ + sysUser.getDepartmentId());
		}
	}
	
	/**
	 * 获取当前用户
	 * @return 取不到返回 new SysUser()
	 */
	public static SysUser getUser(){
		SysUser sysUser = (SysUser)getSession().getAttribute("currentUser");
		if (sysUser == null){
			String currentUserName = (String)getSession().getAttribute("currentUserName");
			if(currentUserName == null) {
				return new SysUser();
			}
			sysUser = getByLoginName(currentUserName);
			if (sysUser != null){
				return sysUser;
			}
		} else {
			return sysUser;
		}
		// 如果没有登录，则返回实例化空的User对象。
		return new SysUser();
	}

	/**
	 * 获取当前用户角色列表
	 * @return
	 */
	public static List<SysRole> getRoleList(){
		SysUser sysUser = getUser();
		@SuppressWarnings("unchecked")
		List<SysRole> roleList = (List<SysRole>)getCache(CACHE_ROLE_LIST + sysUser.getId());
		if (roleList == null){
			roleList = sysRoleMapper.findSysUserList(sysUser);
			putCache(CACHE_ROLE_LIST + sysUser.getId(), roleList);
			putCache(CACHE_ROLE_LIST + sysUser.getUserName(), roleList);
		}
		return roleList;
	}
	
	public static List<SysPermission> getPermissionList(){
		SysUser sysUser = getUser();
		@SuppressWarnings("unchecked")
		List<SysPermission> permissionList = (List<SysPermission>)getCache(CACHE_PERMISSION_LIST + sysUser.getId());
		if (permissionList == null){
			permissionList = sysPermissionMapper.findSysUserList(sysUser);
			putCache(CACHE_PERMISSION_LIST + sysUser.getId(), permissionList);
			putCache(CACHE_PERMISSION_LIST + sysUser.getUserName(), permissionList);
		}
		return permissionList;
	}
	
	/**
	 * 获取当前用户授权菜单
	 * @return
	 */
	public static List<SysMenu> getMenuList(){
		SysUser sysUser = getUser();
		@SuppressWarnings("unchecked")
		List<SysMenu> menuList = (List<SysMenu>)getCache(CACHE_MENU_LIST + sysUser.getId());
		if (menuList == null){
			menuList = sysMenuMapper.findTree(sysUser);
			putCache(CACHE_MENU_LIST + sysUser.getId(), menuList);
			putCache(CACHE_MENU_LIST + sysUser.getUserName(), menuList);
		}
		return menuList;
	}
	
	public static TreeJson getMenuTree(){
		SysUser sysUser = getUser();
		@SuppressWarnings("unchecked")
		TreeJson menuTree = (TreeJson)getCache(CACHE_MENU_TREE + sysUser.getId());
		if (menuTree == null){
			List<SysMenu> mList = sysMenuMapper.findTree(sysUser);
			ArrayList<TreeJson> tList = new ArrayList<TreeJson>();
			for(int i=0; i<mList.size(); i++) {
				TreeJson tj = new TreeJson();
				tj.setId(mList.get(i).getId());
				tj.setPid(mList.get(i).getParentId());
				tj.setText(mList.get(i).getName());
				Attributes attributes = new Attributes();
				attributes.setUrl(mList.get(i).getUrl());
				attributes.setIcon(mList.get(i).getIcon());
				tj.setAttributes(attributes);
				tList.add(tj);
			}
			menuTree = new TreeJson();
			menuTree.setText("导航菜单");
			menuTree.setId(StringConstant.ROOT_ID_32);
			TreeJson.getTree(tList, menuTree);
			tList.clear();
			tList.add(menuTree);
			putCache(CACHE_MENU_TREE + sysUser.getId(), menuTree);
			putCache(CACHE_MENU_TREE + sysUser.getName(), menuTree);
		}
		return menuTree;
	}
	
	/**
	 * 获取当前用户有权限访问的部门
	 * @return
	 */
	public static List<SysDepartment> getDepartmentList(){
		@SuppressWarnings("unchecked")
		List<SysDepartment> officeList = (List<SysDepartment>)getCache(CACHE_DEPARTMENT_LIST);
		if (officeList == null){
			SysUser sysUser = getUser();
//			if (sysUser.isAdmin()){
//				officeList = sysDepartmentMapper.findAllList(new SysDepartment());
//			}else{
//				officeList = sysDepartmentMapper.findList(office);
//			}
			putCache(CACHE_DEPARTMENT_LIST, officeList);
		}
		return officeList;
	}
	
	/**
	 * 获取授权主要对象
	 */
	public static Subject getSubject(){
		return SecurityUtils.getSubject();
	}
	
	public static Session getSession(){
		try{
			Subject subject = SecurityUtils.getSubject();
			Session session = subject.getSession(false);
			if (session == null){
				session = subject.getSession();
			}
			if (session != null){
				return session;
			}
		}catch (InvalidSessionException e){
			
		}
		return null;
	}
	
	// ============== SysUser Cache ==============
	
	public static Object getCache(String key) {
		return getCache(key, null);
	}
	
	public static Object getCache(String key, Object defaultValue) {
//		Object obj = CacheUtils.get(USER_CACHE, key);
		Object obj = getSession().getAttribute(key);
		return obj==null?defaultValue:obj;
	}

	public static void putCache(String key, Object value) {
//		CacheUtils.put(USER_CACHE, key, value);
		getSession().setAttribute(key, value);
	}

	public static void removeCache(String key) {
//		CacheUtils.remove(USER_CACHE, key);
		getSession().removeAttribute(key);
	}	
}
