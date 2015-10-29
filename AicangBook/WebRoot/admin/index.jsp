<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!-- 因为使用的是静态的 include,file,所以在commonjs和commoncss中没有声明basePath，因为静态的include只会生成一个servlet -->
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="common/commoncss.jsp"%>
</head>
<body class="no-skin">
	<jsp:include page="common/header.jsp"></jsp:include>
	<div class="main-container" id="main-container">
		<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>
		<jsp:include page="common/left.jsp"></jsp:include>
		<div class="main-content">
			<iframe name="mainFrame" id="mainFrame" frameborder="0"
				src="<%= basePath%>admin/common/home.jsp"
				style="margin:0 auto;width:100%;height:100%;"></iframe>
		</div>
		<jsp:include page="common/footer.jsp"></jsp:include>
	</div>
    <%@ include file="common/commonjs.jsp"%>
	<script>
	function loginFrame(){
			var mainFrame = document.getElementById("mainFrame");
			var bheight = document.documentElement.clientHeight;
			mainFrame.style.width = '100%';
			mainFrame.style.height = (bheight  - 51) + 'px';		
		}
		loginFrame();
		window.onresize=function(){  
			loginFrame();
		}
	</script>
</body>
</html>
