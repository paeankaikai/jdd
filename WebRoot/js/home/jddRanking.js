$(document).ready(function () {
	var ctx = $("#ctx").val();
		    $('#dg').datagrid({
		        width: 928,
		        nowrap: false,
		        striped: true,
		        loadMsg: '',
		        fitColumns: false,
		        url: ctx + '/home/jddRanking/jddRankingPage.do',
		        idField: 'guid', // 主键
		        singleSelect: true, // 单选
		        columns: [[
		            {field: 'guid', title: 'ID', width: 0,hidden:true ,align:'center'},
		            {field: 'pm', title: '排名', width: 200 , align:'center',formatter: function (value, rowData ,rowIndex) {
	                      return "第 " +((rowData.pageNum * 10)+(rowIndex + 1))+ " 名"}},
		            {field: 'jddNum', title: '吉点点', width: 79 , align:'center',formatter: function (value, rowData ,rowIndex) {
	                      return (rowData.cyNum - rowData.cyCostNum)}},
		            {field: 'userCom', title: '公司名称', width: 300 , align:'center'},
		            {field: 'userName', title: '姓名', width: 150 , align:'center'},
		            {field: 'userPosition', title: '职务', width: 200 , align:'center'},
		        ]],
		        pagination: true,// 显示分页
		        pageSize: 10,// 默认分页大小
		        showPageList: false,
		        onLoadSuccess: function () {
				    var data = $('#dg').datagrid("getData");
			        $("#matchTotal").text(data.total);
			        if (data.total <= 0) {
			            $('#dg').datagrid("getPager").hide();
			            noDataShow('dg', '没有查询记录！');
			        } else {
			            $(".nodata").remove();
			            $('#dg').datagrid("getPager").show();
			       }
			    }
		    });
		    $('#dg').datagrid("getPager").pagination({layout: ['prev', 'links', 'next'], displayMsg: ''});
});