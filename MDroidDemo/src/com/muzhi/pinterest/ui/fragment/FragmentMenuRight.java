package com.muzhi.pinterest.ui.fragment;

import com.muzhi.pinterest.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentMenuRight extends Fragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
		return inflater.inflate(R.layout.fragment_layout_menu_right, container, false);
	}
}
