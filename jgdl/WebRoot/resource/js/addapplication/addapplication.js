
//通过身份证号码进行差查找
function findbyidcard(){
	openMasked();
	//清理历史数据
	$("#createb").css("display","none");
	$(".tablel").css("display","none");
	//关闭输入框的控件
	dclose();
	var idNum = $("#idcard").val().trim();
	
	//判断是否为空值
	if(''==idNum||idNum==null){
		remindMessage('请输入身份证号码')
		reloadOwn();
		return false;
	}
	//判断是否是有效身份证号码
	if(!(checkedSFZ(idNum))){
		reloadOwn();
		return false;
	}
	if((existInPage(idNum))){
		remindMessage('该号码已添加')
		reloadOwn();
		return false;
	}
	//判断是否是布控过的
	if(!(checkIsBked(idNum))){
		remindMessage('该号码已布控')
		reloadOwn();
		return false;
	}else{
		//先展示弹窗信息
		showDetailed();
	}
}
//添加记录到页面
function addRecordToPage(){
	$.ajax({
		  type: 'POST',
		  url: 'findByIDCard.do',
		  dataType: 'JSON',
		  data: { 
			  'idNum' : $("#idcard").val().trim(),
		  },
		  success: function(data){
			 var chanel = data.victory;
			 if(chanel.length > 0){
			 $(".tablel").css("display","block");
			 $("#createb").css("display","block");
			 var html = "";
			 for(var i=0;i<chanel.length;i++){
				  /*console.log(i+'次\tIDCARD:'+(data.victory[i].idcard)+'\tSQU:'+(data.victory[i].squ))*/
				  var showrylx = '';
				  
				  if(data.victory[i].rylx == 0){
					  showrylx = '在逃人员';
				  }
				  if(data.victory[i].rylx == 1){
					  showrylx = '重点人员';
				  }
				  if(data.victory[i].rylx == 2){
					  showrylx = '监控人员';
				  }
				  if(data.victory[i].rylx == 3){
					  showrylx = '案件嫌疑人';
				  }
				  html+="<tr>" +
				  		"<td id='"+i+"'>"+(data.victory[i].name)+"</td>"+
				  		"<td id='"+i+"'>"+(data.victory[i].idcard)+"</td>"+
				  		"<td id='"+i+"'>"+showrylx+"</td>" +
				  		"<td>" +
				  		"<button value="+(data.victory[i].idcard)+" onclick='deleteEle(this);' style='width:14px;height:14px;border-radius: 0px;margin-top:0px;background: #f0f0f0;'><img src='resource/images/130.png' style='width:15px;height:15px;' /></button>" +
				  		"</td>"+
				  		"</tr>";
			  }
			 $("#tbody").html(html);
			 $("#nodata").css("display","none");
			 cleanInput();
			  }else{
				  $("#createb").css("display","none");
				  $(".tablel").css("display","none");
				  $("#nodata").css("display","block");
			  }
		  },
		  error : function(){
			  remindMessage("error")
		  }
		});
}


//删除当前元素
function deleteEle(obj) {
	//按照要求删除页面数据效果，发送请求进行临时数据库的数据的删除
	$(obj).parent().parent().remove();
	var rylx = $(obj).parent().siblings().eq(2).text();
	if("在逃人员" == rylx){ 
		rylx = "0";
	}
	if("重点人员" == rylx){ 
		rylx = "1";
	}
	if("监控人员" == rylx){ 
		rylx = "2";
	}
	if("案件嫌疑人" == rylx){ 
		rylx = "3";
	}
	console.log('rylx:'+rylx)
	var id = obj.value;
	deleteRecordById(id,rylx);
	var len = $("#tbody tr").length;
	if(len == 0){
		$("#createb").css("display","none");
		$(".tablel").css("display","none");
		$("#nodata").css("display","block");
		//清空静态变量
		clearVariable();
	}
}
//删除中间数据库指定的记录数(并没有删除到来源数据的数据库)
function deleteRecordById(id,rylx){
	$.ajax({
		type: 'POST',
		  url: 'deleteRecordById.do',
		  dataType: 'JSON',
		  data: { 
			  'id' : id,
			  'rylx' : rylx,
		  },
		  success: function(data){
			  
			  console.log("deleteRecordById success")
			  console.log(data.result)
		  },
		  error: function(){
			  console.log("deleteRecordById error")
		  }
		
	})
}

//身份证验证JS函数
function checkedSFZ(sfzID) {
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
		remindMessage(tip);
	}
	return pass;
}

//清空JAVA后台静态的变量的值
function clearVariable(){
$.ajax({
	  type: 'POST',
	  url: 'cleanStaticVariable.do',
	  dataType: 'JSON',
	  success: function(data){
	  },
	  error :function(){
	  }
});
}

function showDetailed(){
	var id = $("#idcard").val();
	$.ajax({
		  type: 'POST',
		  url: 'ajaxShowDetailed.do',
		  dataType : 'json',
		  data :{ 
			  'id' : id
		  },
		  success: function(d){
			  $('#city').show();
			  $('#showMsg').show();
			  $('#rylxselected').append("<option value='fault' selected='selected'>-请选择人员类型-</option><option value='0'>在逃人员</option><option value='1'>重点人员</option><option value='2'>监控人员</option><option value='3'>案件嫌疑人</option>");
			 
			  $(".xm").html(d.victory[0].name);
			  $(".sfzh").html(d.victory[0].idcard);
			  $(".mz").html(d.victory[0].mz);
			  $(".jzd").html(d.victory[0].jzdz);
		  },
		  error :function(){
			  console.log('ERROR')
		  }
	});

}
//关闭信息框
function gbclose(){
	$('#showMsg').hide();
	$('#city').hide();
	closeMasked();
	$("#hide").attr("value","20");
}
//开启信息框
function open(){
	 $('#city').show();
	 $('#showMsg').show();
}

