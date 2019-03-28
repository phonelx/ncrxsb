var entitySqu;
var name;
var dbsSqu;
/**
 * 显示字段 列表
 */
function showFieldGridReg(entitySqu, name, dbsSqu, pageNo) {
	this.entitySqu = entitySqu;
	this.name = name;
	this.dbsSqu = dbsSqu;
//	// 已经注册字段列表
//	$('#fieldReged').datagrid({
//		fit : true,
//		nowrap : true,
//		singleSelect : true,
//		collapsible : false,
//		url : 'doGetRegistedField.do?entitySqu=' + entitySqu,
//		idField : 'squ',
//		rownumbers : true,
//		remoteSort : false,
//		columns : [ [ {
//			title : '名称',
//			field : 'name',
//			width : 80,
//			sortable : true,
//			align : 'center'
//		}, {
//			title : '标题',
//			field : 'title',
//			width : 150,
//			sortable : true,
//			align : 'center'
//		}, {
//			title : '描述',
//			field : 'descb',
//			width : 150,
//			sortable : true,
//			align : 'center'
//		}, {
//			title : '是否检索条件',
//			field : 'isquery',
//			align : 'center',
//			width : 100,
//			sortable : true,
//			formatter : function(value, rec) {
//				return '1' == value ? '否' : '是';
//			}
//		}, {
//			title : '是否布控字段',
//			field : 'isbkquery',
//			align : 'center',
//			width : 100,
//			sortable : true,
//			formatter : function(value, rec) {
//				return '1' == value ? '否' : '是';
//			}
//		}, {
//			title : '布控匹配字段',
//			field : 'bkindexof',
//			width : 100,
//			align : 'center',
//			sortable : true,
//			formatter : function(value, rec) {
//				if (value.split(",")[1] == '1') {
//					return "身份证";
//				} else if (value.split(",")[1] == '2') {
//					return "车牌号";
//				} else {
//					return "<span style='color:red'>暂无匹配</span>";
//				}
//			}
//		}, {
//			title : '是否预警字段',
//			field : 'asCondition',
//			width : 80,
//			align : 'center',
//			sortable : true,
//			formatter : function(value, rec) {
//				return '0' == value ? '否' : '是';
//				;
//			}
//		}, {
//			title : '主键',
//			field : 'isKey',
//			width : 60,
//			align : 'center',
//			sortable : true,
//			formatter : function(value, rec) {
//				return '0' == rec.isKey ? '非' : '是';
//			}
//		}, {
//			title : '自增',
//			field : 'isAutoIncrement',
//			width : 60,
//			align : 'center',
//			sortable : true,
//			formatter : function(value, rec) {
//				return '0' == rec.isAutoIncrement ? '否'
//						: '是';
//			}
//		} ] ],
//		toolbar : [ {
//			id : 'btn_f_del',
//			text : '注  销',
//			iconCls : 'icon-remove',
//			handler : function() {
//				var selected = $('#fieldReged')
//						.datagrid('getSelected');
//				if (selected) {
//					// $.messager.confirm('确认提示',
//					// '确认删除?', function(r){
//					if (confirm('确认注销？'))
//						delField(selected.squ,
//								selected.title,
//								treeNode);
//					// });
//				} else
//					// $.messager.alert('提示',
//					// '请选中一条数据!','warning');
//					alert('请选择注销的字段！');
//			}
//		},'-',{
//			id : 'btn_f_Edit',
//			text : '编  辑',
//			iconCls : 'icon-edit',
//			handler : function() {
//				var selected = $('#fieldReged').datagrid('getSelected');
//
//				if (selected) {
//					$('#btn_f_ok').unbind();
//					$('#btn_f_ok').click(function() {
//						if ($('#ftitle').validatebox('isValid') && $('#fdescb').validatebox('isValid')) {
//							// addField(param,treeNode);
//							// if($("input:radio[name=fisquery]:checked").val()=="0"
//							// &&
//							// $(".indexof").val().trim()==""){
//							// alert("请选择匹配字段");
//							// return;
//							// }
//							if ($("input:radio[name=fisbk]:checked").val() == "0" && $(".bkindexof").val().trim() == "") {
//								alert("请选择匹配字段");
//								return;
//							}
//							editField(selected.squ,treeNode);
//						}
//					});
//
//					$('#field_add').window('open');
//					$('#field_add').panel('setTitle','字段编辑');
//					$('#fname').val(selected.name);
//					// $('#fascondition').val(selected.asCondition);
//					$('#ftitle').val(selected.title);
//					$('#fdescb').val(selected.descb);
//					$(":radio[name='fisquery'][value=" + selected.isquery + "]").attr("checked", "checked");
//					$(":radio[name='fisbk'][value=" + selected.isbkquery + "]").attr("checked", "checked");
//					$(":radio[name='ascondition'][value=" + selected.asCondition + "]").attr("checked", "checked");
//					if (selected.isbkquery == "0") {
//						$(".bkinshow").show();
//						if (selected.bkindexof.split(",")[1] == "1") {
//							$("#bkgetFiled").combobox("setValue", "身份证");
//						} else if (selected.bkindexof.split(",")[1] == "2") {
//							$("#bkgetFiled").combobox("setValue", "车牌号码");
//						} else {
//							$("#bkgetFiled").combobox("setValue","请选择匹配字段");
//						}
//					} else {
//						$(".bkinshow").hide();
//					}
//					$(".indexof").val(selected.indexof);
//					$(".bkindexof").val(selected.bkindexof);
//					$('#displayPrio').val(selected.displayPrio);
//					comboboxDic.clearValue();
//
//					// $('#fascondition').val(selected.asCondition);
//					$('#isSimpleEl').val(selected.isSimpleEl);
//				} else {
//					// $.messager.alert('提示',
//					// '请选中一条数据!','warning');
//					alert('请选择需要编辑的字段！');
//				}
//			}
//		} ]
//	});
	$("#fieldReged").uiGrid({
		url : "doGetRegistedField.do",
		rowNum : 10,//每页显示记录数
		columns : [ {
			title : '名称',
			field : 'name',
			width : 80
		}, {
			title : '标题',
			field : 'title',
			width : 100
		}, {
			title : '描述',
			field : 'descb',
			width : 100
		}, {
			title : '是否检索条件',
			field : 'isquery',
			width : 100,
			formatter : function(value, rec) {
				return '1' == value ? '否' : '是';
			}
		} , {
			title : '主键',
			field : 'isKey',
			width : 40,
			formatter : function(value, rec) {
				return '0' == rec.isKey ? '非' : '是';
			}
		}, {
			title : '自增',
			field : 'isAutoIncrement',
			width : 40,
			formatter : function(value, rec) {
				return '0' == rec.isAutoIncrement ? '否' : '是';
			}
		} ],
		divId : "#fieldReged",
		showPage : 5,//显示
		showEdit : true,
		showDelete : true,
		showEditEvent : showEditField,
		showDeleteEvent : delField,
		jsonPager : {
			root : "datas",// 当前页数据
			records : "rowCount",//总记录数
			currentPage : pageNo,//当前访问页
			total : "pageCount"//总页数
		},
		data : {
			entitySqu:entitySqu
		}
	});

//	// 未注册 字段列表
//	$('#fieldNotReg').datagrid({
//		fit : true,
//		nowrap : true,
//		singleSelect : true,
//		collapsible : false,
//		url : encodeURI('doGetFields.do?entityName=' + name
//				+ '&dbsSqu=' + dbsSqu + '&entitySqu='
//				+ entitySqu),
//		idField : 'squ',
//		rownumbers : true,
//		remoteSort : false,
//		columns : [ [ {
//			title : '名称',
//			field : 'columnName',
//			width : 100,
//			sortable : true
//		}, {
//			title : '数据类型',
//			field : 'dataType',
//			width : 100,
//			sortable : true
//		}, {
//			title : '数据精度',
//			field : 'dataPrecision',
//			width : 100,
//			sortable : true
//		}, {
//			title : '小数位数',
//			field : 'dataScale',
//			width : 100,
//			sortable : true
//		}, {
//			title : '是否非空',
//			field : 'nullable',
//			width : 100,
//			sortable : true
//		}, {
//			title : '主键约束',
//			field : 'consType',
//			width : 100,
//			sortable : true,
//			formatter : function(value, rec) {
//				return rec.consType == 'P' ? '主键' : '';
//			}
//		}, {
//			title : '唯一约束',
//			field : 'opt',
//			width : 100,
//			sortable : true,
//			formatter : function(value, rec) {
//				return rec.consType == 'U' ? '唯一键' : '';
//			}
//		} ] ],
//		toolbar : [ {
//			id : 'btn_f_Add',
//			text : '注  册',
//			iconCls : 'icon-add',
//			handler : function() {
//				var selected = $('#fieldNotReg').datagrid('getSelected');
//				if (selected) {
//					$('#btn_f_ok').unbind();
//					$('#btn_f_ok').click(function() {
//						var param = {
//							'field.descb' : $('#fdescb').val(),
//							'field.entitySqu' : entitySqu,
//							'field.isAutoIncrement' : 0,
//							'field.isKey' : selected.consType == 'P' ? 1 : 0,
//							'field.isNullable' : selected.nullable == 'N' ? 0 : 1,
//							'field.isUnique' : selected.consType == 'U' ? 1 : 0,
//							'field.name' : $('#fname').val(),
//							'field.precision' : selected.dataPrecision == '' ? '0' : selected.dataPrecision,
//							'field.scale' : selected.dataScale == '' ? '0' : selected.dataScale,
//							'field.title' : $('#ftitle').val(),
//							'field.type' : selected.dataType,
//							'field.isSimpleEl' : $('#isSimpleEl').val(),
//							'field.dictypesqu' : comboboxDic.getValue() == "" ? "-1" : comboboxDic.getValue().split('@')[0],
//							'field.displayPrio' : $('#displayPrio').val(),
//							'field.isquery' : $("input:radio[name=fisquery]:checked").val(),
//							'field.indexof' : $(".indexof").val(),
//							'field.isbkquery' : $("input:radio[name=fisbk]:checked").val(),
//							'field.bkindexof' : $(".bkindexof").val(),
//							'field.asCondition' : $("input:radio[name=ascondition]:checked").val()
//						};
//						if ($('#ftitle').validatebox('isValid')&& $('#fdescb').validatebox('isValid')) {
//							if ($("input:radio[name=fisbk]:checked").val() == "0" && $(".bkindexof").val().trim() == "") {
//								alert("请选择匹配字段");
//								return;
//							}
//							addField(param,treeNode);
//						}
//					});
//
//					// 弹出添加窗口
//					$('#field_add').window('open');
//					$('#field_add').panel('setTitle', '字段注册');
//					// 清空表单
//					$('#fname').val(selected.columnName);
//					getFieldComments(selected.columnName, name,dbsSqu);
//					$('#fdescb').val('');
//					$(".indexof").val("");
//					$(".bkindexof").val("");
//					$('#displayPrio').val('1');
//					comboboxDic.clearValue();
//					$(":radio[name='fisquery'][value='0']").attr("checked", "checked");
//					$(":radio[name='fisbk'][value='1']").attr("checked", "checked");
//					$(":radio[name='ascondition'][value='0']").attr("checked", "checked");
//					$(".bkinshow").hide();
//					// $('#fascondition').val('0');
//					$('#isSimpleEl').val('0');
//					$('#ftitle').focus();
//				} else {
//					// $.messager.alert('提示',
//					// '请选中一条数据!','warning');
//					alert('请选择需要注册的字段！');
//				}
//			}
//		} ]
//	});
	$("#fieldNotReg").uiGrid({
		url : "doGetFields.do",
		rowNum : 10,//每页显示记录数
		columns : [ {
			title : '名称',
			field : 'columnName',
			width : 100
		}, {
			title : '数据类型',
			field : 'dataType',
			width : 100
		}, {
			title : '数据精度',
			field : 'dataPrecision',
			width : 100
		}, {
			title : '小数位数',
			field : 'dataScale',
			width : 100
		}, {
			title : '是否非空',
			field : 'nullable',
			width : 100
		}, {
			title : '主键约束',
			field : 'consType',
			width : 100,
			formatter : function(value, rec) {
				return rec.consType == 'P' ? '主键' : '';
			}
		}, {
			title : '唯一约束',
			field : 'opt',
			width : 100,
			formatter : function(value, rec) {
				return rec.consType == 'U' ? '唯一键' : '';
			}
		} ],
		divId : "#fieldNotReg",
		showPage : 5,//显示
		showEdit : true,
		showEditEvent : showAddField,
		jsonPager : {
			root : "datas",// 当前页数据
			records : "rowCount",//总记录数
			currentPage : pageNo,//当前访问页
			total : "pageCount"//总页数
		},
		data : {
			entityName : name,
			dbsSqu : dbsSqu,
			entitySqu : entitySqu
		}
	});
	// 清空 列表选中项
//	$('#fieldNotReg').datagrid('clearSelections');
//	$('#fieldReged').datagrid('clearSelections');
}

