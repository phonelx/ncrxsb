/**
 * 部位squ
 */
var _prochildsitesqu;

var _typesqu;

/**
 * 专业类别数组
 * 2018-5-29下午1:51:38
 * TODO
 */
var typeArr = new Array("S","F","D","Z");

$().ready(function(){
	//支架数量按钮操作
	//数量减少
    $(".quantity-remove").live("click",function(e){  
        var elemID = $(this).parent().attr("title");  
        var count = parseInt($(this).parent().find("." + elemID + "inp").val());
        if (count > 1) {
        	$("." + elemID + "inp").val(count - 1);  
        }
    }); 
    //数量增加
    $(".quantity-add").live("click", function(){  
        var elemID = $(this).parent().attr("title");  
        var count = parseInt($(this).parent().find("." + elemID + "inp").val());  
        $(this).parent().find("." + elemID + "inp").val(count + 1);  
    }); 
    
});


/**
  * numberTheHolder:(部位下支架流水号)
  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
  * @author: 黄月
  * @dateTime: 2018-6-2 下午4:17:24
  * @param: 
  * @return: void
 */
function numberTheHolder(){
	Modal.alert({
		title : "等待",
		msg : "正在更新支架流水号......",
	});
	setTimeout("numberHodler()", 2000);
	
}

function numberHodler() {
	$.ajax({
		type : "post",
		url : "numberTheHolder.do",
		timeout : 1321231232131213123,
		dataType : 'text',
		data : {
			siteSqu : _siteObj.site_squ,
		},
		success : function(data) {
			if (data == "success") {
				Modal.alert({
					title : "提示",
					msg : "更新支架流水号成功！",
					btnok : "确定"
				});
				loadHolderTreeAndList(_siteObj.site_squ);
				getHolderNotInSite();
			} else {
				Modal.alert({
					msg : '更新支架流水号异常！',
					title : '消息提示',
					btnok : '确定',
				});
			}
		},
		error : function() {
			Modal.alert({
				msg : 'ajax执行操作错误！',
				title : '消息提示',
				btnok : '确定',
			});
		}
	});
}

/**
  * showHolderMsg:(显示支架信息)
  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
  * @author: 黄月
  * @dateTime: 2018-2-8 下午5:39:11
  * @param: 
  * @return: void
 */
function showHolderMsg(holderObject) {
	var html = "<div class='span6' style='padding:10px;padding-top:15px;'><div>";
	if (holderObject.zp == "" || holderObject.zp == null) {
		html += "<img src='resource/images/mrtp.png' style='width:  100px; height: 70px;'/>";
	} else {
		html += "<img src='/upload/" + holderObject.zp + "' onerror='javascript:this.src=\"resource/images/mrtp.png\"'  style='width:  100px; height: 70px;'/>";
	}
	html += "</div>";
	html += "</div>";
	html += "<div class='span6' style='padding:10px;'>";
	html += "<div class='projectList'><div>支架名称：</div>" + holderObject.dxmc + "</div>";
	html += "<div class='projectList'><div>支架形式：</div>" + holderObject.zjxs + "</div>";
	html += "</div>";
	$('.holder_msg').find("div .row-fluid").html(html);
}

/**
 * 管线类型id
 * 2018-6-12下午5:03:29
 * TODO
 */
var _gxlxSqu = "1C695705ED374FD59A4E0A04399F3316";
/**
 * 管线规格id
 * 2018-6-12下午5:03:31
 * TODO
 */
var _gxggSqu = "703EB1AEE5524C05AFF1E17DD02597E3";
/**
 * 管线材质id
 * 2018-6-12下午5:03:33
 * TODO
 */
var _gxczSqu = "AC3C73E58461401A860B02D097B4084E";
/**
  * showAddHolderModal:(显示支架模态框)
  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
  * @author: 黄月
  * @dateTime: 2018-2-8 下午12:40:16
  * @param: 
  * @return: void
 */
function showAddHolderModal(){
	$('#holderModal').modal();
	//加载管线信息下拉列表
	$("#gxlx").loadGXSelect(_gxlxSqu);
	$("#gxgg").loadGXSelect(_gxggSqu);
	$("#gxcz").loadGXSelect(_gxczSqu);
	
	//查询该部位下没有的支架
	getHolderNotInSite();
}

