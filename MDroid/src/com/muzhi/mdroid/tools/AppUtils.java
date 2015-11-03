package com.muzhi.mdroid.tools;

import java.io.File;
import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.telephony.TelephonyManager;
import android.view.inputmethod.InputMethodManager;

/**
 * 跟App相关的辅助类
 * 
 * 
 * 
 */
public class AppUtils{

	private AppUtils(){
		/* cannot be instantiated */
		throw new UnsupportedOperationException("cannot be instantiated");

	}

	/**
	 * 获取应用程序名称
	 */
	public static String getAppName(Context context){
		
		try{
			PackageManager packageManager = context.getPackageManager();
			PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(),0);
			int labelRes = packageInfo.applicationInfo.labelRes;
			return context.getResources().getString(labelRes);
		} 
		catch (NameNotFoundException e){
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * [获取应用程序版本名称信息]
	 * 
	 * @param context
	 * @return 当前应用的版本名称
	 */
	public static String getVersionName(Context context){
		try	{
			PackageManager packageManager = context.getPackageManager();
			PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
			return packageInfo.versionName;

		} 
		catch (NameNotFoundException e){
			e.printStackTrace();
		}
		return null;
	}

	
	/**
     * 获取软件版本号
     * 
     * @param context
     * @return
     */
    public static int getVersionCode(Context context){  
        int versionCode = 0;
        try{
            // 获取软件版本号           
        	PackageManager packageManager = context.getPackageManager();       
	        PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(),0); 
	        versionCode = packInfo.versionCode;
        } 
        catch (NameNotFoundException e){
            e.printStackTrace();
        }
        return versionCode;
    }
    
    /**
     *  获取本地号码
     */
    public static String getLocationPhone(Context context){
  	  	TelephonyManager tm = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        String deviceid = tm.getDeviceId(); //设备号
        String tel = tm.getLine1Number();	//电话号码
        String imei = tm.getSimSerialNumber();
        String imsi = tm.getSubscriberId();
        return tel;
    }

    /**
     * 判断程序是否在前台运行
     * 需要添加android.permission.GET_TASKS权限
     */
    public static boolean isTopActivity(Context context){
  	  boolean ret=false;
  	  String packageName =context.getPackageName();
  	  ActivityManager activityManager = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
  	  List<RunningTaskInfo>  tasksInfo = activityManager.getRunningTasks(1);  
  	  if(tasksInfo.size() > 0){  
  		//应用程序位于堆栈的顶层 
  		String topPackageName=tasksInfo.get(0).topActivity.getPackageName();
          if(packageName.equals(topPackageName)){  
          	ret= true;  
          } 
  	  } 
  	  return ret;	  
    }
    
    /**
     * 清除本应用所有的数据
     */
	public static void cleanApplicationData(Context context, String filePath) {
		
		File directory=new File(filePath);
		
		if (directory != null && directory.exists() && directory.isDirectory()) {
			/*for (File item : directory.listFiles()) {
				item.delete();
			}*/
			directory.delete();
		}
	}
    
    
	 /**
     * 显示或隐藏软键盘
     */
    public static void showKeyboard(Context context,boolean show) {
        Activity activity = (Activity) context;
        if (activity != null) {
            InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            if(show){
            	imm.showSoftInputFromInputMethod(activity.getCurrentFocus().getWindowToken(), 0);
            	imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
            }
            else{
            	if (imm.isActive() && activity.getCurrentFocus() != null) {
                    imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
                }
            }
        }
    }
    
    
    
    
    
    
    
    
    
}

