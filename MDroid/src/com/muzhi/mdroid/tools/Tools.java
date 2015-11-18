package com.muzhi.mdroid.tools;


import org.json.JSONObject;

import com.muzhi.mdroid.model.ResultInfo;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;


/**
 * 基础的操作类
 */
public class Tools {
	
	
	
	//获取从服务器返回的信息
	public static ResultInfo getJsonResult(String jsonString){
		
		ResultInfo info=new ResultInfo();
		try {
			
			JSONObject json = new JSONObject(jsonString);
			info.setCode(ParseUtil.getInt("code", json));			
			info.setData(ParseUtil.getRawString("data", json));	
			info.setMessage(ParseUtil.getRawString("message", json));
		}
		catch(Exception e){
			info.setCode(1);
			info.setMessage(e.getMessage());
		}
		return info;		
	}
	
	
	
	/**
	 * activity切换公共方法
	 * 
	 * @author 
	 * @date 
	 * @param activity
	 * @param activityclass
	 * @param bundle
	 */
	public static void openActivity(Context mContext, Class<?> activityclass) {
		openActivity(mContext,activityclass);
	}
	public static void openActivity(Context mContext, Class<?> activityclass, Bundle bundle) {
		Intent intent = new Intent(mContext, activityclass);
		if (ObjectUtil.isNotEmpty(bundle)) {
			intent.putExtras(bundle);
		}
		if (ObjectUtil.isNotEmpty(mContext)) {
			mContext.startActivity(intent);
		}
	}

	
	
	public static void openActivityForResult(Activity activity, Class<?> activityclass, Bundle bundle, int requestCode) {
		Intent intent = new Intent(activity, activityclass);
		if (ObjectUtil.isNotEmpty(bundle)) {
			intent.putExtras(bundle);
		}
		activity.startActivityForResult(intent, requestCode);

	}
	
	
	

	/**
	 * 动态切换fragment
	 * 
	 * @author
	 * @date 
	 * @param fragment
	 */
	public static void replaceFragment(int id, Fragment fragment, FragmentManager fm) {
		fm.beginTransaction().replace(id, fragment).commitAllowingStateLoss();
	}
	
	
	
	public static void alertDialog(String message, String positiveText, String negativeButton, OnClickListener onClickListener, Context context) {
		alertDialog("提示", message, positiveText, negativeButton, onClickListener, context);
	}

	public static void alertDialog(String title, String message, String positiveText, String negativeButton, OnClickListener onClickListener,
			Context context) {
		if (ObjectUtil.isNotEmpty(context)) {
			AlertDialog.Builder builder = new Builder(context);
			builder.setMessage(message);
			builder.setTitle(title);
			builder.setCancelable(true);
			builder.setPositiveButton(( ObjectUtil.isNotEmpty(positiveText)? positiveText : "确认"), onClickListener);
			builder.setNegativeButton((ObjectUtil.isNotEmpty(negativeButton)? negativeButton : "取消"), onClickListener);
			builder.create().show();
		}
	}
	
	
	
	
}