/**
  * getHolderNotInSite:(查询该部位下不存在的支架)
  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
  * @author: 黄月
  * @dateTime: 2018-2-8 下午12:41:58
  * @param: 
  * @return: void
 */
function getHolderNotInSite(){
	//搜索
	$("#holderAll").uiGrid({ 
		url : "getHolderNotInSite.do",
		rowNum : 5,//每页显示记录数
		columns : [ {
			field : 'zp',
			title : '照片',
			width : 100,
			formatter : function(value, rec) {
				if (rec.zp == "" || rec.zp == null) {
					return '<img src="resource/images/mrtp.png" width="90px" height="60px">';
				} else {
					return '<img src="/upload/' + rec.zp + '" onerror="javascript:this.src=\'resource/images/mrtp.png\'" width="90px" height="60px">';
				}
			}
		},{
			field : 'dxmc',
			title : '单项名称',
			width : 600,
			formatter : function(value, rec) {
				return '<p><h4>支架名:' + rec.dxmc + '&nbsp;&nbsp;<small>支架形式:'+rec.zjxs+'</small></h4></p>';
		}
		}],
		divId : "#holderAll",
		showPage : 5,//显示
		toAdd : true,
		toAddEvent : toAdd,
		jsonPager : {
			records : "rowCount",//总记录数
			currentPage : 1,//当前访问页
			total : "pageCount",//总页数
		},
		data : {
			site_squ : _siteObj.site_squ
		}
	});
}

/**
  * toAdd:(新增支架)
  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
  * @author: 黄月
  * @dateTime: 2018-2-8 下午2:58:35
  * @param: 
  * @return: void
 */
function toAdd(row){
	if(!$("#gxlx").isEmpty() || !$("#gxcz").isEmpty() || !$("#gxgg").isEmpty()){
		Modal.alert({
			title : "提示",
			msg : "未获取到管道信息！",
			btnok : "确定"
		});
	}
	
	$.ajax({
		type : "post",
		url : "insertDxInfo.do",
		timeout : 1321231232131213123,
		dataType : 'json',
		data : {
			"proZj.psqu" : _siteObj.site_squ,
			"proZj.zjsqu" : row.dxsqu,
			"proZj.gxlxsqu" : $("#gxlx").val(),
			"proZj.gxczsqu" : $("#gxcz").val(),
			"proZj.gxggsqu" : $("#gxgg").val()
		},
		success : function(data) {
			if (data.status == "success") {
				Modal.alert({
					title : "提示",
					msg : "添加成功！",
					btnok : "确定"
				});
				//更新并加载支架(不弹框)
				numberHodler();
			} else {
				Modal.alert({
					msg : '执行添加操作异常！',
					title : '消息提示',
					btnok : '确定',
				});
			}
		},
		error : function() {
			Modal.alert({
				msg : 'ajax执行添加操作错误！',
				title : '消息提示',
				btnok : '确定',
			});
		}
	});
}

/**
  * loadHolderTreeAndList:(加载所有支架树和列表)
  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
  * @author: 黄月
  * @dateTime: 2018-2-7 下午2:54:59
  * @param: @param site_squ 部位id
  * @return: void
 */
function loadHolderTreeAndList(site_squ){
	
	_prochildsitesqu = site_squ;
	$.ajax({
		type : 'post',
		url : 'selectlistDxInfo.do',
		timeout : 1321231232131213123,
		dataType:"json",
		async : false,
		data:{
			squ:site_squ,
			type:""
		},
		success : function(data) {
			if (data.status == "success") {
				var list = data.result;
				//加载当前部位下支架专业类别树
				listTypeOfHolder(site_squ);
				//加载当前所有支架列表
				loadHolderList(list);
			} else {
				Modal.alert({
				    msg: data.result,
				    title: '消息提示',
				    btnok: '确定',
				    btncl:'取消'
				  });
			}
		},
		error : function() {
			Modal.alert({
			    msg: '支架列表加载出错',
			    title: '消息提示',
			    btnok: '确定',
			    btncl:'取消'
			  });
		}
	});
}

/**
  * listTypeOfHolder:(加载支架专业类别树)
  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
  * @author: 黄月
  * @dateTime: 2018-5-29 上午11:03:45
  * @param: list 支架列表（包含专业代码）
  * @param: site_squ 所属部位id
  * @return: void
 */
