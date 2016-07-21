$(document).ready(function () {
	var ctx = $("#ctx").val();
		    $('#dg').datagrid({
		        width: 928,
		        nowrap: false,
		        striped: true,
		        loadMsg: '',
		        fitColumns: false,
		        url: ctx + '/home/weChatInfo/weChatInfoPage.do',
		        idField: 'guid', // 主键
		        singleSelect: true, // 单选
		        columns: [[
		           /* {field: 'guid', title: 'ID', width: 0,hidden:true ,align:'center'},*/
		            {field: 'fromUserCom', title: '赠送者公司', width: 200 , align:'center'},
		            {field: 'fromUserName', title: '赠送者', width: 79 , align:'center'},
		            {field: 'toUserCom', title: '获赠者公司', width: 200 , align:'center'},
		            {field: 'toUserName', title: '获赠者', width: 79 , align:'center'},
		            {field: 'reason', title: '原因', width: 200 , align:'center'},
		            {field: 'count', title: '吉点点', width: 70 , align:'center'},
		            {field: 'createTime', title: '时间', width: 100 , align:'center'}
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