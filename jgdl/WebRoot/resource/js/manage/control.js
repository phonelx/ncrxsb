$().ready(function(){
	loadBk(1);
});

function loadBk(pageNo){
	$("#controlGrid").uiGrid({ 
		url : "listControlInPage.do",
		rowNum : 10,//每页显示记录数
		columns : [ {
			field : 'gmsfhm',
			title : '身份证号',
			width : 100,
			//hidden:true
		},{
			field : 'xm',
			title : '姓名',
			width : 100
		},{
			field : 'rylx',
			title : '人员类型',
			width : 100,
			formatter : function(value, rec) {
				if (value == 0) {
					return "在逃人员";
				} else if (value == 1) {
					return "重点人员";
				} else if (value == 2) {
					return "临控人员";
				} else if (value == 3) {
					return "案件嫌疑人";
				} else {
					return value;
				}
			}
		}, {
			field : 'bkzt',
			title : '布控状态',
			width : 100,
			formatter : function(value, rec) {
				if (value == 0) {
					return "待审批";
				} else if (value == 1) {
					return "已布控";
				} else if (value == 2) {
					return "已过期";
				} else if (value == 3) {
					return "未通过";
				} else {
					return value;
				}
			}
		},{
			field : 'cjsj',
			title : '创建时间',
			width : 100
		},  {
			field : 'sxsj',
			title : '失效时间',
			width : 100
		} ],
		divId : "#controlGrid",
		showPage : 5,//显示
		showDelete : true,
		showDeleteEvent : deleteControl,
	/*	showEdit : true,
		showEditEvent : showEditControl,
		*/
		jsonPager : {
			root : "datas",// 当前页数据
			records : "rowCount",//总记录数
			currentPage : pageNo,//当前访问页
			total : "pageCount"//总页数
		},
		data : {
			searchKey : ""
		}
	});
}

/**
  * deleteControl:(删除布控)
  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
  * @author: 黄月
  * @dateTime: 2017-5-23 上午10:55:43
  * @param: @param row
  * @return: void
 */
