/**
  * 描述：人员布控列表
  * 作者：黄月
  * 修改历史：20170424
 */
$().ready(function(){
	//标题设置
	$(".decoration").html("待审批");
	//列表查询
	getRow(0);
});

/**
  * getRow:(列表查询).&lt;br/&gt;
  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).&lt;br/&gt;
  * @author: 黄月
  * @datetime: 2017-4-24 下午12:19:50
  * @param: 
  * @return: void
 */
function getRow(currentPage){
	var curPage = currentPage
	$("#approvalGrld").uiGrid({
		url: "listApprovalInPage.do",
		//每一页显示的条数
		rowNum:10,
		//每页显示记录数
		columns: [{
			field: 'XM',
			title: '姓名'
		}, {
			field: 'RYLX',
			title: '人员类型',
			formatter : function(value, rec) {
				if (rec.RYLX == 0) {
					return "<span class='label label-success'>在逃</span>";
				} else if (rec.RYLX == 1) {
					return "<span class='label label-warning'>重点</span>";
				} else if (rec.RYLX == 2) {
					return "<span class='label label-important'>临控</span>";
				} else {
					return "<span class='label label-info'>其他</span>";
				}

			}
		},{
			field: 'CJSJ',
			title: '创建时间'
		}],
		divId: "#approvalGrld",
		showPage: 10,
		//行点击事件
		ondblclickEvent : true,
		ondblclickEvent : ondblclickEvent,
		jsonPager : {
			// 总记录数
			records : "rowCount",
			// 当前访问页
			currentPage : 1,
			// 总页数
			total : "pageCount"
	},
		data: {
			//当前页面数据类型(布控状态0:待审批,1:布控,2:过期)
			bkzt:0,
			isnew:0,
			rowNum:5,
			currentPage:curPage
		},
		
	});
}



/**
  * ondblclickEvent:(行点击事件)
  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
  * @author: 黄月
  * @dateTime: 2017-4-24 下午2:14:15
  * @param: 
  * @return: void
 */
function ondblclickEvent(row){
	
toApprovaldetails(row);

}


/**
  * toPersonIndex:(跳转人员详细信息页并提交人员信息)
  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
  * @author: 黄月
  * @dateTime: 2017-4-24 下午2:13:41
  * @return: void
 */
function toPersonIndex(row){
	var strJson = JSON.stringify(row);
	var form = document.createElement("form");
	form.style.display = 'none';
	form.action = 'toPersonMsgIndex.do';
	form.method = "post";
	document.body.appendChild(form);
	var input = document.createElement("input");
	input.type = "hidden";
	input.name = "strJson";
	input.value = strJson;
	form.appendChild(input);
	form.submit();
}

/**
 * toPersonIndex:(跳转人员详细信息页并提交人员信息)
 * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
 * @author: 黄月
 * @dateTime: 2017-4-24 下午2:13:41
 * @return: void
*/
function toOverdueIndex(row){
	var strJson = JSON.stringify(row);
	var form = document.createElement("form");
	form.style.display = 'none';
	form.action = 'toOverdueIndex.do';
	form.method = "post";
	document.body.appendChild(form);
	var input = document.createElement("input");
	input.type = "hidden";
	input.name = "strJson";
	input.value = strJson;
	form.appendChild(input);
	form.submit();
}
/**
 * toapprovaldetails:(跳转人员详细信息页并提交人员信息,待审批详情页面)
 * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
 * 
*/
function toApprovaldetails(row){
	var strJson = JSON.stringify(row);
	var form = document.createElement("form");
	form.style.display = 'none';
	form.action = 'toApprovaldetails.do';
	form.method = "post";
	document.body.appendChild(form);
	var input = document.createElement("input");
	input.type = "hidden";
	input.name = "strJson";
	input.value = strJson;
	form.appendChild(input);
	form.submit();
}

