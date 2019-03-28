$(function() {
	selectAzfs();
	selectGdlx();
	//queryZhbjXq();
});	

function cancel_clear(){
	$("#param_where").val("");
	$("#param_fh").val("");
}


function keyDown(e){ 
	 var keycode = 0;
	 keycode = e.keyCode;
	 if (keycode == 13 ) //回车键是13
	 {
		 searchWord();
	 }
}

$(function(){
	//searchWord();
});
var i=0;

function parentload(obj){
	
	$("#abj").hide();
	$("#azhbj").css("display","block");
	if($(obj).next().hasClass('on')){
    	$(obj).next().slideUp(500).removeClass('on');  
    	$(obj).prev().text('[+]');  
    	$("#zhbjmc").html("");
    }else{  
    	$(obj).next().slideDown(500).addClass('on');  
    	$(obj).prev().text('[-]'); 
    	
    	
    	
    }
	
	var  zhsqu = $(obj).attr("id")
	$("#ZHSQU1").val(zhsqu);
	
	
	var flag = $(obj).attr("flag")
	$("#flag").val(flag)	
	loadMenu();
	searchWord();
	
	
}


//重新加载左部菜单列表
function loadMenu(){
		$.ajax({
			type : 'post',
			url : 'queryZhbjMenu.do',
			timeout : 1321231232131213123,
			dataType:"json",
			data:{
				
			},
			success : function(data) {
				//
				var html = "";
				var list = data.list			
					for(var i = 0;i < data.list.length; i++){				
							html+=" <li class='border-bottom' style='display:block; margin-left:15px;'>";			
							html+=" <a href='javascript:void(0);'  style='color:#3f9fd9;' class='ajax-link'  flag='1'  id='"+data.list[i].ZHSQU+"' title ='"+data.list[i].DXZHMC+"' fsdm='"+data.list[i].AZFSDM+"'onclick='queryZhbjXq(\""+data.list[i].ZHSQU+"\")' '>";
							html+=" <i class='icon-darkgray '></i>&nbsp;"+data.list[i].DXZHMC+"</a>";									
							html+=" </li>";
					
				}
				$("#topnav1").css("display","block") ;
				$("#topnav1").html(html)
			},
			/*error : function() {
				Modal.alert(
				  {
				    msg: '资源分离列表加载出错',
				    title: '消息提示',
				    btnok: '确定',
				    btncl:'取消'
				    	
				  });
			}*/
		});
	

}
var records=0;
var currentPage=0;
var total=0;
var pageShowPage=5;//显示多少个人分页标签
var pageRowNum=10;//每页显示记录数
var pager=1;
var pagertype = 1
/*****关键字检索****/
var query="";
function searchWord(){
	/*var word = $("#input_search").attr("value");
	if (word=="关键字搜索") {
		query ="";
	}else{
		query=" TYPENAME LIKE '%"+word+"%'";
	} */
	gridload(pager);
}

