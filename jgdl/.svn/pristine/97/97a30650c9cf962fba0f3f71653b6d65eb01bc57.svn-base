<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"><!--session1-->
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<base href="<%=basePath%>" target="_self" />
	<%response.setHeader("Pragma","No-cache");          
        response.setHeader("Cache-Control","no-cache");   
        response.setHeader("Cache-Control", "no-store");   
        response.setDateHeader("Expires",0);
        response.flushBuffer();  
 %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0"/>
<meta name="apple-mobile-web-app-capable" content="yes"/>
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<link rel="apple-touch-icon-precomposed" sizes="114x114" href="resource/images/splash/splash-icon.png" />
<link rel="apple-touch-startup-image" href="resource/images/splash/splash-screen.png" media="screen and (max-device-width: 320px)" />  
<link rel="apple-touch-startup-image" href="resource/images/splash/splash-screen_402x.png" media="(max-device-width: 480px) and (-webkit-min-device-pixel-ratio: 2)" /> 
<link rel="apple-touch-startup-image" sizes="640x1096" href="resource/images/splash/splash-screen_403x.png" />
<link rel="apple-touch-startup-image" sizes="1024x748" href="resource/images/splash/splash-screen-ipad-landscape" media="screen and (min-device-width : 481px) and (max-device-width : 1024px) and (orientation : landscape)" />
<link rel="apple-touch-startup-image" sizes="768x1004" href="resource/images/splash/splash-screen-ipad-portrait.png" media="screen and (min-device-width : 481px) and (max-device-width : 1024px) and (orientation : portrait)" />
<link rel="apple-touch-startup-image" sizes="1536x2008" href="resource/images/splash/splash-screen-ipad-portrait-retina.png" media="(device-width: 768px)	and (orientation: portrait)	and (-webkit-device-pixel-ratio: 2)"/>
<link rel="apple-touch-startup-image" sizes="1496x2048" href="resource/images/splash/splash-screen-ipad-landscape-retina.png" media="(device-width: 768px)	and (orientation: landscape)	and (-webkit-device-pixel-ratio: 2)"/>
<title>首页</title>
<link href="resource/styles/style.css"  rel="stylesheet" type="text/css" />
<link href="resource/styles/framework.css" rel="stylesheet" type="text/css" />
<link href="resource/styles/owl.carousel.css" rel="stylesheet" type="text/css" />
<link href="resource/styles/owl.theme.css" rel="stylesheet" type="text/css" />
<link href="resource/styles/swipebox.css"  rel="stylesheet" type="text/css" />
<link href="resource/styles/colorbox.css"  rel="stylesheet" type="text/css" />
<style type="text/css">
.check {
	color: #FFF;
	background-color: #E67E22;
	width: 60px;
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
<script type="text/javascript" src="resource/js/user/cookie.js"></script>
	<script type="text/javascript">
	$(document).ready(function(){ 
	setCookie("username",document.cookie,30);//30天
	   });
	function goLogout(){
	clearCookie("username")
	var form = document.createElement("form");
	form.style.display = "none";
	form.action = "goloutout.do";
	form.method = "post";
	document.body.appendChild(form);
	form.submit(); 
	}
	</script>
</head>
<body>

<div id="preloader">
	<div id="status">
    	<p class="center-text"> 正在加载.. </p>
    </div>
</div>

<div class="top-deco"></div>

<div class="landing-page">
	<div class="content">
		<div class="header">
	    	<a href="javascript:void(0);" class="homepage-logo">
	        	<img src="resource/images/misc/logo.jpg" alt="img" />
	        </a>
	       <!--  <a href="index.do" class="go-home">主页</a> -->
	        <a href="javascript:void(0);" class="go-menu">菜单</a>
	        <a href="javascript:void(0);" class="go-back">CLOSE</a>
	        <a href="javascript:void(0);" class="go-logout" onclick="goLogout()">注销</a>
	    </div>
	    <div class="decoration"></div>
	    
	    <div class="navigation" >
	    	<div class="corner-deco"></div>
	    	<!--动态生成一级菜单div  -->
	    	<div class="navigation-wrapper">
	    	<!-- el遍历生成一级菜单 -->
	          <c:forEach var="mainBean" items="${sessionScope.moduleMainDtoList}">
	            <div class="navigation-item">
		 				<a class="features-icon has-submenu">${mainBean.name}</a><em class="dropdown-menu dropup-menu"></em>
		 			<!--el遍历生成二级菜单  -->
		 			<c:forEach var="subBean" items="${mainBean.subModuleList}">
		 				<div class="submenu">
	                    	<a href="javascript:gotoModule('${subBean.entryUrl}','${subBean.name}','${mainBean.name}');">${subBean.name}<em></em></a>
	                	</div>
	                </c:forEach>
	            </div>
			</c:forEach>
	            
	        </div>
	    </div>
	</div>
     
    <div class="landing-navigation">
    <div class="landing-navigation-tablet-top-adjustment"></div> 
    	<a href="gotoDeployIndex.do">
        	<img class="nav-icon" src="resource/images/icons/misc/home.png" alt="img" />
            <div class="nav-overlay"></div>
        	<img class="nav-image" src="resource/images/general-nature/1s.jpg" alt="img" />
            <em>已部署</em>
        </a>
    	<a href="gotoOverdueIndex.do">
        	<img class="nav-icon" src="resource/images/icons/misc/infoabout.png" alt="img" />
            <div class="nav-overlay"></div>
        	<img class="nav-image" src="resource/images/general-nature/3s.jpg" alt="img" />
            <em>已过期</em>
        </a>
    	<a href="gotoApprovalIndex.do">
        	<img class="nav-icon" src="resource/images/icons/settings/cog4.png" alt="img" />
            <div class="nav-overlay"></div>
        	<img class="nav-image" src="resource/images/general-nature/2s.jpg" alt="img" />
            <em>待审批</em>
        </a>
    	<div class="clear"></div>
        <div class="landing-navigation-tablet-bottom-adjustment"></div> 
    </div>
    
  
    <div class="decoration"></div>
    
    <div class="footer">
    <div class="landing-navigation-add">
      <div class="landing-navigation-tablet-top-adjustment"></div>
      <a href="type.do">
       	  <img class="nav-icon-add" src="resource/images/icons/misc/4.png" alt="img" />
      <div class="nav-overlay-add"></div>
        	<img class="nav-image-add" src="resource/images/general-nature/3s.jpg" alt="img" /><em>新增布控</em></a>
    	<div class="clear"></div>
        <div class="landing-navigation-tablet-bottom-adjustment"></div>
    </div>
    	
        <div class="clear"></div>
        <p class="copyright">
        </p>
        
    </div>  
</div>

<div class="bottom-deco"></div>

</body>
</html>