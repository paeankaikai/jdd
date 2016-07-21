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

<title>支出查询</title>

<link rel="stylesheet" type="text/css" href="/css/admin/expensesQuery.css">
<link rel="stylesheet" type="text/css" href="/css/home/extend.css">
<style>
    .p_buttom222{width:780px;}
    .p_top span,.p_middle span,.p_buttom span,.p_buttom{color:#666666;font-size:12px;font-weight:normal;}
    .p_top select{width:130px; height:25px;border:1px solid #ebedee;color:#999999;}
    .bt_prompt2fs2{margin-right:25px;}
    .bt_t{border:1px solid #ebedee;width:130px;height:25px;color:#999999;}
    .bt_checkbox{width:15px;margin-right:5px;height:15px;border: 1px solid #28ae60;}
	.bt_checkbox2{  background: url("/img/common/checkboxS22.png") 1px 1px no-repeat;}
	.p_buttom222 span{float:left;}
	.bt_span23ac{position:relative;top:4px;}
	#queryConfirm{background: #1089e4;color: #FFFFFF}
	.threebutbtom {padding-right:20px;margin:15px 0; position:absolute; right:0px;bottom:10px;}
	.threebutbtom input{width:60px;margin-left:20px; height:25px;border:0px;background: #999999;color:#FFFFFF;}
	#conditionContent{padding-bottom:10px; position:relative;overflow:hidden;border-bottom:1px solid #ebedee; }
	#resultContent{margin-top:0px;width:740px;margin:0 20px;}
	#resultContent tr.title{background:none!important;}
	#resultContent tr td{font-size:12px;}
    .page .current_pg{color: white; background-color: #AF0000;}
    .page a{background-color: #f1eff0;}
</style>
<script type="text/javascript">
      $(document).ready(function(){
    	  $(".bt_checkbox").click(function(){
    	        if($(this).hasClass("bt_checkbox2")){
    	        	$(this).removeClass("bt_checkbox2");
    	        }
    	        else{
    	        	$(this).addClass("bt_checkbox2");
    	        }
    	  });
      });
</script>
</head>

<body>
	<div class="maina">
		<!--查询-->
		<div class="eq_context" id="conditionContent">
			<p class="p_top">
				<span class="tt_prompt">支出类别：</span> 
				<span class="tt_span">				
				<select id="ruleChilds" >
					<option value="0">全部</option>
					<c:forEach var="message" items="${ruleChilds}" varStatus="status">
						<option value="${message.guid}">${message.className}</option>
			    	</c:forEach>
				</select>
				</span>
			</p>
			<p class="p_buttom p_buttom222">
				<span class="bt_prompt bt_prompt2fs2">时间：</span> 
				<span class="bt_span"><input type="text" class="bt_t" id="startDate" name="startDate" onFocus="expensesQuery.content.startDateSelectFmt(this, 'endDate')"/></span><span>至</span>
				<span class="bt_span"><input type="text" class="bt_t" id="endDate" name="endDate" onFocus="expensesQuery.content.endDateSelectFmt(this, 'startDate')"/></span>
				<span class="bt_span bt_span23ac"><label for="isGroupBy"><input type="checkbox"  class="bt_checkbox" id="isGroupBy" name="isGroupBy"> 按支出类别汇总 </label></span>
				
			</p>
			<p class="threebutbtom">
			   <span class="bt_span"><input type="button" class="bt_button" id="dataExport" value="导出" onclick="expensesQuery.content.doExport()"/></span>
				<span class="bt_span"><input type="button" class="bt_button" id="resetCondition" value="重置" onclick="expensesQuery.content.doReset()"/></span>
				<span class="bt_span"><input type="button" class="bt_button" id="queryConfirm" value="搜索" onclick="expensesQuery.content.doSearch(1)"/></span>
				
			</p>
		
		</div>
		<!--查询-->
		<table id="resultContent" border="0" cellpadding="0" cellspacing="0" >
			<tr class="title" style="background:#FFFFFF;">
				<th scope="col">序号</th>
				<th scope="col">支出类别</th>
				<th scope="col">数量</th>
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
	<script type="text/javascript" src="<%=path %>/js/admin/expensesQuery.js"></script>
	<script>
	$(document).ready(function() {
		expensesQuery.content.init();
	  
	});
    </script>
</body>
</html>

