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

<title>发放配置</title>

<link rel="stylesheet" type="text/css" href="/css/admin/grantSetting.css">
<link rel="stylesheet" type="text/css" href="/css/home/extend.css">
<style type="text/css">
  body{background:#f7f7f7;}
  .contentAll{color:#333333;width:780px;background:#FFFFFF;border:1px solid #ebedee;}
  .contentSetBor{border:1px solid #CCCCCC;clear:both;overflow:hidden;}
  .titlePrompt{margin-left:20px;font-weight:normal;padding-left:0px;font-size:14px;color:#666666;}
  .contentSet{clear:both;padding:0 20px;}
  .contentSetBor{border:1px solid #ebedee;margin-bottom:20px;}
  .checkboxS{width:12px; height:12px;border:1px solid #28ae60;margin-right:10px;position:relative;top:4px;}
  .checkboxS22{background:url("/img/common/checkboxS22.png") 1px 1px no-repeat;}
  .enableSet span{float:left;display:block;color:#666666;}
  .limitSet{color:#666666;padding-left:250px;width:720px;}
  .limitSet span{float:left;}
  .setInput{width:50px;padding-left:20px; display:block;float:left;border:1px solid #ebedee;}
  .setButton{float:right;background:#1089e4;color:#FFFFFF;border:none;width:60px;height:23px;}
</style>
<script type="text/javascript">
      $(document).ready(function(){
    	  $(".checkboxS").click(function(){
    	        if($(this).hasClass("checkboxS22")){
    	        	$(this).removeClass("checkboxS22");
    	        }
    	        else{
    	        	$(this).addClass("checkboxS22");
    	        }
    	  });
      });
</script>
</head>

<body>
	<div class="contentAll">
		<p class="titlePrompt">微送设置:</p>
		<div class="contentSet">
		<div class="contentSetBor">
			<p class="enableSet">
			    <span id="microSendCountEnable" class="checkboxS  <c:if test="${microSendRuleInfo.microSendCountEnable==0}">checkboxS22</c:if> "></span>  
			   
			    <span>限制吉点点赠送次数</span>
			</p>
			<p class="limitSet">
				<span>每月可赠送 </span><input type="text" class="setInput" id="microSendCount" value="${microSendRuleInfo.microSendCount}"
				name="microSendCount" /><span>次</span> <input type="button" class="setButton"
			    id="countEnable" name="countEnable" value="确定" onclick="grantSetting.content.setCountLimit()"/>
			</p>
		</div>
		<div class="contentSetBor">
			<p class="enableSet">
				<span id='microSendNumEnable' class="checkboxS  <c:if test="${microSendRuleInfo.microSendNumEnable==0}">checkboxS22</c:if>"></span>
				<span>限制吉点点赠送数量</span> 
			</p>
			<p class="limitSet">
				<span>每次赠送不超过 </span><input type="text" class="setInput" id="microSendNum" value="${microSendRuleInfo.microSendNum}"
					name="microSendNum" /><span>吉点点</span> <input type="button" class="setButton"
					id="numEnable" name="numEnable" value="确定" onclick="grantSetting.content.setNumLimit()"/>
			</p>
			</div>
		</div>
	</div>
	
	<script type="text/javascript" src="<%=path %>/js/common/extend.js"></script>
	<script>
	$(document).ready(function() {
		extend.msgBox.init();
		extend.dialog.init();
	});
	</script>
	<script type="text/javascript" src="<%=path %>/js/admin/grantSetting.js"></script>
	<script>
	$(document).ready(function() {
		grantSetting.content.init();
	  
	});
    </script>
</body>
</html>

