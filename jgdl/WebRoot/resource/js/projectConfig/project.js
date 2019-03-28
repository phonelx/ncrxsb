
$().ready(function(){
	
	$('#dzlx').on("change", function(){
		$('#dzsfld').html("");
		$('#dzjsd').html("");
		$('#alphaMax').val("");
		if ($(this).val() != "") {
			getParamConfig($(this).val(), "PARENTSQU", "dzsfld");
		}
	});
	
	$('#dzsfld').on("change", function(){
		$('#dzjsd').html("");
		$('#alphaMax').val("");
		if ($(this).val() != "") {
			getParamConfig($(this).val(), "PARENTSQU", "dzjsd");
		}
	});
	
	$('#dzjsd').on("change", function(){
		$('#alphaMax').val($(this).find("option:selected").attr("flag"));
	});
	
});

$(window).load(function(){
	getParamConfig("FD994086CF8C481997715626BF490F5F", "PARENTSQU", "dzlx");
});


/**
  * showProjectMsg:(获取并显示工程信息)
  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
  * @author: 黄月
  * @dateTime: 2018-2-7 上午10:18:03
  * @param: @param projectObject
  * @return: void
 */
function showProjectMsg(projectObject) {
	var html = "<div class='span3' style='padding:10px;'>";
	html += "<div class='projectList'><div>项目名称：</div>" + projectObject.xmmc + "</div>";
	html += "<div class='projectList'><div>项目类型：</div>" + projectObject.xmlx + "</div>";
	html += "</div>";
	html += "<div class='span3' style='padding:10px;'>";
	html += "<div class='projectList'><div>项目编号：</div>" + projectObject.xmbh + "</div>";
	html += "<div class='projectList'><div>工程地区：</div>" + projectObject.xmdz + "</div>";
	html += "</div>";
	html += "<div class='span3' style='padding:10px;'>";
	html += "<div class='projectList'><div>项目面积：</div>" + projectObject.xmmj + "m²</div>";
	html += "<div class='projectList'><div>地震设防烈度：</div>" + projectObject.dzsfld + "</div>";
	html += "</div>";
	html += "<div class='span3' style='padding:10px;'>";
	html += "<div class='projectList'><div>地震加速度：</div>" + projectObject.dzjsd + "</div>";
	html += "<div class='projectList'><div>地震类型：</div>" + projectObject.dzlx + "</div>";
	html += "</div>";

	$('.project_msg').find("div .row-fluid").html(html);
}

/**
 * addOrUpdateProject:(新增或修改项目)
 * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
 * @author: 黄月
 * @dateTime: 2017-10-18 下午7:59:14
 * @param: 
 * @return: void
*/
function addOrUpdateProject(){
	var squ = $("#project_squ").val();
	if (squ != "") {
		updateProject();
	} else {
		addProject();
	}
}

/**
  * addProject:(新增项目)
  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
  * @author: 黄月
  * @dateTime: 2017-10-17 下午9:17:50
  * @param: 
  * @return: void
 */
function addProject(){
	$.ajax({
		type : 'post',
		url : 'insertProject.do',
		timeout : 1321231232131213123,
		data : $('#objectForm').serialize(),
		dataType :'json',
		success : function(data) {
			if (data.status == "success") {
				listProject();
				$('#clBtn').click();
				//默认加载项目树菜单
				loadTree("",0, "");
			} else {
				Modal.alert({
					msg : "新增项目失败：" + data.result,
					title:"错误！",
					btnok:"确定",
					btncl:"取消"
				});
			}
		},
		error : function() {
			Modal.alert({
				msg : "ajax错误！",
				title:"错误！",
				btnok:"确定",
				btncl:"取消"
			});
		}
	});
}


/**
  * updateProject:(执行修改项目)
  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
  * @author: 黄月
  * @dateTime: 2017-10-23 下午6:18:04
  * @param: 
  * @return: void
 */
function updateProject(){
	$.ajax({
		type : 'post',
		url : 'updateProjectBySqu.do',
		timeout : 1321231232131213123,
		data : $('#objectForm').serialize(),
		dataType :'json',
		success : function(data) {
			if (data.status == "success") {
				$('#clBtn').click();
				listProject();
				//默认加载项目树菜单
				loadTree("",0, "");
			} else {
				Modal.alert({
					msg : "修改项目失败：" + data.result,
					title:"错误！",
					btnok:"确定",
					btncl:"取消"
				});
			}
		},
		error : function() {
			Modal.alert({
				msg : "修改项目ajax错误！",
				title:"错误！",
				btnok:"确定",
				btncl:"取消"
			});
		}
	});
}

