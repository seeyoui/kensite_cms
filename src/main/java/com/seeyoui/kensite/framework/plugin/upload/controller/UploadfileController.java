/*
 * Powered By cuichen
 * Since 2014 - 2015
 */
package com.seeyoui.kensite.framework.plugin.upload.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.seeyoui.kensite.common.base.controller.BaseController;
import com.seeyoui.kensite.common.base.domain.EasyUIDataGrid;
import com.seeyoui.kensite.common.constants.StringConstant;
import com.seeyoui.kensite.common.util.CacheUtils;
import com.seeyoui.kensite.common.util.FileUtils;
import com.seeyoui.kensite.common.util.MD5;
import com.seeyoui.kensite.common.util.RequestResponseUtil;
import com.seeyoui.kensite.common.util.StringUtils;
import com.seeyoui.kensite.framework.plugin.upload.domain.Uploadfile;
import com.seeyoui.kensite.framework.plugin.upload.service.UploadfileService;

/**
 * @author cuichen
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping(value = "sys/uploadfile")
public class UploadfileController extends BaseController {

	@Autowired
	private UploadfileService uploadfileService;

	/**
	 * 展示列表页面
	 * 
	 * @param modelMap
	 * @param module
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/showPageList")
	public ModelAndView showUploadfilePageList(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap) throws Exception {
		return new ModelAndView("framework/plugin/upload/uploadfile", modelMap);
	}

	@RequestMapping(value = "/testUpload")
	public ModelAndView testUpload(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap) throws Exception {
		return new ModelAndView("framework/plugin/upload/uploadify", modelMap);
	}

	/**
	 * 获取列表展示数据
	 * 
	 * @param modelMap
	 * @param uploadfile
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getListData", method = RequestMethod.POST)
	@ResponseBody
	public String getListData(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, Uploadfile uploadfile) throws Exception {
		List<Uploadfile> uploadfileList = uploadfileService
				.findUploadfileList(uploadfile);
		EasyUIDataGrid eudg = uploadfileService
				.findUploadfileListTotal(uploadfile);
		eudg.setRows(uploadfileList);
		JSONObject jsonObj = JSONObject.fromObject(eudg);
		RequestResponseUtil.putResponseStr(session, response, request, jsonObj);
		return null;
	}

	/**
	 * 保存新增的数据
	 * 
	 * @param modelMap
	 * @param uploadfile
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public Object uploadfile(HttpSession session, HttpServletResponse response,
			HttpServletRequest request, ModelMap modelMap, Uploadfile uploadfile)
			throws Exception {
		Uploadfile uf = uploadfileService.upload(uploadfile, request);
		// System.out.println(uf.getUrl());
		// JSONObject jsonObject = JSONObject.fromObject(uf);
		// System.out.println(jsonObject.toString());
		// RequestResponseUtil.putResponseStr(session, response, request,
		// jsonObject.toString());
		// return null;
		Map<String, String> result = new HashMap<String, String>();
		result.put("url", uf.getUrl());
		result.put("realurl", uf.getRealurl());
		result.put("realname", uf.getRealname());
		JSONObject jsonObject = JSONObject.fromObject(result);
		RequestResponseUtil.putResponseStr(session, response, request,
				jsonObject.toString());
		return null;
	}

	/**
	 * 保存新增的数据
	 * 
	 * @param modelMap
	 * @param uploadfile
	 * @param file
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	@ResponseBody
	public Object uploadFile(HttpSession session, HttpServletResponse response,
			HttpServletRequest request, ModelMap modelMap, MultipartFile file,
			Uploadfile uploadfile) throws Exception {
		Uploadfile uf = uploadfileService.uploadFile(file, uploadfile, request);
		return uf;
	}

	/**
	 * 保存新增的数据
	 * 
	 * @param modelMap
	 * @param uploadfile
	 * @param file
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/uploadChunk", method = RequestMethod.POST)
	@ResponseBody
	public Object uploadChunk(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, Uploadfile uploadfile, MultipartFile file)
			throws Exception {
		Uploadfile uf = uploadfileService
				.uploadChunk(file, uploadfile, request);
		return uf;
	}

	/**
	 * 保存新增的数据
	 * 
	 * @param modelMap
	 * @param uploadfile
	 * @param file
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/uploadReal", method = RequestMethod.POST)
	@ResponseBody
	public Object uploadReal(HttpSession session,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap modelMap, Uploadfile uploadfile, MultipartFile file)
			throws Exception {
		Uploadfile uf = uploadfileService
				.uploadReal(file, uploadfile, request);
		return uf;
	}

	/**
	 * 检查文件区块
	 * 
	 * @param modelMap
	 * @param uploadfile
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/checkChunk", method = RequestMethod.POST)
	@ResponseBody
	public Object checkChunk(HttpSession session, HttpServletResponse response,
			HttpServletRequest request, ModelMap modelMap, String type,
			String url, String fileName, String fileSize, String fileExt,
			String chunk, String chunkSize, String chunks) throws Exception {
		String ctxPath = request.getSession().getServletContext()
				.getRealPath("/");
		uploadfileService.formatFilePath(ctxPath);
		ctxPath = ctxPath + StringConstant.UPLOAD_FILE_URL + url + "\\";
		uploadfileService.formatFilePath(ctxPath);
		String realName = MD5.md5(fileName + fileSize);
		String cacheName = MD5.md5(fileName);
		String realFileName = ctxPath + realName + "." + fileExt;
		// 是否文件已上传
		if (StringUtils.isNoneBlank(type) && "init".equals(type)) {
			File file = new File(realFileName);
			if (file.exists()) {
				RequestResponseUtil.putResponseStr(session, response, request,
						StringConstant.TRUE);
				return null;
			} else {
				FileUtils.createDirectory(ctxPath + realName);
				RequestResponseUtil.putResponseStr(session, response, request,
						StringConstant.FALSE);
				return null;
			}
		}
		// 是否区块已上传
		if (StringUtils.isNoneBlank(type) && "block".equals(type)) {
			// 用区块的下标当作文件名
			File file = new File(ctxPath + realName + File.separator + chunk);
			if (file.exists()) {// 文件存在
				if (file.length() == Long.parseLong(chunkSize)) {// 文件大小一致
					RequestResponseUtil.putResponseStr(session, response,
							request, StringConstant.TRUE);
					return null;
				} else {// 文件大小不一致
					file.delete();// 删除文件重新上传
					CacheUtils.put(cacheName, chunk);
					CacheUtils.put(cacheName + "size", fileSize);
					RequestResponseUtil.putResponseStr(session, response,
							request, StringConstant.FALSE);
					return null;
				}
			} else {// 文件不存在
				CacheUtils.put(cacheName, chunk);
				CacheUtils.put(cacheName + "size", fileSize);
				RequestResponseUtil.putResponseStr(session, response, request,
						StringConstant.FALSE);
				return null;
			}
		}
		// 合并区块
		if (StringUtils.isNoneBlank(type) && "merge".equals(type)) {
			Map<String, String> result = new HashMap<String, String>();
			System.out.println(ctxPath + realName);
			int chunksCount = Integer.parseInt(chunks);
			int byteLen = 1024*1024;
			for (int i = 0; i < chunksCount; i++) {
				File file = new File(ctxPath + realName + "/" + i);
				int len = -1;
				if (file.exists()) {
					byte[] tempbytes = new byte[byteLen];
					InputStream in = new FileInputStream(ctxPath + realName + "/" + i);
					File f = new File(realFileName);
					while ((len = in.read(tempbytes)) != -1) {
						if(len != byteLen) {
							byte[] tb = new byte[len];
							for(int j=0; j<len; j++) {
								tb[j] = tempbytes[j];
							}
							org.apache.commons.io.FileUtils.writeByteArrayToFile(f, tb, true);
						} else {
							org.apache.commons.io.FileUtils.writeByteArrayToFile(f, tempbytes, true);
						}
					}
					in.close();
					System.gc();
				} else {
					result.put("success", StringConstant.FALSE);
					result.put("message", "文件合并失败");
					JSONObject jsonObject = JSONObject.fromObject(result);
					RequestResponseUtil.putResponseStr(session, response,
							request, jsonObject.toString());
					return null;
				}
			}
			long fl = new File(realFileName).length();
			long flz = Long.parseLong(fileSize);
			if(fl == flz) {
				System.out.println("删除区块文件");
				try {
					for (int i = 0; i < chunksCount; i++) {
						File file = new File(ctxPath + realName + "/" + i);
						if (file.exists()) {
							file.delete();
						}
					}
					File file = new File(ctxPath + realName);
					if (file.exists()) {
						file.delete();
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				result.put("success", StringConstant.TRUE);
				result.put("url", url);
				result.put("realurl", "/");
				result.put("realname", "成功就好了");
				JSONObject jsonObject = JSONObject.fromObject(result);
				RequestResponseUtil.putResponseStr(session, response, request,
						jsonObject.toString());
				return null;
			} else {
				result.put("success", StringConstant.FALSE);
				result.put("message", "文件合并失败");
				JSONObject jsonObject = JSONObject.fromObject(result);
				RequestResponseUtil.putResponseStr(session, response,
						request, jsonObject.toString());
				return null;
			}
			
		}
		return null;
	}

	@RequestMapping(value = "/downloadChunk", method = RequestMethod.GET)
	public void downloadChunk(HttpServletRequest request,
			HttpServletResponse response, String url, String filmName) {
		String ctxPath = "";
		ctxPath = ctxPath + StringConstant.UPLOAD_FILE_URL + url;// "video\\video"
		BufferedInputStream bis = null;
		try {
			String path = ctxPath + "\\" + filmName;
			File file = new File(path);
			if (file.exists()) {
				long p = 0L;
				long toLength = 0L;
				long contentLength = 0L;
				int rangeSwitch = 0; // 0,从头开始的全文下载；1,从某字节开始的下载（bytes=27000-）；2,从某字节开始到某字节结束的下载（bytes=27000-39000）
				long fileLength;
				String rangBytes = "";
				fileLength = file.length();
				// get file content
				InputStream ins = new FileInputStream(file);
				bis = new BufferedInputStream(ins);
				// tell the client to allow accept-ranges
				response.reset();
				response.setHeader("Accept-Ranges", "bytes");
				// client requests a file block download start byte
				String range = request.getHeader("Range");
				if (range != null && range.trim().length() > 0
						&& !"null".equals(range)) {
					response.setStatus(javax.servlet.http.HttpServletResponse.SC_PARTIAL_CONTENT);
					rangBytes = range.replaceAll("bytes=", "");
					if (rangBytes.endsWith("-")) { // bytes=270000-
						rangeSwitch = 1;
						p = Long.parseLong(rangBytes.substring(0,
								rangBytes.indexOf("-")));
						contentLength = fileLength - p; // 客户端请求的是270000之后的字节（包括bytes下标索引为270000的字节）
					} else { // bytes=270000-320000
						rangeSwitch = 2;
						String temp1 = rangBytes.substring(0,
								rangBytes.indexOf("-"));
						String temp2 = rangBytes.substring(
								rangBytes.indexOf("-") + 1, rangBytes.length());
						p = Long.parseLong(temp1);
						toLength = Long.parseLong(temp2);
						contentLength = toLength - p + 1; // 客户端请求的是//
															// 270000-320000//
															// 之间的字节
					}
				} else {
					contentLength = fileLength;
				}
				// 如果设设置了Content-Length，则客户端会自动进行多线程下载。如果不希望支持多线程，则不要设置这个参数。
				// Content-Length: [文件的总大小] - [客户端请求的下载的文件块的开始字节]
				response.setHeader("Content-Length",
						new Long(contentLength).toString());
				// 断点开始
				// 响应的格式是:
				// Content-Range: bytes [文件块的开始字节]-[文件的总大小 - 1]/[文件的总大小]
				if (rangeSwitch == 1) {
					String contentRange = new StringBuffer("bytes ")
							.append(new Long(p).toString()).append("-")
							.append(new Long(fileLength - 1).toString())
							.append("/")
							.append(new Long(fileLength).toString()).toString();
					response.setHeader("Content-Range", contentRange);
					bis.skip(p);
				} else if (rangeSwitch == 2) {
					String contentRange = range.replace("=", " ") + "/"
							+ new Long(fileLength).toString();
					response.setHeader("Content-Range", contentRange);
					bis.skip(p);
				} else {
					String contentRange = new StringBuffer("bytes ")
							.append("0-").append(fileLength - 1).append("/")
							.append(fileLength).toString();
					response.setHeader("Content-Range", contentRange);
				}
				String fileName = file.getName();
				response.setContentType("application/octet-stream");
				response.addHeader("Content-Disposition",
						"attachment;filename=" + fileName);
				OutputStream out = response.getOutputStream();
				int n = 0;
				long readLength = 0;
				int bsize = 1024 * 10;
				byte[] bytes = new byte[bsize];
				if (rangeSwitch == 2) {
					// 针对 bytes=27000-39000 的请求，从27000开始写数据
					while (readLength <= contentLength - bsize) {
						n = bis.read(bytes);
						readLength += n;
						out.write(bytes, 0, n);
						Thread.sleep(100);
					}
					if (readLength <= contentLength) {
						n = bis.read(bytes, 0,
								(int) (contentLength - readLength));
						out.write(bytes, 0, n);
					}
				} else {
					while ((n = bis.read(bytes)) != -1) {
						out.write(bytes, 0, n);
						Thread.sleep(100);
					}
				}
				out.flush();
				out.close();
				bis.close();
			} else {
				if (logger.isDebugEnabled()) {
					logger.debug("Error: file " + path + " not found.");
				}
			}
		} catch (IOException ie) {
			// 忽略 ClientAbortException 之类的异常
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 * 删除数据库
	 * 
	 * @param modelMap
	 * @param uploadfileId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public String delete(HttpSession session, HttpServletResponse response,
			HttpServletRequest request, ModelMap modelMap, String delDataId)
			throws Exception {
		List<String> listId = Arrays.asList(delDataId.split(","));
		uploadfileService.deleteUploadfile(listId);
		RequestResponseUtil.putResponseStr(session, response, request,
				StringConstant.TRUE);
		return null;
	}
}