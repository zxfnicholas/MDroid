package com.muzhi.pinterest;


import com.muzhi.mdroid.ui.MBaseActivity;
import com.muzhi.pinterest.widget.MActionBar;

import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;


public class BaseActivity extends MBaseActivity{

	protected Context mContext;
    protected NotificationManager notificationManager;
    
    protected MActionBar mActionBar;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//getActionBar().hide();
		mContext=this;
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//不可横屏幕
		notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

		
	}
	



}
