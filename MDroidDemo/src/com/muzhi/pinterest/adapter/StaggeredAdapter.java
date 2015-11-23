package com.muzhi.pinterest.adapter;

import com.muzhi.mdroid.adapter.CommonListAdapter;
import com.muzhi.mdroid.tools.ScreenUtils;
import com.muzhi.mdroid.tools.ViewHolder;
import com.muzhi.mdroid.views.ScaleImageView;
import com.muzhi.pinterest.AppData;
import com.muzhi.pinterest.R;
import com.muzhi.pinterest.model.DuitangInfo;
import com.muzhi.pinterest.ui.activity.DetailActivity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

public class StaggeredAdapter extends CommonListAdapter<DuitangInfo> {

	
    public StaggeredAdapter(Context context) {
    	super(context);
    	this.mLayoutId=R.layout.infos_list;
    }


	@Override
	public void getCommonView(ViewHolder holder, DuitangInfo item,int position) {
		// TODO Auto-generated method stub
		
		holder.setText(R.id.news_title, item.getMsg());
		
		int w=200;
	    int h=200;
	    
	    //int w=(int)ScreenUtils.px2dp(this.mContext, t.getWidth());
	    //int h=(int)ScreenUtils.px2dp(this.mContext, t.getHeight());
	    
	    ScaleImageView img=holder.getView(R.id.news_pic);
	    img.setImageWidth(w);
	    img.setImageHeight(h);
	    AppData.imageLoader.displayImage(item.getIsrc(),img);
	    
	    holder.getConvertView().setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				mContext.startActivity(new Intent(mContext, DetailActivity.class));
			}
		});
	        
	}
    
}