//加载表格
var grid ;
var pageNo =1;
function gridload(pager){
	pagertype =1
	$("#zhqx").hide();
	$("#uiGrid-uigrid").css("display","block")
	$("#ss").css("display","block");
	$("#ss1").css("display","none");
	var key = $("#input_search").val();
	
	if(key=="关键字搜索"){
		key = "1";
	}else{
		key = $("#input_search").val();
	}
	
	var html="";
	$.ajax( {
		type : 'post',
		url : 'queryZhbjInfo.do',
		dataType : 'json',
		async : false,
		data : {
			rows:pageRowNum,
			page:pager,
			key:key,
		},
		success : function(data) {
			
			records=data.total;
			currentPage=data.pageNumber;
			total = data.pageCnt;
			pageRowNum=data.pageSize;
			if(data.rows.length==0){
				$("#box_body").html("");
				top=1;
			}
			
			list1 = data.rows;
			
		for(var i = 0;i<list1.length;i++){
			
			html+="<tr class='tr'>";  
			if(list1[i].ZP==null||list1[i].ZP==""){
				html+=" <td class='td4 td7'><img class='dashboard-avatar' src='resource/images/mrtp.png'></td>";
			}else{
				html+=" <td class='td4 td7'><img class='dashboard-avatar' src='/upload/"+list1[i].ZP+"'></td>";
			}			
			html+=" <td class='td4 td5 td6'><p style='font-size: 15px;font-weight: bold'>"+list1[i].DXZHMC+"</p><br> </td>";
			html+=" <td class='td4 td5 '><br><div class='figcaption'><p title='"+list1[i].GDLXDM+"'>"+list1[i].GDLXDM+"</p></div></td>";
			html+=" <td class='td4 td5'><br>"+list1[i].ZJXS+"</td>";			
			
			
			//html+=" <td><a onclick='' title='查看' href='javascript:void(0);'> <i class='icon-zoom-in icon-black'></i></a>&nbsp";
			html+=" <td class='td4 td5'><br><a onclick='editZh(\""+list1[i].ZHSQU+"\")' title='编辑' href='javascript:void(0);'><i class='icon-edit icon-black'></i></a>&nbsp";
			html+=" <a onclick='deleteZh(\""+list1[i].ZHSQU+"\")' title='删除' href='javascript:void(0)'><i class='icon-trash icon-black'></i></a></td>";			
			html+="</tr>";     
		}
		
		/*	$("#box_body").html("");*/
			$("#box_body").html(html);
			pageDiv(records,total,currentPage);
		},
		error : function() {
			Modal.alert(
			  {
			    msg: '应用加载出错',
			    title: '消息提示',
			    btnok: '确定',
			    btncl:'取消'
			    	
			  });
		}
	});
}
var _code = "";
//组合部件基本信息
function queryZhbjXq(code){
	$("#zhbjsqu").val(code);
	_code = code

	$("#zhbjmc").html("/"+$("#"+code).attr("title"));
	var squ = $("#"+code).attr("id");
	var flag = $("#"+code).attr("flag");
	$("#fsdm").val($("#"+code).attr("fsdm"));
	
	$("#ZHSQU2").val(squ)
	$("#abj").show();
	$("#azhbj").css("display","none");
	$("#zhqx").show();
	//$("#flag").val(flag)
	$("#ss").css("display","none");
	$("#ss1").css("display","block");
	var key = $("input_search1").val();
	
	if(key=="关键字搜索"){
		key = "1";
	}else{
		key = $("input_search1").val();
	}
	var html="";
	$.ajax( {
		type : 'post',
		url : 'querySingleZhbj.do',
		dataType : 'json',
		async : false,
		data : {			
			squ:squ,			
		},
		success : function(data) {

			list1 = data.list
			$("#ZHDXMC").html("")
			$("#ZHGDLX").html("")
		
			$("#ZHZJXS").html("")			
				$("#Img").html("<img src='/upload/"+list1[0].ZP+"' />")
				$("#ZHDXMC").html(list1[0].DXZHMC)
				$("#ZHGDLX").html(list1[0].GDLX)
				
				$("#ZHZJXS").html(list1[0].ZJXS)

			
		
			$("#box_body").html(html);
			//pageDiv(records,total,currentPage);
		},
		error : function() {
			Modal.alert(
			  {
			    msg: '应用加载出错',
			    title: '消息提示',
			    btnok: '确定',
			    btncl:'取消'
			    	
			  });
		}
	});
	queryZhbjXq1(code,1);
}

/**
 * 
  * queryZhbjXq:(获取组合部件的详细信息)
  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
  * @author: 唐青
  * @dateTime: 2017-10-26 下午4:45:26
  * @param: @param obj
  * @return: void
 */
