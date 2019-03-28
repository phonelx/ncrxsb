/**
 * 页签计数器
 * 
 */
var _num = 1; 

$(function(){
	//查询首页表格数据
	querySeismicStents(1,"1", "tab11");
	//监听
	listen();
})



function parentload1(obj){
	//显示支架列表
	$("#zjCount").css("display","block");
	//隐藏支架下的不加列表
	$("#span9").css("display","none");
	var ssmc = $(obj).attr("ssmc")

	var squ = $(obj).attr("id");
	var zbm = $(obj).attr("title");
	$('#zjlxsqu').val(squ)
	$("#jdlx").html(zbm);
	$("#fla").val( $(obj).attr("flag"))

	loadMenu1(obj);
	
	if($(obj).attr("isEnd")==0){
		querySeismicStents(1,1, "tab11");
		$('#zjlxsqu').val(1)
	}else{
		querySeismicStents(1,squ, "tab11");
	}
	
}






var obj0 = null;
var menuSqu = null;
function loadMenu1(obj){
	obj0= obj;
	menuSqu=$(obj).attr("id");

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
				squ:menuSqu,
				
			},
			success : function(data) {
				if(data.list == null || data.list == ""){
					loadMenu(pager,menuSqu);
					
				}
				var html = "";
				var list = data.list				
				if(data.list[0]!=undefined){
					
					for(var i = 0;i < data.list.length; i++){
						
							html+=" <li class='nav-header hidden-tablet border-bottom' style='display:block; padding-left:15px;'>";
							html+=" <a href='javascript:void(0);'  style='color:#3f9fd9;' class='ajax-link'  ssmc='"+data.list[i].SSMC+"' zbm='"+data.list[i].DMLB+"' flag='1' id='"+data.list[i].SQU+"'  title ='"+data.list[i].LBMC+"' isEnd = "+data.list[i].JD+" onclick='parentload1(this);'>";
							html+=" <i class='icon-darkgray '></i>&nbsp;"+data.list[i].LBMC+"</a>";
							//if(data.list[i].JD==0){
								html+=" <ul class='topnav' >";
								html+=" </ul>";
							//}
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


function keyDown(e){ 
	 var keycode = 0;
	 keycode = e.keyCode;
	 if (keycode == 13 ) //回车键是13
	 {
		 loadMenu(pager);
	 }
}

var obj1 = null;
function parentload(obj){
	//隐藏支架列表
	$("#zjCount").css("display","none");
    obj1 = obj;
    document.getElementById("span9").style.display = "block";
	showKzzj(obj);
	showBjList(pager1,1);
	//查询首页表格数据
	
}

var records=0;
var currentPage=0;
var total=0;
var pageShowPage=2;//显示多少个人分页标签
var pageRowNum=10;//每页显示记录数
var pager =1;

//加载左部菜单列表
function loadMenu(pager,obj){
	
		$.ajax({
			type : 'post',
			url : 'getKzzjMenu.do',
			timeout : 1321231232131213123,
			dataType:"json",
			data:{
				rows:pageRowNum,
				page:pager,
				zjlx:obj
			},
			success : function(data) {
				
				records=data.total;
				currentPage=data.pageNumber;
				total = data.pageCnt;
				pageRowNum=data.pageSize;
				
				var html="";
				for(var i = 0;i<data.rows.length;i++){
						   
							html+=" <li class='nav-header hidden-tablet border-bottom' style='display:block; padding-left:15px;'>";
							html+=" <a href='javascript:void(0);'  style='color:#3f9fd9;' class='ajax-link'  flag='1' id='"+data.rows[i].dxSqu+"'  title ='"+data.rows[i].dxmc+"' onclick='parentload("+JSON.stringify(data.rows[i])+");'>";
							html+=" &nbsp;"+data.rows[i].dxmc+"</a>";
							//html+="<ul class='topnav'></ul>"
							html+=" </li>";
					}
			
					
					$(obj0).parent().find("ul").html(html);
					
					$(obj0).next("ul").accordion({
						accordion:false,
						speed: 300,
						closedSign: '[+]',
						openedSign: '[-]'
					});
//					$(".topnav #menu").accordion({
//						accordion:false,
//						speed: 500,
//						closedSign: '[+]',
//						openedSign: '[-]'
//					});
//					
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

}




//右边上半部分
function showKzzj(obj){
	$("#azfs").val(obj.azfs);//隐藏安装方式赋值
	$("#gdlx").val(obj.gdlx);//隐藏管道类型赋值
	$('#zjqu').val(obj.dxSqu);
	$.ajax({
		url:'loadKzzjBySqu.do',
		type:'post',
		data:{
			'squ':obj.dxSqu,			
		},		
		success:function(data){
			
			var da = eval("(" + data + ")");
				var list = da.list;

				$("#kzzjDXMC").html(list[0].dxmc);
		
				$("#kzzjZJXS").html(list[0].zjxs);
				$("#kzzjdj").html(list[0].COUN);
				
				if(list[0].zp == null){
					document.getElementById("kzzjImg").src ="resource/images/120.png";
				}else{
					document.getElementById("kzzjImg").src = "/upload/"+list[0].zp;
				}
							
		},
	
	});
	
}

/*****关键字检索****/
var query="";
function searchWord(){
	showBjList(pager1,bjfl);
}  

var records1=0;
var currentPage1=0;
var total1=0;
var pageShowPage1=3;//显示多少个人分页标签
var pageRowNum1=5;//每页显示记录数
var pager1 =1;

//右边下半部分表格
var bjfl = 1;//部件类型标记
function showBjList(pager1,op1){
	bjfl = op1;
	$("#bjfl").val(op1);
	document.getElementById("content").style.display = "block";
	 var llis = document.getElementsByName("tab_li"); 
     
     for(var i = 0; i < llis.length; i++) {  
         var lli = llis[i];  
         if(lli == document.getElementById("tab" + op1)) {  
             lli.style.backgroundColor = "#cfdefc"; 
             lli.style.border = "1px solid #ddd";
         } else {  
             lli.style.backgroundColor = "#f5f5f5";  
         }  
     } 
     
     
     var divs = document.getElementsByClassName("tab_css");  
     for(var i = 0; i < divs.length; i++) {  

         var divv = divs[i];  

         if(divv == document.getElementById("tab" + op1 + "_content")) {  
             divv.style.display = "block";  
         } else {  
             divv.style.display = "none";  
         }  
     }  
     
    if(op1 == 1){
    	var word = $("#input_search0").attr("value");
    	if (word=="关键字搜索") {
    		query ="";
    	}else{
    		query=$("#input_search0").val();
    	} 
    }else if(op1 == 2){
    	var word = $("#input_search1").attr("value");
    	if (word=="关键字搜索") {
    		query ="";
    	}else{
    		query=$("#input_search1").val();
    	} 
    }else if(op1 == 3){
    	var word = $("#input_search2").attr("value");
    	if (word=="关键字搜索") {
    		query ="";
    	}else{
    		query=$("#input_search2").val();
    	} 
    }else if(op1 == 4){
    	var word = $("#input_search3").attr("value");
    	if (word=="关键字搜索") {
    		query ="";
    	}else{
    		query=$("#input_search3").val();
    	} 
    }
	
	var html="";
	$.ajax({
		type : 'post',
		url : 'getKzzjBjList.do',
		timeout : 1321231232131213123,
		dataType:"json",
		data:{
			rows:pageRowNum1,
			page:pager1,
			op:op1,
			dxsqu:obj1.dxSqu,
			searchKey:query,
		},
		success : function(data) {
        
			records1=data.total;
			currentPage1=data.pageNumber;
			total1 = data.pageCnt;
			pageRowNum1=data.pageSize;
			
			html+="<thead><tr><th width ='35'>序号</th>";
			html+="<th width ='0' style = 'padding:0px;border-left:0px'></th>";
//			html+="<th width ='200'>产品编码</th>";
			html+="<th width ='315'>部件名称</th>";
			html+="<th width ='100'>部件类型</th>";
//			html+="<th width ='100'>部件型号</th>";
			html+="<th width ='50'>单位</th>";
			html+="<th width ='50'>数量</th>";
			html+="<th width ='70'>单价</th>";
			html+="<th width ='70'>金额</th>";
			html+="<th width ='160'>操作</th><tr></thead>";
			
			
			html+="<tbody>";
			for(var i = 0;i<data.rows.length;i++){
				if(data.rows[i].BJLX == 0){//普通部件
					
					html+="<tr  width ='35'><td>"+(i+1)+"</td>";
					
					html+="<td style = 'padding:0px;border-left:0px'><input type = 'hidden' id = 'squ' value = '"+data.rows[i].SQU+"'/></td>";
//					html+="<td>"+data.rows[i].CPBM+"</td>";
					html+="<td width ='315'>"+data.rows[i].CYMC+"</td>";
					html+="<td width ='100'>普通部件</td>";
//					html+="<td>"+data.rows[i].BJXH+"</td>";
					html+="<td width ='50'>"+data.rows[i].JLDW+"</td>";
					html+="<td width ='70'>"+data.rows[i].SL+"</td>";
					
					if(data.rows[i].CBDJ == null || data.rows[i].CBDJ == ""){
						html+="<td width ='70'></td>";
					}else{
						html+="<td width ='70'>"+data.rows[i].CBDJ+"</td>";
					}
					
					html+="<td width ='70'>"+(data.rows[i].SL*data.rows[i].CBDJ)+"</td>";
					html+="<td width ='160'><a title='编辑'  href='javascript:void(0);' onclick = 'showEditKzzjBj("+JSON.stringify(data.rows[i])+");'> <i class='icon-edit icon-black'></i></a>&nbsp;";
					html+="<a title='删除'  href='javascript:void(0);' onclick = 'delKzzjBj(\""+data.rows[i].SQU+"\");'> <i class='icon-trash icon-black'></i></a></td></tr>";
				}else{//组合部件
					var money = data.rows[i].CBDJ;
					//
					html+="<tr width ='35'   class='selected' id='"+data.rows[i].ZHSQU+"' ><td>"+(i+1)+"</td>";
					
					html+="<td style = 'padding:0px;border-left:0px'><input type = 'hidden' id = 'squ' value = '"+data.rows[i].SQU+"'/></td>";
//					
					html+="<td width ='315' onclick='queryBj(this)' id='"+data.rows[i].ZHSQU+"'>"+data.rows[i].DXZHMC+"</td>";
					html+="<td>组合部件</td>";
//					html+="<td></td>";
					html+="<td width ='100'>个</td>";
					html+="<td width ='50'>"+data.rows[i].SL+"</td>";
					if(money==-1){
						html+="<td width ='70'></td>";
						html+="<td width ='70'></td>";
					}else{
						html+="<td width ='70'>"+money+"</td>";
						html+="<td width ='70'>"+money*data.rows[i].SL+"</td>";
					}
				
					html+="<td width ='70'><a title='编辑'  href='javascript:void(0);' onclick = 'showEditKzzjBj("+JSON.stringify(data.rows[i])+");'> <i class='icon-edit icon-black'></i></a>&nbsp;";
					html+="<a title='删除'  href='javascript:void(0);' onclick = 'delKzzjBj(\""+data.rows[i].SQU+"\");'> <i class='icon-trash icon-black'></i></a></td></tr>";
					/*html+="<tr id='r"+data.rows[i].ZHSQU+"'style='display:none'> <td colspan='9'>";//style='border-collapse: collapse;border-width:0px; border-style:hidden;'>  cellspacing='0' cellpadding='0'
					html+="<table id='ro"+data.rows[i].ZHSQU+"' class='table table-striped table-bordered bootstrap-datatable datatable'   ";
					html+="style='border-collapse: collapse;border-width:0px; border-style:hidden;margin-top:-8px;margin-left:-8px;'>";
					//html+="<tr><td>1</td><td>O型管束</td><td>普通部件</td><td>个</td><td>6</td><td>10</td><td>60</td></tr><tr><td>2</td><td>鞍型管束</td><td>普通部件</td><td>个</td><td>2</td><td>10</td><td>20</td></tr>"
					html+="</table>";
					html+=" </td></tr>";*/
				
				}
			}
			html+="</tbody>";
			
			if(bjfl == 1){
				$('#t_table1').empty();//让列表置空
				$("#t_table1").append(html);
			}
			 if(bjfl == 2){
				$('#t_table2').empty();//让列表置空
				$("#t_table2").append(html);
			}
			 if(bjfl == 3){
				$('#t_table3').empty();//让列表置空
				$("#t_table3").append(html);
			 }
			if(bjfl == 4){
				$('#t_table4').empty();//让列表置空
				$("#t_table4").append(html);
			}
				

		pageDiv(records1,total1,currentPage1);
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
	
}
/*
	$(function(){  
		
	    $('tr.parent').click(function(){   // 获取所谓的父行  
	    	alert(this.id)
	            $(this)  
	                .toggleClass("selected")   // 添加/删除高亮  
	                .siblings('#r'+this.id).toggle();  // 隐藏/显示所谓的子行  
	    }).click();  
	})*/
/**
 * 组合部件下面部件表格的伸缩效果
 */
function formScale(obj){
var id=$(obj).attr("id");
	
	$(obj).toggleClass("selected")   // 添加/删除高亮  
         .siblings('#r'+id).toggle();  // 隐藏/显示所谓的子行  
}
/**
 * 
  * queryBj:( 组合部件下面部件表格)
  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
  * @author: 唐青
  * @dateTime: 2018-2-2 下午2:27:48
  * @param: @param obj
  * @return: void
 */
function queryBj(obj){
	
	if($("#tabBj").size()>0){
		
			
			 $(obj).parents("tr").siblings("#tabBj").remove();
	
		
	}else{
		var id=$(obj).attr("id");
		$.ajax({
			type : 'post',
			url : 'queryBj.do',
			timeout : 1321231232131213123,
			dataType:"json",
			data:{
				squ:id
			},
			success:function(data){
				var list = data.row
				var len = list.length;
				
				var htm = "";
			
					
					for(var i=0;i<len;i++){
						htm +="<tr id='tabBj'><td ></td>";
						htm +="<td style = 'padding:0px;border-left:0px'><input type = 'hidden'  value = ''/></td>";
						htm +="<td >"+list[i].CYMC+"</td>";
						htm +="<td >普通部件</td>";
						htm +="<td >"+list[i].JLDW+"</td>";
						htm +="<td >"+list[i].SJ+"</td>";
						htm +="<td >"+list[i].CBDJ+"</td>";
						htm +="<td >"+(list[i].CBDJ)*(list[i].SJ)+"</td>";
						htm +="<td ></td></tr>";
						
					}
					 $(obj).parents("tr").after(htm);
					//$("#ro"+id+"").html(htm)
					
				
							
			}
		});
	}
	
	}

function querySeismicStents(pager1,code, tabId){
	
	pager11 = pager1
	bjfl = 5
	$.ajax({
		url : "querySeismicStents.do",
		type : "post",
		dataType : "json",
		data : {
			squ:code,
			rows:pageRowNum1,
			page:pager1,
		},
		success : function(data){			
				createTable(data, tabId);	//加载表格
			
		},
		error : function(){
			("查询数据错误！");
		}
	});
}
/**
 * 
  * createTable:(拼接表格)
  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
  * @author: 唐青
  * @dateTime: 2018-2-2 下午4:55:05
  * @param: @param list
  * @param: @param tabId
  * @return: void
 */
function createTable(data, tabId){
	
	records1=data.total;
	currentPage1=data.pageNumber;
	total1 = data.pageCnt;
	pageRowNum1=data.pageSize;
	
	var list = data.rows;
	
	var html = "<table class='table table-striped table-bordered table_count'>";
		html += "<tr><th width='20%'>序号</th><th>支架名称</th><th>支架类型</th>";
	//	html += "<th>操作</th></tr>";
	//当查询到数据时
	var len = list.length;
	if (len > 0) {
		
		for ( var i = 0; i < len; i++) {
			
				
					html += "<tr><td>" + (1+i) + "</td>";
					html += "<td>" + list[i].DXMC + "</td>";
					html += "<td>" + list[i].ZJXS + "</td>";
					/*<td><span tag='0' class='wk-uigrid'><a title='下一级'  href='javascript:void(0);' onclick='clickTdOrTh(this)' code='" + list[i].DXSQU + "'> <i class='icon icon-black icon-newwin'></i></a></span></td>*/
					html += "</tr>";
			}
			
		
	} else {
		html += "<tr><td colspan='5'>未查询到数据</td></tr>";
	}
	var t = tabId.substr(tabId.length-1,1)
	
	html += "</table>";
	html += "<div id='pageDiv5'></div>";

	$("#" + tabId + "").html(html);
	pageDiv(records1,total1,currentPage1);
	
}

/**
 * 
  * querySeismicStents:(描述)
  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
  * @author: 唐青
  * @dateTime: 2018-2-5 下午4:04:28
  * @param: @param pager1
  * @param: @param code
  * @param: @param tabId
  * @return: void
 */
function querySeismicBj(pager1,code, tabId){
	pager12 = pager1
	bjfl = 6
	//alert(6)
	$.ajax({
		url : "getKzzjBjList.do",
		type : "post",
		dataType : "json",
		data : {
			op:0,
			dxsqu:code,
			rows:pageRowNum1,
			page:pager1,
			searchKey:"",
		},
		success : function(data){			
				createBjTable(data, tabId);	//加载表格
			
		},
		error : function(){
			("查询数据错误！");
		}
	});
}
/**
 * 
  * createTable:(拼接表格)
  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
  * @author: 唐青
  * @dateTime: 2018-2-2 下午4:55:05
  * @param: @param list
  * @param: @param tabId
  * @return: void
 */
function createBjTable(data, tabId){
	
	records1=data.total;
	currentPage1=data.pageNumber;
	total1 = data.pageCnt;
	pageRowNum1=data.pageSize;
	
	var list = data.rows;
	
	var html = "<table class='table table-striped table-bordered table_count'>";
		html += "<tr><th width='20%'>序号</th><th>部件名称</th><th>部件分类</th><th>部件类型</th>";
		html += "<th>单价</th></tr>";
	//当查询到数据时
	var len = list.length;
	if (len > 0) {
		
		for ( var i = 0; i < len; i++) {
		
			
					html += "<tr><td>" + (1+i) + "</td>";
					if(list[i].BJLX==0){
						html += "<td>" + list[i].CPZM + "</td>";
					}else if(list[i].BJLX==1){
						html += "<td>" + list[i].DXZHMC + "</td>";
					}
					if(list[i].BJFL==1){
						html += "<td>结构组建</td>";
					}else if(list[i].BJFL==2){
						html += "<td>承重组件</td>";
					}else if(list[i].BJFL==2){
						html += "<td>抗震组件</td>";
					}else if(list[i].BJFL==2){
						html += "<td>约束组件</td>";
					}
					if(list[i].BJLX==0){
						html += "<td>普通部件</td>";
					}else if(list[i].BJLX==1){
						html += "<td>组合部件</td>";
					}
					
					html += "<td>" + list[i].CBDJ + "</td>";
					
					
				//	html += "<td><span tag='0' class='wk-uigrid'><a title='下一级'  href='javascript:void(0);' onclick='clickTdOrTh(this)' code='" + list[i].DXSQU + "'> <i class='icon icon-black icon-newwin'></i></a></span></td></tr>";
			}
			
		
	} else {
		html += "<tr><td colspan='5'>未查询到数据</td></tr>";
	}
	//var t = tabId.substr(tabId.length-1,1)
	html += "</table>";
	html += "<div id='pageDiv6'></div>";
	
	$("#" + tabId + "").html(html);
	pageDiv(records1,total1,currentPage1);
	
}

function listen(){
	//表格点击事件
	//clickTdOrTh();
	//关闭页签
	$('.icon-remove').live('click', function(){
		//bjfl = 5;
		//alert(11)
		//获取序号ID
		var id = $(this).parents().attr("href");
		$('.tab-content').find("" + id + "").prev().addClass('active');
		$('.tab-content').find("" + id + "").remove();
		//选择新页签
		$(this).parent().parent().prev().addClass('active');
		//移除页签
		$(this).parent().parent().remove();
		
	});
	
	//条件选择框
	$(document).on("click",'.box-icon', function(){
		if ($(this).find('i').attr('class') == "icon-chevron-up"){
			$(this).find('i').removeClass("icon-chevron-up");
			$(this).find('i').addClass("icon-chevron-down");
		} else {
			$(this).find('i').removeClass("icon-chevron-down");
			$(this).find('i').addClass("icon-chevron-up");
		}
		$('.box-content').slideToggle();
	});
	
	$("input[name='personType']").click(function () { 
		//显示当前条件
		//遍历
		$('input[type="checkbox"][name="personType"]').each(function() {
			if ($(this).is(':checked')) {
				$('#' + $(this).attr('value') + 'Condition').show();
			} else {
				$('#' + $(this).attr('value') + 'Condition').hide();
			}
		});
	});
}

/**
 * 表格点击事件
 */

function clickTdOrTh(obj){
	//页签序号自增
	/*_num++;*/
	//区域下钻
	
		//新增页签
		var area_id = $(obj).attr("code");
		
		$('#zjqu').val(area_id)
		var number = $(".nav-tabs li").length
		
		if(number==1){
			addTabs($(obj).text(), "list", area_id, "");
			
			//
		}else{
			$('.nav').find('.active').removeClass("active");
			$('.tab-content').find('.active').removeClass("active");

			$(".nav-tabs li").eq(1).attr("class", "active");
			$("#tab12").attr("class", "tab-pane active");
		}
		querySeismicBj(1,area_id, "tab12");
		//querySeismicBj(1,area_id, "tab1" + _num);

	

}


/**
 * 新增页签
 * @param tagName 页签标题
 * @param flag 类型标识和图标代码
 * @param area_id 区划代码
 */
function addTabs(tagName, flag, area_id, control_type_id){
	//页签序号自增
	_num++;
	
	//移除当前选择页签class
	$('.nav').find('.active').removeClass("active");
	$('.tab-content').find('.active').removeClass("active");
	
	//页签
	//var html = "<li class='active'><a href='#tab1" + _num + "' data-toggle='tab'>";
	var html = "<li class='active'><a href='#tab12' data-toggle='tab' onclick='setBjfl(6)'>";
	html += "<i class='icon-" + flag + "'></i>&nbsp;" + tagName;
	html += "&nbsp;&nbsp;&nbsp;<i class='icon-remove'></i></a></li>";
	$('.nav').append(html);
	
	//在页签中添加区划代码和当前标签类型标识用于区分标签类型
	$('.active').find("a").attr("code",area_id).attr("flag","0").attr("controlType",control_type_id);
	
	//var html = "<div class='tab-pane active' id='tab1" + _num + "'>";
	var html = "<div class='tab-pane active' id='tab12'>";
	/*if (flag == "list") {	//统计列表
		//在页签中添加当前标签类型标识
		$('.active').find("a").attr("flag","0");
		$('.tab-content').append("</div>");
	} else if (flag == "user") {
		$('.active').find("a").attr("flag","1");
		html += "<p></p><div><div id='dataGrld" + _num + "'></div></div>";
	}*/
	$('.tab-content').append(html + "</div>");
}





function setBjfl(code){	
	if(code==5){
		querySeismicStents(pager11,$('#zjlxsqu').val(), "tab11")
		$("#tab12").parent().remove();
	}else if(code==6){
		 querySeismicBj(pager12,$('#zjsqu').val(), "tab12")
		
	}
	
	
	
}




//右边下半部分分页
function pageDiv(records,total,currentPage){
	var pageTotal=total;// 总共多少页
	var pageCurrentPage=currentPage;//当前页
	var pageRecords=records;//总记录数
	var countt="";
	var outstr="";
	
	pageTotal=parseInt(pageRecords/pageRowNum1);
	if(pageRecords%pageRowNum1>0){
		pageTotal=pageTotal+1;
	}
        if(pageTotal<=pageShowPage){
                for (var count=1;count<=pageTotal;count++){
                        if (count<10) {countt ="0"+count+"";}else{countt =""+count+"";}
                        if(count!=pageCurrentPage){
                             outstr = outstr + "<li tag='"+count+"' class='pageCk1'><a href='javascript:void(0);'>"+countt+"</a></li>";
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
                                	outstr = outstr + "<li tag='"+count+"' class='pageCk1'><a  href='javascript:void(0);'>"+countt+"</a></li>";
                                	
                                }else{
                                	 outstr = outstr + "<li class='active'><a href='javascript:void(0);'>"+countt+"</a></li>";
                                }
                        }
                        outstr = outstr+"<li tag='"+count+"' class='pagePk1'><a href='javascript:void(0)'>>></a></li>";
                }else if(parseInt((pageCurrentPage-1)/pageShowPage) == parseInt(pageTotal/pageShowPage)){
                        outstr = outstr + "<li tag='"+(parseInt((pageCurrentPage-1)/pageShowPage)*pageShowPage)+"' class='pagePk1'><a href='javascript:void(0)'><<</a></li> ";
                        for (var count=parseInt(pageTotal/pageShowPage)*pageShowPage+1;count<=pageTotal;count++){
                                if (count<10) {countt ="0"+count+"";}else{countt =""+count+"";}
                                if(count!=pageCurrentPage){
                                	outstr = outstr + "<li  tag='"+count+"' class='pageCk1'><a href='javascript:void(0);'>"+countt+"</a></li>";
                                }else{
                                	 outstr = outstr + "<li class='active'><a href='javascript:void(0);'>"+countt+"</a></li>";
                                }
                        }
                        outstr = outstr + "<li><a href='javascript:void(0)'>>></a></li>";
                }else{
                        outstr = outstr + "<li tag='"+(parseInt((pageCurrentPage-1)/pageShowPage)*pageShowPage)+"' class='pagePk1'><a href='javascript:void(0)'><<</a></li> ";
                        for (var count=parseInt((pageCurrentPage-1)/pageShowPage)*pageShowPage+1;count<=parseInt((pageCurrentPage-1)/pageShowPage)*pageShowPage+pageShowPage;count++){
                                if (count<10) {countt ="0"+count+"";}else{countt =""+count+"";}
                                if(count!=pageCurrentPage){
                                	outstr = outstr + "<li tag='"+count+"' class='pageCk1'><a href='javascript:void(0);'>"+countt+"</a></li>";
                                }else{
                                	
                                        outstr = outstr + "<li class='active'><a href='javascript:void(0);'>"+countt+"</a></li>";
                                }
                        }
                        outstr = outstr + "<li  tag='"+count+"' class='pageCk1'><a href='javascript:void(0);'>>></a></li>";
                }
    } 
   //  alert(bjfl)
        //共"+pageTotal+"页|"+pageRecords+"条数据|第"+pageCurrentPage+"页 每页" + pageRowNum +"个　
        if(bjfl == 1){
        	 $("#pageDiv1").html("<div class='pagination pagination-centered'  align='right'><ul>" + outstr + "<a href='javascript:void(0);'>共"+pageTotal+"页/"+pageRecords+"条数据</a></li></ul></div>");
        }
        if(bjfl == 2){
        	 $("#pageDiv2").html("<div class='pagination pagination-centered'  align='right'><ul>" + outstr + "<a href='javascript:void(0);'>共"+pageTotal+"页/"+pageRecords+"条数据</a></li></ul></div>");
        }
        if(bjfl == 3){
			$("#pageDiv3").html("<div class='pagination pagination-centered'  align='right'><ul>" + outstr + "<a href='javascript:void(0);'>共"+pageTotal+"页/"+pageRecords+"条数据</a></li></ul></div>");
		}
        if(bjfl == 4){
			$("#pageDiv4").html("<div class='pagination pagination-centered'  align='right'><ul>" + outstr + "<a href='javascript:void(0);'>共"+pageTotal+"页/"+pageRecords+"条数据</a></li></ul></div>");
		}
      
      
        if(bjfl == 5){
			$("#pageDiv5").html("<div class='pagination pagination-centered'  align='right'><ul>" + outstr + "<a href='javascript:void(0);'>共"+pageTotal+"页/"+pageRecords+"条数据</a></li></ul></div>");
		}if(bjfl == 6){
			$("#pageDiv6").html("<div class='pagination pagination-centered'  align='right'><ul>" + outstr + "<a href='javascript:void(0);'>共"+pageTotal+"页/"+pageRecords+"条数据</a></li></ul></div>");
		}
        	
       if(bjfl == 5){
    	
    	   var tablId = "tab1"+(bjfl-4)
    	   outstr = "";
		    $(".pageCk1").unbind();
		    
		    $(".pageCk1").bind('click',function(){
		    	
		     querySeismicStents($(this).attr("tag"),$('#zjlxsqu').val(),tablId)
		    //	showBjList();
			});
		    
		    $(".pagePk1").unbind();
		    $(".pagePk1").bind('click',function(){
		    	querySeismicStents($(this).attr("tag"),$('#zjlxsqu').val(),tablId)
			});
    	   
       }else  if(bjfl == 6){
    	   var tablId = "tab1"+(bjfl-4)
    	   outstr = "";
		    $(".pageCk1").unbind();
		    
		    $(".pageCk1").bind('click',function(){
		    	//alert($(this).attr("tag"))
		    	querySeismicBj($(this).attr("tag"),$('#zjsqu').val(),tablId)
		    	
		    //	showBjList();
			});
		   
		    $(".pagePk1").unbind();
		    $(".pagePk1").bind('click',function(){
		    	querySeismicBj($(this).attr("tag"),$('#zjsqu').val(),tablId)
		    	 
		    //	showBjList($(this).attr("tag"),$('#zjlxsqu').val(),bjfl);
			});
    	   
       }else{
    	   outstr = "";
		    $(".pageCk1").unbind();
		    
		    $(".pageCk1").bind('click',function(){
		    	showBjList($(this).attr("tag"),'',bjfl);
			});
		    
		    $(".pagePk1").unbind();
		    $(".pagePk1").bind('click',function(){
		    	showBjList($(this).attr("tag"),'',bjfl);
			});
       }
}

/*function pageDiv(records,total,currentPage){
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
    	gridload($(this).attr("tag"),$("#pd").val());
    	$("#pd").val($("#pd").val())
	});
    $(".pagePk").unbind();
    $(".pagePk").bind('click',function(){
    	gridload($(this).attr("tag"),$("#pd").val());
    	$("#pd").val($("#pd").val())
	});
}
*/

//部件新增功能
function appRegister(){

	$('#pt-select-box').show();
	//$("#clearForm").click();
	
	 $("#examplept").modal();
	 $(".select-box .second").html("");										
		$(".select-box .third").html("");										
		$(".select-box .foure").html("");
		$(".select-box .five").html("");
}

//部件新增功能
function appRegisterZh(){

	$('#zh-select-box').show();
		
	
	 $("#examplezh").modal();
	
}
//部件删除
function delKzzjBj(bjsqu){
	
	if (confirm('确认删除吗？')) {
		$.ajax({
			type : 'post',
			url : 'delKzzjBj.do',
			timeout : 1321231232131213123,
			data : {
				'dxsqu' : obj1.dxSqu,
				'bjsqu':bjsqu
			},
			success : function(data) {
				
				if(data == "delError"){
					alert("删除失败！");
				}else{
					alert("删除成功！");
					showBjList(pager1,bjfl);
				}
			},
			error : function() {
				alert('删除支架部件出错！');
			}
		});
	}
	
}
//普通部件编辑
function showEditKzzjBj(obj){
	 $("#zhexample").modal();
	var bjlx = obj.BJLX;

	$("#zhuserTitleName").html("部件明细")
	//document.getElementById("userTitleName").innerHTML = ;	
	if(bjlx == 0){//普通部件		
	
		$("#bjmc").val(obj.CYMC);
		$("#bjdw").val(obj.JLDW);
		$("#bjlxsl").val(obj.SL);
		$("#bjlx").val("普通部件");
	
		/*
		if(obj.ZP == null){
			$("#photoBox").html("<img src='resource/images/picture.png'/>");
		}else{

			$("#photoBox").html("<img src='/upload/"+obj.ZP+"'/>")
		}*/
	
	}
	if(bjlx == 1){//组合部件
		

		$("#bjlxsl").val(obj.SL);
		$("#bjdw").val("个");
		$("#bjmc").val(obj.DXZHMC);
		$("#bjlx").val("组合部件");
		
	}
	
	
	 $("#example").modal();
	 
	 $('#zhappBtn').unbind();
	 $('#zhappBtn').click(function() {
		 var squ =obj.SQU;//部件id
		 var bjsl = $("#bjlxsl").val();//部件数量
		 
			$.ajax({
				type : 'post',
				url : 'editKzzjBj.do',
				timeout : 1321231232131213123,
				data : {
					
					'sl':bjsl,
					'squ':squ
				},
				success : function(data) {
					
					$('#zhexample').modal("hide");
					$('#closeWin').click();
					showBjList(pager1,bjfl);
				},
				error : function() {
					alert('编辑出错！');
				}
			});
			  
	 });
		
}

//组合部件编辑
function editKzzjZhbj(obj){
	
	document.getElementById("zhuserTitleName").innerHTML = "编辑支架明细";
	document.getElementById("zhselectButton").style.display="none";
	document.getElementById("zhexample").style.height="450px";
	$("#zhphotoBox").css("display","block");
	
	$("#zhgdlx").val(obj.GDLX);
	$("#zhazfs").val(obj.AZFS);
	$("#zhbjsl").val(obj.SL);
	$("#zhzjxs").val(obj.ZJXS);
	$("#zhbjmc").val(obj.DXZHMC);
	$("#zhbjsqu").val(obj.ZHSQU);
	
	if(obj.ZP == null){
		$("#photoBox").html("<img src='resource/images/picture.png'/>");
	}else{
		$("#zhphotoBox").html("<img src='/upload/"+obj.ZP+"'/>")
	}
	$("#zhexample").modal();
	
	$('#zhappBtn').unbind();
	$('#zhappBtn').click(function() {
		var bjsqu = $('#zhbjsqu').val();//部件id
		var bjsl = $('#zhbjsl').val();//部件数量
		
		$.ajax({
			type : 'post',
			url : 'editKzzjBj.do',
			timeout : 1321231232131213123,
			data : {
				'dxsqu' : obj1.dxSqu,
				'sl':bjsl,
				'bjsqu':bjsqu
			},
			success : function(data) {
				
				alert("编辑成功！");
				$('#zhcloseWin').click();
				showBjList(pager1,1);
			},
			error : function() {
				alert('编辑出错！');
			}
		});
		
	});
	
}




