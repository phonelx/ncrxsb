var moduleTree;
var node;
var query;
$(function(){
	 initZtree();
	 
	 listen();
});
/**
 * HSM
 * 初始化顯示
 */
//var bmdmQuery;
function initZtree(){
	var notes=[{"id":-1, "pId":0, "name":"组织机构",isParent:true}];     //isParent属性设置默认为父节点
	var setting={
	    isSimpleData : true,              //数据是否采用简单 Array 格式，默认false 	
	    treeNodeKey : "id",               //在isSimpleData格式下，当前节点id属性  
        treeNodeParentKey : "pId",        //在isSimpleData格式下，当前节点的父节点id属性 
        nameCol: "name",                  //设置 zTree 显示节点名称的属性名称,此处默认为Name
	    showLine: true, 
		async:true,	                      //是否发送异步请求	
        asyncUrl: 'listDeptByBmdm.do',
        asyncParam: ["id"],
    	callback: {
    		expand:success,                //展开树菜单后对应table显示
    		click:treeClick,
        },
	};

	moduleTree = $("#moduleTree").zTree(setting, notes);
	moduleTree.refresh();
}
var treeCheckValue=null;    //部门代码
var treeCheckValue1=null;
var treeCheckValue2=null;
var treeCheckValue3=null;

//节点展开事件
function success(event, treeId, treeNode){
	treeCheckValue=treeNode.id;
	treeCheckValue1=treeNode.pId;
	treeCheckValue2=treeNode.name;
	treeCheckValue3=treeNode.idParent;
	query=" AND PARENTBMDM='"+treeCheckValue+"'";
	//gridload();                 //树菜单对应table显示
	//$("#input_search").attr("value","");
};

//节点单击事件
var clickId=null;
function treeClick(event, treeId, treeNode){
	clickId = treeNode.id;
	if(clickId=="-1"){
		query="";
	}else{
		query=" WHERE BMDM='"+clickId+"'";
	}
	showUsersList(1);
}

function showUsersList(pageNo){
	var search = document.getElementById("input_user_search").value;
	$("#userGrid").uiGrid({
		url : "listUsersInPage.do",
		rowNum : 10,//每页显示记录数
		columns : [ {
			field : 'userTitle',
			title : '用户名称',
			width : 80
		}, {
			field : 'username',
			title : '登录帐户',
			width : 70
		}, {
			field : 'roleEntity',
			title : '角色名称',
			width : 80,
			formatter : function(value, rec) {
				return value.roleName;
			}
		},{
			field : 'sfzhm',
			title : '身份证号码',
			width : 160
		},{
			field : 'crreateTime',
			title : '创建日期',
			width : 120,
			formatter : function(value, rec) {
				var arr = value.trim().split(".");
				return arr[0];
			}
		},{
			field : 'isEnabled',
			title : '状态',
			width : 40,
			formatter : function(value, rec) {
				if (value == 1) {
					return "启用";
				} else {
					return "禁用";
				}
			}
		}, {
			field : 'descb',
			title : '描述',
			width : 120,
		} ],
		divId : "#userGrid",
		showPage : 5,//显示
		showEdit : true,
		showDelete : true,
		showEditEvent : showEditUser,
		showDeleteEvent : deleteUser,
		jsonPager : {
			root : "datas",// 当前页数据
			records : "rowCount",//总记录数
			currentPage : pageNo,//当前访问页
			total : "pageCount"//总页数
		},
		data : {
			searchKey : search,
			query:query
		}
	});
}

/**
 * 查询角色
 */
function loadRoles(id){

	$.ajax({
		type : 'post',
		url : 'listRolesInCombo.do',
		timeout : 1321231232131213123,
		success : function(data) {
			data = eval('(' + data + ')');
			$("#roleSquSelect").empty();
			if(id==0){
				for (i = 0; i < data.length; i++) {
					$("#roleSquSelect").append("<option value='" + data[i].id + "'>" + data[i].text + "</option>");
				}
			}else{
				for (i = 0; i < data.length; i++) {					
					if(id==data[i].id){
						$("#roleSquSelect").append("<option value='" + data[i].id + "' selected='selected'>" + data[i].text + "</option>");
					}else{
						$("#roleSquSelect").append("<option value='" + data[i].id + "'>" + data[i].text + "</option>");
					}  					
				}
			}
			
		},
		error : function() {
			alert('保存出错！');
		}
	});
}

/*
 * 添加用户 ----------- start
 */
/**
 * 显示添加用户BOX
 */