function queryZhbjXq1(code,pager){
	pagertype = 2
	code = _code;
	
	$("#zhbjmc").html("/"+$("#"+code).attr("title"));
	var squ = $("#"+code).attr("id");
	
	var flag = $("#"+code).attr("flag");
	$("#fsdm").val($("#"+code).attr("fsdm"));
//	$("#ZHSQU2").val(squ)
	$("#abj").show();
	$("#azhbj").css("display","none");
	$("#zhqx").show();
	//$("#flag").val(flag)
	$("#ss").css("display","none");
	$("#ss1").css("display","block");
	var key = $("#input_search1").val();

	if(key=="关键字搜索"){
		key = "1";
	}else{
		key = $("#input_search1").val();
	}
	
	var html="";
	$.ajax( {
		type : 'post',
		url : 'queryZhbjXq.do',
		dataType : 'json',
		async : false,
		data : {
			rows:pageRowNum,
			page:pager,
			squ:squ,
			key:key,
		},
		success : function(data) {
			
			records=data.total;
			currentPage=data.pageNumber;
			total = data.pageCnt;
			pageRowNum=data.pageSize;
			if(data.rows.length==0){
				$("#box_body").html("");
				top=1;
			}
		
			list1 = data.rows			
				for(var i = 0;i<list1.length;i++){
					
					
					html+="<tr class='tr'>";     
					if(list1[i].BZP==null||list1[i].BZP==""){
						html+=" <td class='td1 td0'><img class='dashboard-avatar' src='resource/images/mrtp.png'></td>";
					}else{
						html+=" <td class='td1 td0'><img class='dashboard-avatar' src='/upload/"+list1[i].BZP+"'></td>";
					}
					
					html+=" <td class='td1 td2 td3'><p style='font-size: 15px;font-weight: bold'>"+list1[i].CYMC+"</p><br> <p>"+list1[i].CPBM+"</p></td>";
				
					if(list1[i].CBDJ==""||list1[i].CBDJ==null){
						html+=" <td class='td1 td2'></td>";
					}else{
						html+=" <td class='td1 td2'><br>单价："+list1[i].CBDJ+"</td>";
					}
					
					html+=" <td class='td1 td2' ><br><i class='icon-minus'  onclick='minus(this)'></i>&nbsp;&nbsp;<input onblur='getNum(this)' type='text' value='"+list1[i].SJ+"' style='width:30px'>&nbsp;&nbsp;<i class='icon-plus' onclick='plus(this)'></i></td>";
					//html+=" <td><a onclick='' title='查看' href='javascript:void(0);'> <i class='icon-zoom-in icon-black'></i></a>&nbsp";
					html+=" <td class='td1 td2' ><br><a onclick='editZhbj(\""+list1[i].ZBSQU+"\")' title='编辑' href='javascript:void(0);'><i class='icon-edit icon-black'></i></a>&nbsp";
					html+=" <a onclick='deleteZhbj(\""+list1[i].ZBSQU+"\")' title='删除' href='javascript:void(0)'><i class='icon-trash icon-black'></i></a></td>";			
					html+="</tr>";     
				}
			
		
		
			$("#box_body").html(html);
			pageDiv(records,total,currentPage);
		},
		error : function() {
			Modal.alert(
			  {
			    msg: '应用加载出错',
			    title: '消息提示',
			    btnok: '确定',
			    btncl:'取消'
			    	
			  });
		}
	});

}

function getNum(obj){
	var num = $(obj).val()
	$("#shul").val(parseInt(num))
}
function plus(obj){
var jia = 	$(obj).prev().val();

$(obj).prev().val(parseInt(jia)+1);
$("#shul").val(parseInt(jia)+1)
}

function minus(obj){
	var jian = 	$(obj).next().val();

		if(jian==0){
			$(obj).next().val(0)
			$("#shul").val(0)
		}else if(jian>0){
			
			$(obj).next().val(parseInt(jian)-1)
			$("#shul").val(parseInt(jian)-1)
		}
	}

