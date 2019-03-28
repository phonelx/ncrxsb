<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="/pages/head.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>抗震支架检验计算报告</title>
</head>
<style>
		.tableBox,.tableBox1,.inTable{
			border: 1px solid #ccc;
			border-collapse: separate;			
			-webkit-border-radius: 8px;
			-moz-border-radius: 8px;
			border-radius: 8px;
			border-left1:1px solid #ccc;			
		}
		.tableBox1 tr td{			
			border-left:none;
			border-top:none;			
		}
		.inTable tr th,.inTable tr td{			
			border-left:none;
			border-top:none;			
		}
		.tableBox tr td table tr td{
			border:none;			
		}
		table{
			width:72%;
			border-bottom:none;
			text-shadow: 0 -1px 1px rgba(0, 0, 0, 0.2);
			
		}
		table tr th,table tr td {
			border-right: 1px solid #ccc;
			border-bottom: 1px solid #ccc;
			padding: 5px;
		}
		table tr{			
			border-bottom1: 1px solid #ccc;
		}
		
		table tr th:last-child,table tr td:last-child {
			border-right:none;
			border-bottom1: none;
		}
		table tr:last-child{
			
			border-bottom: none;
		}
	
		<!--table tr td{
			padding:10px 20px;
			font-size:14px;
		}-->
		.itemFirst input,.itemFirst1 input{
			width:160px;
			height:30px;
			border:none;
			border-bottom:1px solid #ccc;
			outline:none;
			padding-left:6px;
		}
		.itemFirst1 input{
			margin-top:10px;
		}
		.itemFirst2{
			margin-bottom:10px;
		}
		.itemFirst2 input{
			width:120px;
			height:30px;
			border:none;
			border-bottom:1px solid #ccc;
			outline:none;
			padding-left:6px;
		}
		.inTable tr td{
			padding:10px 20px;
			height:34px;
		}
		.inTable tr th{
			padding:10px 20px;
			font-size:14px;
		}
		.inTable,.inTable1,.inTable2{
			width:100%;
		}
		.inTable input,.inTable1 input,.inTable2 input{
			width:160px;
			height:30px;
			border:none;
			border-bottom:1px solid #ccc;
			outline:none;
			padding-left:6px;
			font-size:14px;
		}
		.inTable1 input{
			width:100px;
			
		}
		.textStyle{
			font-size:14px;
		}
		.textPadding{
			padding:0 50px;
		}
		.titleText{
			font-weight:bold;
			text-align:center;
			font-size:18px;
			margin:18px auto;
			text-shadow: 0 -1px 1px rgba(0, 0, 0, 0.2);
		}
		</style>		
	</head>

	<body>
		<div class="titleText">表A  抗震支吊架节点计算书</div>
		
		<table class="tableBox" align="center" border="0" cellspacing="0" cellpadding="0">
			<!--<caption>抗震支吊架节点计算书</caption>-->
			<tbody>
				<tr>
					<td align="center" colspan="2"><b class="textStyle">抗震支吊架节点计算书</b></td>
				</tr>
				<tr>
					<td colspan="2">
						<table style="width:100%;margin-left:-20px;"  border="0" class="inTable2">
							<tr>
								<td>项目名称：<input type="text" /></td>
								<td>项目地址：<input type="text" /></td>
							</tr>
							<tr>
								<td>支吊架类型：<input type="text" /></td>
								<td>
									支吊架编号：<input type="text" />
									楼层：<input type="text" />
								</td>
							</tr>
						</table>
					
						<!--<div class="itemFirst">
							项目名称：<input type="text" />
							项目地址：<input type="text" />
						</div>
						<div class="itemFirst1">
							支吊架类型：<input type="text" />
							支吊架编号：<input type="text" />
							楼层：<input type="text" />
						</div>αEK=γηζ1ζ2αmax-->
					</td>
				</tr>
				<tr>
					<td align="center" width="180px">
						<b class="textStyle">构件信息</b>
					</td>
					<td align="center">
						<b class="textStyle">支撑信息</b>
					</td>
				</tr>
				<tr>
					<td>
						<div class="itemFirst2">
							侧向管束：<input type="text" />							
						</div>
						<div class="itemFirst2">
							额定荷载：<input type="text" />（N）
						</div>
						<div class="itemFirst2">
							纵向管束：<input type="text" />
						</div>
						<div class="itemFirst2">
							额定荷载：<input type="text" />（N）
						</div>
						<div class="itemFirst2">
							根部连接构件：<input type="text" />
						</div>
						<div class="itemFirst2">
							额定荷载：<input type="text" />（N）
						</div>
						<div class="itemFirst2">
							管部连接构件：<input type="text" />
						</div>
						<div class="itemFirst2">
							额定荷载：<input type="text" />（N）
						</div>
					</td>
					<td>
						<table style="width:100%;margin-left:-20px;" border="0" class="inTable1">
							<tr>
								<td>吊杆规格：<input type="text" />	</td>
								<td>吊杆最大使用荷载：<input type="text" />（N）</td>
							</tr>
							<tr>
								<td>斜撑长度：<input type="text" />（mm）</td>
								<td>
									斜撑垂直夹角：<input type="text" />
								</td>
							</tr>
							<tr>
								<td>最小回转半径：<input type="text" />（mm）</td>
								<td>L/R值：<input type="text" /></td>
							</tr>
							<tr>
								<td colspan="2">斜撑最大水平承载力：<input type="text" style="width:200px"/>（N）</td>
							</tr>
							<tr>
								<td colspan="2">水平加速度：<input type="text" style="width:200px"/>g</td>
							</tr>
						</table>
						
					</td>
				</tr>
				<tr>
					<td align="center">
						<b class="textStyle">锚栓信息</b>
					</td>
					<td align="center" valign="top" rowspan="2" style="position:relative;border-bottom:none">
						<b class="textStyle">抗震支吊架详图</b>
						<div style="position:absolute;bottom:10px;left:36%;">
							<input type="checkbox" />侧向支架&nbsp;&nbsp;&nbsp;
							<input type="checkbox" />双向支架
						</div>
					</td>
				</tr>
				
				<tr style="border-bottom:none">
					<td style="border-right:1px solid #ccc;border-bottom:none">
						<div class="itemFirst2">
							斜撑锚栓规格：<input type="text" />							
						</div>
						<div class="itemFirst2">
							斜撑锚栓安装方向：<input type="text" />（N）
						</div>
						<div class="itemFirst2">
							钻头直径：<input type="text" />
						</div>
						<div class="itemFirst2">
							有效锚固深度：<input type="text" />（N）
						</div>
						<div class="itemFirst2">
							安装扭矩：<input type="text" />
						</div>
						<div class="itemFirst2">
							抗拉承载力：<input type="text" />（N）
						</div>
						<div class="itemFirst2">
							抗剪承载力：<input type="text" />
						</div>
						<div class="itemFirst2">
							*整体安全分项系数 y=1.4<input type="text" />（N）
						</div>
					</td>					
					
				</tr>				
			
			</tbody>
		</table>
		
		<div class="titleText">续表A</div>
		<table class="tableBox1" align="center" border="1" cellspacing="0" cellpadding="0">
			<!--<caption>抗震支吊架节点计算书</caption>-->
			<tbody>
				<tr>
					<td align="center" style="border-bottom:none;">
						<b class="textStyle">荷载计算信息</b>
						<div style="margin:8px auto;">水平地震力综合系数{<img src="pic.jpg" />}计算值小于0.5时，按0.5取值</div>  

						<table style="width:100%;" class="inTable" border="1" cellspacing="0" cellpadding="0" align="center">
							<thead>
								<tr>
									<th rowspan="2">管道类型</th>
									<th rowspan="2">规格</th>
									<th rowspan="2">数量</th>
									<th colspan="2">作用范围</th>
									<th rowspan="2">αEK</th>
									<th colspan="2">计算荷载（N）</th>
								
								</tr>
								<tr>
									<th>侧向</th>
									<th>纵向</th>
									<th>侧向荷载</th>
									<th>纵向荷载</th>
								</tr>
							</thead>
							
							<tbody>
								<tr>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
								<tr>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
								<tr>
									<td colspan="6" style="border-bottom:none;"></td>
									<td style="border-bottom:none;">合计：</td>
									<td style="border-bottom:none;">合计：</td>
								</tr>
							</tbody>
							
						</table>

						<div style="margin-top:10px;">
							<span class="textPadding">深化设计：</span>  
							<span class="textPadding">审核：</span>   
							<span class="textPadding">日期：</span>
						</div>
					</td>
				</tr>

				
			</tbody>
		</table>
	</body>
</html>
