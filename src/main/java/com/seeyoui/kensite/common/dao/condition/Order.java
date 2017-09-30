/**
 * 
 */
package com.seeyoui.kensite.common.dao.condition;

/**
 * 排序条件实体类，存放排序信息
 *
 * @author zouxuemo
 */
public class Order {
	public static String ORDER_ASC = "asc";
	public static String ORDER_DESC = "desc";
	
	private String field;
	
	private String order;
	
	private Order(String field, String order) {
		this.field = field;
		this.order = order;
	}
	
	public String getField() {
		return field;
	}

	public String getOrder() {
		return order;
	}

	public String hql() {
		if (ORDER_ASC.equals(order))
			return field;
		else
			return field + " " + order;
	}
	
	public static Order asc(String field) {
		return new Order(field, ORDER_ASC);
	}
	
	public static Order desc(String field) {
		return new Order(field, ORDER_DESC);
	}
}
