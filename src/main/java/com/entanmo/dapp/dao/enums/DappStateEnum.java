package com.entanmo.dapp.dao.enums;

public enum DappStateEnum {

	CREATED(0, "已创建"), REGISTERED(1, "已注册"), INSTALLED(2, "已安装"), LAUNCHED(3, "已启动"), STOPED(4, "已停止"), REMOVED(5, "已移除");

	private int type;
	private String description;

	private DappStateEnum(int type, String description) {
		this.type = type;
		this.description = description;
	}

	public static DappStateEnum getByType(int type) {
		for (DappStateEnum memberType : values()) {
			if (type == memberType.type) {
				return memberType;
			}
		}
		return null;
	}

	public String getDescription() {
		return description;
	}

	public int getType() {
		return type;
	}

}
