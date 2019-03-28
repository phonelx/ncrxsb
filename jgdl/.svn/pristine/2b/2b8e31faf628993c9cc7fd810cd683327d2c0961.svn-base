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
<meta name="viewport"
	content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />

<link rel="apple-touch-icon-precomposed" sizes="114x114" href="resource/images/splash/splash-icon.png" />
<link rel="apple-touch-startup-image" href="resource/images/splash/splash-screen.png" media="screen and (max-device-width: 320px)" />
<link rel="apple-touch-startup-image" href="resource/images/splash/splash-screen_402x.png" media="(max-device-width: 480px) and (-webkit-min-device-pixel-ratio: 2)" />
<link rel="apple-touch-startup-image" sizes="640x1096" href="resource/images/splash/splash-screen_403x.png" />
<link rel="apple-touch-startup-image" sizes="1024x748" href="resource/images/splash/splash-screen-ipad-landscape"
	media="screen and (min-device-width : 481px) and (max-device-width : 1024px) and (orientation : landscape)" />
<link rel="apple-touch-startup-image" sizes="768x1004" href="resource/images/splash/splash-screen-ipad-portrait.png"
	media="screen and (min-device-width : 481px) and (max-device-width : 1024px) and (orientation : portrait)" />
<link rel="apple-touch-startup-image" sizes="1536x2008" href="resource/images/splash/splash-screen-ipad-portrait-retina.png"
	media="(device-width: 768px) and (orientation: portrait)	and (-webkit-device-pixel-ratio: 2)" />
<link rel="apple-touch-startup-image" sizes="1496x2048" href="resource/images/splash/splash-screen-ipad-landscape-retina.png"
	media="(device-width: 768px) and (orientation: landscape)	and (-webkit-device-pixel-ratio: 2)" />

<title>登录</title>

<!--<link href="resource/styles/style.css" rel="stylesheet" type="text/css" />
 <link href="resource/styles/framework.css" rel="stylesheet" type="text/css" />
<link href="resource/styles/owl.carousel.css" rel="stylesheet" type="text/css" />
<link href="resource/styles/owl.theme.css" rel="stylesheet" type="text/css" />
<link href="resource/styles/swipebox.css" rel="stylesheet" type="text/css" />
<link href="resource/styles/colorbox.css" rel="stylesheet" type="text/css" /> -->
<script type="text/javascript" src="resource/scripts/jquery.js"></script>
<script type="text/javascript" src="resource/scripts/jqueryui.js"></script>
<script type="text/javascript" src="resource/scripts/owl.carousel.min.js"></script>
<script type="text/javascript" src="resource/scripts/jquery.swipebox.js"></script>
<%-- <script type="text/javascript" src="resource/scripts/colorbox.js"></script> --%>
<script type="text/javascript" src="resource/scripts/snap.js"></script>
<script type="text/javascript" src="resource/scripts/contact.js"></script>
<script type="text/javascript" src="resource/scripts/custom.js"></script>
<script type="text/javascript" src="resource/scripts/framework.js"></script>
<%-- <script type="text/javascript" src="resource/scripts/framework.launcher.js"></script> --%>

<style>
.error {
    display: none;
    position: absolute;
    top: 27px;
    right: -55px;
    width: 40px;
    height: 40px;
    background: #2d2d2d; /* browsers that don't support rgba */
    background: rgba(45,45,45,.25);
    -moz-border-radius: 8px;
    -webkit-border-radius: 8px;
    border-radius: 8px;
}

.error span {
    display: inline-block;
    margin-left: 2px;
    font-size: 40px;
    font-weight: 700;
    line-height: 40px;
    text-shadow: 0 1px 2px rgba(0,0,0,.1);
    -o-transform: rotate(45deg);
    -moz-transform: rotate(45deg);
    -webkit-transform: rotate(45deg);
    -ms-transform: rotate(45deg);

}
body{
	margin:0;
	padding:0;
	overflow: hidden;
	background:url(resource/images/login/backPic.jpg) no-repeat center center;
	width:100%;
	height:100%; 
}
#all{
	position:relative;
	overflow: hidden;
}
.loginCenter{
	background:url(resource/images/login/loginBack.png) no-repeat center center;
	width:464px;
	height:541px;
	position:absolute;
	left:50%;
	top:50%;
	margin-left:-232px;
	margin-top:-270px;
	text-align:center;
}
.inputStyle{
	height:49px;
	width:302px;
	line-height:51px;
	font-size:16px;
	border:none;
	outline:none;
	-moz-border-radius:0 100px 100px 0;
	-webkit-border-radius:0 100px 100px 0;
	border-radius:0 100px 100px 0;
	margin-left:59px;
}
.rememberPass{
	color:white;
	
}
.buttonStyle{
	height:51px;
	width:362px;
	line-height:51px;
	-moz-border-radius:100px;
	-webkit-border-radius:100px;
	border-radius:100px;
	border:none;
	outline:none;
	font-size:18px;
	color:white;
	background-color:white;
	cursor:pointer;
	opacity:0;
}
.buttonStyle:hover{
	opacity:0.2;
}
.wrongText{
	color:red;
	font-size:14px;
}
.loginCenter table{
	margin-left: 52px;
	margin-top: 200px;
}
</style>	

<script>
 function init(){
 	document.getElementById('all').style.height=document.documentElement.clientHeight+'px';
 }
</script>

</head>
<body onload="javascript:init()">

	<div id="all">
		<div class="loginCenter">
			<form action="login.do" name="form1" method="post">
				<table border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>
							<input type="text" name="username" class="inputStyle" placeholder="用户名"/>
						</td>
					</tr>
					<tr>
						<td height="24px"></td>
					</tr>
					<tr>
						<td>
							<input type="password" name="pwd" class="inputStyle" placeholder="密码"/>
						</td>
					</tr>
				    <tr>
						<td height="81px" align="right">
							<input type="checkbox" style="width:20px;height:20px;"/>&nbsp;<span class="rememberPass">记住密码？</span>
						</td>
					</tr> 
					<tr>
						<td>
							<input type="submit" class="buttonStyle" value="登 录" onclick="return checkForm()" />
						</td>
					</tr>
					<tr>
						<td height="50px" align="center">
							<div class="error">
								<span>+</span> 
							</div>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	
	

<script>
			$(function(){
				document.form1.username.focus();
				document.form1.username.focus();
				}
			);
			$(document).keydown(function(e) {	
				var   ev   =   window.event||   e; 	
				if (ev.keyCode==13 ){		
					 checkForm();
					 }
				}
			);
			function checkForm(){
				var frm = document.form1;
				if(frm.username.value==""){
					alert("请输入用户名！");
					document.form1.username.focus();
					return false;
				}else if(frm.pwd.value==""){
				    alert("请输入登录密码！");
				    frm.pwd.focus();
				    return false;
				}
				return true;
			}
			
							
			/**
			 * session失效时跳转到login.jsp
			 */
			function beforeLoad(){
				var url = top.location.toString();
				var pageStr = url.substring(url.lastIndexOf("/")+1,url.length);
				var upperPageStr = pageStr.toUpperCase();
				//alert("<%=basePath%>pages/login.jsp");
				if(upperPageStr!="LOGIN.JSP"){
					window.top.navigate("<%=basePath%>pages/login.jsp");
				}
			}
			
			function reloadImg(){
				var img = document.getElementById("randomImg");
				img.src="fetchRandomCode?now="+new Date();
			}
			
			beforeLoad();
		</script>
	</body>
</html>