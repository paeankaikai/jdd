<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<script type="text/javascript" src="/js/jquery11.min.js"></script>
<script type="text/javascript" src="/js/layer/layer.js"></script>
<script type="text/javascript" src="/js/laypage/laypage.js"></script>
<%@ include file="../common/jstl.jsp"%>
<link rel="stylesheet" type="text/css" href="/css/admin/expensesQuery.css">
<link rel="stylesheet" type="text/css" href="/css/admin/index.css">


<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<title></title>
<style>
.import_div2 {
	width: 780px;
}

body {
	margin: 0px;
}

.import_div2 .import_divtop2 {
	border: 1px solid #ebedee;
	height: 40px;
	margin: 0px;
	margin-bottom: 10px;
	padding: 0px;
	background: #FFFFFF;
}

.import_divtop2 a {
	width: 120px;
	text-decoration: none;
	font-size: 14px;
	color: #666666;
	line-height: 40px;
	text-align: center;
	float: left;
}

.rightborer {
	line-height: 40px;
	color: #ebedee;
	float: left;
}

.importadefau {
	border-bottom: 2px solid #5cace2;
	font-weight: bold;
}

.import_divmid {
	background: #FFFFFF;
	border: 0px;
	border-bottom: 1px solid #ebedee;
}

.import_divmiddiv1,.import_divmiddiv1 p {
	color: #666666;
	font-size: 12px;
}

.import_divmiddiv1 p {
	height: 30px;
	line-height: 30px;
	text-align: left;
	padding-left: 20px;
	width: 60px;
}

.import_divmiddiv1 {
	margin-top: 15px;
}

.import_divmiddiv1 span {
	float: left;
	font-size: 12px;
	color: #666666;
}

.import_divmiddiv1 span.checkboxText {
	height: 30px;
	line-height: 30px;
	margin-right: 20px;
}

.import_divmiddiv1 span.checkboxSpan {
	background: url("/img/common/defaultchebox.png");
	width: 12px;
	height: 12px;
	margin: 8px 7px 0 0px;
}

.import_divmiddiv1 span.checkboxSpan2 {
	background: url("/img/common/greencheckbg.png");
}

.bt_t,#createName {
	border: 1px solid #ebedee;
	width: 130px;
	height: 25px;
	color: #999999;
	position: relative;
	top: 2px;
}

.datetotime {
	height: 30px;
	line-height: 30px;
	font-size: 12px;
	color: #666666;
}

#createName {
	width: 320px;
}

.import_divmid {
	position: relative;
}

.button_sear_set {
	position: absolute;
	padding-right: 20px;
	bottom: 10px;
	right: 0px;
}

.button_sear_set a {
	display: block;
	text-decoration: none;
	font-size: 12px;
	font-weight: normal;
	width: 60px;
	height: 25px;
	border: 0px;
	background: #999999;
	color: #FFFFFF;
	margin-left: 0px;
}

#search {
	background: #1089e4;
	color: #FFFFFF;
}

.import_divtable {
	width: 740px;
	margin: 0 20px;
}

.import_div2All {
	border: 1px solid #ebedee;
	background: #FFFFFF;
}

.import_divtableT {
	background: none !important;
}

.import_divtableT span {
	font-size: 14px;
	color: #666666;
	font-weight: normal;
}

.import_divtableT .import_divtableTS1 {
	width: 60px;
}

.import_divtableT .import_divtableTS2 {
	width: 105px;
}

.import_divtableT .import_divtableTS3 {
	width: 160px;
}

.import_divtableT .import_divtableTS4 {
	width: 150px;
}

.import_divtableT .import_divtableTS5 {
	width: 70px;
}

.import_divtableBody .import_divtableT span {
	font-size: 12px;
}

.import_divtableBody .import_divtableT span a {
	color: #1089e4;
	text-decoration: none;
}

#page {
	margin-top: 30px;
}

.import_divtableBody .import_divtableT {
	border-bottom: 1px dashed #ebedee;
}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		$(".import_divmiddiv1 .checkboxSpan").click(function() {
			$(".checkboxSpan").removeClass("checkboxSpan2");
			$(this).addClass("checkboxSpan2");
		});
		$(".import_divtableT:last").css("border", "none");
	})
