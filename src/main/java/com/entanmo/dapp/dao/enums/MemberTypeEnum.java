package com.entanmo.dapp.dao.enums;

public enum MemberTypeEnum {

	DEVELOPER(1, "开发者", new String[]{"developer"}), 
	PROVIDER(2, "节点提供者", new String[]{"provider"}), 
	ADMIN(3, "管理员", new String[]{"admin"});

	private int type;
	private String description;
	private String[] roles;

	private MemberTypeEnum(int type, String description, String[] roles) {
		this.type = type;
		this.description = description;
		this.roles = roles;
	}

	public static MemberTypeEnum getByType(int type) {
		for (MemberTypeEnum memberType : values()) {
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
	
	public String[] getRoles(){
		return roles;
	}

}
