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
<link rel="apple-touch-startup-image" href="resource/images/splash/splash-screen.png" 			media="screen and (max-device-width: 320px)" />  
<link rel="apple-touch-startup-image" href="resource/images/splash/splash-screen_402x.png" 		media="(max-device-width: 480px) and (-webkit-min-device-pixel-ratio: 2)" /> 
<link rel="apple-touch-startup-image" sizes="640x1096" href="resource/images/splash/splash-screen_403x.png" />
<link rel="apple-touch-startup-image" sizes="1024x748" href="resource/images/splash/splash-screen-ipad-landscape" media="screen and (min-device-width : 481px) and (max-device-width : 1024px) and (orientation : landscape)" />
<link rel="apple-touch-startup-image" sizes="768x1004" href="resource/images/splash/splash-screen-ipad-portrait.png" media="screen and (min-device-width : 481px) and (max-device-width : 1024px) and (orientation : portrait)" />
<link rel="apple-touch-startup-image" sizes="1536x2008" href="resource/images/splash/splash-screen-ipad-portrait-retina.png"   media="(device-width: 768px)	and (orientation: portrait)	and (-webkit-device-pixel-ratio: 2)"/>
<link rel="apple-touch-startup-image" sizes="1496x2048" href="resource/images/splash/splash-screen-ipad-landscape-retina.png"   media="(device-width: 768px)	and (orientation: landscape)	and (-webkit-device-pixel-ratio: 2)"/>
<title>人员轨迹信息页面</title>
<link href="resource/styles/style.css" rel="stylesheet" type="text/css" />
<link href="resource/styles/framework.css" rel="stylesheet" type="text/css" />
<link href="resource/styles/owl.carousel.css" rel="stylesheet" type="text/css" />
<link href="resource/styles/owl.theme.css" rel="stylesheet" type="text/css" />
<link href="resource/styles/swipebox.css" rel="stylesheet" type="text/css" />
<link href="resource/styles/colorbox.css"rel="stylesheet" type="text/css" />


<script type="text/javascript" src="resource/scripts/jquery.js"></script>
<script type="text/javascript" src="resource/scripts/jqueryui.js"></script>
<script type="text/javascript" src="resource/scripts/owl.carousel.min.js"></script>
<script type="text/javascript" src="resource/scripts/jquery.swipebox.js"></script>
<script type="text/javascript" src="resource/scripts/snap.js"></script>
<script type="text/javascript" src="resource/scripts/contact.js"></script>
<script type="text/javascript" src="resource/scripts/custom.js"></script>
<script type="text/javascript" src="resource/scripts/framework.js"></script>
<script type="text/javascript" src="resource/scripts/framework.launcher.js"></script>
<script src="resource/new/bootstrap/js/jquery-1.7.2.min.js"></script>
 <link href="resource/new/bootstrap/css/opa-icons.css" rel="stylesheet"/>
<script type="text/javascript">
	/*获取人员信息json*/
	var personJson = ${personMsg};
</script>
<script type="text/javascript" src="resource/js/bk/deploy/personMsg.js"></script>

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
    	<a href="javascript:void(0);" class="homepage-logo">
        	<img src="resource/images/misc/logo.jpg" alt="img"/>
        </a>
         <a href="index.do" class="go-home">主页</a>
        <a href="javascript:void(0);" class="go-menu">菜单</a>
        <a href="javascript:void(0);" class="go-back">CLOSE</a>
    </div>
    
    
    <div class="navigation">
    	<div class="corner-deco"></div>
    	<div class="navigation-wrapper">
            <div class="navigation-item">
                <a href="index.html" class="home-icon">Homepage</a>
                <em class="active-menu"></em>
            </div>
            <div class="navigation-item">
                <a href="#" class="features-icon has-submenu">Features</a>
                <em class="dropdown-menu"></em>
              	<div class="submenu">
                    <a href="type.html">Typography		 <em></em></a>
                    <a href="jquery.html">jQuery		   <em></em></a>
                </div>
            </div>
            <div class="navigation-item">
                <a href="#" class="portfolio-icon has-submenu">Portfolios</a>
                <em class="dropdown-menu"></em>
                <div class="submenu">
                	<a href="tile-folio.html">Tile Portfolio	<em></em></a>
                    <a href="one-column.html">One Column		<em></em></a>
                    <a href="two-column.html">Two Column		<em></em></a>
                    <a href="three-column.html">Three Columns <em></em></a>
                </div>
            </div>
            <div class="navigation-item">
                <a href="#" class="gallery-icon has-submenu">Gallery</a>
                <em class="dropdown-menu"></em>
                <div class="submenu">
                	<a href="tile-gallery.html">Tile Gallery<em></em></a>
                    <a href="thumbnail-gallery.html">Thumb Gallery<em></em></a>
                </div>
            </div>
            <div class="navigation-item">
                <a href="videos.html" class="videos-icon">Videos</a>
                <em class="inactive-menu"></em>
            </div>
            <div class="navigation-item">
                <a href="contact.html" class="contact-icon">Contact</a>
                <em class="inactive-menu"></em>
            </div>
        </div>
    </div>
</div>
<div class="person">
<div class="decoration"></div> 
<div class="one-half-responsive">
            <p class="thumb-left no-bottom">
                <img src="resource/images/general-nature/1111.png" alt="img"/>
                <strong id="name"></strong>
                <em><br/>
                	身份证号码：<span id="sfzhm"></span><br/>
                	民族：<span id="mz"></span><br/>
                	居住地址：<span id="address"></span><br/>
                	人员类别：<span id="personType"></span>
                </em>
            </p>
            <div class="thumb-clear"></div>
        </div>
        <div class="decoration"></div>
        <section id="cd-timeline" class="cd-container">
        
        
			<%-- <div class="cd-timeline-block">
				<div class="cd-timeline-img cd-picture"></div>
		
				<div class="cd-timeline-content">
				  <h2>2017.03.17</h2>
					<p>在逃毒贩</p>
					<a href=" " class="cd-read-more" target="_blank">更多详情</a>
					<span class="cd-date">2014-12-25</span>
				</div>
			</div>
			<div class="cd-timeline-block">
				<div class="cd-timeline-img cd-picture"></div>
				<div class="cd-timeline-content">
				  <h2>2016.03.27</h2>
					<p>在逃毒贩</p>
					<a href=" " class="cd-read-more" target="_blank">更多详情</a>
					<span class="cd-date">2014-12-20</span>
			  </div>
			</div>
			<div class="cd-timeline-block">
				<div class="cd-timeline-img cd-movie"></div>
		
				<div class="cd-timeline-content">
				  <h2>2016.02.14</h2>
					<p>在逃毒贩</p>
					<a href=" " class="cd-read-more" target="_blank">更多详情</a>
					<span class="cd-date">2014-12-14</span>
			  </div>
			</div>
			<div class="cd-timeline-block">
				<div class="cd-timeline-img cd-movie"></div>
		
				<div class="cd-timeline-content">
					<h2>2015.12.27</h2>
					<p>在逃毒贩</p>
					<a href=" " class="cd-read-more" target="_blank">更多详情</a>
					<span class="cd-date">2014-12-05</span>
				</div>
			</div> --%>
		</section>
        <div class="decoration hide-if-responsive"></div>
</div>

<div class="bottom-deco"></div>



</body>
</html>