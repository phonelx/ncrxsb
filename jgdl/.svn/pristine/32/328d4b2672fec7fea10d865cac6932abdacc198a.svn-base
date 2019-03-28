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
<meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />

<link rel="apple-touch-icon-precomposed" sizes="114x114" href="resource/images/splash/splash-icon.png" />
<link rel="apple-touch-startup-image" href="resource/images/splash/splash-screen.png"
	media="screen and (max-device-width: 320px)" />
<link rel="apple-touch-startup-image" href="resource/images/splash/splash-screen_402x.png"
	media="(max-device-width: 480px) and (-webkit-min-device-pixel-ratio: 2)" />
<link rel="apple-touch-startup-image" sizes="640x1096" href="resource/images/splash/splash-screen_403x.png" />
<link rel="apple-touch-startup-image" sizes="1024x748" href="resource/images/splash/splash-screen-ipad-landscape"
	media="screen and (min-device-width : 481px) and (max-device-width : 1024px) and (orientation : landscape)" />
<link rel="apple-touch-startup-image" sizes="768x1004"  href="resource/images/splash/splash-screen-ipad-portrait.png"
	media="screen and (min-device-width : 481px) and (max-device-width : 1024px) and (orientation : portrait)" />
<link rel="apple-touch-startup-image" sizes="1536x2008" href="resource/images/splash/splash-screen-ipad-portrait-retina.png"
	media="(device-width: 768px)	and (orientation: portrait)	and (-webkit-device-pixel-ratio: 2)" />
<link rel="apple-touch-startup-image" sizes="1496x2048" href="resource/images/splash/splash-screen-ipad-landscape-retina.png"
	media="(device-width: 768px)	and (orientation: landscape)	and (-webkit-device-pixel-ratio: 2)" />

<title>已部署列表</title>
<link href="resource/styles/style.css" rel="stylesheet" type="text/css" />
<link href="resource/styles/framework.css" rel="stylesheet" type="text/css" />
<link href="resource/styles/owl.carousel.css" rel="stylesheet" type="text/css" />
<link href="resource/styles/owl.theme.css" rel="stylesheet" type="text/css" />
<link href="resource/styles/swipebox.css" rel="stylesheet" type="text/css" />
<link href="resource/styles/colorbox.css" rel="stylesheet" type="text/css" />
<link href="resource/styles/charisma-app.css" rel="stylesheet"
	type="text/css" />
<link href="resource/styles/bootstrap-cerulean.css" rel="stylesheet"
	type="text/css" />
