<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/pages/head.jsp"%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>" />
<title>抗震支架部件配置</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="shortcut icon" href="resource/new/bootstrap/img/favicon.ico" />
<link rel="stylesheet" type="text/css" href="resource/ztree/css/zTreeStyle.css" />
<link rel="stylesheet" type="text/css" href="resource/new/css/dataSort.css" />
<link rel="stylesheet" type="text/css" href="resource/new/css/iconfont/iconfont.css"/>
<link rel="stylesheet" type="text/css"  href="resource/css/addStyle.css" />
<link rel="stylesheet" type="text/css"  href="resource/css/level/levelStyle.css" />
<link rel="stylesheet" type="text/css" href="resource/extjs/resources/css/ext-all.css">
<script type="text/javascript" src="resource/ztree/jquery.ztree-2.6.min.js"></script>
<script type="text/javascript" src="resource/extjs/ext-base.js"></script>
<script type="text/javascript" src="resource/extjs/ext-all.js"></script>
<script type="text/javascript" src="resource/extjs/ext-lang-zh_CN.js"></script>
<script type="text/javascript" src="resource/js/common/multiSelect.js"></script>
<script type="text/javascript">
		$(document).ready(function() {
			$(".topnav").accordion({
				accordion:true,
				speed: 500,
				closedSign: '[+]',
				openedSign: '[-]'
			});
		});
		function ac(){
		 $(this).attr("checked","true");
		}
</script> 
<style>
	.add-style1{
		background-color: #f5f5f5;
	}
	.container-fluid {
	    padding-right: 10px;
	    padding-left: 10px;
	}
	.topnav li:last-child{
		border-bottom:none;
	}
	.form-horizontal .control-group {
    margin-bottom: 8px;
	}
	.control-group h4{
	    margin-top:4px;
	}
	.control-group{
		overflow: hidden;
	}
	.form-horizontal .control-group .control-label {
	    float: left;
	    width: 140px;
	    padding-top: 5px;
	    text-align: right;
	    border:1px solid yellow;
	}
	label {
	    display: block;
	    margin-bottom: 5px;
	}
	.right-padding{padding-right:6px;}
	.td{height: 30px;width: 150px;text-align: center;} 
	.td1{height: 60px;text-align: center;vertical-align: middle;}
	.td3{text-align:left;vertical-align: middle;}
	.td2{height: 60px;width: 220px;text-align: center;vertical-align: middle;}
	.border-bottom {overflow:hidden;text-overflow:ellipsis;
    white-space:nowrap; }
    
	
}
	</style>
</head>

