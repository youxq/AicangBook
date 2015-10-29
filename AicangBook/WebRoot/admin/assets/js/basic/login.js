$(document).ready(
		function() {
			// 给登录按钮绑定click方法
			$('#loginBtn').click(function() {
				// 获取用户输入的用户名/邮箱、密码和验证码
				var userName = $("input[name='loginName']").val();
				var loginPass = $('input[name="loginPass"]').val();

				// 如果用户名 密码 和验证码有一个没有填，则不采取任何操作
				if (userName == "" || loginPass == "") {
					alert("用户名或密码不能为空！");
				} else {
					// 提交用户名和密码
					login(userName, loginPass);
				}
			});

			function login(userName, password) {

				// 参数 用户名 密码 验证码
				var data = {
					"userName" : userName,
					"userPassword" : password
				};
				ajaxLoginCheck("post",
						"/AicangBook/UserManage/UserLogin", data,
						login_callback);
			}

			function ajaxLoginCheck(method, remote_url, send_data, callback) {

				$.ajax({

					type : method,
					url : remote_url,
					data : send_data,
					dataType : 'json',
					success : function(result) {
						if (result == "" || result == null) {
							alert("后台出错，请重试！");
						} else {
							callback(result); // 成功响应后调用
						}
					},
					error : function(xhr, textStatus, errorThrown) {
						alert("网络错误，请重试！");
					}
				});
			}

			function login_callback(result) {
				// 登录成功
				if (result.result == "loginSuccess") {
					location.href = "/AicangBook/admin/index.jsp";
				} else if (result.result == "loginError") {
					alert("用户名或密码错误！");
				}
			}

		});
