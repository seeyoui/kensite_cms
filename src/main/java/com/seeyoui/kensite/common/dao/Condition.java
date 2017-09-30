package com.seeyoui.kensite.common.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.Assert;

import com.seeyoui.kensite.common.dao.condition.BetweenExpression;
import com.seeyoui.kensite.common.dao.condition.Expression;
import com.seeyoui.kensite.common.dao.condition.InExpression;
import com.seeyoui.kensite.common.dao.condition.LeftParenthesesExpression;
import com.seeyoui.kensite.common.dao.condition.LogicalExpression;
import com.seeyoui.kensite.common.dao.condition.NormalExpression;
import com.seeyoui.kensite.common.dao.condition.NotExpression;
import com.seeyoui.kensite.common.dao.condition.NullExpression;
import com.seeyoui.kensite.common.dao.condition.Order;
import com.seeyoui.kensite.common.dao.condition.QueryExpression;
import com.seeyoui.kensite.common.dao.condition.RightParenthesesExpression;
import com.seeyoui.kensite.common.dao.condition.SimpleExpression;

/**
 * 提供查询条件、排序条件、分页信息的条件类，由dao接口的query等方法使用
 * 
 * @author zouxuemo
 */
public class Condition implements Cloneable {
	public static String CONDITION_EQ = "eq";
	public static String CONDITION_NE = "ne";
	public static String CONDITION_GE = "ge";
	public static String CONDITION_GT = "gt";
	public static String CONDITION_LE = "le";
	public static String CONDITION_LT = "lt";
	public static String CONDITION_LIKE = "like";
	public static String CONDITION_LLIKE = "llike";
	public static String CONDITION_RLIKE = "rlike";
	public static String CONDITION_NOTLIKE = "notlike";
	public static String CONDITION_IN = "in";
	public static String CONDITION_NOTIN = "notin";
	public static String CONDITION_BETWEEN = "between";
	public static String CONDITION_NOTBETWEEN = "notbetween";
	public static String CONDITION_ISNULL = "isnull";
	public static String CONDITION_NOTNULL = "notnull";
	public static String CONDITION_INQUERY = "inquery";
	public static String CONDITION_NOTINQUERY = "notinquery";
	
	public static String ORDER_ASC = "asc";
	public static String ORDER_DESC = "desc";
	
	/**   
	 * IGNORE:忽略这个查询条件<br>
	 * <br>
	 * 已作废，以后前台传了空字符串就认为需要忽略该条件，如果需要查询条件值为空字符串，则条件值可以设置为EMPTY常量值
	 */
	@Deprecated
	public static String IGNORE = "$ignore$";
	
	/**
	 * 传入这个常量值则认为是查询条件值为空字符串
	 */
	public static String EMPTY = "$empty$";
	
	private List<Expression> conditions = new ArrayList<Expression>();
	
	private List<Order> orders = new ArrayList<Order>();
	
	private int start = 0;
	
	private int limit = 0;
	
	public Condition() {
		conditions.add(new NullExpression());
	}
	
	/**
	 * 静态方法，new并返回Condition对象
	 * 
	 * @return
	 */
	public static Condition create() {
		return new Condition();
	}
	
	/**
	 * 插入and连接符
	 * 
	 * @return
	 */
	public Condition and() {
		conditions.add(LogicalExpression.and());
		
		return this;
	}
	
	/**
	 * 插入or连接符
	 * 
	 * @return
	 */
	public Condition or() {
		conditions.add(LogicalExpression.or());
		
		return this;
	}
	
	/**
	 * 插入not操作符
	 * 
	 * @return
	 */
	public Condition not() {
		conditions.add(NotExpression.not());
		
		return this;
	}
	
	/**
	 * 插入左圆括弧
	 * 
	 * @return
	 */
	public Condition lp() {
		conditions.add(LeftParenthesesExpression.lp());
		
		return this;
	}
	
	/**
	 * 插入右圆括弧
	 * 
	 * @return
	 */
	public Condition rp() {
		conditions.add(RightParenthesesExpression.rp());
		
		return this;
	}
	
