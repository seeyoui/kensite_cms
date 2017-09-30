/**
 * 
 */
package com.seeyoui.kensite.common.dao.support;

import java.util.Collection;
import java.util.Iterator;

/**
 * SQL注入监测工具类
 * 
 * @author zouxuemo
 *
 */
public class SqlInjectionChecker {
	
	public static void checkSqlInjection(Object... fragment) {
		for (Object o : fragment)
			checkSingleSqlcheckSqlInjection(o);
	}
	
	public static void checkSqlInjection(Collection<Object> fragment) {
		for (Object o : fragment)
			checkSingleSqlcheckSqlInjection(o);
	}
	
	public static void checkSqlInjection(Iterator<Object> fragment) {
		while (fragment.hasNext())
			checkSingleSqlcheckSqlInjection(fragment.next());
	}
	
	private static void checkSingleSqlcheckSqlInjection(Object o) {
		if (!(o instanceof String))
			return;
		
		String s = (String)o;
		
	}
	
	public void checkHqlInjection() {
		
	}
}