function listTypeOfHolder(site_squ){
	
	var html = "";
	for (var i = 0; i < typeArr.length; i++) {
		html += " <li class='nav-header hidden-tablet border-bottom' style='display:block; padding-left:15px;'>";
		html += " <a href='javascript:void(0);' style='color:#3f9fd9;' class='ajax-link'" ;
		html +=	"  flag='1' onclick='loadTree(this,4,\"" + typeArr[i] + "\")'  title='" + typeArr[i] + "' id='" + site_squ + typeArr[i] + "' >";
		html += typeArr[i] + "</a>";
		html += " <ul class='topnav'>";
		html += " </ul>";
		html += " </li>";
	}
	$("#" + site_squ + "").parent().find("ul").html(html);
	$("#" + site_squ + "").next("ul").accordion({
			accordion:false,
			speed: 300,
			closedSign: '[+]',
			openedSign: '[-]'
		});
}


/**
 * loadHolderTreeAndList:(加载支架树和列表)
 * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
 * @author: 黄月
 * @dateTime: 2018-2-7 下午2:54:59
 * @param: @param site_squ
 * @return: void
*/
function loadTypeOfHolderTreeAndList(site_squ, type){
	//保存当前专业类型id
	_typesqu = site_squ + type;
	
	$.ajax({
		type : 'post',
		url : 'selectlistDxInfo.do',
		timeout : 1321231232131213123,
		dataType:"json",
		async : false,
		data:{
			squ:site_squ,
			type:type
		},
		success : function(data) {
			if (data.status == "success") {
				var list = data.result;
				//加载当前专业下支架树
				loadHolderTree(list, _typesqu);
				//加载当前专业下支架列表
				loadHolderList(list);
			} else {
				Modal.alert({
				    msg: data.result,
				    title: '消息提示',
				    btnok: '确定',
				    btncl:'取消'
				  });
			}
		},
		error : function() {
			Modal.alert({
			    msg: '支架列表加载出错',
			    title: '消息提示',
			    btnok: '确定',
			    btncl:'取消'
			  });
		}
	});
}

/**
  * loadHolderTree:(加载支架树)
  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
  * @author: 黄月
  * @dateTime: 2018-2-7 下午2:56:52
  * @param: @param list
  * @param: @param typeId专业类别id(部位id+专业代码)
  * @return: void
 */
function loadHolderTree (list, typesqu) {
	var html = "";
	for (var i = 0;i < list.length; i++) {
			html += " <li class='nav-header hidden-tablet border-bottom' style='display:block; padding-left:15px;'>";
			html += " <a href='javascript:void(0);' style='color:#3f9fd9;' class='ajax-link'";
			html +=	"  flag='1' onclick='loadTree(this, 5, " + JSON.stringify(list[i]) + ")' title='" + list[i].dxmc + "' >";
			html += "" + list[i].dxmc.substr(0,12) + "...</a>";
			html += " <ul class='topnav'>";
			html += " </ul>";
			html += " </li>";
	}
	$("#" + typesqu + "").parent().find("ul").html(html);
	$("#" + typesqu + "").next("ul").accordion({
			accordion:false,
			speed: 300,
			closedSign: '[+]',
			openedSign: '[-]'
		});
	
	//加载支架列表
	loadHolderList(list);
}

/**
  * loadHolderList:(加载支架列表)
  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
  * @author: 黄月
  * @dateTime: 2018-2-7 下午2:56:55
  * @param: @param list
  * @return: void
 */
