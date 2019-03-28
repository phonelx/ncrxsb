//用户模块授权树對象
var moduleTree;
var node;
$(function() {
	searchWord();
	$(".registerform").Validform({
		tiptype : function(msg, o, cssctl) {
			var objtip = $("#msg");
			cssctl(objtip, o.type);
			objtip.text(msg);
		},
		callback : function(form) {
			var typeId = $("#typeId").val();
			if (typeId == 1) {
				addDept();
			} else {
				editDept();
			}
			return false;
		},
		datatype : {// 传入自定义datatype类型【方式二】;
			"z2-4" : /^[\u4E00-\u9FA5\uf900-\ufa2d]{2,4}$/
		}
	});

	$(".serviceform").Validform({
		tiptype : function(msg, o, cssctl) {
			var objtip = $("#serviceMsg");
			cssctl(objtip, o.type);
			objtip.text(msg);
		},
		callback : function(form) {
			$("#serviceWin").click();
			loadData();
		},
		datatype : {// 传入自定义datatype类型【方式二】;
			"z2-4" : /^[\u4E00-\u9FA5\uf900-\ufa2d]{2,4}$/
		}
	});
});

function keyDown(e){ 
	 var keycode = 0;
	 keycode = e.keyCode;
	 if (keycode == 13 ) {//回车键是13
		 searchWord();
	 }
}

/**
 * HSM
* 关键词搜索（左侧树菜单未展开时，检索目标为全局数据，展开时，检索目标为当前层级数据）
*/
var query="";
var treeSql=null;
var checkValue=null;
var checksql=null;
function searchWord(){
	checkValue=$("#input_search").attr("value").toUpperCase();    //搜索条件
	checksql=checkValue=="关键字搜索"? "":" AND T.BMDM LIKE '%"+checkValue+"%' OR T.BMMC LIKE '%"+checkValue+"%'";
	treeSql=treeCheckValue==null? "": " AND T.PARENTBMDM='"+treeCheckValue+"'";
    query=checksql+treeSql;
	gridload();
}

var pageNo = 1;
var grid ;
//加载表格
function gridload(){
	grid =$("#uigrld").uiGrid({
		url : "listDept.do",
		rowNum : 10,//每页显示记录数
		columns : [{
			field : 'BMDM',
			title : '部门代码',
			width : 140
		}, {
			field : 'BMMC',
			title : '部门名称',
			width : 360
		},{
			field : 'PARENTBMDM',
			title : '上级部门代码',
			width : 140
		}],
		divId : "#uigrld",
		showPage : 5,//显示
		showEdit : true,
		showDelete : true,
		showEditEvent : showEditEvent,
		showDeleteEvent : showDeleteEvent,
		jsonPager : {
			root : "datas",// 当前页数据
			records : "rowCount",//总记录数
			currentPage : pageNo,//当前访问页
			total : "pageCount"//总页数
		},
		data:{
			searchKey:query
		}
	});	
}

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
/*        		asyncSuccess:function(){

				      }*/
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
	query=" AND T.PARENTBMDM='"+treeCheckValue+"'";
	gridload();                 //树菜单对应table显示
	//$("#input_search").attr("value","");
};

//节点单击事件
var clickId=null;
function treeClick(event, treeId, treeNode){
	clickId = treeNode.id;
	if(clickId=="-1"){
		query="";
	}else{
		query="AND T.BMDM='"+clickId+"'";
	}
	gridload();
}

function showViewEvent(row, e) {
	if (row != undefined) {
		$("#user_example").modal();
		user_gridload(row.BMDM);
	}
}

function loadDataShow() {
	$("#serviceType_example").modal();
}

function serviceTypeChange() {
	if ($("#service_Select").attr("value") != "ORGS_ALL") {
		Modal.alert({
			msg : '功能模块研发中',
			title : '消息提示',
			btnok : '确定',
			btncl : '取消'
		});
	}
}
		
