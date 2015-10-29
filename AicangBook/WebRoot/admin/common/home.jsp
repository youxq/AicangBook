<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<%@ include file="commoncss.jsp"%>
</head>
<body class="no-skin">
	<div class="main-content-inner">
		<!-- #section:basics/content.breadcrumbs -->
		<div class="breadcrumbs" id="breadcrumbs" style="padding-top:5px">
			<script type="text/javascript">
				try {
					ace.settings.check('breadcrumbs', 'fixed')
				} catch (e) {
				}
			</script>
			<ul class="breadcrumb">
				<li class="active">后台管理</li>
			</ul>
			<!-- /.breadcrumb -->
		</div>
		<!-- /section:basics/content.breadcrumbs -->
		<div class="page-content">
			<!-- /section:settings.box -->
			<h1 style="text-align:center">欢迎使用爱心仓储后台管理系统</h1>
			


		</div>
		<!-- /.main-content -->
		<%@ include file="commonjs.jsp"%>
</body>
</html>
