<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/pages/head.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>"/>
    <script type="text/javascript" src="resource/js/manage/sms.js"></script>
    
    <style type="text/css">
    	body,div,input,a,table,li,ul{margin:0px;padding:0px;}
		#smsSearchBox{padding:10px 0px;text-align:right;}
		#sms input{width:230px;}
		#sms select{width:240px;border-color:#CCCCCC;border-radius:3px;}
		#sms textarea{width:230px;}
		.boxBottom{padding:10px 0px;text-align:right;}
		#smsBtn,#closeSmsBox{width:90px !important;}

 		#sms_add td{height: 35px;}
 		.tdright{padding-left: 2px}
 		.tdright{text-align: left;}
 	</style>
</head>
<body>
  	<div id="smsSearchBox">
		<div></div>
		<div>
			<input id="input_user_search" type="text" style="width:200px;color:#666;margin:0px;" value="关键字搜索"
				onfocus="if(this.value == '关键字搜索') this.value = ''" onblur="if(this.value =='') this.value = '关键字搜索'">
			<a class="btn " onclick="showSmsList(1);" href="javascript:void(0);">
				<i class="icon-search"></i> 检索
			</a>
			
			<a title="添加用户" class="btn" onclick="showAddSmsBox();" href="javascript:void(0);" style="margin-right:10px;">
				<i class="icon-plus-sign"></i> 添加 
			</a>
		</div>
	</div>

	<div id="sms"></div>
  		
	<div id="sms_add" class="modal hide fade in" style="display:none;width:600px;height:320px;top:50%;left:50%;">
		<div class="modal-header">
			<a class="close" data-dismiss="modal">×</a>
			<h3 id="smsTitleName">添加短信</h3>
		</div>

		<div class="modal-body">
	   		<form action="addsms" method="post" id="SmsForm">
		   		<div style="margin: 10px 0" id="dbsDiv">
		   			<table cellpadding="0" cellspacing="1" bgcolor="#99BBEB" width="95%" >
		   				<tr bgcolor="#ffffff">
		   				    <td align="right" width="30%">短信标题：</td>
		   					<td class="tdright">
		   						<input type="text" id="smstitle" name="sms.title" class="easyui-validatebox" required="true">
	   						</td>
		   				</tr>
		   				<tr bgcolor="#ffffff">
		   				    <td align="right" width="30%">布控规则选择：</td>
		   					<td class="tdright">
		   						<input type="hidden" id="bkid" name="sms.bkid"/>
		   						<input id="bk" />
		   					</td>
		   				</tr>
		   				<tr bgcolor="#ffffff">
		   				    <td align="right" width="30%">短信结尾：</td>
		   					<td class="tdright">
		   						<input type="text"  id="smsend" name="sms.end" class="easyui-validatebox" required="true">
		   					</td>
		   				</tr>
		   				<tr bgcolor="#ffffff">
		   					 <td align="right" width="30%">是否发送：</td>
		   					 <td class="tdright">
		    					<input type="radio" name="sms.isSend"  value="0"/>发送
		    					<input type="radio" name="sms.isSend" value="1"/>不发送
		   				 	 </td>
		   				</tr>
		   			</table>
		   			<input type="hidden" value="" name="sms.id" id="smsid"/>
		   		</div>
		   		
		   		<div class="boxBottom">
					<input type="reset" id="clearForm" name="reset" style="display:none;"/>
					<input type="button" value="确定" class="btn" id="smsBtn"/> &nbsp;
					<input type="button" value="取消" class="btn" id="closeSmsBox" data-dismiss="modal"/> &nbsp;
				</div>
	   		</form>
   		</div>
   	</div>
   	<script type="text/javascript">
   		showSmsList(1);
   	</script>
</body>
</html>
