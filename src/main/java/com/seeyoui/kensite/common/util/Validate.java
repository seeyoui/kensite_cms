package com.seeyoui.kensite.common.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {

	/**
	 * 功能：判断字符串是否为大于等于0的数
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isDecimal(String str) {
		Pattern pattern = Pattern.compile("^[0-9]+\\.{0,1}[0-9]{0,2}$");
		Matcher isNum = pattern.matcher(str);
		if (isNum.matches()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 功能：判断字符串是否为数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (isNum.matches()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 验证手机号码
	 * 
	 * @param mobiles
	 * @return [0-9]{5,9}
	 */
	public static boolean isMobileNO(String mobiles) {
		boolean flag = false;
		try {
			Pattern p = Pattern
					.compile("^((13[0-9]|14[0-9]|15[0-9]|18[0-9]))\\d{8}$");
			Matcher m = p.matcher(mobiles);
			flag = m.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * 验证邮箱地址是否正确
	 * 
	 * @param email
	 * @return
	 */
	public static boolean checkEmail(String email) {
		boolean flag = false;
		try {
			String check = "^([a-z0-9A-Z]+[_|-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(email);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}

		return flag;
	}

	/**
	 * 验证电话号码
	 * */
	public static boolean checkphoneNum(String phoneNum) {
		String REG_EXP = "^([0-9]{3}-?[0-9]{8})|([0-9]{4}-?[0-9]{7})|([0-9]{4}-?[0-9]{8})|([0-9]{7})$";
		if (phoneNum != null) {
			return phoneNum.matches(REG_EXP);
		} else {
			return false;
		}
	}

	/**
	 * 验证是否为时间格式
	 * */
	public static boolean checkDate(String date, String exp) {
		try {
			DateFormat format1 = new SimpleDateFormat(exp);
			format1.parse(date);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 转换为时间格式
	 * */
	public static Date strToDate(String date, String exp) {
		try {
			DateFormat format1 = new SimpleDateFormat(exp);
			return format1.parse(date);
		} catch (Exception e) {
			return null;
		}
	}

	/***
	 * 时间比较
	 * **/
	public static boolean checkDateBegin(Date myString, Date nowdate) {
		boolean flag = myString.before(nowdate);
		if (flag) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean equals(String s1, String s2) {
		if ((s1 == null) && (s2 == null)) {
			return true;
		} else if ((s1 == null) || (s2 == null)) {
			return false;
		} else {
			return s1.equals(s2);
		}
	}

	// 验证email地址
	public static boolean isAddress(String address) {
		if (isNull(address)) {
			return false;
		}

		String[] tokens = address.split(StringPool.AT);

		if (tokens.length != 2) {
			return false;
		}

		for (int i = 0; i < tokens.length; i++) {
			char[] c = tokens[i].toCharArray();

			for (int j = 0; j < c.length; j++) {
				if (Character.isWhitespace(c[j])) {
					return false;
				}
			}
		}

		return true;
	}

	public static boolean isChar(char c) {
		return Character.isLetter(c);
	}

	public static boolean isChar(String s) {
		if (isNull(s)) {
			return false;
		}

		char[] c = s.toCharArray();

		for (int i = 0; i < c.length; i++) {
			if (!isChar(c[i])) {
				return false;
			}
		}

		return true;
	}

	public static boolean isDigit(char c) {
		int x = c;

		if ((x >= 48) && (x <= 57)) {
			return true;
		}

		return false;
	}

	public static boolean isDigit(String s) {
		if (isNull(s)) {
			return false;
		}

		char[] c = s.toCharArray();

		for (int i = 0; i < c.length; i++) {
			if (!isDigit(c[i])) {
				return false;
			}
		}

		return true;
	}

	public static boolean isHex(String s) {
		if (isNull(s)) {
			return false;
		}

		return true;
	}

	public static boolean isHTML(String s) {
		if (isNull(s)) {
			return false;
		}

		if (((s.indexOf("<html>") != -1) || (s.indexOf("<HTML>") != -1))
				&& ((s.indexOf("</html>") != -1) || (s.indexOf("</HTML>") != -1))) {

			return true;
		}

		return false;
	}

	public static boolean hasSpace(String s) {
		if (isNull(s)) {
			return false;
		}

		int i = s.indexOf(StringPool.SPACE);
		if (i == -1) {
			return true;
		} else {
			return false;
		}
	}

	// public static boolean isLUHN(String number) {
	// if (number == null) {
	// return false;
	// }
	//
	// number = StringUtil.reverse(number);
	//
	// int total = 0;
	//
	// for (int i = 0; i < number.length(); i++) {
	// int x = 0;
	//
	// if (((i + 1) % 2) == 0) {
	// x = Integer.parseInt(number.substring(i, i + 1)) * 2;
	//
	// if (x >= 10) {
	// String s = Integer.toString(x);
	//
	// x = Integer.parseInt(s.substring(0, 1)) +
	// Integer.parseInt(s.substring(1, 2));
	// }
	// }
	// else {
	// x = Integer.parseInt(number.substring(i, i + 1));
	// }
	//
	// total = total + x;
	// }
	//
	// if ((total % 10) == 0) {
	// return true;
	// }
	// else {
	// return false;
	// }
	// }

	public static boolean isDate(int month, int day, int year) {
		return isGregorianDate(month, day, year);
	}

	public static boolean isGregorianDate(int month, int day, int year) {
		if ((month < 0) || (month > 11)) {
			return false;
		}

		int[] months = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

		if (month == 1) {
			int febMax = 28;

			if (((year % 4) == 0) && ((year % 100) != 0) || ((year % 400) == 0)) {

				febMax = 29;
			}

			if ((day < 1) || (day > febMax)) {
				return false;
			}
		} else if ((day < 1) || (day > months[month])) {
			return false;
		}

		return true;
	}

	public static boolean isJulianDate(int month, int day, int year) {
		if ((month < 0) || (month > 11)) {
			return false;
		}

		int[] months = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

		if (month == 1) {
			int febMax = 28;

			if ((year % 4) == 0) {
				febMax = 29;
			}

			if ((day < 1) || (day > febMax)) {
				return false;
			}
		} else if ((day < 1) || (day > months[month])) {
			return false;
		}

		return true;
	}

	// public static boolean isEmailAddress(String emailAddress) {
	// Boolean valid = null;
	//
	// try {
	// valid = (Boolean)PortalClassInvoker.invoke(
	// "com.liferay.util.mail.InternetAddressUtil", "isValid",
	// emailAddress);
	// }
	// catch (Exception e) {
	// if (_log.isWarnEnabled()) {
	// _log.warn(e);
	// }
	// }
	//
	// if (valid == null) {
	// return false;
	// }
	// else {
	// return valid.booleanValue();
	// }
	// }

	public static boolean isEmailAddressSpecialChar(char c) {

		// LEP-1445

		for (int i = 0; i < _EMAIL_ADDRESS_SPECIAL_CHAR.length; i++) {
			if (c == _EMAIL_ADDRESS_SPECIAL_CHAR[i]) {
				return true;
			}
		}

		return false;
	}

	/**
	 * @deprecated Use <code>isEmailAddress</code>.
	 */
	// public static boolean isValidEmailAddress(String ea) {
	// return isEmailAddress(ea);
	// }
	@Deprecated
	public static boolean isName(String name) {
		if (isNull(name)) {
			return false;
		}

		char[] c = name.trim().toCharArray();

		for (int i = 0; i < c.length; i++) {
			if (((!isChar(c[i])) && (!Character.isWhitespace(c[i])))
					|| (c[i] == ',')) {

				return false;
			}
		}

		return true;
	}

	public static boolean isNumber(String number) {
		if (isNull(number)) {
			return false;
		}

		char[] c = number.toCharArray();

		for (int i = 0; i < c.length; i++) {
			if (!isDigit(c[i])) {
				return false;
			}
		}

		return true;
	}

	public static boolean isNull(Object obj) {
		if (obj instanceof Long) {
			return isNull((Long) obj);
		} else if (obj instanceof String) {
			return isNull((String) obj);
		} else if (obj == null) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isNull(Long l) {
		if ((l == null) || l.longValue() == 0) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isNull(String s) {
		if (s == null) {
			return true;
		}

		s = s.trim();

		if ((s.equals(StringPool.NULL)) || (s.equals(StringPool.BLANK))) {
			return true;
		}

		return false;
	}

	public static boolean isNull(Object[] array) {
		if ((array == null) || (array.length == 0)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isNotNull(Object obj) {
		return !isNull(obj);
	}

	public static boolean isNotNull(Long l) {
		return !isNull(l);
	}

	public static boolean isNotNull(String s) {
		return !isNull(s);
	}

	public static boolean isNotNull(Object[] array) {
		return !isNull(array);
	}

	// 验证是否符合密码条件
	public static boolean isPassword(String password) {
		if (isNull(password)) {
			return false;
		}

		if (password.length() < 4) {
			return false;
		}

		char[] c = password.toCharArray();

		for (int i = 0; i < c.length; i++) {
			if ((!isChar(c[i])) && (!isDigit(c[i]))) {

				return false;
			}
		}

		return true;
	}

	public static boolean isVariableTerm(String s) {
		if (s.startsWith("[$") && s.endsWith("$]")) {
			return true;
		} else {
			return false;
		}
	}

	private static char[] _EMAIL_ADDRESS_SPECIAL_CHAR = new char[] { '.', '!',
			'#', '$', '%', '&', '\'', '*', '+', '-', '/', '=', '?', '^', '_',
			'`', '{', '|', '}', '~' };

	/**
	 * 验证邮政编码
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isPostalcode(String str) {
		return str.matches("^[0-9]{6}$");
	}

	public static String StringFilter(String str) {
		// 清除掉所有特殊字符
		String regEx = "[`~!#$%^&()+=|{}':;',//[//]<>/?~！#￥%……&（）+|{}【】‘；：”“’。，、？]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();
	}

	/**
	 * 包含特殊字符（<>&!-\$）
	 * 
	 * @param str
	 * @return
	 */
	public static boolean hasSpecialChart(String strText) {
		String strRule = "\\/:,;*?|<>～%";
		String tempRule = "";
		int intStrLen;
		int intI;
		intStrLen = strRule.length();
		for (intI = 1; intI <= intStrLen; intI++) {
			tempRule = getMidW(strRule, intI, 1);
			if (strText.indexOf(tempRule) >= 0) {
				return true;
			}
		}
		return false;
	}

	public static String getMidW(String stringIn, int startIn, int lenIn) {
		StringBuffer midString;
		StringBuffer stringBufferTemp;
		try {
			if (stringIn == null) {
				return null;
			}
			stringBufferTemp = new StringBuffer(stringIn);
			if (startIn > stringBufferTemp.length()) {
				return "";
			}
			if (lenIn > (stringBufferTemp.length() - startIn + 1)) {
				return getMidW(stringBufferTemp.toString(), startIn);
			}
			midString = new StringBuffer(stringBufferTemp.substring(
					startIn - 1, startIn + lenIn - 1));
			return midString.toString();
		} catch (Exception e) {
			return null;
		}
	}

	public static String getMidW(String stringIn, int startIn) {
		StringBuffer midString;
		StringBuffer stringBufferTemp;
		try {
			if (stringIn == null) {
				return null;
			}
			if (startIn > stringIn.length()) {
				return "";
			}
			stringBufferTemp = new StringBuffer(stringIn);
			midString = new StringBuffer(stringBufferTemp
					.substring(startIn - 1));
			return midString.toString();
		} catch (Exception e) {
			return null;
		}
	}

	// 验证测试
	public static void main(String[] args) {

		// String str = "fjlds;aflkdsajf#@324324ldskafuioere<>fdsafkjdsalkfj";
		String str = "00.12";
		if (isDecimal(str)) {
			System.out.println(Double.parseDouble(str) + "  aaa");
		}
		System.out.println(isDecimal(str));
		// System.out.println(hasSpecialChart(str));
		// str = "sdf";
		// System.out.println(hasSpecialChart(str));
	}
}
