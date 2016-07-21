<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/include.jsp"%>

<%@ include file="../common/jstl.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<link rel="stylesheet" type="text/css" href="/css/home/home.css" />
<link rel="stylesheet" type="text/css" href="/css/admin/reset.css" />
<link rel="stylesheet" type="text/css" href="/css/admin/common.css" />
<link rel="stylesheet" type="text/css" href="/css/admin/thems.css">

<link rel="stylesheet" type="text/css" href="/css/admin/expensesQuery.css">

<script type="text/javascript" src="<%=path%>/js/laypage/laypage.js"></script>
<style>
.bt_t {
	width: 120px;
	border: 1px solid #dedede;
}

.bt_prompt {
	color: #666666;
	font-weight: normal !important;
}

.bt_promptinput {
	border: 1px solid #dedede;
	width: 320px;
}

.bt_prompt,.bt_span {
	float: left;
}

.p_buttom {
	clear: both;
	width: 780px;
	min-height: 30px;
	padding-top: 15px;
}

.p_buttom span {
	float: left;
}

.bt_prompt {
	width: 80px;
}

#startDate {
	margin-left: 0px;
}

.p_buttom22,.bt_prompt {
	padding-top: 5px !important;
}

.bt_prompt {
	padding-bottom: 5px;
}

.eq_context22 {
	position: relative;
}

.rightbottomB {
	position: absolute;
	bottom: 15px;
	right: 20px;
}

.rightbottomB input {
	background: #999999;
	color: #FFFFFF;
	border: 0;
	width: 60px;
	height: 25px;
}

.rightbottomB input#queryConfirm {
	background: #1089e4;
	color: #FFFFFF;
}

.threeoprea {
	height: 35px;
	background: #eff3f7;
	border-bottom: 1px solid #ebedee;
}

.threeopreaP a {
	width: 70px;
	line-height: 24px;
	color: #666666;
	margin-left: 20px;
	float: left;
	font-size: 12px;
	height: 24px;
	margin-top: 6px;
	border: 1px solid #1089e4;
	color: #1089e4;
	text-align: center;
	text-decoration: none;
}

.threeopreaP a.defaultath,.threeopreaP a:hover {
	text-decoration: none;
	color: #FFFFFF;
	background: #1089e4;
}

#resultContent {
	margin-top: 0px;
	width: 740px;
	margin: 0 20px;
}

#resultContent .title {
	background: #FFFFFF;
}

#resultContent .title th {
	font-size: 14px;
	color: #666666;
}

#resultContent tr th {
	color: #666666;
	font-size: 12px;
	text-align: center;
	height: 35px;
	border-bottom: 1px dashed #ebedee;
}

