<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<%@taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>用户管理</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!-- bootstrap & fontawesome -->
<link rel="stylesheet"
	href="<%=basePath%>admin/assets/css/bootstrap.css" />
<link rel="stylesheet"
	href="<%=basePath%>admin/assets/css/font-awesome.css" />

<!-- page specific plugin styles -->

<!-- text fonts -->
<link rel="stylesheet"
	href="<%=basePath%>admin/assets/css/ace-fonts.css" />

<!-- ace styles -->
<link rel="stylesheet" href="<%=basePath%>admin/assets/css/ace.css" />

<link rel="stylesheet"
	href="<%=basePath%>admin/assets/css/jquery.dataTables.min.css" />

<link rel="stylesheet"
	href="<%=basePath%>admin/assets/css/book_admin.css" />



<script type="text/javascript"
	src="<%=basePath%>admin/assets/js/jquery.js"></script>

<script type="text/javascript"
	src="<%=basePath%>admin/assets/js/bootstrap.js"></script>
	
<script type="text/javascript"
	src="<%=basePath%>admin/assets/js/jquery.dataTables.js"></script>

<script type="text/javascript"
	src="<%=basePath%>admin/assets/js/basic/book_admin.js"></script>
</head>

<body>
	<div class="main-content-inner">


		<!-- /section:basics/content.breadcrumbs -->
		<div class="page-content">

			<div class="page-header clearfix">
				<h1 class="pull-left">全部书单</h1>

				<a href="/AicangBook/BookManage/GetBookExcel" title="下载全部书单"
					class="pull-right"><img
					src="/AicangBook/admin/assets/images/download.PNG" /></a>
			</div>

			<div class="row">
				<div class="col-xs-12">
					<!-- PAGE CONTENT BEGINS -->
					<div class="row">
						<div class="col-xs-12">
							<table id="book_table" class="table" style="font-size:13px">
								<thead>
									<tr>
										<th class=""><label class="position-relative"
											style="padding-top:4px"> <input type="checkbox"
												class="ace" /> <span class="lbl"></span>
										</label></th>
										<th>书名</th>
										<th>作者</th>
										<th>出版社</th>
										<th>出版时间</th>
										<th>ISBN</th>
										<th>数量</th>
										<th>操作</th>
									</tr>
								</thead>

								<tbody>

								</tbody>
							</table>
						</div>
						<!-- /.span -->
					</div>

				</div>

			</div>
		</div>
		<!-- 修改数据的弹出层 -->
		<div class="modal fade" id="book_edit" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<form class="form-horizontal" role="form" method="POST" action="/AicangBook/BookManage/EditBook">
					
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">
								<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
							</button>
							<h4 class="modal-title">编辑书籍</h4>
						</div>
						<div class="modal-body">
							<input type="hidden" name="bookId" id="bookId" />
							<div class="form-group">
								<label for="bookName" class="col-sm-4 control-label">书名</label>
								<div class="col-sm-5">
									<input type="text" class="form-control" id="bookName"
										name="bookName"/>
								</div>
								<div class="col-sm-3"></div>
							</div>
							<div class="form-group">
								<label for="bookAuthor" class="col-sm-4 control-label">作者</label>
								<div class="col-sm-5">
									<input type="text" class="form-control" id="bookAuthor"
										name="bookAuthor"/>
								</div>
								<div class="col-sm-3"></div>
							</div>
							<div class="form-group">
								<label for="bookPublisher" class="col-sm-4 control-label">出版社</label>
								<div class="col-sm-5">
									<input type="text" class="form-control" id="bookPublisher"
										name="bookPublisher"/>
								</div>
								<div class="col-sm-3"></div>
							</div>

							<div class="form-group">
								<label for="bookPublishDate" class="col-sm-4 control-label">出版时间</label>
								<div class="col-sm-5">

									<input type="text" class="form-control" id=bookPublishDate
										name="bookPublishDate"  readonly="readonly" />


								</div>
								<div class="col-sm-3" id=checkProductId
									style="color:red;font-family:宋体;font-size:14"></div>

							</div>

							<div class="form-group">
								<label for="ISBN" class="col-sm-4 control-label">ISBN</label>
								<div class="col-sm-5">
									<input type="text" class="form-control" id="ISBN" name="ISBN"  readonly="readonly" />
								</div>

								<div class="col-sm-3" id=checkProductName
									style="color:red;font-family:宋体;font-size:14"></div>
							</div>

							<div class="form-group">
								<label for="bookNum" class="col-sm-4 control-label">数量</label>
								<div class="col-sm-5">
									<input type="text" class="form-control" id="bookNum"
										name="bookNum" />
								</div>

								<div class="col-sm-3" id=checkProductName
									style="color:red;font-family:宋体;font-size:14"></div>
							</div>

						</div>
						<div class="modal-footer">
							<input type="submit" id="updateRuleProductBtn" value="保存"
								class="btn btn-primary">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">取消</button>
						</div>
					</form>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<!-- /.modal -->


	</div>
</body>
</html>
