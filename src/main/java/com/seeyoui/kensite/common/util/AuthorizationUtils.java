package com.seeyoui.kensite.common.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.HashSet;
import java.util.Set;

public class AuthorizationUtils {

	public static void main(String args[]) throws Exception {
		String code = getMachineCode();
		System.out.println("机器码：" + MD5.md5(code));
	}

	private static String getMachineCode() throws Exception {
		Set<String> result = new HashSet<>();
		InetAddress ia = InetAddress.getLocalHost();
		String mac = getMACAddress(ia);
		result.add(mac);
//		Properties props = System.getProperties();
//		String javaVersion = props.getProperty("java.version");
//		result.add(javaVersion);
//		String javaVMVersion = props.getProperty("java.vm.version");
//		result.add(javaVMVersion);
//		String osVersion = props.getProperty("os.version");
//		result.add(osVersion);
		result.add(ia.getHostAddress());
		return result.toString();
	}

	private static String getMACAddress(InetAddress ia) throws Exception {
		byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < mac.length; i++) {
			if (i != 0) {
				sb.append("-");
			}
			String s = Integer.toHexString(mac[i] & 0xFF);
			sb.append(s.length() == 1 ? 0 + s : s);
		}
		return sb.toString().toUpperCase();
	}
}