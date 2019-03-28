$(function(){

	$(".registerform").Validform({
		tiptype:function(msg,o,cssctl){
			var objtip=$("#msg");
			cssctl(objtip,o.type);
			objtip.text(msg);
		},
		callback:function(form){
			var typeId=$("#typeId").val();
			if (typeId==1) {
				addApp();
			}else{
				editApp();
			}
			return false;
		},
		datatype:{//传入自定义datatype模块【方式二】;
		"z2-4" : /^[\u4E00-\u9FA5\uf900-\ufa2d]{2,4}$/
			}
	});
	$(".doregisterform").Validform({
		tiptype:function(msg,o,cssctl){
			var objtip=$("#domsg");
			cssctl(objtip,o.type);
			objtip.text(msg);
		},
		callback:function(form){
			var typeId=$("#doType").val();
			if (typeId==1) {
				add_Do();
			}else{
				edit_Do();
			}
			return false;
		},
		datatype:{//传入自定义datatype模块【方式二】;
			"z2-4" : /^[\u4E00-\u9FA5\uf900-\ufa2d]{2,4}$/
		}
	});
	$("#preview").hide();
	loadbank();
	searchWord();
	$("#app_select").toggle(function(){
		$("[name='nice-select'] ul").hide();
	},function(){
		$("[name='nice-select'] ul").show();
	});

});
var root = $("#root").attr("value");
function gotoMag(num){
	if(num==1){
		window.location.href=root+"gotoSystemSetIndex.do";
	}
	
}

function borderIcon(id){
	var n = $(".desktop_icon img");
	for(var i=0; i<n.length; i++){
		$(".desktop_icon img:eq("+i+")").css({"background-color":""});
	}
	 $("#"+id).css("background-color","#369bd7");	
}

function parentload(obj){
	$("#head_title").text($(obj).attr("title"));
	$("#mainSqu").val($(obj).attr("id"));
	$("#input_search").attr("value","关键字搜索");
	loadMenu(obj);
	if($(obj).attr("tier")== 2){
		$("#ality_content").hide();
		$("#pageDiv").hide();
		$("#reqGrid").show();
		sjxGridload();
		$("#level").val(2);
		$("#doSqu").val($(obj).attr("id"));
		$("#doParSqu").val($(obj).attr("id"));
	}
	if($(obj).attr("tier")== 1){
		searchWord();
		$("#reqGrid").hide();
		$("#ality_content").show();
		$("#pageDiv").show();
		$("#level").val(1);
	}
}
function add_Do(){
	$("#doForm").form("submit", {    
	    url:'addAlityUrl.do',
	    onSubmit: function(){
	    },    
	    success:function(data){
	    	if(data.state == 1){
    		Modal.alert(
  				  {
  				    msg: '请求地址已存在！',
  				    title: '消息提示',
  				    btnok: '确定',
  				    btncl:'取消'
  				  });
	    	}else{
	    		sjxGridload();
		    	 $("#doWin").click(); 
	    	}
	    	
	    },
		error : function(data) {
		Modal.alert(
				  {
				    msg: '新增功能请求出错！',
				    title: '消息提示',
				    btnok: '确定',
				    btncl:'取消'
				  });

		}
	});  
}
function edit_Do(){
	$("#doForm").form("submit", {    
	    url:'editAlityUrl.do',
	    onSubmit: function(){
	    	
	    },    
	    success:function(data){
    		sjxGridload();
	    	 $("#doWin").click(); 
	    },
		error : function(data) {
		Modal.alert(
				  {
				    msg: '编辑功能请求出错！',
				    title: '消息提示',
				    btnok: '确定',
				    btncl:'取消'
				  });

		}
	});  
}
var reqGrid;
function sjxGridload(){
	reqGrid=$("#reqGrid").uiGrid(
				{
					url : "fetchAlityUrl.do",
					rowNum : 10,//每页显示记录数
					columns : [  {
						field : 'SQU',
						title : '编号',
						width : 120
					}, {
						field : 'NAME',
						title : '名称',
						width : 120
					}, {
						field : 'URL',
						title : '地址',
						width : 180
					}, {
						field : 'DESCB',
						title : '描述',
						width : 220
					}],
					divId : "#reqGrid",
					showPage : 5,//显示
					showEdit : true,
					showDelete : true,
					showEditEvent : showEditDoEvent,
					showDeleteEvent : showDeleteDoEvent,
					jsonPager : {
						root : "datas",// 当前页数据
						records : "rowCount",//总记录数
						currentPage : 1,//当前访问页
						total : "pageCount"//总页数
					},
				data:{
					squ:$("#mainSqu").val()
					
				}
				});	
	}

