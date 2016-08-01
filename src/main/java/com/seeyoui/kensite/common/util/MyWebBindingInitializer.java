package com.seeyoui.kensite.common.util;

import java.util.Date;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

/**
 * 程序名称： MyWebBindingInitializer.java
 * 程序说明： 页面数据绑定的初始化适配器（用于日期格式的转换）
 * @version： Ver 0.1
 */
public class MyWebBindingInitializer implements WebBindingInitializer {  
	  
    @Override  
    public void initBinder(WebDataBinder binder, WebRequest request) {  
    	/*
    	 * 格式化日期，并将字符串类型转化成日期类型（对应实体类中的date类型属性）
    	 */
//    	1. 使用spring自带的CustomDateEditor  
//      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
//      binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));  
          
//      2. 自定义的PropertyEditorSupport  
        binder.registerCustomEditor(Date.class, new DateConvertPropertyEditor());  
    }  
  
}  
