package com.seeyoui.kensite.common.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.ui.ModelMap;

public class RequestResponseUtil {
	
	/**
	 * 根据请求获得客户端ip
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("http_client_ip");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		// 如果是多级代理，那么取第一个ip为客户ip
		if (ip != null && ip.indexOf(",") != -1) {
			ip = ip.substring(ip.lastIndexOf(",") + 1, ip.length()).trim();
		}
		return ip;
	}
	/**
	 * 获取请求参数
	 * @param session
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static JSONObject getRequestStr(HttpSession session, HttpServletResponse response, HttpServletRequest request) throws Exception {
		request.setCharacterEncoding("utf-8");
 		response.setCharacterEncoding("UTF-8");
 		response.setContentType("text/html;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache"); 
        
        InputStream is = request.getInputStream();
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		int len = 0;
		byte[] buffer = new byte[1024];
		while ((len = is.read(buffer)) != -1) {
			byteArrayOutputStream.write(buffer, 0, len);
		}
		is.close();
		byteArrayOutputStream.close();
		byte[] resultByte = byteArrayOutputStream.toByteArray();
		String tempStr = new String(resultByte);
		tempStr = URLDecoder.decode(tempStr, "utf-8");
		JSONObject jsonObject = JSONObject.fromObject(tempStr);
		return jsonObject;
	}
	
	/**
	 * 通用返回字符串
	 * @param session
	 * @param response
	 * @param request
	 * @param responseObj response写回的对象
	 * @throws Exception
	 */
	public static void putResponseStr(HttpSession session, HttpServletResponse response, HttpServletRequest request, Object responseObj) {
		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=utf-8");
			response.setHeader("Cache-Control", "no-cache"); 
			PrintWriter out = response.getWriter();
			out.write(responseObj.toString());
			out.flush();
			out.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;
	}
	
	/**
	 * 通用返回字符串
	 * @param session
	 * @param response
	 * @param request
	 * @param responseObj response写回的对象
	 * @throws Exception
	 */
	public static void putResponseStr(HttpSession session, HttpServletResponse response, HttpServletRequest request, ModelMap model, String success) {
		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=utf-8");
			response.setHeader("Cache-Control", "no-cache"); 
			PrintWriter out = response.getWriter();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("message", model.get("message")==null?"":model.get("message"));
			map.put("success", success);
			out.write(JSONObject.fromObject(map).toString());
			out.flush();
			out.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;
	}
	/**
	 * 返回Map集合
	 * @param session
	 * @param response
	 * @param request
	 * @param map
	 * @throws Exception
	 */
	public static void putResponseMap(HttpSession session, HttpServletResponse response, HttpServletRequest request, HashMap map)throws Exception{
		JSONObject result=JSONObject.fromObject(map);
		putResponseStr(session, response, request, result);
	}
	/**
	 * 返回List集合
	 * @param session
	 * @param response
	 * @param request
	 * @param list
	 * @throws Exception
	 */
	public static void putResponseList(HttpSession session, HttpServletResponse response, HttpServletRequest request,List list)throws Exception{
		JSONArray result=JSONArray.fromObject(list);
		putResponseStr(session, response, request, result);
	}
}