	/**
	 * 插入大于查询条件
	 * 
	 * @return
	 */
	public Condition gt(String name, Object value) {
		Assert.hasText(name, "name不能为空");
		
		if (value != null)
			conditions.add(NormalExpression.gt(name, value));
		
		return this;
	}
	
	/**
	 * 插入大于等于查询条件
	 * 
	 * @return
	 */
	public Condition ge(String name, Object value) {
		Assert.hasText(name, "name不能为空");
		
		if (value != null)
			conditions.add(NormalExpression.ge(name, value));
		
		return this;
	}
	
	/**
	 * 插入小于查询条件
	 * 
	 * @return
	 */
	public Condition lt(String name, Object value) {
		Assert.hasText(name, "name不能为空");
		
		if (value != null)
			conditions.add(NormalExpression.lt(name, value));
		
		return this;
	}
	
	/**
	 * 插入小于等于查询条件
	 * 
	 * @return
	 */
	public Condition le(String name, Object value) {
		Assert.hasText(name, "name不能为空");
		
		if (value != null)
			conditions.add(NormalExpression.le(name, value));
		
		return this;
	}
	
	/**
	 * 插入等于查询条件
	 * 
	 * @return
	 */
	public Condition eq(String name, Object value) {
		Assert.hasText(name, "name不能为空");
		
		if (value != null)
			conditions.add(NormalExpression.eq(name, value));
		
		return this;
	}
	
	/**
	 * 插入等于查询条件
	 * 
	 * @return
	 */
	public Condition eq(String[] names, Object[] values) {
		if (names == null || values == null)
			return this;
		
		Assert.isTrue(names != null && values != null && names.length == values.length, 
				"names中的字段名数量必须与values中的字段值数量相等");
		
		for (int i = 0; i < names.length; i++) 
			eq(names[i], values[i]);
		
		return this;
	}
	
	/**
	 * 插入不等于查询条件
	 * 
	 * @return
	 */
	public Condition ne(String name, Object value) {
		Assert.hasText(name, "name不能为空");
		
		if (value != null)
			conditions.add(NormalExpression.ne(name, value));
		
		return this;
	}
	
	/**
	 * 插入两边匹配查询条件
	 * 
	 * @return
	 */
	public Condition like(String name, Object value) {
		Assert.hasText(name, "name不能为空");
		
		if (value != null)
			conditions.add(NormalExpression.like(name, value));
		
		return this;
	}
	
	/**
	 * 插入左匹配查询条件
	 * 
	 * @return
	 */
	public Condition llike(String name, Object value) {
		Assert.hasText(name, "name不能为空");
		
		if (value != null)
			conditions.add(NormalExpression.llike(name, value));
		
		return this;
	}
	
	/**
	 * 插入右匹配查询条件
	 * 
	 * @return
	 */
	public Condition rlike(String name, Object value) {
		Assert.hasText(name, "name不能为空");
		
		if (value != null)
			conditions.add(NormalExpression.rlike(name, value));
		
		return this;
	}
	
	/**
	 * 插入两边都不匹配查询条件
	 * 
	 * @return
	 */
	public Condition notlike(String name, Object value) {
		Assert.hasText(name, "name不能为空");
		
		if (value != null)
			conditions.add(NormalExpression.notlike(name, value));
		
		return this;
	}
	
	/**
	 * 插入in查询条件
	 * 
	 * @return
	 */
	public Condition in(String name, Object... value) {
		Assert.hasText(name, "name不能为空");
		
		if (value != null && value.length > 0)
			conditions.add(InExpression.in(name, value));
		
		return this;
	}
	
	/**
	 * 插入not in查询条件
	 * 
	 * @return
	 */
	public Condition notin(String name, Object... value) {
		Assert.hasText(name, "name不能为空");
		
		if (value != null && value.length > 0)
			conditions.add(InExpression.notin(name, value));
		
		return this;
	}
	
