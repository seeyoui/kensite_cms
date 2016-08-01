package com.seeyoui.kensite.common.constants;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * 文件名：StringConstant<br>
 * 
 * 说明：字符串常量类 <br>
 * 
 */
public final class StringConstant {
	private StringConstant(){}
	
	//登陆账号的缓存
	public static final String ACCOUNT_CACHE = "account_cache";
	
	//树形结构的根节点编号
	public static final String ROOT_ID_32 = "00000000000000000000000000000000";
	
	//数据库建模创建表名的前缀
	public static final String TABLE_PREFIX = "BO_";
	
	//附件存放路径
	public static final String UPLOAD_FILE_URL = "upload"+"/";
	
	//附件存放路径
	public static final String LUCENE_INDEX_URL = "/"+"lucene"+"/";
	
	//用户头像存放路径
	public static final String HEAD_ICON_URL = "headIcon"+"/";
	
	//初始化密码
	public static final String INIT_PASSWORD = "123456";
	
	//webSocket
	public static final String WEBSOCKET_USERNAME = "currentUserName";
	
	//字符串表示布尔值
	public static final String TRUE = "T";
	public static final String FALSE = "F";
	
	//字符串表示是&否
	public static final String YES = "Y";
	public static final String NO = "N";
	public static final String HIDDEN = "H";
	public static final String DISABLE = "D";
	
	//jsp表单模板存放路径
	public static final String TEMPLAT_JSP_URL = "/WebContent/WEB-INF/view/templat/jsp/";
	//jsp表单模板后缀
	public static final String TEMPLAT_JSP_SUFFIX = ".jsp";
	
	//性别
	public static final String MALE = "M";
	public static final String FEMALE = "F";

	//在职状态
	public static final String STATE_OUT_OFFICE = "0";
	public static final String STATE_IN_OFFICE = "1";
	public static final String STATE_RETIRE = "2";
	
	//使用状态
	public static final String STATE_DISABLE = "0";
	public static final String STATE_ENABLE = "1";
	
	//关联状态
	public static final String STATE_UNRELATED = "0";
	public static final String STATE_RELATED = "1";
	
	//支付方式
	public static final String STATE_CASH="M";//现金付款
	public static final String STATE_CARD="C";//刷卡付款
	public static final String STATE_NET="N";//网上在线付款
	
	//消息阅读状态
	public static final String READ_SATUS_READ="READ";//已读
	public static final String READ_SATUS_UNREAD="UNREAD";//未读
	
	//WEB门户
	public static final String WEB_ADD = "add";
	public static final String WEB_SET_BACK = "setBack";
	public static final String WEB_SEQ_SQL = "select seq_web_module_id.nextval id from dual";
	
	public static final HashMap<String, String> NULLABLE = new LinkedHashMap<String, String>();
	
	public static final HashMap<String, String> DELETE_FLAG = new LinkedHashMap<String, String>();

	public static final HashMap<String, String> SEX_RADIO = new LinkedHashMap<String, String>();
	
	public static final HashMap<String, String> USER_STATE = new LinkedHashMap<String, String>();
	
	public static final HashMap<String, String> ACCOUNT_STATE = new LinkedHashMap<String, String>();
	
	public static final HashMap<String, String> ACCOUNT_ROLE_STATE = new LinkedHashMap<String, String>();
	
	static {
		NULLABLE.put(TRUE, "是");
		NULLABLE.put(FALSE, "否");
		
		DELETE_FLAG.put(FALSE, "启用");
		DELETE_FLAG.put(TRUE, "禁用");

		SEX_RADIO.put(MALE, "男");
		SEX_RADIO.put(FEMALE, "女");
		
		USER_STATE.put(STATE_IN_OFFICE, "在职");
		USER_STATE.put(STATE_OUT_OFFICE, "离职");
		USER_STATE.put(STATE_RETIRE, "退休");
		
		ACCOUNT_STATE.put(STATE_ENABLE, "启用");
		ACCOUNT_STATE.put(STATE_DISABLE, "禁用");
		
		ACCOUNT_ROLE_STATE.put(STATE_RELATED, "已关联");
		ACCOUNT_ROLE_STATE.put(STATE_UNRELATED, "未关联");
		
	}
	
	/**
	 * 页面获取常量
	 * @see ${fns:getConst('YES')}
	 */
	public static Object getConst(String field) {
		try {
			return StringConstant.class.getField(field).get(null);
		} catch (Exception e) {
			// 异常代表无配置，这里什么也不做
		}
		return null;
	}
}