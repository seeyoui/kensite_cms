package com.seeyoui.kensite.common.dao.condition;

public class NullExpression extends Expression {
	@Override
	public String hql() {
		return null;
	}

	@Override
	public Object[] params() {
		return null;
	}

	@Override
	protected int type() {
		return TYPE_NULL;
	}

}
