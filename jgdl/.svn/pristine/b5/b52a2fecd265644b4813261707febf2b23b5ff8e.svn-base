<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
response.addHeader("__notice","true");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="<%=basePath%>"/>
		<link href="resource/css/prompt.css" type="text/css" rel="stylesheet"/>
	</head>
	<body>
		<div id="prompt"">
			<li class="prompt_title">
				系统提示	
			</li>
			<li class="prompt_info">
				<div id="error" align="center" style="width:300px;margin:30px 0px 0px 50px;">
					<table width="100%">
						<tr> 
							<td align="center" height="60"> 
								<div class="error_info">
									<font size="5"><b>${info}</b></font>
								</div>
							</td>
						</tr>
						 
						<tr>
							<td align="center" valign="bottom">
								<font size="2.5">
								[<a href="#" onclick="javascript:history.go(-1);;return false;">返  回</a>]
								</font>
							</td>
						</tr>
						
					</table>
				</div>
			</li>
		</div>
	</body>
</html>