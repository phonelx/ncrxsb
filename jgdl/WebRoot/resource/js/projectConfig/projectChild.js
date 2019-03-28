/**
 * 项目编号
 */
var _prosqu;

/**
  * editChild:(修改子项目)
  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
  * @author: 黄月
  * @dateTime: 2018-2-7 下午6:46:53
  * @param: @param obj
  * @return: void
 */
function editChild(obj) {
	$('#childModal').modal();
	$('#childHtmlTitle').html("编辑子项目");
	$('#childForm')[0].reset();
	
	//赋值
	$('#child_squ').val(obj.child_squ);		//id
	$('#zdwxmmc').val(obj.zdwxmmc);		//子工程名称
	$('#jzgd').val(obj.jzgd);		//建筑高度
	$('#childNum').val(obj.childNum);		//建筑高度
}
	
/**
  * delChild:(删除子项目)
  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
  * @author: 黄月
  * @dateTime: 2018-2-7 下午6:46:38
  * @param: @param obj
  * @return: void
 */
function delChild(obj) {
	Modal.confirm({msg: "确认删除子工程[ " + obj.zdwxmmc + " ]吗？"}).on(function(e){
		if(e){
			$.ajax({
				type : 'post',
				url : 'delChildProjectBySqu.do',
				timeout : 1321231232131213123,
				dataType :'json',
				data : {
					childSqu:obj.child_squ,
				},
				success : function(data) {
					if (data.status == "success") {
						$('#childclBtn').click();
						//重载子项目
						loadChildTreeAndList(_projectObj.project_squ);
					} else if (data.status == "hasSite") {
						Modal.alert({
							msg : "该项目下存在部位信息，不能删除！",
							title:"！",
							btnok:"确定",
							btncl:"取消"
						});
					} else {
						Modal.alert({
							msg : "删除子项目失败：" + data.result,
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
  * addOrUpdateChild:(新增或修改子项目)
  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
  * @author: 黄月
  * @dateTime: 2018-2-7 下午5:37:24
  * @param: 
  * @return: void
 */
function addOrUpdateChild(){
	var squ = $("#child_squ").val();
	if (squ != "") {
		updateChildProject();
	} else {
		addChildProject();
	}
}

/**
  * addChildProject:(新增子项目)
  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
  * @author: 黄月
  * @dateTime: 2018-2-7 下午5:38:02
  * @param: 
  * @return: void
 */
function addChildProject(){
	$.ajax({
		type : 'post',
		url : 'addChildProject.do',
		timeout : 1321231232131213123,
		data : $('#childForm').serialize() + "&project_squ=" + _projectObj.project_squ,
		dataType :'json',
		success : function(data) {
			$('#childclBtn').click();
			//重载子项目
			loadChildTreeAndList(_projectObj.project_squ);
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
  * updateChildProject:(修改子项目)
  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
  * @author: 黄月
  * @dateTime: 2018-2-7 下午5:51:14
  * @param: 
  * @return: void
 */
function updateChildProject() {
	$.ajax({
		type : 'post',
		url : 'updateChildProject.do',
		timeout : 1321231232131213123,
		data : $('#childForm').serialize(),
		dataType :'json',
		success : function(data) {
			$('#childclBtn').click();
			//重载子项目
			loadChildTreeAndList(_projectObj.project_squ);
		},
		error : function() {
			Modal.alert({
				msg : "修改子项目ajax错误！",
				title:"错误！",
				btnok:"确定",
				btncl:"取消"
			});
		}
	});
}

/**
  * loadChildTree:(根据项目id加载子项目列表)
  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
  * @author: 黄月
  * @dateTime: 2018-2-6 下午3:35:42
  * @param: 
  * @return: void
 */
function loadChildTreeAndList(project_squ) {
	_prosqu = project_squ
	//$("#gc").val(project_squ);
	$.ajax({
		type : 'post',
		url : 'listChildProjectByProjectSqu.do',
		timeout : 1321231232131213123,
		dataType:"json",
		async : false,
		data:{
			project_squ:project_squ,
		},
		success : function(data) {
			if (data.length > 0) {
				loadChildTree(data, project_squ);
			}
			loadChildList(data);
		},
		error : function() {
			Modal.alert({
			    msg: '子项目列表加载出错',
			    title: '消息提示',
			    btnok: '确定',
			    btncl:'取消'
			  });
		}
	});
}

/**
  * loadChildList:(加载子项目列表)
  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
  * @author: 黄月
  * @dateTime: 2018-2-6 下午4:17:56
  * @param: 
  * @return: void
 */
function loadChildList(data){
	//子项目列表
	var html = "<table class='table table-bordered'>";
	html += "<tr><th>子项目名称</th><th>子项目编号</th><th>建筑高度（m）</th><th>创建日期</th><th>修改日期</th><th>操作</th></tr>";
	if (data.length <= 0) {
		html += "<tr><td colspan='3'>未查询到数据</td></tr>";
	} else {
		for ( var i = 0; i < data.length; i++) {
			html += "<tr><td>" + data[i].zdwxmmc + "</td><td>" + data[i].childNum + "</td><td>" + data[i].jzgd + "</td><td>" + data[i].createDate + "</td><td>" + data[i].updateDate + "</td><td>";
			html += "&nbsp;<span><a title='编辑' href='javascript:void(0);' onclick='editChild(" + JSON.stringify(data[i]) + ");'>";
			html += "<i class='icon-edit icon-black'></i></a></span>&nbsp;";
			html += "<span><a title='删除' href='javascript:void(0);' onclick='delChild(" + JSON.stringify(data[i]) + ");'>";
			html += "<i class='icon-trash icon-black'></i></a></span></td></tr>";
		}
	}
	html += "</table>";
	$('#childGrid').html(html);
}

/**
  * loadChildTree:(加载子项目树)
  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
  * @author: 黄月
  * @dateTime: 2018-2-6 下午4:17:30
  * @param: @param data
  * @return: void
 */
function loadChildTree(data, project_squ){
	//子项目树
	var html = "";
	for (var i = 0; i < data.length; i++) {
		html+=" <li class='nav-header hidden-tablet border-bottom' style='display:block; padding-left:15px;'>";
		html+=" <a href='javascript:void(0);' style='color:#3f9fd9;' class='ajax-link'  flag='1' onclick='loadTree(this,2," + JSON.stringify(data[i]) + ")' id='" + data[i].child_squ + "'>";
		html+=" " + data[i].zdwxmmc + "</a>";
		html+=" <ul class='topnav'>";
		html+=" </ul>";
		html+=" </li>";
	}
	$("#" + project_squ + "").parent().find("ul").html(html);
	$("#" + project_squ + "").next("ul").accordion({
			accordion:false,
			speed: 300,
			closedSign: '[+]',
			openedSign: '[-]'
		});
}

/**
 * showChildMsg:(获取并显示子工程信息)
 * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
 * @author: 黄月
 * @dateTime: 2018-2-7 上午10:18:03
 * @param: @param childObject
 * @return: void
*/
function showChildMsg(childObject) {
	var html = "<div class='span3' style='padding:10px;'>";
	html += "<div class='projectList'><div>子项目名称：</div>" + childObject.zdwxmmc + "</div>";
	html += "</div>";
	html += "<div class='span3' style='padding:10px;'>";
	html += "<div class='projectList'><div>建筑高度：</div>" + childObject.jzgd + "m</div>";
	html += "</div>";
	html += "<div class='span3' style='padding:10px;'>";
	html += "<div class='projectList'><div>子项目编号：</div>" + childObject.childNum + "</div>";
	html += "</div>";
	html += "<div class='span3' style='padding:10px;'>";
	html += "<div class='projectList'><div>建筑类别：</div>" + childObject.jzlb + "</div>";
	html += "</div>";

	$('.child_msg').find("div .row-fluid").html(html);
}

/**
 * showAddChildModal:(新增项目模态框)
 * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
 * @author: 黄月
 * @dateTime: 2017-10-17 下午8:47:16
 * @param: 
 * @return: void
*/
function showAddChildModal(){
	$('#childHtmlTitle').html("新增子项目");
	$('#childForm')[0].reset();
	$("#child_squ").val("");
	$('#childModal').modal();
	
}

