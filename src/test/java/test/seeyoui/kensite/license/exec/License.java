package test.seeyoui.kensite.license.exec;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

import test.seeyoui.kensite.license.license.DESCoder;
import test.seeyoui.kensite.license.license.MD5;
import test.seeyoui.kensite.license.license.RSACoder;

public class License {
	public static void main(String[] args) throws Exception {
//		String mac = "90-2B-34-6C-66-29";//192
		String mac = "FA-16-3E-14-0C-14";//119
//		String mac = "02-00-4C-4F-4F-50";//139
//		String mac = MacUtils.getMac();
		String license = MD5.md5(mac); // 客户标识
		System.out.println(license);
		String company = "万洲嘉智"; // 公司名称
		int hrmnum = 99999; // 用户数
		String expiredate = "2017-11-26"; // 过期时间
		int flag = 0; // 并发
		String filePath = "E:/"+license+".license";
		license(filePath, company, license, hrmnum, expiredate, flag);
	}
	
	public static void license(String filePath, String company, String license, int hrmnum, String expireDate, int flag) throws Exception {
		File localZipFile = new File(filePath);
		ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(localZipFile));
		zout.setEncoding("UTF-8");
		// 生成对应的key
		Map<String, Object> keys = RSACoder.initKey();
		// 压缩文件
		try {
			// 公匙 和 私匙
			byte[] publicKeys = RSACoder.getPublicKey(keys);
			byte[] privateKeys = RSACoder.getPrivateKey(keys);
		
			String licenseKey = MD5.md5(company+license+"ALL"+hrmnum+expireDate+flag);
			String licenseInfo = "{\"company\":\""+company+"\",\"license\":\"" + licenseKey
					+ "\",\"software\":\"ALL\",\"hrmnum\":\""+hrmnum+"\",\"expiredate\":\""+expireDate+"\",\"concurrentFlag\":\""+flag+"\"}";
			// 加密前
			byte[] licenseBytes = license.getBytes();
			
			// 加密后
			byte[] licenseRSA = RSACoder.encryptByPrivateKey(licenseBytes, privateKeys);

			// license 加密前
			byte[] licenseInfoBytes = licenseInfo.getBytes();
			// 版本
			byte[] version = ("kensite" + 2).getBytes();
			// 授权号 和 版本
			byte[] licenseTypeId = ArrayUtils.addAll(licenseBytes, version);

			// 加密后内容
			byte[] licenseInfoDES = DESCoder.encrypt(licenseInfoBytes, licenseTypeId);
			
			// 添加公匙key
			putMyEntry(zout, "publicKey", publicKeys);
			// 添加加密内容 客户码加密后的内容
			putMyEntry(zout, "license", licenseRSA);
			// 添加内容
			putMyEntry(zout, "licenseInfo", licenseInfoDES);
			zout.close();
			System.out.println("注册成功!!! 文件地址"+filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void putMyEntry(ZipOutputStream zot, String name, byte[] bts) throws IOException {
		zot.putNextEntry(new ZipEntry(name));
		// 读取下载文件内容
		zot.write(bts);
		zot.flush();
		zot.closeEntry();
	}
}
