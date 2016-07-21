<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<script type="text/javascript" src="/js/jquery11.min.js"></script>
<script type="text/javascript" src="/js/layer/layer.js"></script>
<script type="text/javascript" src="/js/laypage/laypage.js"></script>
<link rel="stylesheet" type="text/css" href="/css/admin/expensesQuery.css">
<link rel="stylesheet" type="text/css" href="/css/admin/index.css">


<%@ include file="../common/jstl.jsp"%>

<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<title></title>
<style type="text/css">
.import_detail table tr {
	font-size: 14px;
}

#filePath {
	margin-right: 20px;
}

.redcolor {
	color: #ff0000;
}

.greencolor {
	color: #00ff00;
}
.yellowcolor {
	color: yellow;
}

.back {
	margin: 15px 0 15px 15px;
	color: #333333;
	float: right;
	font-size: 14px;
	text-decoration: none;
	width: 70px;
	height: 25px;
	border: 1px solid #cccccc;
	text-align: center;
	line-height: 25px;
}

#page {
	height: 40px;
	padding: 15px 0;
     margin-left: 300px;
}
body{margin:0;}
.import_detail{width:778px;background:#FFFFFF;border:1px solid #ebedee;}
.import_detail table{margin:0 20px;}
.import_detail table td{font-size:12px; color:#666666;}
.import_listtable .import_listtableT{ background:#FFFFFF;}
.import_listtable .import_listtableT span{font-size:14px; color:#666666;font-weight:normal;}
.import_listtableT .listtableTS3{width:150px;overflow: hidden; height: 30px;}
.import_listtableT .listtableTS7{width:120px;}
.import_listtable .import_listtableT span{font-size:12px;}
.back{width:60px; heigbt:25px; color:#FFFFFF;background: #1089e4;font-size:12px;border:0px; margin-right:20px;}
.import_listtableT .listtableTS5,.import_listtableT .listtableTS4 {width: 120px;}
.pagetotal{height:40px;padding:20px 0 20px 30px;}
.pagetotal .pagetotaLeft{color:#666666;font-size:12px;line-height:30px;}
.pagetotal .pagetotaLeft,.pagetotal #page{float:left;}
#page{padding:0px; margin:0px;margin-left:20px;}
</style>
</head>

<body>
	<div class="import_detail">
		<table style="text-align: left">
			<tr height="30">
				<td width="400">
					<div class="importtr">
						录入方式:
							<c:if test="${ empty release.excelPath  }">系统录入</c:if>
						<c:if test="${release.excelPath eq 0 }">单条录入</c:if>
						<c:if test="${release.excelPath eq 1 }">批量导入</c:if>

					</div>
				</td>
				<td id="status"><c:if test="${release.ststus eq 0 }">
						<span class="redcolor">未审核</span>
					</c:if> <c:if test="${release.ststus eq 2 }">
						<span class="greencolor">审核通过</span>
					</c:if> <c:if test="${release.ststus eq 1 }">
						<span class="yellowcolor">已驳回</span></c:if></td>
			</tr>
			<tr height="30">
				<td >提交人:${release.createName }</td>
				<td >公司:${release.userCom }</td>
			</tr>
			
			
			<tr height="30">
				<td colspan="2">内容描述:${release.content }</td>
			</tr>

			<tr height="30">
				<td>审批文件: <a id="filePath"
						href="/admin/release/uploadFilePath?filePath=${release.filePath }">${release.filePath }</a>
					
				</td>
				<td>提交时间： <fmt:formatDate type="BOTH" value="${release.createTime }" /></td>
			</tr>

		</table>



		<div class="import_listtable">
			<p class="import_listtableT">
				<span class="listtableTS1">序号</span>
				<span class="listtableTS3" >收入类别</span>
				<span class="listtableTS3">费用来源</span>
				<span class="listtableTS4">员工</span>
				<!-- <span class="listtableTS5">姓名</span> -->
				<span class="listtableTS5">公司部门</span>
				<span class="listtableTS6">数量</span>
				<span class="listtableTS7">原因</span>
			</p>

		</div>

		<div class="pagetotal">
			  <span class="pagetotaLeft"></span>
		      <div id="page"></div>
		</div>


		<div class="bottombuttonT" style="display:none">
			<p>
				<a class="back" href="javascript:history.go(-1);location.reload()">返回</a>
				<a class="back" id="bohui" href="javascript:void(0);">驳回</a>
				<a class="back" id="insert" href="javascript:void(0);">审批通过</a>
			</p>
		</div>
		<div class="bottombuttonT2" style="display:block;">
			<p>
				<a class="back" id="back" href="javascript:void(0);">返回</a>
			</p>
		</div>


	</div>
</html>
</body>


<script>
	$(function() {

		if (${admin} == 1 && ${release.ststus} == 0) {
			$("div .bottombuttonT").css("display", "block");
			$("div .bottombuttonT2").css("display", "none");
		}

		
		$("#back").click(function() {
			if(${admin} == 1)
				window.location.href = "/admin/release/checkRelease";
			else
				window.location.href = "/admin/release/toReleaseManager";
		});

		var list = ${details};
		var num = 1;
		var page=Math.floor(list.length == 0 ? 1 : list.length % 10 == 0 ? list.length / 10: list.length / 10 + 1);
		$(".pagetotaLeft").text("共"+page+"页,"+list.length+"条");		

		laypage({
			cont : 'page',
			groups : 3, //连续显示分页数
			skip : true, //是否开启跳页
			skin : '#AF0000',
			pages : page, //可以叫服务端把总页数放在某一个隐藏域，再获取。假设我们获取到的是18
			curr : function() { //通过url获取当前页，也可以同上（pages）方式获取
				return num;
			}(),
			jump : function(obj, first) { //触发分页后的回调
				if (!first) { //一定要加此判断，否则初始时会无限刷新
					num = obj.curr;
					makeHtml(num);
				} else {
					makeHtml(1);
				}
			}
		});

		function makeHtml(num) {
			var html = "";
			for (var i = (num - 1) * 10, j = 0; i < list.length && j < 10; i++, j++) {
				var detail = list[i];
				html += "<div class='import_listtableBody'>" + "<p class='import_listtableT'>"
						+ "<span class='listtableTS1'>" + (i + 1) + "</span> "
						+ "<span class='listtableTS3'>" + detail[6]+ "</span> " 
						+ "<span class='listtableTS3'>" + detail[7] + "</span> "
						+ "<span class='listtableTS4'>" + (detail[2] +"/"+detail[3])+ "</span>"
						/* + "<span class='listtableTS5'>" + detail[3] + "</span>" */
						+ "<span class='listtableTS5' title='"+(detail[9] +"/"+detail[10])+"'>" + (detail[9] +"/"+detail[10]).substring(0,8)+ "</span>"
						+ "<span class='listtableTS6'>" + detail[4] + "</span>"
						+ "<span class='listtableTS7'>" + detail[5] + "</span>" 
						+ "</p></div>";
			}

			$("div").remove(".import_listtableBody");
			$(".import_listtable").append(html);
		}

		$("#insert").click(function() {
			$.post("/admin/release/checkJddView", {
				releaseGuid : '${release.guid}',
				ststus : 2
			}, function(data) {
				if (data == 2) {
					$("div .bottombuttonT").css("display", "none");
					$("div .bottombuttonT2").css("display", "block");
					$("#status").html('<span class="greencolor">审核通过</span>');
				}
			});
		});

		$("#bohui").click(function() {
			$.post("/admin/release/checkJddView", {
				releaseGuid : '${release.guid}',
				ststus : 1
			}, function(data) {
				if (data == 1) {
					$("div .bottombuttonT").css("display", "none");
					$("div .bottombuttonT2").css("display", "block");
					$("#status").html('<span class="yellowcolor">已驳回</span>');
				}
			});
		});

	})
</script>

