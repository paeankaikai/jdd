<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/home/common/jstl.jsp"%>

<!DOCTYPE HTML>
<html>
<head lang="en">
<title>吉点点前台首页</title>
<%@ include file="/WEB-INF/jsp/home/common/jsCss.jsp"%>
</head>
<body style="background:#f7f7f7">








	<jsp:include page="/WEB-INF/jsp/home/common/header.jsp" />

	<div class="jddindex">
		<div class="jddnotice">
			<div class="overflowS">
				<ul class="overflowUl" id="jddNotice_ul"></ul>
			</div>
		</div>
		<div class="jddshow">
			<!-- 我的吉点点 -->
			<div class="jddshow">
				<div class="jddshowLeft" id="myJDD">
					<div class="jddshowLTop">
						<img src="${ctx}img/home/jddletopimg.png" class="jddletopimg" />
						<p class="jddshowLTopRp">
							<span id="allMyJDD" class="jddnumB">
								<b id="allMyJDD23"></b>吉点点
							</span>
							<span id="allMyJDDOneYear">历年吉点点余额: </span>

						</p>
					</div>
					<div class="jddshowLBot">
						<ul id="myJddShow_ul">
							<li>
								<a href="${ctx}home/makeJdd/makeJdd.do">
									<img src="/img/home/leftlinkimg1.png" />
									<p>我要点</p>
								</a>
							</li>
							<li>
								<a href="${ctx}loginToJtt" target="_blank">
									<img src="/img/home/leftlinkimg2.png" />
									<p>我要花</p>
								</a>
							</li>
							<li style="border:0;">
								<a href="${ctx}home/want_g/showWantGive">
									<img src="/img/home/leftlinkimg3.png" />
									<p>我要送</p>
								</a>
							</li>
						</ul>
					</div>
				</div>

				<!-- 微送公示 -->
				<div class="jddshowMid" id="weChatInfo">
					<h3 id="weChatInfo_h3">
						<span>微送公示</span>
						<a href="${ctx}home/weChatInfo/weChatInfo.do">查看更多</a>
					</h3>
					<ul class="jddshowMidUl" id="weChatInfo_ul"></ul>
				</div>
				<!-- 吉点点达人榜 -->
				<div class="jddshowRight">
					<h3>
						<span>吉点点达人榜</span>
						<a href="${ctx}home/jddRanking/jddRanking.do">查看更多</a>
					</h3>
					<ul id="JddRanking_ul" class="rankjdd"></ul>
				</div>
			</div>
			<!-- 本年度收支信息 -->
			<div class="jddtable">
				<div class="jddtableLeft" id="inOutInfo">
					<h3>本年度收支明细</h3>
					<div class="jddtableLtext">
						<p class="jddtableLtextT">
							<span class="spanRslide">我的收入</span>
							<span>我的支出</span>
						</p>
						<div class="jddtableLtextCon">
							<div id="myJddInCome" class="jddtablextL"></div>
							<div id="myJddOutCome" class="jddtablextL jddtablextL22"></div>
						</div>
						<div class="totalnumb" id="myJddComeTotle"></div>
					</div>
				</div>
				<!-- 我的吉点点记录 -->
				<div class="jddrecord" id="myJDDInfo">
					<h3>
						<span>我的吉点点记录</span>
						<a href="${ctx}home/myJddRecord/myJddRecord.do">查看更多</a>
					</h3>
					<div class="jddrecordText">
						<div class="jddrecordTP">
							<span class="jddrecordTP1">序号</span>
							<span class="jddrecordTP2">类型</span>
							<span class="jddrecordTP3">数量</span>
							<span class="jddrecordTP4">原因</span>
							<span class="jddrecordTP5">时间</span>
						</div>
						<div class="jddrecordTtext" id="myJDDInfo_div"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="${ctx}js/home/home.js"></script>
	<div class="footerout">
		<div class="footer">
	        <span><a href="javascript:void(0)" id="fankui" target="_black">意见反馈</a>|</span>
			<p>© 2013 浙江吉利控股集团 浙ICP备11045738号-8</p>
		</div>
	</div>
</body>
</html>
