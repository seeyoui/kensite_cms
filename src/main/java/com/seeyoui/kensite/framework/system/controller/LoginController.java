package com.seeyoui.kensite.framework.system.controller;


import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.seeyoui.kensite.common.util.CookieUtils;
import com.seeyoui.kensite.common.util.MD5;
import com.seeyoui.kensite.common.util.SessionUtil;
import com.seeyoui.kensite.common.util.StringUtils;
import com.seeyoui.kensite.framework.plugin.skins.domain.Skins;
import com.seeyoui.kensite.framework.plugin.skins.service.SkinsService;
import com.seeyoui.kensite.framework.system.domain.SysUser;
import com.seeyoui.kensite.framework.system.util.SkinsUtils;
import com.seeyoui.kensite.framework.system.util.UserUtils;

/**
 * 程序名称： LoginController.java.java
 * 程序说明：登录controller
 * @version： Ver 0.1
 */

@Controller
@RequestMapping(value = "login")
public class LoginController {
	
	@Autowired
	private SkinsService skinsService;
	
	/**
	 * 账号登录
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = {"", "/login"}, method=RequestMethod.POST)
	public String loginIn(HttpSession session, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		String info = "";
		Boolean status = true;
		String resultPageURL = InternalResourceViewResolver.FORWARD_URL_PREFIX + "/";  
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
        password = MD5.md5(userName+password);
        //获取HttpSession中的验证码
//        String verifyCode = (String)request.getSession().getAttribute("verifyCode");
        //获取用户请求表单中输入的验证码
//        String submitCode = WebUtils.getCleanParam(request, "verifyCode");
//        System.out.println("用户[" + userName + "]登录时输入的验证码为[" + submitCode + "],HttpSession中的验证码为[" + verifyCode + "]");
//        if (StringUtils.isEmpty(submitCode) || !StringUtils.equals(verifyCode, submitCode.toLowerCase())){
//            request.setAttribute("message_login", "验证码不正确");
//            return resultPageURL;
//        }
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        token.setRememberMe(true);
        //获取当前的Subject
        Subject currentUser = SecurityUtils.getSubject();
        try {
            currentUser.login(token);
        }catch(UnknownAccountException uae){
            info = "未知账户";
            status = false;
        }catch(IncorrectCredentialsException ice){  
            info = "密码不正确";
            status = false;
        }catch(LockedAccountException lae){
            info = "账户已锁定";
            status = false;
        }catch(ExcessiveAttemptsException eae){
            info = "用户名或密码错误次数过多";
            status = false;
        }catch(AuthenticationException ae){
            ae.printStackTrace();
            info = "账户状态异常";
            status = false;
        }
        //验证是否登录成功
        if(currentUser.isAuthenticated() && status){
        	String theme = CookieUtils.getCookie(request, "current-theme");
        	modelMap.put("theme", "skin-"+theme);
        	resultPageURL = getSysSkins();
        }else{
            token.clear();
    		UserUtils.clearCache();
    		SecurityUtils.getSubject().logout();  
            modelMap.put("info", info);
        }
        
        return resultPageURL;
	}
	
	/**
	 * 账号登出
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/logout")
	public String loginOut(HttpSession session, ModelMap modelMap) {
		UserUtils.clearCache();
		SecurityUtils.getSubject().logout();  
		return InternalResourceViewResolver.REDIRECT_URL_PREFIX + "/";
	}
	
	/**
	 * 获取主题方案
	 */
	@RequestMapping(value = "/theme/{theme}")
	public String getThemeInCookie(@PathVariable String theme, HttpServletRequest request, HttpServletResponse response){
		if (StringUtils.isNotBlank(theme)){
			CookieUtils.setCookie(response, "theme", theme);
		}else{
			theme = CookieUtils.getCookie(request, "theme");
		}
		return "redirect:"+request.getParameter("url");
	}
	
	/**
	 * 获取系统皮肤方案
	 */
	public String getSysSkins() {
    	Skins skin = SkinsUtils.getCurSysSkins();
    	if(skin==null || skin.getUrl()==null || "".equals(skin.getUrl())) {
    		return "skins/poor/main";
    	} else {
    		return "skins"+skin.getUrl()+"/"+skin.getMain();
    	}
	}
	
	/**
	 * 登陆首页主区域页面
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/skinsPage/{url}")
	public String mainContent(@PathVariable("url") String url, HttpSession session, ModelMap modelMap) {
		return "skins/"+url;
	}
	
	/**
	 * 登陆首页主区域页面
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/skinsPage/common/{url}")
	public String commonPage(@PathVariable("url") String url, HttpSession session, ModelMap modelMap) {
		return "skins/common/"+url;
	}
	
	/**
	 * 单点登录
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sso/weaver")
	@ResponseBody
	public ModelAndView ssoWeaver(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, String userInfo, String url, String license) {
		String mac = "00-26-C6-3B-B6-9A";
		System.out.println(request.getRemoteAddr());
		List<String> result = new ArrayList<String>();
		result.add(mac);
		result.add(request.getRemoteAddr());
		System.out.println(MD5.md5(result.toString()));
		if(StringUtils.isBlank(license) || !license.equals(MD5.md5(result.toString()))) {
			return new ModelAndView("redirect:/WEB-INF/view/error/error404.jsp", modelMap);
		}
		JSONObject jsonObj = JSONObject.fromObject(userInfo);
		SysUser sysUser = (SysUser) JSONObject.toBean(jsonObj, SysUser.class);
		SessionUtil.setSession("currentUserName", "system");
		SessionUtil.setSession("currentUser", sysUser);
		return new ModelAndView("redirect:"+url, modelMap);
	}
}

