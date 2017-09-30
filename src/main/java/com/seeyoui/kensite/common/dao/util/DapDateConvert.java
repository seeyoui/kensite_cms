package com.seeyoui.kensite.common.dao.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.beanutils.converters.DateTimeConverter;

public final class DapDateConvert extends DateTimeConverter {
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private String ZERO_TIME = "00:00:00";
	
	public DapDateConvert() {
		super();
		setUseLocaleFormat(true);
		
		setPatterns(new String[] { "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM-dd HH", "yyyy-MM-dd", "yyyy.MM.dd", "yyyy年MM月dd日", "EEE MMM dd HH:mm:ss 'CST' yyyy", "d MMM yyyy HH:mm:ss 'GMT'" });
	}
	
	@SuppressWarnings("unchecked")
	protected Class getDefaultType() {
        return Date.class;
	}

	@Override
	protected String convertToString(Object value) {
        Date date = null;
        if (value instanceof Date) {
            date = (Date)value;
        } else if (value instanceof Calendar) {
            date = ((Calendar)value).getTime();
//        } else if (value instanceof Long) {
//            date = new Date(((Long)value).longValue());
        }

        String result = null;
        if (date != null) {
        	result = dateFormat.format(date);
        	if (result.lastIndexOf(ZERO_TIME) > 0)
        		result = result.substring(0, result.indexOf(' '));
        } else {
            result = value.toString();
        }
    	
    	return result;
	}
}
