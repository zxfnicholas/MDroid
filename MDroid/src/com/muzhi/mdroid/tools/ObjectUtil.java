package com.muzhi.mdroid.tools;

import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.json.JSONArray;
import org.json.JSONObject;


/**
 * 公共方法定义
 * 
 * @author
 * @date
 */
public class ObjectUtil {
	/**
	 * 判断对象不为空
	 * 
	 * @param object
	 * @return
	 */
	public static boolean isNotEmpty(Object object) {
		if (null != object && !"".equals(object)) {
			return true;
		}
		return false;
	}

	/**
	 * list对象空判断
	 * 
	 * @param t
	 * @return
	 */
	public static boolean isNotEmpty(List<?> t) {
		if (null != t && t.size() > 0) {
			return true;
		}
		return false;
	}

	public static boolean isNotEmpty(Map<?, ?> t) {
		if (null != t && t.size() > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 判断数组不为空
	 * 
	 * @param objects
	 * @return
	 */
	public static boolean isNotEmpty(Object[] objects) {
		if (null != objects && objects.length > 0) {
			return true;
		}
		return false;
	}

	/**
	 * json数组空值判断
	 * 
	 * @param jsonArray
	 * @return
	 */
	public static boolean isNotEmpty(JSONArray jsonArray) {
		if (null != jsonArray && jsonArray.length() > 0) {
			return true;
		}
		return false;
	}

	/**
	 * json对象空值判断
	 * 
	 * @param jsonObject
	 * @return
	 */
	public static boolean isNotEmpty(JSONObject jsonObject) {
		if (null != jsonObject && !"".equals(jsonObject)) {
			return true;
		}
		return false;
	}


	/**
	 * 启动一个自定义线程
	 * 
	 * @param runnable
	 */
	public static void startThreed(Runnable runnable) {
		if (ObjectUtil.isNotEmpty(runnable)) {
			new Thread(runnable).start();
		}
	}

	/**
	 * JSON对象在get值的时候判断不为空和NaN
	 * 
	 * @param object
	 * @return
	 */
	public static boolean isNotEmptyAndNaN(Object object) {
		if (isNotEmpty(object) && !object.toString().equals("NaN")) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 使用StringTokenizer类将字符串按分隔符转换成字符数组
	 * 
	 * @param string
	 *            字符串
	 * @param divisionChar
	 *            分隔符
	 * @return 字符串数组
	 * @see [类、类#方法、类#成员]
	 */
	public static String[] stringAnalytical(String string, String divisionChar) {
		int i = 0;
		StringTokenizer tokenizer = new StringTokenizer(string, divisionChar);
		String[] str = new String[tokenizer.countTokens()];
		while (tokenizer.hasMoreTokens()) {
			str[i] = new String();
			str[i] = tokenizer.nextToken();
			i++;
		}
		return str;
	}
}
