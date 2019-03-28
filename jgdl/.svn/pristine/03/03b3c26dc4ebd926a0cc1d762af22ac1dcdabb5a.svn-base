<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/pages/head.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<base href="<%=basePath%>">

	<title>合力</title>
	<script type="text/javascript" src="resource/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="resource/js/common/ipInput.js"></script>
	<script type="text/javascript" src="resource/js/common/popup.js"></script>
	<script type="text/javascript" src="resource/js/main.js"></script>
	<script type="text/javascript" src="resource/js/log/queryUserLogin.js"></script>
	<style type="text/css">
		body,div,input,a,form,table,li,ul{margin:0px;padding:0px;}
		#userLoginSearchBox{padding:10px 0px;text-align:right;}
		#entityMgr input{width:230px;}
		#entityMgr select{width:240px;border-color:#CCCCCC;border-radius:3px;}
		#entityMgr textarea{width:230px;}
		.boxBottom{padding:10px 0px;text-align:right;}
		#loginLogBtu{width:90px !important;}
		
		#loginIP input{width:47px;margin:0px;}
	</style>
</head>
<body>
	<div id="userLoginSearchBox">
		<div></div>
		<div>
			<a title="导出" class="btn" onclick="exportLoginLog();" href="javascript:void(0);" style="margin-right:10px;">
				<i class="icon-plus-sign"></i> 导出
			</a>
			
			<a title="删除" class="btn" onclick="deleteSelectedUserLgI();" href="javascript:void(0);" style="margin-right:10px;">
				<i class="icon-plus-sign"></i> 删除
			</a>
			
			<a title="清空" class="btn" onclick="confirmDeleteUserLog();" href="javascript:void(0);" style="margin-right:10px;">
				<i class="icon-plus-sign"></i> 清空(筛选日志)
			</a>
		</div>
	</div>

	<div id="entityMgr" style="height: 100%">
		<div style="height: 80px; width: 100%; background-color: #D3EAFC; overflow: hidden;">
			<form id="loginLogForm" action="exportUserLgIAsExcel.do" method="POST" target="_blank">
				<table>
					<tr>
						<td>
							<table>
								<tr>
									<td>
										用&nbsp;&nbsp;&nbsp;户:
									</td>
									<td>
										<input id="usertitle" type="text" name="usertitle" />
									</td>
								</tr>
								<tr>
									<td>
										登录IP:
									</td>
									<td bgcolor="#FFFFFF" align="center">
										<span id="loginIP"></span>
									</td>
								</tr>
							</table>
						</td>

						<td>
							<table>
								<tr>
									<td>
										登录时间:&nbsp;
										<input id="lgistartDatetime" name="lgistartDatetime" class="Wdate" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" style="width: 140px" />
									</td>
									<td>
										至:
										<input id="lgiendDatetime" name="lgiendDatetime" class="Wdate" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" style="width: 140px" />
									</td>
								</tr>
								<tr>
									<td>
										退出时间:&nbsp;
										<input id="lgostartDatetime" name="lgostartDatetime" class="Wdate" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" style="width: 140px" />
									</td>
									<td>
										至:
										<input id="lgoendDatetime" name="lgoendDatetime" class="Wdate" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" style="width: 140px;" />
									</td>
								</tr>
							</table>
						</td>
						<td>
							返回条数:
							<select id="maxRowsLogin" name="maxRowsLogin">
								<option value="500">
									500
								</option>
								<option value="1000">
									1000
								</option>
								<option value="5000">
									5000
								</option>
								<option value="10000">
									10000
								</option>
								<option value="-1">
									所有
								</option>
							</select>
							<input type="button" value="查 询" id="loginLogBtu" class="btn" onclick="timeCheckUserLog()" style="height: 25px"/>
						</td>
					</tr>
				</table>
			</form>
		</div>
		
		<div id='gridDiv'>
			<div id="grid1"></div>
		</div>
	</div>
	<script type="text/javascript">
		//登录IP
		var loginIP = new IpV4Box("loginIP" , "loginIP"); 
		document.onkeydown = function (e) { 
			var theEvent = window.event || e; 
			var code = theEvent.keyCode || theEvent.which; 
			if (code == 13) { 
				showLogGrid(1);
			} 
		}
		//$"#gridDiv").QueryPhoto(0);
		//$(function(){
		//	showLogGrid(1);
		//});

		showLogGrid(1);
	</script>
</body>
</html>
