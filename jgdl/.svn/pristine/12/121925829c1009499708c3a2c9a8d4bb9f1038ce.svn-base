/**
 * 描述：人员布控列表 作者：黄月 修改历史：20170424
 */
$().ready(function() {
	// 列表查询
	getRow(0);
	// 监听
	listenClick();
	getNewDataCount();
});

/**
 * listenClick:(点击事件监听) TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
 * @author: 黄月
 * @dateTime: 2017-4-27 上午10:21:22
 * @param:
 * @return: void
 */
function listenClick() {
	// 最新部署事件监听
	$('.new-list').click(function() {
		// 加载最新部署列表
		getRow(1);
		// 替换标题
		$(".decoration").html("最新部署");
		// 隐藏按钮
		$('.new-list').hide();
		$('.images-content').hide();
		$('.go-home').css('right', '60px');
	});
}

/**
 * getRow:(已部署列表查询).&lt;
 * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).
 * @author: 黄月
 * @dateTime: 2017-4-24 下午12:19:50
 * @param: isNew 是否是最新部署
 * @return: void
 */
function getRow(isNew) {
	$("#dispatchedGrld").uiGrid({
		url : "listDispatchedInPage.do",
		rowNum : 10,
		// 每页显示记录数
		columns : [ {
			field : 'XM',
			title : '姓名'
		}, {
			field : 'RYLX',
			title : '人员类型',
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
		}, {
			field : 'SXSJ',
			title : '撤控时间'
		} ],
		divId : "#dispatchedGrld",
		showPage : 5,
		// 行点击事件
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
		data : {
			isnew : isNew
		}
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
function ondblclickEvent(row) {
	//跳转人员详细信息页
	toPersonIndex(row);
}

/**
 * toPersonIndex:(跳转人员详细信息页并提交人员信息) 
 * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
 * @author: 黄月
 * @dateTime: 2017-4-24 下午2:13:41
 * @return: void
 */
function toPersonIndex(row) {
	//解析行对象为json字符串
	var strJson = JSON.stringify(row);
	//新建form表单
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
  * getNewDataCount:(获取最新预警数)
  * TODO(每次点击查看人员流动信息后如果是最新预警，则改变状态，预警数减一)
  * @author: 黄月
  * @dateTime: 2017-5-22 上午9:41:14
  * @param: 
  * @return: void
 */
function getNewDataCount(){
	$.ajax({
		url : "getNewDataCount.do",
		type : "post",
		dataType : "json",
		async : false,
		success : function(data){
			if (data.status == "success") {
				$(".images-content").html(data.result);
			} else {
				$(".images-content").html(-1);
			}
		}
	});
}