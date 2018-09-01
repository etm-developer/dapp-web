package com.entanmo.dapp.param;

/**
 * 
 * 参数
 * 
 */
public class MemberParam extends BaseParam {

	/**
	 * 
	 */
	private Integer memberId;

	/**
	 * 
	 */
	private String account;

	private String accountFuzzy;

	/**
	 * 
	 */
	private String password;

	private String passwordFuzzy;

	/**
	 * 
	 */
	private String phone;

	private String phoneFuzzy;

	/**
	 * 
	 */
	private String email;

	private String emailFuzzy;

	/**
	 * 
	 */
	private Integer userType;

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
	private java.util.Date lastLogin;

	private String lastLoginStart;

	private String lastLoginEnd;

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public Integer getMemberId() {
		return this.memberId;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getAccount() {
		return this.account;
	}

	public void setAccountFuzzy(String accountFuzzy) {
		this.accountFuzzy = accountFuzzy;
	}

	public String getAccountFuzzy() {
		return this.accountFuzzy;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPasswordFuzzy(String passwordFuzzy) {
		this.passwordFuzzy = passwordFuzzy;
	}

	public String getPasswordFuzzy() {
		return this.passwordFuzzy;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhoneFuzzy(String phoneFuzzy) {
		this.phoneFuzzy = phoneFuzzy;
	}

	public String getPhoneFuzzy() {
		return this.phoneFuzzy;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmailFuzzy(String emailFuzzy) {
		this.emailFuzzy = emailFuzzy;
	}

	public String getEmailFuzzy() {
		return this.emailFuzzy;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Integer getUserType() {
		return this.userType;
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

	public void setLastLogin(java.util.Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public java.util.Date getLastLogin() {
		return this.lastLogin;
	}

	public void setLastLoginStart(String lastLoginStart) {
		this.lastLoginStart = lastLoginStart;
	}

	public String getLastLoginStart() {
		return this.lastLoginStart;
	}

	public void setLastLoginEnd(String lastLoginEnd) {
		this.lastLoginEnd = lastLoginEnd;
	}

	public String getLastLoginEnd() {
		return this.lastLoginEnd;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

}
