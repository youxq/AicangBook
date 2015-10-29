package com.aicang.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aicang.domain.UserBasic;
import com.aicang.domain.UserBasicDAO;

@Component("UserService")
public class UserService {

	@Autowired
	private UserBasicDAO userBasicDAO;
	
	/**
	 * 用户登录验证，不存在为1，密码错误为2，正确为0
	 * @param userName
	 * @param userPassword
	 * @return
	 */
	public UserBasic loginCheck(String userName,String userPassword){
		
		List<UserBasic> userList = null;
		if((userList=userBasicDAO.findByUserName(userName)) != null){
			for(UserBasic user : userList){
				if(user.getPassword().equals(userPassword)){
					return user;
				}
			}
			return null;
		} else {
			return null;
		}
	}
	
	
}
