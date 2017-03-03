/*
 * Powered By cuichen
 * Since 2014 - 2016
 */
package com.seeyoui.kensite.framework.cms.filer.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.seeyoui.kensite.common.base.controller.BaseController;
import com.seeyoui.kensite.common.constants.StringConstant;
import com.seeyoui.kensite.common.util.FileUtils;
import com.seeyoui.kensite.common.util.RequestResponseUtil;
import com.seeyoui.kensite.common.util.StringUtils;
import com.seeyoui.kensite.framework.cms.filer.util.FilerUtils;
import com.seeyoui.kensite.framework.cms.guestbook.service.GuestbookService;
/**
 * 内容发布留言板
 * @author cuichen
 * @version 2.0
 * @since 1.0
 * @date 2016-07-30
 */

@Controller
@RequestMapping(value = "cms/filer")
public class FilerController extends BaseController {
	
	@Autowired
	private GuestbookService guestbookService;
	
	/**
	 * 展示列表页面
	 * @param modelMap
	 * @param module
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/{page}")
	public ModelAndView view(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, @PathVariable String page, String alowPath) throws Exception {
		String basePath = request.getSession().getServletContext().getRealPath("/");
		Map<String, List<Map<String, Object>>> fileMap = FilerUtils.getFolderInfo(basePath, alowPath);
		List<Map<String, Object>> folderList = fileMap.get("folderList");
		List<Map<String, Object>> fileList = fileMap.get("fileList");
		if("image".equals(page)) {
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("id", 1);
			m.put("pId", 0);
			m.put("name", "我的图库");
			m.put("path", alowPath);
			m.put("size", 0);
			m.put("open", true);
			m.put("isParent", true);
			folderList.add(m);
		}
		modelMap.put("alowPath", alowPath);
		modelMap.put("folderList", JSONArray.fromObject(folderList).toString());
		modelMap.put("fileList", JSONArray.fromObject(fileList).toString());
		return new ModelAndView("framework/cms/filer/"+page, modelMap);
	}
	
	/**
	 * 在指定目录创建文件夹
	 * @param session
	 * @param response
	 * @param request
	 * @param modelMap
	 * @param path 指定目录
	 * @param name 文件夹名
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/createFolder")
	@ResponseBody
	public Object createFolder(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, String path, String name) throws Exception {
		//C:/Users/Administrator/git/kensite_cms/src/main/webapp/
		String basePath = request.getSession().getServletContext().getRealPath("/");
		basePath = basePath.replaceAll("\\\\", "/");
		String sourse = basePath + path;
		Map<String, Object> result = new HashMap<String, Object>();
		File sourseF = new File(sourse);
		if(!sourseF.exists()) {
			result.put("success", StringConstant.FALSE);
			result.put("message", "目标文件夹不存在");
			return result;
		}
		String target = basePath + path + '/' + name;
		File targetF = new File(target);
		if(targetF.exists()) {
			result.put("success", StringConstant.FALSE);
			result.put("message", "文件夹命名重复");
			return result;
		}
		if(targetF.mkdir()) {
			result.put("success", StringConstant.TRUE);
			result.put("message", "文件夹创建成功");
			return result;
		} else {
			result.put("success", StringConstant.FALSE);
			result.put("message", "文件夹创建失败");
			return result;
		}
	}
	
	/**
	 * 删除指定文件或文件夹及其下的所有文件
	 * @param session
	 * @param response
	 * @param request
	 * @param modelMap
	 * @param path
	 * @param type
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object delete(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, String path, String type) throws Exception {
		String basePath = request.getSession().getServletContext().getRealPath("/");
		basePath = basePath.replaceAll("\\\\", "/");
		String sourse = basePath + path;
		Map<String, Object> result = new HashMap<String, Object>();
		File sourseF = new File(sourse);
		if(!sourseF.exists()) {
			result.put("success", StringConstant.FALSE);
			result.put("message", "目标不存在");
			return result;
		} else {
			if(FileUtils.delFile(sourse)) {
				result.put("success", StringConstant.TRUE);
				result.put("message", "删除成功");
			} else {
				result.put("success", StringConstant.FALSE);
				result.put("message", "删除失败");
			}
			return result;
		}
	}
	
	/**
	 * 重命名
	 * @param session
	 * @param response
	 * @param request
	 * @param modelMap
	 * @param path
	 * @param name
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/rename")
	@ResponseBody
	public Object rename(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, String path, String name) throws Exception {
		String basePath = request.getSession().getServletContext().getRealPath("/");
		basePath = basePath.replaceAll("\\\\", "/");
		String sourse = basePath + path;
		Map<String, Object> result = new HashMap<String, Object>();
		File sourseF = new File(sourse);
		if(!sourseF.exists()) {
			result.put("success", StringConstant.FALSE);
			result.put("message", "目标不存在");
			return result;
		} else {
			System.out.println(sourse);
			System.out.println(sourse.substring(0, sourse.lastIndexOf("/")+1)+name);
			File targetF = new File(sourse.substring(0, sourse.lastIndexOf("/")+1)+name);
			if(targetF.exists()) {
				result.put("success", StringConstant.FALSE);
				result.put("message", "存在相同文件名");
			} else {
				sourseF.renameTo(targetF);
				result.put("success", StringConstant.TRUE);
				result.put("message", "重命名成功");
			}
			return result;
		}
	}
}