/**
 * 显示添加字段BOX
 */
function showAddField(row){
	document.getElementById("clearFieldForm").click();
	document.getElementById("fieldTitleName").innerHTML = "注册字段";
	document.getElementById("fname").value = row.columnName;
	
	$("#field_add").modal();
	
	$('#btn_f_ok').unbind();
	$('#btn_f_ok').click(function() {
		var param = {
			'field.descb' : $('#fdescb').val(),
			'field.entitySqu' : entitySqu,
			'field.isAutoIncrement' : 0,
			'field.isKey' : row.consType == 'P' ? 1 : 0,
			'field.isNullable' : row.nullable == 'N' ? 0 : 1,
			'field.isUnique' : row.consType == 'U' ? 1 : 0,
			'field.name' : $('#fname').val(),
			'field.precision' : row.dataPrecision == '' ? '0' : row.dataPrecision,
			'field.scale' : row.dataScale == '' ? '0' : row.dataScale,
			'field.title' : $('#ftitle').val(),
			'field.type' : row.dataType,
			'field.isSimpleEl' : $('#isSimpleEl').val(),
			'field.dictypesqu' : comboboxDic.getValue() == "" ? "-1" : comboboxDic.getValue().split('@')[0],
			'field.displayPrio' : $('#displayPrio').val(),
			'field.isquery' : $("input:radio[name=fisquery]:checked").val(),
			'field.indexof' : $(".indexof").val(),
			'field.isbkquery' : $("input:radio[name=fisbk]:checked").val(),
			'field.bkindexof' : $(".bkindexof").val(),
			'field.asCondition' : $("input:radio[name=ascondition]:checked").val()
		};
		var isView = row.type == 'VIEW'? '1': '0';
		addField(param,isView);
	});
}
/**
 * 添加字段
 */
