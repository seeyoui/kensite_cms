/**
 * 
 */
package com.seeyoui.kensite.common.dao.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;

/**
 * @author Administrator
 *
 * 提供对字符串中定义的变量进行处理（简单替换或者执行表达式计算）的工具类
 * <br>允许在构造函数指定变量的左右包含符，默认左右包含符为'{'和'}'
 * <br>左右包含符可以指定常量NO_VARIABLE_SYMBOL，表示找到非变量字符为止
 * <br>对于包含符，允许通过连续设置两个包含符去除包含关系，例如："{{}}"将识别为非包含符
 * <br>
 * <p>1.搜索字符串中所有以包含符包含的变量字段，返回包含的变量列表
 * <br>FieldTokenProcessor processor = new FieldTokenProcessor();
 * <br>processor.searchFieldToken("select {field1}, {field2} from table");	//返回{'field1','field2'}的变量集合
 * 
 * <p>2.搜索字符串中所有以包含符包含的变量字段，并从给定上下文参数中替换字符串变量，返回替换变量后的字符串信息
 * <br>Map<String, Object> params = new HashMap<String, Object>();
 * <br>params.put("a", 123);
 * <br>params.put("b", 123.456);
 * <br>params.put("c", true);
 * <br>params.put("d", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2012-12-21 12:24:42"));
 * <br>
 * <br>FieldTokenProcessor processor = new FieldTokenProcessor();
 * <br>processor.replaceFieldToken("{a},{b},{c},{d},{e}", params, false);	//返回123,123.456,true,2012-12-21 12:24:42,
 * <br>processor.replaceFieldToken("{a},{b},{c},{d},{e}", params, true);		//抛出异常
 * 
 * <p>3.搜索字符串中所有以包含符包含的表达式字段，并根据给定上下文参数计算表达式字段，返回计算表达式后的字符串信息
 * <br>Map<String, Object> context = new HashMap<String, Object>();
 * <br>context.put("field1", "abc");
 * <br>context.put("field2", 123);
 * <br>context.put("field3", 111.1);
 * <br>context.put("field4", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2012-12-21 12:24:42"));
 * <br>
 * <br>FieldTokenProcessor processor = new FieldTokenProcessor();
 * <br>processor.evaluateFieldToken("field1+field2+field3+field4, result: {field1+field2+field3+'('+date_to_string(field4,'yyyy-MM-dd HH:mm:ss')+')'}", map);	//返回field1+field2+field3+field4, result: abc123111.1(2012-12-21 12:24:42)
 */
public class FieldTokenProcessor {
    protected final Log logger = LogFactory.getLog(getClass());

    private static FieldTokenProcessor instance = new FieldTokenProcessor();
    
    public static char NO_VARIABLE_SYMBOL = 0x0;
    
	/**
	 * 左包含符
	 */
	private char include_symbol_left;
	/**
	 * 右包含符
	 */
	private char include_symbol_right;
	
	/**
	 * 默认构造函数
	 */
	public FieldTokenProcessor() {
		this('{', '}');
	}
	
	/**
	 * 构造函数，要求提供左右包含符字符
	 * 
	 * @param include_symbol_left 左包含符字符
	 * @param include_symbol_right 右包含符字符
	 */
	public FieldTokenProcessor(char include_symbol_left, char include_symbol_right) {
		if (include_symbol_left == NO_VARIABLE_SYMBOL)
			throw new RuntimeException("左包含符不允许设置为NO_VARIABLE_SYMBOL常量");
		
		this.include_symbol_left = include_symbol_left;
		this.include_symbol_right = include_symbol_right;
	}
	
	public static FieldTokenProcessor defaultProcessor() {
		return instance;
	} 
	
	/**
	 * 搜索字符串中所有以包含符包含的非空变量字段，返回包含的变量列表（变量名不重复）
	 * 
	 * @param str 要搜索的字符串
	 * @return
	 */
	public List<String> searchFieldToken(String str) {
		return searchFieldToken(str, true);
	}

