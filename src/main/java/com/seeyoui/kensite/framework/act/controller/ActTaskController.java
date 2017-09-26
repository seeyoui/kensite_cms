package com.seeyoui.kensite.framework.act.controller;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.seeyoui.kensite.common.base.controller.BaseController;
import com.seeyoui.kensite.common.base.domain.EasyUIDataGrid;
import com.seeyoui.kensite.common.base.domain.Page;
import com.seeyoui.kensite.common.base.domain.Pager;
import com.seeyoui.kensite.common.constants.StringConstant;
import com.seeyoui.kensite.common.util.RequestResponseUtil;
import com.seeyoui.kensite.framework.act.domain.Act;
import com.seeyoui.kensite.framework.act.service.ActTaskService;
import com.seeyoui.kensite.framework.act.util.ActUtils;
import com.seeyoui.kensite.framework.system.util.UserUtils;

@Controller
@RequestMapping(value = "actTask")
public class ActTaskController extends BaseController {

	@Autowired
	private ActTaskService actTaskService;
	
	/**
	 * 获取待办列表
	 * @param procDefKey 流程定义标识
	 * @return
	 */
	@RequestMapping(value = {"todo", ""})
	public String todoList(Act act, HttpServletResponse response, Model model) throws Exception {
//		List<Act> list = actTaskService.todoList(act);
//		model.addAttribute("list", list);
		return "framework/act/actTaskTodoList";
	}
	
	/**
	 * 获取列表展示数据
	 * @param modelMap
	 * @param actModel
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/list/todo")
	@ResponseBody
	public Object listTodo(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, Act act, Pager pager) throws Exception{
		List<HashMap<String,String>> list = actTaskService.todoList(act);
		EasyUIDataGrid eudg = new EasyUIDataGrid();
		int total = list.size();
		eudg.setTotal(String.valueOf(total));
		eudg.setRows(list.subList(pager.getFirstResult(), total>pager.getMaxResults()?pager.getMaxResults():total));
//		JsonConfig jsonConfig = new JsonConfig();
//		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
//		JSONObject jsonObj = JSONObject.fromObject(eudg, jsonConfig);
//		RequestResponseUtil.putResponseStr(session, response, request, jsonObj);
		return eudg;
	}
	
	/**
	 * 获取已办任务
	 * @param page
	 * @param procDefKey 流程定义标识
	 * @return
	 */
	@RequestMapping(value = "historic")
	public String historicList(Act act, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
//		Page<Act> page = new Page<Act>(request, response);
//		page = actTaskService.historicList(page, act);
//		model.addAttribute("page", page);
		return "framework/act/actTaskHistoricList";
	}

	/**
	 * 获取发起流程列表
	 * @param page
	 * @param procDefKey 流程定义标识
	 * @return
	 */
	@RequestMapping(value = "/created")
	public String createBy(Act act, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
//		Page<Act> page = new Page<Act>(request, response);
//		page = actTaskService.historicList(page, act);
//		model.addAttribute("page", page);
		return "framework/act/actCreatedList";
	}
	
	/**
	 * 
	 * @param session
	 * @param response
	 * @param request
	 * @param modelMap
	 * @param act
	 * @param pager
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/list/created")
	@ResponseBody
	public Object test(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, Act act, Pager pager, String finished) throws Exception{
		List<HashMap<String,String>> list = actTaskService.finishedList(pager, finished);
		EasyUIDataGrid eudg = new EasyUIDataGrid();
		eudg.setTotal(String.valueOf(pager.getTotal()));
		eudg.setRows(list);
		return eudg;
	}
	
	/**
	 * 获取列表展示数据
	 * @param modelMap
	 * @param actModel
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/list/historic")
	@ResponseBody
	public Object listHistoric(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, Act act, Pager pager) throws Exception{
		List<HashMap<String,String>> list = actTaskService.historicList(pager, act);
		EasyUIDataGrid eudg = new EasyUIDataGrid();
		eudg.setTotal(String.valueOf(pager.getTotal()));
		eudg.setRows(list);
//		JsonConfig jsonConfig = new JsonConfig();
//		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
//		JSONObject jsonObj = JSONObject.fromObject(eudg, jsonConfig);
//		RequestResponseUtil.putResponseStr(session, response, request, jsonObj);
		return eudg;
	}

	/**
	 * 获取流转历史列表
	 * @param procInsId 流程实例
	 * @param startAct 开始活动节点名称
	 * @param endAct 结束活动节点名称
	 */
	@RequestMapping(value = "histoicFlow")
	public String histoicFlow(Act act, String startAct, String endAct, Model model){
		if (StringUtils.isNotBlank(act.getProcInsId())){
			List<Act> histoicFlowList = actTaskService.histoicFlowList(act.getProcInsId(), startAct, endAct);
			model.addAttribute("histoicFlowList", histoicFlowList);
		}
		return "framework/act/actTaskHistoricFlow";
	}
	
