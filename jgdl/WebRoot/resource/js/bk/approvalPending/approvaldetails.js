$(document).ready(function() {
	getPersonMsg();

});
/**
 * 获取json里的数据赋值给table
 * 
 */
function getPersonMsg() {
	var rec= json.RYLX;
	var sxsj = json.SXSJ;
	$(".XM").html(json.XM);
	if (rec== 0) {
		$(".RYLX").html("在逃");
	} else if (rec == 1) {
		$(".RYLX").html("重点");
	} else if (rec == 2) {
		$(".RYLX").html("临控");
	} else {
		$(".RYLX").html("其他");
	}
	$(".GMSFHM").html(json.GMSFHM);
	$(".LRRNAME").html(json.LRRNAME);
	$(".HJDZ").html(json.HJDZ);
	$(".CJSJ").html(json.CJSJ);
	if (sxsj == null || sxsj == undefined) {
		$(".SXSJ").html("无");
	} else {
		$(".SXSJ").html(json.SXSJ);
	}
}

/**
 *审批
 */
var falg = "";
function shenPi(){
	falg=1;
	$(".sp").css("display","block");
	$(".question").text("审批描述");
	$("div .yy textarea").attr('placeholder',"简单填写审批描述");
	//$("div .yy textarea").text("简单填写审批描述");
}

/**取消审批这个人*/
function canceled(){
	falg=2;
	$(".sp").css("display","block");
	$(".question").text("取消原因");
	$("div .yy textarea").attr('placeholder',"简单的描述你取消的原因");
	//$("div .yy textarea").text("简单的描述你取消的原因");
}

function tj(){
	if(falg==1){
		var bz = $("#bbzz").val();
		$.ajax({
			type : "post",
			url : "editApproval.do",
			dataType : 'json',
			data : {
				gmsfhm : json.GMSFHM,
				sprsqu : json.SPRSQU,
				bz:bz,
			},
			success : function(data) {
				if (data == '0') {
					window.location.href="gotoApprovalIndex.do?bkzt=1";
				}
			},
			error : function() {
				alert("审核失败");
				window.location.href='gotoApprovalIndex.do?bkzt=0';
			}
		});	
	}else if(falg==2){
		var bz = $("#bbzz").val();
		$.ajax({
			type : "post",
			url : "quxiaoApproval.do",
			dataType : 'json',
			data : {
				gmsfhm : json.GMSFHM,
				sprsqu : json.SPRSQU,
				bz:bz,
			},
			success : function(data) {
				if (data == '0') {
					window.location.href="gotoApprovalIndex.do?bkzt=0";
				}
			},
			error : function() {
				alert("取消失败");
				
			}
		});	
	}
	//$(".sp").css("display","none");
}

/*function quXiao(){
	$("#btnback").css("display","none");
	$("#btnbackcommit").css("display","block");
	$("#bz").css("visibility","visible");
	
}
function quXiaocommit(){
	var bz = $("#bz").val();
	$.ajax({
		type : "post",
		url : "quxiaoApproval.do",
		dataType : 'json',
		data : {
			gmsfhm : json.GMSFHM,
			sprsqu : json.SPRSQU,
			bz:bz,
		},
		success : function(data) {
			if (data == '0') {
				window.location.href="gotoApprovalIndex.do?bkzt=0";
			}
		},
		error : function() {
			alert("取消失败");
			
		}
	});	
}
function commit(){
	var bz = $("#bz").val();
	$.ajax({
		type : "post",
		url : "editApproval.do",
		dataType : 'json',
		data : {
			gmsfhm : json.GMSFHM,
			sprsqu : json.SPRSQU,
			bz:bz,
		},
		success : function(data) {
			if (data == '0') {
				window.location.href="gotoApprovalIndex.do?bkzt=1";
			}
		},
		error : function() {
			alert("审核失败");
			window.location.href='gotoApprovalIndex.do?bkzt=0';
		}
	});	
}

*/
