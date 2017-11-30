/*
 * Powered By cuichen
 * Since 2014 - 2016
 */
package com.seeyoui.kensite.bussiness.demo.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.seeyoui.kensite.bussiness.demo.domain.Demo;
import com.seeyoui.kensite.bussiness.demo.service.DemoService;
import com.seeyoui.kensite.common.base.controller.BaseController;
import com.seeyoui.kensite.common.base.domain.EasyUIDataGrid;
import com.seeyoui.kensite.common.constants.StringConstant;
import com.seeyoui.kensite.common.util.DateUtils;
import com.seeyoui.kensite.common.util.GeneratorUUID;
import com.seeyoui.kensite.common.util.RequestResponseUtil;
import com.seeyoui.kensite.common.util.excel.ExportExcel;
import com.seeyoui.kensite.common.util.excel.ExportSqlExcel;
import com.seeyoui.kensite.common.util.word.ExportWord;
import com.seeyoui.kensite.framework.system.domain.SysUser;
import com.seeyoui.kensite.framework.system.service.SysUserService;
/**
 * 演示
 * @author cuichen
 * @version 1.0
 * @since 1.0
 * @date 2016-06-12
 */
@Controller
@RequestMapping(value = "bussiness/demo")
public class DemoController extends BaseController {
	
	@Autowired
	private DemoService demoService;
	@Autowired
	private SysUserService sysUserService;
	@Resource(name = "taskExecutor")
    private TaskExecutor taskExecutor;
	
	/**
	 * 展示列表页面
	 * @param modelMap
	 * @param module
	 * @return
	 * @throws Exception
	 */
	//@RequiresPermissions("bussiness:demo:view")
	@RequestMapping(value = "/{page}")
	public ModelAndView view(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, @PathVariable String page) throws Exception {
		return new ModelAndView("bussiness/demo/"+page, modelMap);
	}
	
	/**
	 * 根据ID查询单条数据
	 * @param modelMap
	 * @param module
	 * @return
	 * @throws Exception
	 */
	//@RequiresPermissions("bussiness:demo:select")
	@RequestMapping(value = "/form/{id}")
	@ResponseBody
	public ModelAndView form(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, @PathVariable String id) throws Exception {
		String page = "layui";
		Demo demo = demoService.findOne(id);
		modelMap.put("demo", JSONObject.fromObject(demo).toString());
		return new ModelAndView("bussiness/demo/"+page, modelMap);
	}
	
	/**
	 * 根据ID查询单条数据
	 * @param modelMap
	 * @param module
	 * @return
	 * @throws Exception
	 */
	//@RequiresPermissions("bussiness:demo:select")
	@RequestMapping(value = "/data/{id}")
	@ResponseBody
	public Object data(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, @PathVariable String id) throws Exception {
		Demo demo = demoService.findOne(id);
		return demo;
	}
	
	/**
	 * 获取列表展示数据
	 * @param modelMap
	 * @param demo
	 * @return
	 * @throws Exception
	 */
	//@RequiresPermissions("bussiness:demo:select")
	@RequestMapping(value = "/list/data", method=RequestMethod.POST)
	@ResponseBody
	public Object listData(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, Demo demo) throws Exception {
		List<Demo> demoList = demoService.findList(demo);
		int total = demoService.findTotal(demo);
		EasyUIDataGrid eudg = new EasyUIDataGrid();
		eudg.setTotal(String.valueOf(total));
		eudg.setRows(demoList);
		return eudg;
	}
	
	/**
	 * 获取所有数据
	 * @param modelMap
	 * @param demo
	 * @return
	 * @throws Exception
	 */
	//@RequiresPermissions("bussiness:demo:select")
	@RequestMapping(value = "/list/all", method=RequestMethod.POST)
	@ResponseBody
	public Object listAll(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, Demo demo) throws Exception {
		List<Demo> demoList = demoService.findAll(demo);
		return demoList;
	}
	