function appRegister(){
	$("#secondTitle").html("新增组合部件")
	var type = $("#flag").val();
	$("#LXCS1").val("")
	$("#ZJXS1").val("")
	$("#DXZHMC1").val("")
	$("#cymc").val("")
	$("#jldw").val("")
	$("#edhl").val("")			
	//DepartAzfsbox.setValue();
	DepartGdlxbox.setValue();
	$("#example").modal();
	 $("#appBtn").unbind();
		 $("#appBtn").click(function(){
			 var lxcs = $("#LXCS1").val();
			 reg = /^[-+]?\d+$/;
			    if (!reg.test(lxcs)) {
			    	$("#example").hide();
		    		Modal.alert(
					  {
					    msg: '请填入大于或等于零是数字',
					    title: '消息提示',
					    btnok: '确定',
					    btncl:'取消'
					    	
					  }).on(function (e){
						  $("#example").show();
					  });
		    		return false;
			    } else {
			    	if(lxcs < 0 ){
			    		
			    		$("#example").hide();
			    		Modal.alert(
						  {
						    msg: '请填入大于或等于零是数字',
						    title: '消息提示',
						    btnok: '确定',
						    btncl:'取消'
						    	
						  }).on(function (e){
							  $("#example").show();
						  });
			    		return false;
			    	}
			    }
			 //管道类型
			 
			 //$("#hfexample1").val($("#ext-comp-1002").val())
			// alert(DepartGdlxbox.getValue())
			 var sjgdlx = $("#hfexample1").val(DepartGdlxbox.getValue());
				$("#uploadForm").form("submit",{ 
			    	url:"addZhbj.do",
			        success:function(data){ 
			        	searchWord()
						$('#example').modal("hide");
			        	$(".modal-backdrop").remove();
			        	
			        }  
			    });
				
			});	
		 		
}
function appRegisterBj(){
	
		$("#example3").modal();		
	 $("#appBtnBj").unbind();
		 $("#appBtnBj").click(function(){
			 var bjsl =[];
	         $("input[class='input']").each(function(){
	        	 if($(this).val().indexOf("_") >= 0 ) { 
	        		 bjsl.push($(this).val());
	        	 }
	             
	         })
	         if(bjsl.length==0){
	        	 $("#example3").hide();
			    	Modal.alert(
							  {
							    msg: '请填写部件数量',
							    title: '消息提示',
							    btnok: '确定',
							    btncl:'取消'
							    	
							  }).on(function (e){
								  $("#example3").show();
							  });
				    		return false;
	         }else {
	        	 for(var i=0,len=bjsl.length;i<len;i++){
	        		
	        		 var index = bjsl[i].indexOf("_");
	        	
	        		 var value = bjsl[i].substring(index+1,bjsl[i].length);
	        		 if((/^(\+|-)?\d+$/.test( value ))&&value>0){  
	        			
					    	
					    }else{  
					    	$("#example3").hide();
					    	Modal.alert(
									  {
									    msg: '请填入大于零的整数',
									    title: '消息提示',
									    btnok: '确定',
									    btncl:'取消'
									    	
									  }).on(function (e){
										  $("#example3").show();
									  });
						    		return false;
					    }
	        		
	        		
     			 
	        	 }
	        //	 alert(bjsl)
	        	 $.ajax({
  					url:'addBuJian.do',
  					type:'post',
  					data:{
  						bjsl:bjsl.toString(),
  						squ:$("#zhbjsqu").val(),
  					},		
  					success:function(data){
  						queryZhbjXq1(_code,1);
  						$('#example3').modal("hide");
  			        	$(".modal-backdrop").remove();
  					},
  				
  				});
	         }  
			 
	        
			});	

}


function inputOnBlur(obj){

	 var value = $(obj).val();
	 var bjsqu = $(obj).parent(obj).prev().prev().prev().prev().val();
	 if(bjsqu.indexOf("_") >= 0 ) { 
			 $(obj).parent(obj).prev().prev().prev().prev().val(bjsqu.substring(0,bjsqu.indexOf('_')));
	// alert($(obj).parent(obj).prev().prev().prev().prev().val())
	 }
	 bjsqu = $(obj).parent(obj).prev().prev().prev().prev().val();
	 
	 if(value!=""){
		 $(obj).parent(obj).prev().prev().prev().prev().val(bjsqu+"_"+value);
	 }
	/*  
	
	 bjsqu = $(obj).parent(obj).prev().prev().prev().prev().val();
	 
	
	 if(){
		 
	 }*/
   
} 
//编辑分类
function editZhbj(obj){
	Modal.confirm(
			  {
			    msg: "确认修改？"
			  })
			  .on( function (e) {
				  if(e){
var sl = $("#shul").val();
	$.ajax({
		url:'updateBuJian.do',
		type:'post',
		data:{
			'zhVo.ZZBSQU':obj,
			'zhVo.SJ':sl,
		},		
		success:function(data){
			queryZhbjXq1(_code,1);
		},
	
	});
				  }
			  });
}

function deleteZhbj(obj) {
	Modal.confirm(
  {
    msg: "确认删除？"
  })
  .on( function (e) {
	  if(e){
			$.ajax({
				type : "post",
				url : "deleteBuJian.do",
				timeout : 1321231232131213123,
				data : {
					"squ" :obj,
				},
				success : function(data) {
					
					queryZhbjXq1(_code,1);
					},
				
				error : function() {
					Modal.alert(
					  {
					    msg: '删除分类信息出错',
					    title: '消息提示',
					    btnok: '确定',
					    btncl:'取消'
					    	
					  });
				}
			});
	  }
  });
}