//进行添加操作
function addMeW(){
	var myselect=document.getElementById("rylxselected");
	var index=myselect.selectedIndex ; 
  	var rylx = myselect.options[index].value;
  	
  	if("fault"==rylx){
  		/*remindMessage('请选择人员类型');*/
  		$("#rylxselected").css("border","1px solid rgb(239, 42, 82)");
  		return false;
  	}
  	$('#rylxselected').empty();
  	//隐藏当前
  	gbclose();
  	closeMasked();
  	//添加到临时表里去
  	var idcard = $("#idcard").val();
  	addTransition(idcard,rylx);
  	
}
//添加到临时表里去
function addTransition(idcard,rylx){
	$.ajax({
		type: 'POST',
		url: 'addTransition.do',
		dataType : 'json',
		data :{ 
			  'idcard' : idcard,
			  'rylx' : rylx
		  },
		success: function(d){
			//添加该记录到页面
		  	addRecordToPage();
		},
		error: function(){
			console.log('error')
		}
	});
}

//展示ID控件
function showIDCON(){
	$("#showIDCON").show();
}
//输出传输的值
function printValue(obj){
	var val = obj.value;
	var beforev = $("#idcard").val();
	var len = $("#idcard").val().length;
	if("backspace" == val){
		$("#idcard").val(beforev.substring(0,len-1));
		return false;
	}
	$("#idcard").val($("#idcard").val() + val);
}
//关闭ID控件	
function dclose(){
	$("#showIDCON").hide();
}

//关闭提醒
function closeing(){
	$("#remindxx").hide();
	closeMasked();
}
//打开提醒
function remindMessage(message){
	$("#remindxx").show();
	$("#remind p").text(message);
}

//检查该身份证是否是否布控过的
function checkIsBked(idNum){
	var IsExist = false;
	$.ajax({
		type: 'POST',
		url: 'checkIsBked.do',
		dataType : 'json',
		async:false,//同步的
		data :{ 
			  'idNum' : idNum,
		  },
		success: function(d){
			if(d.result == "usable"){
				return IsExist=true;
			}else{ 
				return IsExist=false;
			}
		},
		error: function(){
			console.log('error')
		}
	});
	return IsExist;
}

//判断是否在页面上
function existInPage(idNum){
	var IsExist = false;
	$.ajax({
		type: 'POST',
		url: 'existInPage.do',
		dataType : 'json',
		async:false,//同步的
		data :{ 
			  'idNum' : idNum,
		  },
		success: function(d){
			if(d.result == "exist"){
				return IsExist=true;
			}else{ 
				return IsExist=false;
			}
		},
		error: function(){
			console.log('error')
		}
	});
	return IsExist;
}

//重新读取数据
function reloadOwn(){
		$.ajax({
			  type: 'POST',
			  url: 'reloadOwn.do',
			  dataType: 'JSON',
			  success: function(data){
				 readPrintPage(data);
			  },
			  error : function(){
				 remindMessage("error")
			  }
			});
}
//获取数据打印到页面上
function readPrintPage(data){
	var chanel = data.victory;
	if(chanel.length > 0){
		 $(".tablel").css("display","block");
		 $("#createb").css("display","block");
		 var html = "";
		 for(var i=0;i<chanel.length;i++){
			  /*console.log(i+'次\tIDCARD:'+(data.victory[i].idcard)+'\tSQU:'+(data.victory[i].squ))*/
			  var showrylx = '';
			  
			  if(data.victory[i].rylx == 0){
				  showrylx = '在逃人员';
			  }
			  if(data.victory[i].rylx == 1){
				  showrylx = '重点人员';
			  }
			  if(data.victory[i].rylx == 2){
				  showrylx = '监控人员';
			  }
			  if(data.victory[i].rylx == 3){
				  showrylx = '案件嫌疑人';
			  }
			  html+="<tr>" +
			  		"<td id='"+i+"'>"+(data.victory[i].name)+"</td>"+
			  		"<td id='"+i+"'>"+(data.victory[i].idcard)+"</td>"+
			  		"<td id='"+i+"'>"+showrylx+"</td>" +
			  		"<td>" +
			  		"<button value="+(data.victory[i].idcard)+" onclick='deleteEle(this);' style='width:14px;height:14px;border-radius: 0px;margin-top:0px;background: #f0f0f0;'><img src='resource/images/130.png' style='width:15px;height:15px;' /></button>" +
			  		"</td>"+
			  		"</tr>";
		  }
		 cleanInput();
		 $("#tbody").html(html);
		 $("#nodata").css("display","none");
		  }else{
			  $("#createb").css("display","none");
			  $(".tablel").css("display","none");
		  }
}
//清空输入框的值，方便下次使用
function cleanInput(){
	$("#idcard").val("");
}

//使用遮罩层
function openMasked(){
	$("#bb").addClass("aa");
}
//关闭遮罩层
function closeMasked(){
	$("#bb").removeClass("aa");
}