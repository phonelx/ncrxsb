<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/pages/head.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>My JSP 'selectPage.jsp' starting page</title>
    <script type="text/javascript" src="resource/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="resource/js/manage/Query.js"></script>
    <style type="text/css">
    	.datagrid {position: absolute;top: 33;left: 0;}
    
    	#conditionDiv{width: 100%;height: auto;background: #efefef;padding: 1px 2px;border-bottom: 1px solid #ccc;position: absolute;}
    	#conditionForm tr{margin-bottom:5px;}
    	#conditionForm td{padding: 0px 5px 0px 2px;border-right: 1px solid #cccccc;}
    	#clearButton{padding:0px 5px;}
    	#selectButton{padding:0px 5px;}
    	.xxA{}
    	.xxAs:link{color:#000000;}
    	.xxAs:hover{color:#38f;font-size: larger;}
    	.xxAs:active{color:#ffffff;}
    	.xxAs:visited{color:blue;}
    	
    	
    	.lastSelectForm1{ width:98%;height:auto;padding: 0px 5px 10px;float:left; }
    	#lastSelectForm1 table{ width:100%;height:auto; }
    	.leftTD{ width: 15%;padding:5px 0px 3px 3px;height: auto;text-align: right;padding-bottom: 10px }
		.rightTD{ width: 85%;text-align:left; padding-bottom: 5px}
		.ttds{text-align: center;}
		#showTabss{margin:0px;}
		#showTabss th{ padding: 2px 5px;background-color: #CCCCCC;border-right: 1px dotted #8F8F8F;border-bottom: 1px dotted #8F8F8F;text-align: center;}
		#showTabss tr{ background: #e2e2e2; }
		.tab-pane{height:320px;border-left:1px solid #CCCCCC;border-right:1px solid #CCCCCC;border-bottom:1px solid #CCCCCC;}
		.tabK{margin-right: 10px;margin-left: 5px;margin-top: 5px;margin-bottom: 5px;h：50px;}
		.ttd{font-size: 12px;border-right: 1px dotted #ccc;border-bottom: 1px dotted #ccc;overflow: hidden;padding: 2px 5px;}
		.fenyeCs{float: left;border-top: 1px solid #cccccc;width: 97%;background: #efefef;position: absolute;bottom: 8px;height: 30px;line-height: 30px;}
		#fenyeTables{line-height: 30px;float: left;margin-left: 10px;}
		#fenyeTables td{background: #efefef;}
		#fenyeTables a{height: 24px;width: 24px;display: inline-block;line-height: 24px;border: 1px solid #ffffff;}
		#fenyeTables a:hover{border: 1px solid #0066CC;}
		.fenyeFirsts{width: 16px;height: 16px;display: inline-block;margin: 5px 0px 0px 5px;background: url('/resource/easyui/themes/default/images/pagination_first.gif') no-repeat;}
		.fenyePrevs{width: 16px;height: 16px;display: inline-block;margin: 5px 0px 0px 5px;background: url('/resource/easyui/themes/default/images/pagination_prev.gif') no-repeat;}
		.fenyeNexts{width: 16px;height: 16px;display: inline-block;margin: 5px 0px 0px 5px;background: url('/resource/easyui/themes/default/images/pagination_next.gif') no-repeat;}
		.fenyeLasts{width: 16px;height: 16px;display: inline-block;margin: 5px 0px 0px 5px;background: url('/resource/easyui/themes/default/images/pagination_last.gif') no-repeat;}
		.fenyeShuxians{height: 20px;border-left: 1px solid #cccccc;display: inline-block;line-height: 30px;margin: 0px 5px;}
    </style>
</head>
<body>
	<div id="detailInfors" class="modal hide fade in" style="display:none;width:1000px;height:620px;top:40%;left:35%;">
		<div class="modal-header">
			<a class="close" data-dismiss="modal">×</a>
			<h3 id="titleName">详细信息</h3>
		</div>

		<div class="modal-body">
	    	<input type=hidden id="getYjid" name="getYjhm"/>
	    	<form action="" id="lastSelectForm1" class="lastSelectForm1">
	    		<table cellpadding="0" cellspacing="1" width="95%">
			   		<tr bgcolor="#ffffff">
			   			<td class='leftTD'>数据源：</td>
			   			<td class='rightTD' colspan="2">
			   				<input id="selectSouInputs" type="hidden" value=""/>
			   				<span id="selectSources" name="selectSource"></span>
			   			</td>
			   		</tr>
			   		<tr bgcolor="#ffffff">
			   			<td class='leftTD'>选择查看信息类型：</td>
			   			<td class='rightTD' id="selectTables"></td>
			   		</tr>
			   		<tr bgcolor="#ffffff">
			   			<td class='leftTD'>条件选项：</td>
			   			<td class='rightTD'>
			   			身份证号码: <input type="text" id="sfzh" name="sfzh" style="width:140px;line-height:18px;height: 18px" /> 
			   			  &nbsp;&nbsp;时间段日期选择：  <input style="width: 140px" id="starttimes"  class="Wdate" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  readonly="readonly" />   					
	    					至
	    					  <input style="width: 140px" id="endtimes"  class="Wdate" type="text"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  readonly="readonly" />
			   			</td>
			   		</tr>
			   		<tr bgcolor="#ffffff">
			   			<td colspan="2" style="text-align: center;">
			   				<input id="lastSelectButton" type="button" value="查询" style="padding: 0px 10px;" onclick="lastButtons()"/>
			   			</td>
			   		</tr>
			   	</table>
	    	</form>

			<ul id="showTabss" class="nav nav-tabs"></ul>
			<div id="showDivContents" class="tab-content"></div>
		</div>
    </div>

    <div id=showYJQuery></div>
    
    <script type="text/javascript">
    	selectyjQuery(1);
    </script>
</body>
</html>
