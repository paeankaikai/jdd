<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/include.jsp"%>

<%@ include file="../common/jstl.jsp"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<link rel="stylesheet" type="text/css" href="/css/home/home.css" />
<link rel="stylesheet" type="text/css" href="/css/admin/reset.css"/>
<link rel="stylesheet" type="text/css" href="/css/admin/common.css"/>
<link rel="stylesheet" type="text/css" href="/css/admin/thems.css">

<title>兑换查询</title>

<link rel="stylesheet" type="text/css" href="/css/admin/exchangeQuery.css">
<link rel="stylesheet" type="text/css" href="/css/home/extend.css">
<style type="text/css">
 .p_top{padding:15px 0 10px 20px;}
 .p_top span{font-size:12px;color:#666666;font-weight:normal;}
 .p_condition{font-size:12px;color:#666666;width:780px;}
 .p_condition .ct_t{border:1px solid #ebedee;position:relative;top:3px;width:130px;height:25px;color:#999999;}
 .p_condition span{float:left;}
 .datetotime{height:30px;line-height:30px;font-size:12px; color:#666666;}
 #conditionContent{overflow:hidden;border-bottom:1px solid #ebedee;position:relative;}
 .threebtobut{position:absolute;bottom:13px;right:0px;padding-right:20px;}
 .threebtobut input{margin-left:20px;width:60px;margin-left:20px; height:25px;border:0px;background: #999999;color:#FFFFFF;}
 #queryConfirm{background: #1089e4;color: #FFFFFF;}
 #resultContent{width:740px;margin:0 20px;}
 #resultContent .title{background:#FFFFFF;}
 #resultContent .title{color:#666666;font-size:14px;}
 #resultContent  td{text-align:center;padding:0 10px;font-size:12px;color:#666666;}
 .page .current_pg{color: white; background-color: #AF0000;}
 .page a{background-color: #f1eff0;}
</style>
</head>

<body>
	<div class="maina">
		<!--查询-->
		<div class="eq_context" id="conditionContent">
			<p class="p_top">
				<span>开启吉点点之旅后共兑换吉课金币：</span>
				<span>${countInfo.jkCountTotal}</span>
				<span>（吉点点</span>
				<span>${countInfo.countTotal}</span>
				<span>）</span>
			</p>
			<p class="p_condition">
				<span class="ct_prompt">时间：</span> 
				<span class="ct_span"><input type="text" class="ct_t" id="startDate" name="startDate" onFocus="exchangeQuery.content.startDateSelectFmt(this, 'endDate')"/></span><span class="datetotime">至</span>
				<span class="ct_span"><input type="text" class="ct_t" id="endDate" name="endDate" onFocus="exchangeQuery.content.endDateSelectFmt(this, 'startDate')"/></span>
			</p>
			<p class="threebtobut">
				<input type="button" class="ct_button" id="dataExport" value="导出" onclick="exchangeQuery.content.doExport()"/>
				<input type="button" class="ct_button" id="resetCondition" value="重置" onclick="exchangeQuery.content.doReset()"/>
				<input type="button" class="ct_button" id="queryConfirm" value="搜索" onclick="exchangeQuery.content.doSearch(1)"/>
			</p>
		
		</div>
		<!--查询-->
		<table id="resultContent" border="0" cellpadding="0" cellspacing="0">
			<tr class="title">
				<th scope="col">序号</th>
				<th scope="col">兑换人</th>
				<th scope="col">吉课金币数量</th>
				<th scope="col">吉点点数量</th>
				<th scope="col">时间</th>
			</tr>
		</table>
		<!--分页-->
		<div id="pageContent" class="page">
            <span>共0页0条数据&nbsp</span>
            <a>首页&nbsp</a><a>上一页</a> <a>1</a> <a>下一页</a><a>&nbsp末页&nbsp</a>
            <span>跳到第<input type="text" style="width:25px" id="jumpPage">页<a>GO</a>
            <input type="hidden" id="maxPages" value="1"></span>
		</div>
		<!--分页-->
	</div>
	
	<script type="text/javascript" src="<%=path %>/js/common/extend.js"></script>
	<script>
	$(document).ready(function() {
		extend.msgBox.init();
		extend.dialog.init();
	});
	</script>
	
	<script type="text/javascript" src="<%=path %>/js/admin/exchangeQuery.js"></script>
	<script>
	$(document).ready(function() {
		exchangeQuery.content.init();
	  
	});
    </script>
</body>
</html>

