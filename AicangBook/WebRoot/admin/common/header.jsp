<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript"
	src="<%=basePath%>admin/assets/js/jquery.js"></script>
<div id="navbar" class="navbar navbar-default">
			<script type="text/javascript">
				try{ace.settings.check('navbar' , 'fixed')}catch(e){}
			</script>

			<div class="navbar-container" id="navbar-container">
				<!-- #section:basics/sidebar.mobile.toggle -->
				<button type="button" class="navbar-toggle menu-toggler pull-left" id="menu-toggler" data-target="#sidebar">
					<span class="sr-only">Toggle sidebar</span>

					<span class="icon-bar"></span>

					<span class="icon-bar"></span>

					<span class="icon-bar"></span>
				</button>

				<!-- /section:basics/sidebar.mobile.toggle -->
				<div class="navbar-header pull-left">
					<!-- #section:basics/navbar.layout.brand -->
					<a href="#" class="navbar-brand">
						<small>
							<i class="fa fa-leaf"></i>
							爱心仓储
						</small>
					</a>
				</div>

				

				<!-- /section:basics/navbar.dropdown -->
			</div><!-- /.navbar-container -->
		</div>
		<script type="text/javascript">
		function logout() {

	ajaxLogout("post", "/Questionaire/userInfoManage/userLogout1", null, logoutcallback);

}
function logoutcallback(result){
		
		if(result.code==1){

			//页面跳转
		    location.href="/Questionaire/admin/login.jsp";
			
		}else{
			
			alert("后台出错，请重试！");
			
		}}
	function ajaxLogout(method, remote_url, send_data, callback) {
	
	$.ajax({
	
		type: method,
		url: remote_url,
	    data: send_data,
		dataType: 'json',
		success: function(result) {
			if (result == "" || result == null) {
				alert("后台出错，请重试！");
			} else {
				callback(result); //成功响应后调用
			}},
			error: function(xhr, textStatus, errorThrown) {
				alert("网络错误，请重试！");
			}
		});	}


</script>
