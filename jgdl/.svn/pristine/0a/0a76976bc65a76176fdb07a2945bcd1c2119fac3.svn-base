showListDiv();
function showListDiv() {
	$("#showListDiv").uiGrid({
		url : "selectList.do",
		rowNum : 10,//每页显示记录数
		columns : [{
			field : 'bt',
			title : '布控标题',
			width : fixWidth(0.1)
		}, {
			field : 'bkhm',
			title : '身份证号码/车牌号码',
			width : fixWidth(0.136)
		}, {
			field : 'bklx',
			title : '布控类型',
			width : fixWidth(0.08),
			formatter : function(value, rec) {
				if (value == 1) {
					return "身份证";
				} else if (value == 2) {
					return "车牌号";
				} else {
					return value;
				}
			}
		}, {
			field : 'dxjsdh',
			title : '短信接收电话',
			width : fixWidth(0.1),
		}, {
			field : 'bkr',
			title : '布控人',
			width : fixWidth(0.09),
		}, {
			field : 'cjsj',
			title : '布控时间',
			width : fixWidth(0.16),
		}, {
			field : 'stopTime',
			title : '有效时间',
			width : fixWidth(0.16),
		}, {
			field : 'status',
			title : '布控状态',
			width : fixWidth(0.07),
			formatter : function(value, rec) {
				if (value == 1) {
					return "进行中";
				} else if (value == 2) {
					return "已完成";
				} else {
					return value;
				}
			}
		} ],
		divId : "#showListDiv",
		showPage : 5,//显示
		showView : true,
		showViewEvent : showEvent,
		jsonPager : {
			root : "datas",// 当前页数据
			records : "rowCount",//总记录数
			currentPage : 1,//当前访问页
			total : "pageCount"//总页数
		},
		data:{
			bt : $('#bt').val(),
			bkhm : $('#bkhm').val(),
			bkr : $('#bkr').val(),
			status : $('#status').val(),
			startTime : $('#startTime').val(),
			endTime : $('#endTime').val()
		}
	});
}
function showEvent(row){
	detialInformation(row);
}

function clearForm() {
	$('#conditionForm').form('clear');
	$('#status option[value=0]').attr('selected', 'selected');
}

function selectForm() {
	showListDiv();
}

var table = '';

function closeTab() {
	var arrTitle = new Array();
	var tabs = $('#showTabs').tabs("tabs");// 获得所有小Tab
	var tCount = tabs.length;

	if (tCount > 0) {
		// 收集所有Tab的title
		for ( var i = 0; i < tCount; i++) {
			arrTitle.push(tabs[i].panel('options').title);
		}
		// 根据收集的title一个一个删除=====清空Tab
		for ( var i = 0; i < arrTitle.length; i++) {
			$('#showTabs').tabs("close", arrTitle[i]);
		}
	}
}

function closeMTab() {
	var arrTitle = new Array();
	var tabs = $('#mshowTabs').tabs("tabs");// 获得所有小Tab
	var tCount = tabs.length;

	if (tCount > 0) {
		// 收集所有Tab的title
		for ( var i = 0; i < tCount; i++) {
			arrTitle.push(tabs[i].panel('options').title);
		}
		// 根据收集的title一个一个删除=====清空Tab
		for ( var i = 0; i < arrTitle.length; i++) {
			$('#mshowTabs').tabs("close", arrTitle[i]);
		}
	}
}

function detialInformation(obj) {
	$('#showBkhm').attr('value','');
	//closeTab();
	
	$.ajax({
		type : 'post',
		url : 'getSourceTable.do',
		data : {
			bid:obj.bid,
			bt:obj.bt,
			bkhm : obj.bkhm,
			dxjsdh:obj.dxjsdh,
			bkr:obj.bkr
		},
		datatype : 'json',
		async : false,
		success : function(data) {
			$('#detailInfor').modal();
			$('#titleName').html('详细信息');
			$('#getBkhm').attr('value', obj.bkhm);

			var json=eval('('+data+')');
			
			var source = json.source;// 得到数据源
			table = json.table;// 得到table

			addSource(source);
			addTable(table);
			lastButton();
		},
		error : function() {
			alert('请求失败！');
		}
	});
//	var sr=0;
//	var selectedRow=null;
//	$('#showListDiv').datagrid({
//        onClickRow : function(index, row){
//        	selectedRow = $('#showListDiv').datagrid('getSelected');
//			if (!selectedRow) {
//				alert("请选择要查询信息！");
//				return false;
//			}
//
//			if(sr==0){
////				alert(selectedRow.bkhm);
//				sr=1;
//				$.ajax({
//					type : 'post',
//					url : 'getSourceTable.do',
//					data : {
//						bid:selectedRow.bid,
//						bt:selectedRow.bt,
//						bkhm : selectedRow.bkhm,
//						dxjsdh:selectedRow.dxjsdh,
//						bkr:selectedRow.bkr
//					},
//					datatype : 'json',
//					async : false,
//					success : function(data) {
//						$('#detailInfor').window('open');
//						$('#detailInfor').panel('setTitle', '详细信息');
//						$('#getBkhm').attr('value', selectedRow.bkhm);
//
//						var json=eval('('+data+')');
//						
//						var source = json.source;// 得到数据源
//						table = json.table;// 得到table
//						
//						addSource(source);
//						addTable(table);
//						lastButton();
//					},
//					error : function() {
//						alert('请求失败！');
//					}
//				});
//			}
//        }
//	});
}

