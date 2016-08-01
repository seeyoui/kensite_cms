package com.seeyoui.kensite.common.taglib.expression;

import java.lang.reflect.Method;

import com.seeyoui.kensite.common.taglib.constants.ExpressionConstants;

public class ExpressionUtils {
	public static String parse(String expression) {
		Class<?> expressionClass = null;
        try {
        	for (String key : ExpressionConstants.expressionMap.keySet()) {
        	    String value = ExpressionConstants.expressionMap.get(key);
        	    expressionClass = Class.forName(value);
        	    Method method=expressionClass.getMethod("expresstionPares");
        	    String result = (String) method.invoke(expressionClass.newInstance());
        	    expression = expression.replaceAll(key, result);
        	}
        } catch (Exception e) {
            e.printStackTrace();
        }
		return expression;
	}
}
