package com.seeyoui.kensite.common.dao.simple;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import com.seeyoui.kensite.common.dao.Dao;

@Service("smartDaoFactory")
public class SmartDaoFactory implements ApplicationContextAware {
	private static String DEFAULT_DATASOURCE_NAME = "dataSource";
	
	private ApplicationContext applicationContext = null;
	
	private Map<String, SmartDao> smartDaoMap = new HashMap<String, SmartDao>();
	private SmartDao defaultSmartDao = null;

	@PostConstruct
	public void init() {
		String[] dataSourceNames = applicationContext.getBeanNamesForType(DataSource.class);
		for (String dataSourceName : dataSourceNames) {
			DataSource dataSource = (DataSource) applicationContext.getBean(dataSourceName);
			SmartDao smartDao = new SmartDao(dataSource);
		
			smartDaoMap.put(dataSourceName, smartDao);
			
			if (DEFAULT_DATASOURCE_NAME.equals(dataSourceName))
				defaultSmartDao = smartDao;
		}
	}

	/**
	 * 实现ApplicationContextAware接口, 注入applicationContext到变量中.
	 */
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}
	
	public Dao getSmartDao(String dataSourceName) {
		if (dataSourceName == null || dataSourceName.length() == 0)
			return defaultSmartDao;
		else
			return smartDaoMap.get(dataSourceName);
	}
}
