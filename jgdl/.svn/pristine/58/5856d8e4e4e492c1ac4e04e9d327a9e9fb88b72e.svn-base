<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "w3.org/TR/html4/strict.dtd">
<html>
	<head>
		<base href="<%=basePath%>" target="_self">
	<%response.setHeader("Pragma","No-cache");          
        response.setHeader("Cache-Control","no-cache");   
        response.setHeader("Cache-Control", "no-store");   
        response.setDateHeader("Expires",0);
        response.flushBuffer();  
       
 %>
		<title>已布署人员</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<meta http-equiv="Content-Type" content="application/msword">
		
		<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
		<meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0"/>
		<meta name="apple-mobile-web-app-capable" content="yes"/>
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		
		<link rel="apple-touch-icon-precomposed" sizes="114x114" href="images/splash/splash-icon.png">
		<link rel="apple-touch-startup-image" href="images/splash/splash-screen.png" 			media="screen and (max-device-width: 320px)" />  
		<link rel="apple-touch-startup-image" href="images/splash/splash-screen_402x.png" 		media="(max-device-width: 480px) and (-webkit-min-device-pixel-ratio: 2)" /> 
		<link rel="apple-touch-startup-image" sizes="640x1096" href="images/splash/splash-screen_403x.png" />
		<link rel="apple-touch-startup-image" sizes="1024x748" href="images/splash/splash-screen-ipad-landscape" media="screen and (min-device-width : 481px) and (max-device-width : 1024px) and (orientation : landscape)" />
		<link rel="apple-touch-startup-image" sizes="768x1004" href="images/splash/splash-screen-ipad-portrait.png" media="screen and (min-device-width : 481px) and (max-device-width : 1024px) and (orientation : portrait)" />
		<link rel="apple-touch-startup-image" sizes="1536x2008" href="images/splash/splash-screen-ipad-portrait-retina.png"   media="(device-width: 768px)	and (orientation: portrait)	and (-webkit-device-pixel-ratio: 2)"/>
		<link rel="apple-touch-startup-image" sizes="1496x2048" href="images/splash/splash-screen-ipad-landscape-retina.png"   media="(device-width: 768px)	and (orientation: landscape)	and (-webkit-device-pixel-ratio: 2)"/>
		<link href="pages/styles/style.css"     		rel="stylesheet" type="text/css">
		<link href="pages/styles/framework.css" 		rel="stylesheet" type="text/css">
		<link href="pages/styles/owl.carousel.css" 	 rel="stylesheet" type="text/css">
		<link href="pages/styles/owl.theme.css" 		rel="stylesheet" type="text/css">
		<link href="pages/styles/swipebox.css"		 rel="stylesheet" type="text/css">
		<script type="text/javascript" src="resource/js/jquery-1.4.4.min.js"></script>
		<script type="text/javascript" src="pages/scripts/snap.js"></script>
		<script type="text/javascript" src="pages/scripts/contact.js"></script>
		<script type="text/javascript" src="pages/scripts/custom.js"></script>
		<script type="text/javascript" src="pages/scripts/framework.js"></script>
		<script type="text/javascript" src="pages/scripts/framework.launcher.js"></script>
			<style type="text/css">
		body,div,input,a,table,li,ul{margin:0px;padding:0px;}
		#controlSearchBox{padding:10px 0px;text-align:right;}
		#controlDiv input{width:230px;}
		#controlDiv select{width:240px;border-color:#CCCCCC;border-radius:3px;}
		#controlDiv textarea{width:230px;}
		.boxBottom{padding:10px 0px;text-align:right;}
		#addControlButton,#closeControlBox{width:90px !important;}
		#bk_btnTime,#closeBkTimeBox{width:90px !important;}

		.leftTD{width:100px;padding:5px 5px 3px 0px;height:auto;text-align:right;}
		.leftTD input{width:13px;}
		.rightTD{width:200px;height:auto;padding:5px 0px 3px 5px;text-align:left;}
		.rightTD input{width:180px;height:auto;}
		.file-box{position:relative;width:340px} 
	</style>
	
	</head>
	<body>
        <div id="preloader">
	<div id="status">
    	<p class="center-text">
			Loading the content...
            <em>Loading depends on your connection speed!</em>
        </p>
    </div>
