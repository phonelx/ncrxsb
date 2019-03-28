<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/pages/head.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    
    <title>共享服务系统</title>
    <style type="text/css">
    	body,div,input,a,span,table,li,ul{margin:0px;padding:0px;}
    	#sourceSearchBox{padding:10px 0px;text-align:right;}
    	#dbs_add input{width:230px;}
		#dbs_add select{width:240px;border-color:#CCCCCC;border-radius:3px;}
		#dbs_add textarea{width:230px;}
    	.boxBottom{padding:10px 0px;text-align:right;}
    	
    	#dbIp input{width:48px;border:1px solid #CCCCCC;}
    	#dbPort{margin: 0px 0px 10px 0px;border:1px solid #CCCCCC;padding:5px;border-radius:3px;}
    	#btn_ok_3,#closeSourceBtn{width:90px !important;}
    
		/*IP 输入框*/
		.ipInput{font-size:12px;}
		.ipInput input{border:0px solid #7f9db9;font-size:14px;height:20px;width:34px;background:transparent;text-align:center;}
		#dbsDiv tr{height: 27;}
		.tdright{padding-left: 10px;}
	</style>
</head>

<body>
	<div id="sourceSearchBox">
		<div></div>
		<div>
			<input id="input_source_search" type="text" style="width:200px;color:#666;margin:0px;" value="关键字搜索"
				onfocus="if(this.value == '关键字搜索') this.value = ''" onblur="if(this.value =='') this.value = '关键字搜索'">
			<a class="btn " onclick="initGrid(1);" href="javascript:void(0);">
				<i class="icon-search"></i> 检索
			</a>
			
			<a title="添加数据源" class="btn" onclick="showAddRds();" href="javascript:void(0);" style="margin-right:10px;">
				<i class="icon-plus-sign"></i> 添加 
			</a>
		</div>
	</div>

	<div id="dbs" fit="true" style="padding: 10px 20px;"></div>

   	<div id="dbs_add" class="modal hide fade in" style="display:none;width:600px;height:520px;top:40%;left:50%;">
   		<div class="modal-header">
			<a class="close" data-dismiss="modal">×</a>
			<h3 id="sourceTitleName">添加数据源</h3>
		</div>

		<div class="modal-body">
	   		<form action="" method="post" id="dbsRegForm">
	   			<table cellpadding="0" cellspacing="1" bgcolor="#99BBEB" width="95%">
	   				<tr bgcolor="#ffffff">
	   				    <td align="right" width="30%">标&nbsp;&nbsp;&nbsp;题：</td>
	   					<td class="tdright">
	   						<input type="text" id="title" name="dbsName" class="easyui-validatebox" 
	   							required="true" validType="dbsNameValid[200]" 
	   							onblur="sameNameJudge('SSP_TREGDATASOURCE','TITLE',this.value,'dbsNameSameJudge','title','dbsNameOldValue')"/>
   							<input type="hidden" id="dbsNameSameJudge" value="true"/>
   							<input type="hidden" id="dbsNameOldValue"/>
	   					</td>
	   				</tr>
	   				<tr bgcolor="#ffffff">
	   					<td align="right">类&nbsp;&nbsp;&nbsp;型：</td>
	   					<td class="tdright">
	   						<select id="dbType">
	   							<option value="1">oracle8</option>
	   							<option value="2">oracle9i</option>
	   							<option value="3">oracle10g</option>
	   							<option value="4">oracle11g</option>
	   							<option value="5">sqlserver2000</option>
	   							<option value="6">sqlserver2005</option>
	   						</select>
	   					</td>
	   				</tr>    				
	   				<tr bgcolor="#ffffff">
	   					<td align="right">实例名：</td>
	   					<td class="tdright">
	   						<input type="text" id="dbInstance" name="dbInstance" class="easyui-validatebox" 
	   							required="true" validType="instanceValid[100]">
	   					</td>
	   				</tr>
	   				<tr bgcolor="#ffffff">
	   					<td align="right">I&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;P：</td>
	   					<td class="tdright"><span id="span1"></span></td>
	   				</tr>
	   				<tr bgcolor="#ffffff">
	   					<td align="right">端&nbsp;&nbsp;&nbsp;口：</td>
	   					<td class="tdright">
	   						<input id="dbPort" value="1521" name="dbPort" class="easyui-numberbox" 
	   							max="65535" required="true">
	   					</td>
	   				</tr>
	   				     
	   				<tr bgcolor="#ffffff">
	   					<td align="right">用户名：</td>
	   					<td class="tdright">
	   						<input type="text" id="dbUser" name="dbUser" class="easyui-validatebox" 
	   							required="true" validType="userValid['']">
	   					</td>
	   				</tr>
	   				<tr bgcolor="#ffffff">
	   					<td align="right">密&nbsp;&nbsp;&nbsp;码：</td>
	   					<td class="tdright">
	   						<input type="password" id="dbPwd" name="dbPwd" class="easyui-validatebox" 
	   							required="true" validType="pwdValid['']">
	   					</td>
	   				</tr>
	   				<tr bgcolor="#ffffff">
	   					<td align="right">描&nbsp;&nbsp;&nbsp;述：</td>
	   					<td class="tdright">
	   						<textarea rows="5" id="descb" name="descb" class="easyui-validatebox" validType="descbValid[2000]"></textarea>
	   					</td>
	   				</tr>
	   			</table>
	   			
	   			<div class="boxBottom">
					<input type="reset" id="clearForm" name="reset" style="display:none;"/>
					<input type="button" value="确定" class="btn" id="btn_ok_3"/>&nbsp;
	   				<input type="button" value="取消" class="btn" id="closeSourceBtn"  data-dismiss="modal"/>&nbsp;
				</div>
	   		</form>
	   	</div>
   	</div>
   	
   	<script type="text/javascript" src="resource/js/common/ipInput.js"></script>
    <script type="text/javascript" src="resource/js/registry/registry-dbs.js"></script>
	<script type="text/javascript">	   
		initGrid(1);	
	</script>
</body>
</html>

