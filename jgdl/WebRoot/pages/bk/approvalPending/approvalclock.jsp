<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'approvalclock.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
  </head>
  
  <body>
   	<div>
		<div style="background-color:#ccc;height:40px">
			<div style="width:5%;height:40px;float:left;padding-top: 5px;padding-left:10px"><img src="resource/images/misc/fh.png" style="height:20px" onclick="goBack()"/></div>
			<div style="width:80%;height:40px;float:left;padding-top: 5px;">提问</div>
			<div style="height:40px;padding-top: 5px;">发布</div>
		</div>
		<div style="background-color:write;height:40%;border:0px solid red;">
			<p style="margin-left:17px">取消原因</p>
			<!-- <input type="text" value="简单的描述你取消的原因" style="width:100%;height:95%;border:0px solid red;" autofocus/> -->
			<!-- <textarea rows="1" cols="20" style="width:100%;height:95%;text-algin:left">
				简单的描述你取消的原因
			</textarea> -->
			<textarea class="autogrow" style="height: 200px;resize: none;">简单的描述你取消的原因</textarea>
		</div>
	</div><!--  rows="30" cols="30" style="width:100%;height: 400px; resize: none; max-width:100%; min-width:400px;" -->
  </body>
</html>
