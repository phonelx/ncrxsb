<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/pages/head.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>"/>
    <script type="text/javascript" src="resource/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="resource/js/manage/manage.js"></script>
    <style type="text/css">
    	body,div,input,a,table,li,ul{margin:0px;padding:0px;}
		#yjSearchBox{padding:10px 0px;text-align:right;}
		#yjbk input{width:230px;}
		#yjbk select{width:240px;border-color:#CCCCCC;border-radius:3px;}
		#yjbk textarea{width:230px;}
		.boxBottom{padding:10px 0px;text-align:right;}
		#yjgzBtn,#closeYjgzBox{width:90px !important;}
		#yjruntime,#closeYjgzTimeBox{width:90px !important;}
		.combo input{width:210px !important;}

 		#manage_add td{height:35px;}
 		.bthnsize{height:20px;width:35px;margin-left:10px;}
 		#citys div{margin-left:5px;border: 1px solid #ccc;margin-top: 1px;margin-bottom: 1px;}
 		.tdright{padding-left: 2px}
		#runtime td{height: 35px}
		.tdright{text-align: left;}
	</style>
</head>

<body>
	<div id="yjSearchBox">
		<div></div>
		<div>
			<input id="input_user_search" type="text" style="width:200px;color:#666;margin:0px;" value="关键字搜索"
				onfocus="if(this.value == '关键字搜索') this.value = ''" onblur="if(this.value =='') this.value = '关键字搜索'">
			<a class="btn " onclick="showYjList(1);" href="javascript:void(0);">
				<i class="icon-search"></i> 检索
			</a>

			<a title="添加用户" class="btn" onclick="showTimeYjgzBox();" href="javascript:void(0);" style="margin-right:10px;">
				<i class="icon-plus-sign"></i> 预警时间
			</a>
			
			<a title="添加用户" class="btn" onclick="showAddYjGz();" href="javascript:void(0);" style="margin-right:10px;">
				<i class="icon-plus-sign"></i> 添加
			</a>
		</div>
	</div>

	<div id="yjbk"></div>

 	<div id="manage_add" class="modal hide fade in" style="display:none;width:600px;height:600px;top:40%;left:50%;overflow:hidden;">
 		<div class="modal-header">
			<a class="close" data-dismiss="modal">×</a>
			<h3 id="yjgzTitleName">添加预警规则</h3>
		</div>

		<div class="modal-body" style="height:525px;">
	   		<form action="addmanage" method="post" id="manageForm">
	   			<input id="calx" type="hidden"  value="0"/>
		   		<div style="margin: 10px 0" id="dbsDiv">
		   			<table cellpadding="0" cellspacing="1" bgcolor="#99BBEB" width="95%" >
		   				<tr bgcolor="#ffffff">
		   				    <td align="right" width="30%">预警标题：</td>
		   				    <input type="hidden" id="gzid" name="id"/>
		   					<td class="tdright">
			   					<input type="text" id="dbtitle" name="yjgz.yjbt" class="easyui-validatebox"/>	
			   					<input type="hidden" id="YJNameSameJudge" value="true"/>
			 					<input type="hidden" id="YJNameOldValue"/>
			 				</td>
		   				</tr>
		   				<tr bgcolor="#ffffff">
		   					<td align="right">预警人姓名：</td>
		   					<td class="tdright">
		   						<input type="text" id="name" name="yjgz.name" class="easyui-validatebox">
		   					</td>
		   				</tr>
		   				<tr bgcolor="#ffffff">
		   					<td align="right">是否禁用：</td>
		   					<td class="tdright">
		   						<select name="yjgz.flag" id="isflag">
		   							<option value="1" selected="selected">否</option>
		   							<option value="2">是</option>
		   						</select>
		   					</td>
		   				</tr>
		   				<tr bgcolor="#ffffff">
		   					<td align="right">选择关联数据源：</td>
		   					<td class="tdright">
		   						<input id="seleDB" disabled="disabled"/>
		   						<input type="hidden" name="yjgz.tableID" id="dbtbid">
		   						<input type="hidden" name="yjgz.dbConID" id="dbcon">
		   						<input type="hidden" id="tabval"/>
		   					</td>
		   				</tr>
		   				<tr bgcolor="#ffffff">
		   					<td align="right">性别：</td>
		   					<td class="tdright">
		   						<select style="TEXT-ALIGN:CENTER;" name="yjgz.sex" id="sex">
		   							<option value="1">男性</option>
		   							<option value="2">女性</option>
		   							<option value="0">未知的性别</option>
		   							<option value="9">未说明的性别</option>
		   						</select>
		   					</td>
		   				</tr>
		   				<tr bgcolor="#ffffff">
		   					<td align="right">民族：</td>	
		   					<td class="tdright">
		   						<input id="mz" />
		   						<input id="mzcheck" type="hidden" name="yjgz.mz"/>
		   					</td>
		   				</tr>
		   				<tr bgcolor="#ffffff">
		   					<td align="right">所属省市县(区)：</td>	
		   					<td class="tdright">
		   						<input style="height: 30px;width: 210px" id="citys" />
		   					</td>
		   				</tr>
		   				<tr bgcolor="#ffffff">
		   					<td align="right">选择的省市县(区)</td>
		   					<td>
		   						<div style="100%;height: 150px;overflow:auto;" id="showCheckcitys"></div>
		   						<input type="hidden" id="cityname"/>
		   						<input type="hidden" name="yjgz.hjd" id="checkcityname" value=""/>
		   					</td>
		   				</tr>
		   				<tr bgcolor="#ffffff">
		   					<td align="right">地址：</td>
		   					<td class="tdright">
		   						<input type="text" name="yjgz.zz" id="zz" class="easyui-validatebox" 
		   							style="height:25px;line-height:25px;">
		   					</td>
		   				</tr>
		   				<tr bgcolor="#ffffff">
		   					<td align="right">出生日期：</td>
		   					<td class="tdright">
		   						<input type="radio" class="timeshow" name="yjgz.istime" value="0">时间段</input>
		   						<input type="radio" class="timeshow" name="yjgz.istime" value="1">年龄段</input>
		   						<input type="hidden" id="rdbtn"/>
		   					</td>
		   				</tr>
		   				<tr bgcolor="#ffffff">
		   					<td colspan="2" align="center">
			   					<div class="time1">
			   					时间段日期选择：<input style="width: 125px" id="csrq1" name="yjgz.csrq1" class="Wdate" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"   readonly="readonly" />   					
			   					至
			   							 <input style="width: 125px" id="csrq2" name="yjgz.csrq2" class="Wdate" type="text"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  readonly="readonly" />
			   					</div>
			   					<div class="time2" style="display: none;">
			   					年龄段输入：<input style="width: 125px;"  id="csrq3" name="yjgz.csrq1" onkeydown="onlyNum()" onblur="Checkage()" type="text"  />   					
			   					岁,到
			   							 <input style="width: 125px;"  id="csrq4" name="yjgz.csrq2" onkeydown="onlyNum()" onblur="Checkage()" type="text" />岁
			   					</div>
		   					</td>
		   				</tr>
		   				<tr bgcolor="#ffffff">
		   					<td align="right">失效日期：</td>
		   					<td class="tdright">
		   						<input style="width: 140px" id="stoptime" name="yjgz.stoptime" class="Wdate" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" />
		   					</td>
		   				</tr>
		   			</table>
		   		</div>
		   		
		   		<div class="boxBottom">
					<input type="reset" id="clearForm" name="reset" style="display:none;"/>
					<input type="button" value="确定" class="btn" id="yjgzBtn"/> &nbsp;
					<input type="button" value="取消" class="btn" id="closeYjgzBox" data-dismiss="modal"/> &nbsp;
				</div>
	   		</form>
   		</div>
   	</div>

   	<div id="runtime" class="modal hide fade in" style="display:none;width:600px;height:200px;top:50%;left:50%;">
   		<div class="modal-header">
			<a class="close" data-dismiss="modal">×</a>
			<h3 id="yjbkTimeTitleName">编辑预警时间</h3>
		</div>

		<div class="modal-body">
	   		<form method="post" id="time">
				<div style="margin: 10px 0" id="dbsDiv">
		   			<table cellpadding="0" cellspacing="1" bgcolor="#99BBEB" width="95%">
		   				<tr bgcolor="#ffffff">
		   					<td align="right">比对系统启动时间间隔(分钟)：</td>
		   					<td class="tdright">
		   						<input type="text" class="runtimes"  onkeydown="onlyNum()" onblur="Checkage()"/>
		   					</td>
		   				</tr>
		   			</table>
	   			</div>

	   			<div class="boxBottom">
					<input type="button" value="确定" class="btn" id="yjruntime"/> &nbsp;
					<input type="button" value="取消" class="btn" id="closeYjgzTimeBox" data-dismiss="modal"/> &nbsp;
				</div>
	   		</form>
   		</div>
   	</div>
   	
   	<script type="text/javascript">
   		showYjList(1);
   	</script>
</body>
</html>
