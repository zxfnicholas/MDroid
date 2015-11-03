package com.muzhi.mdroid.ui;

import java.util.Map;

import com.muzhi.mdroid.tools.ObjectUtil;
import com.muzhi.mdroid.widget.AlertDialog;
import com.muzhi.mdroid.widget.LoadingDialog;
import com.muzhi.mdroid.widget.swipeback.SwipeBackActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;


/**
 * activity基础类
 * 
 */
public abstract class MBaseActivity extends SwipeBackActivity{

	/*****
	 * 公共变量
	 */
	protected Context mContext;
	
	protected Handler handler;
	protected Bundle bundle;
	protected Map<String, String> params;
	protected Dialog loadingDialog;
	protected AlertDialog mAlertDialog;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		mContext=this;
		
	}

	
	/**
	 * 弹出加载框
	 */
	protected void showLoadingDialog(String msg){
		if (loadingDialog == null){
			loadingDialog = LoadingDialog.createLoadingDialog(this, msg);
			loadingDialog.show();
		}
		else{
			loadingDialog.show();
		}
	}
	
	/**
	 * 关闭加载框
	 */
	protected void closeLoadingDialog(){
		if (ObjectUtil.isNotEmpty(loadingDialog)){
			loadingDialog.dismiss();
		}
	}
	
	
	protected void showAlertDialog(String title,String yes,String no){
		AlertDialog.Builder customBuilder = new AlertDialog.Builder(this);
	    customBuilder.setMessage("是否删除？").setTitle("提示")
	        .setPositiveButton("取消", new DialogInterface.OnClickListener() {
	            @Override
				public void onClick(DialogInterface dialog, int which) {
	            	
	            }
	        })
	        .setNegativeButton("确定",new DialogInterface.OnClickListener() {
	            @Override
				public void onClick(DialogInterface dialog, int which) {
	                dialog.dismiss();	               
	               
	            }
	        });
	    mAlertDialog = customBuilder.create();
	    mAlertDialog.show();
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
