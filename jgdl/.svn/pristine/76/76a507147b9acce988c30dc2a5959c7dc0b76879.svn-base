$(document).ready(function() {
	getPersonMsg();
});
/**
 * 获取json里的数据赋值给table
 */
function getPersonMsg() {
	// 人员类型翻译
	var type = json.RYLX;
	var typeStr = "未知";
	if (type == 0) {
		typeStr = "在逃人员";
	} else if (type == 1) {
		typeStr = "重点人员";
	} else if (type == 2) {
		typeStr = "临控人员";
	} 
	// 是否是最新数据翻译
	var isNew = json.ISNEW;
	var isNewStr = "未知";
	if (isNew == 0) {
		isNewStr = "否";
	} else if (isNew == 1) {
		isNewStr = "是";
	}
	// 布控状态翻译
	var bkzt = json.BKZT;
	var bkztStr = "未知";
	if (bkzt == 0) {
		bkztStr = "待审批";
	} else if (bkzt == 1) {
		bkztStr = "布控";
	} else if (bkzt == 2) {
		bkztStr = "过期";
	} else if (bkzt == 3) {
		bkztStr = "审批未通过";
	}

	$(".xm").html(json.XM);
	$(".rylx").html(typeStr);
	$(".GMSFHM").html(json.GMSFHM);
	$(".bkzt").html(bkztStr);
	$(".HJDZ").html(json.HJDZ);
	$(".cjsj").html(json.CJSJ);
	$(".isNew").html(isNewStr);
	if (null != json.BZ) {
		$(".BZ").html(json.BZ);
	} else {
		$(".BZ").html("暂无");
	}
}
/**
 * 续期
 * 
 * @author: 鲁绒
 * @dateTime: 2017-5-3 上午10:21:22
 * @param:
 * @return: void
 */
function xuQi() {
	$.ajax({
		type : "post",
		url : "updateOverdue.do",
		timeout : 1321231232131213123,
		dataType : 'text',
		data : {
			gmsfhm : json.GMSFHM
		},
		success : function(data) {
			if (data == 'updateSuccess') {
				$("#ok").click();
			} else {
				$("#err").click();
				secs = 50; // 设置多少秒
				wait = secs * 100;
				for (i = 1; i <= 5; i++) {
					window.setTimeout("doUpdate(" + i + ")", i * 1000);
				}
				window.setTimeout("Timer()", wait);
			}
		},
		error : function() {
			alert("程序异常");
		}
	});

}

/**
 * 倒计时
 * 
 * @param num
 */
function doUpdate(num) {
	if (num == (wait / 100)) {
		document.getElementById("num").innerHTML = " ";
	} else {
		wut = (wait / 1000) - num;
		document.getElementById("num").innerHTML = wut;
	}
}
/**
 * 跳转
 */
function Timer() {
	window.location.href = "gotoDispatchedIndex.do?bkzt=2";
}
