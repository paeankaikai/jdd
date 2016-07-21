<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/include.jsp"%>

<%@ include file="../common/jstl.jsp"%>

<html>
<head>
<title>后台头部</title>
<style type="text/css">
.jddheader {
	background: #1089e4;
	height: 50px;
}

.jddheaderin {
	width: 1000px;
	margin: 0 auto;
}

.jddheaderin p {
	float: left;
	height: 50px;
}

.jddheaderin p a,.jddheaderin p span {
	float: left;
}

.jddheaderin p span {
	line-height: 50px;
	font-size: 20px;
	font-family: "宋体";
	color: #FFFFFF;
	font-weight: bold;
}

.jddheaderRight {
	float: right;
	position: relative;
	z-index: 745;
}

.jddheaderRight .bordermid {
	line-height: 50px;
	color: #FFFFFF;
	float: left;
	font-size: 12px;
	margin: 0 15px;
}

.jddheaderRight a {
	line-height: 50px;
	color: #FFFFFF;
	font-size: 12px;
}

.jddtouxiang {
	height: 35px;
	width: 35px;
	margin: 8px 10px 7px 10px;
}

.jddheaderRight a,.jddheaderRight .headerhoverup {
	float: left;
}

.headerhoverup .welcomeuser {
	float: left;
	line-height: 50px;
	font-size: 12px;
	color: #FFFFFF;
}

.headerhoverup .upimgshow {
	background: url("/img/home/upimg.png");
	width: 8px;
	height: 4px;
	display: block;
	float: left;
	margin: 23px 15px 0 20px;
	cursor: pointer;
}

.headerhoverup .upimgshow2 {
	background: url("/img/home/upimg2.png");
}

.headerhoverupUL {
	width: 182px;
	position: absolute;
	top: 50px;
	left: -1px;
	display: none;
	padding: 0 10px;
	padding-top: 5px;
	background: #FFFFFF;
	border: 1px solid #0975c7;
	border-top: 0px;
	z-index: 884;
}

.headerhoverupUL li {
	height: 30px;
	line-height: 30px;
	font-size: 12px;
	color: #666666;
	border-top: 1px dashed #CCCCCC;
	padding-left: 5px;
}

.headerhoverupUL li:hover {
	background: #F7F7F7;
	color: #0975c7;
}

.jddheaderRightBG {
	background: #FFFFFF;
}

.jddheaderRightBG .welcomeuser {
	color: #666666;
}

.jddheaderRight2ser {
	width: 50px;
	height: 50px;
}
</style>

</head>

<body>
	<input type="hidden" id="ctx" value="${ctx}" />

	<div class="jddheader">
		<div class="jddheaderin">
			<p>
				<a id="backHome" href="javascript:void(0)">
					<img src="/img/home/jddlogo.png" />
				</a>
				<span>吉点点</span>
			</p>
			<div class="jddheaderRight">
				<!-- <a href="javascript:void(0)" class="jddheaderRight2ser">
				<img src="/img/home/jddletopimg.png" class="jddtouxiang" />
			</a> -->
				<div class="headerhoverup">
					<span class="welcomeuser">欢迎你！${userInfo.name }</span>
					<span class="bordermid">|</span>
				</div>
				<a href="javascript:void();" onclick="bake('/home/default/home.do')">返回首页</a>
				<span class="bordermid">|</span>
				<a href="/logout">注销</a>
			</div>

		</div>
	</div>

</body>
</html>

<script>
	$(function() {
		$("#logout").click(function() {
			bake("/logout");
		});

		$("#backHome").click(function() {
			bake("/home/default/home.do");
		});

		bake = function(url) {
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
