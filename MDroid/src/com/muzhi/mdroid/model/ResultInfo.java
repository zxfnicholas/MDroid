package com.muzhi.mdroid.model;

public class ResultInfo {

	/*** HTTP Handle Code ***/
	public final static int SUCCESS_CODE = 0;	//成功
	public final static int LOADING_CODE = 1;	//正在载入
	public final static int FAIL_CODE = 2;	//失败
	
	
	private int code;
	private String data;
	private String message;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
