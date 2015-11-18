package com.muzhi.mdroid.tools;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreUtil {
	
	private static SharedPreferences sp_instance;
	
	public static final String Key_Login_User = "login_user";
	public static final String Key_Login = "isLogin"; //是否登录
	public static final String Key_VerCode="vercode";//程序版本
	
		
	public static SharedPreferences getSharedPreference(Context con) {
		if (sp_instance == null)
			sp_instance = con.getSharedPreferences(con.getPackageName() + "_preferences", 0);
		return sp_instance;
	}

	public static String getString(String key) {
		return sp_instance.getString(key, "");
	}

	public static boolean getBoolean(String key) {
		return sp_instance.getBoolean(key, false);
	}

	public static void setString(String key, String value) {
		sp_instance.edit().putString(key, value).commit();
	}

	public static void setBoolean(String key, boolean value) {
		sp_instance.edit().putBoolean(key, value).commit();
	}

	public static void setInt(String key, int value) {
		sp_instance.edit().putInt(key, value).commit();
	}

	public static int getInt(String key,int value) {
		return sp_instance.getInt(key, value);
	}
	
	
	
}
