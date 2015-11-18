package com.muzhi.pinterest;

import java.util.ArrayList;

import com.muzhi.mdroid.views.PagerSlidingTabStrip;
import com.muzhi.pinterest.adapter.MyFragmentPagerAdapter;
import com.muzhi.pinterest.fragment.FragmentFeeds;
import com.muzhi.pinterest.fragment.FragmentFeeds2;
import com.muzhi.pinterest.widget.MActionBar;
import com.nineoldandroids.view.ViewHelper;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.graphics.Color;
import android.os.Bundle;

public class MainActivity extends BaseActivity {

	
	private MyFragmentPagerAdapter fragmentAdapter;
	private ArrayList<Fragment> fragments;
	private DrawerLayout mDrawerLayout;
	
	private PagerSlidingTabStrip mpager_tab_strip;
	private ViewPager mViewPager;
	private MActionBar mbar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		setSwipeBackEnable(false); //禁止右滑关闭
		
		this.mActionBar=(MActionBar)findViewById(R.id.actiobar);
		this.mActionBar.setTitle("Mdroid");
		this.mActionBar.setBackTitle("菜单",new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				OpenLeftMenu();
			}
		});
		
		mDrawerLayout = (DrawerLayout) findViewById(R.id.id_drawerLayout);
		
		
		mpager_tab_strip=(PagerSlidingTabStrip)findViewById(R.id.pager_tab_strip);
		
		mViewPager=(ViewPager)findViewById(R.id.pager);

		fragments = new ArrayList<Fragment>();	
		
		FragmentFeeds mHomeFeedsFragment1=FragmentFeeds.newInstance(1);
		FragmentFeeds2 mHomeFeedsFragment2=FragmentFeeds2.newInstance(1);
		
		fragments.add(mHomeFeedsFragment1);
		fragments.add(mHomeFeedsFragment2);
		
		
		fragmentAdapter=new MyFragmentPagerAdapter(getSupportFragmentManager(),fragments,new String[]{"最新","热门"});
		mViewPager.setAdapter(fragmentAdapter);		
		mpager_tab_strip.setViewPager(mViewPager);
		mViewPager.setOffscreenPageLimit(1);
		mpager_tab_strip.setSelectedTextColorResource(R.color.action_bar);
		
		initEvents();
	}
	
	public void OpenLeftMenu(){
		mDrawerLayout.openDrawer(Gravity.LEFT);
	}
	public void OpenRightMenu(){
		mDrawerLayout.openDrawer(Gravity.RIGHT);
	}
	private void initEvents(){
		mDrawerLayout.setDrawerListener(new DrawerListener(){
			@Override
			public void onDrawerStateChanged(int newState){
			}

			@Override
			public void onDrawerSlide(View drawerView, float slideOffset){
				View mContent = mDrawerLayout.getChildAt(0);
				View mMenu = drawerView;
				float scale = 1 - slideOffset;
				float rightScale = 0.8f + scale * 0.2f;

				if (drawerView.getTag().equals("LEFT")){

					float leftScale = 1 - 0.3f * scale;

					ViewHelper.setScaleX(mMenu, leftScale);
					ViewHelper.setScaleY(mMenu, leftScale);
					ViewHelper.setAlpha(mMenu, 0.6f + 0.4f * (1 - scale));
					ViewHelper.setTranslationX(mContent,mMenu.getMeasuredWidth() * (1 - scale));
					ViewHelper.setPivotX(mContent, 0);
					ViewHelper.setPivotY(mContent,mContent.getMeasuredHeight() / 2);
					mContent.invalidate();
					//以下代码是仿QQ效果
					ViewHelper.setScaleX(mContent, rightScale);
					ViewHelper.setScaleY(mContent, rightScale);
				} 
				else{
					ViewHelper.setTranslationX(mContent,-mMenu.getMeasuredWidth() * slideOffset);
					ViewHelper.setPivotX(mContent, mContent.getMeasuredWidth());
					ViewHelper.setPivotY(mContent,mContent.getMeasuredHeight() / 2);
					mContent.invalidate();
					//以下代码是仿QQ效果
					ViewHelper.setScaleX(mContent, rightScale);
					ViewHelper.setScaleY(mContent, rightScale);
				}

			}

			@Override
			public void onDrawerOpened(View drawerView){
				if (drawerView.getTag().equals("LEFT")){
					mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.RIGHT);
				}
				else{
					mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.LEFT);
				}
			}

			@Override
			public void onDrawerClosed(View drawerView){
				mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
			}
		});
	}

	
}
