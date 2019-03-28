<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
response.addHeader("__noright","true");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>访问拒绝</title>
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
								<span class="STYLE10">您没有权限</span>
								<div style="text-align: left; line-height: 22px;">
									<font size="2">对不起，您的当前角色没有查看此页面的权限。请联系您的系统管理员，以获得相应的权限。点击这里返回主页。如果需要技术支持，点击这里发送邮件。</font>
								</div>
								<a href="#" onclick="javascript:document.location.href='<%=basePath%>/j_logout.do';">重 新 登 录</a>
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