function editZh(obj){
	
$("#secondTitle").html("修改组合部件")
	$.ajax( {
		type : 'post',
		url : 'getZhbjBysqu.do',
		dataType : 'json',
		async : false,
		data : {	
			squ:obj,
		},
		success:function(data){
			var list = data.list;
			$("#ZHSQU1").val(list[0].ZHSQU)
			//$("#AZFS1").val(list[0].AZFS)
			$("#ZJXS1").val(list[0].ZJXS)
			//$("#GDLX1").val(list[0].GDLX)
			$("#DXZHMC1").val(list[0].DXZHMC)
			$("#LXCS1").val(list[0].LXCS)
			
			//DepartAzfsbox.setValue(list[0].AZFS,true);
			DepartGdlxbox.setValue(list[0].GDLX,true);
			
			
		}
	});
	
	
$("#example").modal();
	$("#appBtn").unbind();
	 $("#appBtn").click(function(){
		 var lxcs = $("#LXCS1").val();
		 reg = /^[-+]?\d+$/;
		    if (!reg.test(lxcs)) {
		    	$("#example").hide();
	    		Modal.alert(
				  {
				    msg: '请填入大于或等于零是数字',
				    title: '消息提示',
				    btnok: '确定',
				    btncl:'取消'
				    	
				  }).on(function (e){
					  $("#example").show();
				  });
	    		return false;
		    } else {
		    	if(lxcs < 0 ){
		    		
		    		$("#example").hide();
		    		Modal.alert(
					  {
					    msg: '请填入大于或等于零是数字',
					    title: '消息提示',
					    btnok: '确定',
					    btncl:'取消'
					    	
					  }).on(function (e){
						  $("#example").show();
					  });
		    		return false;
		    	}
		    }
		 $("#hfexample1").val($("#ext-comp-1002").val())
	
		 var sjgdlx = $("#hfexample1").val(DepartGdlxbox.getValue());
			$("#uploadForm").form("submit",{ 
		    	url:"updateZhbj.do",
		        success:function(data){ 
		        	
		        	searchWord();
					$('#example').modal ("hide");
		        	$(".modal-backdrop").remove();
		        	
		        } 
			
		    });
			
		});
}

function deleteZh(obj){
	
	Modal.confirm({
		msg: "确认删除？"
		}).on( function (e) {
	if(e){
		$.ajax({
			type : "post",
			url : "deleteZhbj.do",
			timeout : 1321231232131213123,
			data : {
				"squ" :obj,
			},
			success : function(data) {
			
				var da = eval("(" + data + ")");
			
				var sta = da.staus;	
				
				if(sta=="bjExit"){
					Modal.alert(
							  {
							    msg: '请先删除组合部件下的部件',
							    title: '消息提示',
							    btnok: '确定',
							    btncl:'取消'
							    	
							  });
				}else  if(sta=="zjExit"){
						Modal.alert(
								  {
								    msg: '该组合部件已被支架使用',
								    title: '消息提示',
								    btnok: '确定',
								    btncl:'取消'
								    	
								  });
					
				}
				searchWord();
				
			},
			
			error : function() {
				Modal.alert(
				  {
				    msg: '删除分类信息出错',
				    title: '消息提示',
				    btnok: '确定',
				    btncl:'取消'
				    	
				  });
			}
		});
		 }
	});
}




function dowloadMB(){

	location.href="dowloadStentsMB.do";
}


function addBatchStents(){
	$('#exampl3').modal();
	$("#btn_file_ok").unbind();
	$("#btn_file_ok").click(function(){
		$("#uploadForm3").form("submit",{ 
	    	url:"addBatchStents.do",
	        success:function(data){ 
	        	$("#input_file").val(data);	
	        	$('#exampl3').hide();
	        	$(".modal-backdrop").remove();
	        	//loadMenu(); 
	        }  
	    });
	});
	
}

