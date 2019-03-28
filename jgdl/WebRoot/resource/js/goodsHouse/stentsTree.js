$(function(){
	selectSJKLX();
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
	
});
var i=0;
function parentload(obj){
	var ssmc = $(obj).attr("ssmc")
	var squ = $(obj).attr("id");
	var zbm = $(obj).attr("zbm");
	var title= $(obj).attr("title");
	$("#plstent").html(title)
	var bm = "";
	$("#CPXH").val(squ);
	
	$("#navg").text($(obj).attr("title"));
	$("#pd").val($(obj).attr("id"));
	$("#input_search").attr("value","关键字搜索");
	loadMenu(obj);
	searchWord();
}


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
				squ:squ
			},
			success : function(data) {
				var html = "";
				var list = data.list				
				if(data.list[0]!=undefined){
					
					for(var i = 0;i < data.list.length; i++){
						
							html+=" <li class='border-bottom' style='display:block; padding-left:15px;'>";
							html+="  <a href='javascript:void(0);'  style='color:#3f9fd9;' class='ajax-link'  zbm='"+data.list[i].DMLB+"' flag='1' id='"+data.list[i].SQU+"'  title ='"+data.list[i].LBMC+"' isEnd = "+data.list[i].JD+" onclick='parentload(this);'>";
							html+=" "+data.list[i].LBMC+"</a>";
							//html+=" <i class='icon-darkgray '></i>&nbsp;"+data.list[i].LBMC+"</a>";
							if(data.list[i].JD==0){
								html+=" <ul class='topnav' >";
								html+=" </ul>";
							}
							html+=" </li>";
					}
					if(squ==0){
						$(obj).parent().parent().find("ul").html(html);
						$(obj).parent().parent().find("ul").accordion({
							accordion:false,
							speed: 300,
							closedSign: '[+]',
							openedSign: '[-]'
						});
					}else{
					$(obj).parent().find("ul").html(html);
					$(obj).next("ul").accordion({
							accordion:false,
							speed: 300,
							closedSign: '[+]',
							openedSign: '[-]'
						});
					}
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
var records=0;
var currentPage=0;
var total=0;
var pageShowPage=5;//显示多少个人分页标签
var pageRowNum=10;//每页显示记录数
var pager=1;
/*****关键字检索****/
var query="";
function searchWord(){
	var word = $("#input_search").val();
	if (word=="关键字搜索") {
		query ="1";
	}else{
		query=$("#input_search").val();
	} 
	gridload(pager,$("#pd").val());
}

//加载表格
var grid ;
var pageNo =1;
function gridload(pager,obj){
	$("#uiGrid-uigrid").css("display","block")
	
	var html="";
	$.ajax( {
		type : 'post',
		url : 'queryChileStents.do',
		dataType : 'json',
		async : false,
		data : {
			squ:obj,
			rows:pageRowNum,
			page:pager,
			key:query,
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
		/*	html+=" <tr>";
			html+=" <th class='td'>图片</th>";
			html+=" <th class='td'>部件编码</th>";
			html+=" <th class='td'>部件名称</th>";		
			html+=" <th class='td'>主要特征参数</th>";		
			html+=" <th class='td'>设定型号</th>";		
			html+=" <th class='td'>计量单位</th>";
			html+=" <th class='td'>成本单价</th>";		
			html+=" <th class='td'>操作</th>";
			html+=" </tr>";*/
			list = data.rows
		for(var i = 0;i<list.length;i++){
			//alert(list[i].SQU)
			html+="<tr>";  
		
			if(list[i].ZP==null||list[i].ZP==""){
				
				html+=" <td class='td'><img class='dashboard-avatar' src='resource/images/mrtp.png'></td>";
			}else{
			
				html+=" <td class='td'><img class='dashboard-avatar' src='/upload/"+list[i].ZP+"'></td>";
			}
			
			html+=" <td class='td1 td2 td3'><p style='font-size: 15px;font-weight: bold'>"+list[i].CYMC+"</p><br> <p>"+list[i].CPBM+"</p></td>";		
			html+=" <td class='td1 td2'><br>计量单位:"+list[i].JLDW+"</td>";	
			html+=" <td class='td1 td2'><br>力学参数:"+list[i].LXCS+"N</td>";	
			html+=" <td class='td1 td2'><br><a title='编辑'  onclick='editStents(\""+list[i].cpSQU+"\")'><i class='icon-edit icon-black'></i></a>&nbsp;<a title='删除' href='javascript:void(0)' onclick='deleteStents(\""+list[i].cpSQU+"\")'><i class='icon-trash icon-black'></i></a></td>";
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

/**
 * 
  * addChildMenu:(新增零部件)
  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
  * @author: 唐青
  * @dateTime: 2018-3-13 下午4:34:39
  * @param: @returns {Boolean}
  * @return: Boolean
 */
var i=0;
function addChildMenu(){

	var BMCPZM = "";
	if($("#BMCPZM").val()==""||$("#BMCPZM").val()==null){
		BMCPZM = ""
	}else{
		BMCPZM = "-"+$("#BMCPZM").val()
	}
	
	var cid = $("#pd").val();	
	$("#bjmc").val("");
	$("#cppm").val("");
	$("#bjxh").val("");
	$("#cbdj").val("");
	$("#edhl").val("");
	$("#jldw").val("");
	$("#lxcs").val("");
	$("#rsqu").val(cid);
	$("#secondTitle").html("新增部件信息");
	//DepartSJKLXbox.setValue();
	
	 if($("#ifEnd").val()!=0){		 
		 $("#example2").modal();
		 
		 $('#sortBtn').unbind();
		// $('#sortBtn').unbind('click')
			$("#sortBtn").click(function(){
				
				var lxcs = $("#lxcs").val();
				 reg = /^[-+]?\d+$/;
				    if (!reg.test(lxcs)) {
				    	$("#example2").hide();
			    		Modal.alert(
						  {
						    msg: '请填入大于或等于零是数字',
						    title: '消息提示',
						    btnok: '确定',
						    btncl:'取消'
						    	
						  }).on(function (e){
							  $("#example2").show();
						  });
			    		return false;
				    } else {
				    	if(lxcs < 0 ){
				    		
				    		$("#example2").hide();
				    		Modal.alert(
							  {
							    msg: '请填入大于或等于零是数字',
							    title: '消息提示',
							    btnok: '确定',
							    btncl:'取消'
							    	
							  }).on(function (e){
								  $("#example2").show();
							  });
				    		return false;
				    	}
				    }
		    		var bjmc = $("#bjmc").val();
		    		var cppm = $("#cppm").val();
		    		var bjxh = $("#bjxh").val();
		    		var cbdj = $("#cbdj").val();
		    		var edhl = $("#edhl").val();
		    		var jldw = $("#jldw").val();
		    		var lxcs = $("#lxcs").val();
		    		if(bjmc==""||bjmc==null||cppm==""||cppm==null||bjxh==""||bjxh==null||cbdj==""||cbdj==null
		    				||edhl==""||edhl==null||jldw==""||jldw==null||lxcs==""||lxcs==null){
		    			 checkInfo();
		    		}else{
		    			$("#uploadForm").form("submit",{ 
					    	url:"addStents.do",
					        success:function(data){
					        	
					        		 $('#example2').modal('hide');
						        	$(".modal-backdrop").remove();
						        	gridload(pager,cid)
					        }  
					    });
		    		}
		    		
					/* $('#example2').modal('hide');
			        	$(".modal-backdrop").remove();*/
		    	
		    	
				
		        	
			})
	 }else{
			Modal.alert(
					  {
					    msg: '请选择到根目录',
					    title: '消息提示',
					    btnok: '确定',
					    btncl:'取消'
					    	
					  });
					return false;
		}
	//	alert($("#tz").val())
		
	
}

//编辑分类
function editStents(obj){
	$("#bjmc").val("");
	$("#cppm").val("");
	$("#bjxh").val("");
	$("#cbdj").val("");
	$("#edhl").val("");
	$("#jldw").val("");
	$("#lxcs").val("");
	$("#secondTitle").html("编辑部件信息");
	 $("#example2").modal();
	$("#cpid").val(obj);
	
	
	$.ajax({
		url:'queryStentsBySqu.do',
		type:'post',
		data:{
			'squ':obj,			
		},		
		success:function(data){
			var da = eval("(" + data + ")");
		
				var list = da.slist;
				
				$("#bjmc").val(list[0].CYMC);
				$("#cppm").val(list[0].CPBM);
				$("#bjxh").val(list[0].BJXH);
				$("#cbdj").val(list[0].CBDJ);
				$("#edhl").val(list[0].EDHL);
				$("#jldw").val(list[0].JLDW);
				$("#lxcs").val(list[0].LXCS);
				//var ms = list[0].AZFSDM
				//DepartSJKLXbox.setValue(ms,true);
		},
	
	});
	
	
	$('#sortBtn').unbind();
	$('#sortBtn').click(function(){
		
		var lxcs = $("#lxcs").val();
		 reg = /^[-+]?\d+$/;
		    if (!reg.test(lxcs)) {
		    	$("#example2").hide();
	    		Modal.alert(
				  {
				    msg: '请填入大于或等于零是数字',
				    title: '消息提示',
				    btnok: '确定',
				    btncl:'取消'
				    	
				  }).on(function (e){
					  $("#example2").show();
				  });
	    		return false;
		    } else {
		    	if(lxcs < 0 ){
		    		
		    		$("#example2").hide();
		    		Modal.alert(
					  {
					    msg: '请填入大于或等于零是数字',
					    title: '消息提示',
					    btnok: '确定',
					    btncl:'取消'
					    	
					  }).on(function (e){
						  $("#example2").show();
					  });
		    		return false;
		    	}
		    }
		
		$("#uploadForm").form("submit",{ 
	    	url:"editStents.do",
	        success:function(data){ 
	        	searchWord();
	        }  
	    });
		$('#example2').modal('hide');
     	$(".modal-backdrop").remove();
	});
}
  
function deleteStents(obj) {
	Modal.confirm(
  {
    msg: "确认删除？"
  })
  .on( function (e) {
	  if(e){
			$.ajax({
				type : "post",
				url : "deleteStents.do",
				timeout : 1321231232131213123,
				data : {
					"squ" :obj,
				},
				success : function(data) {
					var obj = eval('(' + data + ')');  
					var coun = obj.count
					
					if(coun>0){
						Modal.alert(
								  {
								    msg: '部件已被使用，不能直接删除',
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

/**
 * 
  * addBatchStents:(批量添加部件)
  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
  * @author: 唐青
  * @dateTime: 2018-3-13 下午4:34:06
  * @param: @returns {Boolean}
  * @return: Boolean
 */
function addBatchStents(){
	if($("#ifEnd").val()!=0){
		$('#exampl3').modal();
		$('#btn_file_ok').unbind();
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
	}else{
		Modal.alert(
				  {
				    msg: '请选择到根目录',
				    title: '消息提示',
				    btnok: '确定',
				    btncl:'取消'
				    	
				  });
				return false;
	}
	
	
}

/*$.ajax( {
	type : 'post',
	url : 'queryAzlxMenu.do',
	dataType : 'json',
	async : false,
	data : {
		
	},
	success:function(data1){
		var list1 = data1.azlxmenu;			
		var str1 = "";
		str1 +="<option >---请选择安装方式---</option>"
		for(var i=0,len = list1.length;i<len;i++){
			
			if(ms==list1[i].MS){
				str1 +="<option value='"+list1[i].SQU+"' selected='selected'>"+list1[i].MS+"</option>"
			}else{
				str1 +="<option value='"+list1[i].SQU+"'>"+list1[i].MS+"</option>"
			}
			
		}
		$("#azfs").html(str1)						
	}
});*/
//数据库类型
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
    	gridload($(this).attr("tag"),$("#pd").val());
    	$("#pd").val($("#pd").val())
	});
    $(".pagePk").unbind();
    $(".pagePk").bind('click',function(){
    	gridload($(this).attr("tag"),$("#pd").val());
    	$("#pd").val($("#pd").val())
	});
}	

function checkInfo(){
	var bjmc = $("#bjmc").val();
	var cppm = $("#cppm").val();
	var bjxh = $("#bjxh").val();
	var cbdj = $("#cbdj").val();
	var edhl = $("#edhl").val();
	var jldw = $("#jldw").val();
	var lxcs = $("#lxcs").val();
	if(bjmc==""||bjmc==null){
		Modal.alert(
				  {
				    msg: '请填写部件名称',
				    title: '消息提示',
				    btnok: '确定',
				    btncl:'取消'
				    	
				  }).on(function (e){
					
					  $("#example2").show();
				  });
	    		return false;
	}
	if(cppm==""||cppm==null){
		Modal.alert(
				  {
				    msg: '请填写产品编码',
				    title: '消息提示',
				    btnok: '确定',
				    btncl:'取消'
				    	
				  }).on(function (e){
					  $("#example2").show();
				  });
	    		return false;
	}
	if(bjxh==""||bjxh==null){
		Modal.alert(
				  {
				    msg: '请填写部件型号',
				    title: '消息提示',
				    btnok: '确定',
				    btncl:'取消'
				    	
				  }).on(function (e){
					  $("#example2").show();
				  });
	    		return false;
	}
	if(cbdj==""||cbdj==null){
		Modal.alert(
				  {
				    msg: '请填写成本单价',
				    title: '消息提示',
				    btnok: '确定',
				    btncl:'取消'
				    	
				  }).on(function (e){
					  $("#example2").show();
				  });
	    		return false;
	}
	if(edhl==""||edhl==null){
		Modal.alert(
				  {
				    msg: '请填写额定耗量',
				    title: '消息提示',
				    btnok: '确定',
				    btncl:'取消'
				    	
				  }).on(function (e){
					  $("#example2").show();
				  });
	    		return false;
	}
	if(jldw==""||jldw==null){
		Modal.alert(
				  {
				    msg: '请填写计量单位',
				    title: '消息提示',
				    btnok: '确定',
				    btncl:'取消'
				    	
				  }).on(function (e){
					  $("#example2").show();
				  });
	    		return false;
	}
	if(lxcs==""||lxcs==null){
		Modal.alert(
				  {
				    msg: '请填写力学参数',
				    title: '消息提示',
				    btnok: '确定',
				    btncl:'取消'
				    	
				  }).on(function (e){
					  $("#example2").show();
				  });
	    		return false;
	}
}

	
