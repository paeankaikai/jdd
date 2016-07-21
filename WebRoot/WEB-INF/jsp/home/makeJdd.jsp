<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/home/common/jstl.jsp"%>

<!DOCTYPE HTML>
<html>
<head lang="en">
<title>吉点点-收支详情</title>
<%@ include file="/WEB-INF/jsp/home/common/jsCss.jsp"%>
<style type="text/css">
.return_nav span#lastTitle {
	color: #1089e4;
}

.jdruletable {
	background: #FFFFFF;
	min-height: 407px;
}

.per-content {
	margin: 0 20px;
}

.datagrid-header-row {
	height: 40px;
}

.datagrid-header {
	background: none;
	border-bottom: 1px solid #ebedee;
}

.datagrid .panel-body {
	border: 0px;
	color: #666666;
}
.panel-body-noheader{width:680px!important;}
</style>
</head>
<body style="background:#f7f7f7;">
	<jsp:include page="/WEB-INF/jsp/home/common/header.jsp" />
	<jsp:include page="/WEB-INF/jsp/home/common/homeTop.jsp" flush="true" />
	<div class="jdrule">
		<div class="jdruletop">
			<h3>规则说明：</h3>
			<p>1、通过吉点点规则获取吉点点。</p>
			<p>2、通过参加集团或公司组织的活动获得相应的吉点点。</p>
			<p>3、根据集团或公司相关规章制度奖励相应的吉点点。</p>
			<p>4、${changeNum }吉课金币 = 1吉点点</p>
			<p>5、吉课金币兑换时取${changeNum }的整数倍，剩余吉课金币作为下次折算</p>

		</div>
		<div class="jdruleLE">
			<div class="jdruleLeft">
				<div class="jdrulemid">
					<p>
						您有 <b id="jkCount">${jkcount }</b> 吉课金币可兑换 <b id="change"> </b> 吉点点, 已使用 <b>${usedjkcount }</b>
						吉课金币兑换<b id="usedchange"> </b> 吉点点
					</p>
					<!-- <a href="javascript:void(0)">兑换</a> -->
					<button type="button" id="flash" class="btn btn-default btn-sm" style="margin-bottom:4px">刷新</button>
					<button type="button" class="btn btn-default btn-sm" data-toggle="modal" data-target="#myModal"
						style="margin-left: 135px;margin-bottom:4px" id="exchagebuto">兑换</button>
				</div>
				<div class="jdrulebott">
					<h3>吉课金币兑换记录</h3>
					<div class="jdruletable">
						<div class=" per-content">
							<div style="position:relative;width:728px;margin:0 auto; height: 360px">
								<table id="dg"></table>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="jdruleRight">
				<ul>
					<li>
						<img src="/img/guaguale.png" />
						<p>
							<a href="javascript:void(0)">易云公司幸运刮刮乐</a>
						</p>
					</li>
					<li>
						<img src="/img/zajindan.png" />
						<p>
							<a href="javascript:void(0)">易云公司幸运金蛋</a>
						</p>
					</li>
					<li class="linoborder">
						<img src="/img/dazhuanpan.png" />
						<p>
							<a href="javascript:void(0)">集团年终幸运大转盘</a>
						</p>
					</li>
				</ul>
			</div>
		</div>
	</div>

	<!-- bootstarp Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">我要点兑换规则</h4>
				</div>
				<div class="modal-body">
					<div class="makeJddModal01">
						<h3>吉课金币兑换规则</h3>
						<p>1、${changeNum }吉课金币 = 1吉点点</p>
						<p>2、吉课金币兑换时取${changeNum }的整数倍，剩余吉课金币作为下次折算</p>
					</div>
					<div class="makeJddModal02">
						<p>
							您当前剩余即可金币数为： <b id="jkNum">${jkcount }</b> 最多兑换 <b id="jddNum"> <%-- <fmt:formatNumber type="number" value="${jkcount/changeNum}" maxFractionDigits="0" /> --%>
							</b> 吉点点
						</p>
						<p>
							兑换数量：
							<input type="text" size="5" id="jkNumInput" style="line-height:normal">
							吉点点(剩余 <label id="shengyu">${jkcount }</label> 吉课金币用于下次折算。)
						</p>
					</div>
				</div>
				<div class="modal-footer">
					<a class="btn btn-primary" id="save">确认兑换</a>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>



	<input type="hidden" value="${page}" id="pageName" />
	<script type="text/javascript" src="${ctx}js/home/makeJdd.js"></script>
	<script type="text/javascript" src="${ctx}js/home/home.js"></script>
	<div class="footerout">
		<div class="footer">
			 <span><a href="javascript:void(0)" id="fankui" target="_black">意见反馈</a>|</span>
			<p>© 2013 浙江吉利控股集团 浙ICP备11045738号-8</p>
		</div>
	</div>
</body>
</html>



<script>
	$(function() {
		var jkCount = ${jkcount};
		var changeNum = ${changeNum};
		var usedjkcount = ${usedjkcount};
		var count = Math.floor(jkCount / changeNum);

		$("#change").text(count);
		$("#jddNum").text(count);
		$("#usedchange").text(Math.floor(usedjkcount / changeNum));

		$("#jkNumInput").bind('input propertychange', function() {
			var tmptxt = $(this).val();
			$(this).val(tmptxt.replace(/\D|^0/g, ''));
			tmptxt = $(this).val();
			if (tmptxt > count) {
				layer.msg('大于最大兑换数');
				$(this).val('');
				$("#shengyu").text(jkCount);
			} else {
				$("#shengyu").text(jkCount - tmptxt * changeNum);
			}

		});

		$("#save").click(function(event) {
			//$(this).attr("href", "/home/makeJdd/exchangeJdd");

			event.stopPropagation();
			var num = $("#jkNumInput").val();
			if (num == null || num == '') {
				layer.msg('请输入兑换数量');
				return;
			}
			$.post("/home/makeJdd/exchangeJdd", {
				count : $("#jkNumInput").val()
			}, function(data) {
				if (data == 1) {
					window.location.href = "/home/makeJdd/makeJdd.do";
				}

			});

		});

		$("#flash").click(function() {
			$.post("/home/makeJdd/flashJk", {
				changeNum : changeNum
			}, function(data) {
				$("#jkCount").text(data.jkcount);
				$("#change").text(data.jddcount);

			});
		});

	})
</script>


