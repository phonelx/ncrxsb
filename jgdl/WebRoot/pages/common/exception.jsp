<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
response.addHeader("__exception","true");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>程序异常</title>
		<style type="text/css">
<!--
.STYLE10 {
	font-family: "黑体";
	font-size: 36px;
}
-->
</style>
	</head>
	<body>
		<table width="510" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr>
				<td>
					<img src="resource/images/error/error_top.jpg" width="510" height="80" />
				</td>
			</tr>
			<tr>
				<td height="200" align="center" valign="top" background="<%=basePath%>/main/images/error/error_bg.jpg">
					<table width="80%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="34%" align="right">
								<img src="resource/images/error/error.gif" width="128" height="128">
							</td>
							<td width="66%" valign="bottom" align="center">
								<span class="STYLE10">程序异常</span>
								<div style="text-align: left; line-height: 22px;">
									<font size="2">errorMsg:${errorMsg }</font><br/>
									<font size="2">errorClass:${errorClass }</font>
								</div>
								<a href="#" onclick="javascript:history.back(-1);">后 退</a>
							</td>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<img src="resource/images/error/error_bootom.jpg" width="510" height="32" />
				</td>
			</tr>
		</table>
	</body>
</html>