package com.entanmo.dapp.dao.enums;

public enum EtmNodeStateEnum {

	offline(0, "不可用"), online(1, "可用");

	private int type;
	private String description;

	private EtmNodeStateEnum(int type, String description) {
		this.type = type;
		this.description = description;
	}

	public static EtmNodeStateEnum getByType(int type) {
		for (EtmNodeStateEnum memberType : values()) {
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
