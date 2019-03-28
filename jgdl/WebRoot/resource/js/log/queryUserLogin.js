/**
 * 用户登录日志数据grid
 */

var dataSource = ""; // 查询人口数据源
var squ = ""; // 人口
var dataJsonDate;

//--------------------------------- no used------------
// 显示 查询模板树
function getdbsSqu(tableNameUse) {
	var squName = $.trim(tableNameUse);

	$.ajax( {
		type : 'Post',
		url : 'doGetEntityByName.do',
		data : 'squName=' + squName,
		async : false,
		success : function(data) {
			var dataJsonIndex = eval('(' + data + ')');
			// alert(data);
			// 得到表ID
			squ = eval('dataJsonInd.' + squName + 'squ');
			// 得到数据源ID
			dataSource = eval('dataJsonIndex.' + squName + 'squ');
		},
		error : function() {
			alert('请求失败！');
		}
	});

	// 重置 表头信息
	if (squ != null) {
		if (dataJsonDate == null) {
			$.ajax( {
				type : 'Post',
				url : 'doGetQueryFiled.do', // 取得表头信息与数据字典信息
				data : 'id=' + squ,
				async : false,
				success : function(data) {
					dataJsonDate = eval('(' + data + ')');
				},
				error : function() {
					alert('请求数据失败！');
				}
			});
		}
	} else {

	}
};



/**
 * 重新执行sql语句，重现查询 sqlStr:传入的sql语句
 */
function doQueryAgain(indexNum) {
	var allData = $('#grid').datagrid('getData');
	var perData = allData.rows[indexNum];
	var jsonObjPerData = eval('(' + perData.operatedescb + ')');
	var sqlStrTran = jsonObjPerData.sql;
	var pageNumTran = jsonObjPerData.pageNo;
	var pageSizeTrqn = jsonObjPerData.pageSize;
	var maxRowsTran = jsonObjPerData.maxRows;
	var dbsSquTran = jsonObjPerData.dbsSqu;
	var headerConfig = jsonObjPerData.tableHeader;
	re = /(singleAndHLImg)/g;
	headerConfig=headerConfig.replace(re,"<img");
	$('#doQueryAgainGrid').datagrid({
		iconCls : 'icon-save',
		loadMsg : '请稍等...',
		rownumbers : true,
		url : 'doQueryAgain.do',
		fit : true,
		// pagination:true,
		queryParams : {
			sqlTran : sqlStrTran,
			pageNumUseTran : pageNumTran,
			pageSizeUseTran : pageSizeTrqn,
			maxRowsUseTran : maxRowsTran,
			dbsSquTUseTran : dbsSquTran
		},
		columns : eval("(" + headerConfig + ")")
	});
	$('#doQueryAgainShow').window('open');
};
//--------------------------------- no used------------

/*
 * ---------------------------------------------------------------------------------------
 * 用户登录日志 ------------------------ start ----------------------- start ------------------
 * ---------------------------------------------------------------------------------------
 */
/**
 * 显示登录日志列表
 */
function showLogGrid(pageNo) {
	$.fn.pagination.defaults.showPageList = false;
	$("#grid1").uiGrid({
		url : "listUserLogInfo.do",
		rowNum : 13,//每页显示记录数
		columns : [ {
			field : 'usertitle',
			title : '用户名',
			width : 307
		}, {
			field : 'loginIp',
			title : '登录Ip',
			width : 307
		}, {
			field : 'loginDatetime',
			title : '登录时间',
			width : 360
		} ],
		divId : "#grid1",
		showPage : 5,//显示
		jsonPager : {
			root : "datas",// 当前页数据
			records : "rowCount",//总记录数
			currentPage : pageNo,//当前访问页
			total : "pageCount"//总页数
		},
		data : {
			usertitle : stringReplaceFun($('#usertitle').val()),
			loginIp : loginIP.getValue(),
			lgistartDatetime : $('#lgistartDatetime').val(),
			lgiendDatetime : $('#lgiendDatetime').val(),
			lgostartDatetime : $('#lgostartDatetime').val(),
			maxRows : $('#maxRowsLogin').val(),
			lgoendDatetime : $('#lgoendDatetime').val()
		}
	});
}

/**
 * 导出登录日志数据
 */
function exportLoginLog(){
	document.getElementById("loginLogForm").submit();
}

/*
 * 删除选中信息 ------------ start -----
 */
/**
 * 删除选中信息
 */
