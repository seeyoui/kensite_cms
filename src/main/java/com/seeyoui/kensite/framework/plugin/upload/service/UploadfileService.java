/*
 * Powered By cuichen
 * Since 2014 - 2015
 */package com.seeyoui.kensite.framework.plugin.upload.service;  
 
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.seeyoui.kensite.common.base.domain.EasyUIDataGrid;
import com.seeyoui.kensite.common.base.service.BaseService;
import com.seeyoui.kensite.common.constants.StringConstant;
import com.seeyoui.kensite.common.exception.CRUDException;
import com.seeyoui.kensite.common.util.CacheUtils;
import com.seeyoui.kensite.common.util.FileUtils;
import com.seeyoui.kensite.common.util.GeneratorUUID;
import com.seeyoui.kensite.common.util.MD5;
import com.seeyoui.kensite.framework.plugin.upload.domain.Uploadfile;
import com.seeyoui.kensite.framework.plugin.upload.persistence.UploadfileMapper;
import com.seeyoui.kensite.video.constants.VideoConstants;

/**
 * @author cuichen
 * @version 1.0
 * @since 1.0
 */
@Service
public class UploadfileService extends BaseService {
	
	@Autowired
	private UploadfileMapper uploadfileMapper;

	/**
	 * 根据ID查询单条数据
	 * @param id
	 * @return
	 * @throws CRUDException
	 */
	public Uploadfile findUploadfileById(String id) throws CRUDException{
		return uploadfileMapper.findUploadfileById(id);
	}
	
	/**
	 * 查询数据集合
	 * @param uploadfile
	 * @return
	 * @throws CRUDException
	 */
	public List<Uploadfile> findUploadfileList(Uploadfile uploadfile) throws CRUDException {
		return uploadfileMapper.findUploadfileList(uploadfile);
	}
	
	/**
	 * 查询数据总数
	 * @param userinfo
	 * @return
	 * @throws CRUDException
	 */
	public EasyUIDataGrid findUploadfileListTotal(Uploadfile uploadfile) throws CRUDException {
		return uploadfileMapper.findUploadfileListTotal(uploadfile);
	}
	
