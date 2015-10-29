package com.aicang.domain;

/**
 * AbstractUserBasic entity provides the base persistence definition of the
 * UserBasic entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractUserBasic implements java.io.Serializable {

	// Fields

	private Integer id;
	private String userName;
	private String password;
	private String userType;
	private String userQq;
	private String userPhone;
	private String userEmail;
	private String userBirthday;
	private String remark;

	// Constructors

	/** default constructor */
	public AbstractUserBasic() {
	}

	/** minimal constructor */
	public AbstractUserBasic(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	/** full constructor */
	public AbstractUserBasic(String userName, String password, String userType,
			String userQq, String userPhone, String userEmail,
			String userBirthday, String remark) {
		this.userName = userName;
		this.password = password;
		this.userType = userType;
		this.userQq = userQq;
		this.userPhone = userPhone;
		this.userEmail = userEmail;
		this.userBirthday = userBirthday;
		this.remark = remark;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserType() {
		return this.userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserQq() {
		return this.userQq;
	}

	public void setUserQq(String userQq) {
		this.userQq = userQq;
	}

	public String getUserPhone() {
		return this.userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserEmail() {
		return this.userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserBirthday() {
		return this.userBirthday;
	}

	public void setUserBirthday(String userBirthday) {
		this.userBirthday = userBirthday;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}