	/**
	 * 保存新增的数据
	 * @param modelMap
	 * @param demo
	 * @return
	 * @throws Exception
	 */
	//@RequiresPermissions("bussiness:demo:insert")
	@RequestMapping(value = "/save", method=RequestMethod.POST)
	@ResponseBody
	public String save(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, Demo demo) throws Exception {
		if (!beanValidator(modelMap, demo)){
			RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.FALSE);
			return null;
		}
		demoService.save(demo);
		RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.TRUE);
		return null;
	}
	
	/**
	 * 保存修改的数据
	 * @param modelMap
	 * @param demo
	 * @return
	 * @throws Exception
	 */
	//@RequiresPermissions("bussiness:demo:update")
	@RequestMapping(value = "/update", method=RequestMethod.POST)
	@ResponseBody
	public String update(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, Demo demo) throws Exception {
		if (!beanValidator(modelMap, demo)){
			RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.FALSE);
			return null;
		}
		demoService.update(demo);
		RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.TRUE);
		return null;
	}
	
	/**
	 * 删除数据库
	 * @param modelMap
	 * @param demoId
	 * @return
	 * @throws Exception
	 */
	//@RequiresPermissions("bussiness:demo:delete")
	@RequestMapping(value = "/delete", method=RequestMethod.POST)
	@ResponseBody
	public String delete(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, String id) throws Exception {
		List<String> listId = Arrays.asList(id.split(","));
		demoService.delete(listId);
		RequestResponseUtil.putResponseStr(session, response, request, modelMap, StringConstant.TRUE);
		return null;
	}

	/**
	 * 导出Excel数据
	 * @param modelMap
	 * @param demo
	 * @return
	 * @throws Exception
	 */
	//@RequiresPermissions("bussiness:demo:export")
	@RequestMapping(value = "/export")
	public String export(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, Demo demo) throws Exception {
		String fileName = DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
		List<Demo> demoList = demoService.findAll(demo);
		new ExportExcel(null, Demo.class).setDataList(demoList).write(response, fileName).dispose();
		return null;
	}
	
	/**
	 * 按照sql导出excel
	 * @param session
	 * @param response
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sqlexport")
	public String sqlexport(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap) throws Exception {
		String title = "平台开发JAVA WEB";
		String sql = "select user_name,name,lead_name,sysdate create_date from sys_user where department_id='da12ca0b592a48fc94a654f1ed948180' order by user_name";
		List<Map<String, String>> headList = new ArrayList<Map<String,String>>();
		
		Map<String, String> userName = new HashMap<String, String>();//帐号
		userName.put("column", "USER_NAME");//大写列名
		userName.put("title", "帐号");//中文标题
		userName.put("align", "1");//水平居中方式（1：靠左；2：居中；3：靠右）
		headList.add(userName);
		
		Map<String, String> name = new HashMap<String, String>();//名称
		name.put("column", "NAME");//大写列名
		name.put("title", "名称**备注");//中文标题
		name.put("align", "2");//水平居中方式（1：靠左；2：居中；3：靠右）
		headList.add(name);
		
		Map<String, String> leadName = new HashMap<String, String>();//主管
		leadName.put("column", "LEAD_NAME");//大写列名
		leadName.put("title", "主管");//中文标题
		leadName.put("align", "3");//水平居中方式（1：靠左；2：居中；3：靠右）
		headList.add(leadName);
		
		Map<String, String> createDate = new HashMap<String, String>();//日期
		createDate.put("column", "CREATE_DATE");//大写列名
		createDate.put("title", "日期**yyyy-MM-dd");//中文标题
		createDate.put("align", "2");//水平居中方式（1：靠左；2：居中；3：靠右）
		headList.add(createDate);
		
		String fileName = DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
		new ExportSqlExcel(title, sql, headList).write(response, fileName).dispose();
		return null;
	}
	
	/**
	 * 按照sql导出excel
	 * @param session
	 * @param response
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/wordexport")
	public String wordexport(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap) throws Exception {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("tableName", "SYS_USER");
		dataMap.put("tableComments", "系统用户表");
		List<Map<String, Object>> columnList = new ArrayList<Map<String, Object>>();
		Map<String, Object> col1 = new HashMap<String, Object>();
		col1.put("columnName", "ID");
		col1.put("dataType", "char(32)");
		col1.put("nullable", "N");
		col1.put("tableComments", "主键");
		columnList.add(col1);
		Map<String, Object> col2 = new HashMap<String, Object>();
		col2.put("columnName", "USER_NAME");
		col2.put("dataType", "varchar(100)");
		col2.put("nullable", "Y");
		col2.put("tableComments", "帐号");
		columnList.add(col2);
		dataMap.put("columnList", columnList);
		
		String fileName = DateUtils.getDate("yyyyMMddHHmmss")+".doc";
		new ExportWord(dataMap, "table.ftl").write(response, fileName);
		return null;
	}
	
	@RequestMapping(value = "/getUserIdByDepartmentId", method=RequestMethod.POST)
	@ResponseBody
	public Object getUserIdByDepartmentId(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, String departmentId) throws Exception {
		SysUser sysUser = new SysUser();
		sysUser.setDepartmentId(departmentId);
		List<SysUser> sysUserList = sysUserService.findAll(sysUser);
		return sysUserList;
	}
	
	@RequestMapping(value = "/thread")
	@ResponseBody
	public Object thread(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap) throws Exception {
		try {
			taskExecutor.execute(new Runnable() {
				public void run() {
					for(int i=1; i<10; i++) {
						System.out.println(i);
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			});
			taskExecutor.execute(new Runnable() {
				public void run() {
					for(int i=100; i>90; i--) {
						System.out.println(GeneratorUUID.getId());
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			});
			taskExecutor.execute(new Runnable() {
				public void run() {
					for(int i=1; i<=5; i++) {
						int total = demoService.findTotal(new Demo());
						System.out.println("总数"+total);
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "9987";
	}
}