function addField(param, treeNode) {
	zTree1.expandNode(treeNode, true, false);
	$.ajax({
		type : 'post',
		data : param,
		url : 'doAddField.do',
		success : function(data) {
//			$('#field_add').window('close');
			if (data == '-2') {
				// $.messager.alert('提示', '该字段已经被注册!', 'info');
				alert('该字段已经被注册！');
			} else {
				document.getElementById("closeFieldBox").click();
				showFieldGridReg(entitySqu, name, dbsSqu, 1);
//				$('#fieldReged').datagrid('reload');
//				$('#fieldNotReg').datagrid('reload');
//				$('#fieldNotReg').datagrid('clearSelections');
				// 刷新树
				// zTree1.reAsyncChildNodes(treeNode, "refresh");
				// 刷新树
//				var newNode = [ {
//					id : 'field_' + data,
//					name : $('#ftitle').val() + "(" + $('#fname').val() + ")"
//				} ];
//				zTree1.addNodes(treeNode, newNode);
			}

		},
		error : function(data) {
			// $.messager.alert('错误', '注册出错!', 'error');
			alert('注册出错！');
		}
	});
}
/**
 * 删除字段
 */
function delField(row) {
//	zTree1.expandNode(treeNode, true, false);
	if (confirm('确认删除？')) {
		$.ajax({
			type : 'post',
			data : {
				'field.squ' : row.squ,
				'field.title' : row.title
			},
			url : 'doDelField.do',
			success : function(data) {
				// if (data == '') {
				// 添加成功 刷新列表
//				$('#fieldReged').datagrid('reload');
//				$('#fieldNotReg').datagrid('reload');
//				$('#fieldReged').datagrid('clearSelections');
				document.getElementById("closeFieldBox").click();
				showFieldGridReg(entitySqu, name, dbsSqu, 1);
				// 刷新树
				// zTree1.reAsyncChildNodes(treeNode, "refresh");
//				var deleteNode = zTree1.getNodeByParam('id', 'field_' + squ);
//				var newNode = [ {
//					id : 'field_' + data,
//					name : $('#ftitle').val() + "(" + $('#fname').val() + ")"
//				} ];
//				zTree1.removeNode(deleteNode, newNode);
				// } else {
				// // 显示异常信息
				// $('body').html(data);
				// }
			},
			error : function(data) {
				// $.messager.alert('错误', '删除出错!', 'error');
				alert('删除出错！');
			}
		});
	}
}