/**
  * listProject:(描述)
  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
  * @author: 黄月
  * @dateTime: 2018-1-31 上午11:52:23
  * @param: @param searchWord
  * @param: @returns
  * @return: any
 */
function listProject(searchWord){
	$("#projectGrid").uiGrid({ 
		url : "selectProjectPageInfo.do",
		rowNum : 10,//每页显示记录数
		columns : [ {
			field : 'xmmc',
			title : '项目名称',
			width : 100,
		},{
			field : 'xmbh',
			title : '项目编号',
			width : 60,
		},{
			field : 'xmmj',
			title : '项目面积(m²)',
			width : 80,
		},{
			field : 'createDate',
			title : '创建时间',
			width : 120,
		},{
			field : 'updateDate',
			title : '修改时间',
			width : 120,
		},{
			field : 'dzsfld',
			title : '地震设防烈度',
			width : 80,
			statusStyle : [{
				status:'this',
				statusClass:"label label-warning"
				}]
		},{
			field : 'dzjsd',
			title : '地震加速度',
			width : 65,
			statusStyle : [{
				status:'this',
				statusClass:"label label-important"
				}]
		},{
			field : 'dzlx',
			title : '地震类型',
			width : 65,
			statusStyle : [{
				status:'this',
				statusClass:"label label-success"
				}]
		}],
		divId : "#projectGrid",
		showPage : 5,//显示
		showDelete : true,
		showEdit : true,
		showDeleteEvent : deleteProject,
		showEditEvent : showEditControl,
		jsonPager : {
			root : "datas",// 当前页数据
			records : "rowCount",//总记录数
			currentPage : 1,//当前访问页
			total : "pageCount"//总页数
		},
		data : {
			searchWord : searchWord,
		}
	});
}


/**
 * showDelete:(删除项目)
 * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
 * @author: 黄月
 * @dateTime: 2017-10-17 下午9:49:59
 * @param: @param row
 * @param: @returns
 * @return: any
*/
function deleteProject(row){
	Modal.confirm({msg: "确认删除[" + row.xmmc + "]项目吗？"}).on(function(e){
		if(e){
			$.ajax({
				type : 'post',
				url : 'deleteProjectBySqu.do',
				timeout : 1321231232131213123,
				dataType :'json',
				data : {
					squ:row.project_squ,
				},
				success : function(data) {
					if (data.status == "success") {
						listProject();
						//默认加载项目树菜单
						loadTree("",0, "");
					} else if (data.status == "hasChild") {
						Modal.alert({
							msg : "改项目下存在子项目，不能删除！",
							title:"！",
							btnok:"确定",
							btncl:"取消"
						});
					} else {
						Modal.alert({
							msg : "删除项目失败：" + data.result,
							title:"错误！",
							btnok:"确定",
							btncl:"取消"
						});
					}
				},
				error : function() {
					Modal.alert({
						msg : "ajax错误！",
						title:"错误！",
						btnok:"确定",
						btncl:"取消"
					});
				}
			});
		}
	});
}

/**
 * showEditControl:(编辑模态框)
 * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
 * @author: 黄月
 * @dateTime: 2017-10-18 下午7:55:47
 * @param: @param row
 * @return: void
*/
function showEditControl(row){
	$('#objectModal').modal();
	$('#htmlTitle').html("编辑项目");
	$('#objectForm')[0].reset();
	
	//赋值
	$('#project_squ').val(row.project_squ);		//id
	$('#xmmc').val(row.xmmc);		//工程名称
	$('#xmmj').val(row.xmmj);		//项目面积
	$('#xmdz').val(row.xmdz);		//工程地址
	$('#xmlx').val(row.xmlx);		//建筑类别
	$('#xmbh').val(row.xmbh);			//编号
	
	$("#dzlx").find("option:contains('" + row.dzlx + "')").attr("selected", "selected");
	getParamConfig2($("#dzlx").val(), "dzsfld", row);
	
}

