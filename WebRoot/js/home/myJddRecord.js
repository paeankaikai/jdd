$(document).ready(function () {
	 var ctx = $("#ctx").val();
	    $('#dg').datagrid({
	        width: 996,
	        nowrap: false,
	        striped: true,
	        loadMsg: '',
	        fitColumns: false,
	        url: ctx + '/home/myJddRecord/myJddRecordPage.do',
	        idField: 'guid', // 主键
	        singleSelect: true, // 单选
	        columns: [[
	            {field: 'guid', title: 'ID', width: 0,hidden:true ,align:'center'},
	            {field: 'fatherName', title: '类型', width: 220 , align:'center'},
	            {field: 'count', title: '数量', width: 220 , align:'center',formatter: function (value, rowData ,rowIndex) {
	            	if(rowData.ruleType == 0){
	            		return "+"+value;
	            	}else{
	            		return "-"+value;
	            	} }},
	            {field: 'reason', title: '原因', width: 356 , align:'center'},
	            {field: 'createTime', title: '时间', width: 200 , align:'center'}
	        ]],
	        pagination: true,// 显示分页
	        pageSize: 10,// 默认分页大小
	        showPageList: false,
	        onLoadSuccess: function () {
			    var data = $('#dg').datagrid("getData");
		        $("#matchTotal").text(data.total);
		        $("#pageNum").val(data.pageNum);
		        if (data.total <= 0) {
		            $('#dg').datagrid("getPager").hide();
		           // noDataShow('dg', '没有查询记录！');
		        } else {
		            $(".nodata").remove();
		            $('#dg').datagrid("getPager").show();
		       }
		    }
	    });
	    $('#dg').datagrid("getPager").pagination({layout: ['prev', 'links', 'next'], displayMsg: ''});
	    
	  /**
	   * 吉点点统计(总共消费多少，总共支出多少，剩余多少)
	   */
		$.getJSON( '/home/myJddRecord/myJddRecordInfoTop.do', function(data){
			$("#totalInComNum").text(data.totalInComNum);
			$("#totalOutComNum").text(data.totalOutComNum);
			$("#myJddNum").text(data.myJddNum);
		});
	    
	    /**
	     * 添加查询条件
	     */
	    $("#search").click(function () {
	        var url = ($('#dg').datagrid('options').url);
	        var index = url.indexOf("?");
	        if (index > 0) {
	        	url = url.substr(0,index);
	        }
	        var parms="";
	        parms +="?beginDate="+$('#beginDate').datebox('getValue');
	        parms +="&endDate="+$('#endDate').datebox('getValue');
	        parms +="&ruleType="+$('#ruleType').combobox('getValue');
	        parms +="&ruleGrad="+$('#ruleGrad').combobox('getValue');
	        parms +="&ruleFather="+$('#ruleFather').combobox('getValue');
	        $('#dg').datagrid('load', url + parms);
	    });
	    
	  /**
	   * 开启吉点点之旅后共获得吉点点：(?)， 消费吉点点：(?) ; 当前账户余额：(?) ; 吉点点。
	   */
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
	    
	    
	    
	    
	    /**
	     * 收支下来框
	     */
	    $('#ruleType').combobox({
	    	url: ctx + '/home/myJddRecord/ruleType.do',
	        valueField: 'typeCode',
	        textField: 'typeName',
	        onSelect: function(record) {
	        },
	        onLoadSuccess: function() {
	            $(this).combobox('setValue', "-1");
	            $(this).combobox('setText', "请选择收支类型");
	        },
	        onChange:function(n,o){
	        		ruleGrad(n);
	        		ruleFather(-1);
	        }
	    });
	    
	    /**
	     * 一级下拉框
	     */
	    function ruleGrad(ruleType){
	    	$('#ruleGrad').combobox({
	    		url: ctx + '/home/myJddRecord/ruleGrad.do?ruleType='+ruleType,
	    		valueField: 'guid',
	    		textField: 'className',
	    		onSelect: function(record) {
	    		},
	    		onLoadSuccess: function() {
	    			$(this).combobox('setValue', "");
	    			$(this).combobox('setText', "请选择一级类型");
	    		},
	    		onChange:function(n,o){
	    			ruleFather(n);
		        }
	    	}); 
	    }
	    
	    /**
	     * 二级下拉框
	     */
	    function ruleFather(ruleFather){
		    $('#ruleFather').combobox({
		    	url: ctx + '/home/myJddRecord/ruleFather.do?ruleFather='+ruleFather,
		        valueField: 'guid',
		        textField: 'className',
		        onSelect: function(record) {
		        },
		        onLoadSuccess: function() {
		            $(this).combobox('setValue', "");
		            $(this).combobox('setText', "请选择二级类型");
		        }
		    }); 
	    }
	    
	    
	    //重置--cyk
	    $("#Reset").click(function () {
	    	$("#ruleType").combobox('setValue', '');
	    	$("#ruleType").combobox('setText', '请选择收支类型');
	    	
	    	$("#ruleGrad").combobox('setValue', '');
	    	$("#ruleGrad").combobox('setText', '请选择一级类型');
	    	
	    	$("#ruleFather").combobox('setValue', '');
	    	$("#ruleFather").combobox('setText', '请选择二级类型');
	    	
	    	$("#beginDate").datebox("setValue", "");
	    	$("#endDate").datebox("setValue", "");
	        var url = ($('#dg').datagrid('options').url);
	        var index = url.indexOf("?");
	        if (index > 0) {
	        	url = url.substr(0,index);
	        }
	        $('#dg').datagrid('load', url );
	    });
	    
	    
    
});