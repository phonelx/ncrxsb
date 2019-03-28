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
		body,div,input,a,form,td,tr,table,li,ul{margin:0px;padding:0px;}
		#userOprSearchBox{padding:10px 0px;text-align:right;}
		#entityMgr input{width:230px;}
		#entityMgr select{width:240px;border-color:#CCCCCC;border-radius:3px;}
		#entityMgr textarea{width:230px;}
		.boxBottom{padding:10px 0px;text-align:right;}
		#loginLogBtu{width:90px !important;}
	</style>
</head>
<body>
	<div id="userOprSearchBox">
		<div></div>
		<div>
			<a title="导出" class="btn" onclick="exportOprationLog();" href="javascript:void(0);" style="margin-right:10px;">
				<i class="icon-plus-sign"></i> 导出
			</a>
			
			<a title="删除" class="btn" onclick="deleteSelectedUserOprLog();" href="javascript:void(0);" style="margin-right:10px;">
				<i class="icon-plus-sign"></i> 删除
			</a>
			
			<a title="清空" class="btn" onclick="confirmDeleteAllUserOprLog();" href="javascript:void(0);" style="margin-right:10px;">
				<i class="icon-plus-sign"></i> 清空(筛选日志)
			</a>
		</div>
	</div>
	
	<div id="entityMgr" style="height: 100%">
		<div style="height: 80px; width: 100%; background-color: #D3EAFC; overflow: hidden;margin-top: 6px;">
			<form id="oprationLogForm" action="exportUserOprAsExcel.do" method="POST" target="_blank">
				<table>
					<tr>
						<td>
							<table>
								<tr>
									<td>
										用&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;户:
									</td>
									<td>
										<input id="usertitle" type="text" name="usertitle" />
									</td>
								</tr>
								<tr>
									<td>
										操作类型:
									</td>
									<td>
										<select id="operatetype" name="operatetype">
											<option value="">
												==请选择==
											</option>
											<option value="0">
												添加
											</option>
											<option value="1">
												删除
											</option>
											<option value="2">
												修改
											</option>
											<option value="3">
												查询
											</option>
											<option value="4">
												WS查询
											</option>
											<option value="5">
												申请
											</option>
										</select>
									</td>
								</tr>
							</table>
						</td>

						<td>
							<table>
								<tr>
									<td width="70">
										操作时间:
									</td>
									<td>
										<input style="width: 140px" id="lgistartDatetime" name="lgistartDatetime" class="Wdate" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" />
									</td>
									<td align="center">
										至:
									</td>
									<td>
										<input style="width: 140px" id="lgiendDatetime" name="lgiendDatetime" class="Wdate" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" />
									</td>
								</tr>
								<tr>
									<td width="80">
										操作关键字:
									</td>
									<td align="left">
										<select style="width: 140px" id="operatekeywords" name="operatekeywords">
											<option value="">
												===关键字===
											</option>
											<option value="添加数据源">
												添加数据源
											</option>
											<option value="删除查询">
												删除查询
											</option>
											<option value="修改查询">
												修改查询
											</option>
											<option value="添加实体关系">
												添加实体关系
											</option>
											<option value="删除实体关系">
												删除实体关系
											</option>
											<option value="修改实体关系">
												修改实体关系
											</option>
											<option value="添加角色">
												添加角色
											</option>
											<option value="删除角色">
												删除角色
											</option>
											<option value="修改角色">
												修改角色
											</option>
											<option value="添加用户">
												添加用户
											</option>
											<option value="删除用户">
												删除用户
											</option>
											<option value="修改用户">
												修改用户
											</option>
											<option value="添加数据字典类型">
												添加数据字典类型
											</option>
											<option value="删除数据字典类型">
												删除数据字典类型
											</option>
											<option value="修改数据字典类型">
												修改数据字典类型
											</option>
											<option value="添加数据字典详细信息">
												添加数据字典详细信息
											</option>
											<option value="删除数据字典详细信息">
												删除数据字典详细信息
											</option>
											<option value="修改数据字典详细信息">
												修改数据字典详细信息
											</option>
											<option value="执行查询服务">
												执行查询服务
											</option>
											<option value="执行数据同步">
												执行数据同步
											</option>
											<option value="查询用户登录日志">
												查询用户登录日志
											</option>
											<option value="查询用户操作日志">
												查询用户操作日志
											</option>
											<option value="查询系统运行日志">
												查询系统运行日志
											</option>
											<option value="查询用户登录日志">
												查询用户登录日志
											</option>
											<option value="删除用户登录日志">
												删除用户登录日志
											</option>
											<option value="删除用户操作日志">
												删除用户操作日志
											</option>
											<option value="删除系统运行日志">
												删除系统运行日志
											</option>
											<option value="数据共享申请">
												数据共享申请
											</option>
										</select>
									</td>
									<td width="70">
										操作描述:
									</td>
									<td>
										<input style="width: 140px" id="operatedescb" type="text" name="operatedescb" />
									</td>
								</tr>
							</table>
						</td>
						<td>
							返回条数:
							<select id="maxRowsOpr">
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
							<input type="button" value="查 询" onclick="timeCheckUserOpr()" style="width:47px;height:25px;"/>
						</td>
					</tr>
				</table>
			</form>
		</div>

		<div id='gridDiv'>
			<table id="grid"></table>
		</div>
	</div>

	<div id="doQueryAgainShow" class="modal hide fade in" style="display:none;width:600px;height:600px;top:40%;left:50%;">
		<table id="doQueryAgainGrid"></table>
		<div>
			<iframe width="100%" height="100%" style="z-index:-1;top:0px;left:0px;position:absolute;"></iframe>
		</div>
	</div>

	<!-- 右键查看详情 start-->
	<div id="menu" style="display:none;width: 120px;">
		<div onclick="$('#rowDetail').window('open');">查看详情</div>
	</div>
	<div id="rowDetail" class="modal hide fade in" style="display:none;width:600px;height:600px;top:40%;left:50%;">
		<div style="width: 570px;height:400px;overflow-y:auto;overflow-x:hide;" align="left">
			<table id="rowDetailGrid" cellspacing="1" border="1" align="center" width="99%" >
				<tbody id="rowdata">
					<tr>
						<td style="background-color:#8cb5eb;width:15%;"><div align="center">用户名</div></td>
						<td id="row_usertitle"></td>
					</tr>
					<tr>
						<td style="background-color:#8cb5eb;width:15%;"><div align="center">操作类型</div></td>
						<td id="row_operatetype"></td>
					</tr>
					<tr>
						<td style="background-color:#8cb5eb;width:15%;"><div align="center">操作关键字</div></td>
						<td id="row_operatekeywords"></td>
					</tr>
					<tr>
						<td style="background-color:#8cb5eb;width:15%;"><div align="center">操作日期</div></td>
						<td id="row_operateDatetime"></td>
					</tr>
					<tr>
						<td style="background-color:#8cb5eb;width:15%;"><div align="center">操作描述</div></td>
						<td>
							<textarea id="row_operatedescb" rows="20" cols="55" readonly="readonly"></textarea>
						</td>
					</tr>					
				</tbody>
			</table>
		</div>
	</div>
	<!-- 右键查看详情 end-->
	
	<script type="text/javascript">
		//相应回车按钮事件
		document.onkeydown = function (e) {
		var theEvent = window.event || e;
		var code = theEvent.keyCode || theEvent.which;
			if (code == 13) {
				showUserOpr(1);
			}
		};
		//$("#gridDiv").QueryPhoto(0);
		//$("#rowDetail").QueryPhoto(1);

		showUserOpr(1);
	</script>
</body>
</html>
