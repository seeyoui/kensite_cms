package com.seeyoui.kensite.common.dao.condition;


/**
 * 条件运算符表达式，提供大于、大于等于、小于、小于等于、等于、不等于、匹配、不匹配、左匹配、右匹配运算符
 *
 * @author zouxuemo
 */
public class NormalExpression extends Expression {
	public static String CONDITION_GT = ">";
	public static String CONDITION_GE = ">=";
	public static String CONDITION_LT = "<";
	public static String CONDITION_LE = "<=";
	public static String CONDITION_EQ = "=";
	public static String CONDITION_NE = "<>";
	public static String CONDITION_LIKE = "like";
	public static String CONDITION_NOTLIKE = "not like";
	
	private Object[] values;
	
	private NormalExpression(String name, String op, Object[] values) {
		this.name = name;
		this.op = op;
		this.values = values;
	}
	
	@Override
	public String hql() {
		return name + " " + op + " ?";
	}

	@Override
	public Object[] params() {
		return values;
	}

	@Override
	protected int type() {
		return TYPE_CONDITION;
	}
	
	/**
	 * 创建大于运算符
	 * 
	 * @return
	 */
	public static NormalExpression gt(String name, Object value) {
		return new NormalExpression(name, CONDITION_GT, new Object[]{value});
	}
	
	/**
	 * 创建大于等于运算符
	 * 
	 * @return
	 */
	public static NormalExpression ge(String name, Object value) {
		return new NormalExpression(name, CONDITION_GE, new Object[]{value});
	}
	
	/**
	 * 创建小于运算符
	 * 
	 * @return
	 */
	public static NormalExpression lt(String name, Object value) {
		return new NormalExpression(name, CONDITION_LT, new Object[]{value});
	}
	
	/**
	 * 创建小于等于运算符
	 * 
	 * @return
	 */
	public static NormalExpression le(String name, Object value) {
		return new NormalExpression(name, CONDITION_LE, new Object[]{value});
	}
	
	/**
	 * 创建等于运算符
	 * 
	 * @return
	 */
	public static NormalExpression eq(String name, Object value) {
		return new NormalExpression(name, CONDITION_EQ, new Object[]{value});
	}
	
	/**
	 * 创建不等于运算符
	 * 
	 * @return
	 */
	public static NormalExpression ne(String name, Object value) {
		return new NormalExpression(name, CONDITION_NE, new Object[]{value});
	}
	
	/**
	 * 创建匹配运算符
	 * 
	 * @return
	 */
	public static NormalExpression like(String name, Object value) {
		String val = "%" + value.toString() + "%";
		
		return new NormalExpression(name, CONDITION_LIKE, new Object[]{val});
	}
	
	/**
	 * 创建左匹配运算符
	 * 
	 * @return
	 */
	public static NormalExpression llike(String name, Object value) {
		String val = value.toString() + "%";
		
		return new NormalExpression(name, CONDITION_LIKE, new Object[]{val});
	}
	
	/**
	 * 创建右匹配运算符
	 * 
	 * @return
	 */
	public static NormalExpression rlike(String name, Object value) {
		String val = "%" + value.toString();
		
		return new NormalExpression(name, CONDITION_LIKE, new Object[]{val});
	}
	
	/**
	 * 创建不匹配运算符
	 * 
	 * @return
	 */
	public static NormalExpression notlike(String name, Object value) {
		String val = "%" + value.toString() + "%";
		
		return new NormalExpression(name, CONDITION_NOTLIKE, new Object[]{val});
	}
}