function loadHolderList (list) {
	var html= "";
	if (list.length == 0) {
		html += "<li class='border-bottom'>";
		html += "<a href='javascript:void(0);'>未查询到支架列表</a>";
		html += "</li>";
	}
	
	html += "<div class='row-fluid'>";
	for ( var i = 0; i < list.length; i++) {
		html += "<div class='span3' style='width:210px;'>";
		html += "<div class='img_list'>";
		html += "<a class='lightbox'>";
		if (list[i].zp == "" || list[i].zp == null) {
			html += "<img src='resource/images/mrtp.png' />";
		} else {
			html += "<img src='/upload/" + list[i].zp + "' onerror='javascript:this.src=\"resource/images/mrtp.png\"' />";
		}
		html += "</a>";
		html += "<div class='caption overflow_hidden'>";
		html += "<div class='span12'>";
		html += "<h4 class='text_black' title='" + list[i].dxmc + "'>" + list[i].dxmc + "</h4>";
		html += "<div class='text_number' title='" + list[i].zjxs + "'>" + list[i].zjxs + "</div>";
		html += "<div class='text_number' title='" + list[i].serialNumber + "'>流水号：" + list[i].serialNumber + "</div>";
		html += "</div>";
		html += " </div>";
            
		html += "<div class='bottom_btn'>";
		html += "<div class='buttonTeam'>";
		html += "<div class='f-left' style='margin-top:7px;'>";
		html += "当前数量：<span style='color:#369BD7;' class='holder_num" + (i + 1) + "'>" + list[i].sl + "</span>";
		html += "</div>";
		html += "<div class='f-right' style='margin-top:7px;'>";
		html += "<a href='javascript:void(0);'  title='计算报告' target='_blank' onclick='modal_reportParam(\""+list[i].squ+"\")'><i class='icon-zoom-in icon-black'></i></a>&nbsp;&nbsp;";
		html += "<a href='javascript:void(0);' onclick='editNum(this)'><i class='icon-edit icon-black'></i></a>&nbsp;&nbsp;";
		html += "<a href='javascript:void(0);' title='删除' onclick='deleteHolder(" + JSON.stringify(list[i]) + ")'><i class='icon-trash icon-black'></i></a>";
		html += "</div>";
		html += "</div>";
		html += "<div class='editAddNum' style='display: none;'>";
		html += "<div class='f-left'>";
		html += "<div class='form-group form-group-options addBottom'>";
		html += "<div title='" + (i + 1) + "' class='input-group'>";
		html += "<span class='quantity-remove btn' style='margin-top:-10px;'>";
		html += "<span class='icon icon-darkgray icon-minus'></span>";
		html += "</span>";
		html += "<input class='" + (i + 1) + "inp_hide hideInp' type='hidden' value='" + list[i].sl + "' />";
		html += "<input class='" + (i + 1) + "inp form-control quantity-count' type='text' value='' style='width:30px;' name='option[]' placeholder='1'/>";
		html += "<span class='quantity-add btn'  style='margin-top:-10px;'>";
		html += "<span class='icon icon-darkgray icon-plus'></span>";
		html += "</span>";
		html += "</div>";
		html += "</div>";
		html += "</div>";
		html += "<div class='f-right' style='margin-top:7px;'>";
		html += "<a href='javascript:void(0);' onclick='updateHolderNum(this, " + JSON.stringify(list[i]) + ");'>确定</a>&nbsp;";
		html += "<a href='javascript:void(0);' onclick='cancelNum(this)' >取消</a>&nbsp;";
		html += "</div>";
		html += "</div>";
		html += "</div>";
            
		html += "</div>";
		html += "</div>";
		
		if ((i + 1) % 4 == 0) {
			html += "</div><div class='row-fluid'>";
		}
	}
	$("#holderGrid").html(html + "</div>");
}

/**
  * deleteHolder:(删除支架)
  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
  * @author: 黄月
  * @dateTime: 2018-2-8 下午4:10:24
  * @param: @param obj
  * @return: void
 */
function deleteHolder(row){
	Modal.confirm({msg : "确认删除当前支架?"}).on(function(e){
		if (e) {
			$.ajax({
				type : "post",
				url : "delDxInfo.do",
				timeout : 1321231232131213123,
				data : {
					squ : row.squ,
				},
				success : function(data) {
					//更新并加载支架
					numberTheHolder();
				},
				error : function() {
					Modal.alert({
						msg : '删除支架出错！',
						title : '消息提示',
						btnok : '确定',
						btncl : '取消'
					});
				}
			});
		}
	});
}

/**
  * updateHolderNum:(修改支架数量)
  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
  * @author: 黄月
  * @dateTime: 2018-2-8 下午4:12:28
  * @param: @param index
  * @param: @param obj
  * @return: void
 */
function updateHolderNum(index, row){
	var count = $(index).parent().parent().find(".quantity-count").val();
	var r = /^[0-9]*[1-9][0-9]*$/;
	if (!r.test(count)) {
		Modal.alert({
			msg : '请输入正确的数字！',
			title : '消息提示',
			btnok : '确定',
		});
		$(index).parent().parent().find(".quantity-count").val("");
		$(index).parent().parent().find(".quantity-count").focus();
	} else {
		$.ajax({
			type : "post",
			url : "updateDxInfo.do",
			timeout : 1321231232131213123,
			data : {
				"proZj.sl" : count,
				"proZj.squ" : row.squ
			},
			success : function(data) {
				//加载当前支架列表
				loadHolderTreeAndList(row.site_squ);
				//重排序
				numberTheHolder();
			},
			error : function() {
				Modal.alert({
					msg : '修改支架出错！',
					title : '消息提示',
					btnok : '确定',
					btncl : '取消'
				});
			}
		});
	}
}

