/*
 * Powered By cuichen
 * Since 2014 - 2016
 */
package com.seeyoui.kensite.framework.cms.comment.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.seeyoui.kensite.common.base.controller.BaseController;
import com.seeyoui.kensite.common.base.domain.EasyUIDataGrid;
import com.seeyoui.kensite.common.base.domain.Page;
import com.seeyoui.kensite.common.constants.StringConstant;
import com.seeyoui.kensite.common.util.DateUtils;
import com.seeyoui.kensite.common.util.RequestResponseUtil;
import com.seeyoui.kensite.common.util.StringUtils;
import com.seeyoui.kensite.common.util.excel.ExportExcel;
import com.seeyoui.kensite.framework.cms.comment.domain.Comment;
import com.seeyoui.kensite.framework.cms.comment.service.CommentService;
import com.seeyoui.kensite.framework.system.util.UserUtils;
/**
 * 内容发布评价
 * @author cuichen
 * @version 2.0
 * @since 1.0
 * @date 2016-07-15
 */

@Controller
@RequestMapping(value = "cms/comment")
public class CommentController extends BaseController {
	
	@Autowired
	private CommentService commentService;
	
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
			ModelMap modelMap, @PathVariable String page, String basicId) throws Exception {
		if(basicId == null) {
			basicId = "";
		}
		modelMap.put("basicId", basicId);
		return new ModelAndView("framework/cms/comment/"+page, modelMap);
	}
	
	/**
	 * 根据ID查询单条数据
	 * @param modelMap
	 * @param module
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/data/{id}")
	@ResponseBody
	public Object data(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, @PathVariable String id) throws Exception {
		Comment comment = commentService.findOne(id);
		return comment;
	}
	
	/**
	 * 获取列表展示数据
	 * @param modelMap
	 * @param comment
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/list/data", method=RequestMethod.POST)
	@ResponseBody
	public Object listData(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, Comment comment) throws Exception {
		List<Comment> commentList = commentService.findList(comment);
		int total = commentService.findTotal(comment);
		EasyUIDataGrid eudg = new EasyUIDataGrid();
		eudg.setTotal(String.valueOf(total));
		eudg.setRows(commentList);
		return eudg;
	}
	
	/**
	 * 获取列表展示数据
	 * @param modelMap
	 * @param comment
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/list/page", method=RequestMethod.POST)
	@ResponseBody
	public Object listPage(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, Comment comment) throws Exception {
		List<Comment> commentList = commentService.findList(comment);
		int total = commentService.findTotal(comment);
		Page<Comment> page = new Page<Comment>(comment.getPage(), comment.getRows(), total);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("rows", commentList);
		dataMap.put("page", page);
		return dataMap;
	}
	
	/**
	 * 获取所有数据
	 * @param modelMap
	 * @param comment
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/list/all", method=RequestMethod.POST)
	@ResponseBody
	public Object listAll(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, Comment comment) throws Exception {
		List<Comment> commentList = commentService.findAll(comment);
		return commentList;
	}
	
	/**
	 * 保存新增的数据
	 * @param modelMap
	 * @param comment
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/save", method=RequestMethod.POST)
	@ResponseBody
	public String save(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, Comment comment) throws Exception {
		if (!beanValidator(modelMap, comment)){
			RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.FALSE);
			return null;
		}
		String ip = StringUtils.getRemoteAddr(request);
		if(StringUtils.isBlank(ip)) {
			ip = "127.0.0.1";
		}
		comment.setIp(ip);
		commentService.save(comment);
		RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.TRUE);
		return null;
	}
	
	/**
	 * 保存修改的数据
	 * @param modelMap
	 * @param comment
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/update", method=RequestMethod.POST)
	@ResponseBody
	public String update(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, Comment comment) throws Exception {
		if (!beanValidator(modelMap, comment)){
			RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.FALSE);
			return null;
		}
		commentService.update(comment);
		RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.TRUE);
		return null;
	}
	
	/**
	 * 保存修改的数据
	 * @param modelMap
	 * @param comment
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/changeState", method=RequestMethod.POST)
	@ResponseBody
	public String changeState(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, Comment comment) throws Exception {
		if (!beanValidator(modelMap, comment)){
			RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.FALSE);
			return null;
		}
		comment.setAuditDate(new Date());
		comment.setAuditUserId(UserUtils.getUser().getId());
		comment.setAuditUserName(UserUtils.getUser().getName());
		commentService.update(comment);
		RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.TRUE);
		return null;
	}
	
	/**
	 * 删除数据库
	 * @param modelMap
	 * @param commentId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/delete", method=RequestMethod.POST)
	@ResponseBody
	public String delete(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, String id) throws Exception {
		List<String> listId = Arrays.asList(id.split(","));
		commentService.delete(listId);
		RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.TRUE);
		return null;
	}

	/**
	 * 导出Excel数据
	 * @param modelMap
	 * @param comment
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/export")
	public String export(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, Comment comment) throws Exception {
		String fileName = DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
		List<Comment> commentList = commentService.findAll(comment);
		new ExportExcel(null, Comment.class).setDataList(commentList).write(response, fileName).dispose();
		return null;
	}
}