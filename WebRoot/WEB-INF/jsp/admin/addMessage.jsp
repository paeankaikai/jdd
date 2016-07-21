<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ include file="../common/jstl.jsp"%>
<!DOCTYPE HTML >
<html>
<head>

<title>添加信息</title>


<link rel="stylesheet" type="text/css" href="<%=path%>/js/layer/skin/layer.css" />


</head>

<body>
	<form action="" method="post" id="addRuleFrom">
		<div>
			名称：
			<input type="text" name="className">
		</div>

		<div>
			备注：
			<input type="text" name="remark">
		</div>

		<c:if test="${levels eq 3 }">
			<div>
				来源：
				<select name="source">
					<c:forEach items="${SOURCE }" var="s">
						<option value="${s.guid }">${s.paramValue }</option>
					</c:forEach>

				</select>
			</div>
		</c:if>
		<a href="javascript:void(0);" id="addRule">提交</a>

	</form>





	<script type="text/javascript" src="<%=path%>/js/jquery11.min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/layer/layer.js"></script>

	<script>
		$(function() {

			if (${addOrEdit} == "1") {
				$("#editRuleFrom").css("display", "none");
			} else {
				$("#addRuleFrom").css("display", "none");
			}
			;

			$("#addRule").click(function() {
				$.post("/admin/rule/addRule", $("#addRuleFrom").serialize(), function(data) {
					if (data == "success") {
						parent.layer.msg('添加成功');
						parent.window.location.href = "/admin/rule/ruleList?type=" + ${type };
						var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
						parent.layer.close(index);
					} else {
						alert("提交失败");
					}
				});

			});

			$("#editRule").click(function() {
				$.post("/admin/rule/editRule", $("#editRuleFrom").serialize(), function(data) {
					if (data == "success") {
						parent.window.location.href = "/admin/rule/ruleList?type=" + ${type};
						var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
						parent.layer.close(index);
					} else {
						alert("提交失败");
					}
				});

			});

		});
	</script>



</body>
</html>