function editNum(index){
	//当前支架数量
	var count = $(index).parent().parent().parent().find(".hideInp").val();
	//输入框赋值
	$(index).parent().parent().parent().find(".quantity-count").val(count);
	$(index).parent().parent().parent().find(".editAddNum").show();
	$(index).parent().parent().parent().find(".buttonTeam").hide();
}

function cancelNum(index){
	$(index).parent().parent().parent().find(".editAddNum").hide();
	$(index).parent().parent().parent().find(".buttonTeam").show();
}


/**
  * loadPartsTreeAndList:(描述)
  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
  * @author: 黄月
  * @dateTime: 2018-2-8 下午5:05:24
  * @param: @param holderSqu
  * @return: void
 */
function loadPartsTreeAndList(holderSqu){
	loadZJList(1, holderSqu);
}

/**
 * loadZJList:(加载组件列表)
 * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
 * @author: 黄月
 * @dateTime: 2017-12-4 上午10:21:13
 * @param: 
 * @return: void
*/
var holderGrid;
function loadZJList(activeID, dxsqu){
	holderGrid = $("#zjList").holderGrid({
		url : "getKzzjBjList.do",
		data :{
			op:activeID,
			dxsqu:dxsqu,
			searchKey:""
		}
	});
}

/**
 * loadZJListByType:(根据类型加载组件列表)
 * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
 * @author: 黄月
 * @dateTime: 2017-12-4 上午11:09:41
 * @param: @param activeID
 * @return: void
*/
function loadZJListByType(activeID){
	var dxsqu = _holderObj.dxsqu;
	loadZJList(activeID, dxsqu);
}


/**
  * modal_reportParam:(计算报告书参数模态框)
  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
  * @author: 黄月
  * @dateTime: 2018-6-6 下午7:12:24
  * @param: squ 部位之间中间表id
  * @return: void
 */
function modal_reportParam(squ){
	$("#reportModal").modal();
	//获取当前支架_部位表id
	$("#zj_squ").val(squ);
	//加载所属系统下拉列表
	getSystem();
	
	//查询支架信息
	getT_Project_ZjById(squ);
	
	var flag = true;
	if (site_zj.service_system == "" ||site_zj.vb_num == "" ||site_zj.lb_num == "" ||
			site_zj.num == "" ||site_zj.holderImg == "" ||site_zj.lbImg == "" ||
			site_zj.vbImg == "" ) {
		flag = false;
	}
	$("#system option[value='"+site_zj.service_system+"']").attr("selected","selected");
	$("#vb_num").val(site_zj.vb_num);
	$("#lb_num").val(site_zj.lb_num);
	$("#num").val(site_zj.num);
	$("#img1").attr("src","/reportImg/" + site_zj.holderImg);
	$("#img2").attr("src","/reportImg/" + site_zj.lbImg);
	$("#img3").attr("src","/reportImg/" + site_zj.vbImg);
	
	
	//判断是否已上传计算书所需参数和图片（所属系统，侧向斜撑数量，纵向斜撑数量，载荷计算专业管道数量，支架形式简图，侧向/纵向受力分析图）
	//查询支架-项目（部位）中间表
	
	if (!flag) {
		//未上传,填写参数、上传图片
		$("#reportMsg").html("请完善计算书所需信息！");
		$("#showReportBtn").hide();
	} else {
		$("#reportMsg").html("");
		//显示按钮
		$("#showReportBtn").show();
	}

}

/**
 * getT_Project_ZjById:(描述)
 * TODO(获取支架部位信息)
 * @author: 黄月
 * @dateTime: 2018-6-6 下午8:34:27
 * @param: 
 * @return: void
*/
var site_zj = new Object();;
 function getT_Project_ZjById(squ){
	$.ajax({
		type : 'post',
		url : 'getT_Project_ZjById.do',
		dataType:"json",
		async : false,
		data:{
			"proZj.squ":squ,
		},
		success : function(data) {
			if (data.status == "success") {
				console.log(data);
				console.log(data.result);
				site_zj = data.result;
			} else {
				Modal.alert({
				    msg: data.result,
				    title: '消息提示',
				    btnok: '确定',
				    btncl:'取消'
				  });
			}
		}
	});
}