/**
 * 显示编辑字段BOX
 */
function showEditField(row){
	document.getElementById("fieldTitleName").innerHTML = "编辑字段";
	document.getElementById("fname").value = row.name;
	document.getElementById("ftitle").value = row.title;
	document.getElementById("fdescb").value = row.descb;
	document.getElementById("displayPrio").value = row.displayPrio;
	$(":radio[name='fisquery'][value=" + row.isquery + "]").attr("checked", "checked");
	$(":radio[name='fisbk'][value=" + row.isbkquery + "]").attr("checked", "checked");
	$(":radio[name='ascondition'][value=" + row.asCondition + "]").attr("checked", "checked");
	if (row.isbkquery == "0") {
		$(".bkinshow").show();
		if (row.bkindexof.split(",")[1] == "1") {
			$("#bkgetFiled").combobox("setValue", "身份证");
		} else if (row.bkindexof.split(",")[1] == "2") {
			$("#bkgetFiled").combobox("setValue", "车牌号码");
		} else {
			$("#bkgetFiled").combobox("setValue","请选择匹配字段");
		}
	} else {
		$(".bkinshow").hide();
	}
	$(".indexof").val(row.indexof);
	$(".bkindexof").val(row.bkindexof);
	$('#displayPrio').val(row.displayPrio);
	$('#isSimpleEl').val(row.isSimpleEl);
	
	$("#field_add").modal();
	
	$('#btn_f_ok').unbind();
	$('#btn_f_ok').click(function() {
		editField(row.squ);
	});
}
/**
 * 编辑字段
 */
