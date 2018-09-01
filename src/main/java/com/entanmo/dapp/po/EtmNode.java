package com.entanmo.dapp.po;

import java.io.Serializable;

/**
 * 
 * 
 * 
 */
@SuppressWarnings("serial")
public class EtmNode implements Serializable {

	/**
	 * 
	 */
	private Integer nodeId;

	/**
	 * 
	 */
	private Integer ownerId;
	
	private String owner;

	/**
	 * 
	 */
	private String ip;

	/**
	 * 
	 */
	private Integer port;

	/**
	 * 
	 */
	private String masterPassword;

	/**
	 * 
	 */
	private String os;

	/**
	 * (0: offline, 1:online, )
	 */
	private Integer state;

	/**
	 * 
	 */
	private java.util.Date createTime;

	/**
	 * 
	 */
	private java.util.Date updateTime;

	public void setNodeId(Integer nodeId) {
		this.nodeId = nodeId;
	}

	public Integer getNodeId() {
		return this.nodeId;
	}

	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}

	public Integer getOwnerId() {
		return this.ownerId;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getIp() {
		return this.ip;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public Integer getPort() {
		return this.port;
	}

	public void setMasterPassword(String masterPassword) {
		this.masterPassword = masterPassword;
	}

	public String getMasterPassword() {
		return this.masterPassword;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getOs() {
		return this.os;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getState() {
		return this.state;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public java.util.Date getCreateTime() {
		return this.createTime;
	}

	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}

	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}
}
