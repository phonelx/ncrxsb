   

/**请求的js**/
	var mingcheng = "";
	//全局要申请的类型
	var types = [];
	$(function(){
		$("#tabs").niceScroll({ 
		    cursorcolor: "#ccc",//#CC0071 光标颜色 
		    cursoropacitymax: 1, //改变不透明度非常光标处于活动状态（scrollabar“可见”状态），范围从1到0 
		    touchbehavior: false, //使光标拖动滚动像在台式电脑触摸设备 
		    cursorwidth: "5px", //像素光标的宽度 
		    cursorborder: "0", //     游标边框css定义 
		    cursorborderradius: "5px",//以像素为光标边界半径 
		    autohidemode: false //是否隐藏滚动条 
		});
		$("#requestExample").niceScroll({ 
		    cursorcolor: "#ccc",//#CC0071 光标颜色 
		    cursoropacitymax: 1, //改变不透明度非常光标处于活动状态（scrollabar“可见”状态），范围从1到0 
		    touchbehavior: false, //使光标拖动滚动像在台式电脑触摸设备 
		    cursorwidth: "5px", //像素光标的宽度 
		    cursorborder: "0", //     游标边框css定义 
		    cursorborderradius: "5px",//以像素为光标边界半径 
		    autohidemode: false //是否隐藏滚动条 
		});
		//初始化类型
		var cks = [];
		$("#typeDiv .type_ck").each(function(){
			cks.push($(this).val());
		});
		types = cks;
		
		setTimeout("show()",1000);
		initWH();
		initBind();
		getDate();
	});
	function show(){
		openDiv({id:"typeDiv" ,width:"550",height:"200"});
		$("#baseInfo").show();
		$("#overDiv").css("display","block");
	}
	
	//选择类型
	function chooseType(){
		//获取选择的类型
		var cks = [];
		$("#titletip_div_temp_content .type_ck").each(function(){
			if($(this).attr("checked")){
					cks.push($(this).val());
			}
		});
		if(cks.length == 0){
			return ;
		}
		$(".li_css").hide();
		for ( var i = 0; i < cks.length; i++) {
			
				$("#li_"+cks[i]).show();
			    $("#li_"+cks[i]).addClass("show");
		}
		types = cks;
		closeDiv();	
		$("#base").attr("tagname","true");
		$("#overDiv").css("display","none");
	}

	function initBind(){
		//隐藏掉除基本信息
		$("#ulPage .li_css").each(function(){
			$("#tab_"+$(this).attr("tag") ).hide();
		});
		
		$("#ulPage li").click(function(){
			$("#ulPage li").removeClass("checked");
			$(this).addClass("checked");
			//
			$("#ulPage li").each(function(){
				$("#tab_"+$(this).attr("tag") ).hide();
			});
			
			$("#tab_"+$(this).attr("tag") ).show();
		});	
		//提交表单
		$("#context_btn").click(function(){
			formSmt();		
		});
	}
	function initWH(){
		var w = $(window).width();
		var h = $(window).height();
		$("#tabs").css("height",h-3+"px");
	}
	
	/**
	 * 预览提交
	 * 
	 * */
	function formSmt(){
		if(!formSmtCk()){
			return false;
		}
		//清空处理
		$(".prTab .input_td").each(function(){
			$(this).html("");
		});
		$("#input_div").html("");
		$("#requestExample").modal();
		$("#requestExample").show();
		//基础信息赋值
		$("#T_baseInfo_explain").append($("#baseInfo_explain").val());
		$("#T_baseInfo_ajbh").append($("#baseInfo_ajbh").val());
		$("#T_baseInfo_ajxz").append($("#baseInfo_ajxz").val());
		$("#T_baseInfo_ajmc").append($("#baseInfo_ajmc").val());
		$("#T_baseInfo_jyaq").append($("#baseInfo_jyaq").val());
		$("#spr_td input[type=checkbox]").each(function(){
			if($(this).attr("checked")=="checked"){
				$("#T_baseInfo_spr").append($(this).val().split("_")[1]+"&nbsp;&nbsp;");
			}
		});
		//赋值其他信息
		var type=null;
	if($("#base").attr("tagname")=="true"){
		$("#ulPage .show").each(function(){
			 type=$(this).attr("tag");
				//开始赋值
			 if(type=="0_1"){
					var appendData1="<div class='box_top'> <b class='pl15'>信息查询</b> </div>"+
									"<table cellpadding='0' cellspacing='0' class='prTab'>"+
									"<tbody><tr><th rowspan='6'  height='20'>查询内容</th>"+
								    "<th width='50'  >序号</th><th width='187' >电信标识码</th>"+
									"<th width='186' >持有人姓名</th><th width='186' >政治面貌</th>"+
								    "<th width='187' >职务</th></tr>";
					for(var i=1;i<6;i++){
						appendData1+="<tr><td class='input_td'>"+i+"</td><td id='T_tab_0_1_phone"+i+"'></td>"+
						 		     "<td id='T_tab_0_1_username"+i+"'></td>"+
						 		     "<td id='T_tab_0_1_zzmm"+i+"'></td>"+
						 		     "<td id='T_tab_0_1_zw"+i+"'></td></tr>";
					}
					var src="";
					var bt="案件侦办";
					appendData1=appendData1+"<tr><th>查询详情说明</th><td colspan='5'>";
					$("#tab_0_1 input[type=checkbox]").each(function(){
						if($(this).attr("checked")=="checked"){
							src="<img src='resource/images/check.png' width='20' style='margin-left:5px;'>&nbsp;";	
							appendData1=appendData1+src+bt;
							bt="紧情处理";
						}
						else{
							src="<img width='20' src='resource/images/checkno.png' style='margin-left:5px;'>&nbsp;";
							appendData1=appendData1+src+bt;
							bt="紧情处理";
						}
					});
					appendData1=appendData1+"</td></tr></tbody></table>";	
					$("#input_div").append(appendData1);  
					for(var i=1;i<6;i++){
					$("#T_tab_0_1_username"+i).append($("#tab_0_1_username"+i).val());
					$("#T_tab_0_1_phone"+i).append($("#tab_0_1_phone"+i).val());
					$("#T_tab_0_1_zzmm"+i).append($("#tab_0_1_zzmm"+i).val());
					$("#T_tab_0_1_zw"+i).append($("#tab_0_1_zw"+i).val());
					}
				}
				if(type=="3_1"){ 
					var appendData2="<div class='box_top'> <b class='pl15'>银行查询</b></div>"+
		                            "<table cellpadding='0' cellspacing='0' class='prTab'>"+
							        "<tbody><tr><th  height='100'>查询、监控银行卡卡号</th>"+
						            "<td width='800' id='T_tab_3_1_yhkh'></td></tr><tr>"+
						            "<th height='120'>查询、监控具体要求</th>"+
						            "<td width='800' id='T_tab_3_1_yq' ></td></tr></tbody></table>";
					$("#input_div").append(appendData2); 
					$("#T_tab_3_1_yhkh").append($("#tab_3_1_yhkh").val());
					$("#T_tab_3_1_yq").append($("#tab_3_1_yq").val());
				}
				if(type=="4_1"){
					var appendData3="<div class='box_top'> <b class='pl15'>话单查询</b></div>"+
		                	        "<table cellpadding='0' cellspacing='0' class='prTab'><tbody>"+
							        "<tr><th>协作要求详情说明</th><td width='800' height='150' id='T_tab_4_1_content'></td>"+
							        "</tr></tbody></table>";
					$("#input_div").append(appendData3); 
					$("#T_tab_4_1_content").append($("#tab_4_1_content").val());
				}
		});	
	}
	else{
		yulan();
	}
	
	}
	/**
	 * 预览赋值
	 */
	function yulan(){
		var appendData1="<div class='box_top'> <b class='pl15'>信息查询</b> </div>"+
		"<table cellpadding='0' cellspacing='0' class='prTab'>"+
		"<tbody><tr><th rowspan='6'  height='20'>查询内容</th>"+
	    "<th width='50'  >序号</th><th width='187' >电信标识码</th>"+
		"<th width='186' >持有人姓名</th><th width='186' >政治面貌</th>"+
	    "<th width='187' >职务</th></tr>";
		for(var i=1;i<6;i++){
		appendData1+="<tr><td class='input_td'>"+i+"</td><td id='T_tab_0_1_phone"+i+"'></td>"+
			     "<td id='T_tab_0_1_username"+i+"'></td>"+
			     "<td id='T_tab_0_1_zzmm"+i+"'></td>"+
			     "<td id='T_tab_0_1_zw"+i+"'></td></tr>";
		}
		var src="";
		var bt="案件侦办";
		appendData1=appendData1+"<tr><th>查询详情说明</th><td colspan='5'>";
		$("#tab_0_1 input[type=checkbox]").each(function(){
		if($(this).attr("checked")=="checked"){
		src="<img src='resource/images/check.png' width='20' style='margin-left:5px;'>&nbsp;";	
		appendData1=appendData1+src+bt;
		bt="紧情处理";
		}
		else{
		src="<img width='20' src='resource/images/checkno.png' style='margin-left:5px;'>&nbsp;";
		appendData1=appendData1+src+bt;
		bt="紧情处理";
		}
		});
		appendData1=appendData1+"</td></tr></tbody></table>";	
		$("#input_div").append(appendData1);  
		for(var i=1;i<6;i++){
		$("#T_tab_0_1_username"+i).append($("#tab_0_1_username"+i).val());
		$("#T_tab_0_1_phone"+i).append($("#tab_0_1_phone"+i).val());
		$("#T_tab_0_1_zzmm"+i).append($("#tab_0_1_zzmm"+i).val());
		$("#T_tab_0_1_zw"+i).append($("#tab_0_1_zw"+i).val());
		}
		var appendData2="<div class='box_top'> <b class='pl15'>银行查询</b></div>"+
        "<table cellpadding='0' cellspacing='0' class='prTab'>"+
        "<tbody><tr><th  height='100'>查询、监控银行卡卡号</th>"+
        "<td width='800' id='T_tab_3_1_yhkh'></td></tr><tr>"+
        "<th height='120'>查询、监控具体要求</th>"+
        "<td width='800' id='T_tab_3_1_yq' ></td></tr></tbody></table>";
		$("#input_div").append(appendData2); 
		$("#T_tab_3_1_yhkh").append($("#tab_3_1_yhkh").val());
		$("#T_tab_3_1_yq").append($("#tab_3_1_yq").val());
		var appendData3="<div class='box_top'> <b class='pl15'>话单查询</b></div>"+
        "<table cellpadding='0' cellspacing='0' class='prTab'><tbody>"+
        "<tr><th>协作要求详情说明</th><td width='800' height='150' id='T_tab_4_1_content'></td>"+
        "</tr></tbody></table>";
		$("#input_div").append(appendData3); 
		$("#T_tab_4_1_content").append($("#tab_4_1_content").val());
	}
	//表单提交
	function formSmtOk(){
		$.ajax({
            cache: true,
            type: "POST",
            url:"uploadPreview.do",
            data:$('#form1').serialize(),
            async: false,
            dataType:"json",
            error: function(request) {
            	$("#msgExample").modal();
        		$("#requestExample").modal("hide");
            	$("#msgDiv").append("请求失败！");
            },
            success: function(request) {
            	$("#msgExample").modal();
        		$("#requestExample").modal("hide");
        		$("#msgDiv").append("请求成功！");
            }
        });
	}
	function addContinue(){
		location.href="showRequestCIndex.do";
	}
	function backMain(){
		$("#msgExample").modal("hide");
	}
	/**
	 * 表单检查
	 * */
	 function formSmtCk(){
		 
		 var flage = true;
		 $("#type").val(types.join(","));
		 
		 //alert(types.join(","));
		 //基本信息
		 $("#tab_baseInfo .input_ck").each(function(){
			 if(flage ==false){
				 return false;
			 }
			 if($(this).val()=="" || $(this).val().length < parseInt($(this).attr("tagLength")) ){
				 var msg = ($(this).attr("tag")+",不能少于"+$(this).attr("tagLength")+"个字符！" );
				 openMsg({width:300,height:200,msg: msg });
				 flage= false;
				 return false;
			 }
               if($(this).val().length > parseInt($(this).attr("maxtagLength")) ){
				 var msg = ($(this).attr("tag")+",不能大于"+$(this).attr("maxtagLength")+"个字符！" );
				 openMsg({width:300,height:200,msg: msg });
				 flage= false;
				 return false;
			 }
		 });
		//类型信息 
		 for ( var i = 0; i < types.length; i++) {
			var key = types[i];
			
			$("#tab_"+key+" .input_ck").each(function(){
				if(flage ==false){
					 return false;
				 }
				 if($(this).val()=="" || $(this).val().length < parseInt($(this).attr("tagLength")) ){
					 var msg = ($(this).attr("tag")+",不能少于"+$(this).attr("tagLength")+"个字符！" );
					 openMsg({width:300,height:200,msg: msg });
					 flage= false;
					 return false;
				 }
				 if($(this).val().length > parseInt($(this).attr("maxtagLength")) ){
					 var msg = ($(this).attr("tag")+",不能大于"+$(this).attr("maxtagLength")+"个字符！" );
					 openMsg({width:300,height:200,msg: msg });
					 flage= false;
					 return false;
				 }
			 });
		}
		 return flage;
	 }
	 /**
	  * 获取当前系统时间
	  */
