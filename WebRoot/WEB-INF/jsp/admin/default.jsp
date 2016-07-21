<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/include.jsp"%>

<%@ include file="../common/jstl.jsp"%>

<!DOCTYPE HTML>
<head>
<link rel="stylesheet" type="text/css" href="/css/home/home.css" />
<link rel="stylesheet" type="text/css" href="/css/admin/reset.css"/>
<link rel="stylesheet" type="text/css" href="/css/admin/common.css"/>
<link rel="stylesheet" type="text/css" href="/css/admin/thems.css">

<style type="text/css">
.tb960x90 {display:none!important;display:none}
#foot{height:110px;}
</style>
</head>

<body>
<div class="mian clearfix">
<iframe class="head" frameborder="0" src="/admin/default/header"   scrolling="no">&nbsp;</iframe>
<div  style="width:1000px; margin:0 auto;"><iframe id="FrameLeft" name="FrameLeft" class="left" frameborder="0" src="/admin/default/left" >&nbsp;</iframe>
<iframe id="FrameRight" name="FrameRight"  style="height:700px;" class="right" frameborder="0" src="${href }"  >&nbsp;</iframe></div>
<iframe id="foot" class="foot" frameborder="0" src="/admin/default/foot"  scrolling="no" >&nbsp;</iframe>
</div>

</body>
</html>
