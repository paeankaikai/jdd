<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/include.jsp"%>

<%@ include file="../common/jstl.jsp"%>

<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<title>表单提交</title>
<style type="text/css">
.submitform {
	margin: 0 auto;
	width: 938px;
	padding: 0 30px;
	border: 1px solid #CCCCCC;
	overflow: hidden;
}

.submitformleft {
	width: 350px;
	float: left;
}

.submitformleft1 {
	height: 30px;
	margin-top: 10px;
	float:left;
}

.submitformleft1 .submitformlP {
	text-align: right;
	width: 80px;
	float: left;
	line-height: 30px;
	margin-right: 10px;
	color: #333333;
	font-size: 14px;
}

.submitformleft1 select {
	width: 130px;
	float: left;
	height: 26px;
	margin-top: 3px;
	font-size: 12px;
	border: 1px solid #dbdbdc;
}

.jddinput {
	float: left;
	width: 135px;
	height: 25px;
	border: 1px solid #dbdbdc;
}

.submitformTop {
	overflow: hidden;
}

.causedescib {
	margin-top: 20px;
	clear: both;
}

.causedescib p {
	padding: 0px;
	margin: 0;
	font-size: 14px;
	color: #333333;
	padding-left: 10px;
	height: 30px;
	line-height: 30px;
}

.causedescib textarea {
	height: 50px;
	width: 938px;
	border: 1px solid #dbdbdc;
}

.uploadfile {
	height: 40px;
}

.uploadfile .uploadfileSP {
	float: left;
	padding-left: 10px;
	line-height: 40px;
	font-size: 14px;
	color: #333333;
}

.uploadfile .fileupload {
	margin: 7px 0 0 10px;
	position: relative;
	top: 8px;
}

.bottombuttonT {
	height: 40px;
}

.bottombuttonT p {
	float: right;
}

.bottombuttonT p a {
	padding: 5px 15px;
	color: #FFFFFF;
	background: #999999;
	font-size: 12px;
	float: left;
	margin-right: 20px;
}

.bottombuttonT p a.defauButt {
	background: #1089e4;
}

.submitform {
	width: 780px;
	padding: 0 20px;
	margin-left: 0px;
}

.submitformleft1 .submitformlP {
	width: 70px;
	text-align: left;
	font-size: 12px;
	color: #666666;
}

.jddinput {
	width: 135px;
	height: 25px;
}

.causedescib  .textarea_p {
	width: 740px;
	height: 70px;
	border: 1px solid #ebedee;
}

.causedescib p,.uploadfil span.uploadfileSP,.uploadfile input {
	font-size: 12px;
	color: #666666;
}

.uploadfileSP {
	font-size: 12px !important;
}
.p_toppleft{
	float:left;
	margin-right:20px;
	margin-top:15px;
}
.p_toppleft .tt_prompt{
    font-size:12px;
    color:#666666;
}
.p_toppleft select{
	width:120px;
	height:30px;
	border:1px solid #ebedee;
	font-size:12px;
	color:#666666;
}
</style>
</head>
<body>
	<form action="/admin/release/addSingleJdd" method="post" enctype="multipart/form-data"
		id="addRelease">
		<div class="submitform">
			<div class="submitformTop">
			<%-- 	<div class="submitformleft1">
					<span class="submitformlP">发放类别:</span>
					<select name="ruleChild">
						<c:forEach var="rule" items="${rules }">
							<option value="${rule.guid }">${rule.className }</option>
						</c:forEach>
					</select>
				</div> --%>
				<div class="p_top">
					<p class="p_toppleft">
						<span class="tt_prompt">类别：</span>
						<span class="tt_span" style="padding-left: 40px">
							<select id="gradRules" onChange="releaseQuery.content.gradRuleChange()">
								<option value="0">请选择</option>
								<c:forEach var="rule" items="${rules}" varStatus="status">
									<option value="${rule.guid}">${rule.className}</option>
								</c:forEach>
							</select>
						</span>
					</p>
					<p class="p_toppleft">
						<!-- <span class="tt_prompt">二级类别：</span> -->
						<span class="tt_span">
							<select id="fatherRules" onChange="releaseQuery.content.fatherRuleChange()">
								<option value="0">请选择</option>
							</select>
						</span>
					</p>
					<p class="p_toppleft">
						<!-- <span class="tt_prompt">三级类别：</span> -->
						<span class="tt_span">
							<select id="childRules" name="ruleChild">
								<option value="0">请选择</option>
							</select>
						</span>
					</p>
				</div>
				<div class="submitformleft">

					<div class="submitformleft1">
						<span class="submitformlP">员工ID:</span>
						<input type="text" class="jddinput" name="userId" />
					</div>
					<div class="submitformleft1">
						<span class="submitformlP">吉点点数量:</span>
						<input type="text" class="jddinput" name="jddCount" />
					</div>
				</div>
				<div class="submitformright">
				<div class="submitformleft1">
						<span class="submitformlP">员工姓名:</span>
						<input type="text" class="jddinput" name="userName" readonly />
					</div>
					<div class="submitformleft1">
						<span class="submitformlP">费用归属:</span>
						<select name="source">
							<c:forEach var="s" items="${SOURCE }">
								<option value="${s.guid }">${s.paramValue }</option>
							</c:forEach>
						</select>
					</div>
					
				</div>
			</div>
			<div class="causedescib">
				<p>原因描述:</p>
				<textarea name="reason" class="textarea_p"></textarea>
			</div>
			<div class="causedescib">
				<p>内容描述:</p>
				<textarea name="content" class="textarea_p"></textarea>
			</div>
			<div class="uploadfile">
				<span class="uploadfileSP">审批文件:</span>
				<input type="file" class="fileupload" name="filePath" />
			</div>
			<div class="bottombuttonT">
				<p>
					<a class="defaultButt defauButt" href="javascript:void(0);">确认添加</a>
					<a class="defaultButt" href="javascript:history.go(-1);">返回</a>
				</p>

			</div>
	</form>
</body>
</html>


<script type="text/javascript" src="<%=path %>/js/admin/releaseQuery.js"></script>
	<script>
	$(document).ready(function() {
		releaseQuery.content.init();
	  
	});
    </script>




<script>
	$(function() {

		if (${msg} == false) {
			layer.msg('添加失败');
		}

		$("input[name='userId']").blur(function() {
			var value = $(this).val();
			if (value == null || value == '') {
				return;
			}

			$.post("/admin/release/findUser", {
				"userId" : value
			}, function(data) {
				$("input[name='userName']").val(data.name);
			});
		});

		$(".defaultButt").click(function(event) {
			event.stopPropagation();
			var ruleChild=$("#childRules").val();
			var userId = $("input[name='userId']");
			var userName = $("input[name='userName']");
			var filePath = $("input[name='filePath']");
			var jddCount = $("input[name='jddCount']");
			if(ruleChild==0){
				layer.msg('请选择类别');
				return;
			}

			if (userId.val() == null || userId.val() == '') {
				layer.msg('用户Id没有输入');
				return;
			}
			if (jddCount.val() == null || jddCount.val() == '') {
				layer.msg('吉点点数量没有输入');
				return;
			}

			if (filePath.val() == null || filePath.val() == '') {
				layer.msg('没有上传审批文件');
				return;
			}
			$("#addRelease").submit();

		});

	});
</script>
