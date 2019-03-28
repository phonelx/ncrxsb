
//用户模块授权树對象
$(function() {
	selectSJKLX();//数据库安装方式
	selectGDLX();//数据库管道类型
	
});


function parentsearch(obj){

	parentload(obj);
	searchWord();
	
}

//目录分类处填入数据
function searchLbmc(obj) {
	
	var squ = $(obj).attr("id");
	
		$.ajax({
			type : 'post',
			url : 'searchLbmc.do',
			timeout : 1321231232131213123,
			dataType:"json",
			data : {
				'squ' : squ,
			},
			success : function(data) {
				
				var row = data.list;
				var lbmcs = row[0].LBMC;
				var lbmc = lbmcs.split(',');
				
				var menuLbmc ="" ;
				for(var i = lbmc.length;i >0; --i){
					menuLbmc +=lbmc[i-1]+"/";
				}
				
				var showLbmc = menuLbmc.substring(0, menuLbmc.length-1);
				$(".divider1").html(showLbmc);
				
			},
			error : function() {
				$(".divider1").html("目录分类");
			}
		});
}


function parentload(obj){
	var ssmc = $(obj).attr("ssmc")
	var squ = $(obj).attr("id");
	var zbm = $(obj).attr("title");

	$("#jdlx").html(zbm);
	$("#fla").val( $(obj).attr("flag"))
	
	 
	$("#navg").text($(obj).attr("title"));
	$("#pd").val($(obj).attr("id"));
	$("#fid").val($(obj).attr("id"));
	loadMenu(obj);
	searchLbmc(obj);
	searchWord();
	
}
//左部目录
var count = 0;//添加按钮时使用
var flSqu = null;//分类SQU
function loadMenu(obj){
	
	flSqu=$(obj).attr("id");

	var flag = $(obj).attr("flag");
	var isEnd = $(obj).attr("isEnd");
	$("#ifEnd").val(isEnd);
	
		$.ajax({
			type : 'post',
			url : 'getPatrsMenu.do',
			timeout : 1321231232131213123,
			dataType:"json",
			async : false, //是否发送异步请求	
			data:{
				squ:flSqu,
				
			},
			success : function(data) {
			 
				var html = "";
				var list = data.list
				count = list.length;
				
				
				if(data.list[0]!=undefined){
					
					for(var i = 0;i < data.list.length; i++){
						
							html+=" <li class='nav-header hidden-tablet border-bottom' style='display:block; padding-left:15px;'>";
							html+=" <a href='javascript:void(0);'  style='color:#3f9fd9;' class='ajax-link'  ssmc='"+data.list[i].SSMC+"' zbm='"+data.list[i].DMLB+"' flag='1' id='"+data.list[i].SQU+"'  title ='"+data.list[i].LBMC+"' isEnd = "+data.list[i].JD+" onclick='parentsearch(this);'>";
							html+=" <i class='icon-darkgray '></i>&nbsp;"+data.list[i].LBMC+"</a>";
							if(data.list[i].JD==0){
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

/**
 * HSM
*/
var query="";
var checkValue=null;
function searchWord(){
	
	gridload(pager);
		
}

var records=0;
var currentPage=0;
var total=0;
var pageShowPage=5;//显示多少个人分页标签
var pageRowNum=7;//每页显示记录数
var pager=1;

//加载表格
function gridload(pager){
	
checkValue=$("#input_search").attr("value").toUpperCase();//搜索条件
query=checkValue=="关键字搜索"? "":checkValue;
	
var html="";
$.ajax( {
	type : 'post',
	url : 'listKzzj.do',
	dataType : 'json',
	async : false,
	data : {
		rows:pageRowNum,
		page:pager,
		searchKey:query,
		lxsqu:flSqu
	},
	success : function(data) {
	
		records=data.total;
		currentPage=data.pageNumber;
		total = data.pageCnt;
		pageRowNum=data.pageSize;
		
		
		$("#tableDiv").html("");//清空
		
		if(data.rows.length==0){
			$("#tableDiv").html("");
			top=1;
		}else{
		
			html+="<table class='table table-striped table-bordered bootstrap-datatable datatable'>";
			html+=" <tbody>";
		for(var i = 0;i<data.rows.length;i++){
			
				html+=" <tr>";
				html+=" <td class='box_img' style = 'width:84px;height:54px;'>";
				
				if(data.rows[i].zjlx =="DF265123EFA1499E91BDC45AEAF60ECC"){
					
					if(data.rows[i].zp == null ||data.rows[i].zp =="" ){
						html+="<img class='dashboard-avatar' src='resource/images/nopicture.png' /></td>";
					}else{
						html+=" <img class='dashboard-avatar'  src='/upload/"+data.rows[i].zp+"'></td>";
					}
					
				}else{
					
					if(data.rows[i].zp == null ||data.rows[i].zp =="" ){
						html+="<img class='dashboard-avatar' src='resource/images/nopicture.png' /></td>";
					}else{
					html+=" <img class='dashboard-avatar' style ='margin-right:35px'  src='/upload/"+data.rows[i].zp+"'></td>";
					}
				}
				
				html+=" <td class='box_desc'><h4>"+data.rows[i].dxmc+"</h4>";
				html+=" </td>";
				
				html+=" <td class='box_desc'>";
				html+=" <p>支架型式："+data.rows[i].zjxs+"</p></td>";
				
				html+=" <td class='box_desc' style ='border-left:0px'><input type='hidden' name ='id' id = 'dxsqu' value ='"+data.rows[i].dxSqu+"'/></td>";
				
				html+=" <td class='box_desc'><a href='javascript:void(0);'>";
				
				html+=" <a title='编辑'  onclick='showEditKzzj("+JSON.stringify(data.rows[i])+");'><i class='icon-edit icon-black'></i></a>";
				
				html+=" <a title='删除' href='javascript:void(0)' onclick='deleteKzzj(\""+data.rows[i].dxSqu+"\");'><i class='icon-trash icon-black'></i></a>";
				
				html+=" </td></tr>"; 
			}
		html+="</tbody></table>";
		$("#tableDiv").html(html);
		}
		
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

//添加支架
//添加支架
function showAddKzzj(){
	
	if(count!= 0){
		Modal.alert(
				  {
				    msg: '请选择到根目录!',
				    title: '消息提示',
				    btnok: '确定',
				    btncl:'取消'
				    	
				  });
	}else{
		
	
	$("#clearForm").click();
//	document.getElementById("imghead").style = "width:80px;height:80px";
	$("#imghead").css("width","80px");
	$("#imghead").css("height","80px");
	document.getElementById("userTitleName").innerHTML = "添加支架";
	document.getElementById("imghead").src = "resource/images/mrtp.png";
	
	$("#zjlxsqu").val(flSqu);
	
	//DepartSJKLXbox.setValue();//重置值
	//DepartGDLXbox.setValue();
	
	$("#userDiv").modal();
	$('#kzzjBtn').unbind();
	$('#kzzjBtn').click(function() {
		var lxcs = $("#lxcs").val();
		
		
	
	var ZJMC = $('#focusedInput').val();
	
	
	var flsqu = $("#zjlxsqu").val();
	if(flsqu == null || flsqu == ""){
		
		Modal.alert(
				  {
				    msg: '请选择支架类型!',
				    title: '消息提示',
				    btnok: '确定',
				    btncl:'取消'
				    	
				  });
		
	}else if(ZJMC == null || ZJMC == ""){
		Modal.alert(
				  {
				    msg: '支架名称不能为空!',
				    title: '消息提示',
				    btnok: '确定',
				    btncl:'取消'
				    	
				  });
		return false;
	}else{
		$("#kzzjForm").form("submit",{ 
	    	url:"addKzzj.do",
	    	success : function(data) {
	    		
				
				$('#closeUserBox').click();
				searchWord();
			},
			error : function() {
				Modal.alert(
						  {
							    msg: '添加出错！',
							    title: '消息提示',
							    btnok: '确定',
							    btncl:'取消'
							    	
							  });
			}
	    });
	}
	});
	
}
}


/*
 * 删除支架 ------------- start
 */
function deleteKzzj(row) {
	Modal.confirm({
		msg: "确认删除？"
		}).on( function (e) {
	
	if (e) {
		$.ajax({
			type : 'post',
			url : 'deleteKzzj.do',
			timeout : 1321231232131213123,
			data : {
				'dxSqu' : row,
			},
			success : function(data) {
				if (data == 'bjExit') {
					Modal.alert(
							  {
								    msg: '该支架中已存在部件！',
								    title: '消息提示',
								    btnok: '确定',
								    btncl:'取消'
								    	
								  });
					
				}else if (data == 'xmExit') {
					Modal.alert(
							  {
								    msg: '该支架已被项目使用！',
								    title: '消息提示',
								    btnok: '确定',
								    btncl:'取消'
								    	
								  });
					
				} 
				searchWord();
			},
			error : function() {
//				error : function() {
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
/*
 * 删除支架 ------------- end
 */


/*
 * 编辑支架 ------------- start
 */
function showEditKzzj(row){
	
	$("#clearForm").click();
	// 设置文本参数
	document.getElementById("userTitleName").innerHTML = "编辑支架";	

	$("#userDiv").modal();
	$('#kzzjBtn').unbind();
	
	$.ajax({
		url:'queryKzzjBySqu.do',
		type:'post',
		data:{
			'squ':row.dxSqu,			
		},		
		success:function(data){
			
			var da = eval("(" + data + ")");
		
				var list = da.slist;
				
				$("#kzzjDxsqu").val(list[0].dxSqu);
				$("#focusedInput").val(list[0].dxmc);
				$("#zjxs").val(list[0].zjxs);
				$("#zjlxsqu").val(list[0].zjlx);
				$("#lxcs").val(list[0].lxcs);
				$("#image").val(list[0].zp);
				
				// $("#zym").find("option[value = '"+list[0].zym+"']").attr("selected","selected");
				$("#zym option[value='"+list[0].zym+"']").attr("selected",true);
				$("#szzt option[value='"+list[0].szzt+"']").attr("selected",true);
				/*var tem=list[0].zym;
				$(" select option[value='"+tem+"']").attr("select","selected");  */
				
				document.getElementById("imghead").style = "width:80px;height:80px";
				if(list[0].zp == null || list[0].zp == ""){
					document.getElementById("imghead").src = 'resource/images/5047.png';
				}else{
					document.getElementById("imghead").src ="/upload/"+list[0].zp;
				}
				
				
				
		},
	
	});

	$('#kzzjBtn').click(function() {
		
		var ZJMC = $('#focusedInput').val();
		
	
		
		if(ZJMC == null || ZJMC == ""){
			Modal.alert(
					  {
					    msg: '支架名称不能为空!',
					    title: '消息提示',
					    btnok: '确定',
					    btncl:'取消'
					    	
					  });
			return false;
		}else{
			$("#kzzjForm").form("submit",{ 
		    	url:"editKzzj.do",
		    	success : function(data) {
		    		
					$('#closeUserBox').click();
					searchWord();
				},
				error : function() {
					Modal.alert(
							  {
								    msg: '编辑支架信息出错！',
								    title: '消息提示',
								    btnok: '确定',
								    btncl:'取消'
								    	
								  });
				}
		    });

		}
		});
}

/*
 * 编辑支架 ------------- end
 */


//数据库安装方式类型
var DepartSJKLXbox;
function selectSJKLX(){
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
			extSelect(obj);
			DepartSJKLXbox = new Ext.form.MultiSelect( {  
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
function extSelect(obj){
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


//数据库管道类型
var DepartGDLXbox;
function selectGDLX(){
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
				obj += "['" + list1[i].SQU + "','" + list1[i].XH + "']";
				if (data.length != i + 1) {
					obj += ",";
				}
				if (data.length == 1) {
					obj = "[['" + list1[i].SQU + "','" + list1[i].XH + "']";
				}
			}
			obj += "]";
			extGdlxSelect(obj);
			DepartGDLXbox = new Ext.form.MultiSelect( {  
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
			});
		}
	});
}

var Dic1 ;
var reader1;
var store1 ;
function extGdlxSelect(obj){
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

//分页
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
      
   $("#pageDiv").html("<div class='pagination pagination-centered'  align='right'><ul>" + outstr + "<a href='javascript:void(0);'>共"+pageTotal+"页/"+pageRecords+"条数据</a></li></ul></div>");
    outstr = "";
    $(".pageCk").unbind();
    $(".pageCk").bind('click',function(){
    	gridload($(this).attr("tag"));
	});
    
    $(".pagePk").unbind();
    $(".pagePk").bind('click',function(){
    	gridload($(this).attr("tag"));
	});
}	
