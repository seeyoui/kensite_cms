package com.seeyoui.kensite.common.dao.condition;

/**
 * 逻辑运算符表达式，提供and、or逻辑运算符
 * 
 *
 * @author zouxuemo
 */
public class LogicalExpression extends Expression {
	public static String LOGICAL_OPERATORS_AND = "and";
	public static String LOGICAL_OPERATORS_OR = "or";
	
	private String logicalOperators;
	
	private LogicalExpression(String logicalOperators) {
		this.logicalOperators = logicalOperators;
	}

	public static LogicalExpression and() {
		return new LogicalExpression(LOGICAL_OPERATORS_AND);
	}

	public static LogicalExpression or() {
		return new LogicalExpression(LOGICAL_OPERATORS_OR);
	}
	
	@Override
	public String hql() {
		return logicalOperators;
	}

	@Override
	public Object[] params() {
		return null;
	}

	@Override
	protected int type() {
		return TYPE_LOGICAL;
	}
}
