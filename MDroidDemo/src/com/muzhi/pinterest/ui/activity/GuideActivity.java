package com.muzhi.pinterest.ui.activity;


import com.muzhi.mdroid.tools.Tools;
import com.muzhi.mdroid.views.CirclePageIndicator;
import com.muzhi.pinterest.R;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;

public class GuideActivity extends FragmentActivity {

	private ViewPager mViewPager;
    private CirclePageIndicator mCirclePageIndicator;
    private Button btn_login;
    private ImageView[] ivList;
    
    private int[] imgids={R.drawable.guide_background1,R.drawable.guide_background2,R.drawable.guide_background3,R.drawable.guide_background4};
    
    
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_guide);
		
		ivList = new ImageView[4];
		
		btn_login=(Button)findViewById(R.id.btn_login);
		mViewPager = (ViewPager)findViewById(R.id.viewPager);
	    mCirclePageIndicator = (CirclePageIndicator)findViewById(R.id.circlepageIndicator);
	    
	    for(int i=0;i<4;i++){
	    	ImageView iv=new ImageView(this);
	    	iv.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT));
	    	iv.setImageResource(imgids[i]);
	    	iv.setScaleType(ScaleType.CENTER_CROP);
	    	ivList[i]=iv;
	    }
	    
	    PagerAdapter imgAdapter = new ImagePagerAdapter(ivList);
	    mViewPager.setAdapter(imgAdapter);
        mCirclePageIndicator.setViewPager(mViewPager);
        
        
        initEvent();
        
	}

	private void initEvent(){
		
		mCirclePageIndicator.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				arg0=arg0+1;
				if(arg0==ivList.length){
					btn_login.setVisibility(View.VISIBLE);
				}
				else{
					btn_login.setVisibility(View.GONE);
				}
				
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		btn_login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Tools.openActivity(GuideActivity.this, MainActivity.class);
				finish();
			}
		});
	}
	
	
	
	
	public class ImagePagerAdapter extends PagerAdapter {
	    private ImageView[] imageViewList;

	    public ImagePagerAdapter(ImageView[] ivList) {
	        this.imageViewList = ivList;
	    }

	    @Override
	    public int getCount() {
	        return imageViewList.length;
	    }

	    @Override
	    public boolean isViewFromObject(View arg0, Object arg1) {
	        return arg0 == arg1;
	    }

	    @Override
	    public int getItemPosition(Object object) {
	        return super.getItemPosition(object);
	    }

	    @Override
	    public void destroyItem(View arg0, int arg1, Object arg2) {
	        ((ViewPager) arg0).removeView(imageViewList[arg1]);
	    }

	    @Override
	    public Object instantiateItem(View arg0, int arg1) {
	        ((ViewPager) arg0).addView(imageViewList[arg1]);
	        return imageViewList[arg1];
	    }
	}
	
}
