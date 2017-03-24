package com.seeyoui.kensite.common.util.word;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.seeyoui.kensite.common.util.Encodes;
import com.seeyoui.kensite.common.util.GeneratorUUID;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class ExportWord {

	private Configuration configuration = null;
	private File docFile = null;
	private Template t = null;

	public ExportWord(Map<String, Object> dataMap, String ftl) throws Exception {
		if (dataMap == null) {
			throw new RuntimeException("headerList not null!");
		}
		if (com.seeyoui.kensite.common.util.StringUtils.isBlank(ftl)) {
			throw new RuntimeException("ftl not null or empty!");
		}
		configuration = new Configuration();
		configuration.setDefaultEncoding("utf-8");
		createDoc(dataMap, ftl);
	}

	private void createDoc(Map<?, ?> dataMap, String ftl) {
		configuration.setClassForTemplateLoading(this.getClass(),
				"/template/systable");
		docFile = new File("temp" + GeneratorUUID.getId() + ".doc");
		try {
			t = configuration.getTemplate(ftl);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			Writer w = new OutputStreamWriter(new FileOutputStream(docFile),
					"utf-8");
			t.process(dataMap, w);
			w.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 输出到客户端
	 * 
	 * @param fileName
	 *            输出文件名
	 */
	public void write(HttpServletResponse response, String fileName)
			throws IOException {
		InputStream fin = null;
		ServletOutputStream out = null;
		try {
			// 调用工具类WordGenerator的createDoc方法生成Word文档
			fin = new FileInputStream(docFile);

			response.reset();
			response.setContentType("application/octet-stream; charset=utf-8");
			response.setHeader("Content-Disposition", "attachment; filename="
					+ Encodes.urlEncode(fileName));

			out = response.getOutputStream();
			byte[] buffer = new byte[1024];// 缓冲区
			int bytesToRead = -1;
			// 通过循环将读入的Word文件的内容输出到浏览器中
			while ((bytesToRead = fin.read(buffer)) != -1) {
				out.write(buffer, 0, bytesToRead);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (fin != null)
				fin.close();
			if (out != null)
				out.close();
			if (docFile != null)
				docFile.delete(); // 删除临时文件
		}
	}
}
