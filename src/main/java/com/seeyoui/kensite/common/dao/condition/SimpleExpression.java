package com.seeyoui.kensite.common.dao.condition;

/**
 * 简单运算符表达式，提供is null,is not null运算符
 *
 * @author zouxuemo
 */
public class SimpleExpression extends Expression {
	public static String CONDITION_ISNULL = "is null";
	public static String CONDITION_ISNOTNULL = "is not null";
	
	private SimpleExpression(String name, String op) {
		this.name = name;
		this.op = op;
	}

	@Override
	public String hql() {
		return name + " " + op;
	}

	@Override
	public Object[] params() {
		return null;
	}

	@Override
	protected int type() {
		return TYPE_CONDITION;
	}
	
	/**
	 * 创建is null运算符
	 * 
	 * @return
	 */
	public static SimpleExpression isnull(String name) {
		return new SimpleExpression(name, CONDITION_ISNULL);
	}
	
	/**
	 * 创建is not null运算符
	 * 
	 * @return
	 */
	public static SimpleExpression notnull(String name) {
		return new SimpleExpression(name, CONDITION_ISNOTNULL);
	}
}
