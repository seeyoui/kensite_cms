package com.seeyoui.kensite.common.util;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.util.StringUtils;

public class DateConvertPropertyEditor extends PropertyEditorSupport {
	private SimpleDateFormat datetime_HHmmss_Format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	private SimpleDateFormat datetime_HHmm_Format = new SimpleDateFormat("yyyy-MM-dd HH:mm");  
	private SimpleDateFormat datetime_HH_Format = new SimpleDateFormat("yyyy-MM-dd HH");  
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
  
    public void setAsText(String text) throws IllegalArgumentException {  
        if (StringUtils.hasText(text)) {
            try {  
                if (text.indexOf(":") == -1 && text.length() == 10) {  
                    setValue(this.dateFormat.parse(text));
                }else if (text.indexOf(":") == -1 && text.length() == 13) {  
                    setValue(this.datetime_HH_Format.parse(text));  
                }else if (text.indexOf(":") > 0 && text.length() == 16) {  
                    setValue(this.datetime_HHmm_Format.parse(text));  
                }else if (text.indexOf(":") > 0 && text.length() == 19) {  
                    setValue(this.datetime_HHmmss_Format.parse(text));  
                }else{  
                    throw new IllegalArgumentException("Could not parse date, date format is error ");  
                }  
            } catch (ParseException ex) {  
                IllegalArgumentException iae = new IllegalArgumentException("Could not parse date: " + ex.getMessage());  
                iae.initCause(ex);  
                throw iae;  
            }  
        } else {  
            setValue(null);  
        }  
    }  
}