<style type="text/css">
.check {
	color: #FFF;
	background-color: #E67E22;
	width: 60px;
}
.images-content{ 
	position: absolute;
    right: 65px;
	color:#FFFFFF;
	width:40px;
	height:10px;
    top: 8px;
    text-align: center;
    font-family: 'Dosis', sans-serif;
    font-size: 14px;
    font-weight:600;
}
.approval{
   background-image:url(resource/images/lists/blog_cat.png);
	background-size:28px 22px;
	background-position:8px 0px;
	background-repeat:no-repeat; 
	position:absolute;
	left:0px;
	padding-top:30px;
	top:105px;
	height:50px;
	width:50px;
	text-align:center;
	font-family:'Dosis', sans-serif;
	color:#5B5B5B;
	font-size: 6px;
}
.notPass{
    background-image:url(resource/images/lists/blog_more.png);
	background-size:34px 24px;
	background-position:8px 0px;
	background-repeat:no-repeat; 
	padding-top:30px;
	position:absolute;
	left:60px;
	top:105px;
	height:50px;
	width:50px;
	text-align:center;
	font-family:'Dosis', sans-serif;
	color:#5B5B5B;
	font-size: 6px;
}
.back{
    color:#000;
    background-image:url(resource/images/lists/blog_more.png);
	background-size:28px 28px;
	background-position:8px 0px;
	background-repeat:no-repeat; 
	position:absolute;
	left:120px;
	top:105px;
	height:50px;
	width:50px;
	padding-top:30px;
	text-align:center;
	font-family:'Dosis', sans-serif;
}
.search-list{
    background-image: url(../images/lists/Search icon.png);
    background-repeat: no-repeat;
    position:relative;
    top:20px;
    height: 50px;
    margin-left: 83%;
    list-style-type: none;
    background-size: 23px 23px;
    display: block;
    padding: 0px 0px;
}
.tabs{
position: relative;
top:1px;
z-index: 2;
}
table{
	max-width: 97%;
	margin-left:6px;
}
.tab-active {
background-color: #fff !important;
border-top:1px solid #ccc;
border-left:1px solid #ccc;
border-right:1px solid #ccc;
border-bottom: 1px solid #fff;
}
.tab-content {
border-top:1px solid #ccc;
background-color: #fff !important;
}
.tab-but {
  margin-bottom: 0px !important;
}
#btnDiv input{
float: left;
margin-right: 10px;
position: relative;
top: -15px;
}
</style>
<style type="text/css">
DIV.jogger .top A {
	display: inline;
	border: 1px solid #eee;
	padding: 2px 7px;
	background-color: #f6f6f6;
}
DIV.jogger .bottom A{
	margin: 5px 0px;
}
DIV.jogger A:hover { 
	background-color: #fff;
} 
</style>
<script type="text/javascript" src="resource/scripts/jquery.js"></script>
<script type="text/javascript" src="resource/scripts/jqueryui.js"></script>
<script type="text/javascript" src="resource/scripts/owl.carousel.min.js"></script>
<script type="text/javascript" src="resource/scripts/jquery.swipebox.js"></script>
<script type="text/javascript" src="resource/scripts/colorbox.js"></script>
<script type="text/javascript" src="resource/scripts/snap.js"></script>
<script type="text/javascript" src="resource/scripts/contact.js"></script>
<script type="text/javascript" src="resource/scripts/custom.js"></script>
<script type="text/javascript" src="resource/scripts/framework.js"></script>
<script type="text/javascript" src="resource/scripts/framework.launcher.js"></script>
<script type="text/javascript" src="resource/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="resource/js/uiGrid.js"></script>
<script type="text/javascript" src="resource/js/bk/deploy/deployIndex.js"></script>
</head>
<body>
	<!-- 加载动画 -->
	<div id="preloader">
		<div id="status">
			<p class="center-text">正在加载..</p>
		</div>
	</div>

	<div class="top-deco"></div>
	<div class="content">
		<div class="header">
    		<a href="javascript:void(0);" class="homepage-logo">
    			<img src="resource/images/misc/logo.jpg" alt="img" />
       	 	</a>
        	<!-- 当前页面为已部署，并且当前用户不为审批角色时 -->
        	<%-- <c:when test="${userAdmin != 1 } "> --%>
       		<a href="index.do" class="go-home"  style="right:120px;">主页</a>
       		<a href="javascript:void(0);" class="new-list" style="display: block;">最新预警</a>
			<div class="images-content">0</div>
	        <a href="javascript:void(0);" class="go-menu" >菜单</a>
	        <a href="javascript:void(0);" class="go-back">CLOSE</a>
    	</div>
    	<!--<div class="decoration">已部署</div> -->
    	<div class="navigation" >
    		<div class="corner-deco"></div>
    		<!--动态生成一级菜单div  -->
    		<div class="navigation-wrapper">
    			<!-- el遍历生成一级菜单 -->
          		<c:forEach var="mainBean" items="${sessionScope.moduleMainDtoList}">
            		<div class="navigation-item">
	 					<a class="features-icon has-submenu">${mainBean.name}</a><em
	 						class="dropdown-menu dropup-menu"></em>
	 					<!--el遍历生成二级菜单  -->
	 					<c:forEach var="subBean" items="${mainBean.subModuleList}">
	 						<div class="submenu">
                    			<a href="javascript:gotoModule('${subBean.entryUrl}','${subBean.name}',
                    			'${mainBean.name}');">${subBean.name}<em></em></a>
                			</div>
                		</c:forEach>
            		</div>
				</c:forEach>
			</div>
		</div>
	</div>
	<div class="row-fluid sortable dbox" style="width:95%;">	
			<div class="box span12" style="margin-left:10px;margin-bottom: 0px;border-bottom: 0px solid red;">
				<div class="box-header pa" data-original-title="">
					<h2 style=""><i class="icon-user"></i>已部署</h2>	
				</div>
			</div>
	</div>
	<div id="dispatchedGrld" style="margin:0px 5px; "></div>
	<div class="bottom-deco"></div>
</body>

</html>