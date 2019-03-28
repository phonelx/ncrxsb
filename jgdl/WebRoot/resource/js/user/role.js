var moduleTree;// 用户模块授权树
var entityTree;// 实体授权树
var queryTree;// 查询授权树

function initRoleGrid(pageNo) {
	$("#roleGrid").uiGrid({
		url : "listRolesInPage.do",
		rowNum : 20,//每页显示记录数
		columns : [ {
			field : 'roleName',
			title : '角色名称',
			width : 300
		}, {
			field : 'descb',
			title : '描述',
			width : 300
		}, {
			field : 'isAdmin',
			title : '角色类型',
			width : 200,
			formatter : function(value, rec) {
				if (value == 1) {
					return "管理角色";
				} else {
					return "查询角色";
				}
			}
		}, {
			field : 'isDefault',
			title : '默认角色',
			width : 100,
			formatter : function(value, rec) {
				if (value == 1) {
					return "是";
				} else {
					return "否";
				}
			}
		} ,{
			field : 'isSpr',
			title : '审批人',
			width : 100,
			formatter : function(value, rec) {
				if (value == 1) {
					return "是";
				} else {
					return "否";
				}
			}
		}],
		divId : "#roleGrid",
		showPage : 5,//显示
		showEdit : true,
		showDelete : true,
		showEditEvent : showEditRole,
		showDeleteEvent : deleteRole,
		jsonPager : {
			root : "datas",// 当前页数据
			records : "rowCount",//总记录数
			currentPage : pageNo,//当前访问页
			total : "pageCount"//总页数
		},
		data : {
			searchKey : ""
		}
	});
}

/**
 * 模块授权保存时生成模块授权树的json数据
 * 
 * @return {}
 */
function getModuleNodesInJson() {
	/*
	 * easyUi tree var nodes = $('#moduleTree').tree('getChecked'); var
	 * param="["; var flag = false; for(j=0;j<nodes.length;j++){
	 * if(nodes[j].attributes.isMain=='0'){ if(flag == false){
	 * param+="{id:"+nodes[j].id+",text:'"+nodes[j].text+"',children:[]}"; flag =
	 * true; }else{
	 * param+=",{id:"+nodes[j].id+",text:'"+nodes[j].text+"',children:[]}"; } } }
	 * param+="]";
	 */

	// zTree2.6
	var nodes = moduleTree.getCheckedNodes();
	var param = "[";
	var flag = false;
	for (j = 0; j < nodes.length; j++) {
		if (nodes[j].isParent == false) {
			if (flag == false) {
				param += "{id:" + nodes[j].id + ",name:'" + nodes[j].name
						+ "',nodes:[]}";
				flag = true;
			} else {
				param += ",{id:" + nodes[j].id + ",name:'" + nodes[j].name
						+ "',nodes:[]}";
			}
		}
	}
	param += "]";
	return param;
}

/*
 * 添加角色 ------------- start
 */
/*
 * 显示添加角色BOX
 */
function showAddRole(){
	// 清空表单
	$("#clearRoleForm").click();
	document.getElementById("roleTitleName").innerHTML = "添加角色";
	
	// zTree2.6 method
	$("#scrollList").hide(); //初始化权限树时加载遮罩	
	var setting = {
		checkable: true,
		checkType: {
			"Y": "ps",
			"N": "ps"
		},
		async: true,
		//是否发送异步请求	
		asyncUrl: 'fetchAuthTree.do',
		callback: {
			asyncSuccess: function() {
				$("#scrollList").show(); //初始化权限树完成后取消遮罩
			}
		}
	};
	moduleTree = $("#moduleTree").zTree(setting,null);
	
	$("#roleDiv").modal();
	
	$('#roleBtn').unbind();
	$('#roleBtn').click(function() {
		if ($('#roleName').validatebox('isValid')&&$('#descb').validatebox('isValid')) {
			addRole();
		}
	});
}

/*
 * 执行添加角色
 */
function addRole() {
	if ($('#roleForm').form('validate') == true) {
		var nodesJsonStr = getModuleNodesInJson();

		$.ajax({
			type : 'post',
			url : 'addRole.do',
			timeout : 1321231232131213123,
			data : {
				'roleName' : $('#roleName').val(),
				'descb' : $('#descb').val(),
				'isAdmin' : $('#isAdmin').val(),
				'isSpr' : $('#isSpr').val(),
				'jsonStr' : nodesJsonStr
			},
			success : function(data) {
				$('#closeRoleBox').click();
				initRoleGrid(1);
			},
			error : function() {
				alert('添加出错！');
			}
		});
	}
}
/*
 * 添加角色 ------------- end
 */

/*
 * 编辑角色 ------------- start
 */
/*
 * 显示编辑角色BOX
 */
function showEditRole(row){
	// 设置文本参数
	document.getElementById("roleTitleName").innerHTML = "编辑角色";
	document.getElementById("roleSqu").value=row.squ;
	document.getElementById("roleName").value=row.roleName;
	document.getElementById("isAdmin").value=row.isAdmin;
	document.getElementById("descb").value=row.descb;
	// zTree2.6 method
	$("#scrollList").hide(); //初始化权限树时加载遮罩	
	var setting = {
		checkable : true,
		checkType : {
			"Y" : "ps",
			"N" : "ps"
		},
		async : true, // 是否发送异步请求
		asyncUrl : 'fetchGrantedAuthTree.do?squ='+row.squ,
		callback : {
			asyncSuccess : function() {
				$("#scrollList").show(); //初始化权限树完成后取消遮罩
			}
		}
	};
	moduleTree = $("#moduleTree").zTree(setting,null);

	$("#roleDiv").modal();

	$('#roleBtn').unbind();
	$('#roleBtn').click(function() {
		if ($('#roleName').validatebox('isValid') && $('#descb').validatebox('isValid')) {
			editRole();
		}
	});
}

