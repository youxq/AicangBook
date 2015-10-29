<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<div id="sidebar" class="sidebar       responsive" data-sidebar="true" data-sidebar-scroll="true" data-sidebar-hover="true">
				<script type="text/javascript">
					try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
				</script>

				<ul class="nav nav-list" style="top: 0px;">
					<li class="active">
						<a href="index.jsp">
							<i class="menu-icon fa fa-tachometer"></i>
							<span class="menu-text"> 主页 </span>
						</a>
						<b class="arrow"></b>
				    </li>
				    <li class="">
						<a href="common/book_admin.jsp" target="mainFrame">
							<i class="menu-icon fa fa-pencil-square-o"></i>
							<span class="menu-text"> 书籍管理 </span>
						</a>
						<b class="arrow"></b>
				    </li>

					

				</ul>
			     <div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
					<i class="ace-icon fa fa-angle-double-left" data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
				</div>		
</div>
