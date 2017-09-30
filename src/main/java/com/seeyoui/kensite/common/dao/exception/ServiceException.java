/*
 * package com.ieslab.dap.service.right.exception;
 * class RightAccessException
 * 
 * 创建日期 2005-8-8
 *
 * 开发者 zouxuemo
 *
 * 淄博百合电子有限公司版权所有
 */
package com.seeyoui.kensite.common.dao.exception;

/**
 * 服务异常，作为所有服务定义的异常类的父类
 * 
 * @author zouxuemo
 *
 */
public class ServiceException extends RuntimeException {
	private static final long serialVersionUID = -3950860655222698709L;

	/**
	 * 构造函数
	 * 
	 * @param msg 异常信息
	 */
	public ServiceException(String msg) {
		super(msg);
	}

	/**
	 * 构造函数
	 * 
	 * @param msg 异常信息
	 * @param ex 异常引起的根
	 */
	public ServiceException(String msg, Throwable ex) {
		super(msg, ex);
	}
}
