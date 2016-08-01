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
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.seeyoui.kensite.common.base.controller.BaseController;
import com.seeyoui.kensite.common.base.domain.EasyUIDataGrid;
import com.seeyoui.kensite.common.constants.StringConstant;
import com.seeyoui.kensite.common.util.RequestResponseUtil;
import com.seeyoui.kensite.framework.plugin.upload.domain.ImageCrop;
import com.seeyoui.kensite.framework.plugin.upload.domain.Uploadfile;
import com.seeyoui.kensite.framework.plugin.upload.service.FileUtilService;
import com.seeyoui.kensite.framework.plugin.upload.service.UploadfileService;
/**
 * @author cuichen
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping(value = "sys/uploadfile")
public class FileUtilController extends BaseController {
	
	@Autowired
	private FileUtilService fileUtilService;
	
	/**
	 * 展示图片crop页面
	 * @param modelMap
	 * @param module
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/showImageCrop")
	public ModelAndView showImageCrop(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap) throws Exception {
		return new ModelAndView("framework/plugin/upload/imageCrop", modelMap);
	}
	
	/**
	 * 保存新增的数据
	 * @param modelMap
	 * @param uploadfile
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/imageCrop", method=RequestMethod.POST)
	@ResponseBody
	public String imageCrop(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, ImageCrop imageCrop) throws Exception{
//		Uploadfile uf = uploadfileService.uploadfile(imageCrop, request);
//		JSONObject jsonObject = JSONObject.fromObject(uf);
//		RequestResponseUtil.putResponseStr(session, response, request, jsonObject.toString());
		return null;
	}
	
	@RequestMapping(value = "imageCropper/headIcon", method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequiresUser
	public Object imageCropperHeadIcon(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap) {
		return fileUtilService.headIcon(request);
	}
}