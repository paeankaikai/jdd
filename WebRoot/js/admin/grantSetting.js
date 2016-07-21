if (!grantSetting) var grantSetting = {};  
grantSetting.content= function () {

	var _init = function () {
		
	};

	var _setCountLimit =function(){
    	var value = $("#microSendCount").val();   	
    	
    	if(value == ""){
    		extend.msgBox.alert({message : "请输入每月最多可赠送数！"});
    		return;
    	}else{
        	var Reg = new RegExp("^[1-9]\\d*$");
        	if(!Reg.test(value)){
        		extend.msgBox.alert({message : "每月最多可赠送数为整数！"});
        		return;
        	}
    	}
    	
    	var enable = 0;
    	if(!$("#microSendCountEnable").hasClass("checkboxS22")){
    		enable = 1;
    	}
    	 $.ajax({
	         type:"Post",
	         url:"/admin/grant/countLimitSet?enable=" + enable + "&value=" + value + "&setType=0",
	         async: false,   
	         dataType : "json",
	         contentType : "application/json;charset=UTF-8", 
	         success:function(res){     	        	 
	        	 if (res.success) {
	        		 extend.msgBox.alert({message : "设置成功！"});
	            	} else {
	            		extend.msgBox.alert({message : "设置失败！"});
	            	}
	         },
	         error:function(res){
	        	 extend.msgBox.alert({message : "设置失败！"});
	         },
	        });
	};

	var _setNumLimit =function(){
    	var value = $("#microSendNum").val();   	
    	
    	if(value == ""){
    		extend.msgBox.alert({message : "请输入每次赠送吉点点最大数！"});
    		return;
    	}else{
        	var Reg = new RegExp("^[1-9]\\d*$");
        	if(!Reg.test(value)){
        		extend.msgBox.alert({message : "请输入每次赠送吉点点最大数为整数！"});
        		return;
        	}
    	}
    	
    	var enable = 0;
    	if(!$("#microSendNumEnable").hasClass('checkboxS22')){
    		enable = 1;
    	}
    	
    	 $.ajax({
	         type:"Post",
	         url:"/admin/grant/countLimitSet?enable=" + enable + "&value=" + value + "&setType=1",
	         async: false,   
	         dataType : "json",
	         contentType : "application/json;charset=UTF-8", 
	         success:function(res){     	        	 
	        	 if (res.success) {
	        		 extend.msgBox.alert({message : "设置成功！"});
	            	} else {
	            		extend.msgBox.alert({message : "设置失败！"});
	            	}
	         },
	         error:function(res){
	        	 extend.msgBox.alert({message : "设置失败！"});
	         },
	        });
	};

	return {
		init : function() {
			_init();
		},setCountLimit: function(){
			_setCountLimit();
		},setNumLimit: function(id){
			_setNumLimit();
		}
	};

}();