	/**
	 * 搜索字符串中所有以包含符包含的非空变量字段，返回包含的变量列表
	 * 
	 * @param str
	 * @param ignoreRepeatToken 返回变量列表中是否忽略重复的变量，如果不忽略，则查找到的所有变量都返回
	 * @return
	 */
	public List<String> searchFieldToken(String str, boolean ignoreRepeatToken) {
		List<String> result = new ArrayList<String>();
		int tokenBegin = 0, end = str.length() - 1;
		boolean find = false;
		
		for (int i = 0; i <= end; i++) {
			char ch = str.charAt(i);
			
			if (ch == include_symbol_left) {
				if (find)
					throw new RuntimeException("表达式重复嵌套变量！");
				
				// 如果两个左包含字符，则不再作为包含符
				if (i < end && str.charAt(i + 1) == include_symbol_left) {
					i++;
				} else {
					tokenBegin = i + 1;
					find = true;
				}
			} else if (ch == include_symbol_right) {
				if (!find) {
					// 如果两个右包含字符，则不再作为包含符
					if (i < end && str.charAt(i + 1) == include_symbol_right) {
						i++;
					} else
						throw new RuntimeException("表达式变量未设置起始嵌套符！");
				} else {
					String field = str.substring(tokenBegin, i);
					if (field.length() > 0 && (!ignoreRepeatToken || !result.contains(field)))
						result.add(field);
					
					find = false;
				}
			} else if (include_symbol_right == NO_VARIABLE_SYMBOL && find &&
						!Character.isJavaIdentifierPart(ch)) {
				String field = str.substring(tokenBegin, i);
				if (field.length() > 0 && (!ignoreRepeatToken || !result.contains(field)))
					result.add(field);
				
				find = false;
			}
		}
		
		if (find) {
			if (include_symbol_right == NO_VARIABLE_SYMBOL) {
				String field = str.substring(tokenBegin);
				if (field.length() > 0 && (!ignoreRepeatToken || !result.contains(field)))
					result.add(field);
			} else
				throw new RuntimeException("表达式变量未设置结束嵌套符！");
		}
			
		return result;
	}
	
	/**
	 * 搜索字符串中所有的可作为变量字段，返回包含的变量列表
	 * <p>这个方法与searchFieldToken不同之处在于本方法检查变量字段不是用"{}"包围，而是分析变量字段是不是以字符、"$"或"_"开头，后面跟字符、数字、"$"或"_"
	 * <br>例如：a1+$$*10 b-_c/d_d，将会返回{'a1' '$$' 'b' '_c' 'd_d'}变量字段列表
	 * 
	 * @return
	 */
	public static List<String> searchAvailableVariableToken(String searchStr) {
		return searchAvailableVariableToken(searchStr, true);
	}
	
	public static List<String> searchAvailableVariableToken(String searchStr, boolean ignoreRepeatToken) {
		List<String> result = new ArrayList<String>();
		
		int tokenBegin = -1, tokenEnd = 0, length = searchStr.length();
		for (int i = 0; i < length; i++) {
			if (tokenBegin < 0 && Character.isJavaIdentifierStart(searchStr.charAt(i))) {
				tokenBegin = i;
			} else if (tokenBegin >= 0 && !Character.isJavaIdentifierPart(searchStr.charAt(i))) {
				tokenEnd = i;
				
				String field = searchStr.substring(tokenBegin, tokenEnd);
				if (field.length() > 0 && (!ignoreRepeatToken || !result.contains(field)))
					result.add(field);
				
				tokenBegin = -1;
			}
		}
		
		if (tokenBegin >= 0) {
			String field = searchStr.substring(tokenBegin);
			if (field.length() > 0 && (!ignoreRepeatToken || !result.contains(field)))
				result.add(field);
		}
		
		return result;
	}
	
	/**
	 * 搜索字符串中所有的可作为变量字段，返回包含的变量列表
	 * <p>这个方法与searchAvailableVariableToken不同之处在于本方法认为"aa.bb.cc"也是合法的嵌套变量，提供了对嵌套变量的提取支持
	 * <br>例如：a.b+c.d，将会返回{'a.b' 'c.d'}变量字段列表
	 * 
	 * @return
	 */
	public static List<String> searchNestedVariableToken(String searchStr) {
		return searchNestedVariableToken(searchStr, true);
	}
	
