package com.muzhi.pinterest.ui.activity;


import com.muzhi.pinterest.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends Activity {
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		showMainActivity(3*1000);
	}
	

	private void showMainActivity(long delayMillis) {
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				startActivity(new Intent(SplashActivity.this, GuideActivity.class));
				finish();
			}
		};
		Handler handler = new Handler();
		handler.postDelayed(runnable, delayMillis);		
	}
	
}
