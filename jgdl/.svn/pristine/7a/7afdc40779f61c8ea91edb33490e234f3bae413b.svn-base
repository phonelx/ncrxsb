<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/pages/head.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'controlIndex.jsp' starting page</title>

	<script type="text/javascript" src="resource/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="resource/js/manage/control.js"></script>
	<script type="text/javascript" src="resource/js/ajaxfileupload.js"></script>
	
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
	<div id="controlSearchBox">
		<div></div>
		<div>
			<input id="input_user_search" type="text" style="width:200px;color:#666;margin:0px;" value="关键字搜索"
				onfocus="if(this.value == '关键字搜索') this.value = ''" onblur="if(this.value =='') this.value = '关键字搜索'">
			<a class="btn" onclick="loadBk(1);" href="javascript:void(0);">
				<i class="icon-search"></i> 检索
			</a>
			
			<a title="布控时间" class="btn" onclick="showEditBDTime();" href="javascript:void(0);">
				<i class="icon-plus-sign"></i> 布控时间
			</a>
			
			<a title="添加布控" class="btn" onclick="showAddControl();" href="javascript:void(0);" style="margin-right:10px;">
				<i class="icon-plus-sign"></i> 添加布控
			</a>
		</div>
	</div>
	
  	<div id="controlGrid"></div>

    <div id="controlDiv" class="modal hide fade in" style="display:none;width:600px;height:520px;top:50%;left:50%;">
    	<div class="modal-header">
			<a class="close" data-dismiss="modal">×</a>
			<h3 id="controlTitleName">添加布控</h3>
		</div>

		<div class="modal-body">
	    	<form action="" method="post" id="controlForm" enctype="multipart/form-data">
	    		<table bgcolor="#99BBEB" cellpadding="0" cellspacing="1" width="95%" style="margin-bottom: 10px;">
	    			<tr bgcolor="#ffffff">
	    				<td class="leftTD">布&nbsp;&nbsp;控&nbsp;&nbsp;标&nbsp;&nbsp;题&nbsp;&nbsp;:</td>
	    				<td class="rightTD">
	    					<input type="text" name="bt" id="bt" class="easyui-validatebox" 
	    						required="true" validType="dbsNameValid[100]"/>
	    				</td>
	    			</tr>
	    			<tr bgcolor="#ffffff">
	    				<td class="leftTD" style="text-align: right;">
	    					<input type="radio" name="bklx" value="1" style="width:13px;"/>身份证号码:<br/>
	    					<input type="radio" name="bklx" value="2" style="width:13px;padding-right: 8px;"/>车牌号码 :
						</td>
						<td class="rightTD">
							<input type="text" name="bkhm" id="bkhm" style="width:40%" />
	    					<input type="button" class="btn" value="批量上传" style="width:80px;height:28px;margin-top:-10px;"/> 
							<input type="file" id="file" name="file" class="file" onchange="getUpload()" 
								style="opacity:0;height:28px;width:60px;position:absolute;left:62%"/>
							<!-- onchange="getUpload()" -->
						    <input type="button" value="下载模版" style="width:60px;margin-top:-10px;" onclick="javascript:window.location='downloadFile.do';"/>
	    				</td>
	    			</tr>
	    			<tr bgcolor="#ffffff">
						<td class="leftTD">是&nbsp;&nbsp;否&nbsp;&nbsp;启&nbsp;&nbsp;用&nbsp;&nbsp;:</td>
						<td class="rightTD">
							<select name="flag" id="flag">
								<option value="1">启用</option>
								<option value="0">禁用</option>
							</select>
						</td>
					</tr>
					<tr bgcolor="#ffffff">
						<td class="leftTD">关&nbsp;联&nbsp;数&nbsp;据&nbsp;源&nbsp;:</td>
						<td class="rightTD">
							<select id="bkSource" name="bkSource"></select>
						</td>
					</tr>
					<tr bgcolor="#ffffff">
						<td class="leftTD">是否消息提示&nbsp;:</td>
						<td class="rightTD">
							<select id="mess" name="mess">
								<option value="1">是</option>
								<option value="2">否</option>
							</select>
						</td>
					</tr>
	    			<tr bgcolor="#ffffff">
	    				<td class="leftTD">短信接收电话&nbsp;&nbsp;:</td>
	    				<td class="rightTD"><input type="text" name="dxjsdh" id="dxjsdh" class="easyui-validatebox" /></td>
	    			</tr>
	    			<tr bgcolor="#ffffff">
	    				<td class="leftTD">布&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;控&nbsp;&nbsp;&nbsp;&nbsp;人&nbsp;&nbsp;:</td>
	    				<td class="rightTD">
	    					<span style="display: none;" id="bkr_i">${userTitle }</span>
	    					<input type="text" name="bkr" id="bkr" readonly="readonly" />
	    				</td>
	    			</tr>
	    			<tr bgcolor="#ffffff">
	    				<td class="leftTD">失&nbsp;&nbsp;效&nbsp;&nbsp;日&nbsp;&nbsp;期&nbsp;&nbsp;:</td>
	    				<td class="rightTD">
	    					<input type="text" name="stopTime" id="stopTime" class="easyui-validatebox" 
	    						onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" />
	    				</td>
	    			</tr>
	    		</table>
				
				<div class="boxBottom">
					<input type="reset" id="clearForm" name="reset" style="display:none;"/>
					<input type="button" value="确定" class="btn" id="addControlButton"/> &nbsp;
					<input type="button" value="取消" class="btn" id="closeControlBox" data-dismiss="modal"/> &nbsp;
				</div>
	    	</form>
    	</div>
    </div>
    
    <div id="bdTime" class="modal hide fade in" style="display:none;width:600px;height:200px;top:50%;left:50%;">
    	<div class="modal-header">
			<a class="close" data-dismiss="modal">×</a>
			<h3 id="timeTitleName">编辑布控时间</h3>
		</div>

		<div class="modal-body">
	    	<form method="post" id="bk_time">
				<div style="margin: 10px 0" id="dbsDiv">
		   			<table cellpadding="0" cellspacing="1" bgcolor="#99BBEB" width="95%">
		   				<tr bgcolor="#ffffff">
		   					<td align="right">布控比对系统启动时间间隔(分钟)：</td>
		   					<td class="tdright" >
		   						<input id="bdTime_value" type="text" value="15" class="runtimes"/>
		   					</td>
		   				</tr>  				
		   			</table>

		   			<div class="boxBottom">
						<input type="reset" id="clearTimeForm" name="reset" style="display:none;"/>
						<input type="button" value="确定" class="btn" id="bk_btnTime"/> &nbsp;
						<input type="button" value="取消" class="btn" id="closeBkTimeBox" data-dismiss="modal"/> &nbsp;
					</div>
	   			</div>
	   		</form>
	   	</div>
    </div>
    
    <script type="text/javascript">
    	loadBk(1);
    </script>
</body>
</html>