function editField(squ) {
//	zTree1.expandNode(treeNode, true, false);
	$.ajax({
		type : 'post',
		data : {
			'field.squ' : squ,
			'field.name' : $('#fname').val(),
			'field.title' : $('#ftitle').val(),
			'field.descb' : $('#fdescb').val(),
			'field.dictypesqu' : comboboxDic.getValue() == "" ? "-1" : comboboxDic.getValue().split('@')[0],
			'field.isSimpleEl' : $('#isSimpleEl').val(),
			'field.displayPrio' : $('#displayPrio').val(),
			'field.isquery' : $("input:radio[name=fisquery]:checked").val(),
			'field.indexof' : $(".indexof").val(),
			'field.isbkquery' : $("input:radio[name=fisbk]:checked").val(),
			'field.bkindexof' : $(".bkindexof").val(),
			'field.asCondition' : $("input:radio[name=ascondition]:checked").val()
		},
		url : 'doEditField.do',
		success : function(data) {
//			$('#field_add').window('close');
			// if (data == '') {
			document.getElementById("closeFieldBox").click();
			showFieldGridReg(entitySqu, name, dbsSqu, 1);
			// 编辑 刷新列表
//			$('#fieldReged').datagrid('reload');
//			$('#fieldReged').datagrid('clearSelections');
			// 刷新树
			// zTree1.reAsyncChildNodes(treeNode, "refresh");
//			var editNode = zTree1.getNodeByParam('id', 'field_' + squ);
//			editNode.name = $('#ftitle').val() + "(" + $('#fname').val() + ")";
//			zTree1.updateNode(editNode, false);
			// } else {
			// // 显示异常信息
			// $('body').html(data);
			// }
		},
		error : function(data) {
			// $.messager.alert('错误', '编辑出错!', 'error');
			alert("编辑出错！");
		}
	});
}

