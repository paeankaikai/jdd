<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/include.jsp"%>

<%@ include file="../common/jstl.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="/css/admin/exchangeQuery.css">

<link rel="stylesheet" type="text/css" href="/css/admin/thems.css">



<title>支出查询</title>
<style type="text/css">
.maina{width:780px;background:#FFFFFF;}
.maina h4{height:35px;font-size:12px;line-height:35px; color:#666666;
width:780px;margin:0px;padding:0px;background:#e8ebf2 url(/img/common/addmessa.png) 18px 12px no-repeat;padding-left:35px;}
.maina h4 a{color:#666666;}
#resultContent{margin:0;margin:0 15px;width:750px;}
#resultContent .title{background:#FFFFFF;}
#resultContent .title th{color:#666666;font-size:14px;font-weight:normal;text-align:left;}
.jddinfomess{width:370px;margin:10px 0;background:#FFFFFF;border:0px;font-size:12px;line-height:20px;color:#666666;text-align:left;}
.maina table tr td {text-align:left;font-size:12px;border-bottom:1px dashed #ebedee;}
.maina table tr td a{color:#1089e4;,}
.maina table tr td  input{background:#ffffff;border:0;margin:0px;width:100px;height:32px;}
.bordereditor,.maina table tr td  input.bordereditor{border:1px solid #ebedee;}
</style>

</head>
<body>
	<div class="maina">
		<!--查询-->
		<h4>
		    <a id="add" href="javascript:void(0);" onclick="insert(this)">新增</a>
	    </h4>
		<div class=container>	
		</div>
		<!--查询-->
		<table id="resultContent" border="0" cellpadding="0" cellspacing="0">
			<tr class="title">
				<th scope="col"></th>
				<th width="410">信息内容</th>
				<th width="130">开始时间</th>
				<th width="130">结束时间</th>
				<th width="110">操作</th>
			</tr>

			<c:forEach var="message" items="${messages }" varStatus="status">
				<tr height="50">
					<td>
						<input type="hidden" value="${message.guid}">
					</td>
					<td>
				<%-- 		<span class="jddinfomess">${message.message }</span> --%>
						<textarea class="jddinfomess" style="overflow:hidden; resize:none;" disabled>${message.message }</textarea>
					</td>
					<td>
						<input type="text" class="ct_t" id="startDate_${status.index }" name="startDate" disabled
							value="<fmt:formatDate  pattern='yyyy-MM-dd'  value='${message.startDate }' />"
							onFocus="exchangeQuery.content.startDateSelectFmt(this, 'endDate_${status.index }')" />
					</td>
					<td>
						<input type="text" class="ct_t1" id="endDate_${status.index }" name="endDate" disabled
							value="<fmt:formatDate  pattern='yyyy-MM-dd'  value='${message.endDate }' />"
							onFocus="exchangeQuery.content.endDateSelectFmt(this, 'startDate_${status.index }')" />
					</td>
					<td>
						<a href="javaScript:void(0);" class="editorA" name="edit" onclick="edit(this)">编辑</a>
						<a href="javaScript:void(0);" name="save" class="saveabc" hidden="hidden" onclick="save(this)">保存</a>
						&nbsp;|&nbsp;
						<a href="javaScript:void(0);" name="dele" onclick="dele(this)">删除</a>
					</td>
				</tr>
			</c:forEach>
		</table>



		<div id="page"></div>
	</div>
	<script type="text/javascript" src="<%=path%>/js/admin/exchangeQuery.js"></script>
	<script>
		$(function() {
			var t = $("#resultContent tr").length;
			var html = "<tr><td><input type='hidden'></td>"
					+ "	<td><textarea class='jddinfomess'  style='overflow:hidden; resize:none;''></textarea></td>"
					+ "<td><input type='text' class='ct_t' id='startDate_"+t+"' name='startDate' "
					+ " onFocus=\"exchangeQuery.content.startDateSelectFmt(this, \'endDate_"+t+"')\" /></td>"
					+ "<td><input type='text' class='ct_t1' id='endDate_"+t+"' name='endDate' "
					+ " onFocus=\"exchangeQuery.content.endDateSelectFmt(this, 'startDate_"+t+"')\"  /></td>"
					+"<td><a href='javaScript:void(0);' hidden  name='edit' onclick='edit(this)''>编辑</a>"
					+" <a href='javaScript:void(0);' name='save'  onclick='save(this)''>保存</a>"
					+"&nbsp;|&nbsp;<a href='javaScript:void(0);' name='dele' onclick='dele(this)'>删除</a></td></tr>";

			edit = function(obj) {
				$(obj).hide();
				$(obj).next().show();
				var parent = $(obj).parents("tr");
				parent.find(".ct_t ").removeAttr("disabled");
				parent.find(".ct_t1 ").removeAttr("disabled");
				parent.find("textarea").removeAttr("disabled");
			};

			save = function(obj) {
				var parent = $(obj).parents("tr");
				var start = parent.find(".ct_t").val();
				var end = parent.find(".ct_t1").val();
				var message = parent.find("textarea").val();
				var guid = parent.find("input[type='hidden']").val();
				
				if(message==null||message==''){
					alert('信息为空');
					return;
				}
				var falg = false;
				if (start.length > 0 && end.length > 0) {
					falg = true;
				}
				if (start.length == 0 && end.length == 0) {
					falg = true;
				}
				if (!falg) {
					alert('日期有误');
					return;
				}

				$.post("/admin/message/saveMessage", {
					guid : guid,
					message : message,
					start : start,
					end : end
				}, function(data) {
					if (data == 'error') {
						alert('保存失败');
						return;					
					}else{					
						if(data!='success'){
							parent.find("input[type='hidden']").val(data);
						}				
						$(obj).hide();
						$(obj).prev().show();
						parent.find(".ct_t").attr("disabled", true);
						parent.find(".ct_t1").attr("disabled", true);
						parent.find("textarea").attr("disabled", true);
					}
				});

			};

			dele=function(obj) {
				var parent = $(obj).parents("tr");
				var guid = parent.find("input[type='hidden']").val();
				if(guid==null||guid==''){
					parent.remove();
					return;
				}
				$.post("/admin/message/deleMessage", {
					guid : guid
				}, function(data) {
					if (data == 'success') {
						parent.remove();
					} else {
						alert('删除失败');
					}
				});
			};
			
			insert=function(){
				$("#resultContent").append(html);
			}
			
		     $(document).on("click",".editorA",function(){
		    	 $(".jddinfomess,.ct_t,.ct_t1").addClass("bordereditor");
		     });
		     $(document).on("click",".saveabc",function(){
		    	 $(".jddinfomess,.ct_t,.ct_t1").removeClass("bordereditor");
		     });
		});
	</script>
</body>
</html>

