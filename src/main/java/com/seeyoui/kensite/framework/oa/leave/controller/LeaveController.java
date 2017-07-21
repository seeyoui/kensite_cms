package com.seeyoui.kensite.framework.oa.leave.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Maps;
import com.seeyoui.kensite.common.base.controller.BaseController;
import com.seeyoui.kensite.common.constants.StringConstant;
import com.seeyoui.kensite.common.util.RequestResponseUtil;
import com.seeyoui.kensite.common.util.StringUtils;
import com.seeyoui.kensite.framework.oa.leave.domain.Leave;
import com.seeyoui.kensite.framework.oa.leave.service.LeaveService;

@Controller
@RequestMapping(value = "oa/leave")
public class LeaveController extends BaseController {

	@Autowired
	protected LeaveService leaveService;

	//@RequiresPermissions("oa:leave:view")
	@RequestMapping(value = "/form")
	public ModelAndView applyForm(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, Leave leave) {
		String formKey = "framework/oa/leave/form";
		if (StringUtils.isNotBlank(leave.getId())){
			leave = leaveService.findLeaveById(leave.getId());
			modelMap.put("leave", leave);
		}
		return new ModelAndView(formKey);
	}

	/**
	 * 启动请假流程
	 * @param leave	
	 */
	//@RequiresPermissions("oa:leave:create")
	@RequestMapping(value = "/start", method = RequestMethod.POST)
	@ResponseBody
	public Object start(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, Leave leave) {
		try {
			Map<String, Object> variables = Maps.newHashMap();
			leaveService.start(leave, variables);
			System.out.println("流程已启动，流程ID：" + leave.getBindId());
		} catch (Exception e) {
			System.out.println("启动请假流程失败：" + e);
		}
		RequestResponseUtil.putResponseStr(session, response, request, StringConstant.TRUE);
		return null;
	}
	
	/**
	 * 保存新增的数据
	 * @param modelMap
	 * @param leave
	 * @return
	 * @throws Exception
	 */
	//@RequiresPermissions("oa:leave:insert")
	@RequestMapping(value = "/saveOrUpdate", method=RequestMethod.POST)
	@ResponseBody
	public Object saveOrUpdate(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, Leave leave) throws Exception{
		leaveService.saveOrUpdate(leave);
		RequestResponseUtil.putResponseStr(session, response, request, StringConstant.TRUE);
		return null;
	}

}
