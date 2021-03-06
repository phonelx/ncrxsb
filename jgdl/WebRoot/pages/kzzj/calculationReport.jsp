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
<script type="text/javascript">
		$(function(){
		var xmsqu =  ${requestScope.map};
		//工程
		proInReport(xmsqu.prosqu);
		$("#gcsqu").val(xmsqu.prosqu);
		//子单位工程
		childInReport(xmsqu.childsqu);
		$("#zgcsqu").val(xmsqu.childsqu);
		//部位
		siteInReport(xmsqu.sitesqu);
		$("#bwsqu").val(xmsqu.sitesqu);
		//支架
		zjInReport(xmsqu.zjsqu);
		$("#zjsqu").val(xmsqu.zjsqu);
		});
		
</script>
<link href="resource/css/calculationReport.css" rel="stylesheet" type="text/css" media="print"/>
<script type="text/javascript" src='resource/js/jquery.jqprint-0.3.js'></script>
<script type="text/javascript" src='resource/js/projectConfig/calculationReport.js'></script>
<style>
	.tableBox{
		border: 1px solid #ccc;
		border-collapse: separate;			
		-webkit-border-radius: 8px;
		-moz-border-radius: 8px;
		border-radius: 8px;
		border-left1:1px solid #ccc;
		width:80%;			
	}
	/* .tableBox tbody tr td:last-child{
		border-bottom: none;
	}
	 */
	.tableBox tr td table tr td{
		border:none;	
	}
	table{
		width:100%;
		border-bottom:none;
		text-shadow: 0 -1px 1px rgba(0, 0, 0, 0.2);
		margin-bottom:20px;
		border-collapse: separate;	
	}
	table tr th,table tr td {
		border-right: 1px solid #ccc;
		border-bottom: 1px solid #ccc;
		padding:5px;
	}
	table tr{			
		border-bottom1: 1px solid #ccc;
	}
	
	table tr th:last-child,table tr td:last-child {
		border-right:none;
		border-bottom1: none;
	}
	 .tableBox tr td{
		  padding-left:0.4% ; 
		 padding-right:0.4%;  
		/*  */
	} 
	
	#tab_2  .tableBox{
		width1:81.4%;
	}
	.textStyle{
		/* font-size:14px; */
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
	
	.NR input {
		width1:160px;
		height:20px;
		border:none;
	
		outline:none;
		padding-left:6px;
		/* font-size:14px; */
	}
	#tab_3 .tableBox input{
		width:70px;
		height:20px;
		border:none;
	
		outline:none;
		padding-left:6px;
		/* font-size:14px; */
	} 
	.addFont15{
		font-size:15px;
		}
	.borderBottomNone{
	    border-bottom:none;
	}
	.width50{width:50px;}
	</style>	
		
	</head>

	<body>
		<div class="titleText">抗震支吊架节点计算书</div>
		<div class="content-wrapper" >
        	<div class="container">         
         		<section class="content">
					<div class="row">
			            <div class="col-md-6">
			              <!-- Custom Tabs -->
				              <div class="nav-tabs-custom">
				              <div><a href="javaScript:print();" class="btn" data-dismiss="mo1dal" style="float: right;">打印</a></div>
					               <ul class="nav nav-tabs">
						                  <li class="active"><a href="#tab_1" data-toggle="tab">Tab 1</a></li>
						                  <li><a href="#tab_2" data-toggle="tab">Tab 2</a></li>
						                  <li><a href="#tab_3" data-toggle="tab">Tab 3</a></li>						                 
					                </ul>
					                
					                <div class="tab-content"> 
					                	 
					                	  <div class="tab-pane active" id="tab_1">
					                	
						                    	<div id = "title">						                    	 
						                    		<div class="titleText"><span id="bgmc">盐井十组安置房项目<span/>抗震支架项目 </div>
						                    		<div class="titleText">地下室&nbsp; 综KZZ402</div>
						                    		<div class="titleText">节点计算书</div>
						                    		<div class="titleText"><img src="resource/images/ywg.png"></div>
						                    		<div class="titleText">四川意维高科技股份有限公司</div>
						                    	</div>
						                  </div><!-- /.tab-pane -->
						                  
						                  <div class="tab-pane " id="tab_2">
						                    	<table class="tableBox" align="center" border="0" cellspacing="0" cellpadding="0">
													
													<tbody>
														<tr>
															<td align="center" colspan="11" style="height:36px;" class='XH'><b class="textStyle addFont15">第1部分 &nbsp;&nbsp; 信息汇总表</b></td>
														</tr>
														
														<tr>
															<td align="center" rowspan="8" class="width50">
																<div  style="width: 3%;writing-mode: lr-tb;">
																	<b class="textStyle">工程信息</b>
																</div>
															</td>
															<td align="center" style="" class='XH'><b class="textStyle">序号</b></td>
															<td align="center" class='XM' ><b class="textStyle">项目</b></td>
															<td align="center" class='NR'><b class="textStyle">内容</b></td>
															<td align="center" rowspan="11" class="width50">
																<div  style="width: 3%;writing-mode: lr-tb;">
																	<b class="textStyle">支架信息</b>
																</div>
															</td>
															<td align="center" class='XH'><b class="textStyle">序号</b></td>				
															<td align="center" class='XM'><b class="textStyle">项目</b></td>
															<td align="center" class='NR'><b class="textStyle">内容</b></td>
														</tr>
														<tr>
															<!-- <td align="center" rowspan="7" class='XHC' ><div  style="width: 3%;writing-mode: lr-tb;"><b class="textStyle">工程信息</b></div></td> -->
															<td align="center" class='XHH'>1</td>
															<td align="center" class='XM'><b class="textStyle">工程名称</b></td>
															<td align="center" class='NR' ><span id="gcmc"></span></td>
															<!-- <td align="center" rowspan="10" class='XHC'><div  style="width: 0.3%;writing-mode: lr-tb;"><b class="textStyle">支架信息</b><div></td> -->
															<td align="center" class='XHH'>11</td>
															<td align="center" class='XM'><b class="textStyle">型号</b></td>
															<td align="center" class='NR' colspan="2"><span id="xh"></span></td>
														</tr>
														<tr>
															<!-- <td align="center" rowspan="7"><b class="textStyle">工程信息</b></td> -->
															<td align="center" class='XHH'>2</td>
															<td align="center" class='XM'><b class="textStyle">建筑类别</b></td>
															<td align="center" class='NR' ><span id="jzlb"></span></td>
															<!-- <td align="center" rowspan="7"><b class="textStyle">支架信息</b></td> -->
															<td align="center"class='XHH'>12</td>
															<td align="center" class='XM'><b class="textStyle">编号</b></td>
															<td align="center" class='NR' colspan="2"><span id="bh"></span></td>
														</tr>
														<tr>
															<!-- <td align="center" rowspan="7"><b class="textStyle">工程信息</b></td> -->
															<td align="center" class='XHH'>3</td>
															<td align="center" class='XM'><b class="textStyle">工程地址</b></td>
															<td align="center" class='NR' ><span id="gcdz"></span></td>
															<!-- <td align="center" rowspan="7"><b class="textStyle">支架信息</b></td> -->
															<td align="center" class='XHH'>13</td>
															<td align="center" class='XM'><b class="textStyle">服务系统</b></td>
															<td align="center" class='NR' colspan="2"><span id="fwxt"></span></td>
														</tr>
														<tr>
															<!-- <td align="center" rowspan="7"><b class="textStyle">工程信息</b></td> -->
															<td align="center" class='XHH'>4</td>
															<td align="center" class='XM'><b class="textStyle">地区设防烈度</b></td>
															<td align="center" class='NR' ><span id="sfld"></span></td>
															<!-- <td align="center" rowspan="7"><b class="textStyle">支架信息</b></td> -->
															<td align="center" class='XHH'>14</td>
															<td align="center" class='XM'><b class="textStyle">安装部位</b></td>
															<td align="center" class='NR' colspan="2"><span id="azbw"></span></td>
														</tr>
														<tr>
															<!-- <td align="center" rowspan="7"><b class="textStyle">工程信息</b></td> -->
															<td align="center" class='XHH'>5</td>
															<td align="center" class='XM'><b class="textStyle">地震加速度</b></td>
															<td align="center" class='NR' ><span id="dzjst"></span></td>
															<!-- <td align="center" rowspan="7"><b class="textStyle">支架信息</b></td> -->
															<td align="center" class='XHH'>15</td>
															<td align="center" class='XM'><b class="textStyle">安装标高</b></td>
															<td align="center" class='NR' colspan="2"><span id="azbg"></span></td>
														</tr>
														<tr>
															<!-- <td align="center" rowspan="7"><b class="textStyle">工程信息</b></td> -->
															<td align="center" class='XHH'>6</td>
															<td align="center" class='XM'><b class="textStyle">地震类型</b></td>
															<td align="center" class='NR' ><span id="dzlx"></span></td>
															<!-- <td align="center" rowspan="7"><b class="textStyle">支架信息</b></td> -->
															<td align="center" class='XHH' >16</td>
															<td align="center" class='XM'><b class="textStyle">设置状态</b></td>
															<td align="center" class='NR' colspan="2"><span id="szzt"></span></td>
														</tr>
														<tr>
															<!-- <td align="center" rowspan="7"><b class="textStyle">工程信息</b></td> -->
															<td align="center" class='XHH'>7</td>
															<td align="center" class='XM'><b class="textStyle">建筑高度</b></td>
															<td align="center" class='NR' ><span id="jzgd"></span></td>
															<!-- <td align="center" rowspan="7"><b class="textStyle">支架信息</b></td> -->
															<td align="center" class='XHH'>17</td>
															<td align="center" class='XM'><b class="textStyle">斜撑角度</b></td>
															<td align="center" class='NR' colspan="2"><span id="xcjd"></span></td>
														</tr>
														<tr>
															<td align="center" rowspan="3" class="width50"><div  style="width: 3%;writing-mode: lr-tb;"><b class="textStyle">管线信息</b></div></td> 
															<td align="center" class='XHH'>8</td>
															<td align="center" class='XM'><b class="textStyle">管线类型</b></td>
															<td align="center" class='NR'  ><span id="gxlx"></span></td>
															<!-- <td align="center" rowspan="7"><b class="textStyle">支架信息</b></td> -->
															<td align="center" class='XHH'>18</td>
															<td align="center" class='XM'><b class="textStyle">结构连接方式</b></td>
															<td align="center" class='NR' colspan="2"><span id="jgljfs"></span></td>
														</tr>
														<tr>
															<!-- <td align="center" rowspan="7"><b class="textStyle">工程信息</b></td> -->
															<td align="center" class='XHH'>9</td>
															<td align="center" class='XM'><b class="textStyle">管线材质</b></td>
															<td align="center" class='NR' ><span id="gxcz"></span></td>
															<!-- <td align="center" rowspan="7"><b class="textStyle">支架信息</b></td> -->
															<td align="center" class='XHH'>19</td>
															<td align="center" class='XM'><b class="textStyle">约束方式及规格</b></td>
															<td align="center" class='NR' colspan="2"><span id="ysfsgg"></span></td>
														</tr>
														<tr>
															<!-- <td align="center" rowspan="7"><b class="textStyle">工程信息</b></td> -->
															<td align="center" class='XHH'>10</td>
															<td align="center" class='XM'><b class="textStyle">管线规格</b></td>
															<td align="center" class='NR' ><span id="gxgg"></span></td>
															<!-- <td align="center" rowspan="7"><b class="textStyle">支架信息</b></td> -->
															<td align="center" class='XHH'>20</td>
															<td align="center" class='XM'><b class="textStyle">抗震支撑数量</b></td>
															<td align="center" class='NR' colspan="2"><span id="kzzjsl"></span></td>
														</tr>
														<tr style="border:none">
															<!-- <td align="center" rowspan="7"><b class="textStyle">工程信息</b></td> -->
															<td align="center" rowspan="10" class="borderBottomNone"><div  style="width: 3%;writing-mode: lr-tb;"><b class="textStyle">支架形式简图</b></div></div></td>
															<td align="center" class='XM borderBottomNone' colspan="7"><img src="resource/images/zjjt.png"></td>
															
														</tr>
													</tbody>
												</table>
						                  </div><!-- /.tab-pane -->
						                  
						                  <div class="tab-pane" id="tab_3">
						                   	 <table class="tableBox" align="center" border="0" cellspacing="0" cellpadding="0">
													
													<tbody>
														<tr>
															<td align="center" colspan="11" style="height:36px;" ><b class="textStyle addFont15">第2部分 &nbsp;&nbsp; 荷载计算信息</b></td>
											
														</tr>
														<tr>
															<td align="center" rowspan="2" style="white-space: nowrap" ><b class="textStyle">管道类别</b></td>								
															<td align="center" rowspan="2"><b class="textStyle">规格</b></td>
															<td align="center" rowspan="2"><b class="textStyle">数量</b></td>
															<td align="center"  ><b class="textStyle">理论重量</b></td>				
															<td align="center" colspan="2"><b class="textStyle">作用范围（m)</b></td>															
															<td align="center" rowspan="2"><b class="textStyle">αEK</b></td>
															<td align="center" ><b class="textStyle">水平地震</b></td>		
															<td align="center" colspan="2"><b class="textStyle">地震水平力设计值(KN)</b></td>
															<td align="center" ><b class="textStyle">备注</b></td>
														</tr>
														<tr>																								
															<td align="center"  ><b class="textStyle">m/KN</b></td>				
															<td align="center" ><b class="textStyle">侧向</b></td>
															<td align="center" ><b class="textStyle">纵向</b></td>
															
															<td align="center" ><b class="textStyle">分项系数</b></td>
															<td align="center" ><b class="textStyle">侧向</b></td>				
															<td align="center" ><b class="textStyle">纵向</b></td>
															<td align="center" ><b class="textStyle"></b></td>
														</tr>
														<tr>																								
															<td align="center"  >水管</td>				
															<td align="center" >DN200</td>
															<td align="center" ><input type="text" id="gcdz" value="tttt"/></td>
															<td align="center"  ><input type="text" id="gcdz" value=""/></td>				
															<td align="center" ><input type="text" id="gcdz" value=""/></td>
															<td align="center" ><input type="text" id="gcdz" value=""/></td>
															<td align="center" ><input type="text" id="gcdz" value=""/></td>
															<td align="center" ><input type="text" id="gcdz" value=""/></td>				
															<td align="center" ><input type="text" id="gcdz" value=""/></td>
															<td align="center" ><input type="text" id="gcdz" value=""/></td>
															<td align="center" ><input style="width: 60px" type="text"  value=""/></td>
														</tr>
														<tr>																								
															<td align="center"  >风管</b></td>				
															<td align="center" ></td>
															<td align="center" ></td>
															<td align="center"  ></td>				
															<td align="center" >12</td>
															<td align="center" >24</td>
															<td align="center" >tyle">0.5</td>
															<td align="center" >1.3</td>				
															<td align="center" >0</td>
															<td align="center" >0</td>
															<td align="center" ></td>
														</tr>
														<tr>																								
															<td align="center"  >桥架</td>				
															<td align="center" >400*100</td>
															<td align="center" ></td>
															<td align="center"  ></td>				
															<td align="center" >12</td>
															<td align="center" >24</td>
															<td align="center" >0.5</td>
															<td align="center" >1.3</td>				
															<td align="center" >0</td>
															<td align="center" ></td>
															<td align="center" ></td>
														</tr>
														<tr>
															<td  colspan="11" style="width: 20px" ><b class="textStyle">说明：</b>11</td>
														</tr>
														<tr>
															<td align="center" colspan="11" style="width: 20px;height:36px;" ><b class="textStyle addFont15">第3部分&nbsp;&nbsp;  受力分析信息</b></td>
														</tr>
														<tr>																								
															<td align="center"  colspan="6" ><img src="resource/images/cxsl.png"></td>				
															<td align="center" colspan="5"><img src="resource/images/zxsl.png"></td>
															
														</tr>
														<tr>																								
															<td align="center"  colspan="6" ><b class="textStyle">侧向受力分析图</b></td>				
															<td align="center" colspan="5"><b class="textStyle">纵向受力分析图</b></td>
															
														</tr>
														<tr>																								
															<td align="center"  colspan="2" ><b class="textStyle">侧向支架斜撑受力</b></td>				
															<td align="center" colspan="4">N1=18.5KN</td>
															<td align="center"  colspan="2" ><b class="textStyle">纵向支架斜撑受力</b></td>				
															<td align="center" colspan="3">N2=17.7KN；N3=13.6KN</td>
															
														</tr>
														<tr>
															<td align="center" colspan="11" style="width: 20px;height:36px;" ><b class="textStyle addFont15">第4部分&nbsp;&nbsp;  受力校核信息</b></td>
														</tr>
														<tr>
															<td align="center" rowspan="2" colspan="2" style="width: 20px" ><b class="textStyle">项目</b></td>								
															<td align="center" rowspan="2" colspan="2"><b class="textStyle">名称</b></td>
															<td align="center" rowspan="2" ><b class="textStyle">型号</b></td>
															<td align="center"  ><b class="textStyle">荷载</b></td>				
															<td align="center" ><b class="textStyle">构件数</b></td>															
															<td align="center" ><b class="textStyle">构件荷载</b></td>
															<td align="center" ><b class="textStyle">构件承载力</b></td>		
															<td align="center" rowspan="2"><b class="textStyle">校核说明</b></td>
															<td align="center" rowspan="2" ><b class="textStyle">备注</b></td>
														</tr>
														<tr>
															
																													
															<td align="center" ><b class="textStyle">KN</b></td>
															<td align="center" ><b class="textStyle">个</b></td>
															<td align="center"  ><b class="textStyle">KN</b></td>				
															<td align="center" style="border-right:1px solid #ccc; "><b class="textStyle">KN</b></td>															
													
														</tr>
														<tr>
															<td align="center" rowspan="5" colspan="2" style="width: 20px" ><b class="textStyle">抗震连接件</b></td>								
															<td align="center"  colspan="2">抗震铰链A</td>
															<td align="center"  >J-A</td>
															<td align="center"  ></td>				
															<td align="center" >2</td>															
															<td align="center" >0</td>
															<td align="center" >7.3</td>		
															<td align="center" >满足</td>
															<td align="center"  ></td>
														</tr>
														<tr>
														
															<td align="center"  colspan="2">抗震铰链B></td>
															<td align="center"  >J-B</td>
															<td align="center"  ></td>				
															<td align="center" >2</td>															
															<td align="center" >0</td>
															<td align="center" >7.3</td>		
															<td align="center" >满足</td>
															<td align="center"  ></td>
														</tr>
														<tr>
														
															<td align="center"  colspan="2">抗震底座A</td>
															<td align="center"  >D-A</td>
															<td align="center"  ></td>				
															<td align="center" >2</td>															
															<td align="center" >0</td>
															<td align="center" >12</td>		
															<td align="center" >满足</td>
															<td align="center"  ></td>
														</tr>
														<tr>
														
															<td align="center"  colspan="2">抗震底座B1</td>
															<td align="center"  >D-B1</td>
															<td align="center"  ></td>				
															<td align="center" >2</td>															
															<td align="center" >0</td>
															<td align="center" >12</td>		
															<td align="center" >满足</td>
															<td align="center"  ></td>
														</tr>
														<tr>
														
															<td align="center"  colspan="2">抗震底座B2</td>
															<td align="center"  >D-B2</td>
															<td align="center"  ></td>				
															<td align="center" >2</td>															
															<td align="center" >0</td>
															<td align="center" >12</td>		
															<td align="center" >满足</td>
															<td align="center"  ></td>
														</tr>
														<tr>
															<td align="center" rowspan="45" colspan="2" style="width: 20px" ><b class="textStyle">管束</b></td>								
															<td align="center"  rowspan="11" colspan="2">Ω型管束</td>
															<td align="center"  >Ω-65</td>
															<td align="center"  ></td>				
															<td align="center" >1</td>															
															<td align="center" >0</td>
															<td align="center" >7.3</td>		
															<td align="center" >满足</td>
															<td align="center"  ></td>
														</tr>
														<tr>															
															<td align="center"  >Ω-65</td>
															<td align="center"  ></td>				
															<td align="center" >1</td>															
															<td align="center" >0</td>
															<td align="center" >7.3</td>		
															<td align="center" >满足</td>
															<td align="center"  ></td>
														</tr>
														<tr>															
															<td align="center"  >Ω-65</td>
															<td align="center"  ></td>				
															<td align="center" >1</td>															
															<td align="center" >0</td>
															<td align="center" >7.3</td>		
															<td align="center" >满足</td>
															<td align="center"  ></td>
														</tr>
														<tr>															
															<td align="center"  >Ω-65</td>
															<td align="center"  ></td>				
															<td align="center" >1</td>															
															<td align="center" >0</td>
															<td align="center" >7.3</td>		
															<td align="center" >满足</td>
															<td align="center"  ></td>
														</tr>
														<tr>															
															<td align="center"  >Ω-65</td>
															<td align="center"  ></td>				
															<td align="center" >1</td>															
															<td align="center" >0</td>
															<td align="center" >7.3</td>		
															<td align="center" >满足</td>
															<td align="center"  ></td>
														</tr>
														<tr>															
															<td align="center"  >Ω-65</td>
															<td align="center"  ></td>				
															<td align="center" >1</td>															
															<td align="center" >0</td>
															<td align="center" >7.3</td>		
															<td align="center" >满足</td>
															<td align="center"  ></td>
														</tr>
														<tr>															
															<td align="center"  >Ω-65</td>
															<td align="center"  ></td>				
															<td align="center" >1</td>															
															<td align="center" >0</td>
															<td align="center" >7.3</td>		
															<td align="center" >满足</td>
															<td align="center"  ></td>
														</tr>
														<tr>															
															<td align="center"  >Ω-65</td>
															<td align="center"  ></td>				
															<td align="center" >1</td>															
															<td align="center" >0</td>
															<td align="center" >7.3</td>		
															<td align="center" >满足</td>
															<td align="center"  ></td>
														</tr>
														<tr>															
															<td align="center"  >Ω-65</td>
															<td align="center"  ></td>				
															<td align="center" >1</td>															
															<td align="center" >0</td>
															<td align="center" >7.3</td>		
															<td align="center" >满足</td>
															<td align="center"  ></td>
														</tr>
														<tr>															
															<td align="center"  >Ω-65</td>
															<td align="center"  ></td>				
															<td align="center" >1</td>															
															<td align="center" >0</td>
															<td align="center" >7.3</td>		
															<td align="center" >满足</td>
															<td align="center"  ></td>
														</tr>
														<tr>															
															<td align="center"  >Ω-65</td>
															<td align="center"  ></td>				
															<td align="center" >1</td>															
															<td align="center" >0</td>
															<td align="center" >7.3</td>		
															<td align="center" >满足</td>
															<td align="center"  ></td>
														</tr>
														<tr>
															
															<td align="center"  rowspan="7" colspan="2">O型管束</td>
															<td align="center"  >Ω-65</td>
															<td align="center"  ></td>				
															<td align="center" >1</td>															
															<td align="center" >0</td>
															<td align="center" >7.3</td>		
															<td align="center" >满足</td>
															<td align="center"  ></td>
														</tr>
														<tr>															
															<td align="center"  >Ω-65</td>
															<td align="center"  ></td>				
															<td align="center" >1</td>															
															<td align="center" >0</td>
															<td align="center" >7.3</td>		
															<td align="center" >满足</td>
															<td align="center"  ></td>
														</tr>
														<tr>															
															<td align="center"  >Ω-65</td>
															<td align="center"  ></td>				
															<td align="center" >1</td>															
															<td align="center" >0</td>
															<td align="center" >7.3</td>		
															<td align="center" >满足</td>
															<td align="center"  ></td>
														</tr>
														<tr>															
															<td align="center"  >Ω-65</td>
															<td align="center"  ></td>				
															<td align="center" >1</td>															
															<td align="center" >0</td>
															<td align="center" >7.3</td>		
															<td align="center" >满足</td>
															<td align="center"  ></td>
														</tr>
														<tr>															
															<td align="center"  >Ω-65</td>
															<td align="center"  ></td>				
															<td align="center" >1</td>															
															<td align="center" >0</td>
															<td align="center" >7.3</td>		
															<td align="center" >满足</td>
															<td align="center"  ></td>
														</tr>
														<tr>															
															<td align="center"  >Ω-65</td>
															<td align="center"  ></td>				
															<td align="center" >1</td>															
															<td align="center" >0</td>
															<td align="center" >7.3</td>		
															<td align="center" >满足</td>
															<td align="center"  ></td>
														</tr>
														<tr>															
															<td align="center"  >Ω-65</td>
															<td align="center"  ></td>				
															<td align="center" >1</td>															
															<td align="center" >0</td>
															<td align="center" >7.3</td>		
															<td align="center" >满足</td>
															<td align="center"  ></td>
														</tr>
														<tr>
															
															<td align="center"  rowspan="5" colspan="2">U型管束</td>
															<td align="center"  >Ω-65</td>
															<td align="center"  ></td>				
															<td align="center" >1</td>															
															<td align="center" >0</td>
															<td align="center" >7.3</td>		
															<td align="center" >满足</td>
															<td align="center"  ></td>
														</tr>
														<tr>															
															<td align="center"  >Ω-65</td>
															<td align="center"  ></td>				
															<td align="center" >1</td>															
															<td align="center" >0</td>
															<td align="center" >7.3</td>		
															<td align="center" >满足</td>
															<td align="center"  ></td>
														</tr>
														<tr>															
															<td align="center"  >Ω-65</td>
															<td align="center"  ></td>				
															<td align="center" >1</td>															
															<td align="center" >0</td>
															<td align="center" >7.3</td>		
															<td align="center" >满足</td>
															<td align="center"  ></td>
														</tr>
														<tr>															
															<td align="center"  >Ω-65</td>
															<td align="center"  ></td>				
															<td align="center" >1</td>															
															<td align="center" >0</td>
															<td align="center" >7.3</td>		
															<td align="center" >满足</td>
															<td align="center"  ></td>
														</tr>
														<tr>															
															<td align="center"  >Ω-65</td>
															<td align="center"  ></td>				
															<td align="center" >1</td>															
															<td align="center" >0</td>
															<td align="center" >7.3</td>		
															<td align="center" >满足</td>
															<td align="center"  ></td>
														</tr>
														<tr>
															
															<td align="center"  rowspan="6" colspan="2">U型管束</td>
															<td align="center"  >Ω-65</td>
															<td align="center"  ></td>				
															<td align="center" >1</td>															
															<td align="center" >0</td>
															<td align="center" >7.3</td>		
															<td align="center" >满足</td>
															<td align="center"  ></td>
														</tr>
														<td align="center"  >Ω-65</td>
															<td align="center"  ></td>				
															<td align="center" >1</td>															
															<td align="center" >0</td>
															<td align="center" >7.3</td>		
															<td align="center" >满足</td>
															<td align="center"  ></td>
														<tr>															
															<td align="center"  >Ω-65</td>
															<td align="center"  ></td>				
															<td align="center" >1</td>															
															<td align="center" >0</td>
															<td align="center" >7.3</td>		
															<td align="center" >满足</td>
															<td align="center"  ></td>
														</tr>
														<tr>															
															<td align="center"  >Ω-65</td>
															<td align="center"  ></td>				
															<td align="center" >1</td>															
															<td align="center" >0</td>
															<td align="center" >7.3</td>		
															<td align="center" >满足</td>
															<td align="center"  ></td>
														</tr>
														<tr>															
															<td align="center"  >Ω-65</td>
															<td align="center"  ></td>				
															<td align="center" >1</td>															
															<td align="center" >0</td>
															<td align="center" >7.3</td>		
															<td align="center" >满足</td>
															<td align="center"  ></td>
														</tr>
														<tr>															
															<td align="center"  >Ω-65</td>
															<td align="center"  ></td>				
															<td align="center" >1</td>															
															<td align="center" >0</td>
															<td align="center" >7.3</td>		
															<td align="center" >满足</td>
															<td align="center"  ></td>
														</tr>
														<tr>
															
															<td align="center"  rowspan="8" colspan="2">P型保温管束</td>
															<td align="center"  >Ω-65</td>
															<td align="center"  ></td>				
															<td align="center" >1</td>															
															<td align="center" >0</td>
															<td align="center" >7.3</td>		
															<td align="center" >满足</td>
															<td align="center"  ></td>
														</tr>
														<tr>															
															<td align="center"  >Ω-65</td>
															<td align="center"  ></td>				
															<td align="center" >1</td>															
															<td align="center" >0</td>
															<td align="center" >7.3</td>		
															<td align="center" >满足</td>
															<td align="center"  ></td>
														</tr>
														<tr>															
															<td align="center"  >Ω-65</td>
															<td align="center"  ></td>				
															<td align="center" >1</td>															
															<td align="center" >0</td>
															<td align="center" >7.3</td>		
															<td align="center" >满足</td>
															<td align="center"  ></td>
														</tr>
														<tr>															
															<td align="center"  >Ω-65</td>
															<td align="center"  ></td>				
															<td align="center" >1</td>															
															<td align="center" >0</td>
															<td align="center" >7.3</td>		
															<td align="center" >满足</td>
															<td align="center"  ></td>
														</tr>
														<tr>															
															<td align="center"  >Ω-65</td>
															<td align="center"  ></td>				
															<td align="center" >1</td>															
															<td align="center" >0</td>
															<td align="center" >7.3</td>		
															<td align="center" >满足</td>
															<td align="center"  ></td>
														</tr>
														<tr>															
															<td align="center"  >Ω-65</td>
															<td align="center"  ></td>				
															<td align="center" >1</td>															
															<td align="center" >0</td>
															<td align="center" >7.3</td>		
															<td align="center" >满足</td>
															<td align="center"  ></td>
														</tr>
														<tr>															
															<td align="center"  >Ω-65</td>
															<td align="center"  ></td>				
															<td align="center" >1</td>															
															<td align="center" >0</td>
															<td align="center" >7.3</td>		
															<td align="center" >满足</td>
															<td align="center"  ></td>
														</tr>
														<tr>															
															<td align="center"  >Ω-65</td>
															<td align="center"  ></td>				
															<td align="center" >1</td>															
															<td align="center" >0</td>
															<td align="center" >7.3</td>		
															<td align="center" >满足</td>
															<td align="center"  ></td>
														</tr>
														<tr>
															
															<td align="center"  rowspan="8" colspan="2">O型保温管束</td>
															<td align="center"  >Ω-65</td>
															<td align="center"  ></td>				
															<td align="center" >1</td>															
															<td align="center" >0</td>
															<td align="center" >7.3</td>		
															<td align="center" >满足</td>
															<td align="center"  ></td>
														</tr>
														<tr>															
															<td align="center"  >Ω-65</td>
															<td align="center"  ></td>				
															<td align="center" >1</td>															
															<td align="center" >0</td>
															<td align="center" >7.3</td>		
															<td align="center" >满足</td>
															<td align="center"  ></td>
														</tr>
														<tr>															
															<td align="center"  >Ω-65</td>
															<td align="center"  ></td>				
															<td align="center" >1</td>															
															<td align="center" >0</td>
															<td align="center" >7.3</td>		
															<td align="center" >满足</td>
															<td align="center"  ></td>
														</tr>
														<tr>															
															<td align="center"  >Ω-65</td>
															<td align="center"  ></td>				
															<td align="center" >1</td>															
															<td align="center" >0</td>
															<td align="center" >7.3</td>		
															<td align="center" >满足</td>
															<td align="center"  ></td>
														</tr>
														<tr>															
															<td align="center"  >Ω-65</td>
															<td align="center"  ></td>				
															<td align="center" >1</td>															
															<td align="center" >0</td>
															<td align="center" >7.3</td>		
															<td align="center" >满足</td>
															<td align="center"  ></td>
														</tr>
														<tr>															
															<td align="center"  >Ω-65</td>
															<td align="center"  ></td>				
															<td align="center" >1</td>															
															<td align="center" >0</td>
															<td align="center" >7.3</td>		
															<td align="center" >满足</td>
															<td align="center"  ></td>
														</tr>
														<tr>															
															<td align="center"  >Ω-65</td>
															<td align="center"  ></td>				
															<td align="center" >1</td>															
															<td align="center" >0</td>
															<td align="center" >7.3</td>		
															<td align="center" >满足</td>
															<td align="center"  ></td>
														</tr>
														<tr>															
															<td align="center"  >Ω-65</td>
															<td align="center"  ></td>				
															<td align="center" >1</td>															
															<td align="center" >0</td>
															<td align="center" >7.3</td>		
															<td align="center" >满足</td>
															<td align="center"  ></td>
														</tr>
														<tr>
															
															<td align="center"  rowspan="7" colspan="2"><b class="textStyle">结构连接件</b></td>
															<td align="center"  rowspan="2" colspan="2">螺杆式锚栓</td>
															<td align="center"  rowspan="2">M12*113</td>	
															<td align="center" >抗拉：</td>			
															<td align="center" >1</td>															
															<td align="center" >0</td>
															<td align="center" style="border-right:1px solid #ccc; ">7.3</td>										
															<td align="center" rowspan="2" >满足</td>
															<td align="center" rowspan="2" ></td>
														</tr>
														<tr>
															
															
															<td align="center" >抗拉：</td>			
															<td align="center" >1</td>															
															<td align="center" >0</td>
															<td align="center" style="border-right:1px solid #ccc; ">7.3</td>										
															
														</tr>
														<tr>
															
															<td align="center"  rowspan="2" colspan="2">螺杆式锚栓</td>
															<td align="center"  rowspan="2">M12*113</td>	
															<td align="center" >抗拉：</td>			
															<td align="center" >1</td>															
															<td align="center" >0</td>
															<td align="center" style="border-right:1px solid #ccc; ">7.3</td>										
															<td align="center" rowspan="2" >满足</td>
															<td align="center" rowspan="2" ></td>
														</tr>
														<tr>
															
															
															<td align="center" >抗拉：</td>			
															<td align="center" >1</td>															
															<td align="center" >0</td>
															<td align="center" style="border-right:1px solid #ccc; ">7.3</td>										
															
														</tr>
														<tr>
															
															<td align="center"  rowspan="2" colspan="2">螺杆式锚栓</td>
															<td align="center"  rowspan="2">M12*113</td>	
															<td align="center" >抗拉：</td>			
															<td align="center" >1</td>															
															<td align="center" >0</td>
															<td align="center" style="border-right:1px solid #ccc; ">7.3</td>										
															<td align="center" rowspan="2" >满足</td>
															<td align="center" rowspan="2" ></td>
														</tr>
														<tr>
															
															
															<td align="center" >抗拉：</td>			
															<td align="center" >1</td>															
															<td align="center" >0</td>
															<td align="center" style="border-right:1px solid #ccc; ">7.3</td>										
															
														</tr>
														<tr>
															
															<td align="center"   colspan="3"><b class="textStyle">（βN)α＋（βV)α≦1</b></td>
															<td align="center"  colspan="2"><b class="textStyle">取最大值：6.5</b></td>	
															<td align="center" colspan="2"><b class="textStyle">0.255<1</b></td>																					
															<td align="center" ><b class="textStyle">满足</b></td>
															<td align="center"  ><b class="textStyle"></b></td>
														</tr>
														<tr>
															
															<td align="center"  rowspan="2" colspan="2"><b class="textStyle">上横担</b></td>
															<td align="center"  colspan="2">C型槽钢</b></td>	
															<td align="center" >C41</b></td>	
															<td align="center"  ></td>		
															<td align="center" >1</td>															
															<td align="center" ></td>
															<td align="center" ></td>										
															<td align="center" >满足</td>
															<td align="center"  ></td>
														</tr>
														<tr>
															<td align="center"  colspan="2">C型槽钢</b></td>	
															<td align="center" >C41</b></td>	
															<td align="center"  ></td>		
															<td align="center" >1</td>															
															<td align="center" ></td>
															<td align="center" ></td>										
															<td align="center" >满足</td>
															<td align="center"  ></td>
														</tr>
														<tr>
															
															<td align="center"  rowspan="2" colspan="2"><b class="textStyle">下横担</b></td>
															<td align="center"  colspan="2">C型槽钢</b></td>	
															<td align="center" >C41</b></td>	
															<td align="center"  ></td>		
															<td align="center" >1</td>															
															<td align="center" ></td>
															<td align="center" ></td>										
															<td align="center" >满足</td>
															<td align="center"  ></td>
														</tr>
														<tr>
															<td align="center"  colspan="2">C型槽钢</b></td>	
															<td align="center" >C41</b></td>	
															<td align="center"  ></td>		
															<td align="center" >1</td>															
															<td align="center" ></td>
															<td align="center" ></td>										
															<td align="center" >满足</td>
															<td align="center"  ></td>
														</tr>
														<tr>
															
															<td align="center"  rowspan="3" colspan="2"><b class="textStyle">斜撑</b></td>
															<td align="center"  colspan="2">C型槽钢</b></td>	
															<td align="center" >C41</b></td>	
															<td align="center"  ></td>		
															<td align="center" >1</td>															
															<td align="center" ></td>
															<td align="center" ></td>										
															<td align="center" >满足</td>
															<td align="center"  >C41槽钢</td>
														</tr>
														<tr>
															<td align="center"  colspan="2">C型槽钢</b></td>	
															<td align="center" >C41</b></td>	
															<td align="center"  ></td>		
															<td align="center" >1</td>															
															<td align="center" ></td>
															<td align="center" ></td>										
															<td align="center" >满足</td>
															<td align="center"  >极限长度</td>
														</tr>
														<tr>
															<td align="center"  colspan="2">C型槽钢</b></td>	
															<td align="center" >C41</b></td>	
															<td align="center"  ></td>		
															<td align="center" >1</td>															
															<td align="center" ></td>
															<td align="center" ></td>										
															<td align="center" >满足</td>
															<td align="center"  >3M</td>
														</tr>
														<tr>
															<td align="center"  colspan="11" style="height:36px;"><b class="textStyle addFont15">第5部分&nbsp;&nbsp; &nbsp;   校核结论</b></td>																
														</tr>
														<tr>
															<td colspan="11" class="borderBottomNone"><b class="textStyle">经对各主要构件调整验算，抗震支架系统受力满足抗震设防要求。</b></td>																
														</tr>
													</tbody>
												</table>
						                  </div><!-- /.tab-pane -->
					                </div><!-- /.tab-content -->
				              </div><!-- nav-tabs-custom -->
			            	</div><!-- /.col -->
			            </div><!-- row -->
					</div><!-- content -->
			     </div><!-- container -->
			 </div><!-- content-wrapper -->
		
		
		<%-- <table class="tableBox" align="center" border="0" cellspacing="0" cellpadding="0">
			<!--<caption>抗震支吊架节点计算书</caption>-->
			<tbody>
				<tr>
					<td align="center" colspan="8"><b class="textStyle">抗震支吊架节点计算书</b></td>
				</tr>
				<tr>
					<td align="center" colspan="2"><b class="textStyle">序号</b></td>
					
					<td align="center"><b class="textStyle">项目</b></td>
					<td align="center"><b class="textStyle">内容</b></td>
					<td align="center" colspan="2"><b class="textStyle">序号</b></td>				
					<td align="center"><b class="textStyle">项目</b></td>
					<td align="center"><b class="textStyle">内容</b></td>
				</tr>
				<tr>
					<td align="center" rowspan="7"><b class="textStyle">工程信息</b></td>
					<td align="center" ><b class="textStyle">1</b></td>
					<td align="center" ><b class="textStyle">工程名称</b></td>
					<td align="center" ><b class="textStyle">盐井十组安置房项目</b></td>
					<td align="center" rowspan="7"><b class="textStyle">支架信息</b></td>
					<td align="center" colspan="8"><b class="textStyle">11</b></td>
					<td align="center" colspan="8"><b class="textStyle">型号</b></td>
					<td align="center" colspan="8"><b class="textStyle">综KZZ402</b></td>
				</tr>
				<tr>
					<!-- <td align="center" rowspan="7"><b class="textStyle">工程信息</b></td> -->
					<td align="center" ><b class="textStyle">2</b></td>
					<td align="center" ><b class="textStyle">建筑类别</b></td>
					<td align="center" ><b class="textStyle">乙类建筑</b></td>
					<!-- <td align="center" rowspan="7"><b class="textStyle">支架信息</b></td> -->
					<td align="center" colspan="8"><b class="textStyle">12</b></td>
					<td align="center" colspan="8"><b class="textStyle">编号</b></td>
					<td align="center" colspan="8"><b class="textStyle"></b></td>
				</tr>
				<tr>
					<!-- <td align="center" rowspan="7"><b class="textStyle">工程信息</b></td> -->
					<td align="center" ><b class="textStyle">3</b></td>
					<td align="center" ><b class="textStyle">工程地址</b></td>
					<td align="center" ><b class="textStyle">成都市青羊区</b></td>
					<!-- <td align="center" rowspan="7"><b class="textStyle">支架信息</b></td> -->
					<td align="center" colspan="8"><b class="textStyle">13</b></td>
					<td align="center" colspan="8"><b class="textStyle">服务系统</b></td>
					<td align="center" colspan="8"><b class="textStyle">综合系统</b></td>
				</tr>
				<tr>
					<!-- <td align="center" rowspan="7"><b class="textStyle">工程信息</b></td> -->
					<td align="center" ><b class="textStyle">4</b></td>
					<td align="center" ><b class="textStyle">地区设防烈度</b></td>
					<td align="center" ><b class="textStyle">7度</b></td>
					<!-- <td align="center" rowspan="7"><b class="textStyle">支架信息</b></td> -->
					<td align="center" colspan="8"><b class="textStyle">14</b></td>
					<td align="center" colspan="8"><b class="textStyle">安装部位</b></td>
					<td align="center" colspan="8"><b class="textStyle">地下室</b></td>
				</tr>
				<tr>
					<!-- <td align="center" rowspan="7"><b class="textStyle">工程信息</b></td> -->
					<td align="center" ><b class="textStyle">5</b></td>
					<td align="center" ><b class="textStyle">地震加速度</b></td>
					<td align="center" ><b class="textStyle">0.1g</b></td>
					<!-- <td align="center" rowspan="7"><b class="textStyle">支架信息</b></td> -->
					<td align="center" colspan="8"><b class="textStyle">15</b></td>
					<td align="center" colspan="8"><b class="textStyle">安装标高</b></td>
					<td align="center" colspan="8"><b class="textStyle"></b></td>
				</tr>
				<tr>
					<!-- <td align="center" rowspan="7"><b class="textStyle">工程信息</b></td> -->
					<td align="center" ><b class="textStyle">6</b></td>
					<td align="center" ><b class="textStyle">地震类型</b></td>
					<td align="center" ><b class="textStyle">多遇地震</b></td>
					<!-- <td align="center" rowspan="7"><b class="textStyle">支架信息</b></td> -->
					<td align="center" colspan="8"><b class="textStyle">17</b></td>
					<td align="center" colspan="8"><b class="textStyle">安装标高</b></td>
					<td align="center" colspan="8"><b class="textStyle"></b></td>
				</tr>
				<tr>
					<td align="center" colspan="8"><b class="textStyle">抗震支吊架节点计算书</b></td>
				</tr>
				<tr>
					<td align="center" colspan="8"><b class="textStyle">抗震支吊架节点计算书</b></td>
				</tr>
				<tr>
					<td align="center" colspan="8"><b class="textStyle">抗震支吊架节点计算书</b></td>
				</tr>
				<!-- <tr>
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
					
						<div class="itemFirst">
							项目名称：<input type="text" />
							项目地址：<input type="text" />
						</div>
						<div class="itemFirst1">
							支吊架类型：<input type="text" />
							支吊架编号：<input type="text" />
							楼层：<input type="text" />
						</div>αEK=γηζ1ζ2αmax
					</td>
				</tr> -->
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
		</table> --%>
	</body>
	<input type="hidden" id="gcsqu" />
	<input type="hidden" id="zgcsqu" />
	<input type="hidden" id="bwsqu" />
	<input type="hidden" id="zjsqu" />
</html>