function getDate(){
	$("#date").html("");
        var date = new Date();
	    var seperator1 = "-";
	    var seperator2 = ":";
	    var month = date.getMonth() + 1;
	    var strDate = date.getDate();
	    if (month >= 1 && month <= 9) {
	        month = "0" + month;
	    }
	    if (strDate >= 0 && strDate <= 9) {
	        strDate = "0" + strDate;
	    }
	    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
	            + " " + date.getHours() + seperator2 + date.getMinutes()
	            + seperator2 + date.getSeconds();
	    //return currentdate;
	$("#date").append("申请时间："+currentdate);
}	
/**
 * 
 * 打印
 */
function print(){
	$("#req_pr").print();
}
/**
 * 初始化审批人
 */
function initSpr(){
	$("#spr_td").html("");
	$.ajax({
        type: "POST",
        url:"loadSpr.do",
        async: false,
        dataType:"json",
        error: function(data) {
        
        },
        success: function(data) {
        	var spr=data.spr;
        	var html="";
        	for(var i=0;i<spr.length;i++){
        	html+="<input type='checkbox' value='"+spr[i].squ+"_"+spr[i].roleName+"' style='width:20px;" +
			      "height:15px;' name='baseInfo_spr"+i+"' id='baseInfo_spr"+i+"'>"+spr[i].roleName+"&nbsp;&nbsp;";	
        		}
       $("#spr_td").append(html);
        }
    });
}
	