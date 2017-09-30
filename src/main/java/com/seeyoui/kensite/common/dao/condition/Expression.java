/**
 * 
 */
package com.seeyoui.kensite.common.dao.condition;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;

/**
 * 查询条件基类，是所有查询条件（包括：and,or,not,=,like,in,等等）的基类
 *
 * @author zouxuemo
 */
public abstract class Expression {
	protected static int TYPE_NULL				 = 0;	//空操作符			期望：条件表达式、修饰条件表达式、左括弧
	protected static int TYPE_LOGICAL			 = 1;	//逻辑操作符		期望：条件表达式、修饰条件表达式、左括弧
	protected static int TYPE_CONDITION			 = 2;	//条件表达式		期望：逻辑操作符、右括弧			允许：条件表达式、修饰条件表达式、左括弧
	protected static int TYPE_MODIFIED_CONDITION	 = 3;	//修饰条件表达式	期望：条件表达式、左括弧
	protected static int TYPE_LEFT_PARENTHESES	 = 4;	//左括弧			期望：条件表达式、修饰条件表达式
	protected static int TYPE_RIGHT_PARENTHESES	 = 5;	//右括弧			期望：逻辑操作符、右括弧			允许：条件表达式、修饰条件表达式、左括弧

	private static Map<Integer, int[]> expectedMap = new HashMap<Integer, int[]>();
	private static Map<Integer, int[]> allowMap = new HashMap<Integer, int[]>();

	static {
		expectedMap.put(TYPE_NULL, new int[]{TYPE_CONDITION, TYPE_MODIFIED_CONDITION, TYPE_LEFT_PARENTHESES});
		expectedMap.put(TYPE_LOGICAL, new int[]{TYPE_CONDITION, TYPE_MODIFIED_CONDITION, TYPE_LEFT_PARENTHESES});
		expectedMap.put(TYPE_CONDITION, new int[]{TYPE_LOGICAL, TYPE_RIGHT_PARENTHESES});
		expectedMap.put(TYPE_MODIFIED_CONDITION, new int[]{TYPE_CONDITION, TYPE_LEFT_PARENTHESES});
		expectedMap.put(TYPE_LEFT_PARENTHESES, new int[]{TYPE_CONDITION, TYPE_MODIFIED_CONDITION});
		expectedMap.put(TYPE_RIGHT_PARENTHESES, new int[]{TYPE_LOGICAL, TYPE_RIGHT_PARENTHESES});
		
		allowMap.put(TYPE_CONDITION, new int[]{TYPE_CONDITION, TYPE_MODIFIED_CONDITION, TYPE_LEFT_PARENTHESES});
		allowMap.put(TYPE_RIGHT_PARENTHESES, new int[]{TYPE_CONDITION, TYPE_MODIFIED_CONDITION, TYPE_LEFT_PARENTHESES});
	}
	
	protected abstract int type();
	
	public boolean expected(Expression next) {
		int[] expectedType = expectedMap.get(type());
		if (expectedType == null)
			return false;
		
		return ArrayUtils.contains(expectedType, next.type());
	}
	
	public boolean allow(Expression next) {
		int[] allowType = allowMap.get(type());
		if (allowType == null)
			return false;
		
		return ArrayUtils.contains(allowType, next.type());
	}
	
	protected String name = null;
	
	protected String op = null;
	
	public String getName() {
		return this.name;
	}
	
	public String getOp() {
		return this.op;
	}
	
	public abstract String hql();
	
	public abstract Object[] params();
}