//数据库类型
var DepartAzfsbox;
function selectAzfs(){
	$.ajax({
		url:'queryAzlxMenu.do',
		type:'post',
		dataType:'json',
		//async : false,
		data:{
			
		},
		success:function(data){
			var list1=data.azlxmenu;
		
			var obj = "[";
			for ( var i = 0; i <list1.length; i++) {
				obj += "['" + list1[i].SQU + "','" + list1[i].MS + "']";
				if (data.length != i + 1) {
					obj += ",";
				}
				if (data.length == 1) {
					obj = "[['" + list1[i].SQU + "','" + list1[i].MS + "']";
				}
			}
			obj += "]";
			extSelectAzfs(obj);
			DepartAzfsbox = new Ext.form.MultiSelect( {  
				    		renderTo : Ext.get("azfs"),
				            width: 240, 
				            height:40,
				            editable: false,  
				            store: store,  
				            emptyText: '------请选择------',  
				            allowBlank: false, 
				            blankText: '请选择', 
				            displayField: 'bmmc',  
				            valueField: 'bmlb',
				            mode:'local',  
				            triggerAction: 'all',  
				            selectOnFocus: true,  
				            listWidth: 217
				    });
		}
	});
}

var Dic ;
var reader;
var store ;
function extSelectAzfs(obj){
	var proxy = new Ext.data.MemoryProxy(eval("(" + obj + ")"));
	Dic = Ext.data.Record.create([ {
		name : "bmlb",
		type : "string",
		mapping : 0
	}, {
		name : "bmmc",
		type : "string",
		mapping : 1
	} ]);
	reader = new Ext.data.ArrayReader({}, [ {
		name : "bmlb",
		type : "string",
		mapping : 0
	}, {
		name : "bmmc",
		type : "string",
		mapping : 1
	} ]);
	store = new Ext.data.Store({
		proxy : proxy,
		reader : reader,
		autoLoad : true
	});
	
}


