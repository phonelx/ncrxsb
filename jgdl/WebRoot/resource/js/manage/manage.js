/**
 * 初始化显示 数据源列表
 *
 * 
 **/
$(document).ready(function(){
//	$('#yjbk').datagrid({
//				fit:true,
//				nowrap: false,
//				singleSelect:true,
//				collapsible:true,
//				url:'listManage.do',
//				pagination:true,
//				idField : 'id',
//				rownumbers:true,
//				remoteSort: false,
//				columns:[[
//					{title:'预警标题',field:'yjbt',width:fixWidth(0.1),align:'center',sortable:true},
//				    {title:'预警人姓名',field:'name',width:fixWidth(0.1),align:'center',	sortable:true},
//				    {title:'预警人民族',field:'mz',width:fixWidth(0.1),sortable:true,align:'center',
//				    	formatter:function(value,rec){
//				    		if(value==""){
//				    			return "暂未选择";
//				    		}else{
//				    			return value;
//				    		}
//				      }},
//				    {title:'是否禁用',field:'flag',width:fixWidth(0.12),align:'center',sortable:true,
//				    	formatter:function(value,rec){
//				    		if(value=="2"){
//				    			return "是";
//				    		}else{
//				    			return "否";
//				    		}
//				      }},
//				    {title:'创建人',field:'yjr',width:fixWidth(0.12),align:'center',sortable:true},
//				    {title:'创建日期',field:'createtime',width:fixWidth(0.15),align:'center',sortable:true},
//				    {title:'失效日期',field:'stoptime',align:'center',width:fixWidth(0.15)}
//				]],
//				toolbar:[{
//					id:'btnadd',
//					text:'添加规则',
//					iconCls:'icon-add',
//					handler:function(){  
//					$('#manageForm').form('clear');
//					$('#manage_add').window('open');
//					$("#calx").val("0");
//					$("#sex").attr("value", "1");
//					$("#isflag").attr("value", "1");
//					$(".time1").show();
//					$(".time2").hide();
//					$('#mz').combobox("setValue","请输入任意民族");
//					$('#seleDB').combobox("select",$('#seleDB').combobox("getData")[1].name);
//					$("#dbcon").val($('#seleDB').combobox("getData")[1].id)
//					$('#citys').combobox("loadData",$("#citys").combobox("getData"));
//					$('#citys').combobox("setValue","请输入大概范围的省市县(区)");
//					$(":radio[name='yjgz.istime'][value='0']").attr("checked","checked");
//					if($("#cityname").val()=="请输入大概范围的省市县(区)"){$("#cityname").val("")}
//					if($("#mz").val()=="请输入任意民族"){$("#mz").val("")}
//					$("#showCheckcitys").children().remove();
//					$('#btn_ok_1').unbind();
//					$('#btn_ok_1').click(function(){
//						fromUser(0);
//					});
//				}
//				},'-',{
//					id:'btndel',
//					text:'删除规则',
//					iconCls:'icon-remove',
//					handler:function(){
//						var selected = $('#yjbk').datagrid('getSelected');
//						if(selected){
//							if(confirm('确认删除 '+"《"+selected.yjbt+"》"+' 的预警配置?'))DelYjGz(selected.id);
//					 }else{
//						 alert("请选择一个配置");
//					 }
//					}
//				},'-',{
//					id:'btnEdit',
//					text:'编辑规则',
//					iconCls:'icon-edit',
//					handler:function(){
//						$('#manageForm').form('clear');
//						var selected = $('#yjbk').datagrid('getSelected');
//						if(selected){
//							//清空选择
//							$("#calx").val("1");
//							$("#gzid").val(selected.id);
//							$("#showCheckcitys").children().remove();
//							$("#checkcityname").val(selected.hjd);
//							$('#manage_add').window('open');
//							$('#manage_add').panel('setTitle','预警规则编辑');	
//							$("#dbtitle").val(selected.yjbt);
//							$("#name").val(selected.name);	
//							$('#citys').combobox("loadData",$("#citys").combobox("getData"));
//							$("#dbtbid").val(selected.tableID);
//							SelectDB("#seleDB",selected.dbConID,"1");
//							//$('#seleDB').combobox("setValue","请选择数据源");
//							//$('#dbtable').combobox("setValue","请选择数据源");
//							if(selected.istime!="0"){
//								$("#csrq3").val(selected.csrq1);
//								$("#csrq4").val(selected.csrq2);
//							}else{
//								$("#csrq1").val(selected.csrq1);
//								$("#csrq2").val(selected.csrq2);
//							}
//							$("#dbcon").val(selected.dbConID);	
//							if(selected.mz==""){
//								$('#mz').combobox("setValue","请输入任意民族");
//							}else{
//								$('#mz').combobox("setValue",selected.mz);
//								var  mz =$("#mz").combobox("getData");
//								for(var i=0;i<mz.length;i++){
//									if(mz[i].dmmc==selected.mz){
//									$("#mzcheck").val(mz[i].dm);
//									}
//								}
//							}
//							if(selected.hjd==""){
//								$('#citys').combobox("setValues","请输入大概范围的省市县(区)".split(","));
//							}else{
//								$("#citys").combobox("setValues",selected.hjd.split(","));
//							}
//							$("#isflag").attr("value", selected.flag);
//							$("#sex").attr("value", selected.sex);
//							$("#zz").val(selected.zz);					
//							$("#stoptime").val(selected.stoptime);	
//							$("#rdbtn").val(selected.istime);
//							$(":radio[name='yjgz.istime'][value="+selected.istime+"]").attr("checked","checked");
//							CheckRio();
//					$('#btn_ok_1').unbind();
//					$('#btn_ok_1').click(function(){
//						fromUser(1);
//					});
//						}else{
//							alert("请选择需要编辑的预警规则");
//						}
//					}
//				},'-',{
//					id:'btndel',
//					text:'比对系统任务设置',
//					iconCls:'sysLog',
//					handler:function(){
//						var rows = $("#yjbk").datagrid("getRows");
//						if(rows==""){
//							alert("请配置好了预警规则再进行任务设置");
//							return;
//						}
//						$(".runtimes").val(rows[0].runtime);
//						$('#runtime').window('open');
//					}
//				}]
//			});

	/**
	 * 根据用户输入内容模糊查询显示省市县(区)
	 */
	var checkvals="";
	var addvals="";
	$("#citys").combobox({
		url:'showCitys.do',
		valueField:'SQU',
		textField:'cityName',
		multiple:true,
		formatter:function(row){
			return "<input type='checkbox' id="+row.SQU+" disabled='disabled' class='citycheckeds' name='citycheckeds'/>"+row.cityName;
		},
		filter:function(q,row){
			var opts=$(this).combobox("options");
			return row[opts.textField].indexOf(q)>-1;//将从头位置匹配改为任意匹配
		},onSelect:function(rec){
			if(changeForChecked(rec.SQU,rec.cityName)){return};
			addvals = $("#cityname").val();
		 	var v = $("#citys").combobox('getText');
			var arr = v.split(",");
			if(arr.length>5){
				alert("最多只能添加4个所属省区县");
				return;
			}
			$("#"+rec.SQU).attr("checked","true");
			if(addvals.trim()=="" || addvals.indexOf(arr[arr.length-1])==-1 || addvals.indexOf(arr[arr.length-1]+"市辖区")!=-1){
			//添加选择并判断是否重复添加
				addvals=addvals+arr[arr.length-1].trim()+",";
				$("#showCheckcitys").append("<div style='text-align: left;'>"
						+ ""+arr[arr.length-1]
						+ "<input type='button' value='删除'style='padding-right:40px' id="+rec.SQU+" class='bthnsize'/>"
						+ "</div>");
				$("#cityname").val(addvals);
				againCheckCity();
			}else{
				$("#citys").combobox('setValues',$("#cityname").val().split(","));
				alert("您已经添加过该地区！");
			}
		},onLoadSuccess:function(rec){
			var arry =$("#checkcityname").val();
			var comary =$("#citys").combobox("getData");
			if(arry.trim() != ""){
				$("#checkcityname").val("");
				arry = arry.split(",");
				for(var j=0;j<comary.length;j++){
				    for(var i=0;i<arry.length;i++){
						 if(arry[i]==comary[j].SQU){
							 $("#"+comary[j].SQU).attr("checked","true");
							 $("#showCheckcitys").append("<div style='text-align: left;'>"
									+ ""+comary[j].cityName
									+ "<input type='button' value='删除'style='padding-right:40px' id="+comary[j].SQU+" class='bthnsize'/>"
									+ "</div>");
							 $("#cityname").val($("#cityname").val()+comary[j].cityName+",");	
						 }
				    }
				}
			}
		}
	});

	/**
	 * 加载所有注册的数据源
	 */
	$('#seleDB').combobox({  
		url:'listDataSource.do?option=1',  
		valueField:'id',  
		textField:'name',
		filter:function(q,row){ 
			var opts=$(this).combobox("options"); 
			return row[opts.textField].indexOf(q)>-1;//将从头位置匹配改为任意匹配 
		},onLoadSuccess:function(){
			//ajaxLoadTable(-1);
		},onSelect:function(){
			 var v=$("#seleDB").combobox("getValue");
	         if(v!="请选择数据源" && v.trim()!=""){
	        	 //ajaxLoadTable(v);
	        	 $("#dbcon").val(v);
	         }
		}
	});

	/**
	 * 加载所有民族
	 */
	$('#mz').combobox({
		url:'showCNMZ.do',  
		valueField:'dm',  
		textField:'dmmc',
		filter:function(q,row){ 
			var opts=$(this).combobox("options"); 
			return row[opts.textField].indexOf(q)>-1;//将从头位置匹配改为任意匹配 
		},onSelect:function(rec){
			$("#mzcheck").val(rec.dm);
	    }
	});

	/**
	 * 输入查找时清除combobox 默认选项
	 */
	var vals="";
	$(".combo-text").click(function(){
		//vals = $(this).val();
		$(this).val("");
	});

	/**
	 * 
	 */
	$(".combo-arrow").click(function(){
		if($(this).parent().next().val()=="请输入大概范围的省市县(区)"){
			$("#citys").combobox("clear");
		}
	});

	/**
	 * 点击删除时重置数据
	 */
	var NowValu=""
	$(".bthnsize").live('click',function(){
		$("#"+$(this).attr("id")).attr("checked","");
		$(this).parent().remove();
		againCheckCity();
	});

	/**
	 * 根据选择显示不同效果
	 */
	$(".timeshow").click(function(){
		CheckRio()
	});

	/**
	 * 立即执行比对子系统
	 */
	$(".isRun").click(function(){
		var count = 600;
		$(".isRun").attr("disabled", true);
		$.ajax({
			type : 'post',
			url:'doallyjManage.do',
			success:function(data){
				$(".isRun").val("执行成功");
				var countdown = setInterval(CountDown, 1000);
     			function CountDown() {
            	$(".isRun").val(count+"秒");
             		if (count == 0) {
              			 $(".isRun").val("执行").removeAttr("disabled");
                		clearInterval(countdown);
             		}
                 	count--;
        		}
			},
			error : function(data) {
				alert("添加出错！");
			}
		});
	});

	/**
	 * 更改预警比对子系统时间间隔
	 */
	$("#yjruntime").click(function(){
		if($(".runtimes").val().trim()<10){
			alert("最低间隔时间不得低于10分钟");return;
		}
		if($(".runtimes").val().trim()!=""){
			$.ajax({
				type : 'post',
				url:'EditRunTime.do',
				data:{"time":$(".runtimes").val()},// 提交的表单
				success:function(data){
					$("#closeYjgzTimeBox").click();
					showYjList(1);
				},
				error : function(data) {
					alert("添加出错！");
				}
			});
		}
	});

	/**
	 * 检查时间
	 */
	$("#csrq2").blur(function(){
		var startLgi = $('#csrq1').val().replace(/[-,:,\s]/g, "");
		var endLgi = $('#csrq2').val().replace(/[-,:,\s]/g, "");
		var lgiS = new Number(startLgi);
		var lgiE = new Number(endLgi);
		if(endLgi.trim()!=""){
			if (lgiS > lgiE) {
				alert('结束时间不能小于开始时间！');
				$('#csrq2').val("");
			}
		}	
	});

	$("#csrq1").blur(function(){
		var startLgi = $('#csrq1').val().replace(/[-,:,\s]/g, "");
		var endLgi = $('#csrq2').val().replace(/[-,:,\s]/g, "");
		var lgiS = new Number(startLgi);
		var lgiE = new Number(endLgi);
		if(endLgi!=""){
			if (lgiS > lgiE) {
				alert('结束时间不能小于开始时间！');
				$('#csrq2').val("");
			}
		}	
	});
});