function deleteSelectedUserLgI() {
	var squStrGet = $('#grid1').datagrid('getSelections');
	if (squStrGet.length == 0) {
		alert("请选中删除项！");
	} else {
		if (confirm('确认清除选出日志？')) {
			deleteUserLog();
		}
	}
}

/**
 * 清除选中日志
 */
function deleteUserLog() {
	var squStrGet = $('#grid1').datagrid('getSelections');
	var squStr = "";
	if (squStrGet.lenght != 0) {
		for ( var i = 0; i < squStrGet.length - 1; i++) {
			squStr += squStrGet[i].squ + ",";
		}
		squStr += squStrGet[squStrGet.length - 1].squ;
	}

	$.ajax({
		type:"post",
		url:"deleteUserLoginPage.do",
		data:{
			usertitle : $('#usertitle').val(),
			loginIp : loginIP.getValue(),
			lgistartDatetime : $('#lgistartDatetime').val(),
			lgiendDatetime : $('#lgiendDatetime').val(),
			lgostartDatetime : $('#lgostartDatetime').val(),
			maxRows : $('#maxRowsLogin').val(),
			lgoendDatetime : $('#lgoendDatetime').val(),
			squ : squStr
		},
		success:function(data){
			showLogGrid(1);
		},
		error:function(){
			alert("请求删除选中日志信息失败!");
		}
	});
}
/*
 * 删除选中信息 ------------ end -----
 */

/*
 * 清空用户日志 ------------ start ------
 */
/**
 * 清空用户登录日志
 */
function confirmDeleteUserLog() {
	if (confirm('确认清空选出日志？')) {
		deleteALLUserLog();
	}
}

/**
 * 清空所有选中的用户登录信息
 */
function deleteALLUserLog() {
	$.ajax({
		type:"post",
		url:"deleteUserLoginPage.do",
		data:{
			usertitle : $('#usertitle').val(),
			loginIp : loginIP.getValue(),
			lgistartDatetime : $('#lgistartDatetime').val(),
			lgiendDatetime : $('#lgiendDatetime').val(),
			maxRows : $('#maxRowsLogin').val(),
			lgostartDatetime : $('#lgostartDatetime').val(),
			lgoendDatetime : $('#lgoendDatetime').val()
		},
		success:function(data){
			showLogGrid(1);
		},
		error:function(){
			alert("请求清空登录日志失败!");
		}
	});
}
/*
 * 清空用户日志 ------------ end ------
 */

/**
 * 查询选择满足条件的结果
 */
function timeCheckUserLog() {
	var startLgi = $('#lgistartDatetime').val().replace(/[-,:,\s]/g, "");
	var endLgi = $('#lgiendDatetime').val().replace(/[-,:,\s]/g, "");
	var startLgo = $('#lgostartDatetime').val().replace(/[-,:,\s]/g, "");
	var endLgo = $('#lgoendDatetime').val().replace(/[-,:,\s]/g, "");
	var lgiS = new Number(startLgi);
	var lgiE = new Number(endLgi);
	var lgoS = new Number(startLgo);
	var lgoE = new Number(endLgo);
	if (lgiS > lgiE || lgoS > lgoE) {
		alert('结束时间不能小于开始时间！');
	} else {
		if ($('#maxRowsLogin').val() == "-1") {
			if (!confirm("你选择了返回所有数据，如果数据量过大，将影响查询速度，是否继续？")) {
				return;
			}
		}
		showLogGrid(1);
	}
}
/*
 * ----------------------------------------------------------------------------------------
 * 用户登录日志 ------------------------ end -------------------------- end --------------------
 * ----------------------------------------------------------------------------------------
 */

/**
 * =============== spit ====================== spit ===================== spit =============
 */

/*
 * ----------------------------------------------------------------------------------------
 * ---操作日志 ------------------------ start ------------------------ start ------------------
 * ----------------------------------------------------------------------------------------
 */
/**
 * 用户操作列表查询
 */
function showUserOpr(pageNo) {
	$("#grid").uiGrid({
		url : "queryUserOprInfo.do",
		rowNum : 13,//每页显示记录数
		columns : [ {
			field : 'usertitle',
			title : '用户名',
			width : 200
		}, {
			field : 'operatetype',
			title : '操作类型',
			width : 200
		}, {
			field : 'operatekeywords',
			title : '操作关键字',
			width : 200
		}, {
			field : 'operateDatetime',
			title : '操作日期',
			width : 280
		}, {
			field : 'operatedescb',
			title : '操作描述',
			width : 455,
			formatter : function(value, rowData, rowIndex) {
				return value;
			}
		} ],
		divId : "#grid",
		showPage : 5,//显示
		jsonPager : {
			root : "datas",// 当前页数据
			records : "rowCount",//总记录数
			currentPage : pageNo,//当前访问页
			total : "pageCount"//总页数
		},
		data : {
			usertitle : stringReplaceFun($('#usertitle').val()),
			operatetype : $('#operatetype').val(),
			operatekeywords : $('#operatekeywords').val(),
			operateStartime : $('#lgistartDatetime').val(),
			operateEndtime : $('#lgiendDatetime').val(),
			maxRows : $('#maxRowsOpr').val(),
			operatedescb : stringReplaceFun($('#operatedescb').val())
		}
	});
}

