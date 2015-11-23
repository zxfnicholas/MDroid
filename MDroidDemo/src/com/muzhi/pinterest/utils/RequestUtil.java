package com.muzhi.pinterest.utils;


import android.os.Handler;
import android.os.Message;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.muzhi.mdroid.model.ResultInfo;
import com.muzhi.mdroid.tools.L;
import com.muzhi.pinterest.AppData;

/**
 * http请求工具
 */
public class RequestUtil {

	
	static HttpUtils httpUtils;
	
	private static class SingletonHolder {
		// 单例对象实例
		static final HttpUtils http = new HttpUtils();
		
	}

	public static HttpUtils getInstance() {

		if(httpUtils==null){
			//httpUtils=SingletonHolder.http;
			httpUtils=new HttpUtils(50000);
			httpUtils.configCurrentHttpCacheExpiry(0);
		}
		return httpUtils;
		
		
	}

	/**
	 * 请求方法
	 * 
	 * @author zengxiaofeng
	 * @param interfaceName
	 * @param properties
	 *            没有参数时传null
	 * @param mHandler
	 * @param handleCode
	 */
	public static void request(String url, RequestParams params, Handler mHandler, int handleCode) {
		
		HttpRequest.HttpMethod method=HttpMethod.GET;
		request(method,url, params, mHandler, handleCode);
	}

	
	/**
	 * 
	 * @param method
	 * @param url
	 * @param params
	 * @param mHandler
	 * @param handleCode
	 */
	public static void request(HttpRequest.HttpMethod method,String url, RequestParams params, final Handler mHandler, final int handleCode) {
		
		getInstance().send(method,url, params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException error, String errmsg) {
				L.e(error.getExceptionCode() + ":" + errmsg);
				
				ResultInfo info=new ResultInfo();
				info.setCode(ResultInfo.FAIL_CODE);
				info.setMessage(errmsg);
				
				Message msg = mHandler.obtainMessage();
				msg.what = handleCode;	
				msg.obj = AppData.getGson().toJson(info);
				mHandler.sendMessage(msg);
			}

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				Message msg = mHandler.obtainMessage();
				msg.what = handleCode;	
				msg.obj = responseInfo.result;				
				mHandler.sendMessage(msg);
				L.e(responseInfo.result);
			}
		});
		
	}
	
	
	
	/**
	 * 请求方法
	 * 
	 * @author zengxiaofeng
	 * @param interfaceName
	 * @param properties
	 *            没有参数时传null
	 * @param mHandler
	 * @param handleCode
	 */
	/*public static void request(String url, RequestParams params, final Handler mHandler, final int handleCode) {
				
		getInstance().send(HttpRequest.HttpMethod.GET,url, params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException error, String errmsg) {
				L.e(error.getExceptionCode() + ":" + errmsg);
				
				ResultInfo info=new ResultInfo();
				info.setCode(ResultInfo.FAIL_CODE);
				info.setMessage(errmsg);
				
				Message msg = mHandler.obtainMessage();
				msg.what = handleCode;	
				msg.obj = AppData.getGson().toJson(info);
				mHandler.sendMessage(msg);
			}

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				Message msg = mHandler.obtainMessage();
				msg.what = handleCode;	
				msg.obj = responseInfo.result;				
				mHandler.sendMessage(msg);
				L.e(responseInfo.result);
			}
		});
	}*/
	
	
	
	
	/**
	 * 启动一个自定义线程
	 * 
	 * @author zengxiaofeng
	 * @param runnable
	 */
	public static void startThreed(Runnable runnable) {
		
		if (null != runnable && !"".equals(runnable)) {
			new Thread(runnable).start();
		}
		
	}
	
}
