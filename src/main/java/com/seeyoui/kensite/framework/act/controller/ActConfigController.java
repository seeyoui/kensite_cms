package com.seeyoui.kensite.framework.act.controller;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.engine.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.seeyoui.kensite.common.base.controller.BaseController;
import com.seeyoui.kensite.framework.act.service.ActTaskService;

@Controller
@RequestMapping(value = "actConfig")
public class ActConfigController extends BaseController {

	@Autowired
	private RepositoryService repositoryService;

	/**
	 * 获取待办列表
	 * 
	 * @param procDefKey
	 *            流程定义标识
	 * @return
	 */
	@RequestMapping(value = "task")
	@ResponseBody
	public Object task(HttpSession session, HttpServletResponse response,
			HttpServletRequest request, ModelMap modelMap) throws Exception {
		String processDefinitionId = "leave:1:f56527b47a334c3582e15b34a37a6d6c";
		BpmnModel model = repositoryService.getBpmnModel(processDefinitionId);
		if (model != null) {
			Collection<FlowElement> flowElements = model.getMainProcess()
					.getFlowElements();
			for (FlowElement e : flowElements) {
				System.out.println("flowelement id:" + e.getId() + "  name:"
						+ e.getName() + "   class:" + e.getClass().toString());
			}
		}
		return null;
	}
}
