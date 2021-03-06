if (!exchangeQuery) var exchangeQuery = {};  
exchangeQuery.content= function () {

	var _init = function () {
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

    	var startDate = that.find("#startDate").val();
    	var endDate = that.find("#endDate").val();

    	var map = {"startDate":startDate, "endDate":endDate, "pageNum":pageNum};
    	jQuery.ajax({
    		url: "/admin/exchange_q/doExchangeSearch",   // 提交的页面 
    		data:map, // 从表单中获取数据 
    		type: "POST", // 设置请求类型为"POST"，默认为"GET" 
    		dataType: "json", 
    		contentType: "application/x-www-form-urlencoded;charset=UTF-8", // 必须要设置为UTF-8，否则提交数据乱码 
    		cache: false,
    		success: function(data) {
    			if(data.success){
    				var resultData = data.resultPageInfo; 
    				_createShow(resultData);
    				_setPages(resultData);
    			}
    		}, 
    		error : function(res) {
    		}
    	});

	};
	
	var _createShow = function(pageInfo) {
		var recordList = pageInfo.list;
		var content = "<tr class='title'>"
		    + "<th scope='col'>序号</th> "
		    + "<th scope='col'>兑换人</th> "
		    + "<th scope='col'>吉课金币数量</th> "
		    + "<th scope='col'>吉点点数量</th> "
		    + "<th scope='col'>时间</th> "
		    + "</tr>";
		for (var i = 0; i < recordList.length; i++) {
			content += "<tr>"
				+ "<td>" + (pageInfo.startRow + i) + "</td>"
				+ "<td>" + recordList[i].createName + "</td>"
				+ "<td>" + recordList[i].jkCount + "</td>"
				+ "<td>" + recordList[i].count + "</td>"
				+ "<td>" + recordList[i].createTime + "</td>"
				+ "</tr>";
		}		

		$("#resultContent").html(content);
	};
	
	//设置分页
	var _setPages = function(pageInfo) {
		var content = "<span>共" + pageInfo.pages + "页" + pageInfo.total + "条数据</span>"
		    + "<a href='#' onclick='exchangeQuery.content.doSearch(1)' >首页</a>";
		if(pageInfo.isFirstPage){
			content += "<a>上一页</a> ";
		}else{
			content += "<a href='#' onclick='exchangeQuery.content.doSearch(" + pageInfo.prePage + ")'>上一页</a> ";
		}
		
		if(pageInfo.pageNum < 3){
			for (var i = 1; i <= 5; i++) {
				if(i > pageInfo.pages){
					break;
				}
				
				if(i == pageInfo.pageNum){
					content += "<a href='#' class='current_pg' onclick='exchangeQuery.content.doSearch(" + i + ")'>" + i + "</a> ";
				}else{
					content += "<a href='#' onclick='exchangeQuery.content.doSearch(" + i + ")'>" + i + "</a> ";	
				}
			}
		}else{
			for (var i = pageInfo.pageNum - 2; i <= pageInfo.pageNum + 2; i++) {
				if(i > pageInfo.pages){
					break;
				}
				
				if(i == pageInfo.pageNum){
					content += "<a href='#' class='current_pg' onclick='exchangeQuery.content.doSearch(" + i + ")'>" + i + "</a> ";
				}else{
					content += "<a href='#' onclick='exchangeQuery.content.doSearch(" + i + ")'>" + i + "</a> ";	
				}
			}
		}
		
		if(pageInfo.isLastPage){
			content += "<a>下一页</a> ";
		}else{
			content += "<a href='#' onclick='exchangeQuery.content.doSearch(" + pageInfo.nextPage + ")'>下一页</a> ";
		}
		
		content += "<a href='#' onclick='exchangeQuery.content.doSearch(" + pageInfo.pages + ")' >末页</a>"
			+ "<span>跳到第<input type='text' style='width:25px' id='jumpPage'/>"
			+ "页<a href='#' onclick='exchangeQuery.content.doJump()'>GO</a><input type='hidden' id='maxPages' value=" + pageInfo.pages + " /></span>";

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
		
		that.find("#startDate").val('');
		
    	that.find("#endDate").val('');

	};
	
	//导出
	var _doExport=function(){
		var that = $("#conditionContent");

		var startDate = that.find("#startDate").val();
		var endDate = that.find("#endDate").val();

		var value = "startDate=" + startDate + "&endDate=" + endDate;

		window.location.href = '/admin/exchange_q/doExchangeExport?'+value;
		window.event.returnValue = false;

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
		}
		
	};

}();