/*
 * 执行编辑角色
 */
function editRole() {
	if ($('#roleForm').form('validate') == true) {
		var nodesJsonStr = getModuleNodesInJson();

		$.ajax({
			type : 'post',
			url : 'editRole.do',
			timeout : 1321231232131213123,
			data : {
				'roleVo.squ' : $("#roleSqu").val(),
				'roleVo.roleName' : $('#roleName').val(),
				'roleVo.descb' : $('#descb').val(),
				'roleVo.isAdmin' : $('#isAdmin').val(),
				'roleVo.isSpr' : $('#isSpr').val(),
				'jsonStr' : nodesJsonStr
			},
			success : function(data) {
				$('#closeRoleBox').click();
				initRoleGrid(1);
			},
			error : function() {
				alert('添加出错！');
			}
		});
	}
}
/*
 * 编辑角色 ------------- end
 */

/*
 * 删除角色 ------------- start
 */
function deleteRole(row) {
	if (confirm('确认删除吗？')) {
		$.ajax({
			type : 'post',
			url : 'deleteRole.do',
			timeout : 1321231232131213123,
			data : {
				'squ' : row.squ,
				'roleName' : row.roleName
			},
			success : function(data) {
				if (data == '删除成功') {
					initRoleGrid(1);
				} else {
					alert(data);
				}
			},
			error : function() {
				alert('删除角色信息出错！');
			}
		});
	}
}
/*
 * 删除角色 ------------- end
 */

/**
 * 实体授权保存时生成实体授权树的json数据
 * 
 * @return {}
 */
function getEntityNodesInJson() {
	/*
	 * easyUi tree method var nodes = $('#entityTree').tree('getChecked');
	 * 
	 * var param="["; var flag = false; for(j=0;j<nodes.length;j++){
	 * if(nodes[j].attributes.isDs=='0'){//过滤掉数据源 if(flag == false){
	 * param+="{id:"+nodes[j].id+",text:'"+nodes[j].text+"',name:'"+nodes[j].attributes.name+"',children:[]}";
	 * flag = true; }else{
	 * param+=",{id:"+nodes[j].id+",text:'"+nodes[j].text+"',name:'"+nodes[j].attributes.name+"',children:[]}"; } } }
	 * param+="]";
	 */

	// zTree 2.6 method
	var nodes = entityTree.getCheckedNodes();
	var param = "[";
	var flag = false;
	for (j = 0; j < nodes.length; j++) {
		if (nodes[j].isParent == false) {// 过滤掉数据源
			if (flag == false) {
				param += "{id:" + nodes[j].id + ",name:'" + nodes[j].name
						+ "',columnName:'" + nodes[j].columnName
						+ "',nodes:[]}";
				flag = true;
			} else {
				param += ",{id:" + nodes[j].id + ",name:'" + nodes[j].name
						+ "',columnName:'" + nodes[j].columnName
						+ "',nodes:[]}";
			}
		}
	}
	param += "]";

	return param;
}

function saveGrantedEntities() {
	var nodesJsonStr = getEntityNodesInJson();
	$.ajax({
		type : 'post',
		url : 'saveGrantedEntities.do',
		timeout : 1321231232131213123,
		data : {
			'squ' : $("#roleSqu").val(),
			'jsonStr' : nodesJsonStr
		},
		success : function(data) {
			$('#entityDiv').window('close');
			document.forms[1].reset();
		},
		error : function() {
			alert('保存出错！');
		}
	});
}

/**
 * 查询授权保存时生成查询授权树的json数据
 * 
 * @return {}
 */
function getQueryNodesInJson() {

	/*
	 * easyUi tree method var nodes = $('#queryTree').tree('getChecked'); var
	 * param="["; var flag = false; for(j=0;j<nodes.length;j++){
	 * if(nodes[j].attributes.isDs=='0'){//过滤掉数据源 if(flag == false){
	 * param+="{id:"+nodes[j].id+",text:'"+nodes[j].text+"',children:[]}"; flag =
	 * true; }else{
	 * param+=",{id:"+nodes[j].id+",text:'"+nodes[j].text+"',children:[]}"; } } }
	 * param+="]";
	 */

	// zTree 2.6 method
	var nodes = queryTree.getCheckedNodes();
	var param = "[";
	var flag = false;
	for (j = 0; j < nodes.length; j++) {
		if (nodes[j].isParent == false) {// 过滤掉数据源
			if (flag == false) {
				param += "{id:" + nodes[j].id + ",name:'" + nodes[j].name
						+ "',nodes:[]}";
				flag = true;
			} else {
				param += ",{id:" + nodes[j].id + ",name:'" + nodes[j].name
						+ "',nodes:[]}";
			}
		}
	}
	param += "]";

	return param;
}

function saveGrantedQueries() {
	var nodesJsonStr = getQueryNodesInJson();
	$.ajax({
		type : 'post',
		url : 'saveGrantedQueries.do',
		timeout : 1321231232131213123,
		data : {
			'squ' : $("#roleSqu").val(),
			'jsonStr' : nodesJsonStr
		},
		success : function(data) {
			$('#queryDiv').window('close');
			document.forms[2].reset();
		},
		error : function() {
			alert('保存出错！');
		}
	});
}
