package com.seeyoui.kensite.framework.security.shiro;

import java.util.List;

import net.sf.json.JSONArray;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.seeyoui.kensite.common.base.domain.TreeJson;
import com.seeyoui.kensite.common.util.SessionUtil;
import com.seeyoui.kensite.common.util.StringUtils;
import com.seeyoui.kensite.framework.system.domain.SysPermission;
import com.seeyoui.kensite.framework.system.domain.SysRole;
import com.seeyoui.kensite.framework.system.domain.SysUser;
import com.seeyoui.kensite.framework.system.util.UserUtils;

/**
 * 系统安全认证实现类
 * @author ken
 * @version 2015-3-27
 */
@Service
public class SystemAuthorizingRealm extends AuthorizingRealm {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 认证回调函数, 登录时调用
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		// 校验登录验证码
//		if (LoginController.isValidateCodeLogin(token.getUsername(), false, false)){
//			Session session = UserUtils.getSession();
//			String code = (String)session.getAttribute(ValidateCodeServlet.VALIDATE_CODE);
//			if (token.getCaptcha() == null || !token.getCaptcha().toUpperCase().equals(code)){
//				throw new AuthenticationException("msg:验证码错误, 请重试.");
//			}
//		}
		// 校验用户名密码
		SysUser user = UserUtils.getByLoginName(token.getUsername());
		if (user != null && StringUtils.isNoneBlank(user.getId())) {
			if(user.getState() == null || "".equals(user.getState()) || "0".equals(user.getState())) {
				throw new LockedAccountException(); //帐号锁定
			} else {
				AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(
						user.getUserName(), user.getPassword(), user.getName());
				SessionUtil.setSession("currentUser", user);
				SessionUtil.setSession("currentUserName", user.getUserName());
				TreeJson root = UserUtils.getMenuTree();
				if(root != null) {
					List<TreeJson> menuList = root.getChildren();
					SessionUtil.setSession("menuList", menuList);
					SessionUtil.setSession("menuTree", JSONArray.fromObject(menuList));
				}
				return authcInfo;
			}
		} else {
			throw new UnknownAccountException();//没找到帐号
		}
	}

	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String currentUsername = (String) super.getAvailablePrincipal(principals);
		SysUser user = UserUtils.getByLoginName(currentUsername);
		if (user != null) {
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			List<SysRole> sysRoleList = UserUtils.getRoleList();
			// 添加用户角色
			for (SysRole sysRole : sysRoleList){
				info.addRole(sysRole.getShiro());
			}
			// 添加用户权限
			List<SysPermission> sysPermissionList = UserUtils.getPermissionList();
			if(sysPermissionList != null) {
				for (SysPermission sysPermission : sysPermissionList){
					info.addStringPermission(sysPermission.getId());
				}
			}
			// 记录登录日志
			logger.info(currentUsername+"系统登录");
			return info;
		} else {
			return null;
		}
	}

	/**
	 * 获取系统业务对象
	 */
//	public SysUserService getSysUserService() {
//		if (sysUserService == null){
//			sysUserService = SpringContextHolder.getBean(SysUserService.class);
//		}
//		return sysUserService;
//	}
}
