package com.seeyoui.kensite.bussiness.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.seeyoui.kensite.bussiness.demo.domain.Demo;
import com.seeyoui.kensite.bussiness.demo.service.DemoService;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * 演示
 * @author cuichen
 * @version 1.0
 * @since 1.0
 * @date 2016-06-12
 */
@Controller
@RequestMapping(value = "ws/")
public class DemoWebservice {

	@Autowired
	private DemoService demoService;
	
	@ResponseBody
	@RequestMapping(value = "getById", method = RequestMethod.GET, produces = {"application/json; charset=utf-8","application/xml"})
	@ApiOperation(value = "通过ID查询信息", httpMethod = "GET", notes = "暂无")
	public Object getById(
			@ApiParam(required = true, name = "id", value = "ID") 
			@RequestParam(value = "id") String id,HttpServletRequest request) {
		int total = demoService.findTotal(new Demo());
		System.out.println(total);
		Demo demo = new Demo();
		demo.setId(id);
		demo.setUserName("张小三");
		return demo;
	}
}
