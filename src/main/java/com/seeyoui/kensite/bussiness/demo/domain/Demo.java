/*
 * Powered By cuichen
 * Since 2014 - 2016
 */
package com.seeyoui.kensite.bussiness.demo.domain;

import org.springframework.web.util.HtmlUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.seeyoui.kensite.common.base.domain.DataEntity;
import com.seeyoui.kensite.common.util.excel.annotation.ExcelField;

/**
 * 演示
 * @author cuichen
 * @version 1.0
 * @since 1.0
 * @date 2016-06-12
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Demo extends DataEntity<Demo> {
	private static final long serialVersionUID = 1L;

	@ExcelField(title="下拉树", type=1, align=2, sort=7, mod="BO_DEMO|TREE_ID")
	private String treeId;//下拉树
	@ExcelField(title="表达式", type=1, align=2, sort=8, mod="BO_DEMO|EXPRESSION")
	private String expression;//表达式
	@ExcelField(title="用户名", type=1, align=2, sort=9, mod="BO_DEMO|USER_NAME")
	private String userName;//用户名
	@ExcelField(title="用户性别", type=1, align=2, sort=10, mod="BO_DEMO|USER_SEX")
	private String userSex;//用户性别
	@ExcelField(title="用户年龄", type=1, align=2, sort=11, mod="BO_DEMO|USER_AGE")
	private String userAge;//用户年龄
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@ExcelField(title="出生日期", type=1, align=2, sort=12, mod="BO_DEMO|USER_BIRTHDAY")
	private java.util.Date userBirthday;//出生日期
	@ExcelField(title="用户头像", type=1, align=2, sort=13, mod="BO_DEMO|USER_ICON")
	private String userIcon;//用户头像
	@ExcelField(title="用户简介", type=1, align=2, sort=14, mod="BO_DEMO|USER_SUMMARY")
	private String userSummary;//用户简介
	@ExcelField(title="所在部门", type=1, align=2, sort=15, mod="BO_DEMO|DEPARTMENT_ID")
	private String departmentId;//所在部门
	@ExcelField(title="上级领导", type=1, align=2, sort=16, mod="BO_DEMO|MANAGER_ID")
	private String managerId;//上级领导

	public void setTreeId(String treeId) {
		this.treeId = treeId;
	}

	public String getTreeId() {
		return this.treeId;
	}
	public void setExpression(String expression) {
		this.expression = expression;
	}

	public String getExpression() {
		return this.expression;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return this.userName;
	}
	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}

	public String getUserSex() {
		return this.userSex;
	}
	public void setUserAge(String userAge) {
		this.userAge = userAge;
	}

	public String getUserAge() {
		return this.userAge;
	}
	public void setUserBirthday(java.util.Date userBirthday) {
		this.userBirthday = userBirthday;
	}

	public java.util.Date getUserBirthday() {
		return this.userBirthday;
	}
	public void setUserIcon(String userIcon) {
		this.userIcon = userIcon;
	}

	public String getUserIcon() {
		return this.userIcon;
	}
	public void setUserSummary(String userSummary) {
		this.userSummary = userSummary;
	}

	public String getUserSummary() {
//		return this.userSummary;
		return HtmlUtils.htmlUnescape(this.userSummary);
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentId() {
		return this.departmentId;
	}
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public String getManagerId() {
		return this.managerId;
	}
}