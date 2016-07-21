if (!wantGive) var wantGive = {};  
wantGive.content= function () {

	var _init = function () {
		
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

		var selectedRecordType = that.find("#selectedRecordType").val();
		var userName = "";
		if(selectedRecordType == "1"){
			userName = that.find("#toUser").val();
		}else{
			userName = that.find("#fromUser").val();
		}
    	var startDate = that.find("#startDate").val();
    	var endDate = that.find("#endDate").val();

    	var map = {"recordType":selectedRecordType,"userName":userName,"startDate":startDate, "endDate":endDate, "pageNum":pageNum};
    	jQuery.ajax({
    		url: "/home/want_g/doMicroSendSearch",   // 提交的页面 
    		data:map, // 从表单中获取数据 
    		type: "POST", // 设置请求类型为"POST"，默认为"GET" 
    		dataType: "json", 
    		contentType: "application/x-www-form-urlencoded;charset=UTF-8", // 必须要设置为UTF-8，否则提交数据乱码 
    		cache: false,
    		success: function(data) {
    			if(data.success){
    				var resultData = data.resultPageInfo; 
    				if(selectedRecordType == "1"){
        				_createShowOut(resultData);
    				}else{
        				_createShowIn(resultData);
    				}
    				
    				_setPages(resultData, selectedRecordType);

    			}
    		}, 
    		error : function(res) {
    		}
    	});

	};
	
	var _createShowOut = function(pageInfo) {
		var recordList = pageInfo.list;
		var content = "<p class='import_divtableT import_divtableT2'>"
		    + "<span class='import_divtableTS1'>序号</span>"
		    + "<span class='import_divtableTS2'>获赠方</span>"
		    + "<span class='import_divtableTS3'>吉点点数</span>"
		    + "<span class='import_divtableTS4'>获赠原因</span>"
		    + "<span class='import_divtableTS5'>时间</span>"
		    + "</p>";
		for (var i = 0; i < recordList.length; i++) {
			content += "<div class='import_divtableBody import_divtableT2'>"
				+ "<p class='import_divtableT import_divtableT'>"
				+ "<span class='import_divtableTS1'>" + (pageInfo.startRow + i) + "</span>"
				+ "<span class='import_divtableTS2'>" + recordList[i].toUserName + "</span>"
				+ "<span class='import_divtableTS3'>" + recordList[i].count + "</span>"
				+ "<span class='import_divtableTS4'>" + recordList[i].reason + "</span>"
				+ "<span class='import_divtableTS5'>" + recordList[i].createTime + "</span>"
				+ "</p>"
				+ "</div>";
		}		

		$("#outRecordShow").html(content);
	};
	
	var _createShowIn = function(pageInfo) {
		var recordList = pageInfo.list;
		var content = "<p class='import_divtableT import_divtableT2'>"
		    + "<span class='import_divtableTS1'>序号</span>"
		    + "<span class='import_divtableTS2'>赠送人</span>"
		    + "<span class='import_divtableTS3'>吉点点数</span>"
		    + "<span class='import_divtableTS4'>获赠原因</span>"
		    + "<span class='import_divtableTS5'>时间</span>"
		    + "</p>";
		for (var i = 0; i < recordList.length; i++) {
			content += "<div class='import_divtableBody import_divtableT2'>"
				+ "<p class='import_divtableT import_divtableT'>"
				+ "<span class='import_divtableTS1'>" + (pageInfo.startRow + i) + "</span>"
				+ "<span class='import_divtableTS2'>" + recordList[i].fromUserName + "</span>"
				+ "<span class='import_divtableTS3'>" + recordList[i].count + "</span>"
				+ "<span class='import_divtableTS4'>" + recordList[i].reason + "</span>"
				+ "<span class='import_divtableTS5'>" + recordList[i].createTime + "</span>"
				+ "</p>"
				+ "</div>";
		}		

		$("#inRecordShow").html(content);
	};

	//设置分页
	var _setPages = function(pageInfo, recordType) {
		var content = "<span>共" + pageInfo.pages + "页" + pageInfo.total + "条数据&nbsp</span>"
		    + "<a href='#' onclick='wantGive.content.doSearch(1)' >首页&nbsp</a>";
		if(pageInfo.isFirstPage){
			content += "<a>上一页</a> ";
		}else{
			content += "<a href='#' onclick='wantGive.content.doSearch(" + pageInfo.prePage + ")'>上一页</a> ";
		}
		
		if(pageInfo.pageNum < 3){
			for (var i = 1; i <= 5; i++) {
				if(i > pageInfo.pages){
					break;
				}
				if(i == pageInfo.pageNum){
					content += "<a href='#' class='current_pg' onclick='wantGive.content.doSearch(" + i + ")'>" + i + "</a> ";
				}else{
					content += "<a href='#' onclick='wantGive.content.doSearch(" + i + ")'>" + i + "</a> ";	
				}
			}
		}else{
			for (var i = pageInfo.pageNum - 2; i <= pageInfo.pageNum + 2; i++) {
				if(i > pageInfo.pages){
					break;
				}
				if(i == pageInfo.pageNum){
					content += "<a href='#' class='current_pg' onclick='wantGive.content.doSearch(" + i + ")'>" + i + "</a> ";
				}else{
					content += "<a href='#' onclick='wantGive.content.doSearch(" + i + ")'>" + i + "</a> ";	
				}
			}
		}
		
		if(pageInfo.isLastPage){
			content += "<a>下一页</a> ";
		}else{
			content += "<a href='#' onclick='wantGive.content.doSearch(" + pageInfo.nextPage + ")'>下一页</a> ";
		}

		if(recordType == "1"){
			content += "<a href='#' onclick='wantGive.content.doSearch(" + pageInfo.pages + ")' >末页&nbsp</a>"
			+ "<span>跳到第<input type='text' style='width:25px' id='jumpPageOut'/>"
			+ "页<a href='#' onclick='wantGive.content.doJump()'>GO</a><input type='hidden' id='maxPagesOut' value=" + pageInfo.pages + " /></span>";
			$("#outPageContent").html(content);
		}
		
		if(recordType == "2"){
			content += "<a href='#' onclick='wantGive.content.doSearch(" + pageInfo.pages + ")' >末页&nbsp</a>"
			+ "<span>跳到第<input type='text' style='width:25px' id='jumpPageIn'/>"
			+ "页<a href='#' onclick='wantGive.content.doJump()'>GO</a><input type='hidden' id='maxPagesIn' value=" + pageInfo.pages + " /></span>";
			$("#inPageContent").html(content);
		}
		
	};
	
	//跳转
	var _doJump=function(){
		var recordType = $("#selectedRecordType").val();
		var value="0";
		var maxPages="1";
		if(recordType == "1"){
	    	value = $("#jumpPageOut").val();
	    	maxPages = $("#maxPagesOut").val();
		}else if(recordType == "2"){
	    	value = $("#jumpPageIn").val();
	    	maxPages = $("#maxPagesIn").val();
		}else{
			return;
		}
    	
    	if(value == ""){
    		extend.msgBox.alert({message : "请输入跳转页数！"});
    		$("#jumpPageOut").val('');
    		$("#jumpPageIn").val('');
    		return
    	}else{
        	var Reg = new RegExp("^[1-9]\\d*$");
        	if(!Reg.test(value)){
        		extend.msgBox.alert({message : "请输入正确的跳转页数！"});
        		$("#jumpPageOut").val('');
        		$("#jumpPageIn").val('');
        		return;
        	}
    	}
    	
    	if(Number(value) > Number(maxPages)){
    		extend.msgBox.alert({message : "您输入的页数超过当前最大页数！"});
    		$("#jumpPageOut").val('');
    		$("#jumpPageIn").val('');
    		return;
    	}
    	
    	_doSearch(value);
		$("#jumpPageOut").val('');
		$("#jumpPageIn").val('');
	};

	//重置
	var _doReset=function(){
		var that = $("#conditionContent");
		
		that.find("#toUser").val('');
		that.find("#fromUser").val('');
		that.find("#startDate").val('');
    	that.find("#endDate").val('');

	};
	
	//不同记录显示
	var _recordTypeSelected=function(type){
		if(type == 1){
			$("#outRecordShow").css('display', 'block');
			$("#inRecordShow").css('display', 'none');
			$("#toUserInput").css('display', 'block');
			$("#fromUserInput").css('display', 'none');
			$("#outPageContent").css('display', 'block');
			$("#inPageContent").css('display', 'none');
			$("#selectedRecordType").val('1');
			$("#search").click();
			
			
		}
		if(type == 2){
			$("#inRecordShow").css('display', 'block');
			$("#outRecordShow").css('display', 'none');
			$("#fromUserInput").css('display', 'block');
			$("#toUserInput").css('display', 'none');
			$("#inPageContent").css('display', 'block');
			$("#outPageContent").css('display', 'none');
			$("#selectedRecordType").val('2');
			$("#search").click();
			
		}
	};
	
	var _giveInit = function () {
		$("#dialog_doGive").remove();
		_create();
	};
	
    var _create = function() {
		var html = "<div id='dialog_doGive' class='jddalerttext'> "
		+"<input type='hidden' id='g_giveCount' /> "
		+"<input type='hidden' id='g_giveNum' /> "
		+"<div id='showPrompt' class='jddsongrule'> "
		+"</div> "
		+"<div class='gainjdd'> "
		+"<p> "
		+"<span>获赠人工号:</span> "
		+"<input type='text' id='toUserId' class='gainjddinput' onblur='wantGive.content.userValidator()'/> "
		+"</p> "
		+"<p> "
		+"<span>获赠人姓名:</span> "
		+"<input type='text' id='toUserName' class='gainjddinput' disabled='disabled'/> "
		+"<input type='hidden' id='toUserCom' /> "
		+"</p> "
		+"<p> "
		+"<span>获赠点数:</span> "
		+"<input type='text' id='toGiveNum' class='gainjddinput' onblur='wantGive.content.accountValidator()'/> "
		+"</p> "
		+"<p> "
		+"<span>赠送原因:</span> "
		+"<textarea id='toReason'></textarea> "
		+"</p> "
		+"</div> "
		+"</div> ";
		
		$("body:eq(0)").append($(html));
    };
    
	var _getDefaultValue = function () {	
    	jQuery.ajax({
    		url: "/home/want_g/getDefaultInfo",   // 提交的页面 
    		type: "POST", // 设置请求类型为"POST"，默认为"GET" 
    		dataType: "json", 
    		contentType: "application/json;charset=UTF-8", // 必须要设置为UTF-8，否则提交数据乱码 
    		cache: false,
    		success:function(data){     	        	 
    			if (data.success) {
    				 $("#g_giveCount").val(data.giveCount);
    				 $("#g_giveNum").val(data.giveNum);
    				 var ruleInfo = data.microSendRuleInfo;
    				 $("#dialog_doGive").data("ruleInfo",ruleInfo);
    				 
    				 var prompt = '';
    				 
    				 if(ruleInfo.microSendCountEnable == 0){
        				 if(ruleInfo.microSendNumEnable == 0){
        					 prompt = "每人每月最多赠送次数为" + ruleInfo.microSendCount + "次,最多赠送数量为" + ruleInfo.microSendNum + "点。";
        				 }else{
        					 prompt = "每人每月最多赠送次数为" + ruleInfo.microSendCount + "次.";
        				 } 
    				 }else{
    					 if(ruleInfo.microSendNumEnable == 0){
        					 prompt = "每人每月最多赠送数量为" + ruleInfo.microSendNum + "点。";
        				 }
    				 }
    				 
    				 if(prompt == ''){
    					 var content = "<p class='psomgtitle'>规则说明:</p> "
    						+"<p class='songrulelist'>. 限公司内部赠送</p> ";
    					 $("#showPrompt").html(content);
    				 }else{
    					 var content = "<p class='psomgtitle'>规则说明:</p> "
     						+"<p class='songrulelist'>. " + prompt + "</p> "
     						+"<p class='songrulelist'>. 限公司内部赠送</p> ";
    					 $("#showPrompt").html(content);
    				 }
    				 
    				 
    				  content = "<p class='psomgtitle'>我要送规则说明:</p> "
  						+"<p class='songrulelist'>1、得到同事的帮助支持或对提供的服务满意/认可或上级对下级的认可等，员工可赠送吉点点。</p> "
  						+"<p class='songrulelist'>2、系统首次自动赠送2个吉点点，作为公司内部员工之间互相赠送的点数，并通过微送进行吉点点的赠送。</p> "
  						+"<p class='songrulelist'>3、每次赠送" + ruleInfo.microSendNum + "点，共" + ruleInfo.microSendCount + "次/月，超过无法进行员工之间的互送。</p> ";
 					 $("#showPrompt").html(content);
    			}
    		},
    		error:function(data){
    		},
    	});

	};

    var _doGive = function () {
    	var toUserId = $("#toUserId").val();
    	var toUserName = $("#toUserName").val();
    	var toUserCom = $("#toUserCom").val();
    	var toGiveNum = $("#toGiveNum").val();
    	var toReason = $("#toReason").val();
    	var ruleInfo = $("#dialog_doGive").data("ruleInfo") || {};;
    	
    	if(toUserName == ""){
    		extend.msgBox.alert({message : "请输入正确获赠人工号！"});
    		return;
    	}
    	
    	if(toGiveNum == ""){
    		extend.msgBox.alert({message : "请输入赠送吉点点数！"});
    		return;
    	}
    	
    	var g_giveCount = $("#g_giveCount").val();
    	var g_giveNum = $("#g_giveNum").val();
    	if(ruleInfo.microSendCountEnable == 1){
    		var resCount = ruleInfo.microSendCount - Number(g_giveCount);
    		if(resCount <= 0){
        		extend.msgBox.alert({message : "您本月的赠送次数已经到达最大次数！"});
        		return;
    		}
    	}
    	
    	if(ruleInfo.microSendNumEnable == 1){
    		var resNum = ruleInfo.microSendNum - Number(g_giveNum);
    		if(resNum < toGiveNum){
        		extend.msgBox.alert({message : "您本月最多只能赠送吉点点为" + resNum + "点！"});
        		return;
    		}
    	}
    	
    	var map = {"toUserId":toUserId,"toUserName":toUserName,"toUserCom":toUserCom, "toGiveNum":toGiveNum, "toReason": toReason};
    	jQuery.ajax({
    		url: "/home/want_g/doJddGive",   // 提交的页面 
    		data:map, // 从表单中获取数据 
    		type: "POST", // 设置请求类型为"POST"，默认为"GET" 
    		dataType: "json", 
    		contentType: "application/x-www-form-urlencoded;charset=UTF-8", // 必须要设置为UTF-8，否则提交数据乱码 
    		cache: false,
    		success: function(data) {
    			if(data.success){
    				extend.dialog.close();
    				extend.msgBox.alert({message : "吉点点赠送成功点！"});
    				
    				_doSearch(1);
    			}
    		}, 
    		error : function(res) {
    		}
    	});

    };
    
    var _userValidator = function () {
    	var toUserId = $("#toUserId").val();
    	if(toUserId == ""){
    		$("#toUserName").val('');
    		return;
    	}
    	jQuery.ajax({
    		url: "/home/want_g/doUserValidator?toUserId="+toUserId,   // 提交的页面 
    		type: "POST", // 设置请求类型为"POST"，默认为"GET" 
    		dataType: "json", 
    		contentType: "application/json;charset=UTF-8", // 必须要设置为UTF-8，否则提交数据乱码 
    		cache: false,
	         success:function(data){     	        	 
	        	 if (data.success) {
	        		 $("#toUserName").val(data.userName);
	        		 $("#toUserCom").val(data.userCom);
	        		 
	            	} else {
	            		$("#toUserName").val('');
	            		alert("设置失败");
	            	}
	         },
	         error:function(data){
	        	 alert("设置失败");
	        	 $("#toUserName").val('');
	         }
    	});
    };
    
    var _accountValidator = function () {
    	var toGiveNum = $("#toGiveNum").val();
    	if(toGiveNum == ""){
    		return;
    	}
    	
    	var Reg = new RegExp("^[1-9]\\d*$");
    	if(!Reg.test(toGiveNum)){
    		extend.msgBox.alert({message : "请输入正确的吉点点数！"});
    		$("#toGiveNum").val('');
    		return;
    	}
    	
    	jQuery.ajax({
    		url: "/home/want_g/doAccountValidator",   // 提交的页面 
    		type: "POST", // 设置请求类型为"POST"，默认为"GET" 
    		dataType: "json", 
    		contentType: "application/json;charset=UTF-8", // 必须要设置为UTF-8，否则提交数据乱码 
    		cache: false,
	         success:function(data){     	        	 
	        	 if (data.success) {
	        		 if((data.hasJddNum -  Number(toGiveNum)) < 0){
	        			 $("#toGiveNum").val('');
	        			 alert("您的吉点点余额不足，你当前可用吉点点账户余额为" + data.hasJddNum + "点。");
	        		 }
	            	} else {
	            		$("#toGiveNum").val('');
	            		alert("设置失败");
	            	}
	         },
	         error:function(data){
	        	 alert("设置失败");
	        	 $("#toGiveNum").val('');
	         },
    	});
    };
    
	return {
		init : function() {
			_init();
		},doSearch: function(pageNum){
			_doSearch(pageNum);
		},startDateSelectFmt: function(obj, that){
			_startTimeSelectFmt(obj, that);
		},endDateSelectFmt: function(obj, that){
			_endTimeSelectFmt(obj, that);
		},doReset: function(){
			_doReset();
		},recordTypeSelected: function(type){
			_recordTypeSelected(type);
		},doJump: function(){
			_doJump();
		},showDoGive : function() {
			_giveInit();
			_getDefaultValue();
			extend.dialog.show({
				title : "吉点点赠送"
				, body : $("#dialog_doGive")
				, width : "673px"
					, buttons : [{"display" : "确定", "displayCss" : "popBtn_blue", "click" : _doGive }]
			});
		},userValidator: function(){
			_userValidator();
		},accountValidator: function(){
			_accountValidator();
		}
		
	};

}();