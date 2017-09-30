package com.seeyoui.kensite.common.dao.condition;

/**
 * 右括弧运算符
 * 
 *
 * @author zouxuemo
 */
public class RightParenthesesExpression extends Expression {
	public static String EXPRESSION_RIGHT_PARENTHESES = ")";

	@Override
	public String hql() {
		return EXPRESSION_RIGHT_PARENTHESES;
	}

	@Override
	public Object[] params() {
		return null;
	}

	@Override
	protected int type() {
		return TYPE_RIGHT_PARENTHESES;
	}
	
	public static RightParenthesesExpression rp() {
		return new RightParenthesesExpression();
	}
}
