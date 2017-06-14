package test.seeyoui.kensite.modCarrier;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.seeyoui.kensite.common.util.FileUtils;

public class Carrier {
	private String jdbcType;
	private String jdbcUrl;
	private String jdbcUserName;
	private String jdbcPassWord;
	private String targetJdbcType;
	private String targetJdbcUrl;
	private String targetJdbcUserName;
	private String targetJdbcPassWord;
	private String sourceUrl;
	private String siteVersion;
	private String tableName;
	
	public Carrier(String jdbcType, String jdbcUrl, String jdbcUserName,
			String jdbcPassWord, String targetJdbcType, String targetJdbcUrl,
			String targetJdbcUserName, String targetJdbcPassWord,
			String sourceUrl, String siteVersion, String tableName) {
		super();
		this.jdbcType = jdbcType;
		this.jdbcUrl = jdbcUrl;
		this.jdbcUserName = jdbcUserName;
		this.jdbcPassWord = jdbcPassWord;
		this.targetJdbcType = targetJdbcType;
		this.targetJdbcUrl = targetJdbcUrl;
		this.targetJdbcUserName = targetJdbcUserName;
		this.targetJdbcPassWord = targetJdbcPassWord;
		this.sourceUrl = sourceUrl;
		this.siteVersion = siteVersion;
		this.tableName = tableName;
	}

	public static void main(String[] args) {
		Carrier carrier = init();
		gather(carrier);
		createSql(carrier);
		zip();
		deleteFile();
	}

	public static Carrier init() {
		// 读取properties文件
		Properties properties = new Properties();
		InputStream is = null;
		try {
			is = new FileInputStream("src/test/java/carrier.xml");
			properties.loadFromXML(is);
			return new Carrier(properties.getProperty("jdbc.type"),
					properties.getProperty("jdbc.url"),
					properties.getProperty("jdbc.username"),
					properties.getProperty("jdbc.password"),
					properties.getProperty("target.jdbc.type"),
					properties.getProperty("target.jdbc.url"),
					properties.getProperty("target.jdbc.username"),
					properties.getProperty("target.jdbc.password"),
					properties.getProperty("sourceUrl"),
					properties.getProperty("siteVersion"),
					properties.getProperty("tableName"));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (Exception e) {
			}
		}
		return null;
	}
	
	/**
	 * 收拢文件
	 */
	public static void gather(Carrier carrier) {
		String srcDirName = "src/main/java/com/seeyoui/kensite/"+carrier.getSourceUrl();
		String descDirName = "src/test/resources/carrier/upgrade/java/"+carrier.getSourceUrl();
		FileUtils.copyDirectory(srcDirName, descDirName);
		srcDirName = "src/main/resources/mapper/"+carrier.getJdbcType()+"/"+carrier.getSourceUrl();
		descDirName = "src/test/resources/carrier/upgrade/mapper/"+carrier.getSourceUrl();
		FileUtils.copyDirectory(srcDirName, descDirName);
		srcDirName = "src/main/webapp/WEB-INF/view/"+carrier.getSourceUrl();
		descDirName = "src/test/resources/carrier/upgrade/view/"+carrier.getSourceUrl();
		FileUtils.copyDirectory(srcDirName, descDirName);
		createProperties(carrier);
	}
	
	/**
	 * 创建properties xml文件
	 * @param carrier
	 */
	public static void createProperties(Carrier carrier) {
		// 书写properties文件
		Properties properties = new Properties();
		properties.put("jdbc.type", carrier.getJdbcType());
		properties.put("jdbc.url", carrier.getJdbcUrl());
		properties.put("jdbc.username", carrier.getJdbcUserName());
		properties.put("jdbc.password", carrier.getJdbcPassWord());
		properties.put("target.jdbc.type", carrier.getTargetJdbcType());
		properties.put("target.jdbc.url", carrier.getJdbcUrl());
		properties.put("target.jdbc.username", carrier.getJdbcUserName());
		properties.put("target.jdbc.password", carrier.getJdbcPassWord());
		properties.put("sourceUrl", carrier.getSourceUrl());
		properties.put("siteVersion", carrier.getSiteVersion());
		properties.put("tableName", carrier.getTableName());

		OutputStream stream = null;
		try {
			stream = new FileOutputStream("src/test/resources/carrier/upgrade/carrier.xml");
			properties.storeToXML(stream, "model carrier");
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				stream.close();
			} catch (Exception e) {
			}
		}
	}
	
	public static void createSql(Carrier carrier) {
		String fileName = "src/test/resources/carrier/upgrade/create.oracle.sql";
		FileUtils.deleteFile(fileName);
		FileUtils.createFile(fileName);
		if(carrier.getSiteVersion().equals("2")) {
			String modInsertSql = CreateSql.modInsertSql(carrier);
			FileUtils.writeToFile(fileName, modInsertSql, true);
		}
		String createSql = CreateSql.createSql(carrier);
		String insertSql = CreateSql.insertSql(carrier);
		FileUtils.writeToFile(fileName, createSql, true);
		FileUtils.writeToFile(fileName, insertSql, true);
	}
	
	/**
	 * 压缩文件
	 */
	public static void zip() {
		String srcDirName = "src/test/resources/carrier/upgrade/";
		String descFileName = "src/test/resources/carrier/upgrade.zip";
		FileUtils.zipFiles(srcDirName, "", descFileName);
	}
	
	/**
	 * 删除源文件
	 */
	public static void deleteFile() {
		String dirName = "src/test/resources/carrier/upgrade/";
		FileUtils.deleteDirectory(dirName);
	}

	public String getJdbcType() {
		return jdbcType;
	}

	public void setJdbcType(String jdbcType) {
		this.jdbcType = jdbcType;
	}

	public String getJdbcUrl() {
		return jdbcUrl;
	}

	public void setJdbcUrl(String jdbcUrl) {
		this.jdbcUrl = jdbcUrl;
	}

	public String getJdbcUserName() {
		return jdbcUserName;
	}

	public void setJdbcUserName(String jdbcUserName) {
		this.jdbcUserName = jdbcUserName;
	}

	public String getJdbcPassWord() {
		return jdbcPassWord;
	}

	public void setJdbcPassWord(String jdbcPassWord) {
		this.jdbcPassWord = jdbcPassWord;
	}

	public String getTargetJdbcType() {
		return targetJdbcType;
	}

	public void setTargetJdbcType(String targetJdbcType) {
		this.targetJdbcType = targetJdbcType;
	}

	public String getTargetJdbcUrl() {
		return targetJdbcUrl;
	}

	public void setTargetJdbcUrl(String targetJdbcUrl) {
		this.targetJdbcUrl = targetJdbcUrl;
	}

	public String getTargetJdbcUserName() {
		return targetJdbcUserName;
	}

	public void setTargetJdbcUserName(String targetJdbcUserName) {
		this.targetJdbcUserName = targetJdbcUserName;
	}

	public String getTargetJdbcPassWord() {
		return targetJdbcPassWord;
	}

	public void setTargetJdbcPassWord(String targetJdbcPassWord) {
		this.targetJdbcPassWord = targetJdbcPassWord;
	}

	public String getSourceUrl() {
		return sourceUrl;
	}

	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}

	public String getSiteVersion() {
		return siteVersion;
	}

	public void setSiteVersion(String siteVersion) {
		this.siteVersion = siteVersion;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
}
