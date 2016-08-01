package com.seeyoui.kensite.common.taglib.expression.impl.date;

import com.seeyoui.kensite.common.taglib.expression.ExpressionBase;
import com.seeyoui.kensite.common.taglib.expression.ExpressionInterface;
import com.seeyoui.kensite.common.util.DateUtils;

public class TimeExpression extends ExpressionBase implements ExpressionInterface {

	@Override
	public String expresstionPares() {
		return DateUtils.getTime();
	}
	
}