// 部门数据更新
function loadData() {
	if ($("#service_Select").attr("value") == "ORGS_ALL") {
		showMask();
		$.ajax({
			type : "post",
			url : "deptDataUpdate.do",
			timeout : 1321231232131213123,
			dataType : 'json',
			async : false,
			data : {
				'sync.serviceType' : $("#service_Select").attr("value"),
				'sync.serviceName' : $("#service_Select").find("option:selected").text(),
				'sync.start' : $("#startPage").attr("value"),
				'sync.end' : $("#endPage").attr("value")
			},
			success : function(data) {
				if (data.state == 0) {
					searchWord();
					setTimeout('hideMask()', 1000);
				}
			},
			error : function() {
				Modal.alert({
					msg : '部门信息更新出错',
					title : '消息提示',
					btnok : '确定',
					btncl : '取消'
				});
			}
		});
	} else {
		Modal.alert({
			msg : '功能研发中....',
			title : '消息提示',
			btnok : '确定',
			btncl : '取消'
		});
	}
}

/**
 * 显示遮罩层
 */
function showMask() {
	$('#mask').css("height", $(document).height());
	$('#mask').css("width", $(document).width());
	$("#mask").show();
}

function hideMask() {
	$("#mask").hide();
}

// 编辑应用信息
function showEditEvent(row) {
	gdm=row.BMDM;
	if (row != undefined) {
		$("#htmlTitle").html("编辑部门信息");
		$("#dept_example").modal();
		$("#deptForm").form("clear");
		$("#add_bmdm").val(row.BMDM);
		$("#add_bmdm").attr("readonly", "readonly"); // 部门编号为只读，不可修改
		$("#bmmc").val(row.BMMC);
		$("#add_parentBmdm").val(row.PARENTBMDM);
		//$("#add_parentBmdm").attr("readonly", "readonly");
		$("#typeId").val(0);
		
		$("#valueUser").val(row.USERSQU);
		$("#textUser").val(row.USERTITLE);
	}
}
/**
 * 部门修改
 */
function editDept() {
	$("#deptForm").form("submit",{
		url : 'editDept.do',
		success : function(data) {
			$("#closeWin").click();
			Modal.alert({
				msg : data,
				title : '消息提示',
				btnok : '确定',
				btncl : '取消'
			});
			searchWord();
			// 属性匹配查找节点
			if (clickId != null) {
				node = moduleTree.getNodeByParam("id", treeCheckValue,"name", treeCheckValue);
				moduleTree.reAsyncChildNodes(node, "refresh", false);
			}
		},
		error : function(data) {
			Modal.alert({
				msg : data,
				title : '消息提示',
				btnok : '确定',
				btncl : '取消'
			});
		}
	});
}

// 删除app部门
function showDeleteEvent(row) {
	if (row != undefined) {
		Modal.confirm({
			msg : "删除部门将会同时删除部门下的用户，确认删除此部门吗？"
		}).on(function(e) {
			if (e) {
				$.ajax({
					type : "post",
					url : "delDept.do",
					timeout : 1321231232131213123,
					data : {
						'dept.bmdm' : row.BMDM
					},
					success : function(data) {
						Modal.alert({
							msg : data,
							title : '消息提示',
							btnok : '确定',
							btncl : '取消'
						});

						searchWord();

						// alert(clickId);
						// 属性匹配查找节点
						if (clickId != null) {
							node = moduleTree.getNodeByParam("id",treeCheckValue, "name",treeCheckValue);
							moduleTree.reAsyncChildNodes(node,"refresh", false);
						}
					},
					error : function() {
						Modal.alert({
							msg : "部门消息提示",
							title : '消息提示',
							btnok : '确定',
							btncl : '取消'
						});
					}
				});
			}
		});
	}
}
		
// 新增app应用
function deptRegister() {
	$("#add_bmdm").removeAttr("readonly");
	$("#add_parentBmdm").removeAttr("readonly");
	$("#htmlTitle").html("部门注册");
	$("#deptForm").form("clear");
	$("#bmdm").removeAttr("readonly");
	$("#dept_example").modal();
	$("#typeId").val(1);
}

