<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/include.jsp"%>

<%@ include file="../common/jstl.jsp"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="/css/home/home.css" />
<link rel="stylesheet" type="text/css" href="/css/admin/reset.css" />
<link rel="stylesheet" type="text/css" href="/css/admin/common.css" />
<link rel="stylesheet" type="text/css" href="/css/admin/thems.css">

<title>后台左侧</title>

<script type="text/javascript">
	$(function() {
		$('.yiji .erji').show();
		$('.yiji .yi').click(function() {
			$(this).siblings('.erji').toggle();
			$(this).children('em').toggleClass('zk');
		});
	});
</script>
<style type="text/css">
.tb960x90 {
	display: none !important;
	display: none
}
</style>
</head>

<body>

	<div class="box_l">
		<ul class="yiji">
			<!--子公司管理员角色  -->
			<c:if test="${userInfo.userRights.level eq 2 }">
				<li>
					<div class="yi">
						<em class="sq">&nbsp;</em>
						<span>
							<a href="/admin/release_q/showQuery" target="FrameRight">发放记录</a>
						</span>
					</div>
				</li>
				<li>
					<div class="yi">
						<em class="sq">&nbsp;</em>
						<span>
							<a href="/admin/release/toReleaseManager" target="FrameRight">发放管理 
						</span>
					</div>

				</li>
			</c:if>

			<!-- 超级 -->
			<c:if test="${userInfo.userRights.level eq 3 }">
			<li>
					<div class="yi">
						<em class="sq">&nbsp;</em>
						<span>
							<a href="/admin/release_q/showQuery" target="FrameRight">发放记录</a>
						</span>
					</div>
				</li>
				<li>
					<div class="yi">
						<em class="sq">&nbsp;</em>
						<span>
							<a href="/admin/expenses_q/showQuery" target="FrameRight">支出查询</a>
						</span>
					</div>
				</li>
				<li>
					<div class="yi">
						<em class="sq">&nbsp;</em>
						<span>
							<a href="/admin/release/toReleaseManager" target="FrameRight">发放管理 
						</span>
					</div>
				</li>
			</c:if>


			<!-- 审核管理员 -->
			<c:if test="${userInfo.userRights.level eq 4 }">
				<li>
					<div class="yi">
						<em class="sq">&nbsp;</em>
						<span>
							<a href="/admin/release/checkRelease" target="FrameRight">发放审核</a>
						</span>
					</div>

				</li>
			</c:if>


			<!-- 企业大学管理员 -->
			<c:if test="${userInfo.userRights.level eq 5 }">
				<li>
					<div class="yi">
						<em class="sq">&nbsp;</em>
						<span>
							<a href="/admin/exchange_q/showQuery" target="FrameRight">兑换查询</a>
						</span>
					</div>
				</li>

				<li>
					<div class="yi">
						<em class="sq">&nbsp;</em>
						<span>
							<a href="/admin/release/toReleaseManager" target="FrameRight">发放管理 
						</span>
					</div>
				</li>
			</c:if>

		</ul>
	</div>
</body>
</html>
