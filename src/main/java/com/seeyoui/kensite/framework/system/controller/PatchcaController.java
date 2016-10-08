package com.seeyoui.kensite.framework.system.controller;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;

/**
 * 验证码
 */

@Controller
@RequestMapping(value = "login")
public class PatchcaController {

	@Autowired
	private Producer captchaProducer = null;

	/**
	 * 生成验证码图片
	 * 
	 * @param session
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/patchca")
	public ModelAndView patchca(HttpSession session,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap modelMap) throws Exception {
		response.setDateHeader("Expires", 0);
		// Set standard HTTP/1.1 no-cache headers.
		response.setHeader("Cache-Control",
				"no-store, no-cache, must-revalidate");
		// Set IE extended HTTP/1.1 no-cache headers (use addHeader).
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		// Set standard HTTP/1.0 no-cache header.
		response.setHeader("Pragma", "no-cache");
		// return a jpeg
		response.setContentType("image/jpeg");
		// create the text for the image
		String capText = captchaProducer.createText();
		// store the text in the session
		session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
		// create the image with the text
		BufferedImage bi = captchaProducer.createImage(capText);
		ServletOutputStream out = response.getOutputStream();
		// write the data out
		ImageIO.write(bi, "jpg", out);
		try {
			out.flush();
		} finally {
			out.close();
		}
		return null;
	}
	
	@RequestMapping(value = "/test")
	@ResponseBody
	public String test(HttpSession session,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap modelMap) {
		String code = (String)session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
		System.out.println(code);
		return code;
	}
}