</div>

<div class="top-deco"></div>

<div class="content">
	<div class="header">
    	<a href="javascript:void(0);" class="homepage-logo">
        	<img src="pages/images/misc/logo.png" alt="img">
        </a>
        <a href="javascript:void(0);" class="go-home">首页</a>
        <a href="javascript:void(0);" class="go-menu">菜单</a>
        <a href="javascript:void(0);" class="go-back">收起</a>
    </div>
    <div class="decoration"></div>
    
    <div class="navigation">
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
                    	<a id="${subBean.name}">${subBean.name}<em></em></a>
                	</div>
                </c:forEach>
            </div>
		</c:forEach>
            
        </div>
    </div>
</div>

<div class="content">
    </div>  
    <div class="one-half-responsive last-column">
        <div class="container no-bottom">
            <!-- <h4>已布署人员名单</h4> -->
        </div> 
    </div>
    <div class="container no-bottom">
       <table cellspacing='0' class="table" id="showTable">
            <tr>
                <th class="table-title">数据源</th>
                <th class="table-title">布控标题</th>
                <th class="table-title">布控类型</th>
                <th class="table-title">布控号码</th>
                <th class="table-title">接受电话</th>
                <th class="table-title">布控人</th>
                <th class="table-title">创建时间</th>
                <th class="table-title">布控状态</th>
                <th class="table-title">失效时间</th>
                <th class="table-title">状态</th>
            </tr> 
           <c:forEach var="row" items="${json.rows}">
            <tr class="even">
		 		<td class="table-sub-title">${row.bkSource}</td>
		 		<td class="table-sub-title">${row.bt}</td>
		 		<td class="table-sub-title">${row.bklx}</td>
		 		<td class="table-sub-title">${row.bkhm}</td>
		 		<td class="table-sub-title">${row.dxjsdh}</td>
		 		<td class="table-sub-title">${row.bkr}</td>
		 		<td class="table-sub-title">${row.cjsj}</td>
		 		<td class="table-sub-title">${row.status}</td>
		 		<td class="table-sub-title">${row.stopTime}</td>
		 		<td class="table-sub-title">${row.flag}</td>
		 	</tr>
		 	</c:forEach> 
        </table>
        ${rows};
    </div>
    <script type="text/javascript">
    /* $(window).load(function() {
		loadYiBuShu(1);
	}); */
    /*初始化页面表格显示布控人员详细信息  */
    function loadYiBuShu(pageNow){
		 $.ajax({
		 	url:'listControlInPage.do',
		 	type:'POST',
		 	dataType:"json",
		 	data:{
		 		rows:10,
		 		page:1,
		 	},
		 	success:function(data){
		 		var showTable=$("#showTable");/*已部署人员表格  */
		 		/* alert(data.rows[0].bkSource) */
		 		/* alert(data.rows.length) */
		 		for(var i=0;i<data.rows.length;i++){
		 		showTable.append("<tr class='even'>"+
		 								"<td class='table-sub-title'>"+data.rows[i].bkSource+"</td>"+
		 								"<td class='table-sub-title'>"+data.rows[i].bt+"</td>"+
		 								"<td class='table-sub-title'>"+data.rows[i].bklx+"</td>"+
		 								"<td class='table-sub-title'>"+data.rows[i].bkhm+"</td>"+
		 								"<td class='table-sub-title'>"+data.rows[i].dxjsdh+"</td>"+
		 								"<td class='table-sub-title'>"+data.rows[i].bkr+"</td>"+
		 								"<td class='table-sub-title'>"+data.rows[i].cjsj+"</td>"+
		 								"<td class='table-sub-title'>"+data.rows[i].status+"</td>"+
		 								"<td class='table-sub-title'>"+data.rows[i].stopTime+"</td>"+
		 								"<td class='table-sub-title'>"+data.rows[i].flag+"</td>"+
		 							"</tr>")
		 		}
		 	}
		 })};
		 
		 
		/*右侧导航，角色管理  */
		$("#角色管理").click(
		function(){
			jueSeGuanLi()
		})
		
		function jueSeGuanLi(){
			$.ajax({
		 	url:'listRolesInPage.do',
		 	type:'POST',
		 	dataType:"json",
		 	data:{
		 		rows:10,
		 		page:1,
		 	},
		 	success:function(data){
		 		var showTable=$("#showTable");
		 		/*清空表格  */
		 		showTable.html("");
		 		/*添加角色管理th  */
		 		showTable.append("<tr class='even'>"+
                	"<th class='table-title'>角色名称</th>"+
                	"<th class='table-title'>描述</th>"+
                	"<th class='table-title'>角色类型</th>"+
                	"<th class='table-title'>默认角色</th>"+
            	"</tr>");
		 	 for(var i=0;i<data.rows.length;i++){
		 	 	showTable.append("<tr>"+
		 			"<td class='table-sub-title'>"+data.rows[i].roleName+"</td>"+
		 			"<td class='table-sub-title'>"+data.rows[i].descb+"</td>"+
		 			"<td class='table-sub-title'>"+data.rows[i].isAdmin+"</td>"+
		 			"<td class='table-sub-title'>"+data.rows[i].isDefault+"</td>"+
            	"</tr>");
		 		} 
		 	}
		 })
		}
		
		/*右侧导航，用户管理  */
		$("#用户管理").click(
		function(){
			yongHuGuanLi()
		})
		function yongHuGuanLi(){
			$.ajax({
		 	url:'listUsersInPage.do',
		 	type:'POST',
		 	dataType:"json",
		 	data:{
		 		rows:10,
		 		page:1,
		 	},
		 	success:function(data){
		 		var showTable=$("#showTable");
		 		/*清空表格  */
		 		showTable.html("");
		 		/*添加角色管理th  */
		 		 showTable.append("<tr class='even'>"+
                	"<th class='table-title'>用户名称</th>"+
                	"<th class='table-title'>登录账户</th>"+
                	"<th class='table-title'>角色名称</th>"+
                	"<th class='table-title'>默认用户</th>"+
                	"<th class='table-title'>身份证号码</th>"+
                	"<th class='table-title'>创建日期</th>"+
                	"<th class='table-title'>有效日期</th>"+
                	"<th class='table-title'>绑定IP</th>"+
                	"<th class='table-title'>状态</th>"+
                	"<th class='table-title'>描述</th>"+
            	"</tr>");
		 	 for(var i=0;i<data.rows.length;i++){
		 	 	showTable.append("<tr>"+
		 			"<td class='table-sub-title'>"+data.rows[i].userTitle+"</td>"+
		 			"<td class='table-sub-title'>"+data.rows[i].username+"</td>"+
		 			"<td class='table-sub-title'>"+data.rows[i].roleEntity.roleName+"</td>"+
		 			"<td class='table-sub-title'>"+data.rows[i].isDefault+"</td>"+
		 			"<td class='table-sub-title'>"+data.rows[i].sfzhm+"</td>"+
		 			"<td class='table-sub-title'>"+data.rows[i].crreateTime+"</td>"+
		 			"<td class='table-sub-title'>"+data.rows[i].validTime+"</td>"+
		 			"<td class='table-sub-title'>"+data.rows[i].ip+"</td>"+
		 			"<td class='table-sub-title'>"+data.rows[i].isEnabled+"</td>"+
		 			"<td class='table-sub-title'>"+data.rows[i].descb+"</td>"+
            	"</tr>");
		 		} 
		 	}
		 })
		}
		
	</script>
	</body>
</html>
