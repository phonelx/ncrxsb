var dbsSqu = 0;
//---------------------------- 树形结构 start
var zTree1;
var setting= {
	async:true,	//是否发送异步请求
	asyncUrl:"doFindObjects.do",//数据加载url
	callback: {
		click:zTreeOnClick //注册节点单击监听事件
	}
};
// 根节点{"id":"101","isParent":"true","name":"dd[0]","optFlag":""}
var zNodes = [{"id":"0","name":"数据源","isParent":"true"}];
// 加载 树
function showTree(){
	zTree1 = $("#objects").zTree(setting, zNodes);
	zTree1.refresh();
}
// 监听节点单击事件
function zTreeOnClick(event,treeId,treeNode){
	// 显示数据源 的实体对象
	if(treeNode.level=='1' && treeNode.parentNode.id == '0') {
		dbsSqu = treeNode.id ;
		$('#entityTable').show();
		$('#fieldMgr').hide();
		$("#querydbID").val("DATASOURCESQU,"+dbsSqu);
		$("#tabID").val("");
		showRegistedGrid(dbsSqu,1);
		showNotRegGrid(dbsSqu,1);
	}
	// 显示 实体对象的 字段
	if(treeNode.level=='2' && treeNode.parentNode.parentNode.id =='0') {
		// 实体ID
		var idStr = treeNode.id ;
		var id = idStr.substring(idStr.lastIndexOf('_')+1,idStr.length);
		// 实体名称
		var nameStr = treeNode.name ;
		var name = nameStr.substr(nameStr.indexOf('(')+1).replace(")","");
		$("#tabID").val("ENTITYSQU,"+id);
		$("#querydbID").val("");
		$('#entityTable').hide();
		$('#fieldMgr').show();
		showFieldGridReg(id,name,treeNode.parentNode.id,treeNode);
	}
}


