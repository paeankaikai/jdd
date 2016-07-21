$(document).ready(function () {
	// 我的吉点点
	$.getJSON( '/home/default/myJDD.do', function(data){
		  $("#allMyJDD23").append((data.info.cyNum - data.info.cyCostNum));
		  $("#allMyJDDOneYear").append((data.info.lyNum - data.info.lyCostNum));
	});
	// 吉点点达人榜单
	$.getJSON( '/home/default/JDDRanking.do', function(data){
		var rows =data.rows;
		var JddRanking_ul_html ='';
		for (var i = 0; i < rows.length; i++) {
			JddRanking_ul_html+='<li>';
			JddRanking_ul_html+='<span class="rankjdd2">'+(i+1)+'</span>';
			JddRanking_ul_html+='<span class="rankjdd3">'+rows[i].userName+'</span>';
			JddRanking_ul_html+='<span class="rankjdd4">'+(rows[i].cyNum - rows[i].cyCostNum)+'</span>';
			JddRanking_ul_html+='</li>';
		}
		$("#JddRanking_ul").append(JddRanking_ul_html);
		$(".rankjdd2").eq(0).addClass("rankjdd2Red");
		$(".rankjdd2").eq(1).addClass("rankjdd2Red");
		$("#JddRanking_ul li:last").css("border","none");
		
	});
	  $(document).on("mouseover",".jddrecordTtext p",function(){
          $(this).addClass("jddrecordTtextHover");
      })
        $(document).on("mouseout",".jddrecordTtext p",function(){
          $(this).removeClass("jddrecordTtextHover");
      })
      
      
	
	// 吉点点系统消息
	$.getJSON( '/home/default/message.do', function(data){
		var rows =data.rows;
		var jddNotice_ul_html ='';
		for (var i = 0; i < rows.length; i++) {
			jddNotice_ul_html+='<li>';
			jddNotice_ul_html+='<span>'+rows[i].message+'</span>';
			jddNotice_ul_html+='</li>';
		}
		$("#jddNotice_ul").append(jddNotice_ul_html);
	});
		
	
	// 微送公示
	$.getJSON( '/home/default/weChatInfo.do', function(data){
		var rows =data.rows;
		var weChatInfo_ul_html ='';
		for (var i = 0; i < rows.length; i++) {
			weChatInfo_ul_html+='<li>';		
			weChatInfo_ul_html+='<span style="width:48px">'+rows[i].fromUserName+'</span>';
			weChatInfo_ul_html+='<span>赠送</span>';
			weChatInfo_ul_html+='<span style="width:48px">'+rows[i].toUserName+'</span>';
			weChatInfo_ul_html+='<span style="text-align:right;"><b style="display:block;float:left;width:20px;">'+rows[i].count+'</b><i style="display:block;float:left;font-style:normal;">吉点点</i></span>';
			weChatInfo_ul_html+='<span style="float: right;margin-right:0px;">'+rows[i].createTime+'</span>';
			weChatInfo_ul_html+='</li>';
		}
		$("#weChatInfo_ul").append(weChatInfo_ul_html);
		$("#weChatInfo_ul li:last").css("border","none");
	});
	
  // 我的吉点点
	$.getJSON( '/home/default/myJDDInfo.do', function(data){
		var rows =data.rows;
		var myJDDInfo_div_html ='';
		for (var i = 0; i < rows.length; i++) {
			myJDDInfo_div_html+='<p>';
			myJDDInfo_div_html+='<span class="jddrecordTP1">'+(i+1)+'</span>';
			myJDDInfo_div_html+='<span class="jddrecordTP2">'+rows[i].fatherName+'</span>';
			if(rows[i].ruleType == 0){
				myJDDInfo_div_html+='<span class="jddrecordTP3">'+rows[i].count+'</span>';
			}else{
				myJDDInfo_div_html+='<span class="jddrecordTP3">-'+rows[i].count+'</span>';
			}
			
			if(rows[i].reason==null){
				myJDDInfo_div_html+='<span class="jddrecordTP4">'+'无'+'</span>';
			}
			else{
				if(rows[i].reason.length>10){
					myJDDInfo_div_html+='<span class="jddrecordTP4">'+rows[i].reason.substring(0,10)+'...</span>';
				}
				else{
					myJDDInfo_div_html+='<span class="jddrecordTP4">'+rows[i].reason+'</span>';
				}				
			}
			var date=rows[i].createTime;
			//date = new Date(date);
			myJDDInfo_div_html+='<span class="jddrecordTP5">'+date +'</span>';
			myJDDInfo_div_html+='</p>';
		}
		$("#myJDDInfo_div").append(myJDDInfo_div_html);
		$("#myJDDInfo_div p:last").css("border","none");
	});
	
	
	  Date.prototype.Format = function (fmt) { //author: meizz 
	    var o = {
	            "M+": this.getMonth() + 1, //月份 
	            "d+": this.getDate(), //日 
	            "h+": this.getHours(), //小时 
	            "m+": this.getMinutes(), //分 
	            "s+": this.getSeconds(), //秒 
	            "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
	            "S": this.getMilliseconds() //毫秒 
	        };
	        if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	        for (var k in o)
	        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	        return fmt;
	    }
	
	
	// 本年度收支明细
	$.getJSON( '/home/default/inOutInfo.do', function(data){
		// 我的吉点点收入
	    var myJddInCome_html ='';
	    myJddInCome_html+='<p><span>关爱认可:</span><span>'+data.in_gark+'</span></p>';
	    myJddInCome_html+='<p><span>行为认可:</span><span>'+data.in_xwrk+'</span></p>';
	    myJddInCome_html+='<p><span>成长认可:</span><span>'+data.in_czrk+'</span></p>';
	    myJddInCome_html+='<p><span>员工赠送:</span><span>'+data.in_ygzs+'</span></p>';
	    myJddInCome_html+='<p style="border:0;"><span >其他收入:</span><span>'+data.in_other+'</span></p>';
	    $("#myJddInCome").append(myJddInCome_html);
	    // 我的吉点点收入
	    var myJddOutCome_html ='';
	    myJddOutCome_html+='<p><span>商城消费:</span><span>'+data.out_scxf+'</span></p>';
	    myJddOutCome_html+='<p><span>赠送他人:</span><span>'+data.out_zstr+'</span></p>';
	    myJddOutCome_html+='<p><span>过期清零:</span><span>'+data.out_cqql+'</span></p>';
	    myJddOutCome_html+='<p><span>其他支出:</span><span>'+data.out_other+'</span></p>';
	    $("#myJddOutCome").append(myJddOutCome_html);
	    // 我的吉点点收支总计
	    var myJddComeTotle_html ='';
	    myJddComeTotle_html+='<p class="totalnumbL1"><span>收入合计:</span><span>'+data.in_total+'</span></p>';
	    myJddComeTotle_html+='<p class="totalnumbR2"><span>支出合计:</span><span>'+data.out_total+'</span></p>';
	    $("#myJddComeTotle").append(myJddComeTotle_html);
	});
	
	//
	/*function laert(){
		
	}
	$('#laert').on('click', function(){
	    layer.tips('说明:吉点点百科点击打开页面展示文档-吉点点的规则说明', '#laert', {
	      	  tips: [3, '#0FA6D8']
	      	});
	});*/
	
	
	 
	 $("#fankui").click(function(){
		 $(this).attr("href","http://feedback.evun.cn/");
	 });
	
	
});