/**
 *
 */
function goBackDiv(){
	document.getElementById("fieldMgr").style.display = "none";
	document.getElementById("entityTable").style.display = "block";
}

function getFieldComments(fieldName, entityName, dbsSqu) {
	$.ajax({
		type : 'post',
		url : 'getFieldComments.do',
		async : false,
		data : {
			'filedName' : fieldName,
			'entityName' : entityName,
			'dbsSqu' : dbsSqu
		},
		success : function(data) {
			$('#ftitle').val(data);
		}
	});
}

$(document).ready(function() {
	/**
	 * 根据点击显示不同效果(布控)
	 */
	$("input:radio[name=fisbk]").click(function() {
		$("#bkgetFiled").combobox("setValue", "请选择匹配字段");
		if ($(this).val() == "0") {
			$(".bkinshow").show();
		} else {
			$(".bkindexof").val("");
			$(".bkinshow").hide();
		}
	});

	/**
	 * 预警字段匹配
	 */
	$("#getFiled").combobox({
		valueField : 'id',
		textField : 'field',
		data : [ {
			id : 'NAME',
			field : '姓名'
		}, {
			id : 'SEX',
			field : '性别'
		}, {
			id : 'MZ',
			field : '民族'
		}, {
			id : 'HJD',
			field : '所属省市县(区)'
		}, {
			id : 'ZZ',
			field : '居住地'
		}, {
			id : 'CSRQ1',
			field : '出生日期'
		} ],
		filter : function(q, row) {
			var opts = $(this).combobox("options");
			return row[opts.textField].indexOf(q) > -1;// 将从头位置匹配改为任意匹配
		},
		onSelect : function(rec) {
			$(".indexof").val(rec.id);
		},
		onLoadSuccess : function() {
			$("#getFiled").combobox("setValue", "请选择匹配字段");
		}
	});

	/**
	 * 布控字段匹配
	 */
	$("#bkgetFiled").combobox({
		valueField : 'id',
		textField : 'field',
		data : [ {
			id : '1',
			field : '身份证'
		}, {
			id : '2',
			field : '车牌号'
		} ],
		filter : function(q, row) {
			var opts = $(this).combobox("options");
			return row[opts.textField].indexOf(q) > -1;// 将从头位置匹配改为任意匹配
		},
		onSelect : function(rec) {
			$(".bkindexof").val("BKHM," + rec.id);
		},
		onLoadSuccess : function() {
			$("#bkgetFiled").combobox("setValue", "请选择匹配字段");
		}
	});

	var vals = "";
	$(".combo-text").click(function() {
		vals = $(this).val();
		$(this).val("");
	});

	/**
	 * 移除时恢复默认字体
	 */
	$(".combo-text").blur(function() {
		$(this).val(vals);
	});
});