function parentload(obj){
	$("#backLi").hide();
	//是否是最后一级
	var isEnd=$(obj).attr("isEnd");
	dbsSqu=$(obj).attr("id");
	$("#dbsSqu").val(dbsSqu);
	var dbsName=$(obj).attr("title");
	if(isEnd==0){
		$.ajax({
			url:'doFindObjects.do',//数据加载url
			type:"post",
			dataType:"json",
			data:{
				"id":dbsSqu,
				"level":1
			},
			async:true,
			success:function(data){
				var html = "";
				if(data!=undefined){
					for(var i = 0;i < data.length; i++){
						html+=" <li style='display:block; margin-left:15px;'>";
						html+=" <a href='javascript:void(0);'  style='color:#3f9fd9;' class='ajax-link'  flag='1' id='"+data[i].id+"' tag=''  title ='"+data[i].name+"' isEnd = '1' onclick='parentload(this);'>";
						html+=" <i class='icon-darkgray icon-search'></i>&nbsp;"+data[i].name+"</a>";		
						html+=" </li>";
					}
					if(dbsSqu==0){
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
			}
		});
	}else{
		$('#checkInput').show();
		var data = {
			dbsSqu : dbsSqu,
			dbsName : dbsName,
		};
		dataEntity=data;
		$("#title2").html(dbsName);
		param="'VIEW','TABLE'";
//		getSourceTables(data);
//		uigridload(data);
		
		showRegistedGrid(dbsSqu,1);
		showNotRegGrid(dbsSqu,1);
		$("#ngrid").show();
		$("#uigridtltie").show();
		$("#ngridtltie").show();
		$("#ngridtable").show();
	}
}
//---------------------------------------  树形结构 end
	
/**
 * 显示已经注册 的实体列表
 * @param {} dbsSqu
 */
function showRegistedGrid(dbsSqu,pageNo) {
//	$('#registed').datagrid({
//		fit : true,
//		nowrap : false,
//		singleSelect : true,
//		collapsible : false,
//		url : 'doGetRegistedEntity.do?dbsSqu='+dbsSqu,
//		idField : 'squ',
//		rownumbers : true,
//		remoteSort : false,
//		columns : [[{
//					title : '实体名',
//					field : 'name',
//					width : 200,	
//					align:'center',
//					sortable:true
//				}, {
//					title : '显示标题',
//					field : 'title',
//					width : 200,	
//					align:'center',
//					sortable:true,
//					formatter:function(value){
//						return value.substring(0,value.indexOf("["));
//					}
//				}, {
//					title : '注册时间',
//					field : 'regDateTime',
//					align:'center',
//					width : 180,	
//					sortable:true
//				},{
//					title : '实体类型',
//					field : 'isView',
//					align:'center',
//					width : 80,	
//					sortable:true,
//					formatter : function(value, rec) {
//						return 1 == rec.isView ? '视图' : '表';
//					}
//				},{
//					title : '是否预警查询',
//					field : 'isquery',
//					align:'center',
//					width : 80,	
//					sortable:true,
//					formatter : function(value, rec) {
//						return 1 == rec.isquery ? '否' : '是';
//					}
//				},{
//					title : '描述',
//					field : 'descb',
//					align:'center',
//					width : 200,	
//					sortable:true
//				} 
//		]],
//		toolbar : [{
//					id : 'btn_e_del',
//					text : '注  销',
//					iconCls : 'icon-remove',
//					handler : function() {
//						var selected = $('#registed').datagrid('getSelected');
//						if (selected){
//								if(confirm('确认注销？'))deleteEntity(selected.squ,selected.title,treeNode);
//						}
//						else
//							alert('请选择需要注销的实体！');
//					}
//				}, '-', {
//					id : 'btn_e_Edit',
//					text : '编  辑',
//					iconCls : 'icon-edit',
//					handler : function() {
//						var selected = $('#registed').datagrid('getSelected');
//						if (selected) {
//							$('#btn_entity_ok').unbind();
//							$('#btn_entity_ok').click(function(){
//								if($('#edescb').validatebox('isValid')&&$('#edescb').validatebox('isValid')){
//									editEntity(selected.squ,treeNode);
//								}
//								
//							});
//							// 弹出编辑窗口
//							$('#entity_add').window('open');
//							$('#entity_add').panel('setTitle','实体编辑');
//							// 初始化表单
//							$('#ename').val(selected.name);
//							$("input:radio[name=isquery][value="+selected.isquery+"]").attr("checked","checked");
//							$('#etitle').val(selected.title.substring(0,selected.title.indexOf("[")));
//							//$('#EntityNameOldValue').val(selected.title.substring(0,selected.title.indexOf("[")));
//							$('#edescb').val(selected.descb);
//							//$("#Nowtitname").val(selected.title.substring(0,selected.title.indexOf("[")));
//							
//						} else {
//							alert('请选择需要编辑的实体！');
//						}
//					}
//				}]
//
//	});
//	//清空 列表选中项
//	$('#registed').datagrid('clearSelections');
	
	$("#registed").uiGrid({
		url : "doGetRegistedEntity.do",
		rowNum : 5,//每页显示记录数
		columns : [ {
			title : '实体名',
			field : 'name',
			width : 200
		}, {
			title : '显示标题',
			field : 'title',
			width : 130,
			formatter:function(value){
				return value.substring(0,value.indexOf("["));
			}
		}, {
			title : '注册时间',
			field : 'regDateTime',
			width : 80
		},{
			title : '实体类型',
			field : 'isView',
			width : 80,
			formatter : function(value, rec) {
				return 1 == rec.isView ? '视图' : '表';
			}
		},{
			title : '描述',
			field : 'descb',
			width : 100
		} ],
		divId : "#registed",
		showPage : 5,//显示showNewwin : true,
		showNewwin : true,
		showEdit : true,
		showDelete : true,
		showNewwinEvent : showNewwinEvent,
		showEditEvent : showEditEntity,
		showDeleteEvent : deleteEntity,
		jsonPager : {
			root : "datas",// 当前页数据
			records : "rowCount",//总记录数
			currentPage : pageNo,//当前访问页
			total : "pageCount"//总页数
		},
		data : {
			searchKey : "",
			dbsSqu:dbsSqu
		}
	});
}

/**
 * 显示实体表的下一级
 */
function showNewwinEvent(row) {
//	showFieldGridReg(entitySqu, name, dbsSqu, treeNode);
	document.getElementById("entityTable").style.display = "none";
	document.getElementById("fieldMgr").style.display = "block";
	showFieldGridReg(row.squ,row.name,row.datasourceSqu,1);
}

/**
 * 显示未注册实体列表
 */
function showNotRegGrid(dbsSqu,pageNo){
//$('#notreg').datagrid({
//				fit : true,
//				nowrap : false,
//				singleSelect : true,
//				collapsible : false,
//				url : encodeURI('doGetEntityByDbs.do?dbsName='+dbsName+'&dbsSqu='+dbsSqu),
//				// idField:'squ',
//				rownumbers : true,
//				remoteSort : false,
//				columns : [[{
//							title : '实体名',
//							field : 'name',
//							width : 350,
//							sortable:true
//						}, {
//							title : '实体类型',
//							field : 'type',
//							width : 120,
//							sortable:true,
//							formatter : function(value, rec) {
//								return 'VIEW' == rec.type?'视图':'表';
//							}
//						}]],
//				toolbar : [{
//							id : 'btn_e_add',
//							text : '注  册',
//							iconCls : 'icon-add',
//							handler : function() {
//								//绑定关闭窗口事件--jk
//								setWindowcClose('#entity_add');
//								var selected = $('#notreg').datagrid('getSelected');
//								if (selected) {
//									var isView = selected.type == 'VIEW'? '1': '0';
//									$('#btn_entity_ok').unbind();
//									$('#btn_entity_ok').click(function(){
//									if($('#etitle').validatebox('isValid')&&$('#edescb').validatebox('isValid')){
//										addEntity(isView,treeNode1);
//										}
//									});
//									$('#entity_add').window('open');
//									$('#entity_add').panel('setTitle','实体注册');
//									$("input:radio[name=isquery][value='0']").attr("checked","checked");
//									$('#ename').val(selected.name);
//									$('#edescb').val('');
//                                    getEntityComments(selected.name,dbsSqu);
//									$('#etitle').focus();
//									$('#essential').combobox('setValue','');
//								} else {
//									alert('请选择需要注册的实体！');
//								}
//
//							}
//						}]
//
//			});
//// 清空列表选中项
//$('#notreg').datagrid('clearSelections');

	$("#notreg").uiGrid({
		url : "doGetEntityByDbs.do",
		rowNum : 5,//每页显示记录数
		columns : [ {
			title : '实体名',
			field : 'name',
			width : 350
		}, {
			title : '实体类型',
			field : 'type',
			width : 120,
			formatter : function(value, rec) {
				return 'VIEW' == rec.type?'视图':'表';
			}
		} ],
		divId : "#notreg",
		showPage : 5,//显示
		showEdit : true,
		showEditEvent : showAddEntityBox,
		jsonPager : {
			root : "datas",// 当前页数据
			records : "rowCount",//总记录数
			currentPage : pageNo,//当前访问页
			total : "pageCount"//总页数
		},
		data : {
			searchKey : "",
			dbsSqu:dbsSqu
		}
	});
}


/**
 * 
 */
function showAddEntityBox(row){
	document.getElementById("clearEntityForm").click();
	document.getElementById("entityTitleName").innerHTML = "注册实体表";
	document.getElementById("ename").value = row.name;
	
	$("#entity_add").modal();
	
	$('#btn_entity_ok').unbind();
	$('#btn_entity_ok').click(function() {
		var isView = row.type == 'VIEW'? '1': '0';
		addEntity(isView);
	});
}
/**
 * 注册实体
 */
function addEntity(isView) {
//	zTree1.expandNode(treeNode1, true, false);
	$.ajax({
		type : 'post',
		data : {
			'entity.name' : $('#ename').val(),
			'entity.title' : $('#etitle').val(),
			'entity.descb' : $('#edescb').val(),
			'entity.isquery':$("input:radio[name=isquery]:checked").val(),
			'entity.isView' : isView,
			'entity.datasourceSqu' : dbsSqu
		},
		url : 'doAddEntity.do',
		success : function(data) {
			if(data == "-2"){// 已经被注册
				document.getElementById("closeEntityBox").click();
				alert('该实体已经被注册！');
			}else{
//				$('#registed').datagrid('reload');
//				$('#notreg').datagrid('reload');
//				$('#notreg').datagrid('clearSelections');
				
				showRegistedGrid(dbsSqu,1);
				showNotRegGrid(dbsSqu,1);
				document.getElementById("closeEntityBox").click();
				// 刷新树
				//zTree1.reAsyncChildNodes(treeNode1, "refresh");
				//zTree1.expandNode(treeNode1, true, false);
//				var newNode = [{id:'entity_'+data,name:$('#etitle').val()+"("+$('#ename').val()+")",isParent:'true'}] ;
//				zTree1.addNodes(treeNode1,newNode);
			}
		},
		error : function(data) {
			alert("注册出错！");
		}
	});
}
/**
 * 删除实体
 */
function deleteEntity(row) {
//	zTree1.expandNode(treeNode1, true, false);
//	$.ajax({
//		type : 'post',
//		data : {
//			'entity.squ' : squ,
//			'entity.title' : title
//		},
//		url : 'doDeleteEntity.do',
//		success : function(data) {
//			if(data=='true'){// 删除成功
//				//刷新列表
//				$('#registed').datagrid('reload');
//				$('#notreg').datagrid('reload');
//				$('#registed').datagrid('clearSelections');
//				// 刷新树
//				var deleteNode = zTree1.getNodeByParam('id','entity_'+squ) ;
//				zTree1.removeNode(deleteNode);
//			}else{
//				alert('当前实体正在被使用不能注销！');
//			}
//			
//		},
//		error : function(data) {
//			alert("删除出错！");
//		}
//	});
	if (confirm('确认删除？')) {
		$.ajax({
			type : 'post',
			data : {
				'entity.squ' : row.squ,
				'entity.title' : row.title
			},
			url : 'doDeleteEntity.do',
			success : function(data) {
				if(data=='true'){// 删除成功
					//刷新列表
//					$('#registed').datagrid('reload');
//					$('#notreg').datagrid('reload');
//					$('#registed').datagrid('clearSelections');
					
					showRegistedGrid(dbsSqu,1);
					showNotRegGrid(dbsSqu,1);
				}else{
					alert('当前实体正在被使用不能注销！');
				}
			},
			error : function(data) {
				Modal.alert({
					msg : '删除出错！',
					title : '消息提示',
					btnok : '确定',
					btncl : '取消'
				});
			}
		});
	};
}

/**
 * 显示编辑实体BOX
 */
function showEditEntity(row){
	document.getElementById("entityTitleName").innerHTML = "编辑实体表";
	document.getElementById("ename").value = row.name;
	document.getElementById("etitle").value = row.title;
	document.getElementById("edescb").value = row.descb;
	
	$("#entity_add").modal();
	
	$('#btn_entity_ok').unbind();
	$('#btn_entity_ok').click(function() {
		editEntity(row.squ);
	});
}
/**
 * 编辑实体
 */
function editEntity(squ) {
//	zTree1.expandNode(treeNode1, true, false);
	$.ajax({
		type : 'post',
		data : {
			'entity.squ' : squ,
			'entity.title' : $('#etitle').val(),
			'entity.descb' : $('#edescb').val(),
			'entity.isquery':$("input:radio[name=isquery]:checked").val()
		},
		url : 'doEditEntity.do',
		success : function(data) {
//				$('#entity_add').window('close');
//				$('#registed').datagrid('reload');
//				$('#registed').datagrid('clearSelections');
//				// 刷新树
//				var editNode = zTree1.getNodeByParam('id','entity_'+squ);
//				editNode.name = $('#etitle').val()+"("+$('#ename').val()+")";
//				zTree1.updateNode(editNode, false);
				
				showRegistedGrid(dbsSqu,1);
				showNotRegGrid(dbsSqu,1);
				document.getElementById("closeEntityBox").click();
		},
		error : function(data) {
			alert('编辑出错！');
		}
	});
}

function getEntityComments(entityName,dbsSqu){
    $.ajax({
        type:'post',
        async:false,
        data:{
            'entityName':entityName,
            'dbsSqu':dbsSqu
        },
        url:'getEntityComments.do',
        success:function(data){
            $('#etitle').val(data);
        }
    });
}

