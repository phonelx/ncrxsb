// 新建 ip输入框
var dbIp = new IpV4Box("dbIp" , "span1"); 
 
/**
 * 初始化显示 数据源列表
 */
function initGrid(pageNo){
	
	//处理搜索条件
	var query = $("#input_source_search").attr("value");
	if (query == "关键字搜索") {
		query = "";
	}
	$("#dbs").uiGrid({
		url : "listDataSource.do",
		rowNum : 10,//每页显示记录数
		columns : [ {
			title:'数据源名称',
			field:'title',
			width:130
		}, {
	    	title:'数据源类型',
	    	field:'dbType',
	    	width:100,
	    	formatter:function(value,rec){
	    		var type="" ;
	    		switch(rec.dbType){
	    		case 1 :
	    			type= 'oracle8';
	    			break ;
	    		case 2 :
	    			type= 'oracle9i';
	    			break ;
	    		case 3 :
	    			type= 'oracle10g';
	    			break ;
	    		case 4 :
	    			type= 'oracle11g';
	    			break ;
	    		case 5 :
	    			type= 'sqlserver2000';
	    			break ;
	    		case 6 :
	    			type= 'sqlserver2005';
	    			break ;
	    		default :
	    			type= '其他类型数据源';
	    		    break ;
	    		}
	    		return type ;
	    	}
	    }, {
	    	title:'实例名',
	    	field:'dbInstance',
	    	width:100
	    }, {
	    	title:'IP',
	    	field:'dbIp',
	    	width:130
	    }, {
	    	title:'端口',
	    	field:'dbPort',
	    	width:60
	    }, {
	    	title:'用户',
	    	field:'dbUser',
	    	width:100
	    }, {
	    	title:'密码',
	    	field:'dbPwd',
	    	width:90,	
	    	formatter:function(value,rec){
	    		return '******';
	    	}
	    }, {
	    	title:'描述',
	    	field:'descb',
	    	width:130
	    }, {
	    	title:'测试',
			field:'opt',
	    	width:90,	
	    	formatter:function(value,rec){    	
				return '<a href="javascript:void(0);" onclick="javascript:testConn(\''+rec.dbClass+'\',\''+rec.dbUrl+'\',\''+rec.dbUser+'\',\''+rec.dbPwd+'\');">测试连接</a>';
	    	}
	    } ],
		divId : "#dbs",
		showPage : 5,//显示
		showEdit : true,
		showDelete : true,
		showEditEvent : showEditSource,
		showDeleteEvent : removeRds,
		jsonPager : {
			root : "datas",// 当前页数据
			records : "rowCount",//总记录数
			currentPage : pageNo,//当前访问页
			total : "pageCount"//总页数
		},
		data : {
			searchKey : query
		}
	});
}

/**
 * 数据源注册 --------------- start
 */
/**
 * 显示数据源注册BOX
 */
function showAddRds(){
	// 清空表单数据
	document.getElementById("clearForm").click();
	document.getElementById("sourceTitleName").innerHTML = "添加数据源";
	
	$("#dbs_add").modal();
	
	$('#btn_ok_3').unbind();
	$('#btn_ok_3').click(function(){
		var coulduse=$("#dbsNameSameJudge").val();
		if(coulduse=='true'){
			if($('#title').validatebox('isValid') & $('#dbInstance').validatebox('isValid')  
				& $('#dbPort').validatebox('isValid') & $('#dbUser').validatebox('isValid') 
			    & $('#dbPwd').validatebox('isValid') & $('#descb').validatebox('isValid')){
				addRds();
			}
		}
	});
}

/**
 * 执行添加数据源
 */
function addRds(){
	$.ajax({
			type:'post',
			url:'doRegDataSource.do',
			data:{
				'rds.title':$('#title').val(),
				'rds.dbType':$('#dbType').val(),
				'rds.dbInstance':$('#dbInstance').val(),
				'rds.dbIp':dbIp.getValue(),
				'rds.dbPort':$('#dbPort').val(),
				'rds.dbUser':$('#dbUser').val(),
				'rds.dbPwd':$('#dbPwd').val(),
				'rds.descb':$('#descb').val()
			},
			success:function(data){
				document.getElementById("closeSourceBtn").click();
				initGrid(1);
			},
			error:function(){
				alert("请求失败！");
			}
		});
}
/**
 * 数据源注册 --------------- end
 */

