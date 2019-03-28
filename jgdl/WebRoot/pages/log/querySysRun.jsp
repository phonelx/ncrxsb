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
	<script type="text/javascript" src="resource/js/main.js"></script>
	<script type="text/javascript" src="resource/js/log/queryUserLogin.js"></script>
	
	<style type="text/css">
		body,div,input,a,form,table,li,ul{margin:0px;padding:0px;}
		#systemSearchBox{padding:10px 0px;text-align:right;}
		#entityMgr input{width:230px;}
		#entityMgr select{width:240px;border-color:#CCCCCC;border-radius:3px;}
		#entityMgr textarea{width:230px;}
		.boxBottom{padding:10px 0px;text-align:right;}
		#systemLogBtu{width:90px !important;}
	</style>
</head>
<body>
	<div id="systemSearchBox">
		<div></div>
		<div>
			<a title="导出" class="btn" onclick="exportLoginLog();" href="javascript:void(0);" style="margin-right:10px;">
				<i class="icon-plus-sign"></i> 导出
			</a>
			
			<a title="删除" class="btn" onclick="deleteSelectedSysRunLog();" href="javascript:void(0);" style="margin-right:10px;">
				<i class="icon-plus-sign"></i> 删除
			</a>
			
			<a title="清空" class="btn" onclick="confirmDeleteSysRunLog();" href="javascript:void(0);" style="margin-right:10px;">
				<i class="icon-plus-sign"></i> 清空(筛选日志)
			</a>
		</div>
	</div>

	<div id="entityMgr" style="height:100%">
		<div style="height:80px;width:100%;background-color:#D3EAFC;overflow:hidden;">
			<form action="exportSysRunAsExcel.do" method="POST" target="_blank">
				<table>
					<tr>
						<td>
							<table>
								<tr>
									<td>
										系统对象:
									</td>
									<td>
										<input id="exceptionClassName" type="text" name="exceptionClassName" />
									</td>
								</tr>
								<tr>
									<td>
										时&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;间:
									</td>
									<td>
										<input style="margin:0px;" id="throwStartDatetime" name="throwStartDatetime" class="Wdate" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" />
									</td>
								</tr>
							</table>
						</td>
						<td>
							<table>
								<tr>
									<td>
										日志类型:
									</td>
									<td>
										<select id="exceptionType" name="exceptionType" required="true" height="27" style="width: 150px;">
											<option value="">
												==请选择==
											</option>
											<option value="0">
												业务逻辑
											</option>
											<option value="1">
												系统运行
											</option>
											<option value="2">
												数据库
											</option>
											<option value="3">
												其他
											</option>
										</select>
									</td>
								</tr>
								<tr>
									<td align="center">
										至:
									</td>
									<td>
										<input style="width:140px;margin:0px;" id="throwEndDatetime" name="throwEndDatetime" class="Wdate" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" />
									</td>
								</tr>
							</table>

						</td>
						<td>
							返回条数:
							<select id="maxRowsSys">
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
							<input type="button" value="查 询" id="systemLogBtu" onclick="timeCheckSysRun()" style="height: 25px"/>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div>
			<div id="grid"></div>
		</div>
	</div>
	
	<script type="text/javascript">
		//showSysInfo();
		
		//相应回车按钮事件
		document.onkeydown = function (e) { 
		var theEvent = window.event || e; 
		var code = theEvent.keyCode || theEvent.which; 
			if (code == 13) { 
				showSysInfo(1);
			} 
		}
		
		showSysInfo(1);
	</script>
</body>
</html>
