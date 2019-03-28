<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/pages/head.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>My JSP 'selectPage.jsp' starting page</title>
    <script type="text/javascript" src="resource/js/manage/controlIndex.js"></script>
    <style type="text/css">
    	body,div,input,form,ul,li,span,a,table{margin:0px;padding:0px;}
    
    	.datagrid {position: absolute;top: 33;left: 0;}
    
    	#conditionDiv{width: 100%;height: auto;background: #efefef;padding: 1px 2px;border-bottom: 1px solid #ccc;}
    	#conditionForm tr{margin-bottom:5px;}
    	#conditionForm td{padding: 0px 5px 0px 2px;border-right: 1px solid #cccccc;}
    	#clearButton{}
    	#selectButton{}
    	.xxA{}
    	.xxA:link{color:#000000;}
    	.xxA:hover{color:#38f;font-size: larger;}
    	.xxA:active{color:#ffffff;}
    	.xxA:visited{color:blue;}
    	
    	
    	.lastSelectForm{ width:98%;height:auto;padding: 0px 5px 10px;float:left; }
    	#lastSelectForm table{ width:100%;height:auto; }
    	.leftTD{ width: 10%;padding:5px 0px 3px 3px;height: auto;text-align: right; }
		.rightTD{ width: 90%;height: auto;padding:5px 0px 3px 3px;text-align:left; }
		
		/* checkBox BKHM */
		.selBkhm{padding-left:5px;}
		.selBkhm:hover{background-color: #dddddd;}
		.selBkhm span{margin-left:3px;}
		.hm_div{border-top: 1px solid #8DB2E3;}
		.hm_div input{font-size: smaller;width: 60px;margin: 5px 0px 3px 15px;}
		
		/* tabs标签  */
		#showTabs{margin:0px;}
		#showTabs th{ padding: 2px 5px;background-color: #CCCCCC;border-right: 1px dotted #8F8F8F;border-bottom: 1px dotted #8F8F8F; }
		#showTabs tr{ background: #e2e2e2; }
		.fade{border-left:1px solid #CCCCCC;border-right:1px solid #CCCCCC;border-bottom:1px solid #CCCCCC;}
		.tabK{}
		
		.ttd{font-size:12px;border-right:1px dotted #ccc;border-bottom:1px dotted #ccc;padding:2px 5px;word-break:keep-all;white-space:nowrap;}
		
		/* page分页  */
		.fenyeC{float:left;border-top:1px solid #cccccc;width:97%;background: #efefef;position: absolute;bottom: 8px;height: 30px;line-height: 30px;}
		#fenyeTable{line-height:30px;float:left;margin-left:10px;}
		#fenyeTable td{background:#efefef;}
		#fenyeTable a{height:24px;width:24px;display:inline-block;line-height:24px;border:1px solid #ffffff;text-align:center;}
		#fenyeTable a:hover{border: 1px solid #0066CC;}
		.fenyeFirst{width: 16px;height: 16px;display: inline-block;margin: 5px 0px 0px 5px;background: url('/resource/easyui/themes/default/images/pagination_first.gif') no-repeat;}
		.fenyePrev{width: 16px;height: 16px;display: inline-block;margin: 5px 0px 0px 5px;background: url('/resource/easyui/themes/default/images/pagination_prev.gif') no-repeat;}
		.fenyeNext{width: 16px;height: 16px;display: inline-block;margin: 5px 0px 0px 5px;background: url('/resource/easyui/themes/default/images/pagination_next.gif') no-repeat;}
		.fenyeLast{width: 16px;height: 16px;display: inline-block;margin: 5px 0px 0px 5px;background: url('/resource/easyui/themes/default/images/pagination_last.gif') no-repeat;}
		.fenyeShuxian{height: 20px;border-left: 1px solid #cccccc;display: inline-block;line-height: 30px;margin: 0px 5px;}
    </style>
  </head>
  
  <body>
 	 <div id="detailInfor" class="modal hide fade in" style="display:none;width:1000px;height:620px;top:40%;left:35%;">
    	<div class="modal-header">
			<a class="close" data-dismiss="modal">×</a>
			<h3 id="titleName">详细信息</h3>
		</div>

		<div class="modal-body" style="padding-left:15px;">
			<div>
				<input type="hidden" id="allTable"/>
		    	<input type="hidden" id="tableTitle"/>
		    	<input type="hidden" id="getBkhm"/>
		    	<form action="" id="lastSelectForm" class="lastSelectForm">
		    		<table>
				   		<tr>
				   			<td class='leftTD'>数据源:</td>
				   			<td class='rightTD'>
				   				<input id="selectSouInput" type="hidden" value=""/>
				   				<span id="selectSource" name="selectSource"></span>
				   			</td>
				   		</tr>
				   		<tr>
				   			<td class='leftTD'>布控号码:</td>
				   			<td class='rightTD'>
				   				<input id="showBkhm" name="" type="text" style="width:170px;"/>
				   			</td>
				   		</tr>
				   	</table>
				   	
				   	<input id="lastSelectButton" type="button" value="查询" style="padding: 0px 10px;" onclick="lastButton()"/>
		    	</form>
    		</div>

			<ul id="showTabs" class="nav nav-tabs"></ul>
			<div id="showDivContent" class="tab-content"></div>
		</div>
    </div>
    
    <div id="conditionDiv">
    	<form id="conditionForm" action="">
    		<table>
    			<tr>
    				<td>布控标题:<input type="text" id="bt" name="bt" style="width:120px;" /></td>
    				<td>身份证号码/车牌号码:<input  type="text" id="bkhm" name="bkhm" style="width:140px;" /></td>
    				
    				<td>姓名:<input type="text" id="bkr" name="bkr" style="width:80px;" /></td>
    				<td>布控状态:
    					<select id="status" name="status">
    						<option value="0">所有</option>
    						<option value="1">进行中</option>
    						<option value="2">已完成</option>
    					</select>
    				</td>
    				<td>
    					布控时间:<input type="text" id="startTime" name="startTime" class="easyui-validatebox" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" style="width:130px;" />
    					至<input type="text" id="endTime" name="endTime" class="easyui-validatebox" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" style="width:130px;" />
    				</td>
    				<td>
						<a id="clearButton" title="清空" class="btn" onclick="clearForm();" href="javascript:void(0);">
							<i class="icon-plus-sign"></i> 清空
						</a>
    				</td>
    				<td>
						<a id="selectButton" title="查询" class="btn" onclick="selectForm();" href="javascript:void(0);">
							<i class="icon-plus-sign"></i>查询
						</a>
    				</td>
    			</tr>
    		</table>
    	</form>
    </div>
    
    <div id="showListDiv"></div>
    
  	<script type="text/javascript">
  		selectForm();
  	</script>
  </body>
</html>
