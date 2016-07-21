<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/include.jsp"%>

<%@ include file="../common/jstl.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>批量发放</title>
<link rel="stylesheet" type="text/css" href="/css/home/home.css" />
<link rel="stylesheet" type="text/css" href="/css/admin/reset.css" />
<link rel="stylesheet" type="text/css" href="/css/admin/common.css" />
<link rel="stylesheet" type="text/css" href="/css/admin/thems.css">
<link rel="stylesheet" type="text/css" href="/css/admin/expensesQuery.css">
<style type="text/css">
.bottombuttonT {
	height: 40px;
	margin-bottom: 20px;
}

.bottombuttonT p {
	float: right;
	margin: 0px;
	padding: 0px;
}

.bottombuttonT p a {
	cursor: pointer;
	height: 30px;
	margin-left: 20px;
	width: 80px;
	font-size: 14px;
	border: 1px solid #dbdbdc;
	background: #f1f1f1;
	color: #333333;
	display: block;
	text-align: center;
	margin: 10px 20px 0 0;
	float: left;
}

.bottombuttonT p button.defaultButt {
	background: #3aa3c3;
	border: 1px solid #3aa3c3;
	color: #FFFFFF;
}

.p_buttom span,.p_buttom input {
	float: left;
}

.p_buttom  .bt_span2 {
	float: right;
}

#page1 {
	height: 40px;
	padding: 15px 0;
}

