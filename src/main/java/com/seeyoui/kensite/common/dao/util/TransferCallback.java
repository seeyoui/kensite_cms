/*
 * LilyDAP平台系统源文件.
 *
 * Copyright 2008 百合软件开发有限公司
 */

package com.seeyoui.kensite.common.dao.util;

/**
 * <code>TransferCallback</code>
 * <p>数据传递回调接口</p>
 *
 * @author 邹学模
 * @date 2008-5-22
 */
public interface TransferCallback {
	/**
	 * 准备属性拷贝回调方法，在复制对象的每个属性前调用。如果回调函数返回值为true，则执行复制这个属性操作，如果返回值为false，不执行复制操作
	 * 
	 * @param src
	 * @param srcFieldName
	 * @param srcFieldValue
	 * @param tgt
	 * @param tgtFieldName
	 * @param tgtFieldValue
	 * @return
	 */
	public boolean readyCopy(Object src, String srcFieldName, Object srcFieldValue, Object tgt, String tgtFieldName, Object tgtFieldValue);
}
