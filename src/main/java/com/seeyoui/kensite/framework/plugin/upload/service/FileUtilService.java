/*
 * Powered By cuichen
 * Since 2014 - 2015
 */package com.seeyoui.kensite.framework.plugin.upload.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sun.misc.BASE64Decoder;

import com.seeyoui.kensite.common.base.service.BaseService;
import com.seeyoui.kensite.common.constants.StringConstant;
import com.seeyoui.kensite.common.exception.CRUDException;
import com.seeyoui.kensite.common.util.SessionUtil;
import com.seeyoui.kensite.framework.plugin.upload.domain.Uploadfile;
import com.seeyoui.kensite.framework.plugin.upload.persistence.UploadfileMapper;
import com.seeyoui.kensite.framework.system.domain.SysUser;
import com.seeyoui.kensite.framework.system.persistence.SysUserMapper;
import com.seeyoui.kensite.framework.system.util.UserUtils;

/**
 * @author cuichen
 * @version 1.0
 * @since 1.0
 */
@Service
public class FileUtilService extends BaseService {

	@Autowired
	private SysUserMapper sysUserMapper;

	/**
	 * 数据新增
	 * 
	 * @param uploadfile
	 * @throws CRUDException
	 */
	public Object headIcon(HttpServletRequest request) throws CRUDException {
		String ctxPath = request.getSession().getServletContext()
				.getRealPath("/");
		String userId = UserUtils.getUser().getId();
		if (!ctxPath.endsWith(File.separator)) {
			ctxPath = ctxPath + File.separator;
		}
		ctxPath = ctxPath + StringConstant.UPLOAD_FILE_URL
				+ StringConstant.HEAD_ICON_URL;
		if (!ctxPath.endsWith(File.separator)) {
			ctxPath = ctxPath + File.separator;
		}
		String fileName = userId + ".png";
		// 参数序列化
		String image = request.getParameter("image"); // 拿到字符串格式的图片
		String PicName = fileName;

		// 只允许image
		String header = "data:image";
		String[] imageArr = image.split(",");
		if (imageArr[0].contains(header)) {// 是img的
			// 去掉头部
			image = imageArr[1];
			// image = image.substring(header.length());
			// 写入磁盘
			BASE64Decoder decoder = new BASE64Decoder();
			try {
				byte[] decodedBytes = decoder.decodeBuffer(image); // 将字符串格式的image转为二进制流（biye[])的decodedBytes
				String imgFilePath = ctxPath + PicName; // 指定图片要存放的位置
				File targetFile = new File(ctxPath);
				if (!targetFile.exists()) {
					targetFile.mkdirs();
				}
				FileOutputStream out = new FileOutputStream(imgFilePath); // 新建一个文件输出器，并为它指定输出位置imgFilePath
				out.write(decodedBytes); // 利用文件输出器将二进制格式decodedBytes输出
				out.close(); // 关闭文件输出器
				Map<String, String> result = new HashMap<String, String>();
				String fileUrl = StringConstant.UPLOAD_FILE_URL
						+ StringConstant.HEAD_ICON_URL + fileName;
				SysUser sysUser = sysUserMapper.findOne(userId);
				sysUser.setId(userId);
				sysUser.setHeadIcon(fileName);
				sysUserMapper.update(sysUser);
				UserUtils.clearCache(sysUser);
				SessionUtil.setSession("currentUser", sysUser);
				result.put("fileUrl", fileUrl);
				return result;
			} catch (Exception e) {
				System.err.println("上传文件失败！");
			}
		}
		return null;
	}
}