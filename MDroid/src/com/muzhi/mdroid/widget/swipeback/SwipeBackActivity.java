package com.muzhi.mdroid.widget.swipeback;


import com.muzhi.mdroid.R;
import com.muzhi.mdroid.listener.BackGestureListener;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;

/**
 * 想要实现向右滑动删除Activity效果只需要继承SwipeBackActivity即可，如果当前页面含有ViewPager
 * 只需要调用SwipeBackLayout的setViewPager()方法即可
 * 
 * @author Jazzy
 *
 */
public class SwipeBackActivity extends FragmentActivity {
	protected SwipeBackLayout layout;
	private ImageView ivShadow;
	
	/** 手势监听 */
	GestureDetector mGestureDetector;
	/** 是否需要监听手势关闭功能 */
	private boolean mNeedBackGesture = false;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//layout = (SwipeBackLayout) LayoutInflater.from(this).inflate(R.layout.base, null);
		
		layout = new SwipeBackLayout(this);	
		
		//getContainer();
		
		layout.attachToActivity(this);
		
		//initGestureDetector();
	}
	
	private void getContainer() {
		ivShadow = new ImageView(this);

        ivShadow.setBackgroundColor(Color.parseColor("#7f000000"));
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        layout.addView(ivShadow, params);
        layout.addView(layout);
        
    }
	

    public void setSwipeBackEnable(boolean enable) {
    	mNeedBackGesture=enable;
    	layout.setEnableGesture(enable);
    }
	
	
	@Override
	public void startActivity(Intent intent) {
		super.startActivity(intent);
		overridePendingTransition(R.anim.base_slide_right_in, R.anim.base_slide_remain);
	}

	// Press the back button in mobile phone
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(0, R.anim.base_slide_right_out);
	}
	
	
	
	/*private void initGestureDetector() {
		if (mGestureDetector == null) {
			mGestureDetector = new GestureDetector(getApplicationContext(),new BackGestureListener(this));
		}
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		if(mNeedBackGesture){
			return mGestureDetector.onTouchEvent(ev) || super.dispatchTouchEvent(ev);
		}
		return super.dispatchTouchEvent(ev);
	}
	
	
	 * 设置是否进行手势监听
	 
	public void setNeedBackGesture(boolean mNeedBackGesture){
		this.mNeedBackGesture = mNeedBackGesture;
	}*/
	
	
	
	
	
	
	
	
	
	
	
	/*private View getContainer() {
        RelativeLayout container = new RelativeLayout(this);
        layout = new SwipeBackLayout(this);
       
        ivShadow = new ImageView(this);

        ivShadow.setBackgroundColor(Color.parseColor("#7f000000"));
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        container.addView(ivShadow, params);
        container.addView(layout);
        
        return container;
    }*/
	
	/*@Override
	public void startActivity(Intent intent) {
		super.startActivity(intent);
		overridePendingTransition(R.anim.base_slide_right_in, R.anim.base_slide_remain);
	}




	// Press the back button in mobile phone
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(0, R.anim.base_slide_right_out);
	}*/


}
