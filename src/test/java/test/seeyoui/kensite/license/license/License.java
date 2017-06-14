package test.seeyoui.kensite.license.license;

import java.io.File;

import net.sf.json.JSONObject;

import org.apache.commons.lang.ArrayUtils;

public class License {
	public static boolean license(String licensefilepath) throws Exception {
		// 公匙key
		byte[] publicKey = Zip.getZipSomeByte(licensefilepath, "publicKey");
		// 加密内容
		byte[] licenseRSA = Zip.getZipSomeByte(licensefilepath, "license");
		// 授权文件
		byte[] licenseInfoDES = Zip.getZipSomeByte(licensefilepath, "licenseInfo");
		// 最终license
		byte[] license = RSACoder.decryptByPublicKey(licenseRSA, publicKey);

		// 授权key和版本ID
		byte[] licenseTypeId = ArrayUtils.addAll(license, ("kensite" + 2).getBytes());
		byte[] licenseInfo = DESCoder.decrypt(licenseInfoDES, licenseTypeId);
		JSONObject jsonLicense = JSONObject.fromObject(new String(licenseInfo));
		
//		JSONObject jsonLicense = new JSONObject(new String(licenseInfo));
		String company = jsonLicense.getString("company");
		String ln = jsonLicense.getString("license");
		String software = jsonLicense.getString("software");
		String hrmnum = jsonLicense.getString("hrmnum");
		String expiredate = jsonLicense.getString("expiredate");
		String concurrentFlag = jsonLicense.getString("concurrentFlag");
		if(expiredate.compareTo(DateUtils.getDate()) < 0) {
			return false;
		}
		String mac = MacUtils.getMac();
		String licensecode = MD5.md5(mac);
		String src = company + licensecode + software + hrmnum + expiredate + concurrentFlag;
		return ln.equals(MD5.md5(src));
	}
	
	public static boolean ckLicense(String licensefilepath) throws Exception {
		if(!new File(licensefilepath).exists()) {
			return false;
		}
		return license(licensefilepath);
	}
}
