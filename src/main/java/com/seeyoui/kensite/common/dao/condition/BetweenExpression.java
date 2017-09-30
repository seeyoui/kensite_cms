package com.seeyoui.kensite.common.dao.condition;


/**
 * BETWEEN运算符表达式，提供between、not between运算符
 *
 * @author zouxuemo
 */
public class BetweenExpression extends Expression {
	public static String CONDITION_BETWEEN = "between";
	public static String CONDITION_NOTBETWEEN = "not between";
	public static String CONDITION_AND = "and";
	
	private Object [] values;
	
	private BetweenExpression(String name, String op, Object value1, Object value2) {
		this.name = name;
		this.op = op;
		this.values = new Object[]{value1, value2};
	}
	
	@Override
	public String hql() {
		StringBuffer buf = new StringBuffer();
		buf.append(name);
		buf.append(" ");
		buf.append(op);
		buf.append(" ? ");
		buf.append(CONDITION_AND);
		buf.append(" ?");
		
		return buf.toString();
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
	 * 创建Between运算符
	 * 
	 * @return
	 */
	public static BetweenExpression between(String name, Object value1, Object value2) {
		return new BetweenExpression(name, CONDITION_BETWEEN, value1, value2);
	}
	
	/**
	 * 创建Not Between运算符
	 * 
	 * @return
	 */
	public static BetweenExpression notbetween(String name, Object value1, Object value2) {
		return new BetweenExpression(name, CONDITION_NOTBETWEEN, value1, value2);
	}
}