function getParamConfig2(search, selectID, row) {
	$.ajax({
		type : 'post',
		url : 'getParamConfig.do',
		timeout : 1321231232131213123,
		dataType :'json',
		data : {
			search : search,
			param : "PARENTSQU"
		},
		success : function(data) {
			if (data.length > 0) {
				var html = "<option value=''>—— ——请选择—— ——</option>";
				if (selectID != "dzjsd") {
					for ( var i = 0; i < data.length; i++) {
						html += "<option value='" + data[i].SQU + "'>" + data[i].LBMC + "</option>";
					}
				} else {
					for ( var i = 0; i < data.length; i++) {
						html += "<option value='" + data[i].SQU + "' flag='" + data[i].XSCS  + "'>" + data[i].LBMC + "</option>";
					}
				}
				$('#' + selectID + "").html(html);
				if (selectID == "dzsfld") {
					$('#' + selectID + "").find("option:contains('" + row.dzsfld + "')").attr("selected", "selected");
					getParamConfig2($("#dzsfld").val(), "dzjsd", row);
				} else if (selectID == "dzjsd") {
					$('#' + selectID + "").find("option:contains('" + row.dzjsd + "')").attr("selected", "selected");
					$('#' + selectID + "").change();
				}
			}
		}
	});
}

var records = 0;
var currentPage = 0;
var total = 0;
var pageShowPage = 5;// 显示多少个人分页标签
var pageRowNum1 = 10;// 每页显示记录数
var pager = 1;

/**
  * loadProjectTree:(加载项目树)
  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
  * @author: 黄月
  * @dateTime: 2018-2-6 下午2:55:17
  * @param: @param pager
  * @return: void
 */
function loadProjectTree(pager) {
	var html = "";
	$.ajax({
		type : 'post',
		url : 'selectProjectPageInfo.do',
		dataType : 'json',
		async : false,
		timeout : 1321231232131213123,
		data : {
			rows : pageRowNum1,
			page : pager,
			searchWord : ""
		},
		success : function(data) {
			if (data.rows.length != 0) {
				aData = data.rows;
			}
			records = data.total;
			currentPage = data.pageNumber;
			total = data.pageCnt;
			pageRowNum1 = data.pageSize;
			
			if (data.rows.length == 0) {
				html += "<li class='border-bottom'>";
				html += "<a href='javascript:void(0);'>未查询到项目列表</a>";
				html += "</li>";
			}
			
			for ( var i = 0; i < data.rows.length; i++) {
				html += "<li class='border-bottom'>";
				html += "<a href='javascript:void(0);' onclick='loadTree(this,1," + JSON.stringify(data.rows[i]) + ")' id='" + data.rows[i].project_squ + "' title='" + data.rows[i].xmmc + "'>" + data.rows[i].xmmc + "</a>";
				html += "<ul class='topnav'></ul></li>";
			}
			$("#tree").html(html);
			pageDiv1(records, total, currentPage);
		},error : function() {
				Modal.alert({
					msg : '功能菜单加载出错！',
					title : '消息提示',
					btnok : '确定',
					btncl : '取消'
				});
			}
		});
}

/**
 * showAddProjectModal:(新增项目模态框)
 * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
 * @author: 黄月
 * @dateTime: 2017-10-17 下午8:47:16
 * @param: 
 * @return: void
*/
function showAddProjectModal(){
	$('#htmlTitle').html("新增项目");
	$('#objectForm')[0].reset();
	$('#project_squ').val("");
	$('#objectModal').modal();
}


function getParamConfig(search, param, selectID) {
	$.ajax({
		type : 'post',
		url : 'getParamConfig.do',
		timeout : 1321231232131213123,
		dataType :'json',
		data : {
			search : search,
			param : param
		},
		success : function(data) {
			if (data.length > 0) {
				var html = "<option value=''>—— ——请选择—— ——</option>";
				if (selectID != "dzjsd") {
					for ( var i = 0; i < data.length; i++) {
						html += "<option value='" + data[i].SQU + "'>" + data[i].LBMC + "</option>";
					}
				} else {
					for ( var i = 0; i < data.length; i++) {
						html += "<option value='" + data[i].SQU + "' flag='" + data[i].XSCS  + "'>" + data[i].LBMC + "</option>";
					}
				}
				$('#' + selectID + "").html(html);
			}
		}
	});
}

/**
  * pageDiv1:(分页)
  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
  * @author: 黄月
  * @dateTime: 2018-2-6 下午2:48:03
  * @param: @param records
  * @param: @param total
  * @param: @param currentPage
  * @return: void
 */