function detial(bksource,bkhm) {
	var sr=0;
	//if(sr!=0){
		closeMTab();
	//}
	if(sr==0){
		sr=1;
		$.ajax({
			type : 'post',
			url : 'getSourceTable.do',
			data : {
				bkSource:bksource,
				bkhm:bkhm
			},
			datatype : 'json',
			async : false,
			success : function(data) {
				var json=eval('('+data+')');
				
				var source = json.source;// 得到数据源
				table = json.table;// 得到table
				var bkhmd=json.bkhm;//得到布控号码

				$('#mselectSouInput').attr('value',source[0].squ);
				$('#mselectSource').html(source[0].title);
				
				$('#mgetBkhm').attr('value', bkhm);
			},
			error : function() {
				alert('请求失败！');
			}
		});
	}
}

function addSource(source) {
	// 在下拉列表添加数据源
	$('#selectSource').empty();
	var moSel=0;
	var selObj = $("#selectSource");
	for ( var i = 0; i < source.length; i++) {
		$('#selectSouInput').attr('value',source[i].squ);
		$("#selectSource").html(source[i].title);
	}
}

//添加表名信息，隐藏
function addTable(table){
	var tableStr="";
	var tableTitle="";
	for(var j=0;j<table.length;j++){
		tableStr=tableStr+table[j].squ+",";
		tableTitle=tableTitle+table[j].title+",";
	}
	
	$('#allTable').attr('value',tableStr);
	$('#tableTitle').attr('value',tableTitle);
}

function lastButton() {
	//closeTab();// 关闭tab
	var strChecked = $('#tableTitle').val();
	var strTableID= $('#allTable').val();

	$.ajax({
		type : 'post',
		url : 'detialInformation.do',
		data : {
			squ : $('#selectSouInput').val(),
			bkhm : $('#showBkhm').val(),
			checked : $('#allTable').val(),
			pageNo:1,
			pageSize:10
		},
		datatype : 'json',
		async : false,
		success : function(data) {
			var text = strChecked.substring(0, strChecked.length - 1);
			text = text.split(',');
			
			//由于为分页提供参数tabIDb,khmID
			var sourceID=$('#selectSouInput').val();
			var tabID=strTableID.substring(0,strTableID.length - 1);
			tabID=strTableID.split(',');
			var bkhmID=$('#getBkhm').val();
			
			var json = eval('(' + data + ')');
			var tabTitle="";//
			var tabContent="";
			// 添加Tabs标签页，如果已经有标签页，则将已有的标签页关闭
			for ( var j = 0; j < text.length; j++) {
				//得到每个表中有几条数据-----start
				var count=0;
				var tableName="";
				for(var key in json.rows[j]){
					//WB_SWRY_NEW@0
					tableName=key.split("@")[0];
					count=key.split("@")[1];
				}
				//得到每个表中有几条数据-----end
//				$('#showTabs').tabs('close', text[j]);
//				$('#showTabs').tabs('add', {
//					title : text[j]+"("+count+")",
//					content : pjTabsList(json.rows[j],tabID[j],bkhmID,sourceID),
//					closable : true
//				});
//				$('#showTabs').tabs('select', 1);
				// 
				if(j==0){
					tabTitle = tabTitle+"<li class='active'><a href='#"+tableName+"' data-toggle='tab'>"+text[j]+"</a></li>";
					tabContent = tabContent+"<div class='tab-pane fade in active' id='"+tableName+"'>"
						+pjTabsList(json.rows[j],tabID[j],bkhmID,sourceID)+"</div>";
				}else{
					tabTitle = tabTitle+"<li><a href='#"+tableName+"' data-toggle='tab'>"+text[j]+"</a></li>";
					tabContent = tabContent+"<div class='tab-pane fade' id='"+tableName+"'>"
						+pjTabsList(json.rows[j],tabID[j],bkhmID,sourceID)+"</div>";
				}
				
				document.getElementById("showTabs").innerHTML = tabTitle;
				document.getElementById("showDivContent").innerHTML = tabContent;
			}
		},
		error : function() {
			alert('请求失败！');
		}
	});
}

