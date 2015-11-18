package com.muzhi.pinterest;

import android.view.View;
import android.view.View.OnClickListener;

import com.muzhi.pinterest.widget.MActionBar;

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
				showAlertDialog("是否要删除？","确定","取消",null);
			}
		});
		
	}

	
}
