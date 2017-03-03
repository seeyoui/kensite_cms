package com.seeyoui.kensite.framework.cms.filer.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class FilerUtils {
	private static ObjectMapper objectMapper = null;

	private static int seq = 1;
	private static Map<String, Integer> map = new HashMap<String, Integer>();
	private static List<Map<String, Object>> folderList = new ArrayList<Map<String, Object>>();
	private static List<Map<String, Object>> fileList = new ArrayList<Map<String, Object>>();
	
	public static Map<String, List<Map<String, Object>>> getFolderInfo(String basePath, String alowPath) {
		basePath = basePath.replaceAll("\\\\", "/");
		alowPath = alowPath.replaceAll("\\\\", "/");
		String path = basePath + alowPath;
		seq = 1;
		map = new HashMap<String, Integer>();
		folderList = new ArrayList<Map<String, Object>>();
		fileList = new ArrayList<Map<String, Object>>();
		Map<String, List<Map<String, Object>>> m = new HashMap<String, List<Map<String,Object>>>();
		map.put(path, seq);
		File file = new File(path);
		traverseFolder(basePath, file);
		m.put("folderList", folderList);
		m.put("fileList", fileList);
		return m;
	}

	public static void traverseFolder(String basePath, File file) {
		if (file.exists()) {
			File[] files = file.listFiles();
			if (files.length == 0) {
				return;
			} else {
				for (File f : files) {
					if (f.isDirectory()) {
						int parentId = getId(f.getParent());
						Map<String, Object> m = new HashMap<String, Object>();
						int childId = getId(f.getAbsolutePath());
						m.put("id", childId);
						m.put("pId", parentId);
						m.put("name", f.getName());
						m.put("path", f.getAbsolutePath().replaceAll("\\\\", "/").replaceAll(basePath, ""));
						m.put("isParent", true);
						m.put("size", 0);
						folderList.add(m);
						fileList.add(m);
						traverseFolder(basePath, f);
					} else {
						int parentId = getId(f.getParent());
						Map<String, Object> m = new HashMap<String, Object>();
						int childId = getId(f.getAbsolutePath());
						m.put("id", childId);
						m.put("pId", parentId);
						m.put("name", f.getName());
						m.put("path", f.getAbsolutePath().replaceAll("\\\\", "/").replaceAll(basePath, ""));
						m.put("size", f.length());
						fileList.add(m);
					}
				}
			}
		} else {
		}
	}

	public static int getId(String path) {
		Integer sequence = map.get(path);
		if (sequence == null) {
			sequence = seq++;
			map.put(path, sequence);
		}
		return sequence;
	}
}