function addDept() {
	if ($("#add_bmdm").val().trim() == $("#add_parentBmdm").val().trim()) {
		Modal.alert({
			msg : '上级部门代码不能等于部门代码',
			title : '消息提示',
			btnok : '确定',
			btncl : '取消'
		});
		return;
	}

	$("#deptForm").form("submit",{
		url : 'addDept.do',
		success : function(data) {
			$("#closeWin").click();
			Modal.alert({
				msg : data,
				title : '消息提示',
				btnok : '确定',
				btncl : '取消'
			});
			// moduleTree.refresh();
			// alert(clickId);

			searchWord();

			// 属性匹配查找节点
			if (clickId != null) {
				node = moduleTree.getNodeByParam("id", treeCheckValue,"name", treeCheckValue);
				moduleTree.reAsyncChildNodes(node, "refresh", false);
			}
		},
		error : function(data) {
			Modal.alert({
				msg : '部门新增出错',
				title : '消息提示',
				btnok : '确定',
				btncl : '取消'
			});
		}
	});
}

/**
 *
  * loadAllUser:(加载所有用户)
  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
  * @author: 黄月
  * @dateTime: 2017-6-20 上午11:16:05
  * @param: 
  * @return: void
 */
function loadAllUser() {
	$.ajax({
		type : "post",
		url : "findAllUser.do",
		timeout : 1321231232131213123,
		dataType : 'json',
		success : function(data) {
			var html = "";
			for ( var i = 0; i < data.rows.length; i++) {
				html += "<option value=" + data.rows[i].SQU + ">"+ data.rows[i].USERNAME + "</option>";
			}
			$("#select").html(html);
		},
		error : function() {
			Modal.alert({
				msg : '加载用户列表出错',
				title : '消息提示',
				btnok : '确定',
				btncl : '取消'
			});
		}
	});
}
		

// 加载部门下用户
function loadUserBySqu(squ) {

}

// 加载表格
var usergrid;
function user_gridload(squ) {
	usergrid = $("#usergrld").uiGrid({
		url : "findUserBySqu.do",
		rowNum : 10,//每页显示记录数
		columns : [ {
			field : 'USERTITLE',
			title : '用户',
			width : 120
		}, {
			field : 'USERNAME',
			title : '证件号',
			width : 500
		} ],
		divId : "#usergrld",
		showPage : 5,//显示
		showView : false,
		showEdit : false,
		showDelete : false,
		jsonPager : {
			root : "datas",// 当前页数据
			records : "rowCount",//总记录数
			currentPage : pageNo,//当前访问页
			total : "pageCount"//总页数
		},
		data : {
			'dept.bmdm' : squ
		}
	});
}
/**
 * 显示添加用户到部门
 */
var gdm = "";
function showAddUserToBM(){
	document.getElementById("rightSelect").innerHTML="";
	$("#addUserToBM").modal();

	$.ajax({
		type : "post",
		url : "loadUserGrld.do",
		timeout : 1321231232131213123,
		data:{
			param:gdm
		},
		dataType : "json",
		success : function(data) {
			var html="";
			
			for(var i = 0;i<data.rows.length;i++){
				html+="<option value="+data.rows[i].SQU+">"+data.rows[i].USERTITLE+"</option>";
			}
			
			$("#leftSelect").html(html);
		},
		error : function() {
			Modal.alert({
				msg : '加载用户列表出错',
				title : '消息提示',
				btnok : '确定',
				btncl : '取消'
			});
		}
	});
}

// 添加用户进入预览列表
function addUserToList(obj){
	
}

// 将选中的用户先添加到用户文本框
function sureAddUser(){
	var opValue="";
	var opText="";
	$("#rightSelect option").each(function(){
		opValue=opValue+$(this).val()+",";
		opText=opText+$(this).text()+",";
	});
	
	opValue=opValue.substring(0, opValue.length-1);
	opText=opText.substring(0, opText.length-1);

	$("#valueUser").val(opValue);
	$("#textUser").val(opText);
	
	$("#closeWinUser").click();
}

