package com.muzhi.pinterest.datas;


import com.lidroid.xutils.http.RequestParams;
import com.muzhi.pinterest.utils.RequestUtil;

import android.os.Handler;

public class ImageData {
	
	
	/**
	 * 获取图片列表
	 * @param url
	 * @param params
	 * @param mHandler
	 * @param handleCode
	 */
	public static void getImageList(int pageindex, Handler mHandler, int handleCode){
		
		String url = "http://www.duitang.com/album/62572137/masn/p/" + pageindex + "/24/";
		
		RequestParams params=new RequestParams();
		params.addBodyParameter("pageindex",String.valueOf(pageindex));
		RequestUtil.request(url, params, mHandler, handleCode);
		
	}
	
	

}
