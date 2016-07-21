<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/home/common/jstl.jsp"%>

<!DOCTYPE HTML>
<html>
<head lang="en">
<title>我要送</title>
<%@ include file="/WEB-INF/jsp/home/common/jsCss.jsp"%>
<link rel="stylesheet" type="text/css" href="/css/home/wantGive.css">
<link rel="stylesheet" type="text/css" href="/css/home/extend.css">
<style>
#outPageContent .current_pg,#inPageContent .current_pg {
	color: white;
	background-color: #AF0000;
}

#outPageContent a,#inPageContent a {
	background-color: #f1eff0;
	padding: 2px 6px;
	border: 1px solid #FFF;
	margin-left: 3px;
	margin-right: 3px;
	margin-top: 8px;
}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		$(".recordtabs a").click(function() {
			$(this).addClass("defaultanow").siblings().removeClass("defaultanow");
		});
	});
</script>
</head>
<body style="background:#F7F7F7;">
	<jsp:include page="/WEB-INF/jsp/home/common/header.jsp" />
	<jsp:include page="/WEB-INF/jsp/home/common/homeTop.jsp" flush="true" />

	<div class="jdweisong">
		<div class="jdweisongTop">
			<p>微送之旅开启后您与${empty totalInfo.friendTotal?0: totalInfo.friendTotal}个好友有微送往来,共赠送吉点点${empty totalInfo.outToatal?0: totalInfo.outToatal}个,获赠${empty totalInfo.inTotal?0: totalInfo.inTotal}个。</p>
			<a href="javascript:void(0)" onclick="wantGive.content.showDoGive()">微送</a>
		</div>
		<div class="weisongrecord">
			<p class="recordtabs">
				<a href="javascript:void(0)" onclick="wantGive.content.recordTypeSelected(1)"
					class="defaultanow">我的赠送记录</a>
				<span>|</span>
				<a href="javascript:void(0)" onclick="wantGive.content.recordTypeSelected(2)">我的获赠记录</a>
			</p>
			<div id="conditionContent" class="import_divmid">
				<input type="hidden" value="1" id="selectedRecordType" />
				<div class="import_divmiddiv1">
					<p>时间:</p>
					<span class="selectDateSpan">
						<input type="text" class="selectDate" id="startDate" name="startDate"
							onFocus="wantGive.content.startDateSelectFmt(this, 'endDate')" />
						&nbsp至 &nbsp
					</span>
					<span class="selectDateSpan">
						<input type="text" class="selectDate" id="endDate" name="endDate"
							onFocus="wantGive.content.endDateSelectFmt(this, 'startDate')" />
					</span>
				</div>
				<div class="import_divmiddiv1" id="toUserInput">
					<p>获赠方:</p>
					<input type="text" class="infodescible" id="toUser" />
				</div>
				<div class="import_divmiddiv1" id="fromUserInput" style="display:none;">
					<p>赠送人:</p>
					<input type="text" class="infodescible" id="fromUser" />
				</div>
				<p class="button_sear_set">
					<a href="javascript:void(0)" id="search" onclick="wantGive.content.doSearch(1)">搜索</a>
					<a href="javascript:void(0)" onclick="wantGive.content.doReset()">重置</a>
				</p>
			</div>
			<div id="outRecordShow" class="import_divtable">
				<p class="import_divtableT import_divtableT2">
					<span class="import_divtableTS1">序号</span>
					<span class="import_divtableTS2">获赠方</span>
					<span class="import_divtableTS3">吉点点数</span>
					<span class="import_divtableTS4">获赠原因</span>
					<span class="import_divtableTS5">时间</span>
				</p>

			</div>
			<div id="outPageContent" class="pageShow">
				<span>共0页0条数据&nbsp</span>
				<a>首页&nbsp</a>
				<a>上一页</a>
				<a>1</a>
				<a>下一页</a>
				<a>&nbsp末页&nbsp</a>
				<span>
					跳到第
					<input type="text" style="width:25px" id="jumpPageOut">
					页
					<a class="surebtom">确定</a>
					<input type="hidden" id="maxPagesOut" value="1">
				</span>
			</div>
			<div id="inRecordShow" class="import_divtable" style="display:none;">
				<p class="import_divtableT import_divtableT2">
					<span class="import_divtableTS1">序号</span>
					<span class="import_divtableTS2">赠送人</span>
					<span class="import_divtableTS3">吉点点数</span>
					<span class="import_divtableTS4">获赠原因</span>
					<span class="import_divtableTS5">时间</span>
				</p>

			</div>
			<div id="inPageContent" class="pageShow" style="display:none;">
				<span>共0页0条数据&nbsp</span>
				<a>首页&nbsp</a>
				<a>上一页</a>
				<a>1</a>
				<a>下一页</a>
				<a>&nbsp末页&nbsp</a>
				<span>
					跳到第
					<input type="text" style="width:25px" id="jumpPageIn">
					页
					<a>GO</a>
					<input type="hidden" id="maxPagesIn" value="1">
				</span>
			</div>
		</div>

	</div>
	<input type="hidden" value="wantGive" id="pageName" />
	<script type="text/javascript" src="<%=path%>/js/common/extend.js"></script>
	<script>
		$(document).ready(function() {
			extend.msgBox.init();
			extend.dialog.init();
			wantGive.content.recordTypeSelected(1);
		});
	</script>
	<script type="text/javascript" src="<%=path%>/js/home/wantGive.js"></script>
	<script>
		$(document).ready(function() {
			wantGive.content.init();

		});
	</script>
	<script type="text/javascript">
	window.onload = function() {
		if (window.screen.height <= 768) {
			$(".jdweisong").css("min-height", "400px");
		}
	}
</script>
	
	<div class="footerout">
		<div class="footer">
		 <span><a href="javascript:void(0)" id="fankui" target="_black">意见反馈</a>|</span>
			<p>© 2013 浙江吉利控股集团 浙ICP备11045738号-8</p>
		</div>
	</div>
</body>
</html>