/**
 * 导出操作日志数据
 */
function exportOprationLog(){
	document.getElementById("oprationLogForm").submit();
}

/*
 * 清空选中的操作日志 ---------- start ----
 */
/**
 * 确认删除选中操作日志信息
 */
function deleteSelectedUserOprLog() {
	var squStrGet = $('#grid').datagrid('getSelections');
	if (squStrGet.length == 0) {
		alert("请选中删除项！");
	} else {
		if(confirm('确认清除选出日志？')){
			deleteUserOprLog();
		}
	}
}

/**
 * 删除用户选中信息
 */
function deleteUserOprLog() {
	var squStrGet = $('#grid').datagrid('getSelections');
	var squStr = "";
	if (squStrGet.lenght != 0) {
		for (var i = 0; i < squStrGet.length - 1; i++) {
			squStr += squStrGet[i].squ + ",";
		}
		squStr += squStrGet[squStrGet.length - 1].squ;
	}

	$.ajax({
		type:"post",
		url:"deleteUserOpr.do",
		data:{
			usertitle : $("#usertitle").val(),
			operatetype : $("#operatetype").combobox("getValue"),
			operatekeywords : $("#operatekeywords").combobox("getValue"),
			operateStartime : $("#lgistartDatetime").val(),
			operateEndtime : $("#lgiendDatetime").val(),
			operatedescb : $("#operatedescb").val(),
			maxRows : $("#maxRowsOpr").combobox("getValue"),
			squ : squStr
		},
		success:function(data){
			showUserOpr(1);
		},
		error:function(){
			alert("请求删除选中日志信息失败!");
		}
	});
}
/*
 * 清空选中的操作日志 ---------- end ----
 */

/*
 * 清空操作日志 ---------- start ----
 */
/**
 * 确认是否清空选出数据信息
 */
function confirmDeleteAllUserOprLog() {
	if(confirm('确认清空选出日志？')){
		deleteAllUserOprLog();
	}
}

/**
 * 清空所有用户操作信息
 */
function deleteAllUserOprLog() {
	$.ajax({
		type:"post",
		url:"deleteUserOpr.do",
		data:{
			usertitle : $('#usertitle').val(),
			operatetype : $('#operatetype').combobox('getValue'),
			operatekeywords : $('#operatekeywords').combobox('getValue'),
			operateStartime : $('#lgistartDatetime').val(),
			operateEndtime : $('#lgiendDatetime').val(),
			maxRows : $('#maxRowsOpr').combobox('getValue'),
			operatedescb : $('#operatedescb').val()
		},
		success:function(data){
			showUserOpr(1);
		},
		error:function(){
			alert("请求清空登录日志失败!");
		}
	});
}
/*
 * 清空操作日志 ---------- end ----
 */

/**
 * 校验用户选中时间
 */
function timeCheckUserOpr() {
	var startLgi = $('#lgistartDatetime').val().replace(/[-,:,\s]/g, "");
	var endLgi = $('#lgiendDatetime').val().replace(/[-,:,\s]/g, "");
	var lgiS = new Number(startLgi);
	var lgiE = new Number(endLgi);
	if (lgiS > lgiE) {
		alert('结束时间不能小于开始时间！');
	} else {
		if ($('#maxRowsOpr').val() == "-1") {
			if (!confirm("你选择了返回所有数据，如果数据量过大，将影响查询速度，是否继续？")) {
				return;
			}
		}
		showUserOpr(1);
	}
}
/*
 * ----------------------------------------------------------------------------------------
 * ---操作日志 ------------------------ end -------------------------- end --------------------
 * ----------------------------------------------------------------------------------------
 */

/**
 * =============== spit ====================== spit ===================== spit =============
 */

/*
 * ----------------------------------------------------------------------------------------
 * ---系统日志 ------------------------ start ------------------------ start ------------------
 * ----------------------------------------------------------------------------------------
 */