function mButton() {
	closeMTab();// 关闭tab
	var strChecked = $('#mTab').val();

	$.ajax({
		type : 'post',
		url : 'detialInformation.do',
		data : {
			squ : $('#mselectSouInput').val(),
			bkhm :  $('#mxiala').html(),
			checked : $('#mTab').val(),
			pageNo:1,
			pageSize:10
		},
		datatype : 'json',
		async : false,
		success : function(data) {
			//由于为分页提供参数tabIDb,khmID
			var sourceID=$('#mselectSouInput').val();
			
			var tabID=strChecked.substring(0,strChecked.length - 1);
			var bkhmID=$('#mgetBkhm').val();
			
			var json = eval('(' + data + ')');
			
			//得到每个表中有几条数据-----start
			var count=0;
			for(var key in json.rows[0]){
				//WB_SWRY_NEW@0
				count=key.split("@")[1];
			}
			//得到每个表中有几条数据-----end
			
			// 添加Tabs标签页，如果已经有标签页，则将已有的标签页关闭
			$('#mshowTabs').tabs('close', $('#tabSpan').html());
			$('#mshowTabs').tabs('add', {
				title : $('#tabSpan').html()+"("+count+")",
				content : mpjTabsList(json.rows[0],tabID,bkhmID,sourceID),
				closable : true
			});
		},
		error : function() {
			alert('请求失败！');
		}
	});
}

function pjTabsList(data,tabid,bkhmid,sourceID) {
	var pjtab = '';
	var countPageNo='';
//	alert(data+'----'+tabid+'----'+bkhmid);
	pjtab = pjtab + "<div style='width:100%;height:380px;overflow-x:auto;'>";
	pjtab = pjtab + "<table id='"+tabid+"' border='0' cellpadding='0' cellspacing='0' style=''>";

	pjtab = pjtab + '<tr>';
	for ( var key in data) {
		countPageNo=key.split('@')[1];
		countPageNo=parseInt(countPageNo/10)+1;
		var key_a = data[key];
		for ( var a = 0; a < key_a.length; a++) {
			// 添加列表标题行
			//pjtab = pjtab + '<tr>';
			if (a == 0) {
				pjtab = pjtab + '<th style="width:16px;height:30px;"></th>';
			}
			for ( var m in key_a[a]) {
				if (a == 0&&m!='R1') {
					pjtab = pjtab + '<th class="ttd">' + m + '</th>';
				}
			}
			//pjtab = pjtab + '</tr>';

			// 添加列表数据
			pjtab = pjtab + '<tr>';
			pjtab = pjtab + '<td class="ttd" style="width:16px;height:30px;">' + (a + 1) + '</td>';
			for ( var n in key_a[a]) {
				if(key_a[a][n]==''||key_a[a][n]==null){
					pjtab = pjtab + '<td class="ttd"></td>';
				}else if(n!='R1'){
					pjtab = pjtab + '<td class="ttd">' + key_a[a][n] + '</td>';
				}
			}
			pjtab = pjtab + '</tr>';
		}
	}
	pjtab = pjtab + '</tr>';
	pjtab = pjtab + '</table>';
	pjtab = pjtab + '</div>';
	
	//详细信息分页按钮及当前页码，共多少页部分
	pjtab=pjtab+'<div id="fenyeID" class="fenyeC">';
	pjtab=pjtab+'<input id="getTabid'+tabid+'" type="hidden" value="'+tabid+'" />';
	pjtab=pjtab+'<input id="bkhmid'+tabid+'" type="hidden" value="'+bkhmid+'" />';
	
	pjtab=pjtab+'<table id="fenyeTable" border="0" cellpadding="0" cellspacing="0"><tbody><tr>';
	pjtab=pjtab+'<td><a id="first'+tabid+'" class="" href="javascript:void(0);" onclick="goPage(id,'+tabid+');">|<</a></td>';
	pjtab=pjtab+'<td><a id="shang'+tabid+'" class="" href="javascript:void(0)" onclick="goPage(id,'+tabid+');"><<</a></td>';
	
	pjtab=pjtab+'<td>|</td>';
	pjtab=pjtab+'<td>第<input id="pageNo'+tabid+'" type="text" value="1" size="1" style="width:25px;margin:0px;" readonly="readonly"></input>页</td>';
	pjtab=pjtab+'<td>共<span id="countNo'+tabid+'">'+countPageNo+'</span>页</td>';
	pjtab=pjtab+'<td>|</td>';
	
	pjtab=pjtab+'<td><a id="xia'+tabid+'" class="" href="javascript:void(0)" onclick="goPage(id,'+tabid+');">>></a></td>';
	pjtab=pjtab+'<td><a id="last'+tabid+'" class="" href="javascript:void(0)" onclick="goPage(id,'+tabid+');">>|</a></td>';
	pjtab=pjtab+'</tr></tbody></table></div>';
	
	return pjtab;
}

