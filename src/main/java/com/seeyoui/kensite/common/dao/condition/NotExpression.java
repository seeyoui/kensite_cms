package com.seeyoui.kensite.common.dao.condition;

/**
 * Not修饰运算符
 * 
 * @author zouxuemo
 */
public class NotExpression extends Expression {
	public static String EXPRESSION_NOT = "not";

	@Override
	public String hql() {
		return EXPRESSION_NOT;
	}

	@Override
	public Object[] params() {
		return null;
	}

	@Override
	protected int type() {
		return TYPE_MODIFIED_CONDITION;
	}
	
	public static NotExpression not() {
		return new NotExpression();
	}
}
