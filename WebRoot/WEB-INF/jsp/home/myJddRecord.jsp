<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/home/common/jstl.jsp"%>

<!DOCTYPE HTML>
<html>
<head lang="en">
<title>吉点点-收支详情</title>
<%@ include file="/WEB-INF/jsp/home/common/jsCss.jsp"%>
<style>
.sort_addques {
	background: #FFFFFF;
	width: 998px;
}

.datagrid {
	width: 1000px !important;
}
</style>
</head>
<body style="background:#F7F7F7;">
	<jsp:include page="/WEB-INF/jsp/home/common/header.jsp" />
	<jsp:include page="/WEB-INF/jsp/home/common/homeTop.jsp" flush="true" />

	<div class="jddindex" style="margin-top: 25px">
		<div class="sort_addques" style="padding: 0 0 0px 20px;">
			<div class="search_type">
				<p>
					开启吉点点之旅后共获得吉点点： <b id="totalInComNum">0</b> ， 消费吉点点： <b id="totalOutComNum">0</b> ; 当前账户余额： <b
						id="myJddNum">0</b> 吉点点。
				</p>
				<input type="hidden" id="firstModelId" name="firstModelId" />
				<ul class="search_ul" id="firstmodelName" name="firstmodelName"></ul>
			</div>

		</div>
		<div class="sort_addques" style="padding: 0 0 0px 20px; margin-top: 25px ">
			<div class="search_type">
				<p class="search_typeL">类型：</p>
				<p class="datep" style="margin-left:15px;">
					<input id="ruleType" class="customP easyui-combobox" style="width:173px;height: 30px;"
						placeholder="请选择收支类型">
				</p>
				<!-- 	<p class="search_typeL" style="margin-left:15px;">一级类型：</p> -->
				<p class="datep" style="margin-left:15px;">
					<input id="ruleGrad" class="customP easyui-combobox" style="width:173px;height: 30px;"
						placeholder="请选择一级类型">
				</p>
				<!-- <p class="search_typeL" style="margin-left:15px;">二级类型：</p> -->
				<p class="datep" style="margin-left:15px;">
					<input id="ruleFather" class="customP easyui-combobox" style="width:173px;height: 30px;"
						placeholder="请选择二级类型">
				</p>

			</div>
			<div class="asktime">
				<p class="search_typeL">时间:</p>
				<p class="datep" style="margin-left: 15px;">
					<input name="beginDate" type="text" id="beginDate" class="easyui-datebox"
						style="height: 30px; width: 140px; border: 1px solid #e0e5e8;" />
				</p>
				<p class="to">至</p>
				<p class="datep">
					<input name="endDate" type="text" id="endDate" class="easyui-datebox"
						style="height: 30px; width: 140px; border: 1px solid #e0e5e8;"
						validType="dateValid['#beginDate']" />
				</p>
				<button class="btn_search" id="search">搜索</button>
				<button class="btn_search" id="Reset">重置</button>
			</div>

		</div>
		<div class=" per-content" style="margin-top:20px; ">
			<div style="position:relative;width:1000px;margin:0 auto;">
				<table id="dg"></table>
			</div>
		</div>
	</div>
	<input type="hidden" value="${page}" id="pageName" />
	<script type="text/javascript" src="${ctx}js/home/myJddRecord.js"></script>
	<div class="footerout">
		<div class="footer">
		 <span><a href="javascript:void(0)" id="fankui" target="_black">意见反馈</a>|</span>
			<p>© 2013 浙江吉利控股集团 浙ICP备11045738号-8</p>
		</div>
	</div>
</body>
</html>

<script type="text/javascript">
	window.onload = function() {
		if (window.screen.height <= 768) {
			$(".jddindex").css("min-height", "365px");
		}
	}
</script>
