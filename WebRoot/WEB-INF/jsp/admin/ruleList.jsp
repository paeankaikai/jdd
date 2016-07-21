<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%-- <%@ include file="../common/include.jsp"%> --%>

<%@ include file="../common/jstl.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>Bootstrap Tree View</title>
<link href="http://libs.useso.com/js/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet">
<link href="/css/bootstrap/css/bootstrap-treeview.css" rel="stylesheet">


<script type="text/javascript" src="<%=path%>/js/jquery11.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/layer/layer.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap/bootstrap.min.js"></script>
<style type="text/css">
	body{background:#f7f7f7;}
	.container{padding-left:0px;margin-left:0px;background:#f7f7f7;}
	 .layui-layer-setwin a{background:url("/img/common/closedivbg.png") no-repeat !important;}
    .containerH4{height:40px;margin:0px; margin-bottom:10px;padding:0px;background:#FFFFFF;}
    .containerH4 a{width:180px;color:#666666;font-size:14px;line-height:40px;text-align:center;float:left;}
    .containerH4 span{float:left;line-height:40px;color:#ebedee;}
    .defaultjddA{border-bottom:2px solid #5dace2;font-weight:bold;}
    .containerOpera h4{height:30px;background:#e8ebf2;margin:0px;padding:0px;}
    .containerOpera a{margin-left:20px; display:block;float:left;padding-left:20px;line-height:30px;width:45px;font-size:12px; color:#666666;}
    #add{background:url("/img/common/addmessa.png") 0px 9px no-repeat;}
    #edit{background:url("/img/common/jddmodifybg.png") 0px 9px no-repeat;}
    #dele{background:url("/img/common/jdddeletebg.png") 0px 9px no-repeat;}
     .layui-layer-title{background:#1089e4!important;color:#FFFFFF!important;}

</style>
<script type="text/javascript">
      $(document).ready(function(){
    	   $(".containerH4 a").click(function(){
    		   $(this).addClass("defaultjddA").siblings("a").removeClass("defaultjddA");    
    	   });
      });
</script>
</head>
<body>
	<div class="container">
		<h4 class="containerH4">
			<a href="/admin/rule/ruleList?type=0" >吉点点收入类别配置</a><span>|</span>
			<a href="/admin/rule/ruleList?type=1">吉点点支出类别配置</a>
		</h4>
	<div class="containerOpera">	
		<h4>
			<a id="add" href="javascript:void(0);" onclick="ruleAdd(this)">新增</a>
			<a id="edit1" href="javascript:alert('目前不允许修改');">修改</a>
			<a id="dele1" href="javascript:alert('目前不允许删除');">删除</a>
		</h4>
		   <div class="row">
			     <div class="col-sm-12">
				   <div id="treeview" />
			 	   </div>
		    </div>
	     </div>
	   </div>
	<script src="/js/bootstrap/bootstrap-treeview.min.js"></script>
	<script type="text/javascript">
		$(function() {		
			if(${type}==0){
				$('.containerH4 a').first().addClass('defaultjddA');
				$('.containerH4 a').last().removeClass('defaultjddA');
			}
			else{			
				$('.containerH4 a').last().addClass('defaultjddA');
				$('.containerH4 a').first().removeClass('defaultjddA');
			}
			
			var content = "/admin/rule/toAddRule?levels=0&parentGuid=0&type=" + ${type};
			var level = 0;
			var tguid = null;

			var options = {
				bootstrap2 : false,
				showTags : true,
				levels : 1,
				data : ${tree}
			//data:tree
			};

			$('#treeview').treeview(options);

			//节点选中时触发
			$('#treeview').on(
					'nodeSelected',
					function(event, data) {
						/* jddRule是个数组，问我为什么，我也不清楚 */
						var jddRule = data.jddRule;
						level = jddRule[7];
						tguid = jddRule[0];
						content = "/admin/rule/toAddRule?levels=" + level + "&parentGuid=" + tguid
								+ "&type=" + ${type};
						//	$("#add").attr("href","/admin/rule/add?level="+level +"&parentGuid="+parentGuid);
						//	alert(JSON.stringify(jddRule[8]));

					});

			//节点取消选中时触发
			$('#treeview').on('nodeUnselected', function(event, data) {
				//$("#add").attr("href", "/admin/rule/addRule?levels=0&parentGuid=0");
				level = 0;
				tguid = null;
				content = "/admin/rule/toAddRule?levels=0&parentGuid=0&type=" + ${type};
			});

			/* 删除 */
			$("#dele").click(function() {
				if (tguid == null) {
					layer.msg('请先选择一行');
					return;
				}
				layer.confirm('您确定要删除吗？', {
					btn : [ '确认', '返回' ]
				//按钮
				}, function() {
					$.post("/admin/rule/deleRule", {
						tguid : tguid,
						levels : level
					}, function(data) {
						if (data == "success") {
							window.location.href = "/admin/rule/ruleList?type=" + ${type};
						}

					});

				}, function() {
					return;
				});

			});

			//修改
			$("#edit").click(function() {
				if (tguid == null) {
					layer.msg('请先选择一行');
					return;
				}

				layer.open({
					type : 2,
					title : '修改' + level + '级规则',
					area : [ '400px', '200px' ],
					fix : false, //不固定
					content : "/admin/rule/toEditRule?guid=" + tguid
				});

			});

			/* 添加 */
			ruleAdd = function() {

				if (level == 3) {
					layer.msg('已经是最后一级了！');
					return;
				}

				var index = layer.open({
					type : 2,
					title : '新增' + (level + 1) + '级规则',
					area : [ '400px', '200px' ],
					fix : false, //不固定
					content : content
				});

			}

		});
	</script>
</body>