$(document).ready(function () {
	//提示信息滚动JS
        $(".overflowS").mouseover(function(){
            clearInterval(t);
        });
        $(".overflowS").mouseout(function(){
            t=setInterval(function(){
                $(".overflowUl").animate({marginTop:"-30px"},function() {
                    $(".overflowUl").css("marginTop", "0");
                    $(".overflowUl li").eq(0).appendTo(".overflowUl");
                });
            },7000);
        });
        t=setInterval(function(){
            $(".overflowUl").animate({marginTop:"-30px"},function() {
                $(".overflowUl").css("marginTop", "0");
                $(".overflowUl li").eq(0).appendTo(".overflowUl");
            });
        },7000);
   //title信息
        var pageName = $("#pageName").val();
    	if(pageName == 'myJddRecord'){
    		$("#lastTitle").text('我的吉点点记录');
    	}else if(pageName == 'weChatInfo'){
    		$("#lastTitle").text('微送公示榜');
    	}else if(pageName == 'jddRanking'){
    		$("#lastTitle").text('吉点点达人榜');
    	}else if(pageName == 'wantGive'){
    		$("#lastTitle").text('我要送');
    	}else if(pageName == 'makeJdd'){
    		$("#lastTitle").text('赚取吉点点')
    	}
        $('[data-toggle="popover"]').popover();
  
});
	//正则判断是否是数字
	function isNum(data){
		var boolean = /^[0-9]*$/g.test(data)
		return boolean;
	}