</script>
</head>
<body>
	<div class="import_div2">
		<div class="import_divtop2">
			<a href="/admin/release/checkRelease">吉点点</a>
			<span class="rightborer">|</span>
			<a href="/admin/release/checkRelease?type=1">吉课金币</a>
		</div>

		<div class="import_div2All">
			<form action="" method="post" style="margin:0;">
				<input type="hidden" name="pageNum" value="1">

				<div class="import_divmid">
					<div class="import_divmiddiv1">
						<p>处理状态:</p>
						<label style="padding-right: 15px;"> <input type="radio" id="all" name="ststus"
								<c:if test="${ststus eq -1 }">checked</c:if> value="-1"> 全部
						</label> <label style="padding-right: 15px;"> <input type="radio" name="ststus"
								<c:if test="${ststus eq 0 }">checked</c:if> value="0"> 待审核
						</label> <label style="padding-right: 15px;"> <input type="radio" name="ststus"
								<c:if test="${ststus eq 1 }">checked</c:if> value="1"> 驳回
						</label> <label style="padding-right: 15px;"> <input type="radio" name="ststus"
								<c:if test="${ststus eq 2 }">checked</c:if> value="2"> 审核通过
						</label>

					</div>
					<div class="import_divmiddiv1">
						<p>时间:</p>

						<span class="bt_span">
							<input type="text" class="bt_t" id="startDate" name="startDate"
								onFocus="expensesQuery.content.startDateSelectFmt(this, 'endDate')" value="${startDate }" />
						</span>
						<span class="datetotime">至</span>
						<span class="bt_span">
							<input type="text" class="bt_t" id="endDate" name="endDate"
								onFocus="expensesQuery.content.endDateSelectFmt(this, 'startDate')" value="${endDate }" />
						</span>

					</div>
					<div class="import_divmiddiv1">
						<p>提交人:</p>
						<input type="type" id="createName" name="createName" value="${createName }" />
					</div>
					<p class="button_sear_set">
						<a id="search" href="javascript:void(0)">搜索</a>
						<a id="reset" href="javascript:void(0)">重置</a>
					</p>
				</div>
			</form>

			<div class="import_divtable">
				<p class="import_divtableT" style="border-top:0px;">
					<span class="import_divtableTS1">序号</span>
					<span class="import_divtableTS2">提交人</span>
					<span class="import_divtableTS2">公司</span>
					<c:if test="${type eq 0 }">
						<span class="import_divtableTS3">内容描述</span>
					</c:if>

					<span class="import_divtableTS4">时间</span>
					<span class="import_divtableTS5">状态</span>
					<span class="import_divtableTS6">操作</span>
				</p>

				<c:forEach items="${pageInfo.list }" varStatus="status" var="release">
					<div class="import_divtableBody">
						<p class="import_divtableT">
							<span class="import_divtableTS1">${status.index+1 }</span>
							<span class="import_divtableTS2">${release.createName }</span>
							<span class="import_divtableTS2">${empty release.userCom?'无':release.userCom  }</span>
							<span class="import_divtableTS3">
								<c:if test="${type eq 0 }">
								${empty release.content?'无':fn:substring(release.content, 0, 20) }
								</c:if>
							</span>
							<span class="import_divtableTS4">
								<fmt:formatDate type="BOTH" value="${release.createTime }" />
							</span>
							<span class="import_divtableTS5">
								<c:if test="${release.ststus eq 0}">待审核</c:if>
								<c:if test="${release.ststus eq 1}">驳回</c:if>
								<c:if test="${release.ststus eq 2}">审核通过</c:if>
							</span>
							<span class="import_divtableTS6">
								<c:if test="${type eq 0 }">
									<a href="/admin/release/jddView?releaseGuid=${release.guid }&admin=admin">
								</c:if>
								<c:if test="${type eq 1 }">
									<a href="/admin/release/jkView?releaseGuid=${release.guid }&admin=admin">
								</c:if>
								查看详情
								</a>
							</span>
						</p>
					</div>
				</c:forEach>
			</div>
		</div>

		<div id="page" style="padding-left: 150px"></div>
	</div>
</body>
</html>

<script type="text/javascript" src="/js/common/WdatePicker.js"></script>
<script type="text/javascript" src="/js/admin/expensesQuery.js"></script>
<script>
	$(document).ready(function() {
		if (${type} == 0) {
			$(".import_divtop2 a:first").addClass("importadefau");
			$(".import_divtop2 a:last").removeClass("importadefau");
		} else {
			$(".import_divtop2 a:first").removeClass("importadefau");
			$(".import_divtop2 a:last").addClass("importadefau");
		}

		var num = 1;

		expensesQuery.content.init();

		//重置
		$("#reset").click(function() {
			$("#startDate").val('');
			$("#endDate").val('');
			$("#createName").val('');
			/* 					$("input[name='ststus']").each(function() {
			 if ($(this).val() == '-1') {
			 $(this).attr("selected", "selected");
			 }
			 else{
			 $(this).removeAttr("selected");
			 }
			 }); */

		});

		$("#search").click(function() {
			$("form").attr("action", "/admin/release/checkRelease?type=" + ${type});
			$("form").submit();
		});

		laypage({
			cont : 'page',
			groups : 3, //连续显示分页数
			skip : true, //是否开启跳页
			skin : '#AF0000',
			pages : ${pageInfo.pages}, //可以叫服务端把总页数放在某一个隐藏域，再获取。假设我们获取到的是18
			curr : function() { //通过url获取当前页，也可以同上（pages）方式获取
				return ${pageInfo.pageNum};
			}(),
			jump : function(obj, first) { //触发分页后的回调
				if (!first) { //一定要加此判断，否则初始时会无限刷新
					num = obj.curr;
					$("input[name='pageNum']").val(num);
					$("#search").click();
				}
			}
		});

	});
</script>