	/**
	 * 数据新增
	 * @param uploadfile
	 * @throws CRUDException
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	public Uploadfile upload(Uploadfile uploadfile, HttpServletRequest request) throws CRUDException {
		String ctxPath = request.getSession().getServletContext().getRealPath("/"); 
//		MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
//		MultipartHttpServletRequest multipartRequest = resolver.resolveMultipart(request);
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		if (!ctxPath.endsWith(File.separator)) {
			ctxPath = ctxPath + File.separator;
		}
		FileUtils.createDirectory(ctxPath);
		String url = uploadfile.getUrl();
		if(url==null || "".equals(url)) {
			url = "temp";
		}
		ctxPath = ctxPath + StringConstant.UPLOAD_FILE_URL + url;
		if (!ctxPath.endsWith(File.separator)) {
			ctxPath = ctxPath + File.separator;
		}
		FileUtils.createDirectory(ctxPath);
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {    
            // 上传文件名 
			Uploadfile uploadFile = new Uploadfile();
            MultipartFile mf = entity.getValue();
            String fileName = mf.getOriginalFilename();
//            String fName = fileName.indexOf(".") != -1 ? fileName.substring(0, fileName.lastIndexOf(".")) : fileName;
            String suffix = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf("."), fileName.length()) : null;
            String UUID = GeneratorUUID.getId();
            String newFileName = UUID + suffix;// 构成新文件名。
            File realFile = new File(ctxPath + newFileName);    
            try {
            	uploadFile.setId(UUID);
                uploadFile.setViewname(fileName);
                uploadFile.setRealname(newFileName);
                String fileUrl = StringConstant.UPLOAD_FILE_URL + (url==null?"":url);
                uploadFile.setUrl(fileUrl.replaceAll("\\\\", "/"));
                uploadFile.setRealurl(ctxPath.replaceAll("\\\\", "/"));
                uploadFile.setSuffix(suffix);
                uploadFile.setFilesize(mf.getSize()+"");
            	if(!(url==null?"temp":url).startsWith("temp")) {
            		uploadfile.preInsert();
            		uploadfileMapper.saveUploadfile(uploadFile);
            	}
                FileCopyUtils.copy(mf.getBytes(), realFile);
                return uploadFile;
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }
		}
        return null;
		
	}
	
	/**
	 * 数据新增
	 * @param uploadfile
	 * @throws CRUDException
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	public Uploadfile uploadFile(MultipartFile file, Uploadfile uploadfile, HttpServletRequest request) throws CRUDException {
		String ctxPath = request.getSession().getServletContext().getRealPath("/"); 
		if (!ctxPath.endsWith(File.separator)) {
			ctxPath = ctxPath + File.separator;
		}
		FileUtils.createDirectory(ctxPath);
		String url = uploadfile.getUrl();
		if(url==null || "".equals(url)) {
			url = "temp";
		}
		ctxPath = ctxPath + StringConstant.UPLOAD_FILE_URL + url;
		if (!ctxPath.endsWith(File.separator)) {
			ctxPath = ctxPath + File.separator;
		}
		FileUtils.createDirectory(ctxPath);
		try {
			if (file != null) {
				String fileName = file.getOriginalFilename();
				String suffix = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf("."), fileName.length()) : null;
				String UUID = GeneratorUUID.getId();
				String newFileName = UUID + suffix;// 构成新文件名。
				File realFile = new File(ctxPath + newFileName);    
				Uploadfile uploadFile = new Uploadfile();
	            try {
	            	uploadFile.setId(UUID);
	                uploadFile.setViewname(fileName);
	                uploadFile.setRealname(newFileName);
	                String fileUrl = StringConstant.UPLOAD_FILE_URL + (url==null?"":url);
	                uploadFile.setUrl(fileUrl.replaceAll("\\\\", "/"));
	                uploadFile.setRealurl(ctxPath.replaceAll("\\\\", "/"));
	                uploadFile.setSuffix(suffix);
	                uploadFile.setFilesize(file.getSize()+"");
	            	if(!(url==null?"temp":url).startsWith("temp")) {
	            		uploadfile.preInsert();
	            		uploadfileMapper.saveUploadfile(uploadFile);
	            	}
					file.transferTo(realFile);
	                return uploadFile;
		        } catch (IOException e) {  
		            e.printStackTrace();  
		        }
			}
		} catch (Exception e) {
		}
        return null;
	}
	
	/**
	 * 数据新增
	 * @param uploadfile
	 * @throws CRUDException
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	public Uploadfile uploadChunk(MultipartFile file, Uploadfile uploadfile, HttpServletRequest request) throws CRUDException {
		String ctxPath = request.getSession().getServletContext().getRealPath("/"); 
		formatFilePath(ctxPath);
		ctxPath = ctxPath + StringConstant.UPLOAD_FILE_URL + uploadfile.getUrl() + "\\";
		formatFilePath(ctxPath);
		String fileName = file.getOriginalFilename();
		String cacheName = MD5.md5(fileName);
		String chunk = CacheUtils.get(cacheName).toString();
		String fileSize = CacheUtils.get(cacheName+"size").toString();
		String realName = MD5.md5(fileName+fileSize);
		//用区块的下标当作文件名
		String filePath = ctxPath + realName + File.separator + chunk;
		try {
			if (file != null) {
				File f = new File(filePath);
				file.transferTo(f);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
        return null;
	}
	
	/**
	 * 数据删除
	 * @param listId
	 * @throws CRUDException
	 */
	public void deleteUploadfile(List<String> listId) throws CRUDException {
		for(String id : listId) {
			Uploadfile uploadfile = uploadfileMapper.findUploadfileById(id);
			FileUtils.deleteFile(uploadfile.getRealurl()+uploadfile.getRealname());
			uploadfileMapper.deleteUploadfile(listId);
		}
	}
	
	public String formatFilePath(String path) {
		path = path.replaceAll("\\\\", "/");
		if (!path.endsWith("/") && !path.endsWith("\\\\")) {
			path = path + File.separator;
		}
		return path;
	}
}