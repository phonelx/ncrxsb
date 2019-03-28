/*$(function() {
	gridload("-1")
	});	
*/
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


var i=0;
function parentload(obj){
/*	var ssmc = $(obj).attr("ssmc")*/

	var squ = $(obj).attr("id");
	var zbm = $(obj).attr("title");

	$("#typename").val($(obj).attr("zhdm"));
	$("#jdlx").html(zbm);
	$("#fla").val( $(obj).attr("flag"))
	$(".divider2").html(zbm);
	
	$("#navg").text($(obj).attr("title"));
	$("#pd").val($(obj).attr("id"));
	$("#fid").val($(obj).attr("id"));	
	loadMenu(obj);
	searchWord();
	if($("#az").next().hasClass('on')){
    	$("#az").next().slideUp(500).removeClass('on');  
    	$("#az").prev().text('[+]');  
    	
    }
	if($("#gd").next().hasClass('on')){
    	$("#gd").next().slideUp(500).removeClass('on');  
    	$("#gd").prev().text('[+]');  
    	
    }
}

function parentloadGd(obj){
	$("#fla").val( $(obj).attr("flag"))
	$("#gdpd").val( $(obj).attr("squ"))
	
	$("#gdtitle").val( $(obj).attr("titlezh"))
	
	loadGdlxMenu(obj);
	searchWord();
	
}

function parentloadAz(obj){
	$("#fla").val( $(obj).attr("flag"))
	if($(obj).next().hasClass('on')){
    	$(obj).next().slideUp(500).removeClass('on');  
    	$(obj).prev().text('[+]');  
    	var ssmc =  $(obj).attr("ssmc");   	
    	if(ssmc=="安装方式"){			
    		$(".divider1").html(ssmc);
    		$(".divider2").html("");
    		$(".divider3").html("");
    		$(".divider4").html("");		
    	}
    	searchWord();
    }else{  
    	$(obj).next().slideDown(500).addClass('on');  
    	$(obj).prev().text('[-]'); 
    	var ssmc = "";
    	
    	$("#fla").val( $(obj).attr("flag"))
    	if(ssmc=="安装方式"){			
    		$(".divider1").html(ssmc);
    		$(".divider2").html("");
    		$(".divider3").html("");
    		$(".divider4").html("");		
    	}
    	loadAzlxMenu(obj);
    	searchWord();
    } 
	
	if($("#gd").next().hasClass('on')){
    	$("#gd").next().slideUp(500).removeClass('on');  
    	$("#gd").prev().text('[+]');  
    	
    } 
	
	
}

/*function parentZjLxload(obj){
	var squ = $(obj).attr("id");

	$("#fla").val( $(obj).attr("flag"))


	$("#navg").text($(obj).attr("title"));
	$("#pd").val($(obj).attr("id"));
	$("#fid").val($(obj).attr("id"));	
	loadZjLxMenu(obj);
	searchWord();
}

function parentDzload(obj){
	var squ = $(obj).attr("id");

	$("#fla").val( $(obj).attr("flag"))


	$("#navg").text($(obj).attr("title"));
	$("#pd").val($(obj).attr("id"));
	$("#fid").val($(obj).attr("id"));	
	loadDzMenu(obj);
	searchWord();
}

function parentSsXtload(obj){
	var squ = $(obj).attr("id");

	$("#fla").val( $(obj).attr("flag"))


	$("#navg").text($(obj).attr("title"));
	$("#pd").val($(obj).attr("id"));
	$("#fid").val($(obj).attr("id"));	
	loadSsXtMenu(obj);
	searchWord();
}*/


//重新加载左部菜单列表
function loadMenu(obj){
	
	var squ=$(obj).attr("id");
	var flag = $(obj).attr("flag");
	var isEnd = $(obj).attr("isEnd");
	$("#ifEnd").val(isEnd);
	if(flag==1){
		$.ajax({
			type : 'post',
			url : 'getPatrsMenu.do',
			timeout : 1321231232131213123,
			dataType:"json",
			async : false, //是否发送异步请求	
			data:{
				squ:squ,
				
			},
			success : function(data) {
				var html = "";
				var list = data.list				
				if(data.list[0]!=undefined){
					
					for(var i = 0;i < data.list.length; i++){
						
							html+="<li class='nav-header hidden-tablet border-bottom' style='display:block; padding-left:15px;'>";
							html+="<a href='javascript:void(0);'  style='color:#3f9fd9;' class='ajax-link'  zbm='"+data.list[i].DMLB+"' zhdm='"+data.list[i].ZHDM+"' flag='1' id='"+data.list[i].SQU+"'  title ='"+data.list[i].LBMC+"' isEnd = "+data.list[i].JD+" onclick='parentload(this);'>";
							html+=" <i class='icon-darkgray '></i>&nbsp; "+data.list[i].LBMC+"</a>";
							/*html += "<i class='" + data.list[i].ICON + "'></i> " +data.list[i].LBMC+ "</a>";*/
							if(data.list[i].JD==0){
								html+=" <ul class='topnav' >";
								html+=" </ul>";
							}
							html+=" </li>";
					}
					/*if(squ==0){
						$(obj).parent().parent().find("ul").html(html);
						$(obj).parent().parent().find("ul").accordion({
							accordion:false,
							speed: 300,
							closedSign: '[+]',
							openedSign: '[-]'
						});
					}else{*/
					$(obj).parent().find("ul").html(html);
					$(obj).next("ul").accordion({
							accordion:false,
							speed: 300,
							closedSign: '[+]',
							openedSign: '[-]'
						});
					//}
				}
			},
			error : function() {
				Modal.alert(
				  {
				    msg: '资源分离列表加载出错',
				    title: '消息提示',
				    btnok: '确定',
				    btncl:'取消'
				    	
				  });
			}
		});
		$(obj).attr("flag","0");
	}else{
		$(obj).attr("flag","1");
	}

}


