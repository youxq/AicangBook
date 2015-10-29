<%@ page contentType="text/html;charset=utf-8" language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>后台登录</title>
		<meta name="description" content="User login page" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
		<!-- bootstrap & fontawesome -->
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/admin/assets/css/bootstrap.css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/admin/assets/css/font-awesome.css" />
		<!-- text fonts -->
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/admin/assets/css/ace-fonts.css" />
		<!-- ace styles -->
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/admin/assets/css/ace.css" />
		<!--[if lte IE 9]>
			<link rel="stylesheet" type="text/css" href="<%=basePath%>admin/assets/css/ace-part2.css" />
		<![endif]-->
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/admin/assets/css/ace-rtl.css" />
		<!--[if lte IE 9]>
		  <link rel="stylesheet" type="text/css" href="<%=basePath%>admin/assets/css/ace-ie.css" />
		<![endif]-->
		<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
		<!--[if lt IE 9]>
		<script type="text/javascript" src=<%=basePath%>admin/assets/js/html5shiv.js"></script>
		<script type="text/javascript" src="<%=basePath%>admin/js/respond.js"></script>
		<![endif]-->
		
		
	</head>
	<body class="login-layout">
		<div class="main-container">
			<div class="main-content">
				<div class="row">
					<div class="col-sm-10 col-sm-offset-1">
						<div class="login-container">
							<div class="center">
								<h1>
									<i class="ace-icon fa fa-leaf green"></i>
									<span class="red">爱心仓储</span>
									<span class="white" id="id-text2">后台管理系统</span>
								</h1>
								<h4 class="blue" id="id-company-text">&copy; 爱心仓储项目部</h4>
							</div>

							<div class="space-6"></div>

							<div class="position-relative">
								<div id="login-box" class="login-box visible widget-box no-border">
									<div class="widget-body">
										<div class="widget-main">
											<h4 class="header blue lighter bigger">
												<i class="ace-icon fa fa-coffee green"></i>
												输入账号与密码
											</h4>

											<div class="space-6"></div>

											<form>
												<fieldset>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text" class="form-control" placeholder="用户名" name='loginName' />
															<i class="ace-icon fa fa-user"></i>
														</span>
													</label>

													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" class="form-control" placeholder="密码" name='loginPass'/>
															<i class="ace-icon fa fa-lock"></i>
														</span>
													</label>

													<div class="space"></div>

													<div class="clearfix" >
														<button type="button" class="center-block width-100 btn btn-sm btn-primary" id="loginBtn">
															<i class="ace-icon fa fa-key"></i>
															<span class="bigger-110" >登录</span>
														</button>
													</div>
													<div class="space-4"></div>
												</fieldset>
											</form>
										</div>
									</div>
								</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

		<script type="text/javascript" src="<%=basePath%>admin/assets/js/jquery.js"></script>
		<script type="text/javascript">
			jQuery(function($) {
				$('body').attr('class', 'login-layout light-login');
				$('#id-text2').attr('class', 'grey');
				$('#id-company-text').attr('class', 'blue');		 
			});
		</script>
		<script src="<%=basePath%>admin/assets/js/basic/login.js"></script>
		
	</body>
</html>