function pageDiv1(records, total, currentPage) {
	var pageTotal = total;// 总共多少页
	var pageCurrentPage = currentPage;// 当前页
	var pageRecords = records;// 总记录数
	var countt = "";
	var outstr = "";
	pageTotal = parseInt(pageRecords / pageRowNum1);
	if (pageRecords % pageRowNum1 > 0) {
		pageTotal = pageTotal + 1;
	}
	if (pageTotal <= pageShowPage) {
		for ( var count = 1; count <= pageTotal; count++) {
			if (count < 10) {
				countt = "0" + count + "";
			} else {
				countt = "" + count + "";
			}
			if (count != pageCurrentPage) {
				outstr = outstr + "<li tag='" + count
						+ "' class='pageCk'><a href='javascript:void(0);'>"
						+ countt + "</a></li>";
			} else {
				outstr = outstr
						+ "<li class='active'><a href='javascript:void(0);'>"
						+ countt + "</a></li>";
			}
		}
	}
	if (pageTotal > pageShowPage) {
		if (parseInt((pageCurrentPage - 1) / pageShowPage) == 0) {
			outstr = outstr + "<li><a><<</a></li> ";
			for ( var count = 1; count <= pageShowPage; count++) {
				if (count < 10) {
					countt = "0" + count + "";
				} else {
					countt = "" + count + "";
				}
				if (count != pageCurrentPage) {
					outstr = outstr
							+ "<li tag='"
							+ count
							+ "' class='pageCk'><a  href='javascript:void(0);'>"
							+ countt + "</a></li>";

				} else {
					outstr = outstr
							+ "<li class='active'><a href='javascript:void(0);'>"
							+ countt + "</a></li>";
				}
			}
			outstr = outstr
					+ "<li tag='"
					+ count
					+ "' class='pagePk'><a href='javascript:void(0)'>>></a></li>";
		} else if (parseInt((pageCurrentPage - 1) / pageShowPage) == parseInt(pageTotal
				/ pageShowPage)) {
			outstr = outstr
					+ "<li tag='"
					+ (parseInt((pageCurrentPage - 1) / pageShowPage) * pageShowPage)
					+ "' class='pagePk'><a href='javascript:void(0)'><<</a></li> ";
			for ( var count = parseInt(pageTotal / pageShowPage) * pageShowPage
					+ 1; count <= pageTotal; count++) {
				if (count < 10) {
					countt = "0" + count + "";
				} else {
					countt = "" + count + "";
				}
				if (count != pageCurrentPage) {
					outstr = outstr + "<li  tag='" + count
							+ "' class='pageCk'><a href='javascript:void(0);'>"
							+ countt + "</a></li>";
				} else {
					outstr = outstr
							+ "<li class='active'><a href='javascript:void(0);'>"
							+ countt + "</a></li>";
				}
			}
			outstr = outstr + "<li><a href='javascript:void(0)'>>></a></li>";
		} else {
			outstr = outstr
					+ "<li tag='"
					+ (parseInt((pageCurrentPage - 1) / pageShowPage) * pageShowPage)
					+ "' class='pagePk'><a href='javascript:void(0)'><<</a></li> ";
			for ( var count = parseInt((pageCurrentPage - 1) / pageShowPage)
					* pageShowPage + 1; count <= parseInt((pageCurrentPage - 1)
					/ pageShowPage)
					* pageShowPage + pageShowPage; count++) {
				if (count < 10) {
					countt = "0" + count + "";
				} else {
					countt = "" + count + "";
				}
				if (count != pageCurrentPage) {
					outstr = outstr + "<li tag='" + count
							+ "' class='pageCk'><a href='javascript:void(0);'>"
							+ countt + "</a></li>";
				} else {

					outstr = outstr
							+ "<li class='active'><a href='javascript:void(0);'>"
							+ countt + "</a></li>";
				}
			}
			outstr = outstr
					+ "<li  tag='"
					+ count
					+ "' class='pageCk'><a href='javascript:void(0);'>>></a></li>";
		}
	}
	// 共"+pageTotal+"页|"+pageRecords+"条数据|第"+pageCurrentPage+"页 每页" + pageRowNum
	// +"个
	$("#pageInfo").html(
			"<div class='pagination pagination-centered'  align='right'><ul>"
					+ outstr + "<a href='javascript:void(0);'>共" + pageTotal
					+ "页/" + pageRecords + "条数据</a></li></ul></div>");
	outstr = "";
	$(".pageCk").unbind();
	$(".pageCk").bind('click', function() {
		loadProjectTree($(this).attr("tag"));
	});
	$(".pagePk").unbind();
	$(".pagePk").bind('click', function() {
		loadProjectTree($(this).attr("tag"));
	});
}