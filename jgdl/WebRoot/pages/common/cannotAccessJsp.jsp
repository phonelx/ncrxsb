<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <base href="<%=basePath%>"/>
    <title>chenlong 's JSP page</title>
<style type="text/css">
body{ background:url(resource/images/error/bg.gif); text-align:center;}
#bg{}
</style>
<script type="text/javascript">
	$('#errorimgbackgo').bind('click', function() {   
		 $('#errorimgbackgo').hide();   		
  		 $('#errorimgbackgo').unbind();
  		  backGo(-1); 
});
	  
</script>
</head>

<body >
<div style="background:url(resource/images/error/bg.gif); text-align:center;" id="error1"> 
<div style=" text-align:center; padding-top:50px; vertical-align:middle; background-image:url(resource/images/error/1.gif); width:534px; height:340px;">
<div style=" padding-top:15px;">
<img src="resource/images/error/pic2.jpg"/>
</div>
<div style=" margin-top:20px; color:#51688e; font-size:18px; font-weight:bolder;"> 您没有此页面的访问权限，请点击返回...</div>
<div style=" margin-top:22px; cursor:pointer;">
<img id="errorimgbackgo" src="resource/images/error/btn.gif" width="72"   height="33"/>
 </div></div></div>
  </body>
</html>
