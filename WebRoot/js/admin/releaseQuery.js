if (!releaseQuery) var releaseQuery = {};  
releaseQuery.content= function () {

	var _init = function () {
		$("#conditionContent").find("#selectAll_t").prop('checked',true);
		$("#conditionContent").find("#selectAll_m").prop('checked',true);
		_doSearch();
	};

	var _startTimeSelectFmt = function(obj, that) {
		WdatePicker({maxDate:'#F{$dp.$D(\'' + that + '\');}',startDate:obj.value});
		
	};
	
	var _endTimeSelectFmt = function(obj, that) {
		WdatePicker({minDate:'#F{$dp.$D(\'' + that + '\');}',startDate:obj.value});
		
	};
	
	//查询
	var _doSearch=function(pageNum){
		var that = $("#conditionContent");
		var gradRule = that.find("#gradRules").val();
		var fatherRule = that.find("#fatherRules").val();
		var childRule = that.find("#childRules").val();
    	var costSource = that.find("#costSources").val();

    	var startDate = that.find("#startDate").val();
    	var endDate = that.find("#endDate").val();

    	var isGroupBy = that.find("#isGroupBy").is(':checked');

    	var map = {"gradRule":gradRule,"fatherRule":fatherRule,"childRule":childRule,"costSource":costSource,"startDate":startDate,"endDate":endDate, "isGroupBy":isGroupBy, "pageNum":pageNum};
    	jQuery.ajax({
    		url: "/admin/release_q/doReleaseSearch",   // 提交的页面 
    		data:map, // 从表单中获取数据 
    		type: "POST", // 设置请求类型为"POST"，默认为"GET" 
    		dataType: "json", 
    		contentType: "application/x-www-form-urlencoded;charset=UTF-8", // 必须要设置为UTF-8，否则提交数据乱码 
    		cache: false,
    		success: function(data) {
    			if(data.success){
    				var resultData = data.resultPageInfo; 
    				if(isGroupBy){
    					_createGroupByShow(resultData);
    				}else{
    					_createShow(resultData);
    				}
    				
    				_setPages(resultData);
    			}
    		}, 
    		error : function(res) {
    		}
    	});

	};
	
	var _createShow = function(pageInfo) {
		var baserecordList = pageInfo.list;
		var content = "<tr class='title'>"
		    + "<th class='numwidth'>序号</th> "
		    + "<th>发放类别</th> "
		    + "<th>费用来源</th> "
		    + "<th>数量</th> "
		    + "<th class='datewidth'>时间</th> "
		    + "</tr>";
		for (var i = 0; i < baserecordList.length; i++) {	
			var s=baserecordList[i].sourceName;
			if(s=='所在单位/部门'){
				s+='----'+baserecordList[i].userCom;
			}
			
			content += "<tr>"
				+ "<td class='numwidth'>" + (pageInfo.startRow + i) + "</td>"
				+ "<td>" + baserecordList[i].childName + "</td>"
				+ "<td>" + s + "</td>"
				+ "<td>" + baserecordList[i].jddCount + "</td>"
				+ "<td class='datewidth'>" + baserecordList[i].createTime + "</td>"
				+ "</tr>";
		}		

		$("#resultContent").html(content);
	};
	
	var _createGroupByShow = function(pageInfo) {
		var baserecordList = pageInfo.list;
		var content = "<tr class='title'>"
		    + "<th scope='col'>序号</th> "
		    + "<th scope='col'>收入类别</th> "
		    + "<th scope='col'>费用来源</th> "
		    + "<th scope='col'>数量</th> "
		    + "</tr>";
		for (var i = 0; i < baserecordList.length; i++) {
			content += "<tr>"
				+ "<td>" + (pageInfo.startRow + i) + "</td>"
				+ "<td>" + baserecordList[i].childName + "</td>"
				+ "<td>" + baserecordList[i].sourceName + "</td>"
				+ "<td>" + baserecordList[i].jddCount + "</td>"
				+ "</tr>";
		}	
		$("#resultContent").html(content);
	};
	
	//设置分页
	var _setPages = function(pageInfo) {
		var content = "<span>共" + pageInfo.pages + "页" + pageInfo.total + "条数据</span>"
		    + "<a href='#' onclick='releaseQuery.content.doSearch(1)' >首页</a>";
		if(pageInfo.isFirstPage){
			content += "<a>上一页</a> ";
		}else{
			content += "<a href='#' onclick='releaseQuery.content.doSearch(" + pageInfo.prePage + ")'>上一页</a> ";
		}
		
		if(pageInfo.pageNum < 3){
			for (var i = 1; i <= 5; i++) {
				if(i > pageInfo.pages){
					break;
				}
				if(i == pageInfo.pageNum){
					content += "<a href='#' class='current_pg' onclick='releaseQuery.content.doSearch(" + i + ")'>" + i + "</a> ";
				}else{
					content += "<a href='#' onclick='releaseQuery.content.doSearch(" + i + ")'>" + i + "</a> ";	
				}
				
			}
		}else{
			for (var i = pageInfo.pageNum - 2; i <= pageInfo.pageNum + 2; i++) {
				if(i > pageInfo.pages){
					break;
				}
				if(i == pageInfo.pageNum){
					content += "<a href='#' class='current_pg' onclick='releaseQuery.content.doSearch(" + i + ")'>" + i + "</a> ";	
				}else{
					content += "<a href='#' onclick='releaseQuery.content.doSearch(" + i + ")'>" + i + "</a> ";	
				}
			}
		}
		
		if(pageInfo.isLastPage){
			content += "<a>下一页</a> ";
		}else{
			content += "<a href='#' onclick='releaseQuery.content.doSearch(" + pageInfo.nextPage + ")'>下一页</a> ";
		}
		
		content += "<a href='#' onclick='releaseQuery.content.doSearch(" + pageInfo.pages + ")' >末页</a>"
			+ "<span>跳到第<input type='text' style='width:25px' id='jumpPage'/>"
			+ "页<a href='#' onclick='releaseQuery.content.doJump()'>GO</a><input type='hidden' id='maxPages' value=" + pageInfo.pages + " /></span>";

		$("#pageContent").html(content);
	};
	
	//跳转
	var _doJump=function(){		
    	var value = $("#jumpPage").val();
    	var maxPages = $("#maxPages").val();
    	
    	if(value == ""){
    		extend.msgBox.alert({message : "请输入跳转页数！"});
    		$("#jumpPage").val('');
    		return
    	}else{
        	var Reg = new RegExp("^[1-9]\\d*$");
        	if(!Reg.test(value)){
        		extend.msgBox.alert({message : "请输入正确的跳转页数！"});
        		$("#jumpPage").val('');
        		return;
        	}
    	}
    	
    	if(Number(value) > Number(maxPages)){
    		extend.msgBox.alert({message : "您输入的页数超过当前最大页数！"});
    		$("#jumpPage").val('');
    		return;
    	}
    	
    	_doSearch(value);

	};
	
	//重置
	var _doReset=function(){
		var that = $("#conditionContent");
		
		that.find("#gradRules").val('0');
		var defaultContent = "<option value='0'  selected = 'selected' >全部</option>";
		$("#fatherRules").html(defaultContent);
		$("#childRules").html(defaultContent);
		
		that.find("#costSources").val('0');
		
		that.find("#startDate").val('');
		
    	that.find("#endDate").val('');
    	
    	that.find("#isGroupBy").prop("checked",false);

	};
	
	//导出
	var _doExport=function(){
		var that = $("#conditionContent");

		var gradRule = that.find("#gradRules").val();
		var fatherRule = that.find("#fatherRules").val();
		var childRule = that.find("#childRules").val();
    	var costSource = that.find("#costSources").val();

		var startDate = that.find("#startDate").val();
		var endDate = that.find("#endDate").val();

		var isGroupBy = that.find("#isGroupBy").is(':checked');

		var value = "gradRule=" + gradRule + "&fatherRule=" + fatherRule + "&childRule=" + childRule + "&costSource=" + costSource + "&startDate=" + startDate + "&endDate=" + endDate + "&isGroupBy=" + isGroupBy;

		window.location.href = '/admin/release_q/doReleaseExport?'+value;
		window.event.returnValue = false;

	};
	
	var _gradRuleChange=function(){
		var gradRuleId = $("#gradRules").val();
		
		var defaultContent = "<option value='0'  selected = 'selected' >全部</option>";
		$("#fatherRules").html(defaultContent);
		$("#childRules").html(defaultContent);
		
		if(gradRuleId != '0'){
			$.ajax({
				type:"Post",
				url:"/admin/release_q/queryChildRules?parentGuid=" + gradRuleId,
				async: false,   
				dataType : "json",
				contentType : "application/json;charset=UTF-8", 
				success:function(res){     	        	 
					if (res.success) {
						var childRules = res.childRules;
						var content = "";
						for (var i = 0; i < childRules.length; i++) {
							content += "<option value='"+childRules[i].guid+"'>"+childRules[i].className+"</option>";
						}
						
						$("#fatherRules").append(content);
					}
				},
				error:function(res){
				},
			});
		}

	};
	
	var _fatherRuleChange=function(){
		var fatherRuleId = $("#fatherRules").val();
		var defaultContent = "<option value='0'  selected = 'selected' >全部</option>";
		$("#childRules").html(defaultContent);
		
		if(fatherRuleId != '0'){
			$.ajax({
				type:"Post",
				url:"/admin/release_q/queryChildRules?parentGuid=" + fatherRuleId,
				async: false,   
				dataType : "json",
				contentType : "application/json;charset=UTF-8", 
				success:function(res){     	        	 
					if (res.success) {
						var childRules = res.childRules;
						var content = "";
						for (var i = 0; i < childRules.length; i++) {
							content += "<option value='"+childRules[i].guid+"'>"+childRules[i].className+"</option>";
						}
						
						$("#childRules").append(content);
					}
				},
				error:function(res){
				},
			});
		}

	};

	return {
		init : function() {
			_init();
		},startDateSelectFmt: function(obj, that){
			   _startTimeSelectFmt(obj, that);
		},endDateSelectFmt: function(obj, that){
			   _endTimeSelectFmt(obj, that);
		},doSearch: function(pageNum){
			   _doSearch(pageNum);
		},doReset: function(){
			   _doReset();
		},doExport: function(){
			   _doExport();
		},doJump: function(){
			   _doJump();
		},gradRuleChange: function(){
			   _gradRuleChange();
		},fatherRuleChange: function(){
			   _fatherRuleChange();
		}
		
	};

}();