/**
 * 
 */
package com.seeyoui.kensite.common.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import com.seeyoui.kensite.common.dao.Condition;
import com.seeyoui.kensite.common.dao.Dao;
import com.seeyoui.kensite.common.dao.condition.Expression;
import com.seeyoui.kensite.common.dao.condition.LeftParenthesesExpression;
import com.seeyoui.kensite.common.dao.condition.LogicalExpression;
import com.seeyoui.kensite.common.dao.condition.NormalExpression;
import com.seeyoui.kensite.common.dao.condition.Order;
import com.seeyoui.kensite.common.dao.condition.RightParenthesesExpression;
import com.seeyoui.kensite.common.dao.condition.SimpleExpression;

/**
 * Condition解析器，负责读取Condition中的查询条件和排序条件，组成参数查询HQL的where子句<br>
 * 返回的where子句格式类似于： where field1 = ? and field2 = ? or ... er by field1, field2 desc, ...<br>
 * 如果Condition没有查询条件，则返回null
 *
 * @author zouxuemo
 */
public class ConditionParser {
	/**
	 * 连接数据库标识，支持标识见Dao接口类的数据库标识名称常量定义
	 */
	private String databaseIdentity;
	
	/**
	 * 连接数据库版本号
	 */
	private String databaseVersion;
	
	/**
	 * 是否是ORACLE数据库
	 */
	private boolean oracleDb = false;
	
	private Condition condition = null;
	
	private String hql = null;
	
	private List<Object> params = new ArrayList<Object>();
	
	
	public ConditionParser(String databaseIdentity, String databaseVersion, Condition condition) {
		this.databaseIdentity = databaseIdentity;
		this.databaseVersion = databaseVersion;
		
		oracleDb = Dao.DATABASE_ORACLE.equals(databaseIdentity);
		
		this.condition = condition;
	}
	
	public String parse(boolean includeOrder) {
		clear();

		if (condition == null)
			return null;
		
		String whereHQL = null, orderHQL = null;
		if (condition.getConditions().size() > 0) {
			HqlAssembler hqlAssembler = new HqlAssembler(" where ", " ");
			
			int leftParenthesesCount = 0;
			
			Expression prevExpression = null;
			for (Expression expression : condition.getConditions()) {
				if (expression instanceof LeftParenthesesExpression)
					leftParenthesesCount++;
				else if (expression instanceof RightParenthesesExpression)
					leftParenthesesCount--;
				
				boolean expected = true, allow = false;
				if (prevExpression != null) {
					expected = prevExpression.expected(expression);
					if (!expected) {
						allow = prevExpression.allow(expression);
						if (!allow)
							throw new RuntimeException("Condition逻辑错误，传入的运算符顺序不符合语法！");
					}
				}
				
				if (allow) {
					Expression insertExpression = LogicalExpression.and();
					
					joinHqlAndParams(insertExpression, hqlAssembler, params);
				}
				
				joinHqlAndParams(expression, hqlAssembler, params);
				prevExpression = expression;
			}
			
			if (leftParenthesesCount != 0)
				throw new RuntimeException("Condition逻辑错误，左括弧和右括弧不匹配！");
				
			whereHQL = hqlAssembler.toString();
		}
		
		if (includeOrder && condition.getOrders().size() > 0) {
			HqlAssembler hqlAssembler = new HqlAssembler(" order by ", ", ");
			
			for (Order order : condition.getOrders())
				hqlAssembler.append(order.hql());
			
			orderHQL = hqlAssembler.toString();
		}
		
		if (whereHQL != null)
			hql = whereHQL;
		
		if (orderHQL != null) {
			if (whereHQL != null)
				hql += orderHQL;
			else
				hql = orderHQL;
		}
		
		return hql;
	}
	
	private void joinHqlAndParams(Expression expression, HqlAssembler hqlAssembler, List<Object> params) {
		/*
		 * 如果Oracle数据库，并且查询条件是等于或者不等于空字符串，则需要把查询条件改为is null或者 is not null查询
		 */
		if (oracleDb) {	//如果是Oracle数据库
			String op = expression.getOp();
			
			if (NormalExpression.CONDITION_EQ.equals(op) || NormalExpression.CONDITION_NE.equals(op)) {	//如果是等于或者不等于查询操作
				Object val = expression.params()[0];
				
				if (val instanceof String) {
					String s = (String)val;
					
					if (s == null || s.length() == 0) {	// 如果条件值是NULL或者空字符串
						
						// 则重新构造ISNULL或者NOTNULL查询表达式以替换原查询表达式
						if (NormalExpression.CONDITION_EQ.equals(op))
							expression = SimpleExpression.isnull(expression.getName());
						else
							expression = SimpleExpression.notnull(expression.getName());
					}
				}
			}
		}
		
		hqlAssembler.append(expression.hql());
		
		Object[] appendParams = expression.params();
		if (appendParams != null && appendParams.length > 0) {
			for (Object obj : appendParams)
				params.add(obj);
		}
	}

	private void clear() {
		hql = null;
		params.clear();
	}
	
	public String hql() {
		return hql;
	}
	
	public Object[] params() {
		return params.toArray(new Object[0]);
	}
}

class HqlAssembler {
	private StringBuffer buf = new StringBuffer();
	
	private boolean first = true;
	
	private String start;
	
	private String connector;
	
	public HqlAssembler(String start, String connector) {
		this.start = start;
		this.connector = connector;
	}
	
	public void append(String fragment) {
		if (fragment == null)
			return;
		
		if (first) {
			buf.append(start);
			
			first = false;
		} else
			buf.append(connector);
		
		buf.append(fragment);
	}
	
	public String toString() {
		return buf.toString();
	}
}
 
