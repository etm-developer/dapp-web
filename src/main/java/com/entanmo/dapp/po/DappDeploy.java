package com.entanmo.dapp.po;

import java.io.Serializable;

/**
 * 
 * 
 * 
 */
@SuppressWarnings("serial")
public class DappDeploy implements Serializable {

	/**
	 * 
	 */
	private Integer deployId;

	/**
	 * 
	 */
	private String transactionId;

	/**
	 * 
	 */
	private Integer nodeId;

	private Integer ownerId; // 节点的所有者， 这个字段是冗余的

	/**
	 * 
	 */
	private Integer state;

	/**
	 * 
	 */
	private java.util.Date createTime;

	public void setDeployId(Integer deployId) {
		this.deployId = deployId;
	}

	public Integer getDeployId() {
		return this.deployId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getTransactionId() {
		return this.transactionId;
	}

	public void setNodeId(Integer nodeId) {
		this.nodeId = nodeId;
	}

	public Integer getNodeId() {
		return this.nodeId;
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

	public Integer getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}
}
