package com.seeyoui.kensite.common.dao.condition;

/**
 * 子查询运算符表达式，提供in、not in子查询
 *
 * @author zouxuemo
 */
public class QueryExpression extends Expression {
	public static String SUBQUERY_IN = "in";
	public static String SUBQUER_NOTIN = "not in";
	
	private String subQuery;
	
	private QueryExpression(String name, String op, String subQuery) {
		this.name = name;
		this.op = op;
		this.subQuery = subQuery;
	}

	@Override
	public String hql() {
		return name + " " + op + " (" + subQuery + ")";
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
	 * 创建in query运算符
	 * 
	 * @return
	 */
	public static QueryExpression inquery(String name, String subquery) {
		return new QueryExpression(name, SUBQUERY_IN, subquery);
	}
	
	/**
	 * 创建not in query运算符
	 * 
	 * @return
	 */
	public static QueryExpression notinquery(String name, String subquery) {
		return new QueryExpression(name, SUBQUER_NOTIN, subquery);
	}
}