/**
  * report_link:(描述)
  * TODO(废弃)
  * @author: 黄月
  * @dateTime: 2018-6-6 下午7:10:50
  * @return: void
 */
function report_link(){
	//传值：项目id,子项目id,部位id,部位支架id
	window.open("geToCalculationIndex.do?prosqu="+_projectObj.project_squ+"&childsqu="
			+_childObj.child_squ+"&sitesqu="+_siteObj.site_squ+"&zjsqu="+$("#zj_squ").val(),"_black");
}

/**
  * getSystem:(获取所属系统下拉列表)
  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
  * @author: 黄月
  * @dateTime: 2018-6-19 下午7:01:17
  * @param: 
  * @return: void
 */
function getSystem(){
	
	$.ajax({
		type : 'post',
		url : 'getParamConfig.do',
		dataType:"json",
		async : false,
		data:{
			param:'DMLB',//查询参数
			search:'LBXS',//条件
		},
		success : function(data) {
		//	console.log(data);
			var html = "";
			for ( var i = 0; i < data.length; i++) {
				console.log(data[i].LBMC);
				html += "<option value='" + data[i].SQU + "'>" + data[i].LBMC + "</option>";
			}
			$("#system").html(html);
		}
	});
}


/**
  * loadGXSelect:(加载管线信息下拉列表)
  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
  * @author: 黄月
  * @dateTime: 2018-6-12 下午4:48:46
  * @param:  pid 管线信息父id
  * @return: void
 */
(function($){
$.fn.loadGXSelect = function(pid) {
	// 页面DOM元素ID
	var domId = this.attr("id");
	
	var util= {
		load : function(pid) {
			$.ajax({
				type : 'post',
				url : 'queryGdlxMenu.do',
				dataType:"json",
				async : false,
				data:{
					pid:pid,
				},
				success : function(data) {
					var html = "";
					var list = data.gdlxmenu;
					var len = list.length;
					for ( var i = 0; i < len; i++) {
						html += "<option value='" + list[i].SQU + "' title='" + list[i].XH + "'>" + list[i].XH + "</option>"
					}
					$("#" + domId + "").html(html);
				}
			});
		}
	}
	util.load(pid);
	return util;
};
})(jQuery);


/**
  * addReport:(添加或修改报告所需信息)
  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
  * @author: 黄月
  * @dateTime: 2018-6-21 下午6:44:33
  * @param: 
  * @return: void
 */
function addReport(){
	var formData = new FormData($("#reportForm")[0]);
	console.log(formData);
	$.ajax({
		type : 'post',
		url : 'updateReportParam.do',
		timeout : 1321231232131213123,
		data : formData,
		contentType: false, // 注意这里应设为false
		processData: false,
		cache: false,
		dataType:"json",
		success : function(data) {
			if (data.status == "success") {
				Modal.alert({
				    msg: "数据录入成功",
				    title: '消息提示',
				    btnok: '确定',
				    btncl:'取消'
				  });
				modal_reportParam($("#zj_squ").val());
			} else {
				Modal.alert({
				    msg: data.result,
				    title: '消息提示',
				    btnok: '确定',
				    btncl:'取消'
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
  * previewImage:(文件上传读取)
  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
  * @author: 黄月
  * @dateTime: 2018-6-21 下午6:36:38
  * @param: @param index
  * @return: void
 */
function previewImage(index){
	var file = index.files[0];
    
 //   console.log(file);
 //   console.log("file.size = " + file.size);  //file.size 单位为byte

    var reader = new FileReader();

    //读取文件过程方法
    reader.onloadstart = function (e) {
        console.log("开始读取....");
    }
    reader.onprogress = function (e) {
        console.log("正在读取中....");
    }
    reader.onabort = function (e) {
        console.log("中断读取....");
    }
    reader.onerror = function (e) {
        console.log("读取异常....");
    }
    reader.onload = function (e) {
        console.log("成功读取....");
        console.log($(index).prev().attr("src"));
        $(index).prev().attr("src",e.target.result);
    }

    reader.readAsDataURL(file)
}