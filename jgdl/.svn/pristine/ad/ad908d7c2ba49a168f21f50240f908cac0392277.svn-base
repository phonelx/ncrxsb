/**
 * 初始化显示 数据源列表
 *
 * 
 **/
//$(document).ready(function(){
//	$('#sms').datagrid({
//				fit:true,
//				nowrap: false,
//				singleSelect:true,
//				collapsible:true,
//				url:'listSMS.do',
//				pagination:true,
//				idField : 'id',
//				rownumbers:true,
//				remoteSort: false,
//				columns:[[
//					{title:'布控标题',field:'bt',width:180,align:'center',sortable:true},
//					{title:'布控人',field:'bkr',width:150,align:'center',	sortable:true},
//					{title:'短信标题',field:'title',width:180,align:'center',sortable:true,
//						formatter:function(value,rec){
//							if(value.length>10){
//				    			return value.substring(0,10)+"....";
//				    		}else{
//				    			return value;
//				    		}
//						}
//					},
//				    {title:'是否发送',field:'isSend',width:100,align:'center',sortable:true,
//				    	formatter:function(value,rec){
//				    		if(value=="0"){
//				    			return "发送";
//				    		}else{
//				    			return "不发送";
//				    		}
//				      }},
//				      {title:'接收号码',field:'phone',width:150,align:'center',	sortable:true}
//				      
//				]],
//				toolbar:[{
//					id:'btnadd',
//					text:'短信添加',
//					iconCls:'icon-add',
//					handler:function(){  
//					$('#SmsForm').form('clear');
//					$('#sms_add').window('open');
//					$(":radio[name='sms.isSend'][value=0]").attr("checked","checked");
//					$("#bk").combobox("setValue","请输入或选择布控标题");
//					$('#btn_ok_2').unbind();
//					$('#btn_ok_2').click(function(){
//						AddSMS('AddSMS.do');
//					});
//				}
//				},'-',{
//					id:'btndel',
//					text:'删除短信',
//					iconCls:'icon-remove',
//					handler:function(){
//					  var selected = $('#sms').datagrid('getSelected');
//					 if(selected){
//							if(confirm('确定要删除规则名为: '+"《"+selected.bt+"》"+' 的短信配置?'))
//								DelSMS(selected.id);
//					 }else{
//						 alert("请选择一个配置");
//					 }
//					}
//				},'-',{
//					id:'btnEdit',
//					text:'编辑短信',
//					iconCls:'icon-edit',
//					handler:function(){	
//					var selected = $('#sms').datagrid('getSelected');
//					if(selected){
//					$('#SmsForm').form('clear');
//					$("#smstitle").val(selected.title);
//					$("#smsend").val(selected.end);
//					$("#bkid").val(selected.bkid);
//					$("#smsid").val(selected.id);
//					$("#bk").combobox("setValue",selected.bt);
//					$(":radio[name='sms.isSend'][value="+selected.isSend+"]").attr("checked","checked");
//					$('#sms_add').window('open');
//					$('#sms_add').panel('setTitle','短信管理配置编辑');	
//					$('#btn_ok_2').unbind();
//					$('#btn_ok_2').click(function(){
//						
//						AddSMS('ExitSMS.do');
//					});
//					}else{
//						alert("请选择一个配置进行编辑");
//					}
//				}
//			}]
//	  });
//});

function showSmsList(pageNo){
	$("#sms").uiGrid({
		url : "listSMS.do",
		rowNum : 20,//每页显示记录数
		columns : [ {
			field : 'userTitle',
			title : '用户名称',
			width : 80
		}, {
			title:'布控标题',
			field:'bt',
			width:180
		}, {
			title:'布控人',
			field:'bkr',
			width:150
		}, {
			title:'短信标题',
			field:'title',
			width:180,
			formatter:function(value,rec){
				if(value.length>10){
	    			return value.substring(0,10)+"....";
	    		}else{
	    			return value;
	    		}
			}
		}, {
			title:'是否发送',
			field:'isSend',
			width:100,
	    	formatter:function(value,rec){
	    		if(value=="0"){
	    			return "发送";
	    		}else{
	    			return "不发送";
	    		}
	    	}
		}, {
	    	  title:'接收号码',
	    	  field:'phone',
	    	  width:150
	    } ],
		divId : "#sms",
		showPage : 5,//显示
		showEdit : true,
		showDelete : true,
		showEditEvent : showEditSmsBox,
		showDeleteEvent : DelSMS,
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

/*
 * 添加/编辑短信配置 ---------------- start
 */
/**
 * 显示短信BOX
 */
function showAddSmsBox(){
	document.getElementById("clearForm").click();
	document.getElementById("smsTitleName").innerHTML = "添加短信";
	$('#sms_add').modal();

	$('#smsBtn').unbind();
	$('#smsBtn').click(function(){
		AddSMS('AddSMS.do');
	});
}

/**
 * 短信添加方法
 */
function AddSMS(url){
	if(checkSms()==true){
		$.ajax({
			type : 'post',
			url:url,
			data:$("#SmsForm").serialize(),// 提交的表单
			success:function(data){
				document.getElementById("closeSmsBox").click();
				showSmsList(1);
			},
			error : function(data) {
				alert("编辑出错！");
			}
		});
	}
}
/*
 * 添加/编辑短信配置 ---------------- end
 */

/**
 * 删除短信
 */
function DelSMS(row){
	$.ajax({
		type : 'post',
		url:"DelSms.do",
		data:{"sms.id":row.id},// 提交的表单
		success:function(data){
			showSmsList(1);
		},
		error : function(data) {
			alert("编辑出错！");
		}
	 });
}

/**
 * 显示编辑短信BOX
 */
function showEditSmsBox(row){
	document.getElementById("clearForm").click();
	document.getElementById("smsTitleName").innerHTML = "编辑短信";
	
	$("#smstitle").val(row.title);
	$("#smsend").val(row.end);
	$("#bkid").val(row.bkid);
	$("#smsid").val(row.id);
	$("#bk").combobox("setValue",row.bt);
	$(":radio[name='sms.isSend'][value="+row.isSend+"]").attr("checked","checked");

	$('#sms_add').modal();

	$('#smsBtn').unbind();
	$('#smsBtn').click(function(){
		AddSMS('ExitSMS.do');
	});
}

/**
 * 添加/编辑前检查表单
 */
function checkSms(){
	if($("#smstitle").val().trim()==""){
		alert("请填写短信标题");
		return false;
	}
	if($("#bkid").val().trim()==""){
		alert("请您选择一个布控规则");
		return false;
	}
	if($("#smsend").val().trim()==""){
		alert("请填写短信结尾");
		return false;
	}
	return true;
}

$("#bk").combobox({
	url:'showBK.do',  
	valueField:'bid',  
	textField:'bt',
	filter:function(q,row){ 
		var opts=$(this).combobox("options"); 
		return row[opts.textField].indexOf(q)>-1;//将从头位置匹配改为任意匹配 
	},onSelect:function(){
		$("#bkid").val($("#bk").combobox("getValue"));
	}
});

/**
 * 输入查找时清除combobox 默认选项
 */
var vals="";
$(".combo-text").click(function(){
	vals = $(this).val();
	$(this).val(""); 
});

/**
 * 移除时恢复默认字体
 */
$(".combo-text").blur(function(){	
	$(this).val(vals);
});	

