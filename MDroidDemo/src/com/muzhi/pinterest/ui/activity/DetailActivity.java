package com.muzhi.pinterest.ui.activity;

import android.view.View;
import android.view.View.OnClickListener;

import com.muzhi.mdroid.tools.T;
import com.muzhi.pinterest.R;
import com.muzhi.pinterest.R.id;
import com.muzhi.pinterest.R.layout;
import com.muzhi.pinterest.widget.MActionBar;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;

public class DetailActivity extends BaseActivity {

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		
		this.mActionBar=(MActionBar)findViewById(R.id.actiobar);
		this.mActionBar.setBackgroundColor(Color.parseColor("#f4a2ae"));
		this.mActionBar.setTitle("详情页");
		this.mActionBar.setBackTitle("关闭",new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		
		initEvent();
		
		
	}
	
	
	private void initEvent(){
		
		findViewById(R.id.btn_dialog1).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showLoadingDialog("正在加载");
			}
		});
		
		
		findViewById(R.id.btn_dialog2).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog();
			}
		});
		
	}

	private void showDialog(){
		
		showAlertDialog("是否要删除？","确定","取消",new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				if(which==-1){
					//取消
					T.showShort(mContext, "取消");
				}
				else{
					//确定
					T.showShort(mContext, "确定");
				}
				dialog.dismiss();
			}
		});
	}
	
}
