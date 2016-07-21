<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/home/common/jstl.jsp"%>

<!DOCTYPE HTML>
<html>
<head lang="en">
<title>吉点点达人榜</title>
<%@ include file="/WEB-INF/jsp/home/common/jsCss.jsp"%>
</head>
<body style="background:#F7F7F7;">
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
	<script type="text/javascript" src="${ctx}js/home/jddRanking.js"></script>
</body>
</html>
