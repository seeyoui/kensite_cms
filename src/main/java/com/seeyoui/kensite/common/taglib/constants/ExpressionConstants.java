package com.seeyoui.kensite.common.taglib.constants;

import java.util.HashMap;
import java.util.Map;

public class ExpressionConstants {
	public static Map<String, String> expressionMap = new HashMap<String, String>();
	static {
		expressionMap.put("@userId", "com.seeyoui.kensite.common.taglib.expression.impl.user.UserIdExpression");
		expressionMap.put("@userName", "com.seeyoui.kensite.common.taglib.expression.impl.user.UserNameExpression");
		expressionMap.put("@name", "com.seeyoui.kensite.common.taglib.expression.impl.user.NameExpression");
		expressionMap.put("@departmentId", "com.seeyoui.kensite.common.taglib.expression.impl.user.DepartmentIdExpression");
		expressionMap.put("@departmentName", "com.seeyoui.kensite.common.taglib.expression.impl.user.DepartmentNameExpression");
		expressionMap.put("@departmentCode", "com.seeyoui.kensite.common.taglib.expression.impl.user.DepartmentCodeExpression");
		
		expressionMap.put("@dateTime", "com.seeyoui.kensite.common.taglib.expression.impl.date.DateTimeExpression");
		expressionMap.put("@date", "com.seeyoui.kensite.common.taglib.expression.impl.date.DateExpression");
		expressionMap.put("@time", "com.seeyoui.kensite.common.taglib.expression.impl.date.TimeExpression");
		expressionMap.put("@year", "com.seeyoui.kensite.common.taglib.expression.impl.date.YearExpression");
		expressionMap.put("@month", "com.seeyoui.kensite.common.taglib.expression.impl.date.MonthExpression");
		expressionMap.put("@day", "com.seeyoui.kensite.common.taglib.expression.impl.date.DayExpression");
		expressionMap.put("@week", "com.seeyoui.kensite.common.taglib.expression.impl.date.WeekExpression");
	}
}