function loadGdlxMenu(obj){

	/*$("#span").text("[-]")*/
		$.ajax({
			type : 'post',
			url : 'queryGdlxMenu.do',
			timeout : 1321231232131213123,
			dataType:"json",
			//async : false, //是否发送异步请求	
			data:{
				pid:$(obj).attr("squ")
			},
			success : function(data) {
			
				var html = "";
				var list = data.gdlxmenu
		
				if(list[0]!=undefined){
					
					for(var i = 0;i < list.length; i++){
						/*
							html+="<li class='nav-header hidden-tablet border-bottom' style='display:block; padding-left:15px;'>";
							html+="<a href='javascript:void(0);'  style='color:#3f9fd9;' class='ajax-link'  zbm='"+data.list[i].DMLB+"' zhdm='"+data.list[i].ZHDM+"' flag='1' id='"+data.list[i].SQU+"'  title ='"+data.list[i].LBMC+"' isEnd = "+data.list[i].JD+" onclick='parentload(this);'>";
							html+=" <i class='icon-darkgray '></i>&nbsp; "+data.list[i].LBMC+"</a>";*/
						
							html+=" <li class='nav-header hidden-tablet border-bottom' style='display:block; margin-left:15px;'>";			
							html+=" <a href='javascript:void(0);'  style='color:#3f9fd9;' class='ajax-link'  flag='3'  id='"+list[i].SQU+"' squ='"+list[i].SQU+"' titlezh ='"+list[i].ZH+"' title ='"+list[i].XH+"' isend='"+list[i].FLAGE+"' onclick='parentloadGd(this)' '>";
							html+=" <i class='icon-darkgray '></i>&nbsp;"+list[i].XH+"</a>";									
							
							/*html += "<i class='" + data.list[i].ICON + "'></i> " +data.list[i].LBMC+ "</a>";*/
							if(list[i].FLAGE==0){
								html+=" <ul class='topnav' >";
								html+=" </ul>";
							}
							html+=" </li>";
					}
					
					$(obj).parent().find("ul").html(html);
					$(obj).next("ul").accordion({
							accordion:false,
							speed: 300,
							closedSign: '[+]',
							openedSign: '[-]'
						});
					//}
				}
					/*for(var i = 0,len=list.length;i < len; i++){				
							html+=" <li class='border-bottom' style='display:block; margin-left:15px;'>";			
							html+=" <div class='figcaption'><a href='javascript:void(0);'  style='color:#3f9fd9;' class='ajax-link'  flag='3'  id='"+list[i].SQU+"' squ='"+list[i].SQU+"' title ='"+list[i].XH+"' isend='"+list[i].FLAGE+"' onclick='queryZhbjXq(this)' '>";
							html+=" <i class='icon-darkgray '></i>&nbsp;"+list[i].XH+"</a></div>";									
							html+=" </li>";
					
				}
				
				$(obj).parent().find("ul").html(html);
				$(obj).next("ul").accordion({
						accordion:false,
						speed: 300,
						closedSign: '[+]',
						openedSign: '[-]'
					});*/

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

function loadAzlxMenu(obj){
	/*$("#span").text("[-]")*/
		$.ajax({
			type : 'post',
			url : 'queryAzlxMenu.do',
			timeout : 1321231232131213123,
			dataType:"json",
		
			data:{
				
			},
			success : function(data) {
				
				var html = "";
				var list = data.azlxmenu			
					for(var i = 0,len = list.length;i < len; i++){				
							html+=" <li class='border-bottom' style='display:block; margin-left:15px;'>";			
							html+="<div class='figcaption'> <a href='javascript:void(0);'  style='color:#3f9fd9;' class='ajax-link'  flag='1'  id='"+list[i].SQU+"' title ='"+list[i].MS+"' onclick='queryZhbjXq(this)' '>";
							html+=" <i class='icon-darkgray '></i>&nbsp;"+list[i].MS+"</a></div>";									
							html+=" </li>";
					
				}
				
				$(obj).parent().find("ul").html(html);
				$(obj).next("ul").accordion({
						accordion:false,
						speed: 300,
						closedSign: '[+]',
						openedSign: '[-]'
					});
		
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
//加载表格
var grid ;
var pageNo =1;

/*****关键字检索****/
var query="";
function searchWord(){
	var word = $("#input_search").val();
	
	if (word=="关键字搜索") {
		query ="1";
	}else{
		query=$("#input_search").val();
	} 
	
	var fla = $("#fla").val();
	var gdpd = $("#gdpd").val();
	var gdtitle=$("#gdtitle").val();
	
	if(fla == 1 || fla == 0){
		gridload($("#pd").val(),pager);
	}else if(fla == 3){
		
		if(gdpd!="000000"){
			if(gdtitle=="GXLX"){
				gridloadGd();
			}else if(gdtitle=="GXGG"){
				
				gridloadGg();
			}else if(gdtitle=="GXCZ"){
				gridloadCz();
			}
		}
		
	}else if(fla == 4){
		gridloadAz()
	}else if(fla == 5){
		//gridloadAz()
	}else if(fla == 9){
		gridload("-1")
	}
	
}  

function gridload(obj,pager){
	$("#uiGrid-uigrid").css("display","block");
	$("#pageDiv").css("display","block");
	$("#uigrld").css("display","none");
	var html= "";
	$.ajax({
			url:'getChildMenu.do',
			type:'post',
			data:{
				squ:obj,
				searchKey:query,
				rows:pageRowNum,
				page:pager,
			},
			success:function(data){
				var dat = eval('(' + data + ')');
				records=dat.total;
				currentPage=dat.pageNumber;
				total = dat.pageCnt;
				pageRowNum=dat.pageSize;
				var list1 = dat.rows;
			
				if(list1.length!=0){
					
					html +="<tr class='tr'>";  
					html +=" <td class='td4 td5 td6'>名称</td>";
					 if(list1[0].ZHDM=="LBXS"){						
						html +=" <td class='td4 td5 '>类别系数</td>";	
					}else if(list1[0].ZHDM=="GNXS"){					
						html +=" <td class='td4 td5 '>功能系数</td>";	
					}else if(list1[0].ZHDM=="ZJ"){					
						html +=" <td class='td4 td5 '>最大间距</td>";
					}else if(list1[0].ZHDM=="JSD"){					
						html +=" <td class='td4 td5 '>系数最大值</td>";	
					}else if(list1[0].ZHDM!="LBXS"&&list1[0].ZHDM!="GNXS"&&list1[0].ZHDM!="ZJ"){						
						html +=" <td class='td4 td5 '>识别码</td>";	
					} 
					html +=" <td class='td4 td5 '>操作</td>";	
					html +="</tr>"; 
				}
				
					for(var i = 0;i<list1.length;i++){
					
						html +="<tr class='tr'>";  		
						html +=" <td class='td4 td5 td6'>"+list1[i].LBMC+"</td>";
						if(list1[0].ZHDM=="LBXS"||list1[0].ZHDM=="GNXS"||list1[0].ZHDM=="ZJ"||list1[0].ZHDM=="JSD"){
							html +=" <td class='td4 td5 '>"+list1[i].XSCS+"</td>";  
						}else if(list1[0].ZHDM!="LBXS"&&list1[0].ZHDM!="GNXS"&&list1[0].ZHDM!="ZJ"&&list1[0].ZHDM!="JSD"){
							html +=" <td class='td4 td5 '>"+list1[i].DMLB+"</td>";	
						}
														
						html +=" <td class='td4 td5'><a onclick='showEditEvent("+JSON.stringify(list1[i])+")' title='编辑' href='javascript:void(0);'><i class='icon-edit icon-black'></i></a>&nbsp";
						html +=" <a onclick='showDeleteEvent("+JSON.stringify(list1[i])+")' title='删除' href='javascript:void(0)'><i class='icon-trash icon-black'></i></a></td>";			
						html +="</tr>";   
					}
				
				
				
				/*	$("#box_body").html("");*/
					$("#box_body").html(html);
					pageDiv(records,total,currentPage);
			},
			error:function(){
				alert("编辑出错！");
			}
		});
	/*$("#uigrld").uiGrid({
		url : "getChildMenu.do",
		rowNum : 10,//每页显示记录数
		columns : [  {
			field : 'LBMC',
			title : '名称',
			width : 220
		}, {
			field : 'DMLB',
			title : '识别码',
			width : 200
		}],
		divId : "#uigrld",
		showPage : 5,//显示
		showEdit : true,
		showDelete : true,
		
		showEditEvent : showEditEvent,
		showDeleteEvent : showDeleteEvent,
		data : {
			squ:obj,
			searchKey:query,
		},
		jsonPager : {
			root : "datas",// 当前页数据
			records : "rowCount",//总记录数
			currentPage : pageNo,//当前访问页
			total : "pageCount"//总页数
		}
	});	*/
	 
}

function gridloadGd(){

	$("#uiGrid-uigrid").css("display","none");
	$("#pageDiv").css("display","none");
	$("#uigrld").css("display","block");
	$("#uigrld").uiGrid({
		url : "queryGdlx.do",
		rowNum : 10,//每页显示记录数
		columns : [  {
			field : 'ZH',
			title : '理论重量(KN/M)',
			width : 100
		}, {
			field : 'XH',
			title : '管道型号',
			width : 240
		}, {
			field : 'LBDM',
			title : '类别代码',
			width : 90
		}, {
			field : 'LRR',
			title : '录入人',
			width : 90
		}, {
			field : 'LRDATE',
			title : '录入时间',
			width : 200
		}],
		divId : "#uigrld",
		showPage : 5,//显示
		showEdit : true,
		showDelete : true,
		
		showEditEvent : showEditEventGd,
		showDeleteEvent : showDeleteEventGd,
		data : {			
			searchKey:query,
			pid:$("#gdpd").val()
		},
		jsonPager : {
			root : "datas",// 当前页数据
			records : "rowCount",//总记录数
			currentPage : pageNo,//当前访问页
			total : "pageCount"//总页数
		}
	});	
	 
}
function gridloadGg(){

	$("#uiGrid-uigrid").css("display","none");
	$("#pageDiv").css("display","none");
	$("#uigrld").css("display","block");
	$("#uigrld").uiGrid({
		url : "queryGdlx.do",
		rowNum : 10,//每页显示记录数
		columns : [   {
			field : 'XH',
			title : '管线规格',
			width : 240
		}, {
			field : 'LRR',
			title : '录入人',
			width : 90
		}, {
			field : 'LRDATE',
			title : '录入时间',
			width : 200
		}],
		divId : "#uigrld",
		showPage : 5,//显示
		showEdit : true,
		showDelete : true,
		
		showEditEvent : showEditEventGd,
		showDeleteEvent : showDeleteEventGd,
		data : {			
			searchKey:query,
			pid:$("#gdpd").val()
		},
		jsonPager : {
			root : "datas",// 当前页数据
			records : "rowCount",//总记录数
			currentPage : pageNo,//当前访问页
			total : "pageCount"//总页数
		}
	});	
	 
}
function gridloadCz(){

	$("#uiGrid-uigrid").css("display","none");
	$("#pageDiv").css("display","none");
	$("#uigrld").css("display","block");
	$("#uigrld").uiGrid({
		url : "queryGdlx.do",
		rowNum : 10,//每页显示记录数
		columns : [   {
			field : 'XH',
			title : '管线材质',
			width : 240
		}, {
			field : 'LRR',
			title : '录入人',
			width : 90
		}, {
			field : 'LRDATE',
			title : '录入时间',
			width : 200
		}],
		divId : "#uigrld",
		showPage : 5,//显示
		showEdit : true,
		showDelete : true,
		
		showEditEvent : showEditEventGd,
		showDeleteEvent : showDeleteEventGd,
		data : {			
			searchKey:query,
			pid:$("#gdpd").val()
		},
		jsonPager : {
			root : "datas",// 当前页数据
			records : "rowCount",//总记录数
			currentPage : pageNo,//当前访问页
			total : "pageCount"//总页数
		}
	});	
	 
}
function gridloadAz(){
	$("#uiGrid-uigrid").css("display","none");
	$("#pageDiv").css("display","none");
	$("#uigrld").css("display","block");
	$("#uigrld").uiGrid({
		url : "queryAzlx.do",
		rowNum : 10,//每页显示记录数
		columns : [  {
			field : 'MS',
			title : '描述',
			width : 220
		}, {
			field : 'LBDM',
			title : '类别代码',
			width : 200
		}],
		divId : "#uigrld",
		showPage : 5,//显示
		showEdit : true,
		showDelete : true,
		
		showEditEvent : showEditEventAz,
		showDeleteEvent : showDeleteEventGd,
		data : {
			
			searchKey:query,
		},
		jsonPager : {
			root : "datas",// 当前页数据
			records : "rowCount",//总记录数
			currentPage : pageNo,//当前访问页
			total : "pageCount"//总页数
		}
	});	
	 
}
function addChildMenu(){
	
	     var fla = $("#fla").val();
	     var gdtitle=$("#gdtitle").val();	 
	 	var gdpd = $("#gdpd").val();
	     if(fla==1){
	    	 if($("#ifEnd").val()!=1){
	 			var cid = $("#pd").val();	
	 			//alert(cid)
	 			$("#LBMC").val("");
	 			$("#DMLB").val("");	
	 			$("#XSCS").val("");	
	 			$("#rsqu").val(cid);
	 			if(cid !=""){
		 			$("#secondTitle2").html("新增");
		 			
		 			
		 			var type = $("#typename").val()
		 			
		 			if(type == "LBGNXS"){
		 				
		 				 htmlobj=$.ajax({url:"download/lbxs.txt",async:false});
		 			}else if(type == "LBXS"){
		 				
		 				 htmlobj=$.ajax({url:"download/gnxs.txt",async:false});
		 			}else if(type=="GDLB"){
		 				
		 				 htmlobj=$.ajax({url:"download/gdlb.txt",async:false});
		 			}else if(type=="ZDJJ"){
		 				
		 				 htmlobj=$.ajax({url:"download/zdjj.txt",async:false});
		 			}else if(type=="DZYX"){
		 				
		 				 htmlobj=$.ajax({url:"download/dzyx.txt",async:false});
		 			}else if(type=="DZLX"){
		 				
		 				 htmlobj=$.ajax({url:"download/sfld.txt",async:false});
		 			}else if(type=="SFLD"){				
		 				 htmlobj=$.ajax({url:"download/jsd.txt",async:false});
		 			}else if(type!="LBXS"&&type!="GNXS"&&type!="GDLB"&&type!="ZDJJ"&&type!="DZYX"
		 				&&type!="DZLX"&&type!="SFLD"){
		 				htmlobj=$.ajax({url:"download/cpxl.txt",async:false});
		 			}
		 					
		 			$(".container1").html(htmlobj.responseText);
		 			
		 			$("#example2").modal();
		 			$('#sortBtn').unbind();
		 			$('#sortBtn').click(function(){
		 				   
		 				$.ajax({
		 					url:'addChildMenu.do',
		 					type:'post',
		 					data:{
		 						//'wzz.SQU':row.SQU,
		 						'wzz.DMLB':$('#DMLB').val(),
		 						'wzz.LBMC':$('#LBMC').val(),
		 						'wzz.ZHDM':$('#ZHDM').val(),
		 						'wzz.PARENTSQU':cid,
		 						'wzz.JD':$('#JD').val(),
		 						'wzz.XSCS':$('#XSCS').val()
		 					},
		 					success:function(data){
		 							$('#closeWin').click();
		 							searchWord();  
		 						
		 					},
		 					error:function(){
		 						alert("编辑出错！");
		 					}
		 				});
		 			});
	 			}
	    	 
	 		}else{
	 			Modal.alert(
	 			  {
	 			    msg: '当前分类已结束',
	 			    title: '消息提示',
	 			    btnok: '确定',
	 			    btncl:'取消'	 			    	
	 			  });
	 			return false;
	 		}
	 		
	     }else if(fla == 3){//添加管道类型
	    	 	$('#XH').val(""),
				$('#ZH').val(""),
				$('#LBDM').val(""),
				$("#CX").val("");
				$("#ZX").val("");
				if(gdtitle=="GXLX"){
					$("#example4").modal();
		 			$('#sortBtnGd').unbind();
		 			$('#sortBtnGd').click(function(){
		 				$.ajax({
		 					url:'addGdlx.do',
		 					type:'post',
		 					data:{
		 						//'wzz.SQU':row.SQU,
		 						'gdlx.XH':$('#XH').val(),
		 						'gdlx.ZH':$('#ZH').val(),
		 						'gdlx.LBDM':$('#LBDM').val(),
		 						'gdlx.PARENTID':gdpd,
		 						
		 					},
		 					success:function(data){
		 						
		 							$('#closeWin').click(); 
		 							searchWord();
		 							$('#example4').modal("hide");
		 				        	$(".modal-backdrop").remove();
		 							
		 					},
		 					error:function(){
		 						alert("编辑出错！");
		 					}
		 				});
		 				
		 			});
				}else if(gdtitle=="GXGG"){
					$("#exampleGg").modal();
		 			$('#sortBtnGg').unbind();
		 			$('#sortBtnGg').click(function(){
		 				$.ajax({
		 					url:'addGdlx.do',
		 					type:'post',
		 					data:{
		 						//'wzz.SQU':row.SQU,
		 						'gdlx.XH':$('#XHGg').val(),		 				
		 						'gdlx.PARENTID':gdpd,
		 						
		 					},
		 					success:function(data){
		 						
		 							$('#closeWin').click(); 
		 							searchWord();
		 							$('#exampleGg').modal("hide");
		 				        	$(".modal-backdrop").remove();
		 							
		 					},
		 					error:function(){
		 						alert("编辑出错！");
		 					}
		 				});
		 				
		 			});
				}else if(gdtitle=="GXCZ"){
					$("#exampleCz").modal();
		 			$('#sortBtnCz').unbind();
		 			$('#sortBtnCz').click(function(){
		 				$.ajax({
		 					url:'addGdlx.do',
		 					type:'post',
		 					data:{
		 						//'wzz.SQU':row.SQU,
		 						'gdlx.XH':$('#XHCz').val(),
		 						'gdlx.PARENTID':gdpd,
		 						
		 					},
		 					success:function(data){
		 						
		 							$('#closeWin').click(); 
		 							searchWord();
		 							$('#exampleCz').modal("hide");
		 				        	$(".modal-backdrop").remove();
		 							
		 					},
		 					error:function(){
		 						alert("编辑出错！");
		 					}
		 				});
		 				
		 			});
				}
	 			
	     }else if(fla == 4){//添加安装方式
	    	 $('#MS').val(""),	 						
	    	 $('#LBDM1').val(""),
	    	 $("#example5").modal();
	 			$('#sortBtnAz').unbind();
	 			$('#sortBtnAz').click(function(){
	 				// Output(row.SQU);//赋值
	 				//alert(cid)
	 				$.ajax({
	 					url:'addAzlx.do',
	 					type:'post',
	 					data:{
	 						//'wzz.SQU':row.SQU,
	 						'gdlx.MS':$('#MS').val(),	 						
	 						'gdlx.LBDM':$('#LBDM1').val(),
	 						
	 					},
	 					success:function(data){
	 							$('#closeWin').click();
	 							searchWord()
	 							$('#example5').modal("hide");
	 				        	$(".modal-backdrop").remove();
	 					},
	 					error:function(){
	 						alert("编辑出错！");
	 					}
	 				});
	 			});
	     }/*else if(fla == 9){
	    	
	    	 if($("#ifEnd").val()!=1){
		 			//var cid = $("#pd").val();	
		 			//alert(cid)
		 			$("#LBMC1").val("");
		 			$("#DMLB1").val("");		
		 			//$("#rsqu").val(cid);
		 			
			 			$("#secondTitle9").html("新增目录分类");
			 			$("#example9").modal();
			 			$('#sortZjLxBtn').unbind();
			 			$('#sortZjLxBtn').click(function(){
			 				// Output(row.SQU);//赋值
			 				//alert(cid)
			 				$.ajax({
			 					url:'addChildMenu.do',
			 					type:'post',
			 					data:{
			 						//'wzz.SQU':row.SQU,
			 						'wzz.DMLB':$('#DMLB1').val(),
			 						'wzz.LBMC':$('#LBMC1').val(),
			 						'wzz.PARENTSQU':-1,
			 						'wzz.JD':$('#JD1').val(),
			 						'wzz.SSMC':0
			 					},
			 					success:function(data){
			 							$('#closeWin').click();
			 							searchWord(); 
			 							$('#example9').modal("hide");
			 				        	$(".modal-backdrop").remove();
			 						
			 					},
			 					error:function(){
			 						alert("编辑出错！");
			 					}
			 				});
			 			});
		 			}
	    	
	     }*/
}
/**
* 新增
*/
function addSort(){
$("#secondForm").form("submit", {
	url : 'addChildMenu.do',
	success : function(data) {
		$("#closeTwo").click();
		searchWord();
	
	}
});
}

//编辑分类
function showEditEvent(row) {
	
	var type = $("#typename").val()
		
		if(type == "LBGNXS"){
			
			 htmlobj=$.ajax({url:"download/lbxs.txt",async:false});
		}else if(type == "LBXS"){
			
			 htmlobj=$.ajax({url:"download/gnxs.txt",async:false});
		}else if(type == "GDLB"){
			
			 htmlobj=$.ajax({url:"download/gdlb.txt",async:false});
		}else if(type == "ZDJJ"){
			 htmlobj=$.ajax({url:"download/zdjj.txt",async:false});
		}else if(type == "DZYX"){
			 htmlobj=$.ajax({url:"download/dzyx.txt",async:false});
		}else if(type=="DZLX"){				
			 htmlobj=$.ajax({url:"download/sfld.txt",async:false});
		}else if(type=="SFLD"){				
			 htmlobj=$.ajax({url:"download/jsd.txt",async:false});
		}else if(type!="LBXS"&&type!="GNXS"&&type!="GDLB"&&type!="ZDJJ"&&type!="DZYX"
			&&type!="DZLX"&&type!="SFLD"){
			htmlobj=$.ajax({url:"download/cpxl.txt",async:false});
		}
		$(".container1").html(htmlobj.responseText);
		$("#secondTitle").html("编辑");
	$("#example2").modal();
	$("#LBMC").val(row.LBMC);
	
	$("#DMLB").val(row.DMLB);
	$("#XSCS").val(row.XSCS);
	 var objjd = document.getElementById("JD");	
	 for(var i=0;i<objjd.length;i++){
		// alert(i+"="+objjd[i].value+"="+row.JD)
		 if(objjd[i].value==row.JD){
			 objjd[i].selected = true;
		 }
		 
	 }
   /*
	 var objssmc = document.getElementById("SSMC");	
	 for(var i=0;i<objssmc.length;i++){
		 if(objssmc[i].value==row.SSMC){
			 objssmc[i].selected = true;
		 }
		 
	 }*/

	$('#sortBtn').unbind();
	$('#sortBtn').click(function(){
		
		$.ajax({
			url:'editChildMenu.do',
			type:'post',
			data:{
				'wzz.SQU':row.SQU,
				'wzz.DMLB':$('#DMLB').val(),
				'wzz.LBMC':$('#LBMC').val(),
				'wzz.ZHDM':$('#ZHDM').val(),	
				'wzz.JD':$('#JD').val(),
				'wzz.XSCS':$('#XSCS').val()
			},		
			success:function(data){
				
					$('#closeWin').click();
					searchWord();  
				
			},
			error:function(){
				alert("编辑出错！");
			}
		});
	});
}


function showEditEventGd(row){
	   var gdtitle=$("#gdtitle").val();	 
	 	var gdpd = $("#gdpd").val();
	
	 	if(gdtitle=="GXLX"){
	 		$("#secondTitle4").html("编辑管线类型");
	 		$("#example4").modal();
	 		$("#XH").val(row.XH);
	 		$("#ZH").val(row.ZH);
	 		$("#LBDM").val(row.LBDM);
	 		$("#CX").val(row.CX);
	 		$("#ZX").val(row.ZX);
	 		$('#sortBtnGd').unbind();
	 		$('#sortBtnGd').click(function(){
	 			$.ajax({
	 				url:'editGdlx.do',
	 				type:'post',
	 				data:{
	 					'gdlx.SQU':row.SQU,
	 					'gdlx.XH':$('#XH').val(),
	 					'gdlx.ZH':$('#ZH').val(),
	 					'gdlx.LBDM':$('#LBDM').val(),
	 					'gdlx.CX':$('#CX').val(),
	 					'gdlx.ZX':$('#ZX').val(),
	 				},		
	 				success:function(data){
	 					
	 						$('#closeWin').click();
	 						searchWord();  
	 						$('#example4').modal("hide");
	 			        	$(".modal-backdrop").remove();
	 					
	 				},
	 				error:function(){
	 					alert("编辑出错！");
	 				}
	 			});
	 		});
	 	}else if(gdtitle=="GXGG"){
	 		$("#secondTitleGg").html("编辑管线规格");
	 		$("#exampleGg").modal();
	 		$("#XHGg").val(row.XH);
	 		$('#sortBtnGg').unbind();
	 		$('#sortBtnGg').click(function(){
	 			$.ajax({
	 				url:'editGdlx.do',
	 				type:'post',
	 				data:{
	 					'gdlx.SQU':row.SQU,
	 					'gdlx.XH':$('#XHGg').val(),
	 					
	 				},		
	 				success:function(data){
	 					
	 						$('#closeWin').click();
	 						searchWord();  
	 						$('#exampleGg').modal("hide");
	 			        	$(".modal-backdrop").remove();
	 					
	 				},
	 				error:function(){
	 					alert("编辑出错！");
	 				}
	 			});
	 		});
	 	}else if(gdtitle=="GXCZ"){
	 		$("#secondTitleCz").html("编辑管线材质");
	 		$("#exampleCz").modal();
	 		$("#XHCz").val(row.XH);
	 		$('#sortBtnCz').unbind();
	 		$('#sortBtnCz').click(function(){
	 			$.ajax({
	 				url:'editGdlx.do',
	 				type:'post',
	 				data:{
	 					'gdlx.SQU':row.SQU,
	 					'gdlx.XH':$('#XHCz').val(),
	 					
	 				},		
	 				success:function(data){
	 					
	 						$('#closeWin').click();
	 						searchWord();  
	 						$('#exampleCz').modal("hide");
	 			        	$(".modal-backdrop").remove();
	 					
	 				},
	 				error:function(){
	 					alert("编辑出错！");
	 				}
	 			});
	 		});
	 	}
	
	
}

function showEditEventAz(row){
	$("#secondTitle5").html("编辑安装方式");
	$("#example5").modal();
	$("#MS").val(row.MS);
	$("#LBDM1").val(row.LBDM);

	$('#sortBtnAz').unbind();
	$('#sortBtnAz').click(function(){
		$.ajax({
			url:'editAzlx.do',
			type:'post',
			data:{
				'gdlx.SQU':row.SQU,
				'gdlx.MS':$('#MS').val(),			
				'gdlx.LBDM':$('#LBDM1').val(),
			},		
			success:function(data){
				
					$('#closeWin').click();
					searchWord();  	
					$('#example5').modal("hide");
		        	$(".modal-backdrop").remove();
			},
			error:function(){
				alert("编辑出错！");
			}
		});
	});
}



function showDeleteEvent(row) {
	if (row != undefined) {
		deleteEvent(row);
	}
}

function deleteEvent(row) {
	Modal.confirm(
  {
    msg: "确认删除？"
  })
  .on( function (e) {
	  if(e){
			$.ajax({
				type : "post",
				url : "delChildMenu.do",
				timeout : 1321231232131213123,
				data : {
					"squ" : row.SQU,
				},
				success : function(data) {
					var da = eval("(" + data + ")");
						var sta = da.status
					
						if(sta!=0){
							Modal.alert(
									  {
									    msg: '请先删除该目录下的子目录',
									    title: '消息提示',
									    btnok: '确定',
									    btncl:'取消'
									    	
									  });
						}
						if(row.PARENTSQU==-1){
							gridload("-1")
						}else{
							searchWord();
						}
						
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


function showDeleteEventGd(row) {
	
	Modal.confirm(
			  {
			    msg: "确认删除？"
			  })
			  .on( function (e) {
				  if(e){
						$.ajax({
							type : "post",
							url : "deleteGdlx.do",
							timeout : 1321231232131213123,
							data : {
								"squ" : row.SQU,
							},
							success : function(data) {
								
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

/**
 * 模板下载
  * dowloadMB:(描述)
  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
  * @author: 唐青
  * @dateTime: 2017-10-21 下午3:08:39
  * @param: 
  * @return: void
 */
function dowloadMB(){
	var fla = $("#fla").val();
	
	if(fla==1){//部件目录
		location.href="dowloadPartsMB.do";
	}else if(fla==3){//管道类型
		location.href="dowloadGDLXMB.do";
	}else if(fla==4){//安装方式
		location.href="dowloadAZFSMB.do";
	}else{
   	 Modal.alert(
			  {
			    msg: '请选择节点',
			    title: '消息提示',
			    btnok: '确定',
			    btncl:'取消'	 			    	
			  });
			return false;
}
	
	
	
}

/**
 * 
  * addBatchParts:(批量导入)
  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
  * @author: 唐青
  * @dateTime: 2017-11-9 下午5:40:52
  * @param: 
  * @return: void
 */
function addBatchParts(){
	var fla = $("#fla").val();
	var file = $("#fie") 
	file.after(file.clone().val("")); 
	file.remove(); 
	if(fla==1){//部件目录
		$('#exampl3').modal();
		$("#btn_file_ok").click(function(){
			$("#uploadForm3").form("submit",{ 
		    	url:"addBatchParts.do",
		        success:function(data){ 
		        	$("#input_file").val(data);	
		        	$('#exampl3').modal("hide");
		        	$(".modal-backdrop").remove();
		        	searchWord();
		        }  
		    });
		});
	}else if(fla==3){//管道类型
		$("#secondTitle4").html("添加管道类型");
		$('#exampl3').modal();
		$("#btn_file_ok").click(function(){
			$("#uploadForm3").form("submit",{ 
		    	url:"addBatchGdlx.do",
		        success:function(data){ 
		        	$("#input_file").val(data);	
		        	$('#exampl3').modal("hide");
		        	$(".modal-backdrop").remove();
		        	searchWord();
		        }  
		    });
		});
	}else if(fla==4){//安装方式
		$("#secondTitle5").html("添加安装方式");
		$('#exampl3').modal();
		$("#btn_file_ok").click(function(){
			$("#uploadForm3").form("submit",{ 
		    	url:"addBatchAzfs.do",
		        success:function(data){ 
		        	$("#input_file").val(data);	
		        	$('#exampl3').modal("hide");
		        	$(".modal-backdrop").remove();
		        	searchWord();
		        }  
		    });
		});
	}else{
   	 Modal.alert(
			  {
			    msg: '请选择节点',
			    title: '消息提示',
			    btnok: '确定',
			    btncl:'取消'	 			    	
			  });
			return false;
}
	
	
}


//右边下半部分分页
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
    outstr = "";
    $(".pageCk").unbind();
    $(".pageCk").bind('click',function(){
    	gridload($("#pd").val(),$(this).attr("tag"));
    	//gridload($(this).attr("tag"));
    	$("#pd").val($("#pd").val())
	});
    $(".pagePk").unbind();
    $(".pagePk").bind('click',function(){
    	//gridload($(this).attr("tag"));
    	gridload($("#pd").val(),$(this).attr("tag"));
    	$("#pd").val($("#pd").val())
	});
}	