function deleteControl(row) {
	if (confirm('确认删除？')) {
		$.ajax({
			type : 'post',
			url : 'deleteControl.do',
			timeout : 1321231232131213123,
			data : {
				'bid' : row.bid,
				'bt' : row.bt,
				'bkhm' : row.bkhm
			},
			success : function(data) {
				if (data == '删除成功') {
					loadBk(1);
				} else {
					alert(data);
				}
			},
			error : function() {
				alert('删除布信息出错！');
			}
		});
	}
}
/*
function checkedForm() {
	// 验证布控号码
	var bklx = $('input[type=radio][name=bklx]:checked').val();
	var bkhmStr = $('#bkhm').val().trim();
	var checked=true;
	if($("#bt").val().trim()==""){
		alert('布控标题不能为空');
		return false;
	}
	
	if (bkhmStr <= 0) {
		alert('身份证或车牌号不能为空');
		return false;
	}

	if(checked){
		var bks=bkhmStr.split(',');
		// 1:身份证 2:车牌号
		if (bklx == 1) {
			for(var i=0;i<bks.length;i++){
				checked= checkedSFZ(bks[i]);
			}
		} else if (bklx == 2) {
			for(var j=0;j<bks.length;j++){
				checked= checkedCarId(bks[j]);
			}
		} else {
			alert('请选择布控类型:身份证或车牌号');
			checked= false;
		}
	}
	
	
	if($("#stopTime").val().trim()==""){
		alert('失效日期不能为空');
		return false;
	}
	if(checked){
		// 验证是否启用
		var flag = $('#flag').val();
		if (flag == 1 || flag == 0) {
	
		} else {
			alert('请选择是否启用');
			checked= false;
		}
	}
	
	if(checked){
		// 验证短信接收号码
		var dxjsdh = $('#dxjsdh').val();
		if(dxjsdh.length<0){
			alert("短信接收号码不能为空");
			checked= false;
		}else{
			checked= checkedPhone(dxjsdh);
		}
	}
	return checked;
}
//var sfz='510106198810152912';
//checkedSFZ(sfz);
// 验证身份证号码
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
		pass= false;
	} else if (!city[sfzID.substr(0, 2)]) {
		tip = "地址编码错误";
		pass= false;
	} else {
		// 18位身份证需要验证最后一位校验位
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
				tip = "校验位错误";
				pass= false;
			}
		}
	}
	if (!pass) {
		alert(tip);
	}
	return pass;
}

// 验证车牌号
function checkedCarId(carID) {
	var carRule = /^[\u4e00-\u9fa5]{1}[A-Z]{1}[A-Z_0-9]{5}$/;
	var pass=true;
	
	if (carID.search(carRule) == -1) {
		alert(carID + "输入的车牌号格式不正确");
		pass= false;
	}
	return pass;
}

// 手机号码验证
function checkedPhone(phone) {// /^1[3,5,8]\d{9}$/
	var phoneRule = /^1[3|4|5|7|8]\d{9}$/;
	var pass=true;
	
	if (!(phoneRule.test(phone))) {
		alert(phone + "输入的手机号格式不正确");
		pass= false;
	}
	return pass;
}


 * 添加布控规则 --------------- start
 
*//**
 * 显示布控规则BOX
 *//*
function showAddControl(){
	$("#clearForm").click();
	document.getElementById("controlTitleName").innerHTML = "添加布控规则";
	
	$("input[type=radio][name=bklx][value=1]").attr("checked", true);
	$("#mess").attr("value", "1");
	$("#flag").attr("value", "1");
	$("#bkr").attr("value", $("#bkr_i").html());

	getAddSource();
	$("#controlDiv").modal();
	addControl();
}

*//**
 * 执行添加布控规则方法
 *//*
function addControl() {
	$("#addControlButton").unbind("click");
	
	$("#addControlButton").click(function() {
		if (checkedForm()) {
//			var i = $("#controlGrid").datagrid("getRows");
//			if(i.length>10){
//				alert("最多只能添加10个规则");
//				return;
//			}
			$("#controlForm").form("submit", {
				url : "addControl.do",
				success : function(data) {
					document.getElementById("closeControlBox").click();
					loadBk(1);
				}
			});
		}
	});
}

 * 添加布控规则 --------------- end
 





 * 编辑布控规则 ----------- start
 
/**
 * 显示布控规则BOX
 *//*
function showEditControl(row){
	$("#clearForm").click();
	document.getElementById("controlTitleName").innerHTML = "编辑布控规则";
	
	// 设置已有参数
	$('#bt').val(row.bt);
	var bklx = row.bklx;
	if (bklx == 1) {
		$('input[type=radio][name=bklx][value=1]').attr('checked', true);
	} else if (bklx == 2) {
		$('input[type=radio][name=bklx][value=2]').attr('checked', true);
	} else {
		alert('bklx:' + bklx);
	}
	$('#bkhm').val(row.bkhm);
	$('#flag').val(row.flag);
	$('#dxjsdh').val(row.dxjsdh);
	$('#mess').attr('value', '1');
	$('#bkr').val(row.bkr);
	$('#stopTime').val(row.stopTime);
	$("#bkSource").attr('value', row.bkSource);
	
	getAddSource();
	$("#controlDiv").modal();
	editControl(row.bid);
}

*//**
 * 执行布控规则方法
 *//*
function editControl(bid) {
	$('#addControlButton').unbind("click");
	$('#addControlButton').click(function() {
		if (checkedForm()) {
			$.ajax({
				type : 'post',
				url : 'editControl.do',
				data : {
					'bid' : bid,
					'bt' : $('#bt').val(),
					'bklx' : $('input[type=radio][name=bklx]:checked').val(),
					'bkhm' : $('#bkhm').val(),
					'flag' : $('#flag').val(),
					'bkSource':$('#bkSource').val(),
					'dxjsdh' : $('#dxjsdh').val(),
					'bkr' : $('#bkr').val(),
					'stopTime' : $('#stopTime').val()
				},
				success : function(data) {
					document.getElementById("closeControlBox").click();
					loadBk(1);
				}
			});
		}
	});
}

 * 编辑布控规则 ----------- end
 

function getAddSource(){
	$.ajax({
		type:'post',
		url:'getGLSource.do',
		datatype:'json',
		async : false,
		success : function(data) {
			// 在下拉列表添加数据源
			$('#bkSource').empty();
			var selObj = $("#bkSource");
			var soujson = eval('(' + data + ')');

			for(var key in soujson){
				for ( var i = 0; i < soujson[key].length; i++) {
					selObj.append("<option value='" + soujson[key][i].squ + "'>" + soujson[key][i].title + "</option>");
				}
			}
			$("#bkSource").attr('value', 661);
		},
		error : function() {
			alert('请求失败！');
		}
	});
}


 * 编辑布控时间 ------------ start
 
*//**
 * 显示布控时间BOX
 *//*
function showEditBDTime(){
	$("#clearTimeForm").click();
	document.getElementById("timeTitleName").innerHTML = "设置布控时间";
	
	$.ajax({
		type:'post',
		url:'selectBDTime.do',
		data:{},
		success:function(data){
			var json=eval('('+data+')');
			$('#bdTime_value').attr('value',json.getTime);
		},
		error:function(){
			alert('请求错误！');
		}
	});
	
	$("#bdTime").modal();
	
	editBDTime();
}

*//**
 * 执行编辑布控时间
 *//*
function editBDTime(){
	$('#bk_btnTime').unbind("click");
	$('#bk_btnTime').click(function(){
		var timeValue=$('#bdTime_value').val();
		if(timeValue==''||timeValue<=0){
			alert('时间不能为空或0');
		}else{
			$.ajax({
				type:'post',
				url:'editBDTime.do',
				data:{
					"time":timeValue
				},
				success:function(data){
					var json=eval('('+data+')');
					if(json.setTime==''||json.setTime.equals('null')){
						
					}else{
						alert(json.setTime);
					}
					$('#bdTime').window('close');
					$('#controlGrid').datagrid('reload');// 重新加载页面
				},
				error:function(){
					alert('请求失败！');
				}
			});
		}
	});
}

 * 编辑布控时间 ------------ end
 

$('#inputNumber').click(function(){
	$.ajax({
		type:'post',
		url:'readFile.do',
		data:{
			file:k
		},
		dataType:'json',
		success:function(data){
			alert(data);
		},
		error:function(){
			alert('请求失败！');
		}
	});
});

*//**
 * 上传文件
 *//*
function getUpload(){
	var elementIds=["file"];
    $.ajaxFileUpload({
        url:'uploadExecl.do',//处理图片脚本
        secureuri :false,
        dataType:'text',
        fileElementId :'file',//file控件id
        elementIds: elementIds,
        success : function (data){
			$('#bkhm').attr('value',data);
        },
        error: function(data, status, e){
            alert("上传错误");
        }
    });
}

function closeAddWindow() {
	$('#controlDiv').window('close');
}

function fixWidth(percent){
	return $("#mian").width() * percent;
}
*/