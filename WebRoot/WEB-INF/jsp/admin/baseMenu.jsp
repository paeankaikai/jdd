<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/include.jsp"%>

<%@ include file="../common/jstl.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>后台左侧</title>

<script type="text/javascript">
	$(function() {
		$('.yiji .erji').show();
		$('.yiji .yi').click(function() {
			$(this).siblings('.erji').toggle();
			$(this).children('em').toggleClass('zk');
		});
		$(".jddmainli .jddmainliP").click(function() {
			$(".jddsubul").hide();
			$(this).next().show();
			$(".upimgbg").removeClass("upimgbg2");
			$(this).children(".upimgbg").addClass("upimgbg2");
		})
		$(".jddsubul li").click(function() {
			$(this).addClass("jddsubunowli").siblings("li").removeClass("jddsubunowli")
		})
	});
</script>
<!-- <link rel="stylesheet" type="text/css" href="/css/home/home.css" />
<link rel="stylesheet" type="text/css" href="/css/admin/reset.css"/>
<link rel="stylesheet" type="text/css" href="/css/admin/common.css"/>
<link rel="stylesheet" type="text/css" href="/css/admin/thems.css"> -->

<style type="text/css">
body {
	font-family: "微软雅黑";
	margin: 0 auto;
	background: #e8ebf2;
}

p,ul,h1,h2,h3,h4,h5,h6 {
	margin: 0;
	padding: 0;
}

li {
	list-style: none;
	vertical-align: bottom;
}

a {
	text-decoration: none;
	outline: none;
	blur: expression(this.onFocus = this.blur ());
}

button {
	outline: none;
}

img {
	border: 0;
}

.tb960x90 {
	display: none !important;
	display: none
}

.coler {
	color: red;
}

.jddmenuleft {
	width: 200px;
	float: left;
	min-height: 530px;
	background: #e8ebf2;
}

.jddmainul .jddmainli {
	cursor: pointer;
}

.jddmainul .jddmainli .jddmainliP {
	line-height: 35px;
	padding-left: 45px;
	font-size: 14px;
	color: #666666;
	border-bottom: 1px solid #dedede;
	background: url("/img/common/menulibg.png") 22px 11px no-repeat;
}

.jddmainul .jddmainli .jddsubul {
	display: none;
}

.jddmainul .jddmainli .jddsubul li {
	height: 35px;
	padding-left: 50px;
	line-height: 35px;
	font-size: 14px;
	color: #666666;
}

.jddmainul .jddmainli .jddsubul li a {
	color: #666666;
	width:120px;height:35px;line-height:35px; display:block;
	text-decoration:none;
}

.upimgbg {
	float: right;
	background: url("/img/common/lidownbg.png") 0px 0px no-repeat;
	margin: 15px 20px 0 0;
	width: 11px;
	height: 6px;
}

.upimgbg2 {
	background: url("/img/common/liupbg.png") 0px 0px no-repeat;
}

.jddmainul .jddmainli .jddsubul li.jddsubunowli {
	background: #5cace2;
}

.jddmainul .jddmainli .jddsubul li.jddsubunowli a {
	color: #FFFFFF;
}
</style>






</head>

<body>
	<div class="jddmenuleft">
		<ul class="jddmainul">

			<!--  普通管理员-->
			<c:if test="${userInfo.userRights.level eq 2 }">
				<li class="jddmainli">
					<p class="jddmainliP">
						<span>发放管理</span>
					</p>
					<ul class="jddsubul">
						<li>
							<a href="/admin/release_q/showQuery" target="FrameRight">发放记录</a>
						</li>
						<li>
							<a href="/admin/release/toReleaseManager" target="FrameRight">发放管理 </a>
						</li>
					</ul>
				</li>
			</c:if>

			<!--集团人资普通管理员  -->
			<c:if test="${userInfo.userRights.level eq 3 }">
				<li class="jddmainli">
					<p class="jddmainliP">
						<span>发放管理</span>
					</p>
					<ul class="jddsubul" style="display:block;">
						<li class="jddsubunowli">
							<a href="/admin/release_q/showQuery" target="FrameRight">发放记录</a>
						</li>
						<li>
							<a href="/admin/expenses_q/showQuery" target="FrameRight">支出查询</a>
						</li>
						<li>
							<a href="/admin/release/toReleaseManager" target="FrameRight">发放管理 </a>
						</li>
					</ul>
				</li>
			</c:if>


			<c:if test="${userInfo.userRights.level eq 4 }">
				<li class="jddmainli">
					<p class="jddmainliP">
						<span>发放管理</span>
					</p>
					<ul class="jddsubul" style="display:block;">
						<li class="jddsubunowli">
							<a href="/admin/release/checkRelease" target="FrameRight">发放审核</a>
						</li>
					</ul>
				</li>
			</c:if>


			<c:if test="${userInfo.userRights.level eq 5 }">
				<li class="jddmainli">
					<p class="jddmainliP">
						<span>发放管理</span>
					</p>
					<ul class="jddsubul" style="display:block;">
						<li>
							<a href="/admin/release/toReleaseManager" target="FrameRight">发放管理 </a>
						</li>
						<li>
							<a href="/admin/exchange_q/showQuery" target="FrameRight">兑换查询</a>
						</li>
					</ul>
				</li>
			</c:if>

			<!-- 超级管理员 -->
			<c:if test="${userInfo.userRights.level eq 6 }">
				<li class="jddmainli">
					<p class="jddmainliP">
						<span>基本配置</span>					
					</p>
					<ul class="jddsubul" style="display:block;">
						<li class="jddsubunowli">
							<a href="/admin/message/messageList" target="FrameRight">消息设置</a>
						</li>
						<li>
							<a href="/admin/rule/ruleList" target="FrameRight">类型设置</a>
						</li>
						<li>
							<a href="/admin/grant/showSetting" target="FrameRight">发放设置</a>
						</li>
					</ul>
				</li>
			</c:if>




		</ul>
	</div>
</body>
</html>
