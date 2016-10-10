package com.seeyoui.kensite.framework.act.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.stream.XMLStreamException;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.seeyoui.kensite.common.base.controller.BaseController;
import com.seeyoui.kensite.common.base.domain.EasyUIDataGrid;
import com.seeyoui.kensite.common.base.domain.Pager;
import com.seeyoui.kensite.common.constants.StringConstant;
import com.seeyoui.kensite.common.util.RequestResponseUtil;
import com.seeyoui.kensite.common.util.StringUtils;
import com.seeyoui.kensite.framework.act.service.ActProcessService;


@Controller
@RequestMapping(value = "actProcess/")
public class ActProcessController extends BaseController {

	@Autowired
	private ActProcessService actProcessService;

	/**
	 * 流程定义列表
	 */
	/**
	 * 流程模型列表
	 */
	@RequestMapping(value = {"showPageList", ""})
	public ModelAndView showactModelPageList(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap) throws Exception {
		return new ModelAndView("framework/act/actProcessList", modelMap);
	}
	
	/**
	 * 获取列表展示数据
	 * @param modelMap
	 * @param actModel
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getListData", method=RequestMethod.POST)
	@ResponseBody
	public String getListData(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, String category, Pager pager) throws Exception{
		EasyUIDataGrid eudg = actProcessService.processList(pager, category);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		JSONObject jsonObj = JSONObject.fromObject(eudg, jsonConfig);
		RequestResponseUtil.putResponseStr(session, response, request, jsonObj);
		return null;
	}
	
	/**
	 * 运行中的实例列表
	 */
	@RequestMapping(value = "showRunningPageList")
	public ModelAndView runningList(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap) throws Exception {
		return new ModelAndView("framework/act/actProcessRunningList", modelMap);
	}
	
	/**
	 * 获取列表展示数据
	 * @param modelMap
	 * @param actModel
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getRunningListData", method=RequestMethod.POST)
	@ResponseBody
	public String getRunningListData(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, String category, String procDefKey, Pager pager) throws Exception{
		EasyUIDataGrid eudg = actProcessService.runningList(pager, category, procDefKey);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		JSONObject jsonObj = JSONObject.fromObject(eudg, jsonConfig);
		RequestResponseUtil.putResponseStr(session, response, request, jsonObj);
		return null;
	}

	/**
	 * 读取资源，通过部署ID
	 * @param processDefinitionId  流程定义ID
	 * @param processInstanceId 流程实例ID
	 * @param resourceType 资源类型(xml|image)
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "resource")
	public void resourceRead(String procDefId, String proInsId, String resType, HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap) throws Exception {
		InputStream resourceAsStream = actProcessService.resourceRead(procDefId, proInsId, resType);
		byte[] b = new byte[1024];
		int len = -1;
		while ((len = resourceAsStream.read(b, 0, 1024)) != -1) {
			response.getOutputStream().write(b, 0, len);
		}
		response.getOutputStream().flush();
	}

	/**
	 * 部署流程
	 */
	@RequestMapping(value = "/deploy", method=RequestMethod.GET)
	public String deploy(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap) {
		return "modules/act/actProcessDeploy";
	}
	
	/**
	 * 部署流程 - 保存
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "/deploy", method=RequestMethod.POST)
	public String deploy(@Value("#{APP_PROP['activiti.export.diagram.path']}") String exportDir, 
			String category, MultipartFile file, RedirectAttributes redirectAttributes) {

		String fileName = file.getOriginalFilename();
		
		if (StringUtils.isBlank(fileName)){
			redirectAttributes.addFlashAttribute("message", "请选择要部署的流程文件");
		}else{
			String message = actProcessService.deploy(exportDir, category, file);
			redirectAttributes.addFlashAttribute("message", message);
		}

		return "redirect:/act/process";
	}
	
	/**
	 * 设置流程分类
	 */
	@RequestMapping(value = "updateCategory")
	public String updateCategory(String procDefId, String category, RedirectAttributes redirectAttributes,
			HttpSession session, HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap) {
		actProcessService.updateCategory(procDefId, category);
		RequestResponseUtil.putResponseStr(session, response, request, StringConstant.TRUE);
		return null;
	}

	/**
	 * 挂起、激活流程实例
	 */
	@RequestMapping(value = "update/{state}")
	public String updateState(@PathVariable("state") String state, String procDefId, RedirectAttributes redirectAttributes,
			HttpSession session, HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap) {
		String message = actProcessService.updateState(state, procDefId);
		RequestResponseUtil.putResponseStr(session, response, request, message);
		return null;
	}
	
	/**
	 * 将部署的流程转换为模型
	 * @param procDefId
	 * @param redirectAttributes
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws XMLStreamException
	 */
	@RequestMapping(value = "convertToModel")
	public String convertToModel(String procDefId, RedirectAttributes redirectAttributes,
			HttpSession session, HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap) throws UnsupportedEncodingException, XMLStreamException {
		org.activiti.engine.repository.Model modelData = actProcessService.convertToModel(procDefId);
		String message = "转换模型成功，模型ID="+modelData.getId();
		RequestResponseUtil.putResponseStr(session, response, request, message);
		return null;
	}
	
	/**
	 * 导出图片文件到硬盘
	 */
	@RequestMapping(value = "export")
	@ResponseBody
	public List<String> exportDiagrams(@Value("#{APP_PROP['activiti.export.diagram.path']}") String exportDir) throws IOException {
		List<String> files = actProcessService.exportDiagrams(exportDir);;
		return files;
	}

	/**
	 * 删除部署的流程，级联删除流程实例
	 * @param deploymentId 流程部署ID
	 */
	@RequestMapping(value = "remove")
	public String delete(HttpSession session, HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, String deploymentId) {
		actProcessService.deleteDeployment(deploymentId);
		RequestResponseUtil.putResponseStr(session, response, request, StringConstant.TRUE);
		return null;
	}
	
	/**
	 * 删除流程实例
	 * @param procInsId 流程实例ID
	 * @param reason 删除原因
	 */
	@RequestMapping(value = "deleteProcIns")
	public String deleteProcIns(String procInsId, String reason, RedirectAttributes redirectAttributes,
			HttpSession session, HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap) {
		if (StringUtils.isBlank(reason)){
			RequestResponseUtil.putResponseStr(session, response, request, "请填写删除原因");
		}else{
			actProcessService.deleteProcIns(procInsId, reason);
			RequestResponseUtil.putResponseStr(session, response, request, "删除流程实例成功，实例ID=" + procInsId);
		}
		return null;
	}
	
}