	/**
	 * 插入between...and...查询条件
	 *
	 * @param name 条件字段名
	 * @param value1 between条件值
	 * @param value2 and条件值
	 * @return
	 */
	public Condition between(String name, Object value1, Object value2) {
		Assert.hasText(name, "name不能为空");
		Assert.notNull(value1, "beterrn值不能为空");
		Assert.notNull(value2, "and值不能为空");
		
		conditions.add(BetweenExpression.between(name, value1, value2));
		return this;
	}
	
	/**
	 * 插入not between...and...查询条件
	 *
	 * @param name 条件字段名
	 * @param value1 between条件值
	 * @param value2 and条件值
	 * @return
	 */
	public Condition notbetween(String name, Object value1, Object value2) {
		Assert.hasText(name, "name不能为空");
		Assert.notNull(value1, "beterrn值不能为空");
		Assert.notNull(value2, "and值不能为空");
		
		conditions.add(BetweenExpression.notbetween(name, value1, value2));
		return this;
	}
	
	/**
	 * 插入 IS NULL查询条件
	 *
	 * @param name
	 * @return
	 */
	public Condition isnull(String name) {
		Assert.hasText(name, "name不能为空");
		
		conditions.add(SimpleExpression.isnull(name));
		return this;
	}
	
	/**
	 * 插入 IS NOT NULL查询条件
	 *
	 * @param name
	 * @return
	 */
	public Condition notnull(String name) {
		Assert.hasText(name, "name不能为空");
		
		conditions.add(SimpleExpression.notnull(name));
		return this;
	}
	
	/**
	 * 插入IN子查询查询条件
	 * 
	 * @return
	 */
	public Condition inquery(String name, String subquery) {
		Assert.hasText(name, "name不能为空");
		
		if (subquery != null && subquery.length() > 0)
			conditions.add(QueryExpression.inquery(name, subquery));
		
		return this;
	}
	
	/**
	 * 插入NOT IN子查询查询条件
	 * 
	 * @return
	 */
	public Condition notinquery(String name, String subquery) {
		Assert.hasText(name, "name不能为空");
		
		if (subquery != null && subquery.length() > 0)
			conditions.add(QueryExpression.notinquery(name, subquery));
		
		return this;
	}
	
	/**
	 * 插入正序排序条件
	 * 
	 * @return
	 */
	public Condition asc(String name) {
		Assert.hasText(name, "name不能为空");

		orders.add(Order.asc(name));
		return this;
	}
	
	/**
	 * 插入倒序排序条件
	 * 
	 * @return
	 */
	public Condition desc(String name) {
		Assert.hasText(name, "name不能为空");

		orders.add(Order.desc(name));
		return this;
	}
	
	/**
	 * 设置分页信息（页尺寸、页号）
	 * 
	 * @param pageNo 起始页号，从1开始
	 * @param pageSize 每页尺寸，如果为0表示不分页
	 * @return
	 */
	public Condition page(int pageNo, int pageSize) {
		Assert.isTrue(pageNo >= 0, "page no必须为0或者正数");
		Assert.isTrue(pageSize >= 0, "page size必须为0或者正数");
		
		if (pageNo > 0)
			this.start = (pageNo - 1) * pageSize;
		else
			this.start = 0;
    	this.limit = pageSize;
			
		return this;
	}
	
	/**
	 * 设置分页信息（起始行号和返回行数）
	 * 
	 * @param start 起始行号，从0开始
	 * @param limit 限制返回行数，如果为0表示不分页
	 * @return
	 */
	public Condition limit(int start, int limit) {
		Assert.isTrue(start >= 0, "start必须为0或者正数");
		Assert.isTrue(limit >= 0, "limit必须为0或者正数");
		
		this.start = start;
		this.limit = limit;
		
		return this;
	}
	
