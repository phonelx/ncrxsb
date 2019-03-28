/**
 * 跳转到url对应的模块地址
 * @param  url    模块地址
 * @param  title  模块名称
 */
 
//记录操作页面 5次
var backId;  
//错误上报功能名称  
var cumode;
//错误上报对应Grid名称  
var gridName;
//错误上报对应Grid父亲Div名称  
var gridNameDiv;
//错误上报对应数据源ID  
var dataEntityId;
//错误上报对应表ID  
var tableId;
function gotoModule(url,title){
//	$('body').layout('panel','center').panel({
//		title:title
//	});
	closeNoticeBoard();	
cumode="";
gridNameDiv="";
gridName=""; 
dataEntityId = 0;
tableId = "";
cumode =  title; 
	if(backId!=null){
		if(backId.split(",").length>6){
			//去掉多余url 
			backId = backId.substring(backId.indexOf(",")+1,backId.length);
			backId=backId+","+url+"_"+title;
		}else{ 
			backId=backId+","+url+"_"+title;			
		}
	}else{
		backId=url+"_"+title;
	}  
	qjbl==null; 
	if(title!="欢迎使用"){
		testDisdory();
	}
	
	//清除错误上报状态 
	$('#errorFont').css('color','black');
	$('#errorFont').text('错误数据上报');
	
	if(url=="gotoStandardQuery.do" || url=="gotoCustomiseQuery.do" || url=="gotoCommonUseQuery.do"){
		$('#navigation').show();
		//进入查询模块显示 错误上报弹出层，2秒后收起
		$('a',$('#errorli')).stop().animate({'marginLeft':'-2px'},200);
        setTimeout(function(){ 
        	$('a',$('#errorli')).stop().animate({'marginLeft':'-85px'},200);
        },2000);        
	}else{
		$('#navigation').hide();
	}
//	$('body').layout('panel','center').panel('options').href=url;
////	$('body').layout('panel','center').panel('setTitle','<div style="width:99%;"><div style="float:left;width:20%;">'+title+'</div><div style="float:left;width:78%;text-align:right">当前用户：'+login_userName+'</div></div>');
//	$('body').layout('panel','center').panel('setTitle',title);
//	$('body').layout('panel','center').panel('refresh');
	
	$("#rightMain").attr("src",url);
}

 
function windowcClose(id) {
	$(id).window({
		onClose : function() { 
			$('.validatebox-tip').hide();
			$('#rowNum').val('');
			$('#errordata').val('');
			$('#note').val(''); 
  			$('#'+gridName).datagrid('reload');
		}
	});
	var a=document.getElementById("");
	
}


/**
 * 添加数据源
 */
function submitError(){ 
//REPORTDATE
			//USERNAME
			//TABLEID
			//DATAENTITYID
			//ERRORDATA
			//NOTE 
	$.ajax({
			type:'post',
			url:'submitError.do',
			data:{
				'rer.userName':$('#userNameError').val(),
				'rer.tableId':$('#tableId').val(),
				'rer.dataEntityId':$('#dataEntityId').val(),
				'rer.errordata':$('#errordata').val(),
				'rer.note':$('#note').val()			
			},
			success:function(data){
				$('#errorDataReport').window('close');
				alert('上报成功！');
				$('.validatebox-tip').hide(); 
  				$('#rowNum').val('');
  				$('#errordata').val('');
  				$('#note').val('');	
  				$('#'+gridName).datagrid('reload');
			},
			error:function(){
				alert('上报数据出错！');
			}
		});
}



function installFlashPlayer(divId) {
	var requiredMajorVersion = 10;
	var requiredMinorVersion = 0;
	var requiredRevision = 0;
	var hasProductInstall = DetectFlashVer(6, 0, 65);
	var hasRequestedVersion = DetectFlashVer(requiredMajorVersion,
			requiredMinorVersion, requiredRevision);
	if (!hasProductInstall) {
		var alternateContent = '<div style="text-align:center;  margin-top:20px;font-family:"Courier New", Courier, monospace; font-size:14px; padding:10px 0 10px 0;"><strong style="color: blue;">此处内容需要falsh player才能使用，请先点击后边的链接下载flash player并安装<br/></strong><p></p><a href="/download/install_flash_player_10_active_x.exe" style="color:red;font-size:20px;">下载 Flash player</a></div>';
		$('#' + divId).empty();
		$('#' + divId).append(alternateContent); // insert non-flash content
		if (divId == 'cloudChart') {
			$('#' + divId).css('display', 'block');
		}
	} else if (!hasProductInstall && !hasRequestedVersion) {
		} else if (hasRequestedVersion) {
	}
}


function closePanel(layoutId,region){
	$("#"+layoutId).layout('collapse',region);
}

function openPanel(layoutId,region){
	$("#"+layoutId).layout('expand',region);
}

/**
 * 替换url中的' " & 符号
 * '   替换成 singleQuot#Sun
 * "   替换成 singleAnd#Sun
 * &   替换成 doubleQuot#Sun
*/
function stringReplaceFun(strRe){
	if(strRe!=null){
	strRe=strRe.replace(/\'/g,"[singleQuotHL]");
	strRe=strRe.replace(/\&/g,"[singleAndHL]");
	strRe=strRe.replace(/\"/g,"[doubleQuotHL]");
	return strRe;
	}else {
		return null;
	}
}


/**
 * 进行同名验证的公共函数
 */
function sameNameJudge(tableName,filedName,nowUseName,globeField,valueFiled,oldValueField){
	nowUseName=nowUseName.replace(/(^\s*)|(\s*$)/g, "");
	oldValueField=oldValueField.replace(/(^\s*)|(\s*$)/g, "");
	if(tableName!=null&&tableName!=''&&tableName!='null'&&tableName!='undefined'&&filedName!=null&&filedName!=''&&filedName!='null'&&filedName!='undefined'&&nowUseName!=null&&nowUseName!=''&&nowUseName!='null'&&nowUseName!='undefined'){
	  if($("#"+oldValueField).val()!=nowUseName){
			$.ajax({
				type:'post',
				url:'sameNameJudge.do',
				async:false,
				data:{
					'tableNameTran':tableName,
					'filedNameTran':filedName,
					'nowUseNameTran':stringReplaceFun(nowUseName),
					'dbid':$("#querydbID").val(),
					'tabid':$("#tabID").val()
				},
				success:function(data){
					var returnObj=eval("("+data+")");
					//alert(returnObj.cloudUse);
					if(returnObj.cloudUse==true){
						$("#"+globeField).val(true);
					}else{
						alert('标题(名称)已被使用，请重新输入标题(名称)！');
						$("#"+valueFiled).val('');
						$("#"+globeField).val(false);
					}
				},
				error:function(){
					alert('数据库忙，无法进行同名验证！');
					$("#"+valueFiled).val('');
					$("#"+globeField).val(false);
					//alert('');
				}
			});
	  }
}
}
function closeNoticeBoard(){
	if($(".panel-title:contains('公告')").length>0){
		$('.panel-tool-close').trigger('click');
		$('div[class="panel-header accordion-header"]').unbind('click',closeNoticeBoard)
	}
}	

