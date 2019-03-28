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
<meta name=”viewport” content=”width=device-width, initial-scale=1″ />
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
	media="(device-width: 768px)	and (orientation: portrait)	and (-webkit-device-pixel-ratio: 2)" />
<link rel="apple-touch-startup-image" sizes="1496x2048"
	href="resource/images/splash/splash-screen-ipad-landscape-retina.png"
	media="(device-width: 768px)	and (orientation: landscape)	and (-webkit-device-pixel-ratio: 2)" />
<title></title>
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
<link href="resource/css/approvaldetails.css" rel="stylesheet"
	type="text/css" />
<style type="text/css">



@media only screen and (max-width: 600px) { #showMsg{ font-size: 14px; }}
@media only screen and (max-width: 500px) { #showMsg{ font-size: 10px; }}
@media only screen and (max-width: 400px) { #showMsg{ font-size: 8px; }}
@media only screen and (max-width: 300px) { #showMsg{ font-size:6px; }}

</style>
<script type="text/javascript" src="resource/scripts/jquery.js"></script>
<script type="text/javascript" src="resource/scripts/jqueryui.js"></script>
<link rel="shortcut icon" href="resource/new/bootstrap/img/favicon.ico" />
<script type="text/javascript"
	src="resource/scripts/owl.carousel.min.js"></script>
<script type="text/javascript" src="resource/scripts/jquery.swipebox.js"></script>
<script type="text/javascript" src="resource/scripts/colorbox.js"></script>
<script type="text/javascript" src="resource/scripts/snap.js"></script>
<script type="text/javascript" src="resource/scripts/contact.js"></script>
<script type="text/javascript" src="resource/scripts/custom.js"></script>
<script type="text/javascript" src="resource/scripts/framework.js"></script>
<script type="text/javascript" src="resource/scripts/framework.launcher.js"></script>
<script type="text/javascript" src="resource/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="resource/js/uiGrid.js"></script>
<script type="text/javascript">
	var json = ${personMsg.personMsg};

</script>
	<script type="text/javascript" src="resource/js/addapplication/addnewbkxx.js"></script>

</head>
<body>
	<div id="preloader">
		<div id="status">
			<p class="center-text">正在加载..</p>
		</div>
	</div>
	<div class="top-deco"></div>
	<div class="content">
		<div class="header">
			<a href="#" class="homepage-logo"> <img
				src="resource/images/misc/logo.jpg" alt="img" />
			</a> <a href="index.do" class="go-home">主页</a> <a href="#"
				class="go-menu">菜单</a> <a href="#" class="go-back">CLOSE</a>
		</div>
	</div>
	<div id="overdueGrld">
		<div id="showMsg">
					<div class="text">姓名:&nbsp;<span class="xm"></span></div>
					<div class="text">身份证号:&nbsp;<span class="sfzh"></span></div>
					<div class="text">民族:&nbsp;<span class="mz"></span></div>
				    <div class="text">居住地&nbsp;<span class="jzd"></span></div>
				    <div class="text">人员类型:&nbsp;<span class="rylx"></span></div>
	    </div>
	    <div id="form">
			 <div id="bzz">
			 	<textarea type="text" name="bz" id="bz" style="width:100%;height: 70px"/></textarea>
			 </div>
			 <div id="input">
			 	<div id="back">
			 	  <input type="button" value="返回" id="btnback" style="margin-left:250%;" onclick="window.history.go(-1)"/> 
			 	</div>
	    	</div>
	    </div>	
	</div>
</body>
</html>