function mpjTabsList(data,tabid,bkhmid,sourceID) {
	var pjtab = '';
	var countPageNo='';
	//alert(data+'----'+tabid+'----'+bkhmid);
	pjtab = pjtab + "<div style='width:100%;height:380px;overflow-x:auto;'>";
	pjtab = pjtab + "<table id='"+tabid+"' border='0' cellpadding='0' cellspacing='0' style=''>";

	pjtab = pjtab + '<tr>';
	for ( var key in data) {
		countPageNo=key.split('@')[1];
		countPageNo=parseInt(countPageNo/10)+1;
		var key_a = data[key];
		for ( var a = 0; a < key_a.length; a++) {
			// 添加列表标题行
			//pjtab = pjtab + '<tr>';
			if (a == 0) {
				pjtab = pjtab + '<th style="width:16px;height:30px;"></th>';
			}
			for ( var m in key_a[a]) {
				if (a == 0&&m!='R1') {
					pjtab = pjtab + '<th class="ttd">' + m + '</th>';
				}
			}
			//pjtab = pjtab + '</tr>';

			// 添加列表数据
			pjtab = pjtab + '<tr>';
			pjtab = pjtab + '<td class="ttd" style="width:16px;height:30px;">' + (a + 1) + '</td>';
			for ( var n in key_a[a]) {
				if(key_a[a][n]==''||key_a[a][n]==null){
					pjtab = pjtab + '<td class="ttd"></td>';
				}else if(n!='R1'){
					pjtab = pjtab + '<td class="ttd">' + key_a[a][n] + '</td>';
				}
			}
			
			pjtab = pjtab + '</tr>';
		}
	}
	pjtab = pjtab + '</tr>';
	pjtab = pjtab + '</table>';
	pjtab = pjtab + '</div>';
	
	//详细信息分页按钮及当前页码，共多少页部分
	pjtab=pjtab+'<div id="fenyeID" class="fenyeC">';
	pjtab=pjtab+'<input id="mgetTabid'+tabid+'" type="hidden" value="'+tabid+'" />';
	pjtab=pjtab+'<input id="mbkhmid'+tabid+'" type="hidden" value="'+bkhmid+'" />';
	
	pjtab=pjtab+'<table id="mfenyeTable" border="0" cellpadding="0" cellspacing="0"><tbody><tr>';
	pjtab=pjtab+'<td><a id="mfirst'+tabid+'" class="" href="javascript:void(0);" onclick="gomPage(id,'+tabid+');">|<</a></td>';
	pjtab=pjtab+'<td><a id="mshang'+tabid+'" class="" href="javascript:void(0)" onclick="gomPage(id,'+tabid+');"><<</a></td>';
	
	pjtab=pjtab+'<td>|</td>';
	pjtab=pjtab+'<td>第<input id="mpageNo'+tabid+'" type="text" value="1" size="1" style="width:25px;margin:0px;" readonly="readonly"></input>页</td>';
	pjtab=pjtab+'<td>共<span id="mcountNo'+tabid+'">'+countPageNo+'</span>页</td>';
	pjtab=pjtab+'<td>|</td>';
	
	pjtab=pjtab+'<td><a id="mxia'+tabid+'" class="" href="javascript:void(0)" onclick="gomPage(id,'+tabid+');">>></a></td>';
	pjtab=pjtab+'<td><a id="mlast'+tabid+'" class="" href="javascript:void(0)" onclick="gomPage(id,'+tabid+');">>|</a></td>';
	pjtab=pjtab+'</tr></tbody></table></div>';
	
	return pjtab;
}

