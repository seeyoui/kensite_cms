package com.seeyoui.kensite.framework.oa.leave.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Maps;
import com.seeyoui.kensite.common.base.controller.BaseController;
import com.seeyoui.kensite.common.base.persistence.JsonMapper;
import com.seeyoui.kensite.common.constants.StringConstant;
import com.seeyoui.kensite.common.util.RequestResponseUtil;
import com.seeyoui.kensite.framework.act.service.ActTaskService;
import com.seeyoui.kensite.framework.oa.leave.domain.Leave;
import com.seeyoui.kensite.framework.oa.leave.service.LeaveService;
import com.seeyoui.kensite.framework.system.util.UserUtils;

@Controller
@RequestMapping(value = "oa/leave")
public class LeaveController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	protected LeaveService leaveService;
	@Autowired
	protected RuntimeService runtimeService;
	@Autowired
	protected ActTaskService actTaskService;

	@Autowired
	protected TaskService taskService;
	
	//@RequiresPermissions("oa:leave:view")
	@RequestMapping(value = "{state}/form")
	public ModelAndView applyForm(@PathVariable("state") String state, HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, String id, String pdid, String tdkey) {
		Leave leave = leaveService.findLeaveById(id);
		Task task = taskService.createTaskQuery().processInstanceId(leave.getBindId()).active().singleResult();
		if(task!=null) {
			leave.setTask(task);
		}
		modelMap.put("leave", leave);
		if(state!=null && "read".equals(state)) {
			state = "disabled=\"\"";
		}
		if(state!=null && "write".equals(state)) {
			state = "";
		}
		modelMap.put("state", state);
//		String formKey = actTaskService.getFormKey(pdid, tdkey);//可以将表单路径配置到节点formKey上在此取出使用
		String formKey = "framework/oa/leave/leaveApplyForm";
		return new ModelAndView(formKey);
	}

	/**
	 * 启动请假流程
	 * @param leave	
	 */
	//@RequiresPermissions("oa:leave:create")
	@RequestMapping(value = "start", method = RequestMethod.POST)
	public ModelAndView start(HttpSession session,
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
	@RequestMapping(value = "/save", method=RequestMethod.POST)
	@ResponseBody
	public String save(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, Leave leave) throws Exception{
		leaveService.save(leave);
		RequestResponseUtil.putResponseStr(session, response, request, StringConstant.TRUE);
		return null;
	}

	/**
	 * 任务列表
	 * @param leave	
	 */
//	//@RequiresPermissions("oa:leave:view")
	@RequestMapping(value = {"list/task",""})
	public ModelAndView taskList(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap) {
		String userId = UserUtils.getUser().getUserName();//ObjectUtils.toString(UserUtils.getUser().getId());
		List<Leave> leaveList = leaveService.findTodoTasks(userId);
		modelMap.put("leaveList", leaveList);
		return new ModelAndView("framework/oa/leave/leaveTaskList");
	}

	/**
	 * 读取所有流程
	 * @return
	 */
	//@RequiresPermissions("oa:leave:view")
	@RequestMapping(value = {"list"})
	public ModelAndView list(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, Leave leave) {
		leave.setCreateUser(UserUtils.getUser());
        List<Leave> leaveList = leaveService.find(leave); 
        modelMap.put("leaveList", leaveList);
		return new ModelAndView("framework/oa/leave/leaveList");
	}
	
	/**
	 * 读取详细数据
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "detail/{id}")
	@ResponseBody
	public String getLeave(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, @PathVariable("id") String id) {
		Leave leave = leaveService.findLeaveById(id);
//		JSONObject jsonObject = JSONObject.fromObject(leave);
//		System.out.println(jsonObject.toString());
		RequestResponseUtil.putResponseStr(session, response, request, JsonMapper.getInstance().toJson(leave));
		return null;
	}

	/**
	 * 读取详细数据
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "detail-with-vars/{id}/{taskId}")
	@ResponseBody
	public String getLeaveWithVars(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, @PathVariable("id") String id, @PathVariable("taskId") String taskId) {
		Leave leave = leaveService.findLeaveById(id);
		Map<String, Object> variables = taskService.getVariables(taskId);
		leave.setVariables(variables);
		return JsonMapper.getInstance().toJson(leave);
	}

}
