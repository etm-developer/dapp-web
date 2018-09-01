package com.entanmo.dapp.dao.enums;

public enum ResponseEnum {

	SUCCESS(20000, "成功"), 
	
	LOGIC_ERROR(40001, "逻辑错误"),
	BAD_PARAM(40002, "参数错误"),
	
	NOT_LOGIN(50001, "请先登录"),	
	NO_RIGHTS(50002, "没有权限"),	
	TOKEN_INVALID(50008, "非法的token"), 
	TOKEN_LOGIN_ELSEWHERE(50012, "其他客户端登录了"),		
	TOKEN_TIMEOUT(50012, "Token 过期了");
	
	

	private int code;
	private String message;

	private ResponseEnum(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public static ResponseEnum getByCode(int code) {
		for (ResponseEnum memberType : values()) {
			if (code == memberType.code) {
				return memberType;
			}
		}
		return null;
	}

	public String getMessage() {
		return message;
	}

	public int getCode() {
		return code;
	}

}
