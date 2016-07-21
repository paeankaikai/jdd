$(document).ready(function () {
	var ctx = $("#ctx").val();
		    $('#dg').datagrid({
		        width: 718,
		        height: 360,
		        nowrap: false,
		        striped: true,
		        loadMsg: '',
		        fitColumns: false,
		        url: ctx + '/home/makeJdd/jddEXrecord.do',
		        idField: 'guid', // 主键
		        singleSelect: true, // 单选
		        columns: [[
		            {field: 'guid', title: 'ID', width: 0,hidden:true ,align:'center'},
		            {field: 'pm', title: '序号', width: 118 , align:'center',formatter: function (value, rowData ,rowIndex) {
	                      return ((rowData.pageNum * 5)+(rowIndex + 1))}},
		            {field: 'jkCount', title: '兑换吉课金币数', width: 200 , align:'center'},
		            {field: 'count', title: '兑换吉点点数', width: 200, align:'center'},
		            {field: 'createTime', title: '兑换时间', width: 200 , align:'center'}
		        ]],
		        pagination: true,// 显示分页
		        pageSize: 5,// 默认分页大小
		        pageList: [5,10],
		        onLoadSuccess: function () {
				    var data = $('#dg').datagrid("getData");
			        $("#matchTotal").text(data.total);
				    if (data.total <= 0) {
			            $('#dg').datagrid("getPager").hide();
			        
			        } else {
			            $(".nodata").remove();
			            $('#dg').datagrid("getPager").show();
			       }
			    }
		    });
		    $('#dg').datagrid("getPager").pagination({layout: ['prev', 'links', 'next'], displayMsg: ''});
		//
		  $("#save").click(function(){
			  var jddNum = $("#jddNum").val();//可以兑换的金额
			  var jkNumInput = $("#jkNumInput").val();//输入兑换的金额
			  if(isNum(jkNumInput) && jkNumInput >0){
				  $('#myModal').modal('hide')
			  }else{
				  layer.msg('输入要兑换的金额不正确！！', function(){});
			  }
		  })
});