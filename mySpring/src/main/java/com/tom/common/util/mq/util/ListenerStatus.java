package com.tom.common.util.mq.util;

public enum ListenerStatus {
	
	isExit(1,"监听已经存在"),success(0,"设置成功");
	
	private int code;
	private String msg;

	private ListenerStatus(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	public int getCode(){
		return code;
	}
	public String getMsg(){
		return msg;
	}
}