function goPage(id,tabid){
	var sourceID=$('#selectSouInput').val();
	var bkhmid=$('#bkhmid'+tabid).val();
	var gettabid=$('#getTabid'+tabid).val();
	var pageno=$('#pageNo'+tabid).val();
	var pagesize=10;
	var countNo=$('#countNo'+tabid).html();//总页数

//	alert(pageno);
	//判断点击的按钮，向后台传送当前页码、每页多少条等参数
	if(id=="first"+tabid){
		pageno=1;
	}else if(id=="shang"+tabid){
		if(pageno==1){
			pageno=1;
		}else{
			pageno=parseInt(pageno)-1;
		}
	}else if(id=="xia"+tabid){
		if(pageno==countNo){
			pageno=countNo;
		}else{
			pageno=parseInt(pageno)+1;
		}
	}else if(id=="last"+tabid){
		pageno=countNo;
	}
//	alert(id+'-'+pageno);
	$.ajax({
		type:'post',
		url:'detialTabPage.do',
		data:{
			squ:$('#selectSouInput').val(),
			bkhm:bkhmid,
			checked:gettabid,
			pageNo:pageno,
			pageSize:pagesize
		},
		datatype:'json',
		async:false,
		success:function(data){
			var json=eval('(' + data + ')');
//			var currTab =$('#showTabs').tabs('getSelected');

//			$('#showTabs').tabs('update', {
//				tab : currTab,
//				options:{
//					content:pjTabsList(json.rows[0],tabid,bkhmid,sourceID)
//				}
//			});
			pjTabsList(json.rows[0],tabid,bkhmid,sourceID);
			
			//更新页码
			var total=parseInt(json.total/10)+1;
//			$('#countNo'+tabid).attr('value',total);
			$('#countNo'+tabid).html(total);
			if(id=="first"+tabid){
				$('#pageNo'+tabid).attr('value',1);
			}else if(id=="shang"+tabid){
				if(pageno==1){
					$('#pageNo'+tabid).attr('value',1);
				}else{
					$('#pageNo'+tabid).attr('value',parseInt(pageno));
				}
			}else if(id=="xia"+tabid){
				if(pageno==total){
					$('#pageNo'+tabid).attr('value',total);
				}else{
					$('#pageNo'+tabid).attr('value',parseInt(pageno));
				}
			}else if(id=="last"+tabid){
				$('#pageNo'+tabid).attr('value',total);
			}
		},
		error:function(){
			alert('请求失败！');
		}
	});
}

function gomPage(id,tabid){
	var sourceID=$('#mselectSouInput').val();
	var bkhmid=$('#mbkhmid'+tabid).val();
	var gettabid=$('#mgetTabid'+tabid).val();
	var pageno=$('#mpageNo'+tabid).val();
	var pagesize=10;
	var countNo=$('#mcountNo'+tabid).html();//总页数

	//alert(pageno);
	//判断点击的按钮，向后台传送当前页码、每页多少条等参数
	if(id=="mfirst"+tabid){
		pageno=1;
	}else if(id=="mshang"+tabid){
		if(pageno==1){
			pageno=1;
		}else{
			pageno=parseInt(pageno)-1;
		}
	}else if(id=="mxia"+tabid){
		if(pageno==countNo){
			pageno=countNo;
		}else{
			pageno=parseInt(pageno)+1;
		}
	}else if(id=="mlast"+tabid){
		pageno=countNo;
	}
	//alert(id+'-'+pageno+'-'+tabid);
	$.ajax({
		type:'post',
		url:'detialTabPage.do',
		data:{
			squ:$('#mselectSouInput').val(),
			bkhm:bkhmid,
			checked:gettabid,
			pageNo:pageno,
			pageSize:pagesize
		},
		datatype:'json',
		async:false,
		success:function(data){
			var json=eval('(' + data + ')');
			var currTab =$('#mshowTabs').tabs('getSelected');

			$('#mshowTabs').tabs('update', {
				tab : currTab,
				options:{
					content:mpjTabsList(json.rows[0],tabid,bkhmid,sourceID)
				}
			});
			
			//更新页码
			var total=parseInt(json.total/10)+1;
//			$('#countNo'+tabid).attr('value',total);
			$('#mcountNo'+tabid).html(total);
			if(id=="mfirst"+tabid){
				$('#mpageNo'+tabid).attr('value',1);
			}else if(id=="mshang"+tabid){
				if(pageno==1){
					$('#mpageNo'+tabid).attr('value',1);
				}else{
					$('#mpageNo'+tabid).attr('value',parseInt(pageno));
				}
			}else if(id=="mxia"+tabid){
				if(pageno==total){
					$('#mpageNo'+tabid).attr('value',total);
				}else{
					$('#mpageNo'+tabid).attr('value',parseInt(pageno));
				}
			}else if(id=="mlast"+tabid){
				$('#mpageNo'+tabid).attr('value',total);
			}
		},
		error:function(){
			alert('请求失败！');
		}
	});
}

function fixWidth(percent){
	return $("#mian").width() * percent;
}
