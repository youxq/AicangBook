$(document).ready(function() {

	/**
	 * 显示数据的事件
	 */
	$("#book_table").dataTable({
		"bServerSide" : true,// 这个用来指明是通过服务端来取数据
		"sAjaxSource" : "/AicangBook/BookManage/GetBookTable",// 这个是请求的地址
		"fnServerData" : retrieveData, // 获取数据的处理函数
		"bProcessing" : true, // 当datatable获取数据时候是否显示正在处理提示信息。
		"bStateSave" : false,
		"bInfo" : false,
		"aoColumnDefs": [ { "bSortable": false, "aTargets": [ 0 ] }],
		"aLengthMenu" : [ 5, 10, 15, 20, 50, 100 ],
		"iDisplayLength" : 5,
		"oLanguage" : { // 主要用于设置各种提示文本
			"sProcessing" : "正在处理...", // 设置进度条显示文本
			"sLengthMenu" : "每页_MENU_行",// 显示每页多少条记录
			"sEmptyTable" : "没有找到记录",// 没有记录时显示的文本
			"sZeroRecords" : "没有找到记录",// 没有记录时显示的文本
			"sInfo" : "总记录数_TOTAL_当前显示_START_至_END_",
			"sInfoEmpty" : "",// 没记录时,关于记录数的显示文本
			"sSearch" : "搜索:",// 搜索框前的文本设置
			"oPaginate" : {
				"sFirst" : "首页",
				"sLast" : "未页",
				"sNext" : "下页",
				"sPrevious" : "上页"
			}
		}
	// 用于设置提示文本
	});

	function retrieveData(sSource, aoData, fnCallback) {
		$.ajax({
			url : sSource,// 这个就是请求地址对应sAjaxSource
			data : {
				"aoData" : JSON.stringify(aoData)
			},// 这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
			type : 'post',
			dataType : 'json',
			async : false,
			success : function(result) {
				fnCallback(result);// 把返回的数据传给这个方法就可以了,datatable会自动绑定数据的
			},
			error : function(msg) {
			}
		});
	}
	
	
	
	
	
	/**
	 * 设置选择框全部选择时间
	 */
	$(document).on(
			'click',
			'th input:checkbox',
			function() {
				var that = this;
				$(this).closest('table').find(
						'tr > td:first-child input:checkbox').each(
						function() {
							this.checked = that.checked;
							$(this).closest('tr').toggleClass('selected');
						});
			});
	
	
});


/**
 * 编辑弹出层
 */
function edit(id){
	var tdList = $(id).parent().parent().children();
	$("#bookId").val(tdList.eq(0).find("input").eq(0).val());
	$("#bookName").val(tdList.eq(1).text().trim());
	$("#bookAuthor").val(tdList.eq(2).text().trim());
	$("#bookPublisher").val(tdList.eq(3).text().trim());
	$("#bookPublishDate").val(tdList.eq(4).text().trim());
	$("#ISBN").val(tdList.eq(5).text().trim());
	$("#bookNum").val(tdList.eq(6).text().trim());
	$('#book_edit').modal();
}


function updateBook(){
	
}

function ajax(method, remote_url, send_data, callback) {

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