#resultContent tr.title th {
	border-bottom: 1px solid #ebedee;
}
.pagetotal{height:40px;padding:20px 0 0 30px;}
.pagetotal .pagetotaLeft{color:#666666;font-size:12px;}
.pagetotal .pagetotaLeft,.pagetotal #page{float:left;}
</style>

</head>

<body>
	<input type="hidden" name="oldStartDate" value="${startDate }">
	<input type="hidden" name="oldEndDate" value="${endDate }">
	<input type="hidden" name="oldContent" value="${content }">


	<div class="maina">
		<%-- <div class="maina2">
			<c:if test="${type==0 }">
				<a href="/admin/release/toReleaseManager" style="color:red;">吉点点发放</a>
			</c:if>
			<c:if test="${type==1 }">
				<a href="/admin/release/toReleaseManager?type=1" style="color: red">吉课金币发放</a>
			</c:if>
		</div> --%>
		<!--查询-->
		<div class="eq_context eq_context22" id="conditionContent">
			<form action="/admin/release/toReleaseManager" method="post" id="from">
				<p class="p_buttom">
					<span class="bt_prompt">时间：</span>
					<span class="bt_span">
						<input type="text" class="bt_t" id="startDate" name="startDate"
							onFocus="expensesQuery.content.startDateSelectFmt(this, 'endDate')" value="${startDate }" />
					</span>
					<span>至</span>
					<span class="bt_span">
						<input type="text" class="bt_t" id="endDate" name="endDate"
							onFocus="expensesQuery.content.endDateSelectFmt(this, 'startDate')" value="${endDate }" />
					</span>
				</p>

				<input type="hidden" name="pageNum" value="1" id="pageNum">
				<input type="hidden" name="type" value="${type }">
				<c:if test="${type ne 1 }">
					<p class="p_buttom p_buttom22">
						<span class="bt_prompt">内容描述：</span>
						<input class="bt_promptinput" type="text" name="content" value="${content }">
					</p>
				</c:if>
			</form>

			<p class="rightbottomB">
				<input type="button" class="bt_button" id="resetCondition" value="重置" />
				<input type="button" class="bt_button" id="queryConfirm" value="搜索" />
			</p>
		</div>
		<!--查询-->
		<div class="threeoprea">
			<p class="threeopreaP">
				<c:if test="${type eq 0 }">
					<a href="/admin/release/toAddSingleJdd" class="singleimpo defaultath">单条导入</a>
					<a href="/admin/release/toInportJdd" class="moreimpo">批量导入</a>
					<a href="/admin/release/uploadTemplate?tempType=0" class="Tempdownload">模版下载</a>
				</c:if>
				<c:if test="${type eq 1 }">
					<a href="/admin/release/toInportJk">批量导入</a>
					<a href="/admin/release/uploadTemplate?tempType=1">模版下载</a>
				</c:if>
			</p>
		</div>

		<table id="resultContent" border="0" cellpadding="0" cellspacing="0">
			<tr class="title">
				<th scope="col">序号</th>
				<th scope="col">提交人</th>
				<th scope="col">公司名称</th>
				<c:if test="${type ne 1 }">
					<th scope="col">内容描述</th>
				</c:if>
				<th scope="col">时间</th>
				<th scope="col">状态</th>
				<th scope="col">操作</th>
			</tr>

			<c:forEach var="jddRelease" items="${pageInfo.list }" varStatus="status">
				<tr>
					<th scope="col">${status.index+((pageInfo.pageNum-1)*10)+1 }</th>
					<th scope="col">${jddRelease.createName}</th>
					<th scope="col">${jddRelease.userCom }</th>
					<c:if test="${type eq 0 }">
						<th scope="col">${fn:substring(jddRelease.content, 0, 20)}</th>
					</c:if>
					<th scope="col"><fmt:formatDate type="BOTH" value="${jddRelease.createTime }" /></th>
					<th scope="col"><c:if test="${jddRelease.ststus eq 0 }">
							审核中
						</c:if> <c:if test="${jddRelease.ststus eq 2 }">
							审核通过
						</c:if> <c:if test="${jddRelease.ststus eq 1 }">
							已驳回
						</c:if></th>
					<th scope="col"><c:if test="${type eq 0}">
							<a href="/admin/release/jddView?releaseGuid=${jddRelease.guid }">
						</c:if> <c:if test="${type eq 1}">
							<a href="/admin/release/jkView?releaseGuid=${jddRelease.guid }">
						</c:if> 查看详情 </a></th>
				</tr>
			</c:forEach>
			</div>

		</table>
		<!--分页-->
		<div class="pagetotal">
			<span class="pagetotaLeft" style="padding-top: 5px;padding-right: 5px">共${pageInfo.pages}页,${  pageInfo.total}条</span>
			<div id="page"></div>
		</div>

		<!--分页-->
	</div>

	<script type="text/javascript" src="<%=path%>/js/admin/expensesQuery.js"></script>


	<script>
		$(document).ready(function() {
			//重置
			$("#resetCondition").click(function() {
				$("#startDate").val('');
				$("#endDate").val('');
				$("#content").val('');

			});

			$("#queryConfirm").click(function() {
				$("#from").submit();
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
						var num = obj.curr;
						var startDate = $("input[name='oldStartDate']").val();
						var endDate = $("input[name='oldEndDate']").val();
						var content = $("input[name='oldContent']").val();

						$("#startDate").val(startDate);
						$("#endDate").val(endDate);
						$("#content").val(content);
						$("#pageNum").val(num);
						$("#from").submit();

					}
				}
			});
		});
	</script>
</body>
</html>

