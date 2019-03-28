/**
 * 描述：人员布控列表 作者：黄月 修改历史：20170424
 */
$().ready(function() {
	// 列表查询,默认已过期（2）
	getRow(2);
});

/**
 * getRow:(列表查询)
 * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).
 * @author: 黄月
 * @dateTime: 2017-4-24 下午12:19:50
 * @param: isNew 是否是最新部署
 * @return: void
 */
function getRow(type) {
	showOrHide(type);
	var searchKey = $('.search_input').val();
	$("#dispatchedGrld").uiGrid({
		url : "listOverdueInPage.do",
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
			title : '撤控时间',
			formatter : function(value, rec) {
				if (rec.SXSJ == null || rec.SXSJ == undefined) {
					return "无";
				} else {
					return rec.SXSJ;
				}
			}
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
			bkzt : type,
			search : searchKey
		}
	});
}

/**
  * showOrHide:(显示隐藏按钮)
  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
  * @author: 黄月
  * @dateTime: 2017-5-22 下午1:37:50
  * @param: @param type
  * @return: void
 */
function showOrHide(type){
	if (type == 1) {
		$('.decoration').html("待续期");
		/*$('#type1').hide();
		$('#type2').show();
		$('#type3').show();*/
	} else if (type == 2) {
		$('.decoration').html("已过期");
		/*$('#type1').show();
		$('#type2').hide();
		$('#type3').show();*/
	} else if (type == 3) {
		$('.decoration').html("未通过");
		/*$('#type1').show();
		$('#type2').show();
		$('#type3').hide();*/
	}
}
/**
 * ondblclickEvent:(行点击事件) TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
 * @author: 黄月
 * @dateTime: 2017-4-24 下午2:14:15
 * @param:
 * @return: void
 */
function ondblclickEvent(row) {
	toOverdueIndex(row);
}

/**
 * toPersonIndex:(跳转过期人员信息页并提交人员信息)
 * @author: 黄月
 * @dateTime: 2017-4-24 下午2:13:41
 * @return: void
 */
function toOverdueIndex(row) {
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
  * checkedSFZ:(身份证验证)
  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
  * @author: 黄月
  * @dateTime: 2017-5-22 下午2:16:27
  * @param: @param sfzID
  * @param: @returns {Boolean}
  * @return: Boolean
 */
function checkedSFZ(sfzID) {// .test($("check").value)
	var sfzRule = /^\d{6}(18|19|20)?\d{2}(0[1-9]|1[012])(0[1-9]|[12]\d|3[01])\d{3}(\d|X)$/i;
	var city = {
		11 : "北京",
		12 : "天津",
		13 : "河北",
		14 : "山西",
		15 : "内蒙古",
		21 : "辽宁",
		22 : "吉林",
		23 : "黑龙江",
		31 : "上海",
		32 : "江苏",
		33 : "浙江",
		34 : "安徽",
		35 : "福建",
		36 : "江西",
		37 : "山东",
		41 : "河南",
		42 : "湖北",
		43 : "湖南",
		44 : "广东",
		45 : "广西",
		46 : "海南",
		50 : "重庆",
		51 : "四川",
		52 : "贵州",
		53 : "云南",
		54 : "西藏",
		61 : "陕西",
		62 : "甘肃",
		63 : "青海",
		64 : "宁夏",
		65 : "新疆",
		71 : "台湾",
		81 : "香港",
		82 : "澳门",
		91 : "国外 "
	};
	var tip = "";
	var pass = true;
	if (!sfzID || !sfzRule.test(sfzID)) {
		tip = "身份证号格式错误";
		pass = false;
	} else if (!city[sfzID.substr(0, 2)]) {
		tip = "地址编码错误";
		pass = false;
	} else { // 18位身份证需要验证最后一位校验位
		if (sfzID.length == 18) {
			sfzID = sfzID.split('');
			// ∑(ai×Wi)(mod 11)
			// 加权因子
			var factor = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 ];
			// 校验位
			var parity = [ 1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2 ];
			var sum = 0;
			var ai = 0;
			var wi = 0;
			for ( var i = 0; i < 17; i++) {
				ai = sfzID[i];
				wi = factor[i];
				sum += ai * wi;
			}
			var last = parity[sum % 11];
			if (parity[sum % 11] != sfzID[17]) {
				tip = "身份证号错误！";
				pass = false;
			}
		}
	}
	if (!pass) {
		alert(tip);
	}
	return pass;
}
