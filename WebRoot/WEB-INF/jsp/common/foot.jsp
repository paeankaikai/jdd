<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/include.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />


<link rel="stylesheet" type="text/css" href="/css/home/home.css" />
<link rel="stylesheet" type="text/css" href="/css/admin/reset.css"/>
<link rel="stylesheet" type="text/css" href="/css/admin/common.css"/>
<link rel="stylesheet" type="text/css" href="/css/admin/thems.css">


<style type="text/css">
.tb960x90 {display:none!important;display:none}</style>
</head>

<body>
<div class="footerout">
	<div class="footer">
		<p>© 2013 浙江吉利控股集团 浙ICP备11045738号-8</p>
	</div>
</div>
</body>
</html>

<script>
	$(function() {
		 $("#fankui").click(function(){
			 $(this).attr("href","http://feedback.evun.cn/");
		 });
		

		bake=function (url) {
			var userAgent = navigator.userAgent;
			if (userAgent.indexOf("Firefox") != -1 || userAgent.indexOf("Chrome") != -1) {
				window.top.location.href = url;
			} else {
				window.top.opener = null;
				window.top.open(url, "_self");
				window.top.close();
			}

		}

	})
	

</script>