//管道类型
var gdsqu="";
var gdxh="";
var DepartGdlxbox;
function selectGdlx(){
	$.ajax({
		url:'queryGdlxMenu.do',
		type:'post',
		dataType:'json',
		//async : false,
		data:{
			pid:1
		},
		success:function(data){
			var list1=data.gdlxmenu;
		
			var obj = "[";
		/*	for ( var i = 0; i <list1.length; i++) {
				obj += "['" + list1[i].SQU +"@"+list1[i].XH+ "','" + list1[i].XH + "']";
				if (data.length != i + 1) {
					obj += ",";
				}
				if (data.length == 1) {
					obj = "[['" + list1[i].SQU +"@"+list1[i].XH+ "','" + list1[i].XH + "']";
				}
			}*/
			for ( var i = 0; i <list1.length; i++) {
				obj += "['" + list1[i].SQU + "','" + list1[i].XH + "']";
				if (data.length != i + 1) {
					obj += ",";
				}
				if (data.length == 1) {
					obj = "[['" + list1[i].SQU + "','" + list1[i].XH + "']";
				}
			}
			obj += "]";
			extSelectGdlx(obj);
		
			DepartGdlxbox = new Ext.form.MultiSelect( { 
							//renderTo : Ext.get("DQdictype"),
				    		/*renderTo : Ext.get("gdlx"),
				
							width: 240, 
				            height:40,
				            listWidth: 217,
				            editable:false ,  
				            store: store,  
				            emptyText: '------请选择------',  
				            allowBlank: false, 
				            blankText: '请选择', 
				            displayField: 'bmmc',  
				            valueField: 'bmlb',
				            mode:'local',  
				            triggerAction: 'all',  
				            selectOnFocus: true,  
				            anyMatch: true ,*/
				renderTo : Ext.get("gdlx"),
				width: 240, 
				height:40,
				editable: false,  
				store: store,  
				emptyText: '------请选择------',  
				allowBlank: false, 
				blankText: '请选择', 
				displayField: 'bmmc',  
				valueField: 'bmlb',
				mode:'local',  
				triggerAction: 'all',  
				selectOnFocus: true,  
				listWidth: 217
				           /* listeners : {
				            	'beforequery' : function(e) {	
				            		
									var combo = e.combo;
									if (!e.forceAll) {
										var value = e.query;
										combo.store.filterBy(function(record, id) {
											var val = record.get(combo.valueField);
										
											var text = record.get(combo.displayField);
										
											return (val.split('@')[1]).indexOf(value
													.toLowerCase()) != -1
													|| text.indexOf(value) != -1;
										});
										combo.expand();
										return fals
							
									}
				            	}
								
							}*/
				    });
		}
	});
}
//管道类型
/*var gdsqu="";
var gdxh="";
var DepartGdlxbox;
function selectGdlx(){
	$.ajax({
		url:'queryGdlxMenu.do',
		type:'post',
		dataType:'json',
		//async : false,
		data:{
			
		},
		success:function(data){
			var list1=data.gdlxmenu;
		
			var obj = "[";
			for ( var i = 0; i <list1.length; i++) {
				obj += "['" + list1[i].SQU +"@"+list1[i].XH+ "','" + list1[i].XH + "']";
				if (data.length != i + 1) {
					obj += ",";
				}
				if (data.length == 1) {
					obj = "[['" + list1[i].SQU +"@"+list1[i].XH+ "','" + list1[i].XH + "']";
				}
			}
			obj += "]";
			extSelectGdlx(obj);
		
			DepartGdlxbox = new Ext.form.MultiSelect( { 
							//renderTo : Ext.get("DQdictype"),
				    		renderTo : Ext.get("gdlx"),
				    		triggerAction : "all",
							store : store,
							width : 230,
							maxHeight : 120,
							listWidth : 220,
							shadow : false,
							displayField : "bmmc",
							valueField : "bmlb",
							mode : "local",
							grow : true,
							emptyText : "-----请选择-----",
							//MultiSelect
							width: 240, 
				            height:40,
				            listWidth: 217,
				            editable:true ,  
				            store: store,  
				            emptyText: '------请选择------',  
				            allowBlank: false, 
				            blankText: '请选择', 
				            displayField: 'bmmc',  
				            valueField: 'bmlb',
				            mode:'local',  
				            triggerAction: 'all',  
				            selectOnFocus: true,  
				            anyMatch: true ,
				            listeners : {
				            	'beforequery' : function(e) {	
				            		
									var combo = e.combo;
									
									if (!e.forceAll) {
										var value = e.query;
										combo.store.filterBy(function(record, id) {
											var val = record.get(combo.valueField);
											
											var text = record.get(combo.displayField);
											
											return (val.split('@')[1]).indexOf(value
													.toLowerCase()) != -1
													|| text.indexOf(value) != -1;
										});
										combo.expand();
										return false;
									 if(!e.forceAll){  
							                var value = e.query;  
							                combo.store.filterBy(function(record,id){
							                var val = record.get(combo.valueField);
							                var text = record.get(combo.displayField);  
							                alert(val)
							              //  return (text.indexOf(value)!=-1);  
							                return (val.split('@')[1]).indexOf(value
													.toLowerCase()) != -1
													|| text.indexOf(value) != -1;
							                });  
							                combo.expand();  
							                return false;   
									}
								}
								
							}
				    });
		}
	});
}*/
var Dic ;
var reader;
var store ;
function extSelectGdlx(obj){
	var proxy = new Ext.data.MemoryProxy(eval("(" + obj + ")"));
	Dic = Ext.data.Record.create([ {
		name : "bmlb",
		type : "string",
		mapping : 0
	}, {
		name : "bmmc",
		type : "string",
		mapping : 1
	} ]);
	reader = new Ext.data.ArrayReader({}, [ {
		name : "bmlb",
		type : "string",
		mapping : 0
	}, {
		name : "bmmc",
		type : "string",
		mapping : 1
	} ]);
	store = new Ext.data.Store({
		proxy : proxy,
		reader : reader,
		autoLoad : true,
		
	});
	
		
}
function pageDiv(records,total,currentPage){
	var pageTotal=total;// 总共多少页
	var pageCurrentPage=currentPage;//当前页
	var pageRecords=records;//总记录数
	var countt="";
	var outstr="";
	pageTotal=parseInt(pageRecords/pageRowNum);
	if(pageRecords%pageRowNum>0){
		pageTotal=pageTotal+1;
	}
        if(pageTotal<=pageShowPage){
                for (var count=1;count<=pageTotal;count++){
                        if (count<10) {countt ="0"+count+"";}else{countt =""+count+"";}
                        if(count!=pageCurrentPage){
                             outstr = outstr + "<li tag='"+count+"' class='pageCk'><a href='javascript:void(0);'>"+countt+"</a></li>";
                        }else{
                        	 outstr = outstr + "<li class='active'><a href='javascript:void(0);'>"+countt+"</a></li>";
                        }
                }
        }
        if(pageTotal>pageShowPage){
                if(parseInt((pageCurrentPage-1)/pageShowPage) == 0){
                        outstr = outstr + "<li><a><<</a></li> ";
                        for (var count=1;count<=pageShowPage;count++){
                                if (count<10) {countt ="0"+count+"";}else{countt =""+count+"";}
                                if(count!=pageCurrentPage){
                                	outstr = outstr + "<li tag='"+count+"' class='pageCk'><a  href='javascript:void(0);'>"+countt+"</a></li>";
                                	
                                }else{
                                	 outstr = outstr + "<li class='active'><a href='javascript:void(0);'>"+countt+"</a></li>";
                                }
                        }
                        outstr = outstr+"<li tag='"+count+"' class='pagePk'><a href='javascript:void(0)'>>></a></li>";
                }else if(parseInt((pageCurrentPage-1)/pageShowPage) == parseInt(pageTotal/pageShowPage)){
                        outstr = outstr + "<li tag='"+(parseInt((pageCurrentPage-1)/pageShowPage)*pageShowPage)+"' class='pagePk'><a href='javascript:void(0)'><<</a></li> ";
                        for (var count=parseInt(pageTotal/pageShowPage)*pageShowPage+1;count<=pageTotal;count++){
                                if (count<10) {countt ="0"+count+"";}else{countt =""+count+"";}
                                if(count!=pageCurrentPage){
                                	outstr = outstr + "<li  tag='"+count+"' class='pageCk'><a href='javascript:void(0);'>"+countt+"</a></li>";
                                }else{
                                	 outstr = outstr + "<li class='active'><a href='javascript:void(0);'>"+countt+"</a></li>";
                                }
                        }
                        outstr = outstr + "<li><a href='javascript:void(0)'>>></a></li>";
                }else{
                        outstr = outstr + "<li tag='"+(parseInt((pageCurrentPage-1)/pageShowPage)*pageShowPage)+"' class='pagePk'><a href='javascript:void(0)'><<</a></li> ";
                        for (var count=parseInt((pageCurrentPage-1)/pageShowPage)*pageShowPage+1;count<=parseInt((pageCurrentPage-1)/pageShowPage)*pageShowPage+pageShowPage;count++){
                                if (count<10) {countt ="0"+count+"";}else{countt =""+count+"";}
                                if(count!=pageCurrentPage){
                                	outstr = outstr + "<li tag='"+count+"' class='pageCk'><a href='javascript:void(0);'>"+countt+"</a></li>";
                                }else{
                                	
                                        outstr = outstr + "<li class='active'><a href='javascript:void(0);'>"+countt+"</a></li>";
                                }
                        }
                        outstr = outstr + "<li  tag='"+count+"' class='pageCk'><a href='javascript:void(0);'>>></a></li>";
                }
    }    
        //共"+pageTotal+"页|"+pageRecords+"条数据|第"+pageCurrentPage+"页 每页" + pageRowNum +"个　
        $("#pageDiv").html("<div class='pagination pagination-centered'  align='right'><ul>" + outstr + "<a href='javascript:void(0);'>共"+pageTotal+"页/"+pageRecords+"条数据</a></li></ul></div>");
        
    if(pagertype==1){
	  outstr = "";
	    $(".pageCk").unbind();
	    $(".pageCk").bind('click',function(){
	    	gridload($(this).attr("tag"));
	    	$("#pd").val($("#pd").val())
		});
	    $(".pagePk").unbind();
	    $(".pagePk").bind('click',function(){
	    	gridload($(this).attr("tag"));
	    	$("#pd").val($("#pd").val())
		});
  }else if(pagertype==2){
	  outstr = "";
	    $(".pageCk").unbind();
	    $(".pageCk").bind('click',function(){
	    	queryZhbjXq1(_code,$(this).attr("tag"));
	    	//gridload($(this).attr("tag"));
	    	$("#pd").val($("#pd").val())
		});
	    $(".pagePk").unbind();
	    $(".pagePk").bind('click',function(){
	    	queryZhbjXq1(_code,$(this).attr("tag"));
	    	//gridload($(this).attr("tag"));
	    	$("#pd").val($("#pd").val())
		});
  }
    
}	


/*<script type="text/javascript">
$(document).ready(function() {
    $('#example-enableCollapsibleOptGroups-enableClickableOptGroups-enableFiltering-includeSelectAllOption').multiselect({
        enableClickableOptGroups: true,
        enableCollapsibleOptGroups: true,
        enableFiltering: true,
        includeSelectAllOption: true
    });
});
</script>*/
