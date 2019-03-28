<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!--session1-->
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<base href="<%=basePath%>" target="_self" />
<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setDateHeader("Expires", 0);
	response.flushBuffer();
%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport"
	content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />

<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="resource/images/splash/splash-icon.png" />
<link rel="apple-touch-startup-image"
	href="resource/images/splash/splash-screen.png"
	media="screen and (max-device-width: 320px)" />
<link rel="apple-touch-startup-image"
	href="resource/images/splash/splash-screen_402x.png"
	media="(max-device-width: 480px) and (-webkit-min-device-pixel-ratio: 2)" />
<link rel="apple-touch-startup-image" sizes="640x1096"
	href="resource/images/splash/splash-screen_403x.png" />
<link rel="apple-touch-startup-image" sizes="1024x748"
	href="resource/images/splash/splash-screen-ipad-landscape"
	media="screen and (min-device-width : 481px) and (max-device-width : 1024px) and (orientation : landscape)" />
<link rel="apple-touch-startup-image" sizes="768x1004"
	href="resource/images/splash/splash-screen-ipad-portrait.png"
	media="screen and (min-device-width : 481px) and (max-device-width : 1024px) and (orientation : portrait)" />
<link rel="apple-touch-startup-image" sizes="1536x2008"
	href="resource/images/splash/splash-screen-ipad-portrait-retina.png"
	media="(device-width: 768px) and (orientation: portrait)	and (-webkit-device-pixel-ratio: 2)" />
<link rel="apple-touch-startup-image" sizes="1496x2048"
	href="resource/images/splash/splash-screen-ipad-landscape-retina.png"
	media="(device-width: 768px) and (orientation: landscape)	and (-webkit-device-pixel-ratio: 2)" />
<title>注册</title>
<link href="resource/styles/style.css" rel="stylesheet" type="text/css" />
<link href="resource/styles/framework.css" rel="stylesheet"
	type="text/css" />
<link href="resource/styles/owl.carousel.css" rel="stylesheet"
	type="text/css" />
<link href="resource/styles/owl.theme.css" rel="stylesheet"
	type="text/css" />
<link href="resource/styles/swipebox.css" rel="stylesheet"
	type="text/css" />
<link href="resource/styles/colorbox.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="resource/scripts/jquery.js"></script>
<script type="text/javascript" src="resource/scripts/jqueryui.js"></script>
<script type="text/javascript"
	src="resource/scripts/owl.carousel.min.js"></script>
<script type="text/javascript" src="resource/scripts/jquery.swipebox.js"></script>
<%-- <script type="text/javascript" src="resource/scripts/colorbox.js"></script> --%>
<script type="text/javascript" src="resource/scripts/snap.js"></script>
<script type="text/javascript" src="resource/scripts/contact.js"></script>
<script type="text/javascript" src="resource/scripts/custom.js"></script>
<script type="text/javascript" src="resource/scripts/framework.js"></script>

<%-- <script type="text/javascript" src="resource/scripts/framework.launcher.js"></script> --%>
<style type="text/css">
input {
	margin-top: 15px;
}

.landing-page .decoration {
	margin-bottom: 12px;
	margin-left: 20px;
	margin-right: 20px;
}

.landing-navigation a {
	color: #fff;
	position: relative;
	left: 225px;
	top: 10px;
}
.submit_button
{
  background-color: #fff;
  color:#003366;
  font-weight:900;
  font-size: 16px;
}
</style>
</head>
<body>
	<div id="bj-deco">
		<div class="top-deco"></div>
		<div class="landing-page">
			<a class="landing-logo" href="javaScript:void(0);"> <img
				src="resource/images/misc/logo.png" alt="img" />
			</a>
			<div class="decoration"></div>
			<div class="landing-navigation">
				<div class="page-container">
					<form action="register.do" name="form1" method="post">
						<input type="text" name="name" class="name" placeholder="请输入您的登录名" />
						<input type="password" name="pwd" class="pwd"
							placeholder="请输入您的登录密码" /> <input type="password" name="cpwd"
							class="cpwd" placeholder="请再次输入您的密码" /> <input type="text"
							name="usertitle" class="usertitle" placeholder="请输入您的用户名称" /> <input
							type="text" name="sfzhm" class="sfzhm" placeholder="请输入您的身份证号码" />
						<input type="submit" class="submit_button" value="注&nbsp;册"
							onclick="return checkForm();" /> <a
							href="/monitor/pages/login.jsp">立即登录</a>
						<h5 style="color:#fff;padding-top: 20px;">${msg }</h5>
					</form>
				</div>
			</div>
			<div class="decoration"></div>
			<div class="footer">
				<div class="clear"></div>
				<p class="copyright" id="font-copyright">COPYRIGHT 2017.</p>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="resource/js/user/register.js"></script>
</body>
</html>