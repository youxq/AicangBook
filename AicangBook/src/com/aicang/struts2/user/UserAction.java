package com.aicang.struts2.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.aicang.domain.UserBasic;
import com.aicang.service.user.UserService;
import com.aicang.struts2.book.BaseAction;
import com.opensymphony.xwork2.ActionSupport;

@Namespace("UserManage")
public class UserAction extends ActionSupport {

	// 保存从http请求中传来的参数
	private String userName = null;
	private String userPassword = null;

	@Autowired
	private UserService userService;

	// 用户登录action
	@Action(value = "/UserLogin")
	public void userLogin() {

		Map map = new HashMap();

		try {

			if (userName == null || userPassword == null) {
				map.put("result", "loginError");
			} else {
				
				HttpSession session = BaseAction.getSession();
				UserBasic user = userService.loginCheck(userName, userPassword);
				if (user== null) {
					map.put("result", "loginError");
				} else {
					session.setAttribute("user", user.getUserName());
					map.put("result", "loginSuccess");
				}
			}

		} catch (Exception e) {
			map.put("result", "loginError");
			e.printStackTrace();
		}

		// 回传数据到前台
		JSONObject json = JSONObject.fromObject(map);
		try {
			PrintWriter pw = BaseAction.getResponse().getWriter();
			pw.print(json);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	
	
}
