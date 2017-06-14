package test.seeyoui.kensite.word;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import test.seeyoui.kensite.word.util.WordExport;

public class Export {
	public static void main(String[] args) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("tableName", "SYS_USER");
		dataMap.put("tableComments", "系统用户表");
		List<Map<String, Object>> columnList = new ArrayList<Map<String, Object>>();
		Map<String, Object> col1 = new HashMap<String, Object>();
		col1.put("columnName", "ID");
		col1.put("dataType", "char(32)");
		col1.put("nullable", "N");
		col1.put("tableComments", "主键");
		columnList.add(col1);
		Map<String, Object> col2 = new HashMap<String, Object>();
		col2.put("columnName", "USER_NAME");
		col2.put("dataType", "varchar(100)");
		col2.put("nullable", "Y");
		col2.put("tableComments", "帐号");
		columnList.add(col2);
		dataMap.put("columnList", columnList);

		WordExport mdoc = new WordExport();
		try {
			mdoc.createDoc(dataMap, "table.ftl", "D:/outFile.doc");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
