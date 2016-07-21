<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/home/common/jstl.jsp"%>

<!DOCTYPE HTML>
<html>
<head lang="en">
<title>吉点点微送详情</title>
<%@ include file="/WEB-INF/jsp/home/common/jsCss.jsp"%>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/home/common/header.jsp" />
	<jsp:include   page="/WEB-INF/jsp/home/common/homeTop.jsp" flush="true"/>
	
	<div class="jddindex" style="margin-top: 25px">
		<div class=" per-content" style="margin-top:20px; ">
		    <div style="position:relative;width:930px;margin:0 auto;">
		        <table id="dg"></table>
		    </div>
		</div>
     </div>
	<input type="hidden" value="${page}" id="pageName"/>
	<script type="text/javascript" src="${ctx}js/home/weChatInfo.js"></script>
</body>
</html>
