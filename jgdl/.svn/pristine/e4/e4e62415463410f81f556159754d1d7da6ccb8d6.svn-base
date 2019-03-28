<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>统计在线人数</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="refresh" content="10">
	<script type="text/javascript">
		
	</script>
  </head>
  
  <body style="margin-top: 1px;height: 20px;font-size: 12px;" bgcolor="#EFEFEF">
   	当前在线用户数：<span id="onLineUserCount">
   <c:choose>
   	 <c:when test="${username == 'admin'}">
   	   <a href="javascript:void(0);" onclick="parent.showOnlineUserList();" title="点击查看在线用户列表">${fn:length(onLineUserList)}</a>
     </c:when>
     <c:when test="${fn:containsIgnoreCase(userPrivilege, 'admin')}">
  	   <a href="javascript:void(0);" onclick="parent.showOnlineUserList();" title="点击查看在线用户列表">${fn:length(onLineUserList)}</a>
     </c:when>
     <c:otherwise>${fn:length(onLineUserList)}</c:otherwise>
   </c:choose>
   </span><span style="margin-left:30px;"><a href="javaScript:void(0);" onclick="parent.handerGetNotice();">显示公告</a></span>
   <table style="display: node;" id="onLineUserList">
  	 <c:forEach var="userDto" items="${onLineUserList}">
  	 	<tr bgcolor="#ffffff">
  	 		<td>${userDto.userInfo}</td>
  	 		<td>${userDto.loginIp}</td>
  	 		<td>${userDto.loginDatetime}</td>
  	 	</tr>
  	 </c:forEach>
  </table>
  </body>
</html>
