package com.muzhi.pinterest.widget;


import com.muzhi.pinterest.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 封装ActionBar
 *
 * @author Jazzy
 */

@SuppressLint("NewApi")
public class MActionBar extends LinearLayout{

    
    private Context mContext;
    private View mView;
    
    private TextView actiobar_title;
    private TextView back_title;		//返回按钮
    private ImageView back_icon;		//返回图标
    
    public MActionBar(Context context) {
        super(context);
        init(context);
    }

    public MActionBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    
	public MActionBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        int rid=context.getResources().getIdentifier("actionbar", "layout", context.getPackageName());
        mView = View.inflate(context,rid, null);
        this.addView(mView);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initData();
        initWidget();
    }

    
    
    
    //**************************************** 标题相关 *************************************************//
    
    private void instanseTitle(){
    	if(actiobar_title==null){
    		actiobar_title=(TextView)mView.findViewById(R.id.actionbar_title);
    		actiobar_title.setClickable(true);
    	}
    }
    
    public void setTitle(String title){
    	instanseTitle();
    	actiobar_title.setText(title);
    	actiobar_title.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				((Activity)mContext).onBackPressed();
			}
		});
    }
    public void setTitle(String title,OnClickListener click_call_back){
    	instanseTitle();
    	setTitle(title);
    	actiobar_title.setOnClickListener(click_call_back);
    }
    
    public void setTitleOnClick(OnClickListener click_call_back){
    	instanseTitle();
    	actiobar_title.setOnClickListener(click_call_back);
    }
    
    
    
  //**************************************** 左侧按钮相关 *************************************************//
    
    private void instanseBack(){
    	if(back_title==null){
    		back_title=(TextView)mView.findViewById(R.id.title_txv_left_text);
    		back_title.setClickable(true);    		
    	}
    }
    
    public void setBackTitle(String title){
    	instanseBack();
    	back_title.setVisibility(View.VISIBLE);
    	if(back_icon!=null){
    		back_icon.setVisibility(View.GONE);
    	}
    	back_title.setText(title);
    }
    public void setBackTitle(String title,OnClickListener click_call_back){
    	instanseBack();
    	setBackTitle(title);
    	back_title.setOnClickListener(click_call_back);
    }
    
    public void setBackOnClick(OnClickListener click_call_back){
    	instanseBack();
    	back_title.setOnClickListener(click_call_back);
    }
    
    
    private void instanseBackIcon(){
    	if(back_icon==null){
    		back_icon=(ImageView)mView.findViewById(R.id.title_imgv_left_icon);
    		back_icon.setClickable(true);
    		back_icon.setVisibility(View.VISIBLE);
    	}
    }
    
    public void setBackIcon(int rid){
    	instanseBackIcon();
    	back_icon.setImageResource(rid);
    }
    public void setBackIcon(int rid,OnClickListener click_call_back){
    	instanseBackIcon();
    	setBackIcon(rid);
    	back_title.setOnClickListener(click_call_back);
    }
    
   
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    private void initData() {
        
    }

    private void initWidget() {
       
    }

   
}
