(function($){
	$.fn.uiGrid = function(param){
		// 描述
		var descb="";
		//每页显示记录数
		var rowNum=5;
		//总记录数
		var records=0;
		//当前访问页
		var currentPage=0 ;
		// 总共多少页
		var total=0;
		var showPage=10;
		/*************
		 * 访问后台
		 *************/
		var resultDatas=null;
		var objInfoColName = null;
		var parModel="";
		if(param.columns != undefined){
			 if(param.columns != "" ){
					parModel=param.columns;
			 }
		}
		var option = { // 基本配置信息
			styleHeight:param.height,      			// 列表高度
			rowNum:param.rowNum,           			// 每页显示多少条记录
			strResult:param.jsonPager.root,			// 
			strRecords:param.jsonPager.records,		// 总记录数
			strCurrentPage:param.jsonPager.currentPage,// 当前访问页
			strTotal:param.jsonPager.total,			   // 总页数
			rowTitle:param.rowTitle,					// title
			data:param.data,							// 后台请求参数
			showPage:param.showPage,//一次加载显示页数
			columns:parModel,       // 列信息
			url:param.url,							    // 后台访问URL
			ondblclickEvent:param.ondblclickEvent,		// 双击事件
			completeEvent:param.completeEvent,		// 数据加载完成事件
			showView:param.showView,
			showViewEvent:param.showViewEvent,
			format: eval("("+param.format+")"),//格式化 format:"[{STATUS:'{\"1\":\"ssss\",\"2\":\"aaaa\"}'}]",
			//格式化功能按钮 formatImage:"[{ID:'<img class=\"delManual\" title=\"删除\" onclick=\"alert()\" />'}]", 
			//formatImage 只能在 ID列使用  将自动将ID号码传入alert()方法作为参数,默认方法体必须为空  如果有参数请在最后一个参数后新增逗号 alert(1,2,)
			formatImage:eval("("+param.formatImage+")") 
		} ; 
		// 页面DOM元素ID
		var domId = this.attr("id");
		
		// 行数据
		var rowObjectArray = new Array();
		
		// 工具类
		var util = {
				resultData:function(){
					return resultDatas;
				},
				reloadGrid:function(){
					this.loadGrid(currentPage);
				},
				currentPage:function(){
					return currentPage;
				},
				load:function(){
					this.loadGrid(1);
				},
			loadGrid:function(currPage){ // 加载当前页数据
				$.ajax({
					url:option.url+"?page="+currPage+"&rows="+option.rowNum,
					type:'post',
					dataType:'json',
					async : false,
					data:option.data,
					success:function(result){
						records=result.total;//总记录数
						currentPage = result.pageNumber;//当前页
						total = result.pageCnt;//总页数
						rowNum=result.pageSize;
						resultDatas = result.rows;
						descb=result.descb;
					},
					complete:function(xhr, ts){
						if(param.completeEvent!=null){
							param.completeEvent(xhr.responseText);
						}
					},
					error:function(){
						alert('uiGrid装载数据错误');
					}
				});
				this.genuiGrid();
				$("#ShowCover-"+domId).show();
				this.genPager();
				if(objInfoColName == null){
					this.genuiGridContent(domId);
				}else{
					this.genuiGridMoreContent(domId);
				}
				//遮罩结束
				setTimeout(function(){$("#ShowCover-"+domId).hide();},1000);
				//$("#ShowCover-"+domId).hide();
			},
			genuiGrid:function(){
				var strTable="<div id='ShowCover-"+domId+"' style='position:fixed; top: 0px; right:0px; bottom:0px;filter: alpha(opacity=60); background-color: #777;z-index: 1002; left: 0px; display:none;opacity:0.5; -moz-opacity:0.5;'></div><div  style='height:"+option.styleHeight+";'>" +"<div>";
				strTable+="<table id='uiGrid-"+domId+"' >";
				//thead
				strTable+="<thead><tr id='table-head-"+domId+"'></tr></thead>";
				//tbody
				strTable+="<tbody id='table-body-"+domId+"'></tbody>";
				//pager
				strTable+="</table>";
				//descb
				strTable+="<div id='descb-"+domId+"' style='display:none;'>"+descb+"</div>";
				strTable+="</div></div><div id='foot"+domId+"'></div><div id='setpage-"+domId+"'></div>";

				$('#'+domId).html(strTable);
			},
			genPager:function(){
				var outstr = "";
				showPage=option.showPage;
				var countt="";
				total=parseInt(records/10);
				if(records%rowNum>0){
					total=total+1;
				} //共"+total+"页|"+records+"条数据|第"+currentPage+"页 每页" + rowNum +"个
			        if(total<=showPage){
			                for (var count=1;count<=total;count++){
			                       if (count<10) {countt ="0"+count+"";}else{countt =""+count+"";}
			                        if(count!=currentPage){
			                             outstr = outstr + "<li tag='"+count+"' class='ck'><a href='javascript:void(0);'>"+countt+"</a></li>";
			                        }else{
			                        	 outstr = outstr + "<li class='active'><a href='javascript:void(0);'>"+countt+"</a></li>";
			                        }
			                }
			        }
			        if(total>showPage){
			                if(parseInt((currentPage-1)/showPage) == 0){
			                        outstr = outstr + "<li><a><<</a></li> ";
			                        alert(outstr)
			                        for (var count=1;count<=showPage;count++){
			                                if (count<10) {countt ="0"+count+"";}else{countt =""+count+"";}
			                                if(count!=currentPage){
			                                	outstr = outstr + "<li tag='"+count+"' class='ck'><a  href='javascript:void(0);'>"+countt+"</a></li>";
			                                	
			                                }else{
			                                	 outstr = outstr + "<li class='active'><a href='javascript:void(0);'>"+countt+"</a></li>";
			                                }
			                        }
			                        outstr = outstr+"<li tag='"+count+"' class='pk'><a href='javascript:void(0)'>>></a></li>";
			                }else if(parseInt((currentPage-1)/showPage) == parseInt(total/showPage)){
			                        outstr = outstr + "<li tag='"+(parseInt((currentPage-1)/showPage)*showPage)+"' class='pk'><a href='javascript:void(0)'><<</a></li> ";
			                        for (var count=parseInt(total/showPage)*showPage+1;count<=total;count++){
			                                if (count<10) {countt ="0"+count+"";}else{countt =""+count+"";}
			                                if(count!=currentPage){
			                                	outstr = outstr + "<li  tag='"+count+"' class='ck'><a href='javascript:void(0);'>"+countt+"</a></li>";
			                                }else{
			                                	 outstr = outstr + "<li class='active'><a href='javascript:void(0);'>"+countt+"</a></li>";
			                                }
			                        }
			                        outstr = outstr + "<li><a href='javascript:void(0)'>>></a></li>";
			                }else{
			                        outstr = outstr + "<li tag='"+(parseInt((currentPage-1)/showPage)*showPage)+"' class='pk'><a href='javascript:void(0)'><<</a></li> ";
			                        for (var count=parseInt((currentPage-1)/showPage)*showPage+1;count<=parseInt((currentPage-1)/showPage)*showPage+showPage;count++){
			                                if (count<10) {countt ="0"+count+"";}else{countt =""+count+"";}
			                                if(count!=currentPage){
			                                	outstr = outstr + "<li tag='"+count+"' class='ck'><a href='javascript:void(0);'>"+countt+"</a></li>";
			                                }else{
			                                	
			                                        outstr = outstr + "<li class='active'><a href='javascript:void(0);'>"+countt+"</a></li>";
			                                }
			                        }
			                        outstr = outstr + "<li  tag='"+count+"' class='ck'><a href='javascript:void(0);'>>></a></li>";
			   
			                }
			    }    
			        //共"+total+"页|"+records+"条数据|第"+currentPage+"页 每页" + rowNum +"个　
			  /* $("#setpage-"+domId).html("<div  align='right'><ul>" + outstr + "<a href='javascript:void(0);'>共"+total+"页/"+records+"条数据</a></li></ul></div>");
			   outstr = "";*/
			  
			    $("#"+domId+" .ck").unbind();
			    $("#"+domId+" .ck").bind('click',function(){
			    	if(($(this).attr("tag")-1)!=total){
			    		util.loadGrid($(this).attr("tag")) ;
			    	}
				});
			    
			    $("#"+domId+" .pk").unbind();
			    $("#"+domId+" .pk").bind('click',function(){
			    	if(($(this).attr("tag")-1)!=total){
			    		util.gotoPage($(this).attr("tag")) ;
			    	}
				});
			},gotoPage:function(curPage){
				currentPage = curPage;
				this.loadGrid(curPage);
				this.genPager();
			},
			genuiGridContent:function(){
				rowObjectArray =[];
				//表头
				var tHead="";
				for(var i=0;i<option.columns.length;i++){
					tHead+="<th width='"+option.columns[i].width+"'>"+option.columns[i].title+"</th>";
				}
				if(option.showView==true||option.showEdit==true||option.showDelete==true||option.showTest==true||option.showFile==true||option.showNewwin==true||option.showDownload==true||option.showMessageTX==true){
					tHead+="<th >操作</th>";
					
				}
				$('#table-head-'+domId).html(tHead);
				//表体
				var tBody="";
				for(i=0;i<resultDatas.length;i++){
					/*alert("长度"+resultDatas.length)*/
					tBody += "<tr style=\"cursor:pointer\" title='"+option.rowTitle+"' >";
					for(var j=0;j<option.columns.length;j++){
						//加上&nbsp;，防止cell无边框线
						var model = option.columns[j].field.split(".");
						var cellContent="";
						if(model.length>1){
							cellContent = (resultDatas[i][model[0]][model[1]] != "")?resultDatas[i][model[0]][model[1]]:"&nbsp;";
						}else{
							cellContent = resultDatas[i][model[0]];
							
						}
						if(cellContent!=0&&cellContent==""){
							cellContent="&nbsp";
						}
						if(option.columns[j].statusStyle!=undefined){
							var dataStyle=option.columns[j].statusStyle;
							var	sBody ="<td class='center' title='未知状态'><span class='label label-warning'>未知状态</span></td>";
							if(dataStyle.length==1){
								if(dataStyle[0].status=="this"){
									//判断title内容是否为空
									if(cellContent==""){
										sBody ="<td class='center' title='"+cellContent+"'><span>"+cellContent+"</span></td>";
									}else{
										sBody ="<td class='center' title='"+cellContent+"'><span class='"+dataStyle[0].statusClass+"'>"+cellContent+"</span></td>";
									}
								}
							}else{
								for(var f=0;f<dataStyle.length;f++){
									if(cellContent==dataStyle[f].status){
										sBody ="<td class='center' title='"+dataStyle[f].statusTitle+"'><span class='"+dataStyle[f].statusClass+"'>"+dataStyle[f].statusTitle+"</span></td>";
									}
								}
							}
							
							
							tBody+=sBody;
							}else if(option.columns[j].formatter!=undefined){
								tBody +="<td class='center'>"+option.columns[j].formatter(cellContent,resultDatas[i],i)+"</td>";
							}else{
							tBody +="<td class='center' title='"+cellContent+"'>"+cellContent+"</td>";
						}
						
						
					}
					var phtml="";
					if(option.showView==true){
						phtml+="<td class='center' title='操作'>";
						phtml+="&nbsp;<span  tag='"+i+"' class='vk-"+domId+"'><a  title='查看'  href='javascript:void(0);'> <i class='icon-zoom-in icon-black'></i></a></span>";
						phtml+="</td>";
					}
				
					tBody += phtml+"</tr>";
					rowObjectArray.push(resultDatas[i]);
				}
				$('#table-body-'+domId).html(tBody);
				$('#table-body-'+domId+' tr').each(function(i){
					$(this).click(function(){
						if(option.ondblclickEvent!=undefined){
							option.ondblclickEvent(rowObjectArray[i]);
						}
					});
				});
				
			 	$(".vk-"+domId).unbind();
			    $(".vk-"+domId).bind('click',function(){
			    	if(rowObjectArray[$(this).attr("tag")]!=undefined){
			    		option.showViewEvent(rowObjectArray[$(this).attr("tag")]);
			    	}
			    	
				});
			    
			}
		};
		// 初始加载第一页数据
		util.loadGrid(option.strCurrentPage);

		return util;
	};
})(jQuery);

