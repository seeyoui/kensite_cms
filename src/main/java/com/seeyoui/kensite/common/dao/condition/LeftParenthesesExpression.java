package com.seeyoui.kensite.common.dao.condition;

/**
 * 左括弧运算符
 * 
 *
 * @author zouxuemo
 */
public class LeftParenthesesExpression extends Expression {
	public static String EXPRESSION_LEFT_PARENTHESES = "(";

	@Override
	public String hql() {
		return EXPRESSION_LEFT_PARENTHESES;
	}

	@Override
	public Object[] params() {
		return null;
	}

	@Override
	protected int type() {
		return TYPE_LEFT_PARENTHESES;
	}
	
	public static LeftParenthesesExpression lp() {
		return new LeftParenthesesExpression();
	}
}
