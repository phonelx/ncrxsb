<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">session1 -->
<!DOCTYPE html>
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
<link rel="apple-touch-startup-image" href="resource/images/splash/splash-screen.png" 			media="screen and (max-device-width: 320px)" />  
<link rel="apple-touch-startup-image" href="resource/images/splash/splash-screen_402x.png" 		media="(max-device-width: 480px) and (-webkit-min-device-pixel-ratio: 2)" /> 
<link rel="apple-touch-startup-image" sizes="640x1096" href="resource/images/splash/splash-screen_403x.png" />
<link rel="apple-touch-startup-image" sizes="1024x748" href="resource/images/splash/splash-screen-ipad-landscape" media="screen and (min-device-width : 481px) and (max-device-width : 1024px) and (orientation : landscape)" />
<link rel="apple-touch-startup-image" sizes="768x1004" href="resource/images/splash/splash-screen-ipad-portrait.png" media="screen and (min-device-width : 481px) and (max-device-width : 1024px) and (orientation : portrait)" />
<link rel="apple-touch-startup-image" sizes="1536x2008" href="resource/images/splash/splash-screen-ipad-portrait-retina.png"   media="(device-width: 768px)	and (orientation: portrait)	and (-webkit-device-pixel-ratio: 2)"/>
<link rel="apple-touch-startup-image" sizes="1496x2048" href="resource/images/splash/splash-screen-ipad-landscape-retina.png"   media="(device-width: 768px)	and (orientation: landscape)	and (-webkit-device-pixel-ratio: 2)"/>

<title></title>

<link href="resource/styles/style.css"  rel="stylesheet" type="text/css" />
<link href="resource/styles/framework.css" rel="stylesheet" type="text/css" />
<link href="resource/styles/owl.carousel.css" rel="stylesheet" type="text/css" />
<link href="resource/styles/owl.theme.css" rel="stylesheet" type="text/css" />
<link href="resource/styles/swipebox.css"  rel="stylesheet" type="text/css" />
<link href="resource/styles/colorbox.css"  rel="stylesheet" type="text/css" />
<link href="resource/css/approval.css"  rel="stylesheet" type="text/css" />
<link href="resource/styles/charisma-app.css" rel="stylesheet"
	type="text/css" />
<link href="resource/styles/bootstrap-cerulean.css" rel="stylesheet"
	type="text/css" />
<style type="text/css">
.h{
	margin-left:12px;
	background-color:rgba(255, 255, 255, 0.1);
}
.check {
	color: #FFF;
	background-color: #E67E22;
	width: 60px;
}
table{
    max-width: 95%;
    margin-left: 10.1px;
}
th{
	background-color: #ffffff;
}
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

<script type="text/javascript">
//获取当前页面数据类型(布控状态0:待审批,1:布控,2:过期)
var type = "${type}";
</script>
<script type="text/javascript" src="resource/js/bk/approvalPending/approvals.js"></script>
</head>
<body>
<div id="preloader">
	<div id="status">
    	<p class="center-text">
		正在加载..
        </p>
    </div>
</div>
<div class="top-deco"></div>
<div class="content">
	<div class="header">
    	<a href="#" class="homepage-logo">
        	<img src="resource/images/misc/logo.jpg" alt="img" />
        </a>
       <a href="index.do" class="go-home">主页</a>
        <a href="#" class="go-menu">菜单</a>
        <a href="#" class="go-back">CLOSE</a>
    </div>
    <!-- <div class="decoration">11</div> -->
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
<div class="row-fluid sortable dbox" style="width:95%;">	
			<div class="box span12 ds" style="margin-left:10px;margin-bottom: 0px;border-bottom: 0px solid red;">
				<div class="box-header pa" data-original-title="">
					<h2 class="decoration h"><i class="icon-user"></i>11</h2>	
				</div>
			</div>
</div>
<div id="approvalGrld"></div>
<div id="container"></div>
<div class="bottom-deco"></div>
</body>
</html>