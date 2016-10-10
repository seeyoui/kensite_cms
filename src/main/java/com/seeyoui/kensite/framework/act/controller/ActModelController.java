package com.seeyoui.kensite.framework.act.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.seeyoui.kensite.common.base.controller.BaseController;
import com.seeyoui.kensite.common.base.domain.EasyUIDataGrid;
import com.seeyoui.kensite.common.base.domain.Pager;
import com.seeyoui.kensite.common.constants.StringConstant;
import com.seeyoui.kensite.common.util.RequestResponseUtil;
import com.seeyoui.kensite.framework.act.service.ActModelService;

@Controller
@RequestMapping(value = "actModel/")
public class ActModelController extends BaseController {

	@Autowired
	private ActModelService actModelService;

	/**
	 * 流程模型列表
	 */
	@RequestMapping(value = {"showPageList", ""})
	public ModelAndView showactModelPageList(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap) throws Exception {
		return new ModelAndView("framework/act/actModelList", modelMap);
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
		EasyUIDataGrid eudg = actModelService.modelList(pager, category);
		JSONObject jsonObj = JSONObject.fromObject(eudg);
		RequestResponseUtil.putResponseStr(session, response, request, jsonObj);
		return null;
	}
	
	/**
	 * 创建模型
	 */
//	@RequestMapping(value = "create", method = RequestMethod.GET)
//	public String create(Model model) {
//		return "modules/act/actModelCreate";
//	}
	
	/**
	 * 保存新增的数据
	 * @param modelMap
	 * @param actModel
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "saveByAdd", method=RequestMethod.POST)
	@ResponseBody
	public String saveactModelByAdd(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap,
			String name, String key, String description, String category) throws Exception{
		try {
			org.activiti.engine.repository.Model modelData = actModelService.create(name, key, description, category);
			response.sendRedirect(request.getContextPath() + "/act/modeler.jsp?moduleId=" + modelData.getId());
//			RequestResponseUtil.putResponseStr(session, response, request, StringConstant.TRUE);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("创建模型失败：", e);
		}
		return null;
	}
	
	/**
	 * 根据Model部署流程
	 */
	@RequestMapping(value = "deploy")
	public String deploy(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, String id) throws Exception{
		String message = actModelService.deploy(id);
		RequestResponseUtil.putResponseStr(session, response, request, message);
		return null;
	}
	
	/**
	 * 导出model的xml文件
	 */
	@RequestMapping(value = "export")
	public void export(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, String id) {
		actModelService.export(id, response);
	}
	
	/**
	 * 保存修改的数据
	 * @param modelMap
	 * @param actModel
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "saveByUpdate", method=RequestMethod.POST)
	@ResponseBody
	public String saveactModelByUpdate(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, String id, String category) throws Exception{
		actModelService.updateCategory(id, category);
		RequestResponseUtil.putResponseStr(session, response, request, StringConstant.TRUE);
		return null;
	}
	
	/**
	 * 删除数据库
	 * @param modelMap
	 * @param actModelId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "delete", method=RequestMethod.POST)
	@ResponseBody
	public String delete(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, String delDataId) throws Exception {
		actModelService.delete(delDataId);
		RequestResponseUtil.putResponseStr(session, response, request, StringConstant.TRUE);
		return null;
	}
}
