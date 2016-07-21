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
<base href="<%=basePath%>">

<title>My JSP 'addRule.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<script type="text/javascript" src="<%=path%>/js/jquery11.min.js"></script>
<!-- jayer弹出层 -->
<script type="text/javascript" src="<%=path%>/js/layer/layer.js"></script>
<style>
     #layui-layer1{width:500px!important;height:210px!important;}
   
    .layui-layer-title div{font-size:12px;color:#666666;}
  
    #layui-layer3,#layui-layer2{height:210px!important;}
    #layui-layer3{width:500px;height:210px;}
    .layui-layer-content{color:#666666;font-size:12px;}
  .namedivl{font-size:12px; color:#666666;height:30px;padding-left:20px;margin-top:5px;}
  .namedivl input{width:280px; height:22px;border:1px solid #ebedee;}
  #addRule,#editRule{float:right;margin-right:20px;width:60px; height:25px;border:0;background:#1089e4;font-size:12px; color:#FFFFFF;text-decoration:none;line-height:25px;text-align:center;margin-top:40px;}
</style>
</head>

<body>
	<form action="" method="post" id="addRuleFrom">

		<input type="hidden" name="parentGuid" value="${parentGuid }">
		<input type="hidden" name="levels" value="${levels }">
		<input type="hidden" name="type" value="${type }">

		<div class="namedivl">
			名称：
			<input type="text" name="className">
		</div>

		<div class="namedivl">
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



	<form action="" method="post" id="editRuleFrom">

		<input type="hidden" name="guid" value="${jddRule.guid }">
		<div class="namedivl">
			名称：
			<input type="text" name="className" value="${jddRule.className }">
		</div>

		<div class="namedivl">
			备注：
			<input type="text" name="remark" value="${jddRule.remark }">
		</div>

		<c:if test="${jddRule.levels eq 3 }">
			<div>
				来源：
				<select name="source">
					<c:forEach items="${SOURCE }" var="s">
						<option value="${s.guid }" <c:if test="${s.guid eq jddRule.source }">selected</c:if>>${s.paramValue }</option>
					</c:forEach>

				</select>
			</div>


		</c:if>
		<a href="javascript:void(0);" id="editRule">提交</a>

	</form>





	<script>
		$(function() {

			if (${addOrEdit} == "1") {
				$("#editRuleFrom").css("display", "none");
			} else {
				$("#addRuleFrom").css("display", "none");
			}
			;

			$("#addRule").click(function(event) {
				event.stopPropagation();
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

			$("#editRule").click(function(event) {
				event.stopPropagation();
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
