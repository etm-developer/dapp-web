package com.entanmo.dapp.param;



/**
 * 
 * 参数
 * 
 */
public class EtmNodeParam extends BaseParam{


	/**
	 * 
	 */
	private Integer nodeId;

	/**
	 * 
	 */
	private Integer ownerId;

	/**
	 * 
	 */
	private String ip;

	private String ipFuzzy;

	/**
	 * 
	 */
	private Integer port;

	/**
	 * 
	 */
	private String masterPassword;

	private String masterPasswordFuzzy;

	/**
	 * 
	 */
	private String os;

	private String osFuzzy;

	/**
	 * (0: offline, 1:online, )
	 */
	private Integer state;

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


	public void setNodeId(Integer nodeId){
		this.nodeId = nodeId;
	}

	public Integer getNodeId(){
		return this.nodeId;
	}

	public void setOwnerId(Integer ownerId){
		this.ownerId = ownerId;
	}

	public Integer getOwnerId(){
		return this.ownerId;
	}

	public void setIp(String ip){
		this.ip = ip;
	}

	public String getIp(){
		return this.ip;
	}

	public void setIpFuzzy(String ipFuzzy){
		this.ipFuzzy = ipFuzzy;
	}

	public String getIpFuzzy(){
		return this.ipFuzzy;
	}

	public void setPort(Integer port){
		this.port = port;
	}

	public Integer getPort(){
		return this.port;
	}

	public void setMasterPassword(String masterPassword){
		this.masterPassword = masterPassword;
	}

	public String getMasterPassword(){
		return this.masterPassword;
	}

	public void setMasterPasswordFuzzy(String masterPasswordFuzzy){
		this.masterPasswordFuzzy = masterPasswordFuzzy;
	}

	public String getMasterPasswordFuzzy(){
		return this.masterPasswordFuzzy;
	}

	public void setOs(String os){
		this.os = os;
	}

	public String getOs(){
		return this.os;
	}

	public void setOsFuzzy(String osFuzzy){
		this.osFuzzy = osFuzzy;
	}

	public String getOsFuzzy(){
		return this.osFuzzy;
	}

	public void setState(Integer state){
		this.state = state;
	}

	public Integer getState(){
		return this.state;
	}

	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}

	public java.util.Date getCreateTime(){
		return this.createTime;
	}

	public void setCreateTimeStart(String createTimeStart){
		this.createTimeStart = createTimeStart;
	}

	public String getCreateTimeStart(){
		return this.createTimeStart;
	}
	public void setCreateTimeEnd(String createTimeEnd){
		this.createTimeEnd = createTimeEnd;
	}

	public String getCreateTimeEnd(){
		return this.createTimeEnd;
	}

	public void setUpdateTime(java.util.Date updateTime){
		this.updateTime = updateTime;
	}

	public java.util.Date getUpdateTime(){
		return this.updateTime;
	}

	public void setUpdateTimeStart(String updateTimeStart){
		this.updateTimeStart = updateTimeStart;
	}

	public String getUpdateTimeStart(){
		return this.updateTimeStart;
	}
	public void setUpdateTimeEnd(String updateTimeEnd){
		this.updateTimeEnd = updateTimeEnd;
	}

	public String getUpdateTimeEnd(){
		return this.updateTimeEnd;
	}

	public java.util.Map<String, Object> toMap(){
		java.util.Map<String, Object> dataMap = new java.util.HashMap<String, Object>();
		dataMap.put("nodeId", this.nodeId);
		dataMap.put("ownerId", this.ownerId);
		dataMap.put("ip", this.ip);
		dataMap.put("ipFuzzy", this.ipFuzzy);
		dataMap.put("port", this.port);
		dataMap.put("masterPassword", this.masterPassword);
		dataMap.put("masterPasswordFuzzy", this.masterPasswordFuzzy);
		dataMap.put("os", this.os);
		dataMap.put("osFuzzy", this.osFuzzy);
		dataMap.put("state", this.state);
		dataMap.put("createTime", this.createTime);
		dataMap.put("createTimeStart", this.createTimeStart);
		dataMap.put("createTimeEnd", this.createTimeEnd);
		dataMap.put("updateTime", this.updateTime);
		dataMap.put("updateTimeStart", this.updateTimeStart);
		dataMap.put("updateTimeEnd", this.updateTimeEnd);
		return dataMap;
	}
}
