package com.entanmo.dapp.po;

import java.io.Serializable;

import org.json.JSONObject;

/**
 * 
 * 
 * 
 */
@SuppressWarnings("serial")
public class Dapp implements Serializable {

	/**
	 * 
	 */
	private Integer dappId;

	/**
	 * 
	 */
	private Integer creatorId;

	/**
	 * 
	 */
	private String transactionId;

	/**
	 * 
	 */
	private String name;

	/**
	 * 
	 */
	private Integer category;

	/**
	 * 
	 */
	private String description;

	/**
	 * 
	 */
	private String link;

	/**
	 * 
	 */
	private String delegates;

	/**
	 * 
	 */
	private String secrets;

	/**
	 * 
	 */
	private Integer unlockDelegates;

	/**
	 * 0:created, 1: registered, 2:installed, 3:launched, 4:stoped
	 */
	private Integer state;
	
	private String icon;
	
	private String tags;
	
	private Integer type;

	/**
	 * 
	 */
	private java.util.Date createTime;

	/**
	 * 
	 */
	private java.util.Date updateTime;
	
	private String owner;

	public void setDappId(Integer dappId) {
		this.dappId = dappId;
	}

	public Integer getDappId() {
		return this.dappId;
	}

	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}

	public Integer getCreatorId() {
		return this.creatorId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getTransactionId() {
		return this.transactionId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public Integer getCategory() {
		return this.category;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getLink() {
		return this.link;
	}

	public void setDelegates(String delegates) {
		this.delegates = delegates;
	}

	public String getDelegates() {
		return this.delegates;
	}

	public void setSecrets(String secrets) {
		this.secrets = secrets;
	}

	public String getSecrets() {
		return this.secrets;
	}

	public void setUnlockDelegates(Integer unlockDelegates) {
		this.unlockDelegates = unlockDelegates;
	}

	public Integer getUnlockDelegates() {
		return this.unlockDelegates;
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

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public void parseFromJson(JSONObject json){
		this.name = json.getString("name");
		this.link = json.getString("link");
		this.category = json.getInt("category");
		this.description = json.getString("description");
		this.tags = json.getString("tags");
		this.icon = json.getString("icon");
		this.unlockDelegates = json.getInt("unlockDelegates");
		this.type = json.getInt("type");
		this.delegates = json.getJSONArray("delegates").toString();
	}
}
