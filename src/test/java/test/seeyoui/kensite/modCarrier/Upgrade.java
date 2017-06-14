package test.seeyoui.kensite.modCarrier;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.seeyoui.kensite.common.util.FileUtils;

public class Upgrade {
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
	
	public Upgrade(String jdbcType, String jdbcUrl, String jdbcUserName,
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
		unzip();
		Upgrade upgrade = init();
		scatter(upgrade);
		ExcuteSql.excuteSql(upgrade);
	}

	/**
	 * 解压
	 */
	public static void unzip() {
		String zipFileName ="src/test/resources/carrier/upgrade.zip";
		String descFileName ="src/test/resources/carrier/upgrade/";
		FileUtils.unZipFiles(zipFileName, descFileName);
	}

	/**
	 * 初始化
	 * @return
	 */
	public static Upgrade init() {
		// 读取properties文件
		Properties properties = new Properties();
		InputStream is = null;
		try {
			is = new FileInputStream("src/test/java/carrier.xml");
			properties.loadFromXML(is);
			return new Upgrade(properties.getProperty("jdbc.type"),
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
	 * 散布文件
	 */
	public static void scatter(Upgrade upgrade) {
		String descDirName = "src/main/java/com/seeyoui/kensite/"+upgrade.getSourceUrl();
		String srcDirName = "src/test/resources/carrier/upgrade/java/"+upgrade.getSourceUrl();
		FileUtils.copyDirectory(srcDirName, descDirName);
		descDirName = "src/main/resources/mapper/"+upgrade.getJdbcType()+"/"+upgrade.getSourceUrl();
		srcDirName = "src/test/resources/carrier/upgrade/mapper/"+upgrade.getSourceUrl();
		FileUtils.copyDirectory(srcDirName, descDirName);
		descDirName = "src/main/webapp/WEB-INF/view/"+upgrade.getSourceUrl();
		srcDirName = "src/test/resources/carrier/upgrade/view/"+upgrade.getSourceUrl();
		FileUtils.copyDirectory(srcDirName, descDirName);
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
