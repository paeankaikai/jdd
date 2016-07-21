<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

	<meta content="width=device-width, initial-scale=1.0" name="viewport" />
	<meta content="Geely|HR-Portal" name="description" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

	<!--支持低版本ie  -->
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<!-- 让部分国产浏览器默认采用高速模式渲染页面 -->
	<meta name="renderer" content="webkit">


<link rel="stylesheet" type="text/css" href="<%=path %>/css/bootstrap/css/bootstrap-theme.min.css" />
<link rel="stylesheet" type="text/css" href="<%=path %>/css/bootstrap/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="<%=path %>/js/layer/skin/layer.css" />






<script type="text/javascript" src="<%=path %>/js/jquery11.min.js" ></script>

<script type="text/javascript" src="<%=path %>/js/bootstrap/bootstrap.min.js"></script>

<!-- jayer弹出层 -->
<script type="text/javascript" src="<%=path %>/js/layer/layer.js"></script>

<script type="text/javascript" src="<%=path %>/js/laypage/laypage.js"></script>


<script type="text/javascript" src="<%=path %>/js/common/WdatePicker.js"></script>