	/**
	 * 获取流程列表
	 * @param category 流程分类
	 */
	@RequestMapping(value = "process")
	public String processList(String category, HttpServletRequest request, HttpServletResponse response, Model model) {
//	    Page<Object[]> page = new Page<Object[]>(request, response);
//	    page = actTaskService.processList(page, category);
//		model.addAttribute("page", page);
//		model.addAttribute("category", category);
		return "framework/act/actTaskProcessList";
	}
	
	/**
	 * 获取列表展示数据
	 * @param modelMap
	 * @param actModel
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/list/process")
	@ResponseBody
	public Object listProcess(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, Act act, String category, Pager pager) throws Exception{
		List<Object[]> list = actTaskService.processList(pager, category);
		EasyUIDataGrid eudg = new EasyUIDataGrid();
		eudg.setTotal(String.valueOf(pager.getTotal()));
		eudg.setRows(list);
		return eudg;
	}
	
	/**
	 * 获取流程表单
	 * @param taskId	任务ID
	 * @param taskName	任务名称
	 * @param taskDefKey 任务环节标识
	 * @param procInsId 流程实例ID
	 * @param procDefId 流程定义ID
	 */
	@RequestMapping(value = "form")
	public String form(Act act, HttpServletRequest request, Model model){
		
		// 获取流程XML上的表单KEY
		String formKey = actTaskService.getFormKey(act.getProcDefId(), act.getTaskDefKey());

		// 获取流程实例对象
		if (act.getProcInsId() != null){
			act.setProcIns(actTaskService.getProcIns(act.getProcInsId()));
		}
		if (act.getProcInsId() != null){
			if(actTaskService.getProcIns(act.getProcInsId())!=null){
				act.setProcIns(actTaskService.getProcIns(act.getProcInsId()));
			}else{
//				act.setFinishedProcIns(actTaskService.getFinishedProcIns(act.getProcInsId()));
			}
		
		}
		return "redirect:" + ActUtils.getFormUrl(formKey, act);
	}
	
	/**
	 * 启动流程
	 * @param procDefKey 流程定义KEY
	 * @param businessTable 业务表表名
	 * @param businessId	业务表编号
	 */
	@RequestMapping(value = "start")
	@ResponseBody
	public String start(Act act, String table, String id, Model model) throws Exception {
		actTaskService.startProcess(act.getProcDefKey(), act.getBusinessId(), act.getBusinessTable(), act.getTitle());
		return StringConstant.TRUE;
	}

	/**
	 * 签收任务
	 * @param taskId 任务ID
	 */
	@RequestMapping(value = "claim")
	@ResponseBody
	public String claim(Act act) {
		String userId = UserUtils.getUser().getUserName();//ObjectUtils.toString(UserUtils.getUser().getId());
		actTaskService.claim(act.getTaskId(), userId);
		return StringConstant.TRUE;
	}
	
	/**
	 * 完成任务
	 * @param taskId 任务ID
	 * @param procInsId 流程实例ID，如果为空，则不保存任务提交意见
	 * @param comment 任务提交意见的内容
	 * @param vars 任务流程变量，如下
	 * 		vars.keys=flag,pass
	 * 		vars.values=1,true
	 * 		vars.types=S,B
	 */
	@RequestMapping(value = "complete")
	@ResponseBody
	public String complete(Act act) {
		actTaskService.complete(act.getTaskId(), act.getProcInsId(), act.getComment(), act.getVars().getVariableMap());
		return StringConstant.TRUE;
	}
	