/**
 * 删除数据源 --------------- start
 */
function removeRds(row){
	if(confirm('确认删除？')){
		$.ajax({
			type:'post',
			url:'doDeleteDbs.do',
			data:{
				'rds.squ':row.squ,
				'rds.title':row.title
			},
			success:function(data){
				if(data == 'true'){ // 删除成功
					// 刷新列表
					initGrid(1);
				}else{//删除失败
					alert('当前数据源正在被使用不能删除！');
				}
			},
			error:function(){
				alert('删除出错！');
			}
		});
	}
}
/**
 * 删除数据源 --------------- end
 */

/**
 * 编辑数据源 --------------- start
 */
/**
 * 显示编辑数据源BOX
 */
function showEditSource(row){
	// 清空表单数据
	document.getElementById("clearForm").click();
	document.getElementById("sourceTitleName").innerHTML = "编辑数据源";
	
	$("#dbs_add").modal();
	
	// 初始化 表单
	$('#title').val(row.title);
	$('#dbsNameOldValue').val(row.title);
	$('#dbType').val(row.dbType);
	$('#dbInstance').val(row.dbInstance);
	dbIp.setValue(row.dbIp);
	$('#dbPort').val(row.dbPort);
	$('#dbUser').val(row.dbUser);
	$('#dbPwd').val(row.dbPwd);
	$('#descb').val(row.descb);
	
	$('#btn_ok_3').unbind();
	$('#btn_ok_3').click(function(){
		if($('#title').validatebox('isValid') & $('#dbInstance').validatebox('isValid')
			& $('#dbPort').validatebox('isValid') & $('#dbUser').validatebox('isValid') 
		    & $('#dbPwd').validatebox('isValid') & $('#descb').validatebox('isValid')){
			editRds(row.squ);
		}
	});					
}
/**
 * 执行编辑数据源
 */
function editRds(squ){
	$.ajax({
		type:'post',
		url:'doEditDbs.do',
		data:{
			'rds.squ':squ,
			'rds.title':$('#title').val(),
			'rds.dbType':$('#dbType').val(),
			'rds.dbInstance':$('#dbInstance').val(),
			'rds.dbIp':dbIp.getValue(),
			'rds.dbPort':$('#dbPort').val(),
			'rds.dbUser':$('#dbUser').val(),
			'rds.dbPwd':$('#dbPwd').val(),
			'rds.descb':$('#descb').val()
		},
		success:function(data){
			document.getElementById("closeSourceBtn").click();
			initGrid(1);
		},
		error:function(){
			alert('编辑出错！');
		}
	});
}
/**
 * 编辑数据源 --------------- end
 */

/**
 * 测试 数据源是否可用
 */
testConn = function(clazz,url,user,pwd) {
	$.ajax({
		type : "post",
		data : {
			'rds.dbClass' :clazz,
			'rds.dbUrl' : url,
			'rds.dbUser' : user,
			'rds.dbPwd' : pwd
		},
		url : 'doTestConn.do',
		success : function(data) {
			if (data == 'true')
				alert('测试通过!');
			else
				alert('测试失败!');
		},
		error : function() {
			alert('请求失败!');
		}
	});
}


/**
 * 或者字节数
 * @param {} value
 * @return {}
 */
function getBytes(value){		 
	var length = 0;
	var charCode;
	for(idx=0;idx<value.length;idx++){	
	// charCode=tempStr.charAt(idx); //返回unicode本身
	charCode = value.charCodeAt(idx); // 获取unicode编码
		if (charCode < 0x007f) {
			length++;
		} else if ((0x0080 <= charCode) && (charCode <= 0x07ff)) {
			length += 2;
		} else if ((0x0800 <= charCode) && (charCode <= 0xffff)) {
			length += 3;
		} 			 
	}

	return length;	
};
/**
 * ajax 校验 数据源名 是否重名
 * */
function checkNameAjax(dbsName){
	var flag = false ;
	$.ajax({
		type:'Post',
		url:'doCheckDbsName.do',
		data:{'rds.title':dbsName},
		async:false,
		success:function(data){
			flag = data=="true"?true:false;
		},
		error:function(data){
			alert('请求失败！');
		}
	});

	return flag ;
}

