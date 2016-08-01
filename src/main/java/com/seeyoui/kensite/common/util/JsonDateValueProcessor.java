package com.seeyoui.kensite.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

/**
 * 类描述 :JSON对于日期的格式化
 * @author Administrator
 */
public class JsonDateValueProcessor implements JsonValueProcessor {

    private String datePattern = "yyyy-MM-dd HH:mm:ss";
    private SimpleDateFormat sdf;
    private Calendar calendar = Calendar.getInstance();
    
    public JsonDateValueProcessor() {
        super();
    }

    public JsonDateValueProcessor(String format) {
        try {
			sdf = new SimpleDateFormat(format);
		} catch (Exception e) {
			sdf = new SimpleDateFormat(datePattern);
		}
    }

    public Object processArrayValue(Object value, JsonConfig jsonConfig) {
        return process(value);
    }

    public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
        return process(value);
    }

    private Object process(Object value) {
    	 try {
             if (value instanceof Date) {
             	Date d = (Date) value;
             	calendar.setTime(d);
             	if(calendar.get(Calendar.YEAR) == 1970){
             		//则该Date类型为时间类型，没有时间类型
             		return sdf.format(d);
             	}else{
             		//日期时间类型
             		return sdf.format(d);
             	}
                 
             }
             return value == null ? "" : value.toString();
         } catch (Exception e) {
             return "";
         }
    }

    public String getDatePattern() {
        return datePattern;
    }

    public void setDatePattern(String pDatePattern) {
        datePattern = pDatePattern;
    }

}
