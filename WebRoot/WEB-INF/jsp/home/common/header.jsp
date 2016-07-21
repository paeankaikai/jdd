<%@page pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/home/common/jstl.jsp"%>

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
	width: 180px;
	z-index:745;
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
	z-index:884;
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

<input type="hidden" id="ctx" value="${ctx}" />
 
 
 
 
<div class="jddheader">
	<div class="jddheaderin">
		<p>
			<a href="/home/default/home.do">
				<img src="/img/home/jddlogo.png" />
			</a>
			<span>吉点点</span>
		</p>
		
		<div class="jddheaderRight">
			<a href="javascript:void(0)" class="jddheaderRight2ser">
				<img src="/img/home/jddletopimg.png" class="jddtouxiang" />
			</a>
			<div class="headerhoverup">
				<span class="welcomeuser">欢迎你！${userInfo.name }</span>
				<span class="upimgshow"></span>
				<ul class="headerhoverupUL">
					<c:if test="${userInfo.userRights.level ne 1 }">
						<li>
							<a href="/admin/default">后台管理</a>
						</li>
					</c:if>
					<li>
					
					
						<a href="/logout">注销</a>
					</li>
				</ul>
			</div>
		</div>
		<a href="/home/default/encyclopedia" class="dabaike">吉点点大百科</a>
	</div>
</div>


<script type="text/javascript">
	$(function() {
		//头部下拉菜单
		$(".jddheaderRight").hover(function() {
			if ($(".upimgshow").hasClass("upimgshow2")) {
				$(".upimgshow").removeClass("upimgshow2");
				$(".headerhoverupUL").hide();
				$(this).removeClass("jddheaderRightBG");
			} else {
				$(".upimgshow").addClass("upimgshow2");
				$(".headerhoverupUL").show();
				$(this).addClass("jddheaderRightBG");
			}

		});

	})
</script>