function showEditDoEvent(row){
	$("#doName").attr("value",row.NAME);
	$("#doUrl").attr("value",row.URL);
	$("#doDesc").attr("value",row.DESCB);
	$("#doType").val(2);
	$("#doexample").modal();
	$("#doTitle").html("功能请求编辑");
	$("#crentSqu").attr("value",row.SQU);
	
}
function showDeleteDoEvent(row){
		
		Modal.confirm(
	  {
	    msg: "确认删除此功能请求吗？"
	  })
	  .on( function (e) {
		  if(e){
			  $.ajax({
					type : "post",
					url : "delAlityUrl.do",
					timeout : 1321231232131213123,
					data : {
						squ:row.SQU,
						doSqu:$("#doParSqu").val()
					},
					success : function(data) {
						sjxGridload();
					},
					error : function() {
					Modal.alert(
					  {
					    msg: '删除功能请求出错！',
					    title: '消息提示',
					    btnok: '确定',
					    btncl:'取消'
					  });
					}
				});
		  }
	  });
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
			url : 'fetchParentMenu.do',
			timeout : 1321231232131213123,
			dataType:"json",
			async : false, //是否发送异步请求	
			data:{
				squ:squ
			},
			success : function(data) {
				var html = "";
				if(data.row[0]!=undefined){
					
					for(var i = 0;i < data.row.length; i++){
						
							html+=" <li style='display:block; margin-left:15px;'>";
							html+=" <a href='javascript:void(0);'  style='color:#3f9fd9;' class='ajax-link' ";
							html+=" flag='1' id='"+data.row[i].SQU+"'  tier='"+data.row[i].TIER+"'  title ='"+data.row[i].NAME+"' isEnd = "+data.row[i].ISEND+" onclick='parentload(this);'>";
							html+=" "+data.row[i].NAME+"</a>";		
							if(data.row[i].ISEND<1){
								html+=" <ul class='topnav' >";
								html+=" </ul>";
							}
							html+=" </li>";
					}
//					if(squ==0){
//						$(obj).parent().parent().find("ul").html(html);
//						$(obj).parent().parent().find("ul").accordion({
//							accordion:false,
//							speed: 300,
//							closedSign: '[+]',
//							openedSign: '[-]'
//						});
//					}else{
					$(obj).parent().find("ul").html(html);
					$(obj).next("ul").accordion({
							accordion:false,
							speed: 300,
							closedSign: '[+]',
							openedSign: '[-]'
						});
					}
			},
			error : function() {
				Modal.alert(
						  {
						    msg: '系统功能加载出错！',
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

//加载应用库
function loadbank(){
	$.ajax({
		type : "post",
		url : "fetchImageLibrary.do",
		timeout : 1321231232131213123,
		dataType:"json",
		success : function(data) {
		var icon="";
		for(var i = 0; i<data.row.length;i++){
			icon+="<li> <img src='"+path+""+data.row[i].img+"' style='width:50px;height:50px;' />"+data.row[i].img+"</li>";
		}
		$("#app_list").html(icon);
		$("[name='nice-select']").click(function(e) {
			$("[name='nice-select']").find('ul').hide();
			$(this).find('ul').show();
			e.stopPropagation();
		});
		$("[name='nice-select'] li").hover(function(e) {
			$(this).toggleClass('on');
			e.stopPropagation();
		});
		$("[name='nice-select'] li").click(function(e) {
				var val = $(this).text();
				var dataVal = $(this).attr("data-value");
				$(this).parents('[name="nice-select"]').find(
						'input').val(val);
				$("#preview").show();
				var $img_src = $(this).find("img").attr("src");
				$("#preview").html("<img width='80px' id='imghead' style='width:50px;height: 50px;' src="+$img_src+">");
				$("#appLibrary").val($img_src);
				$("[name='nice-select'] ul").hide();
				e.stopPropagation();
				var icon = "<i id='text_icon' style='display:block;' class="+dataVal+"></i>";
			});
		},
		error : function() {
		}
	});
}
/**
 * 
 * @param selectId 下拉框ID
 */
function loadOption(selectId,address){
	$.ajax({
		url:'fetchParentMenu.do',
		type:'post',
		dataType:'json',
		async : false,
	
		success:function(data){
			//下拉列表类容
			var selectHtml="";
			for ( var i = 0; i < data.row.length; i++) {
					var isselected ="";
					if(selectId!=""&&data.row[i].SQU == selectId){
						isselected="selected";
					}
					selectHtml+="<option value='"+data.row[i].SQU+"' "+isselected+">"+data.row[i].NAME+"</option>";
			
			}
			$('#'+address).html(selectHtml);
		}
	});
}




var path ="resource/images/left/";


	var pageNo = 1;
	var grid ;
	var aData ;

		//编辑功能信息
		function showEditEvent(index){
			var row = aData[index];
			if (row != undefined) {
					$("#htmlTitle").html("编辑功能信息");
					$("#example").modal();
					$("#preview").hide();
					$("#crentSqu").val(row.SQU);
					$("#appForm").form("clear");
					$("#alityName").val(row.NAME);
					$("#entryUrl").val(row.ENTRYURL);
					var text = "";
					if(row.ISHIDDE == 0){
						text+="<option value='0' selected='selected'>显示</option><option value='1'>隐藏</option>";
					}
					if(row.ISHIDDE == 1){
						text+="<option value='0'>显示</option><option  selected='selected' value='1'>隐藏</option>";
					}
					$("#state").html(text);
					$("#dispOrder").val(row.DISPORDER);
					$("#divID").val(row.DIVID);
					$("#alitySqu").val(row.SQU);
					loadOption(row.MAINSQU,"placeItem");
					$("#td_tag").show();
					$("#td_sgin").show();
					$("#typeId").val(0);
					
			}
		}
		function editApp(){
			$("#appForm").form("submit", {    
			    url:'editAlityMenu.do',
			    onSubmit: function(){
			    	if($("#upload").val().lastIndexOf(".")!=-1){
			    	var fileName = $("#upload").val();
			    	var fileType = fileName.substring(fileName.lastIndexOf(".")+1,fileName.length).toLowerCase();
			    	
			    	var suppotFile = new Array();
			    	suppotFile[0] = "gif";
			    	suppotFile[1] = "bmp";
			    	suppotFile[2] = "jpg";
			    	suppotFile[3] = "png";
			    	for(var i = 0;i<suppotFile.length;i++){
			    		if(suppotFile[i]== fileType ){
			    			return true;
			    		}else{
			    			continue;
			    		}
			    	}
			    	Modal.alert(
					  {
					    msg: '不支持的文件类型'+fileType,
					    title: '消息提示',
					    btnok: '确定',
					    btncl:'取消'
					  });
			    	return false;
			    	}
			    },    
			    success:function(data){
			    	 $("#closeWin").click(); 
			    	 searchWord();
			    },
				error : function(data) {
					Modal.alert(
					  {
					    msg: '功能请求修改出错！',
					    title: '消息提示',
					    btnok: '确定',
					    btncl:'取消'
					  });

				}
			});  
		}
		
			
		//删除app功能
		function showDeleteEvent(index){
			var row = aData[index];
			if (row != undefined) {
				Modal.confirm(
			  {
			    msg: "确认删除此功能吗？"
			  })
			  .on( function (e) {
				  if(e){
					$.ajax({
						type : "post",
						url : "delAlityMenu.do",
						timeout : 1321231232131213123,
						dataType:'json',
						data : {
							squ:row.SQU
						},
						success : function(data) {
							if(data.state == 0){
								searchWord();
							}
							if(data.state == 1){
								Modal.alert(
								  {
								    msg: '功能下存在请求,请先删除请求！',
								    title: '消息提示',
								    btnok: '确定',
								    btncl:'取消'
								  });
							}
						},
						error : function() {
							Modal.alert(
							  {
							    msg: '删除功能信息出错！',
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
		
		//新增app功能
		function appRegister(){
			var tier = $("#level").attr("value");
			if(tier == 1){
				$("#htmlTitle").html("功能配置");
				$("#appForm").form("clear");
				$("#preview").hide();
				$("#example").modal();
				$("#typeId").val(1);
				$("#state").val(0);
				$("#main_Squ").attr("value",$("#mainSqu").val());
				fetchOrder();
				$("#td_tag").hide();
				$("#td_sgin").hide();
			
			}
			if(tier == 2){
				$("#doType").val(1);
				$("#doexample").modal();
				$("#doTitle").html("功能请求配置");
				$("#doName").attr("value","");
				$("#doUrl").attr("value","");
				$("#doDesc").attr("value","");
				$("#doParSqu").attr("value",$("#mainSqu").val());
			}
		}
		
		
		function addApp(){
			$("#appForm").form("submit", {    
			    url:'addAlityMenu.do',
			    onSubmit: function(){
			    	if($("#upload").val().lastIndexOf(".")!=-1){
			    	var fileName = $("#upload").val();
			    	var fileType = fileName.substring(fileName.lastIndexOf(".")+1,fileName.length).toLowerCase();
			    	
			    	var suppotFile = new Array();
			    	suppotFile[0] = "gif";
			    	suppotFile[1] = "bmp";
			    	suppotFile[2] = "jpg";
			    	suppotFile[3] = "png";
			    	for(var i = 0;i<suppotFile.length;i++){
			    		if(suppotFile[i]== fileType ){
			    			return true;
			    		}else{
			    			continue;
			    		}
			    	}
			    	Modal.alert(
					  {
					    msg: '不支持的文件类型'+fileType,
					    title: '消息提示',
					    btnok: '确定',
					    btncl:'取消'
					  });
			    	return false;
			    	}
			    },    
			    success:function(data){
			    	var obj = eval("("+data+")");
			    	if(obj.state == 1){
			    		Modal.alert(
			  				  {
			  				    msg: '功能菜单,或请求地址已存在!',
			  				    title: '消息提示',
			  				    btnok: '确定',
			  				    btncl:'取消'
			  				  });
			    	}
			    	if(obj.state == 0){
			    		$("#closeWin").click();
			    		searchWord();
			    	}
			    },error : function(data) {
					Modal.alert(
					  {
					    msg: '功能菜单注册出错！',
					    title: '消息提示',
					    btnok: '确定',
					    btncl:'取消'
					  });

				}
			});  
		}
		
		function loadCategory(squ,name){
			$("#mainSqu").val(squ);
			$("#head_title").text(name);
			pageNo=1;
			pageSize=5;
			top=0;
			searchWord();	
			
		}
		function keyDown(e){ 
			 var keycode = 0;
			 keycode = e.keyCode;
			 if (keycode == 13 ) //回车键是13
			 {
				 searchWord();
			 }
		}
		/*****关键字检索****/
		var query="";
		function searchWord(){
			var word = $("#input_search").attr("value");
			if (word=="关键字搜索") {
				query ="";
			}else{
				query=" AND A.NAME LIKE '%"+word+"%'";
			} 
			loadAlityMenu(pager);
		}
		
		var records=0;
		var currentPage=0;
		var total=0;
		var pageShowPage=5;//显示多少个人分页标签
		var pageRowNum=5;//每页显示记录数
		var pager=1;
		// 加载app功能列表
		function loadAlityMenu(pager){
				var html="";
			$.ajax( {
				type : 'post',
				url : 'fetchAlityMenu.do',
				dataType : 'json',
				async : false,
				timeout : 1321231232131213123,
				data : {
					squ:$("#mainSqu").val(),
					rows:pageRowNum,
					page:pager,
					query:query
				},
				success : function(data) {
					$("#reqGrid").hide();
					$("#ality_content").show();
					$("#pageDiv").show();
					if(data.rows.length != 0){
						$("#pageDiv").show();
						aData = data.rows;
					}
					records=data.total;
					currentPage=data.pageNumber;
					total = data.pageCnt;
					pageRowNum=data.pageSize;
					if(data.rows.length==0){
						$("#box_body").html("<tr><td colspan='5'><div style='color:red;width: 100px;margin: 20px auto;'>暂无菜单功能！</div></td></tr>");
						$("#pageDiv").hide();
						top=1;
					}
				for(var i = 0;i<data.rows.length;i++){
					
					html+=" <tr>";
					html+=" <td class='box_img'>";
					html+=" <img class='dashboard-avatar' src='"+path+data.rows[i].IMGURL+"'></td>";
					html+=" <td class='box_desc'><strong>"+data.rows[i].NAME+"</strong>";
					html+=" <p>"+data.rows[i].ENTRYURL+"</p></td>";
					
					html+=" <td class='box_rate'>类型："+data.rows[i].TYPENAME+"</td>";
					var play_text="";
					if(data.rows[i].ISHIDDE== 0){
						play_text = "显示";
					}
					if(data.rows[i].ISHIDDE== 1){
						play_text = "隐藏";
					}
					html+=" <td class='box_rate'>状态："+play_text+"</td>";
					html+=" <td class='box_rate'>";
					html+=" <span tag='0' class='wk-uigrid'><a title='下一级' onclick='showViewEvent("+i+");' href='javascript:void(0);'> <i class='icon icon-black icon-newwin'></i></a></span>&nbsp;";
					html+=" <span tag='0' class='ek-uigrld'><a title='编辑' onclick='showEditEvent("+i+");' href='javascript:void(0);'><i class='icon-edit icon-black'></i></a></span>&nbsp;";
					html+=" <span tag='0' class='ek-uigrld'><a title='删除' onclick='showDeleteEvent("+i+");' href='javascript:void(0);'><i class='icon-trash icon-black'></i></a></span>";
					html+=" </td></tr>";                                  
					$("#box_body").html(html);
					pageDiv(records,total,currentPage);
				}
				},
				error : function() {
					Modal.alert(
					  {
					    msg: '功能菜单加载出错！',
					    title: '消息提示',
					    btnok: '确定',
					    btncl:'取消'
					  });
				}
			});
			}
		
		/**加载菜单下请求地址
		 * @param index 数组下标
		 */
		function showViewEvent(index){
			var row = aData[index];
			if(row!=undefined){
				$("#ality_content").hide();
				$("#pageDiv").hide();
				$("#reqGrid").show();
				$("#mainSqu").val(row.SQU);
				sjxGridload();
				$("#level").val(2);
				$("#doParSqu").attr("value",row.SQU);
			}
		}
		//最大排序编号
		function fetchOrder(){
			$.ajax({
				type : "post",
				url : "fetchMaxOrder.do",
				timeout : 1321231232131213123,
				dataType:'json',
				data : {
					squ:$("#mainSqu").val()
				},
				success : function(data) {
					$("#dispOrder").val(data.row);
				},
				error : function() {
					Modal.alert(
					  {
					    msg: '加载功能编号信息出错！',
					    title: '消息提示',
					    btnok: '确定',
					    btncl:'取消'
					  });
				}
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
		    	loadAlityMenu($(this).attr("tag"));
			});
		    $(".pagePk").unbind();
		    $(".pagePk").bind('click',function(){
		    	loadAlityMenu($(this).attr("tag"));
			});
		}	


			