	public Condition condition(String name, String op, Object... value) {
		if (CONDITION_EQ.equals(op)) {
			checkValuesLength(value, 1, 1);
			
			eq(name, value[0]);
		} else if (CONDITION_NE.equals(op)) {
			checkValuesLength(value, 1, 1);
			
			ne(name, value[0]);
		} else if (CONDITION_GE.equals(op)) {
			checkValuesLength(value, 1, 1);
			
			ge(name, value[0]);
		} else if (CONDITION_GT.equals(op)) {
			checkValuesLength(value, 1, 1);
			
			gt(name, value[0]);
		} else if (CONDITION_LE.equals(op)) {
			checkValuesLength(value, 1, 1);
			
			le(name, value[0]);
		} else if (CONDITION_LT.equals(op)) {
			checkValuesLength(value, 1, 1);
			
			lt(name, value[0]);
		} else if (CONDITION_LIKE.equals(op)) {
			checkValuesLength(value, 1, 1);
			
			like(name, value[0]);
		} else if (CONDITION_LLIKE.equals(op)) {
			checkValuesLength(value, 1, 1);
			
			llike(name, value[0]);
		} else if (CONDITION_RLIKE.equals(op)) {
			checkValuesLength(value, 1, 1);
			
			rlike(name, value[0]);
		} else if (CONDITION_NOTLIKE.equals(op)) {
			checkValuesLength(value, 1, 1);
			
			notlike(name, value[0]);
		} else if (CONDITION_IN.equals(op)) {
			checkValuesLength(value, 1, -1);
			
			in(name, value);
		} else if (CONDITION_NOTIN.equals(op)) {
			checkValuesLength(value, 1, -1);
			
			notin(name, value);
		} else if (CONDITION_BETWEEN.equals(op)) {
			checkValuesLength(value, 2, 2);
			
			between(name, value[0], value[1]);
		} else if (CONDITION_NOTBETWEEN.equals(op)) {
			checkValuesLength(value, 2, 2);
			
			notbetween(name, value[0], value[1]);
		} else if (CONDITION_ISNULL.equals(op)) {
			checkValuesLength(value, -1, 0);
			
			isnull(name);
		} else if (CONDITION_NOTNULL.equals(op)) {
			checkValuesLength(value, -1, 0);
			
			notnull(name);
		} else if (CONDITION_INQUERY.equals(op)) {
			checkValuesLength(value, 1, 1);
			
			inquery(name, value[0].toString());
		} else if (CONDITION_NOTINQUERY.equals(op)) {
			checkValuesLength(value, 1, 1);
			
			notinquery(name, value[0].toString());
		} else
			Assert.isTrue(false, "不识别的条件操作字符串");

		return this;
	}
	
	public void clearCondition() {
		conditions.clear();
	}
	
	public Condition order(String name, String dir) {
		if (ORDER_ASC.equalsIgnoreCase(dir)) 
			asc(name);
		 else if (ORDER_DESC.equalsIgnoreCase(dir)) 
			desc(name);
		 else
			 Assert.isTrue(false, "不识别的排序方向字符串");
		
		return this;
	}
	
	public void clearOrder() {
		orders.clear();
	}
	
	private void checkValuesLength(Object[] values, int minLength, int maxLength) {
		Assert.isTrue((minLength  < 0 || values.length >= minLength) && (maxLength  < 0 || values.length <= maxLength), "条件值个数错误");
	}
	
	@Override
	public Condition clone() {
		Condition clone = null;
		try {
			clone = (Condition)super.clone();
		} catch (CloneNotSupportedException e) {}
		
		if (conditions.size() > 0) {
			List<Expression> cloneConditions = new ArrayList<Expression>();
			cloneConditions.addAll(conditions);
		}
		
		if (orders.size() > 0) {
			List<Order> cloneOrders = new ArrayList<Order>();
			cloneOrders.addAll(orders);
		}
		
		return clone;
	}
	
	/********** 提供给Condition解析器调用 **********/
	
	public List<Expression> getConditions() {
		return conditions;
	}
	
	public List<Order> getOrders() {
		return orders;
	}

	public int getStart() {
		return start;
	}

	public int getLimit() {
		return limit;
	}
}
