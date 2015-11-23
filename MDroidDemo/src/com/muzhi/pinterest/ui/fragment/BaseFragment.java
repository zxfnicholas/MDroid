package com.muzhi.pinterest.ui.fragment;




import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;


public class BaseFragment extends Fragment{

	protected Context mContext;
	
	protected boolean isVisible;
	private AssetManager mgr;

	protected int page=1;
	protected int count=20;//最大不超过100，默认为20，可不传
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.mContext = getActivity();
		mgr = mContext.getAssets();
	}

    
	
}