	public static List<String> searchNestedVariableToken(String searchStr, boolean ignoreRepeatToken) {
		List<String> result = new ArrayList<String>();
		
		int tokenBegin = -1, tokenEnd = 0, length = searchStr.length();
		for (int i = 0; i < length; i++) {
			char ch = searchStr.charAt(i);
			if (tokenBegin < 0 && Character.isJavaIdentifierStart(ch)) {
				tokenBegin = i;
			} else if (tokenBegin >= 0 && !Character.isJavaIdentifierPart(ch) && ch != '.') {
				tokenEnd = i;
				
				String field = searchStr.substring(tokenBegin, tokenEnd);
				if (field.length() > 0 && (!ignoreRepeatToken || !result.contains(field)))
					result.add(field);
				
				tokenBegin = -1;
			}
		}
		
		if (tokenBegin >= 0) {
			String field = searchStr.substring(tokenBegin);
			if (field.length() > 0 && (!ignoreRepeatToken || !result.contains(field)))
				result.add(field);
		}
		
		return result;
	}
	
	/**
	 * 搜索字符串中所有以包含符包含的变量字段，并从给定上下文参数中替换字符串变量，返回替换变量后的字符串信息
	 * 
	 * @param str 要替换的字符串
	 * @param context 给定变量值的上下文，可以是一个Map&lt;String, Object&gt;，或者是一个Bean
	 * @param throwExceptionParamNotFound 如果为true，当变量值在上下文中未提供时抛出异常，否则忽略该变量
	 * @return
	 */
	public String replaceFieldToken(String str, Object context, boolean throwExceptionParamNotFound) {
		return processFieldToken(str, context, true, throwExceptionParamNotFound);
	}
	
	/**
	 * 搜索字符串中所有以包含符包含的变量字段，并按照顺序从给定的变量值数组中替换字符串变量，返回替换变量后的字符串信息<br>
	 * 示例：<br>
	 * 　processor.replaceFieldToken("select {}, {} from {} where a = {a}", "code", "name", "table", "123")<br>
	 * 　// 结果：select code, name from table where a = 123
	 * @param str
	 * @param context 要替换的变量值数组，要求变量值是基本类型或者包装类（String,Number,Boolean,Date）
	 * @return
	 */
	public String replaceFieldToken(String str, Object... context) {
		StringBuffer buf = new StringBuffer();
		
		int end = str.length() - 1;
		boolean find = false;
		int index = 0, contextLength = context.length;
		for (int i = 0; i <= end; i++) {
			char ch = str.charAt(i);
			
			if (ch == include_symbol_left) {
				if (find)
					throw new RuntimeException("表达式重复嵌套变量！");
				
				// 如果两个左包含字符，则不再作为包含符
				if (i < end && str.charAt(i + 1) == include_symbol_left) {
					buf.append(ch);
					
					i++;
				} else {
					find = true;
				}
			} else if (ch == include_symbol_right) {
				if (!find) {
					// 如果两个右包含字符，则不再作为包含符
					if (i < end && str.charAt(i + 1) == include_symbol_right) {
						buf.append(ch);
						
						i++;
					} else
						throw new RuntimeException("表达式变量未设置起始嵌套符！");
				} else {
					if (index == contextLength)
						throw new RuntimeException("要替换变量超出给定变量值范围！");
					
					String val = ConvertUtils.convert(context[index++]);
					buf.append(val);
					
					find = false;
				}
			} else if (include_symbol_right == NO_VARIABLE_SYMBOL && find &&
					!Character.isJavaIdentifierPart(ch)) {
				if (index == contextLength)
					throw new RuntimeException("要替换变量超出给定变量值范围！");
				
				String val = ConvertUtils.convert(context[index++]);
				buf.append(val);
				
				find = false;
				buf.append(ch);
			} else if (!find)
				buf.append(ch);
		}
		
		if (find) {
			if (include_symbol_right == NO_VARIABLE_SYMBOL) {
				if (index == contextLength)
					throw new RuntimeException("要替换变量超出给定变量值范围！");
				
				String val = ConvertUtils.convert(context[index++]);
				buf.append(val);
			} else
				throw new RuntimeException("表达式变量未设置结束嵌套符！");
		}
		
		return buf.toString();
		
	}
	
	/**
	 * 搜索字符串中所有以包含符包含的表达式字段，并根据给定上下文参数计算表达式字段，返回计算表达式后的字符串信息
	 * 
	 * @param str 要计算的字符串
	 * @param context 给定变量值的上下文，可以是一个Map&lt;String, Object&gt;，或者是一个Bean
	 * @return
	 */
	public String evaluateFieldToken(String str, Object context) {
		return processFieldToken(str, context, false, false);
	}
	
