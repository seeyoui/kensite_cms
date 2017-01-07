/*
 * Powered By cuichen
 * Since 2014 - 2015
 */
package com.seeyoui.kensite.framework.plugin.upload.controller;  
 
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.seeyoui.kensite.common.base.controller.BaseController;
import com.seeyoui.kensite.common.base.domain.EasyUIDataGrid;
import com.seeyoui.kensite.common.constants.StringConstant;
import com.seeyoui.kensite.common.util.Global;
import com.seeyoui.kensite.common.util.RequestResponseUtil;
import com.seeyoui.kensite.framework.plugin.upload.domain.Uploadfile;
import com.seeyoui.kensite.framework.plugin.upload.service.UploadfileService;
/**
 * @author cuichen
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping(value = "sys/uploadfile")
public class UploadfileController extends BaseController {
	
	@Autowired
	private UploadfileService uploadfileService;
	
	/**
	 * 展示列表页面
	 * @param modelMap
	 * @param module
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/showPageList")
	public ModelAndView showUploadfilePageList(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap) throws Exception {
		return new ModelAndView("framework/plugin/upload/uploadfile", modelMap);
	}
	
	@RequestMapping(value = "/testUpload")
	public ModelAndView testUpload(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap) throws Exception {
		return new ModelAndView("framework/plugin/upload/uploadify", modelMap);
	}
	
	/**
	 * 获取列表展示数据
	 * @param modelMap
	 * @param uploadfile
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getListData", method=RequestMethod.POST)
	@ResponseBody
	public String getListData(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, Uploadfile uploadfile) throws Exception{
		List<Uploadfile> uploadfileList = uploadfileService.findUploadfileList(uploadfile);
		EasyUIDataGrid eudg = uploadfileService.findUploadfileListTotal(uploadfile);
		eudg.setRows(uploadfileList);
		JSONObject jsonObj = JSONObject.fromObject(eudg);
		RequestResponseUtil.putResponseStr(session, response, request, jsonObj);
		return null;
	}
	
	/**
	 * 保存新增的数据
	 * @param modelMap
	 * @param uploadfile
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/upload", method=RequestMethod.POST)
	@ResponseBody
	public Object uploadfile(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, Uploadfile uploadfile) throws Exception{
		Uploadfile uf = uploadfileService.upload(uploadfile, request);
//		System.out.println(uf.getUrl());
//		JSONObject jsonObject = JSONObject.fromObject(uf);
//		System.out.println(jsonObject.toString());
//		RequestResponseUtil.putResponseStr(session, response, request, jsonObject.toString());
//		return null;
		Map<String, String> result = new HashMap<String, String>();
		result.put("url", uf.getUrl());
		result.put("realurl", uf.getRealurl());
		result.put("realname", uf.getRealname());
		JSONObject jsonObject = JSONObject.fromObject(result);
		RequestResponseUtil.putResponseStr(session, response, request, jsonObject.toString());
		return null;
	}
	
	/**
	 * 保存新增的数据
	 * @param modelMap
	 * @param uploadfile
	 * @param file
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/uploadFile", method=RequestMethod.POST)
	@ResponseBody
	public Object uploadFile(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, MultipartFile file, Uploadfile uploadfile) throws Exception{
		Uploadfile uf = uploadfileService.uploadFile(file, uploadfile, request);
		return uf;
	}
	
	/**
	 * 保存新增的数据
	 * @param modelMap
	 * @param uploadfile
	 * @param file
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/uploadMarkdown", method=RequestMethod.POST)
	@ResponseBody
	public Object uploadMarkdown(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, MultipartFile file, Uploadfile uploadfile) throws Exception{
		uploadfile.setUrl("tempMarkdown");
		Uploadfile uf = uploadfileService.upload(uploadfile, request);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", 1);
		result.put("url", "/"+Global.getConfig("productName")+"/"+uf.getUrl()+"/"+uf.getRealname());
		result.put("message", "上传成功");
		return result;
	}
	
	/**
	 * 删除数据库
	 * @param modelMap
	 * @param uploadfileId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/delete", method=RequestMethod.POST)
	@ResponseBody
	public String delete(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, String delDataId) throws Exception {
		List<String> listId = Arrays.asList(delDataId.split(","));
		uploadfileService.deleteUploadfile(listId);
		RequestResponseUtil.putResponseStr(session, response, request, StringConstant.TRUE);
		return null;
	}
}