<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/include.jsp"%>

<%@ include file="../common/jstl.jsp"%>

<html >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<link rel="stylesheet" type="text/css" href="/css/home/home.css" />
<link rel="stylesheet" type="text/css" href="/css/admin/reset.css"/>
<link rel="stylesheet" type="text/css" href="/css/admin/common.css"/>
<link rel="stylesheet" type="text/css" href="/css/admin/thems.css">

<title>发放记录</title>

<link rel="stylesheet" type="text/css" href="/css/admin/releaseQuery.css">
<link rel="stylesheet" type="text/css" href="/css/home/extend.css">
<style type="text/css">
#resultContent{width:740px;margin:0 20px;}
#resultContent .title{background:#FFFFFF;}
#resultContent .title{color:#666666;font-size:14px;}
#resultContent  td{text-align:left;padding:0 10px;font-size:12px;color:#666666;line-height:22px;}
.datewidth{width:140px;}
#resultContent  td.numwidth{width:45px;padding-left:15px;text-align:center;}
.p_top{width:780px;margin-top:10px;}
.p_top span,.p_middle span,.p_buttom span,.p_buttom{color:#666666;font-size:12px;font-weight:normal;}
.p_toppleft{float:left;margin-right:20px;}
.p_toppleft select,.p_middle select,.bt_t{border:1px solid #ebedee;width:130px;height:25px;color:#999999;}
.p_middle{clear:both;}
.p_middle select{width:320px;}
.p_buttom{width:780px;}
.p_buttom .bt_prompt{margin-right:25px; }
.threebutbtom {padding-right:20px;margin:15px 0; position:absolute; right:0px;bottom:10px;}
.threebutbtom input{width:60px;margin-left:20px; height:25px;border:0px;background: #999999;color:#FFFFFF;}
#queryConfirm{background: #1089e4;color: #FFFFFF;}
.bt_checkbox{width:16px;margin-right:5px;height:16px;border: 1px solid #28ae60;}
.bt_checkbox2{  background: url("/img/common/checkboxS22.png") 1px 1px no-repeat;}
.p_buttom3sr span{float:left;}
.bt_span23ac{position:relative;top:4px;}
#conditionContent{padding-bottom:10px; position:relative;overflow:hidden;border-bottom:1px solid #ebedee; }
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
			<div class="p_top">
			   <p class="p_toppleft">
					<span class="tt_prompt">一级类别：</span> 
					<span class="tt_span">
					<select id="gradRules" onChange="releaseQuery.content.gradRuleChange()" >
						<option value="0">全部</option>
						<c:forEach var="message" items="${gradRules}" varStatus="status">
							<option value="${message.guid}">${message.className}</option>
				    	</c:forEach>
					 </select>
					 </span>
				</p>
				<p class="p_toppleft">
					<span class="tt_prompt">二级类别：</span> 
					<span class="tt_span">
					<select id="fatherRules" onChange="releaseQuery.content.fatherRuleChange()">
					    <option value="0">全部</option>
					</select>
					</span>
			    </p>
			   <p class="p_toppleft">
					<span class="tt_prompt">三级类别：</span> 
					<span class="tt_span">
					<select id="childRules" >
					    <option value="0">全部</option>
					</select>
					</span>
			     </p>
			</div>
			<div class="p_middle">
				<span class="mt_prompt">费用来源：</span> 
				<span class="mt_span">
				<select id="costSources" >
					<option value="0">全部</option>
					<c:forEach var="message" items="${costSources}" varStatus="status">
						<option value="${message.guid}">${message.paramValue}</option>
			    	</c:forEach>
				</select>
				
				</span>
			</div>
			<div class="p_buttom p_buttom3sr">
				<span class="bt_prompt">时间：</span> 
				<span class="bt_span"><input type="text" class="bt_t" id="startDate" name="startDate" onFocus="releaseQuery.content.startDateSelectFmt(this, 'endDate')"/></span><span>至</span>
				<span class="bt_span"><input type="text" class="bt_t" id="endDate" name="endDate" onFocus="releaseQuery.content.endDateSelectFmt(this, 'startDate')"/></span>
				<span class="bt_span bt_span23ac"><label for="isGroupBy"><input type="checkbox" class="bt_checkbox" id="isGroupBy" name="isGroupBy"> 按收入类别汇总</label> </span>				
			</div>
			<p class="threebutbtom">
		        <input type="button" class="bt_button" id="dataExport" value="导出" onclick="releaseQuery.content.doExport()"/>
				<input type="button" class="bt_button" id="resetCondition" value="重置" onclick="releaseQuery.content.doReset()"/>
				<input type="button" class="bt_button" id="queryConfirm" value="搜索" onclick="releaseQuery.content.doSearch(1)"/>
			</p>		
		</div>
		<!--查询-->
		<table id="resultContent" border="0" cellpadding="0" cellspacing="0">
			<tr class="title">
				<th style="width:60px;">1序号</th>
				<th>发放类别</th>
				<th>费用来源</th>
				<th style="width:60px;">数量</th>
				<th>时间</th>
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
	<script type="text/javascript" src="<%=path %>/js/admin/releaseQuery.js"></script>
	<script>
	$(document).ready(function() {
		releaseQuery.content.init();
	  
	});
    </script>
</body>
</html>

