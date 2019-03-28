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
<link href="resource/css/overdue.css" rel="stylesheet" type="text/css" />
<!--  -->
<link rel="shortcut icon" href="../favicon.ico">
	<link rel="stylesheet" type="text/css"
		href="resource/bootstrap/css/bootstrap.css" />

	<style type="text/css">
@media only screen and (max-width: 1500px) {
	.cjsj {
		font-size: 17px;
	}
	#showMsg {
		margin: 0px 0px 0px 45px;
	}
	.modal-dialog {
		width: 30%;
		margin: 250px auto;
	}
	#showMsg p {
		font-size: 14px;
	}
	#lastP div {
		font-size: 14px;
		margin-top: 5px;
	}
	.xm {
		font-size: 15px;
	}
}

@media only screen and (max-width: 900px) {
	.modal-dialog {
		width: 45%;
		margin: 200px auto;
	}
}

@media only screen and (max-width: 768px) {
	.cjsj {
		font-size: 16px;
	}
	.xm {
		font-size: 14.5px;
	}
	#showMsg  p {
		font-size: 14px;
	}
	.modal-dialog h3 {
		font-size: 16px;
	}
	#lastP div {
		font-size: 14px;
	}
}

@media only screen and (max-width: 600px) {
	#showMsg  p {
		font-size: 13px;
	}
	.modal-dialog h3 {
		font-size: 16px;
	}
	#lastP div {
		font-size: 13px;
	}
	.modal-dialog {
		width: 50%;
	}
	.xm {
		font-size: 14px;
	}
}

@media only screen and (max-width: 480px) {
.modal-dialog h3 {
		font-size: 14px;
	}
	#showMsg  p {
		font-size: 13px;
	}
	#lastP div {
		font-size: 13px;
	}
	#lastP {
		margin-top: 20px;
	}
	.modal-body h5 {
		font-size: 10px;
	}
	.cjsj {
		font-size: 16px;
	}
}

@media only screen and (max-width: 400px) {
	.modal-body h5 {
		font-size: 8px;
	}
	.cjsj {
		font-size: 14px;
	}
	#showMsg  p {
		font-size: 13px;
	}
	#lastP div {
		font-size: 13px;
	}
	.xm {
		font-size: 13px;
	}
}

@media only screen and (max-width: 330px) {
	#showMsg  p {
		font-size: 8px;
	}
		.cjsj {
		font-size: 12px;
	}
	#showMsg  p {
		font-size: 10px;
	}
	#lastP div {
		font-size: 10px;
	}
}
</style>

	<script type="text/javascript" src="resource/scripts/jquery.js"></script>
	<script type="text/javascript" src="resource/scripts/jqueryui.js"></script>
	<script type="text/javascript"
		src="resource/scripts/owl.carousel.min.js"></script>
	<script type="text/javascript"
		src="resource/scripts/jquery.swipebox.js"></script>
	<script type="text/javascript" src="resource/scripts/colorbox.js"></script>
	<script type="text/javascript" src="resource/scripts/snap.js"></script>
	<script type="text/javascript" src="resource/scripts/contact.js"></script>
	<script type="text/javascript" src="resource/scripts/custom.js"></script>
	<script type="text/javascript" src="resource/scripts/framework.js"></script>
	<script type="text/javascript"
		src="resource/scripts/framework.launcher.js"></script>
	<script type="text/javascript" src="resource/bootstrap/js/modal.js"></script>
	<script type="text/javascript" src="resource/js/jquery-1.4.4.min.js" />
	<script type="text/javascript" src="resource/js/uiGrid.js"></script>
	<script type="text/javascript">
		var json = ${personMsg};
	</script>
	<script type="text/javascript" src="resource/js/bk/overdue.js"></script>
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
			<a href="#" class="homepage-logo"> <img src="resource/images/misc/logo.jpg" alt="img" /></a>
		    <a href="index.do" class="go-home">主页</a> 
		    <a href="#" class="go-menu">菜单</a> 
		    <a href="#" class="go-back">CLOSE</a>
		</div>
		<div class="decoration"></div>
		<div class="navigation">
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
								<a
									href="javascript:gotoModule('${subBean.entryUrl}','${subBean.name}','${mainBean.name}');">${subBean.name}<em></em></a>
							</div>
						</c:forEach>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
	<div id="overdueGrld">
		<div id="showMsg">
			<p class="headeP">
				<img src="resource/images/general-nature/P.jpg" alt="img"> <span
					class="titleSpan"> <span class="marginSpan"> <strong><span
								class="xm"> </span></strong><br />
					</span> <span class="marginSpan"> 人员类型：<span class="rylx"></span><br />
					</span> <span class="marginSpan"> 布控状态：<span class="bkzt"></span>
					</span>
				</span>
			</p>
		</div>
	</div>
	<div id="lastDiv">
		<div id="lastP">
			<div>
				<strong> <span class="cjsj"></span><br />
				</strong>
			</div>
			<div>
				是否是最新数据：<span class="isNew"></span><br />
			</div>
			<div>
				户籍地址：<span class="HJDZ"></span><br />
			</div>
			<div>
				身份证号：<span class="GMSFHM"></span><br />
			</div>
			<div>
				备注信息：<span class="BZ"></span><br />
			</div>

		</div>
		<a href="javascript:history.go(-1)" class="button button-dark"style="margin-top: 20px; margin-right: 10px;">返&nbsp;回</a>
		<a href="javascript:void(0)" onclick="javascript:if (confirm('是否要申请续期？')) { return xuQi()}" class="button button-dark"style="margin-top: 20px; margin-left: 10px;">续&nbsp;期</a>
	</div>
	<!--成功消息提示  -->
	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true"
		data-backdrop="static">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<span class="modal-title" id="myModalLabel">操作提示</span>
				</div>
				<div class="modal-body" style="text-align: center;">
					<h3 style="margin: 0px;padding: 0px;color:red;font-size: 16px;">申请续期已成功</h3>
				</div>
				<div class="modal-footer">
					<a href="/monitor/pages/bk/overdue/overdueIndex.jsp" class="back">返回列表</a>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	<!--失败消息提示  -->
	<div class="modal fade" id="error" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true"
		data-backdrop="static">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<span class="modal-title" id="myModalLabel">操作提示</span>
				</div>
				<div class="modal-body" style="text-align: center;">
					<h5 style="color: red;">
						申请续期失败 页面&nbsp;<span id="num">5</span>&nbsp;秒后自动跳转
					</h5>
				</div>
				<div class="modal-footer">
					<a href="/monitor/pages/bk/overdue/overdueIndex.jsp">立即跳转</a>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	<input class="btn btn-primary btn-lg" data-toggle="modal"
		style="display: none;" data-target="#myModal" id="ok" type="button" />
	<input lass="btn btn-primary btn-lg" data-toggle="modal" type="button"
		style="display: none;" data-target="#error" id="err" />
</body>
</html>