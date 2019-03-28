function selectyjQuery(pageNo){
//	$('#showYJQuery').datagrid({
//		fit:true,
//		nowrap: false,
//		singleSelect:true,
//		collapsible:true,
//		url:'listManage.do',
//		pagination:true,
//		idField : 'id',
//		rownumbers:true,
//		remoteSort: false,
//		queryParams : {
//			isquery : "1"
//		},
//		columns:[[
//			{title:'预警标题',field:'yjbt',width:fixWidth(0.08),align:'center',sortable:true},
//		    {title:'预警人姓名',field:'name',width:fixWidth(0.08),align:'center',	sortable:true},
//		    {title:'预警人民族',field:'mz',width:fixWidth(0.08),sortable:true,align:'center',
//		    	formatter:function(value,rec){
//		    		if(value==""){
//		    			return "暂未选择";
//		    		}else{
//		    			return value;
//		    		}
//		      }},
//		    {title:'是否禁用',field:'flag',width:fixWidth(0.05),align:'center',sortable:true,
//		    	formatter:function(value,rec){
//		    		if(value=="2"){
//		    			return "是";
//		    		}else{
//		    			return "否";
//		    		}
//		      }},
//		    {title:'创建日期',field:'createtime',width:fixWidth(0.12),align:'center',sortable:true},
//		    {title:'失效日期',field:'stoptime',align:'center',width:fixWidth(0.12)},
//		    {title:'预警人口数',field:'sfhm',align:'center',width:fixWidth(0.08)},
//		    {field :'id',title : '详细信息',width : fixWidth(0.09),align : 'center',
//		     sortable : true,formatter : function(value, rec) {
//					return "<a class='xxAs' href='javascript:void(0);' onclick='detialInformations("+value+")'>详细信息</a>";
//			 	}
//		    }
//		]]
//	});
	$("#showYJQuery").uiGrid({
		url : "listManage.do",
		rowNum : 10,//每页显示记录数
		columns : [ {
			title:'预警标题',
			field:'yjbt',
			width:fixWidth(0.08)
		}, {
			title:'预警人姓名',
			field:'name',
			width:fixWidth(0.08)
		}, {
			title:'预警人民族',
			field:'mz',
			width:fixWidth(0.08),
	    	formatter:function(value,rec){
	    		if(value==""){
	    			return "暂未选择";
	    		}else{
	    			return value;
	    		}
	    	}
	    }, {
	    	title:'是否禁用',
	    	field:'flag',
	    	width:fixWidth(0.05),
	    	formatter:function(value,rec){
	    		if(value=="2"){
	    			return "是";
	    		}else{
	    			return "否";
	    		}
	    	}
	    }, {
	    	title:'创建日期',
	    	field:'createtime',
	    	width:fixWidth(0.12)
	    }, {
	    	title:'失效日期',
	    	field:'stoptime',
	    	width:fixWidth(0.12)
	    }, {
	    	title:'预警人口数',
	    	field:'sfhm',
	    	width:fixWidth(0.08)
	    }, {
	    	title : '详细信息',
	    	field :'id',
	    	width : fixWidth(0.09),
	    	sortable : true,formatter : function(value, rec) {
				return "<a class='xxAs' href='javascript:void(0);' onclick='detialInformations("+value+")'>详细信息</a>";
		 	}
	    } ],
		divId : "#showYJQuery",
		showPage : 5,//显示
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

function detialInformations(value) {
	$("#sfzh").val("");
	$("#starttimes").val("");
	$("#endtimes").val("");
//	closeTabs();
	$.ajax({
		type : 'post',
		url : 'getSourceTables.do',
		data : {
			yjid:value
		},
		datatype : 'json',
		async : false,
		success : function(data) {
//			$('#detailInfors').window('open');
//			$('#detailInfors').panel('setTitle', '详细信息');
//			$('#getYjid').attr('value', value);
//			var source = data.substring(data.indexOf('['),data.indexOf(']') + 1);// 得到数据源
//			table = data.substring(data.indexOf('table') + 'table'.length + 2,data.length - 1);// 得到table
//			addSources(source);
//			addTables(table);
			
			document.getElementById("titleName").innerHTML = "详细信息";
			$('#getYjid').attr('value', value);
			var source = data.substring(data.indexOf('['),data.indexOf(']') + 1);// 得到数据源
			table = data.substring(data.indexOf('table') + 'table'.length + 2,data.length - 1);// 得到table
			addSources(source);
			addTables(table);
			
			$("#detailInfors").modal();
		},
		error : function() {
			alert('请求失败！');
		}
	});
}

var table = '';
function closeTabs() {
	var arrTitle = new Array();
	var tabs = $('#showTabss').tabs("tabs");// 获得所有小Tab
	var tCount = tabs.length;
	if (tCount > 0) {
		// 收集所有Tab的title
		for ( var i = 0; i < tCount; i++) {
			arrTitle.push(tabs[i].panel('options').title);
		}
		// 根据收集的title一个一个删除=====清空Tab
		for ( var i = 0; i < arrTitle.length; i++) {
			$('#showTabss').tabs("close", arrTitle[i]);
		}
	}
}

function addSources(source) {
	// 在下拉列表添加数据源
	$('#selectSources').empty();
	var moSel=0;
	var selObj = $("#selectSources");
	var soujson = eval('(' + source + ')');
	for ( var i = 0; i < soujson.length; i++) {
		$('#selectSouInputs').attr('value',soujson[i].squ);
		$("#selectSources").html(soujson[i].title);
	}
}

function addTables(table) {
	$('#selectTables').empty();
	// 添加数据表
	var tabObj = $('#selectTables');
    var xz = $("#checkxx");
	var tabjson = eval('(' + table + ')');
	var isBR=0;
	
	tabObj.append("<input type='checkbox' id='allchecks' name='hh' style='vertical-align:middle;'> 全选/取消</input>");
	for ( var j = 0; j < tabjson.length; j++) {
		if ($("#selectSouInputs").val() == tabjson[j].dataSourceSqu) {
			if (isBR % 5 == 0 && isBR > 0) {
				tabObj.append("<br/>");
			}
			tabObj.append("<input class='tabK' type='checkbox' name='selTabs' id='con" 
					+ tabjson[j].squ + "' style='vertical-align:middle;margin-right:5px' value='" + tabjson[j].name
					+ "'><span>" + tabjson[j].title+"</span> ["+tabjson[j].count+"]</input>");
			
			isBR++;
		}
	}
}

/**
 * 取消一个后取消全选
 */
$(".tabK").live('change',function(){
	var size = $("input[name=selTabs]").length;
	var checksize =0;
	$("input[name='selTabs']:checkbox").each(function() {
		if (!$(this).attr('checked')) {
			$("#allchecks").attr("checked",false);
			return;
		}else{
			checksize++;
		}
	});
	if(checksize==size){
		$("#allchecks").attr("checked",true);
	}
});
/**
 * 全选
 */
$("#allchecks").live('change',function(){
	var tabObj = $('#selectTables');
	
	var tabjson = eval('(' + table + ')');
	
	if(this.checked==true){
		for(var i =0;i<tabjson.length;i++){
			$("#con"+tabjson[i].squ).attr("checked",true);
		}
	}
	if(this.checked==false){
		for(var i =0;i<tabjson.length;i++){
			$("#con"+tabjson[i].squ).attr("checked",false);
		}
	}
});

function lastButtons() {
	nowpageno = 0;
	if($("#starttimes").val()!="" && $("#endtimes").val()==""){
		alert("结束时间不能为空！");
		return ;
	}
	if($("#starttimes").val()=="" && $("#endtimes").val()!=""){
		alert("开始时间不能为空！");
		return ;
	}
//	closeTabs();// 关闭tab
	var strChecked = getCheckedValues();
	if (!strChecked) {
		alert("选择查看类型");
		return false;
	}
	$.ajax({
		type : 'post',
		url : 'detialInformations.do',
		data : {
			dbid : $('#selectSouInputs').val(),
			id : $('#getYjid').val(),
			checked : strChecked,
			pageNo:1,
			pageSize:10,
			sfhm:$("#sfzh").val(),
			rq1:$("#starttimes").val(),
			rq2:$("#endtimes").val()
		},
		datatype : 'json',
		async : false,
		success : function(data) {
			var text = textStr.substring(0, textStr.length - 1);
			text = text.split(',');	
			//由于为分页提供参数tabIDb,khmID
			var sourceID=$('#selectSouInputs').val();
			var yiid=$("#getYjid").val();
			var tabID=strChecked.substring(0,strChecked.length - 1);
			tabID=strChecked.split(',');
			var json = eval('(' + data + ')');
			var tabTitle="";//
			var tabContent="";
			// 添加Tabs标签页，如果已经有标签页，则将已有的标签页关闭
			for ( var j = 0; j < text.length; j++) {
//				$('#showTabss').tabs('close', text[j]);
//				$('#showTabss').tabs('add', {
//					title : text[j],
//					content : pjTabsLists(json.rows[j],tabID[j],yiid,sourceID),
//					closable : true
//				});
				//得到每个表中有几条数据-----start
				var count=0;
				var tableName="";
				for(var key in json.rows[j]){
					//WB_SWRY_NEW@0
					tableName=key.split("@")[0];
					count=key.split("@")[1];
				}
				//得到每个表中有几条数据-----end

				if(j==0){
					tabTitle = tabTitle+"<li class='active'><a href='#"+tableName+"' data-toggle='tab'>"+text[j]+"</a></li>";
					tabContent = tabContent+"<div class='tab-pane fade in active' id='"+tableName+"'>"
						+pjTabsLists(json.rows[j],tabID[j],yiid,sourceID)+"</div>";
				}else{
					tabTitle = tabTitle+"<li><a href='#"+tableName+"' data-toggle='tab'>"+text[j]+"</a></li>";
					tabContent = tabContent+"<div class='tab-pane fade' id='"+tableName+"'>"
						+pjTabsLists(json.rows[j],tabID[j],yiid,sourceID)+"</div>";
				}
				
				document.getElementById("showTabss").innerHTML = tabTitle;
				document.getElementById("showDivContents").innerHTML = tabContent;
			}
//			textStr = '';
//			$("#showTabss").tabs({ selected: 1 });
		},
		error : function() {
			alert('请求失败！');
		}
	});
}

var textStr = '';
function getCheckedValues() {
	textStr="";
	var str = '';
	$("input[name='selTabs']:checkbox").each(function() {
		if ($(this).attr('checked')) {
			str=str+$(this).attr('id').substring(3,$(this).attr('id').length)+',';
			textStr = textStr + $(this).next('span').text() + ',';
		}
	});
	return str;
}

var nowpagesize =10;
var nowpageno =0;
function pjTabsLists(data,tabid,yiid,sourceID) {
	var pjtab = '';
	var countPageNo='';
//	alert(data+'----'+tabid+'----'+bkhmid);
	pjtab = pjtab + "<table id='s"+tabid+"' border='0' cellpadding='0' cellspacing='0' style='float:left;width:100%;'>";
	pjtab = pjtab + '<tr>';
	for ( var key in data) {
		countPageNo=key.split('@')[1];
		countPageNo=parseInt(countPageNo/10)+1;
		var key_a = data[key];
		for ( var a = 0; a < key_a.length; a++) {
			if (a == 0) {
				pjtab = pjtab + '<th style="width:16px;height:30px;"></th>';
			}
			for ( var m in key_a[a]) {
				if (a == 0) {
					pjtab = pjtab + '<th class="ttd" sytle="text-align: center;">' + m + '</th>';
				}
			}
			//pjtab = pjtab + '</tr>';

			// 添加列表数据
			pjtab = pjtab + '<tr>';
			if(nowpageno==0){
				pjtab = pjtab + '<td class="ttd" style="width:16px;height:30px;">' + (a + 1) + '</td>';
			}else{
				var si = (parseInt(a) + parseInt(1))+parseInt(nowpageno)*parseInt(nowpagesize);
				pjtab = pjtab + '<td class="ttd" style="width:16px;height:30px;">' +si+ '</td>';
			}
			for ( var n in key_a[a]) {
				pjtab = pjtab + '<td class="ttd">' + key_a[a][n] + '</td>';
			}
			pjtab = pjtab + '</tr>';
		}
	}
	pjtab = pjtab + '</tr>';
	pjtab = pjtab + '</table>';
	
	//详细信息分页按钮及当前页码，共多少页部分
	pjtab=pjtab+'<div id="fenyeIDs" class="fenyeCs">';
	pjtab=pjtab+'<input id="getTabids'+tabid+'" type="hidden" value="'+tabid+'" />';
	pjtab=pjtab+'<input id="yjid'+tabid+'" type="hidden" value="'+yiid+'" />';
	
	pjtab=pjtab+'<table id="fenyeTables" border="0" cellpadding="0" cellspacing="0"><tbody><tr>';
	pjtab=pjtab+'<td><a id="firsts'+tabid+'" class="" href="javascript:void(0);" onclick="goPages(id,'+tabid+');">|<</a></td>';
	pjtab=pjtab+'<td><a id="shangs'+tabid+'" class="" href="javascript:void(0)" onclick="goPages(id,'+tabid+');"><<</a></td>';
	
	pjtab=pjtab+'<td>|</td>';
	pjtab=pjtab+'<td>第<input id="pageNos'+tabid+'" type="text" value="1" size="1" style="width:25px;margin:0px;" readonly="readonly"></input>页</td>';
	pjtab=pjtab+'<td>共<span id="countNos'+tabid+'">'+countPageNo+'</span>页</td>';
	pjtab=pjtab+'<td>|</td>';
	
	pjtab=pjtab+'<td><a id="xias'+tabid+'" class="" href="javascript:void(0)" onclick="goPages(id,'+tabid+');">>></a></td>';
	pjtab=pjtab+'<td><a id="lasts'+tabid+'" class="" href="javascript:void(0)" onclick="goPages(id,'+tabid+');">>|</a></td>';
	pjtab=pjtab+'</tr></tbody></table></div>';
	return pjtab;
}


function goPages(id,tabid){
	var sourceID=$('#selectSouInputs').val();
	var yjid=$('#yjid'+tabid).val();
	var gettabid=$('#getTabids'+tabid).val();
	var pageno=$('#pageNos'+tabid).val();
	var pagesize=10;
	var countNo=$('#countNos'+tabid).html();//总页数
	if(id=="firsts"+tabid){
		pageno=1;
	}else if(id=="shangs"+tabid){
		if(pageno==1){
			pageno=1;
		}else{
			pageno=parseInt(pageno)-1;
		}
	}else if(id=="xias"+tabid){
		if(pageno==countNo){
			pageno=countNo;
		}else{
			pageno=parseInt(pageno)+1;
		}
	}else if(id=="lasts"+tabid){
		pageno=countNo;
	}
	nowpageno =pageno-1;
	$.ajax({
		type:'post',
		url:'detialTabPages.do',
		data:{
			dbid:$('#selectSouInputs').val(),
			id:yjid,
			checked:gettabid,
			pageNo:pageno,
			pageSize:pagesize,
			sfhm:$("#sfzh").val(),
			rq1:$("#starttimes").val(),
			rq2:$("#endtimes").val()
		},
		datatype:'json',
		async:false,
		success:function(data){
			var json=eval('(' + data + ')');
			var currTab =$('#showTabss').tabs('getSelected');
			$('#showTabss').tabs('update', {
				tab : currTab,
				options:{
					content:pjTabsLists(json.rows[0],tabid,yjid,sourceID)
				}
			});
			//更新页码
			var total=parseInt(json.total/10)+1;
			$('#countNos'+tabid).html(countNo);
			if(id=="firsts"+tabid){
				$('#pageNos'+tabid).attr('value',1);
			}else if(id=="shangs"+tabid){
				if(pageno==1){
					$('#pageNos'+tabid).attr('value',1);
				}else{
					$('#pageNos'+tabid).attr('value',parseInt(pageno));
				}
			}else if(id=="xias"+tabid){
				if(pageno==total){
					$('#pageNos'+tabid).attr('value',total);
				}else{
					$('#pageNos'+tabid).attr('value',parseInt(pageno));
				}
			}else if(id=="lasts"+tabid){
				$('#pageNos'+tabid).attr('value',total);
			}
		},
		error:function(){
			alert('请求失败！');
		}
	});
}
$("#starttimes").blur(function(){
	var startLgi = $('#starttimes').val().replace(/[-,:,\s]/g, "");
	var endLgi = $('#endtimes').val().replace(/[-,:,\s]/g, "");
	var lgiS = new Number(startLgi);
	var lgiE = new Number(endLgi);
	if(endLgi!=""){
		if (lgiS > lgiE) {
			alert('结束时间不能小于开始时间！');
			 $('#endtimes').val("");
		}
	}	
});
$("#endtimes").blur(function(){
	var startLgi = $('#starttimes').val().replace(/[-,:,\s]/g, "");
	var endLgi = $('#endtimes').val().replace(/[-,:,\s]/g, "");
	var lgiS = new Number(startLgi);
	var lgiE = new Number(endLgi);
	if(endLgi!=""){
		if (lgiS > lgiE) {
			alert('结束时间不能小于开始时间！');
			 $('#endtimes').val("");
		}
	}	
});
function fixWidth(percent)     
{     
    return (document.body.clientWidth - 5) * percent ;      
}  