.eq_context {
	overflow: hidden;
	font-size: 16px;
}
.maina{width:780px;}
.infoh4{height:40px;line-height:40px;font-size:12px; color:#666666;}
.textarea_p{width:740px;height:70px;border:1px solid #ebedee;}
.p_buttom{font-size:12px; color:#666666;}
.p_buttom input{position:relative;top:4px;}
.threeoprea{height:35px;background:#eff3f7;border-bottom:1px solid #ebedee;}
.threeoprea a{width:70px; height:24px;margin-top:5px;line-height:24px;color:#FFFFFF;background:#1089e4; margin-left:20px;float:left;font-size:12px;}
#details{width:740px;margin:0 20px;}
#details .title{background:#FFFFFF;}
#details .title th{font-size:14px; color:#666666;}
.maina{height:auto!important;}
.maina table tr td{font-size:12px;}
#message{font-size:14px;color:#666666;font-weight:normal;}
.bottombuttonT p a{padding:5px 15px;background:#999999;font-size:12px;color:#FFFFFF;}
#insert{background:#1089e4;}
#conditionContent{position:relative;}
.bottombuttonT{position:absolute;bottom:10px;right:0px;margin-bottom:0px;}

</style>


</head>

<body>
	<div class="maina">
		<!--查询-->
		<div class="eq_context" id="conditionContent">

			<form id="uplaodExcel" enctype="multipart/form-data" method="post"
				action="/admin/release/inportJdd">
				<input type="hidden" name="type">
				<p class="p_top">
					<span class="infoh4">内容描述：</span>
					<textarea  class="textarea_p" rows="2" cols="80" name="content"></textarea>
				</p>
				<p class="p_buttom">
					<span class="bt_prompt">审批文件：</span>
					<span class="bt_span">
						<input type="file" name="filePath" />
					</span>
				</p>

				<p class="p_buttom">
					<span class="bt_prompt ">数据文件：</span>
					<span class="bt_span">
						<input type="file" name="excelPath" />
					</span>
					
				</p>
			</form>
		<div class="bottombuttonT">
		   <p>
			  <a class="defaultButt" id="insert" href="javascript:void(0);">确认添加</a>
			  <a id="back" href="javascript:history.go(-1);">返回</a>
		  </p>
	</div>
		</div>


		<label id="message" class="eq_context"></label>
       <div class="threeoprea"><a href="javascript:void(0)" class="readexcel" id="readExcel">读取excel</a></div>
		<!--查询-->
		<table border="0" cellpadding="0" cellspacing="0" id="details">
			<tr class="title titleBG" style="color: green;">
				<th width="50" scope="col">序号</th>
				<th width="205" scope="col">类别</th>
				<th width="200" scope="col">费用来源</th>
				<th width="120" scope="col">员工ID</th>
				<th width="120" scope="col">姓名</th>
				<th width="50" scope="col">数量</th>
				<th width="200" scope="col">原因</th>

			</tr>


		</table>

	</div>
	<div id="page1"></div>
	

	<input type="hidden" value="${error }" id="msg">
</html>
</body>




<script type="text/javascript">
	$(function() {
		var messge = $("#msg").val();
		if (messge != null && messge != "") {
			alert(messge);
		}

		//记录获取过来的excel
		var excel = $("input[name='excelPath']");
		var list;
		var num = 1;
		var falg = true;
		/* 
		 $("#back").click(function() {
		 window.location.href = "javascript:history.go(-1);";
		 });
		 */
		$("#readExcel").click(
				function() {
					//	layer.load();
					//是否为空
					falg=true;
					var excelName = $.trim(excel.val());
					if (excelName == '') {
						layer.msg("请选择数据文件");
						return;
					}
					//判断格式
					var extStart = excelName.lastIndexOf(".");
					var ext = excelName.substring(extStart, excelName.length).toUpperCase();
					if (ext != ".XLSX") {
						layer.msg("文件仅限于xlsx格式");
						return;
					}
					var index=layer.load(1);
					var formData = new FormData($("#uplaodExcel")[0]);
					$.ajax({
						url : '/admin/release/readExcel',
						type : 'POST',
						data : formData,
						async : true,
						cache : false,
						contentType : false,
						processData : false,
						success : function(data) {
							layer.close(index);
							list = data;
							if (list == null || list.length == 0) {
								layer.msg("读取失败，请检查导入文件的数据是否正确");
								return;
							}
							makeHtml(1);

							laypage({
								cont : 'page1',
								groups : 3, //连续显示分页数
								skip : true, //是否开启跳页
								skin : '#AF0000',
								pages : list.length == 0 ? 1
										: list.length % 10 == 0 ? list.length / 10
												: list.length / 10 + 1, //可以叫服务端把总页数放在某一个隐藏域，再获取。假设我们获取到的是18
								curr : function() { //通过url获取当前页，也可以同上（pages）方式获取								
									return num;
								}(),
								jump : function(obj, first) { //触发分页后的回调
									if (!first) { //一定要加此判断，否则初始时会无限刷新
										num = obj.curr;
										makeHtml(num);
									}
								}
							});
						},
						error : function(returndata) {
							alert("读取失败，请检查导入文件的数据是否正确");
						}
					});

				});

		function makeHtml(num) {
			var html = "";
			var error = '';
			//falg = true;
			for (var i = (num - 1) * 10, j = 0; i < list.length && j < 10; i++, j++, error = '') {
				var detail = list[i].detail;
				var right = list[i].right;
				if (right == 1) {
					falg = false;
					error = "bgcolor='yellow'";
				}

				html += "<tr class='detail' "+error+">" + "<td scope='col'>" + (i + 1) + "</td>"
						+ "<td scope='col'>" + list[i].child + "</td>" + "<td scope='col'>"
						+ list[i].source + "</td>" + "<td scope='col'>" + detail.userId + "</td>"
						+ "<td scope='col'>" + detail.userName + "</td>" + "<td scope='col'>"
						+ detail.jddCount + "</td>" + "<td scope='col'>" + detail.reason + "</td>"
						+ "</tr>";
			}

			$("tr").remove(".detail");
			$("#details").append(html);
			$("#message").text("共导入" + list.length + "条数据");

		}

		$("#insert").click(function(event) {
			event.stopPropagation();
			var area = $("textarea[name='content']").val();
			if (null == area || area == '') {
				layer.msg('内容描述为空');
				return;
			}
			//	$("#uplaodExcel").submit();
			if (falg && list != null) {
				//var content = $("textarea[name='content']").val();
				var filePath = $("input[name='filePath']").val();
				if (null != filePath && filePath != '') {
					$("#uplaodExcel").submit();
				} else {
					layer.msg('审批文件为空');
					return;
				}

			} else {
				layer.tips('操作有误，请重试', '#insert');
			}

		});

	});
</script>

