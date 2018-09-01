package com.entanmo.dapp.vo;

import com.entanmo.dapp.dao.enums.ResponseEnum;

public class BaseResponse {
	private int code = ResponseEnum.SUCCESS.getCode();
	private String message;

	public Object data;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setResp(ResponseEnum resp) {
		code = resp.getCode();
		message = resp.getMessage();
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}