	/**
	 * 分析表达式，返回分析结果
	 * 实例：
	 * 	List<Object> expressionFragment = parseExpression("my name is {name}, i have age {datediff(sysdate(), string_to_date(birthday, 'yyyy-MM-dd'), 'y')}, thank you!")
	 * 	//结果：["my name is ", Expression("name"), ", i have age ", Expression("datediff(sysdate(), string_to_date(birthday, 'yyyy-MM-dd'), 'y')"), ", thank you!"]
	 * 
	 * @param expression
	 * @return
	 */
	public List<Object> parseExpression(String expression)  {
		if (include_symbol_right == NO_VARIABLE_SYMBOL)
			throw new RuntimeException("表达式分析不允许设置左右包含符为NO_VARIABLE_SYMBOL");
		
		List<Object> expressionFragment = new ArrayList<Object>();
		
		if (expression == null)
			return expressionFragment;
		
		int tokenBegin = 0, end = expression.length() - 1;
		StringBuffer buf = new StringBuffer();
		boolean find = false;
		for (int i = 0; i <= end; i++) {
			char ch = expression.charAt(i);
			
			if (ch == include_symbol_left) {
				if (find)
					throw new RuntimeException("表达式重复嵌套变量！");
				
				// 如果两个左包含字符，则不再作为包含符
				if (i < end && expression.charAt(i + 1) == include_symbol_left) {
					buf.append(ch);
					
					i++;
				} else {
					if (buf.length() > 0) {
						expressionFragment.add(buf.toString());
						
						buf.setLength(0);
					}
					
					tokenBegin = i + 1;
					find = true;
				}
			} else if (expression.charAt(i) == include_symbol_right) {
				if (!find) {
					// 如果两个右包含字符，则不再作为包含符
					if (i < end && expression.charAt(i + 1) == include_symbol_right) {
						buf.append(ch);
						
						i++;
					} else
						throw new RuntimeException("表达式变量未设置起始嵌套符！");
				} else {
					String exp = expression.substring(tokenBegin, i);
					if (exp.length() > 0)
						expressionFragment.add(AviatorEvaluator.compile(exp, true));
					
					find = false;
				}
			} else if (!find){
				buf.append(ch);
			}
		}
		
		if (find)
			throw new RuntimeException("表达式变量未设置结束嵌套符！");
		else if (buf.length() > 0)
			expressionFragment.add(buf.toString());
		
		return expressionFragment;
	}
	
	public String evaluateExpression(List<Object> expressionFragment, Map<String, Object> env) {
		if (expressionFragment == null)
			return "";
		
		StringBuffer buf = new StringBuffer();
		for (Object o : expressionFragment) {
			if (o instanceof String) {
				buf.append(o);
			} else {
				Expression exp = (Expression)o;
				Object result = exp.execute(env);
				
				buf.append(result);
			}
		}
		
		return buf.toString();
	}
	
