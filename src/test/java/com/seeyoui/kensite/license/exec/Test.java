package com.seeyoui.kensite.license.exec;

import com.seeyoui.kensite.common.util.MD5;
import com.seeyoui.kensite.license.license.MacUtils;
import com.seeyoui.kensite.license.license.License;

public class Test {
	public static void main(String[] args) throws Exception {
		System.out.println(new Test().getClass().getResource("").getPath());
		System.out.println(System.getProperty("user.dir"));
		String mac = MacUtils.getMac();
		String license = MD5.md5(mac); // 客户标识
		String licensefilepath = "E:/"+license+".license";
		System.out.println(License.ckLicense(licensefilepath));
	}
}