<body onkeydown="keyDown(event);">

	<ul class="breadcrumb" id="" style="list-style:none;">
		<li id="titleName1" style="color: #369BD7;">
			当前页面：支架模型&nbsp;&gt;
			<span> 
				<a href="javascript:void(0)"> 
					零件模型
				</a>
			</span>
		</li>
	</ul>

	<c:forEach items="${list }" var="t" varStatus="status">
		<c:if test="${status.index == 0 }">
			<input type="hidden" name="pd" id="pd" value="${t.SQU }">
			<input type="hidden" name = "ifEnd" id="ifEnd" value="${t.JD}">
			<input type="hidden" name = "bjname" id="bjname" value="${t.LBMC}">
			<input type="hidden" name="gotohome" id="gotohome" value="0">
			<input type="hidden" name="homeTypeName" id="homeTypeName"
				value="所有分类">
		</c:if>
	</c:forEach>
	<input type="hidden" name="parentId" id="parentId">
	
	<div class="container-fluid">
		<div class="row-fluid">
		<!-- 左边菜单 start-->
			<div class="leftSideBar span3">
				<ul style="width:100%;" class="parNav">
					<li class="nav-header hidden-tablet border-bottom">
						<a class="ajax-link" style="padding-left:10px;" href="javascript:void(0);" id="0" flag="1" title="所有分类" onclick="parentload(this);" >
							<font size="2">目录分类</font>
						</a>
					</li>
					<li class="border-bottom">
						<ul class="topnav">
							<c:forEach items="${list}" var="t" varStatus="status">
	                            <li style="padding-left:15px;" class="border-bottom">
	                              <a href="javascript:void(0);"  flag="1" id="${t.SQU}"  title ='${t.LBMC}' isEnd = "${t.JD}" onclick="parentload(this)">
	                             	${t.LBMC}</a>
		                            <ul class="topnav">
												
									</ul>
	                            </li>
                          
                            </c:forEach>    
						</ul>
					</li>
				</ul>
			</div>
			
			<div id="content" class="span9">
			  
					<ul class="breadcrumb">
						<li class="nav-header hidden-tablet">
						<span id="home" style="padding-top: 10px;"> 
						<a href="javascript:void(0);" onclick="goHome();"></a>
						</span>
						<!-- <a href="javascript:void(0);" ><span class="divider1"></span></a> -->
						<!-- <a href="javascript:void(0);" ><span class="divider2"></span></a>
						<a href="javascript:void(0);" ><span class="divider3"></span></a>
						<a href="javascript:void(0);" ><span class="divider4"></span></a>
						<a href="javascript:void(0);" ><span class="divider5"></span></a> -->
							<span id="home" style="padding-top: 10px;"> 
								<a href="javascript:void(0);" onclick="goHome();">目录分类</a>
								 <span class="divider">/</span>
							</span>
							<span> 
								<a href="javascript:void(0);" id="navg"> 
								<c:forEach items="${list}" var="l" varStatus="i" begin="0" end="0">
									${l.DESCB}
								</c:forEach>
								</a>
							</span> 
						</li>
						<li style="float: right;">
							<a onclick="addChildMenu();" title="新增产品分类" href="javascript:void(0);" class="btn btn-primary">
								<i class="icon-plus-sign icon-white"></i>  新增
							</a>
							<a onclick="addBatchStents();" title="批量导入" href="javascript:void(0);" class="btn btn-danger">
								<i class="icon-inbox icon-white"></i> 批量导入
							</a>
							<a onclick="dowloadMB();" title="新增" href="javascript:void(0);" class="btn btn-success">
								<i class="icon-download-alt icon-white"></i> 模板下载
							</a>
						</li>
						<li id="ss" style="float:right;margin-right: 5px;">
							<input id="input_search" type="text" style="width:200px;color:#ccc;margin-top:2px;" placeholder="关键字搜索" >
							&nbsp;
							<a style="margin-top:-8px;" class="btn " onclick="searchWord();" href="javascript:void(0);"><i class="icon-search"></i> 检索</a>
						</li>
					</ul>
			
			
				<div>
					<table id="uiGrid-uigrid" class="table" style="display: none">
						  <tbody id="box_body">
						  </tbody>
					 </table>  
				</div>
			
				<div id="pageDiv"></div>
				   
		  	</div>
		</div>
		
		<input type="hidden" value="1" id="cpsqu">
	</div>
	

 <div class="container">
	       <input type="hidden" value="1" id="typeId">
		
			<div id="example2" class="modal hide fade in  windowBoxMiddle" style="display: none;overflow: hidden;height: 500px;width: 800px">
			
				<input type="hidden" id="rsqu" name="rtype.parSqu" />
				<div class="modal-header">
					<a class="close" data-dismiss="modal">×</a>
					<h3 id="secondTitle">新增部件</h3>
				</div>
				<div class="modal-body">
				<form id="uploadForm" method="post" action="" enctype="multipart/form-data">
					<div>
						<input type="hidden" id="CPXH" name="cpxh"/>
						<input type="hidden" id="CPXL" name="cpxl"/>
						<input type="hidden" id="CPTZ" name="cptz"/>
						<input type="hidden" id="CPZM" name="cpzm"/>
						<input type="hidden" id="cpid" name="cpid"/>
					
						<input type="hidden" id="BMCPXH" name="bmcpxh"/>
						<input type="hidden" id="BMCPXL" name="bmcpxl"/>
						<input type="hidden" id="BMCPTZ" name="bmcptz"/>
						<input type="hidden" id="BMCPZM" name="bmcpzm"/>
						
						<div class="box-content" id="sort_table"  style="float: left;">
							<div><span class="right-padding">部件名称：</span><input type="text" id="bjmc" name="bjmc"/></div>
							<div><span class="right-padding">产品编码：</span><input type="text" id="cppm" name="cppm"/></div>
							<div><span class="right-padding">部件型号：</span><input type="text" id="bjxh" name="bjxh"/></div>
							<div><span class="right-padding">成本单价：</span><input type="text" id="cbdj" name="cbdj"/></div>
							<div><span class="right-padding">额定耗量：</span><input type="text" id="edhl" name="edhl"/></div>
							<div><span class="right-padding">计量单位：</span><input type="text" id="jldw" name="jldw"/></div>
							<div class="input-append"><span class="right-padding">力学参数：</span><input type="text" id="lxcs" name="lxcs" style="width: 175px"/>&nbsp;&nbsp;<span class="add-on">N</span></div>
							<!-- <input type="hidden" id="hfexample" name="azfs"/>
							<div style="margin-top: 5px;"><div style="margin-left: 2px;float: left;">安装方式：</div><div id="azfs" style="border:none;float: right;margin-right: 615px" class="multiselect"></div> 
							</div> -->
							<div style="margin-top: 10px;margin-left: -2px"><p><span class="right-padding">上传图片：</span><input type="file" name="file" /></p></div>
						</div>
						<div style="float: right;"></div>
						<!-- <div class="container-fluid">
						 <div class="row-fluid">
						     左边图片
						    <div class="span6">
						     <div class="box-content" id="sort_table">
								<form class="form-horizontal">
									<fieldset>
									
									
										部件名称：<input type="text" id="bjmc" name="bjmc"/>
									
									 
									  
									   <div class="control-group">
										<label class="control-label" for="focusedInput">产品编码：</label>
										<div class="controls">
											 <input type="text" id="cppm" name="cppm"/>
										</div>
									  </div>
									  
									   <div class="control-group">
										<label class="control-label" for="focusedInput">部件型号：</label>
										<div class="controls">
											 <input type="text" id="bjxh" name="bjxh"/>
										</div>
									  </div>
									  
									   <div class="control-group">
										<label class="control-label" for="focusedInput">成本单价：</label>
										<div class="controls">
											 <input type="text" id="cbdj" name="cbdj"/>
										</div>
									  </div>
									  
									   <div class="control-group">
										<label class="control-label" for="focusedInput">额定耗量：</label>
										<div class="controls">
											 <input type="text" id="edhl" name="edhl"/>
										</div>
									  </div>
									  
									   <div class="control-group">
										<label class="control-label" for="focusedInput">计量单位：</label>
										<div class="controls">
											 <input type="text" id="jldw" name="jldw"/>
										</div>
									  </div>
									  
									   <div class="control-group">
										<label class="control-label" for="focusedInput">上传图片：</label>
										<div class="controls">
											<input type="file" name="file" />
										</div>
									  </div>
									  
									 </fieldset>
								</form>
							 </div>
							 </div>
							</div>
					     </div> -->
					     
					 </div>
					
					</form>
					<span id="msg"></span>
				</div>
				
				<div class="modal-footer">
					<a href="javaScript:void(0);" class="btn btn-success" id="sortBtn">
					<i class="icon-ok icon-white"></i> 确定</a>
					<a href="javaScript:void(0);" class="btn" data-dismiss="modal"
						id="closeWin" ><i class="icon-remove"></i> 取消</a>
				</div>
				
	</div>
	
	<div id="exampl3" class="modal hide fade in" 
	style="display: none;top: 60%;left: 55%;  width: 500px;height: 300px;">
			<div class="modal-header">
				<a class="close" data-dismiss="modal">×</a>
				<h3 id="titleHtmlZD">批量导入&nbsp;<span id="plstent"></span></h3>
			</div>	
		
			<form id="uploadForm3" action="" method="post"   class="modal-body" enctype="multipart/form-data" style="height:160px;margin:0px;">
				<input type="file" name="userUploadFile">
				<input type="hidden" id="CPXH1" name="cpxh"/>
				<input type="hidden" id="CPXL1" name="cpxl"/>
				<input type="hidden" id="CPTZ1" name="cptz"/>
				<input type="hidden" id="CPZM1" name="cpzm"/>
			</form>
	
		  <div class="modal-footer">
			<a href="javaScript:void(0);" class="btn btn-success" id="btn_file_ok">
			<i class="icon-ok icon-white"></i> 确定</a>
			<a href="javaScript:void(0);" class="btn" data-dismiss="modal"
				id="closeWin" ><i class="icon-remove"></i> 取消</a>
		  </div>
		
	</div>
</div>
	<script type="text/javascript" src="resource/js/goodsHouse/stentsTree.js"></script>
	<script type="text/javascript" src="resource/js/scriptbreaker-multiple-accordion-1.js"></script>
<!-- 	<script type="text/javascript" src='resource/js/uiGrid.js'></script> -->
</body>

</html>
                       