/**
 * 系统日志grid
 */
function showSysInfo(pageNo) {
	$("#grid").uiGrid({
		url : "querySystemRun.do",
		rowNum : 13,//每页显示记录数
		columns : [ {
			field : 'exceptionType',
			title : '日志类型',
			width : 100
		}, {
			field : 'exceptionClassName',
			title : '系统对象',
			width : 360
		}, {
			field : 'exceptionmessage',
			title : '消息',
			width : 400
		}, {
			field : 'throwDatetime',
			title : '时间',
			width : 260
		} ],
		divId : "#grid",
		showPage : 5,//显示
		jsonPager : {
			root : "datas",// 当前页数据
			records : "rowCount",//总记录数
			currentPage : pageNo,//当前访问页
			total : "pageCount"//总页数
		},
		data : {
			exceptionType : $('#exceptionType').val(),
			exceptionType : $('#exceptionType').val(),
			exceptionClassName : stringReplaceFun($('#exceptionClassName').val()),
			exceptionmessage : $('#exceptionmessage').val(),
			throwStartDatetime : $('#throwStartDatetime').val(),
			maxRows : $('#maxRowsSys').val(),
			throwEndDatetime : $('#throwEndDatetime').val()
		}
	});
}

/*
 * 删除选中的操作日志 ---------- start ----
 */
/**
 * 确认删除选中信息
 */
function deleteSelectedSysRunLog() {
	var squStrGet = $('#grid').datagrid('getSelections');
	if (squStrGet.length == 0) {
		alert("请选中删除项！");
	} else {
		if(confirm('确认清除选出日志？')){
			deleteSysRunLog();
		}
	}
}

/**
 * 删除用户选中信息
 */
function deleteSysRunLog() {
	var squStrGet = $('#grid').datagrid('getSelections');
	var squStr = "";
	if (squStrGet.lenght != 0) {
		for (var i = 0; i < squStrGet.length - 1; i++) {
			squStr += squStrGet[i].squ + ",";
		}
		squStr += squStrGet[squStrGet.length - 1].squ;
	}

	$.ajax({
		type:"post",
		url:"deleteSysRunLog.do",
		data:{
			exceptionType : $('#exceptionType').val(),
			exceptionType : $('#exceptionType').val(),
			exceptionClassName : $('#exceptionClassName').val(),
			exceptionmessage : $('#exceptionmessage').val(),
			throwStartDatetime : $('#throwStartDatetime').val(),
			throwEndDatetime : $('#throwEndDatetime').val(),
			maxRows : $('#maxRowsSys').val(),
			squ : squStr
		},
		success:function(data){
			showSysInfo(1);
		},
		error:function(){
			alert("请求删除选中日志信息失败!");
		}
	});
}
/*
 * 删除选中的操作日志 ---------- end ----
 */

/*
 * 清空操作日志 ---------- start ----
 */
/**
 * 确认是否清空选出数据信息
 */
function confirmDeleteSysRunLog() {
	if(confirm('确认清空选出日志？')){
		deleteAllSysRunLog();
	}
}

/**
 * 清空所有选中的用户登录信息
 */
function deleteAllSysRunLog() {
	$.ajax({
		type:"post",
		url:"deleteSysRunLog.do",
		data:{
			exceptionType : $('#exceptionType').val(),
			exceptionType : $('#exceptionType').val(),
			exceptionClassName : $('#exceptionClassName').val(),
			exceptionmessage : $('#exceptionmessage').val(),
			throwStartDatetime : $('#throwStartDatetime').val(),
			maxRows : $('#maxRowsSys').val(),
			throwEndDatetime : $('#throwEndDatetime').val()
		},
		success:function(data){
			showSysInfo(1);
		},
		error:function(){
			alert("请求删除选中日志信息失败!");
		}
	});
}

function timeCheckSysRun() {
	var startLgi = $('#throwStartDatetime').val().replace(/[-,:,\s]/g, "");
	var endLgi = $('#throwEndDatetime').val().replace(/[-,:,\s]/g, "");
	var lgiS = new Number(startLgi);
	var lgiE = new Number(endLgi);
	if (lgiS > lgiE) {
		alert('结束时间不能小于开始时间！');
	} else {
		if ($('#maxRowsSys').val() == "-1") {
			if (!confirm("你选择了返回所有数据，如果数据量过大，将影响查询速度，是否继续？")) {
				return;
			}
		}
		showSysInfo(1);
	}
}
/*
 * 清空操作日志 ---------- end ----
 */

