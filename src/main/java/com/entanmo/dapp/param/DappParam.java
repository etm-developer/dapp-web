package com.entanmo.dapp.param;

/**
 * 
 * 参数
 * 
 */
public class DappParam extends BaseParam {

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

	private String transactionIdFuzzy;

	/**
	 * 
	 */
	private String name;

	private String nameFuzzy;

	/**
	 * 
	 */
	private Integer category;

	/**
	 * 
	 */
	private String description;

	private String descriptionFuzzy;

	/**
	 * 
	 */
	private String link;

	private String linkFuzzy;

	/**
	 * 
	 */
	private String delegates;

	private String delegatesFuzzy;

	/**
	 * 
	 */
	private String secrets;

	private String secretsFuzzy;

	/**
	 * 
	 */
	private Integer unlockDelegates;

	/**
	 * 0:created, 1: registered, 2:installed, 3:launched, 4:stoped
	 */
	private Integer state;

	private String tags;
	private String icon;

	/**
	 * 
	 */
	private java.util.Date createTime;

	private String createTimeStart;

	private String createTimeEnd;

	/**
	 * 
	 */
	private java.util.Date updateTime;

	private String updateTimeStart;

	private String updateTimeEnd;

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

	public void setTransactionIdFuzzy(String transactionIdFuzzy) {
		this.transactionIdFuzzy = transactionIdFuzzy;
	}

	public String getTransactionIdFuzzy() {
		return this.transactionIdFuzzy;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setNameFuzzy(String nameFuzzy) {
		this.nameFuzzy = nameFuzzy;
	}

	public String getNameFuzzy() {
		return this.nameFuzzy;
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

	public void setDescriptionFuzzy(String descriptionFuzzy) {
		this.descriptionFuzzy = descriptionFuzzy;
	}

	public String getDescriptionFuzzy() {
		return this.descriptionFuzzy;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getLink() {
		return this.link;
	}

	public void setLinkFuzzy(String linkFuzzy) {
		this.linkFuzzy = linkFuzzy;
	}

	public String getLinkFuzzy() {
		return this.linkFuzzy;
	}

	public void setDelegates(String delegates) {
		this.delegates = delegates;
	}

	public String getDelegates() {
		return this.delegates;
	}

	public void setDelegatesFuzzy(String delegatesFuzzy) {
		this.delegatesFuzzy = delegatesFuzzy;
	}

	public String getDelegatesFuzzy() {
		return this.delegatesFuzzy;
	}

	public void setSecrets(String secrets) {
		this.secrets = secrets;
	}

	public String getSecrets() {
		return this.secrets;
	}

	public void setSecretsFuzzy(String secretsFuzzy) {
		this.secretsFuzzy = secretsFuzzy;
	}

	public String getSecretsFuzzy() {
		return this.secretsFuzzy;
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

	public void setCreateTimeStart(String createTimeStart) {
		this.createTimeStart = createTimeStart;
	}

	public String getCreateTimeStart() {
		return this.createTimeStart;
	}

	public void setCreateTimeEnd(String createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

	public String getCreateTimeEnd() {
		return this.createTimeEnd;
	}

	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}

	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTimeStart(String updateTimeStart) {
		this.updateTimeStart = updateTimeStart;
	}

	public String getUpdateTimeStart() {
		return this.updateTimeStart;
	}

	public void setUpdateTimeEnd(String updateTimeEnd) {
		this.updateTimeEnd = updateTimeEnd;
	}

	public String getUpdateTimeEnd() {
		return this.updateTimeEnd;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
}