function showAddUser(){
	$("#clearForm").click();
	document.getElementById("userTitleName").innerHTML = "添加用户";
	// 添加角色到下拉列表
	loadRoles("0");
	$("#userDiv").modal();
	
	$('#userBtn').unbind();
	$('#userBtn').click(function() {
		if($("#userTitle").val() == null || $("#userTitle").val() == ""){
			alert('用户名称为空'); 
			return false;
		}
		if($("#username").val() == null || $("#username").val() == ""){
			alert('登录帐户为空');
			return false;
		}
		
		if($("#pwd").val() == null || $("#pwd").val() == "" || $("#repeatPwd").val() == null || $("#repeatPwd").val() == ""){
			alert('密码或重复密码为空');
			return false;
		}
		if($("#pwd").val() != $("#repeatPwd").val()){
			alert('两次密码不一致');
			return false;
		}
		if ($('#descb').validatebox('isValid')
				&& $('#pwd').validatebox('isValid')
				&& $('#username').validatebox('isValid')
				&& $('#userTitle').validatebox('isValid')) {
			addUser();
		}
	});
}

/**
 * 执行添加用户
 */
function addUser() {
	if ($('#userForm').form('validate') == true) {
		$('#userForm').form('submit', {
			url : 'addUser.do',
			success : function(data) {
				if (data == 'nameHasExist') {
					$("#username").select();
				} else {
					$('#closeUserBox').click();
					showUsersList(1);
				}
			}
		});
	}
}

/*
 * 编辑用户 ----------- start
 */
function showEditUser(row){
	// 设置文本参数
	document.getElementById("userTitleName").innerHTML = "编辑用户";
	// 添加角色到下拉列表
	loadRoles(row.roleSqu);
	document.getElementById("squ").value=row.squ;
	document.getElementById("userTitle").value=row.userTitle;
	document.getElementById("username").value=row.username;
	console.log("password:"+row.pwd)
	document.getElementById("pwd").value=row.pwd;
	document.getElementById("repeatPwd").value=row.pwd;
	document.getElementById("isEnabled").value=row.isEnabled;
	document.getElementById("roleSquSelect").value=row.roleSquSelect;
	document.getElementById("sfzhm").value=row.sfzhm;
	document.getElementById("validTime").value=row.validTime;
	document.getElementById("dept").value = row.deptName;
	document.getElementById("descb").value=row.descb;

	$("#userDiv").modal();

	$('#userBtn').unbind();
	$('#userBtn').click(function() {
		if($("#pwd").val() == null || $("#pwd").val() == "" || $("#repeatPwd").val() == null || $("#repeatPwd").val() == ""){
			alert('密码或重复密码为空');
			return false;
		}
		if($("#pwd").val() != $("#repeatPwd").val()){
			alert('两次密码不一致');
			return false;
		}
		if ($('#descb').validatebox('isValid')
				&& $('#pwd').validatebox('isValid')
				&& $('#username').validatebox('isValid')
				&& $('#userTitle').validatebox('isValid')) {
			editUser();
			
		}
	});
}

/*
 * 执行编辑用户
 */
function editUser() {
	if ($('#userForm').form('validate') == true) {
		$('#userForm').form('submit', {
			url : 'editUser.do',
			success : function(data) {
				$('#closeUserBox').click();
				showUsersList(1);
			}
		});
	}
}
/*
 * 编辑用户 ----------- end
 */

/*
 * 删除用户 ----------- start
 */
function deleteUser(row) {
	if (confirm('确认删除？')) {
		$.ajax({
			type : 'post',
			url : 'deleteUser.do',
			timeout : 1321231232131213123,
			data : {
				'squ' : row.squ,
				'userTitle' : row.userTitle,
				'username' : row.username
			},
			success : function(data) {
				if (data == '删除成功') {
					showUsersList(1);
				} else {
					alert(data);
				}
			},
			error : function() {
				alert('删除用户信息出错！');
			}
		});
	}	
}
/*
 * 删除用户 ----------- end
 */

/**
  * loadDeptList:(加载部门列表)
  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
  * @author: 黄月
  * @dateTime: 2017-11-10 上午10:57:29
  * @param: 
  * @return: void
 */
function loadDeptList(){
	$("#deptList").uiGrid({
		url : "listDept.do",
		rowNum : 10,//每页显示记录数
		columns : [{
			field : 'BMDM',
			title : '部门代码',
			width : 100
		}, {
			field : 'BMMC',
			title : '部门名称',
			width : 200
		},{
			field : 'PARENTBMDM',
			title : '上级部门代码',
			width : 100
		}],
		divId : "#deptList",
		showPage : 5,//显示
		showView:true,
		showViewEvent:getDept,
		jsonPager : {
			root : "datas",// 当前页数据
			records : "rowCount",//总记录数
			currentPage : 1,//当前访问页
			total : "pageCount"//总页数
		},
		data:{
			searchKey:""
		}
	});	
}

/**
  * getDept:(部门赋值)
  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
  * @author: 黄月
  * @dateTime: 2017-11-10 上午11:32:05
  * @param: @param row
  * @return: void
 */
function getDept(row){
	$('#dept').val(row.BMMC);
	$('#deptID').val(row.BMDM);
	$('#closeDeptBox').click();
}

/**
  * listen:(监听)
  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
  * @author: 黄月
  * @dateTime: 2017-11-10 上午11:08:02
  * @param: 
  * @return: void
 */
function listen(){
	//加载部门列表
	$("#dept").on("click", function(){
		$('#deptModal').modal();
		loadDeptList();
	});
}