	@SuppressWarnings("unchecked")
	private String processFieldToken(String str, Object context, boolean simpleReplaceParam, boolean throwExceptionIfParamNotFound) {
		StringBuffer buf = new StringBuffer();
		
		Map<String, Object> env;
		if (context instanceof Map)
			env = (Map<String, Object>)context;
		else {
			env = new HashMap<String, Object>();
			
			TransferUtils.copy(context, env, null);
		}
			
		int tokenBegin = 0, end = str.length() - 1;
		boolean find = false;
		for (int i = 0; i <= end; i++) {
			char ch = str.charAt(i);
			
			if (ch == include_symbol_left) {
				if (find)
					throw new RuntimeException("表达式重复嵌套变量！");
				
				// 如果两个左包含字符，则不再作为包含符
				if (i < end && str.charAt(i + 1) == include_symbol_left) {
					buf.append(ch);
					
					i++;
				} else {
					tokenBegin = i + 1;
					find = true;
				}
				
			} else if (ch == include_symbol_right) {
				if (!find) {
					// 如果两个右包含字符，则不再作为包含符
					if (i < end && str.charAt(i + 1) == include_symbol_right) {
						buf.append(ch);
						
						i++;
					} else
						throw new RuntimeException("表达式变量未设置起始嵌套符！");
				} else {
					String exp = str.substring(tokenBegin, i);
					if (exp.length() > 0) {
						String val = processExpression(exp, env, simpleReplaceParam, throwExceptionIfParamNotFound);
						buf.append(val);						
					}
					
					find = false;
				}
			} else if (include_symbol_right == NO_VARIABLE_SYMBOL && find &&
					!Character.isJavaIdentifierPart(ch)) {
				String exp = str.substring(tokenBegin, i);
				if (exp.length() > 0) {
					String val = processExpression(exp, env, simpleReplaceParam, throwExceptionIfParamNotFound);
					buf.append(val);
				} else
					throw new RuntimeException("表达式变量格式错误，变量名要求是字母、数字和下划线组合！");
				
				find = false;
				buf.append(ch);
			} else if (!find)
				buf.append(ch);
		} 
		
		if (find) {
			if (include_symbol_right == NO_VARIABLE_SYMBOL) {
				String exp = str.substring(tokenBegin);
				if (exp.length() > 0) {
					String val = processExpression(exp, env, simpleReplaceParam, throwExceptionIfParamNotFound);
					buf.append(val);
				} else
					throw new RuntimeException("表达式变量格式错误，变量名要求是字母、数字和下划线组合！");
			} else
				throw new RuntimeException("表达式变量未设置结束嵌套符！");
		}
		
		return buf.toString();
	}
	
	private String processExpression(String exp, Map<String, Object> env, boolean simpleReplaceParam, boolean throwExceptionIfParamNotFound) {
		Object result;
		if (simpleReplaceParam) {
			if (!env.containsKey(exp)) {
				if (throwExceptionIfParamNotFound)
					throw new RuntimeException("表达式变量[" + exp + "]未提供变量值！");
				else
					result = "";
			} else {
				result = env.get(exp);
			}
		} else {
			Expression expression = AviatorEvaluator.compile(exp, true);
			result = expression.execute(env);
		}
		
		return ConvertUtils.convert(result);
	}
	
	public static void main(String[] args) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("a", "abc");
		param.put("b", 123.45);
		param.put("c", true);
		param.put("d", new Date());
		
		FieldTokenProcessor processor = new FieldTokenProcessor();
		
		List<String> tokens = processor.searchFieldToken("select * from }} {{table}} where a = {{{a}}} and {{{{b}}}}");
		System.out.println(tokens);
		
		List<Object> elements = processor.parseExpression("{wsx}select * from }} {{table}} where a = {{{abcd}}} and {{{{b}}}}");
		System.out.println(elements);
		
		System.out.println(processor.replaceFieldToken("select {}, {} from {} where a = {a}", "code", "name", "table", "123"));
		System.out.println(processor.replaceFieldToken("{a},{b},{c},{d}", param, true));
		System.out.println(processor.replaceFieldToken("{a},{b},{c},{d},qaz", param, true));
		
		System.out.println(processor.parseExpression("否"));
		System.out.println(processor.parseExpression(""));
		System.out.println(processor.parseExpression("否{}"));
		System.out.println(processor.parseExpression("{ddd}"));
		System.out.println(processor.parseExpression("{ddd}否"));
		
		System.out.println(FieldTokenProcessor.searchAvailableVariableToken("a.d+b/c+a"));
		System.out.println(FieldTokenProcessor.searchAvailableVariableToken("a.d+b/c+a", false));
		System.out.println(FieldTokenProcessor.searchNestedVariableToken("a.d+b/c+a"));
		
		processor = new FieldTokenProcessor(':', NO_VARIABLE_SYMBOL);
		System.out.println(processor.searchFieldToken("select * from t where a = :a and b = :b and c = :a"));
		System.out.println(processor.replaceFieldToken(":a,:b,:c,:d", param, true));
		System.out.println(processor.replaceFieldToken(":a,:b,:c,:d,qaz", param, true));
		
		System.out.println(processor.replaceFieldToken(":::a,:b,:c,:d,::", "abc", 123.45, true, new Date()));
		System.out.println(processor.replaceFieldToken(":a,:b,:c,:d,qaz", "abc", 123.45, true, new Date()));
	}
}
