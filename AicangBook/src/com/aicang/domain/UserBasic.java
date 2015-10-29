package com.aicang.domain;

/**
 * UserBasic entity. @author MyEclipse Persistence Tools
 */
public class UserBasic extends AbstractUserBasic implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public UserBasic() {
	}

	/** minimal constructor */
	public UserBasic(String userName, String password) {
		super(userName, password);
	}

	/** full constructor */
	public UserBasic(String userName, String password, String userType,
			String userQq, String userPhone, String userEmail,
			String userBirthday, String remark) {
		super(userName, password, userType, userQq, userPhone, userEmail,
				userBirthday, remark);
	}

}