	/**
	 * 读取带跟踪的图片
	 */
	@RequestMapping(value = "trace/photo/{procDefId}/{execId}")
	public void tracePhoto(@PathVariable("procDefId") String procDefId, @PathVariable("execId") String execId, HttpServletResponse response) throws Exception {
		InputStream imageStream = actTaskService.tracePhoto(procDefId, execId);
		
		// 输出资源内容到相应对象
		byte[] b = new byte[1024];
		int len;
		while ((len = imageStream.read(b, 0, 1024)) != -1) {
			response.getOutputStream().write(b, 0, len);
		}
	}
	
	/**
	 * 输出跟踪流程信息
	 * 
	 * @param processInstanceId
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "trace/info/{proInsId}")
	public List<Map<String, Object>> traceInfo(@PathVariable("proInsId") String proInsId) throws Exception {
		List<Map<String, Object>> activityInfos = actTaskService.traceProcess(proInsId);
		return activityInfos;
	}

	/**
	 * 显示流程图
	 
	@RequestMapping(value = "processPic")
	public void processPic(String procDefId, HttpServletResponse response) throws Exception {
		ProcessDefinition procDef = repositoryService.createProcessDefinitionQuery().processDefinitionId(procDefId).singleResult();
		String diagramResourceName = procDef.getDiagramResourceName();
		InputStream imageStream = repositoryService.getResourceAsStream(procDef.getDeploymentId(), diagramResourceName);
		byte[] b = new byte[1024];
		int len = -1;
		while ((len = imageStream.read(b, 0, 1024)) != -1) {
			response.getOutputStream().write(b, 0, len);
		}
	}*/
	
	/**
	 * 获取跟踪信息
	 
	@RequestMapping(value = "processMap")
	public String processMap(String procDefId, String proInstId, Model model)
			throws Exception {
		List<ActivityImpl> actImpls = new ArrayList<ActivityImpl>();
		ProcessDefinition processDefinition = repositoryService
				.createProcessDefinitionQuery().processDefinitionId(procDefId)
				.singleResult();
		ProcessDefinitionImpl pdImpl = (ProcessDefinitionImpl) processDefinition;
		String processDefinitionId = pdImpl.getId();// 流程标识
		ProcessDefinitionEntity def = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
				.getDeployedProcessDefinition(processDefinitionId);
		List<ActivityImpl> activitiList = def.getActivities();// 获得当前任务的所有节点
		List<String> activeActivityIds = runtimeService.getActiveActivityIds(proInstId);
		for (String activeId : activeActivityIds) {
			for (ActivityImpl activityImpl : activitiList) {
				String id = activityImpl.getId();
				if (activityImpl.isScope()) {
					if (activityImpl.getActivities().size() > 1) {
						List<ActivityImpl> subAcList = activityImpl
								.getActivities();
						for (ActivityImpl subActImpl : subAcList) {
							String subid = subActImpl.getId();
							System.out.println("subImpl:" + subid);
							if (activeId.equals(subid)) {// 获得执行到那个节点
								actImpls.add(subActImpl);
								break;
							}
						}
					}
				}
				if (activeId.equals(id)) {// 获得执行到那个节点
					actImpls.add(activityImpl);
					System.out.println(id);
				}
			}
		}
		model.addAttribute("procDefId", procDefId);
		model.addAttribute("proInstId", proInstId);
		model.addAttribute("actImpls", actImpls);
		return "framework/act/actTaskMap";
	}*/
	
	/**
	 * 删除任务
	 * @param taskId 流程实例ID
	 * @param reason 删除原因
	 */
	@RequestMapping(value = "deleteTask")
	public String deleteTask(String taskId, String reason, RedirectAttributes redirectAttributes) {
		if (StringUtils.isBlank(reason)){
			addMessage(redirectAttributes, "请填写删除原因");
		}else{
			actTaskService.deleteTask(taskId, reason);
			addMessage(redirectAttributes, "删除任务成功，任务ID=" + taskId);
		}
		return "redirect:/act/task";
	}
}