function showTimeYjgzBox (){
//	var rows = $("#yjbk").datagrid("getRows");
//	if(rows==""){
//		alert("请配置好了预警规则再进行任务设置");
//		return;
//	}
//	$(".runtimes").val(rows[0].runtime);
	$("#runtime").modal();
}

function showYjList(pageNo){
	$("#yjbk").uiGrid({
		url : "listManage.do",
		rowNum : 50,//每页显示记录数
		columns : [ {
			title:'预警标题',
			field:'yjbt',
			width:fixWidth(0.1)
		}, {
			title:'预警人姓名',
			field:'name',
			width:fixWidth(0.1)
		}, {
			title:'预警人民族',
			field:'mz',
			width:fixWidth(0.1),
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
			width:fixWidth(0.12),
	    	formatter:function(value,rec){
	    		if(value=="2"){
	    			return "是";
	    		}else{
	    			return "否";
	    		}
	    	}
		}, {
			title:'创建人',
			field:'yjr',
			width:fixWidth(0.12)
		}, {title:'创建日期',
			field:'createtime',
			width:fixWidth(0.15)
		}, {
			title:'失效日期',
			field:'stoptime',
			width:fixWidth(0.15)
		} ],
		divId : "#yjbk",
		showPage : 5,//显示
		showEdit : true,
		showDelete : true,
		showEditEvent : showEditYjGz,
		showDeleteEvent : DelYjGz,
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

//------------------------

/**
 * 自动选择数据源,注册实体
 */
function SelectDB(id,value,option){
	var ary = $(id).combobox("getData");
	for(var i = 0;i < ary.length;i ++){
		if(value==ary[i].id){
			$(id).combobox("setValue",ary[i].id);
			if(option=="1"){
				//ajaxLoadTable(ary[i].id);
			}
		}
	}
}

/**
 * 根据复选框是否选中重置显示先过
 */
var subval="";
function changeForChecked(id,name){
	subval="";
	if($("#"+id).attr("checked")==true ){
		var v=$("#cityname").val().split(",");
		for(var i=0;i<v.length;i++){
			if(v[i].trim()!=name && v[i].trim()!=""){
				subval=subval+v[i]+",";
			}
		}
		$("#citys").combobox('setValues',subval.split(","));
		$("#"+id).attr("checked","");
		RelodaCity(id);
		return true;
	}
}

/**
 * 重置省市区显示
 */
function RelodaCity(id){
	var arry=$("#citys").combobox('getText');
	$("#"+id).attr("checked","");
	arry=arry.split(",");
	addvals=""
	$("#showCheckcitys").children().remove();
	for(var i=0;i<arry.length;i++){
		if(arry[i].trim()!=""){
			 $("#showCheckcitys").append(
			 "<div style='margin-left:5px;margin-bottom:2px;margin-top:2px'>" +
			 ""+arry[i]
			+"<input type='button' value='删除'style='padding-right:40px'id="+id+" class='bthnsize'/> "+
			 "</div>");
			 addvals=addvals+arry[i]+",";
		}
	}
	$("#cityname").val(addvals);
}


/**
 * 获取所属省市ID
 */
function getcityid(){
	var cityss =$("#citys").combobox("getData");
	var arr = $("#cityname").val().split(",");	
	for(var i=0;i<cityss.length;i++){
		for(var j = 0;j<arr.length;j++){
			if(arr[j].trim()!="" && arr[j].trim()==cityss[i].cityName){
				$("#checkcityname").val($("#checkcityname").val()+cityss[i].SQU+",");
			}
		}
	}
}

/**
 * 删除配置
 */
function DelYjGz(row){
	if(confirm('确认删除 '+"《"+row.yjbt+"》"+' 的预警配置?')){
		$.ajax({
			type : 'post',
			url:'DelManage.do',
			data:{"id":row.id},
			success:function(data){
				showYjList(1);
			},
			error : function(data) {
				alert("删除出错！");
			}
		});
	}
}

/**
 * 重新计算生成省市县
 */
var saves="";
function againCheckCity(){
	var oldRemove = $("#cityname").val();
	var check =oldRemove;
	var NowValu=$("#checkcityname").val();
	var  size =$("#showCheckcitys").children().length;
	for(var i=0;i<size;i++){
		var text =$("#showCheckcitys").children().eq(i).text();
		saves =saves+text+",";
	}
	$("#cityname").val(saves);	
	saves="";
	$("#checkcityname").val("");
	$("#citys").combobox("setValues",$("#cityname").val().split(","))
}

/**
 * 编辑添加前根据用户条件查询一次给予提示
 */
function fromUser(number){
	if(checkFrom()==true){
		$("#btn_ok_1").val("请稍候");
		$("#btn_ok_1").attr("disabled",true);
		if(number=="0"){
			AddYjGz();
		}else{
			EditYjGz();
		}
	}
}

/**
 * 检查表单是否填写
 */
function checkFrom(){
	if($("#dbtitle").val().trim()==""){
		alert("标题不能为空");
		return false;
	}
	//校验规则填写是否能够进行查询
	var name = $("#name").val().trim();
	var mz = $("#mz").combobox("getValue").replace("请输入任意民族","").trim();
	var zz = $("#zz").val().trim();
	//记得更改为 checkcityname
	var city = $("#cityname").val().trim();
	var sex =$("#sex option:selected").val().trim();
	var chck =$(".timeshow:checked").val().trim();
	var rq ="";
	var rq1="";
	if(chck=="1"){
		var csrq3 =$("#csrq3").val().trim();
		var csrq4 =$("#csrq4").val().trim();
		if(csrq3!="" && csrq4!=""){
			if(csrq3!="" && csrq4==""){
				 alert("结束日期未s填写");
			     return false;
			}
			if(csrq3=="" && csrq4!=""){
				 alert("开始日期未填写");
			     return false;
			}
			if(csrq3!="" && csrq4!=""){
				var a= Checkage();
				if(a!=false){
					rq =csrq3;
					rq1= csrq4;
				}		     
			}
		}
	}
	if(chck=="0"){
		var csrq1 = $("#csrq1").val().trim();
		var csrq2 = $("#csrq2").val().trim();
		if(csrq1!="" && csrq2==""){
			 alert("结束日期未填写");
		     return false;
		}
		if(csrq1=="" && csrq2!=""){
			 alert("开始日期未填写");
		     return false;
		}
		if(csrq1!="" && csrq2!=""){
			var startLgi=csrq1.replace(/[-,:,\s]/g, "");
			var sytime = csrq2.replace(/[-,:,\s]/g, "");
			var lgiS = new Number(startLgi);
			var lgiE = new Number(sytime);
			if(lgiE-lgiS<0){
				alert('结束日期不得小于开始日期');
				return false;
			}else{
					rq =csrq1;
					rq1= csrq2;
				}		 
			}
		}
	//检查规则不合法添加
	if(mz==""&& name=="" &&zz=="" &&city=="" &&sex=="" && rq==""){
		alert("您没有填写预警条件,请填写条件");
		return false;
	}
	if(mz!="" && sex!="" && name=="" &&zz=="" &&city==""&&rq=="" ){
		alert("您填写的规则条件为《性别，民族》不适合作为人口查询条件,请增加1-2条条件");
		return false;
	}
	var where = 0;
	if(mz!=""){where +=1;}
	if(name!=""){where +=1;}
	if(zz!=""){where +=1;}
	if(city!=""){where +=1;}
	if(sex!=""){where +=1;}
	if(rq!="" &&rq1!=""){where +=1;}
	if(where<2){alert("您填写的规则条件太少,请再填写1-2条条件！");return false}
	var startLgi=$("#stoptime").val().replace(/[-,:,\s]/g, "");
	var sytime =getNowFormatDate().replace(/[-,:,\s]/g, "");
	var lgiS = new Number(startLgi);
	var lgiE = new Number(sytime);
	if(lgiE-lgiS>0){
		alert('失效日期不能小于当前时间');
		$("#stoptime").val("");
		return false;
	}

	return true;
}

/*
 * 添加预警 -------------------- start
 */
/**
 * 显示添加预警规则BOX
 */
function showAddYjGz(){
	$("#clearForm").click();
	document.getElementById("yjgzTitleName").innerHTML = "添加预警规则";
	
	$("#calx").val("0");
	$("#sex").attr("value", "1");
	$("#isflag").attr("value", "1");
	$(".time1").show();
	$(".time2").hide();
	$('#mz').combobox("setValue","请输入任意民族");
	$('#seleDB').combobox("select",$('#seleDB').combobox("getData")[1].name);
	$("#dbcon").val($('#seleDB').combobox("getData")[1].id)
	$('#citys').combobox("loadData",$("#citys").combobox("getData"));
	$('#citys').combobox("setValue","请输入大概范围的省市县(区)");
	$(":radio[name='yjgz.istime'][value='0']").attr("checked","checked");
	if($("#cityname").val()=="请输入大概范围的省市县(区)"){$("#cityname").val("")}
	if($("#mz").val()=="请输入任意民族"){$("#mz").val("")}
	$("#showCheckcitys").children().remove();
	
	$("#manage_add").modal();
	
	$('#yjgzBtn').unbind();
	$('#yjgzBtn').click(function() {
		if ($('#descb').validatebox('isValid')
				&& $('#pwd').validatebox('isValid')
				&& $('#username').validatebox('isValid')
				&& $('#userTitle').validatebox('isValid')) {
			fromUser(0);
		}
	});
}

/**
 * 执行添加预警规则方法
 */
function AddYjGz(){
	againCheckCity();
	getcityid();
	if($("#cityname").val()=="请输入大概范围的省市县(区)"){$("#cityname").val("");};
	if($("#mz").val()=="请输入任意民族"){$("#mz").val("");};
	
	if(checkFrom()==true){
//		var i = $("#yjbk").datagrid("getRows");
//		if(i.length>10){
//			alert("最多只能添加10个规则");
//			return;
//		}
		$.ajax({
			type : 'post',
			url:'addManage.do',
			data:$("#manageForm").serialize(),// 提交的表单
			success:function(data){
				$("#closeYjgzBox").click();
				showYjList(1);
			},
			error : function(data) {
				alert("添加出错！");
			}
		});
	}
}
/*
 * 添加预警 -------------------- end
 */

/*
 * 编辑预警 -------------------- start
 */
/**
 * 显示编辑BOX
 */
function showEditYjGz(row){
	$("#clearForm").click();
	document.getElementById("yjgzTitleName").innerHTML = "编辑预警规则";
	
	//清空选择
	$("#calx").val("1");
	$("#gzid").val(row.id);
	$("#showCheckcitys").children().remove();
	$("#checkcityname").val(row.hjd);
	$('#manage_add').window('open');
	$('#manage_add').panel('setTitle','预警规则编辑');	
	$("#dbtitle").val(row.yjbt);
	$("#name").val(row.name);	
	$('#citys').combobox("loadData",$("#citys").combobox("getData"));
	$("#dbtbid").val(row.tableID);
	SelectDB("#seleDB",row.dbConID,"1");
	//$('#seleDB').combobox("setValue","请选择数据源");
	//$('#dbtable').combobox("setValue","请选择数据源");
	if(selected.istime!="0"){
		$("#csrq3").val(row.csrq1);
		$("#csrq4").val(row.csrq2);
	}else{
		$("#csrq1").val(row.csrq1);
		$("#csrq2").val(row.csrq2);
	}
	$("#dbcon").val(row.dbConID);	
	if(row.mz==""){
		$('#mz').combobox("setValue","请输入任意民族");
	}else{
		$('#mz').combobox("setValue",row.mz);
		var  mz =$("#mz").combobox("getData");
		for(var i=0;i<mz.length;i++){
			if(mz[i].dmmc==row.mz){
			$("#mzcheck").val(mz[i].dm);
			}
		}
	}
	if(row.hjd==""){
		$('#citys').combobox("setValues","请输入大概范围的省市县(区)".split(","));
	}else{
		$("#citys").combobox("setValues",row.hjd.split(","));
	}
	$("#isflag").attr("value", row.flag);
	$("#sex").attr("value", row.sex);
	$("#zz").val(row.zz);					
	$("#stoptime").val(row.stoptime);	
	$("#rdbtn").val(row.istime);
	$(":radio[name='yjgz.istime'][value="+row.istime+"]").attr("checked","checked");
	CheckRio();
		
	$('#yjgzBtn').unbind();
	$('#yjgzBtn').click(function(){
		fromUser(1);
	});
}

/**
 * 执行编辑预警规则方法
 */
function EditYjGz(){
	againCheckCity();
	getcityid();
	if($("#cityname").val()=="请输入大概范围的省市县(区)"){$("#cityname").val("")};
	if($("#mz").val()=="请输入任意民族"){$("#mz").val("")};
	$.ajax({
		type : 'post',
		url:'EditManage.do',
		data:$("#manageForm").serialize(),// 提交的表单
		success:function(data){
			$("#closeYjgzBox").click();
			showYjList(1);
		},
		error : function(data) {
			alert("编辑出错！");
		}
	});
}
/*
 * 编辑预警 -------------------- end
 */

/**
 * 获取当前时间并格式化为yyyy-MM-dd HH:MM:SS
 */
function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
            + " " + date.getHours() + seperator2 + date.getMinutes()
            + seperator2 + date.getSeconds();
    return currentdate;
}
/**
 * 检查年龄
 */
function Checkage(){
	var startLgi = $('#csrq3').val();
	if(startLgi.trim()>100){
		alert("年龄太大了");
		$('#csrq3').val("");
		return false;
	}
	var endLgi = $('#csrq4').val();
	if(endLgi.trim()>100){
		alert("年龄太大了");
		$('#csrq4').val("");
		return false;
	}
	if(endLgi.trim()!=""){
		if(startLgi>endLgi){
			alert('开始年龄段不能小于结束年龄段');
			$('#csrq4').val("");
			return false;
		}
	}
	
}
function CheckRio(){
	var chck =$(".timeshow:checked").val().trim();
	if(chck!=0){
		$(".time2").show();
		$(".time1").hide();
		$("#csrq1").attr("name","");
		$("#csrq2").attr("name","");
		$("#csrq3").attr("name","yjgz.csrq1");
		$("#csrq4").attr("name","yjgz.csrq2");
	}else{
		$(".time1").show();
		$(".time2").hide();
		$("#csrq3").attr("name","");
		$("#csrq4").attr("name","");
		$("#csrq1").attr("name","yjgz.csrq1");
		$("#csrq2").attr("name","yjgz.csrq2");
	}
	$("#rdbtn").val($(this).val());
}
function fixWidth(percent)     
{     
    return (document.body.clientWidth - 5) * percent ;      
}  
function onlyNum()
{
 if(!(event.keyCode==46)&&!(event.keyCode==8)&&!(event.keyCode==37)&&!(event.keyCode==39))
  if(!((event.keyCode>=48&&event.keyCode<=57)||(event.keyCode>=96&&event.keyCode<=105)))
    event.returnValue=false;
}  