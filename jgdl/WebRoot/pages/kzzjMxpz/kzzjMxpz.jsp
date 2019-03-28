<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="/pages/head.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>菜单配置</title>
	<link rel="stylesheet" type="text/css" href="resource/ztree/css/zTreeStyle.css" />
	<link rel="stylesheet" type="text/css" href="resource/new/css/iconfont/iconfont.css" />
	<link rel="stylesheet" type="text/css" href="pages/ality/css/set.css" />
	<link rel="stylesheet" type="text/css"  href="resource/css/addStyle.css" />
	<link rel="stylesheet" type="text/css"  href="resource/css/level/levelStyle.css" />
	<link rel="stylesheet" type="text/css"  href="resource/css/level/levelStyle2.css" />
	<script type="text/javascript" src="resource/ztree/jquery.ztree-2.6.min.js"></script>
	
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
	
	.zhphotoBox{
	border: 1px dashed #bbb;
    height: 150px;
    width: 150px;
    line-height: 150px;
	}
	   .border-bottom {
    
     overflow:hidden;
    text-overflow:ellipsis;
    white-space:nowrap;
}
	</style>
	
	
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
       
</head>

<body onkeydown="keyDown(event);">

	<c:forEach items="${lists}" var="t" varStatus="status">
		<c:if test="${status.index == 0 }">
			<input type="hidden" name="pd" id="pd" value="${t.SQU }">
			<input type="hidden" name = "ifEnd" id="ifEnd" value="${t.JD}">
			<input type="hidden" name="gotohome" id="gotohome" value="0">
			<input type="hidden" name="homeTypeName" id="homeTypeName"
				value="所有分类">
		</c:if>
	</c:forEach> 

	
	<ul class="breadcrumb" id="" style="list-style:none;">
		<li id="titleName1" style="color: #369BD7;">
			当前页面：支架模型&nbsp;&gt;
			<span> 
				<a href="javascript:void(0)"> 
					抗震支架关联配置
				</a>
			</span>
		</li>
	</ul>
	
	
 
  <div class="container-fluid">
	<div class="row-fluid">
	<!-- 左边菜单 start-->
		<div class="leftSideBar span3">
			<ul style="width:100%;" class="parNav">
			
				<li class="nav-header hidden-tablet border-bottom">
					<a class="ajax-link" style="padding-left:10px;"
						href="javascript:void(0);" flag="1" id="0" tier="0" title="功能目录"
						isend="1" onclick="parentload1(this);"><b>功能目录</b>
					</a>
				</li>
				
				<li class="nav-header hidden-tablet border-bottom">
						 <ul class="topnav">
						 	<c:forEach items="${lists}" var="t" varStatus="status">
	                           <li class="border-bottom">
	                             <a href="javascript:void(0);"  flag="1"  id="${t.SQU}"  ssmc="${t.LBMC}" title ='${t.LBMC}' isEnd = "${t.JD}" onclick="parentload1(this)">
	                         <!--  <i class="icon-darkgray"></i>  -->
	                               &nbsp;${t.LBMC}</a>
	                            <ul class="topnav">
											
								</ul>
	                           </li>
	                        
	                          </c:forEach>      
						</ul> 
						
					</li>
				
				<!-- <li>
					<ul class="topnav">
					         
						<div id = "menu"></div>
						
					</ul>
				</li> -->
			</ul>
			
			<!-- <div id="menuPageDiv"></div> -->
			 
		</div>
		
	<!-- 左边菜单 end-->	
	
	
	
	<!-- 右边内容 start-->	
		<div class="span9" id ="span9" style = "display:none">
			
				 <div class="container-fluid">
					 <div class="row-fluid tooltip-demo">
					     <!--左边图片-->
					     <div class="span2">
					     	<div class="photoBox" align="center" style="margin-bottom:10px;">
								<img id = "kzzjImg" src="resource/images/1208160.png" />
							</div>
					     </div>
					      <!--左边图片-->
					     
					      <!--右边内容-->
					     <div class="span10">
					     	<div class="box-content">
								<form class="form-horizontal" style="margin-bottom:-40px;">
									<fieldset>
									  <div class="control-group">
										<label class="control-label" for="focusedInput">支架名称：</label>
										<div class="controls">
											 <h4 id = "kzzjDXMC"></h4>
										</div>
									  </div>
									  
									   <!-- <div class="control-group">
										<label class="control-label" for="focusedInput">管道类型：</label>
										<div class="controls">
											  <h4 id = "kzzjGDLX"></h4>
										</div>
									  </div> -->
									  
									  
									  <div class="control-group">
										<label class="control-label" for="focusedInput">支架形式：</label>
										<div class="controls">
											  <h4 id= "kzzjZJXS"></h4>
										</div>
									  </div>
									  
									  <div class="control-group">
										<label class="control-label" for="focusedInput">支架单价：</label>
										<div class="controls">
											  <h4 id= "kzzjdj"></h4>
										</div>
									  </div>
									  
									  <!-- 隐藏安装方式值 -->
									  <div class="control-group">
										<label class="control-label" for="focusedInput"></label>
										<div class="controls">
											 <input type = "hidden" id = "azfs" value = "" />
										</div>
									  </div>
									  
									  
									  <!-- 隐藏管道类型值 -->
									  <div class="control-group">
										<label class="control-label" for="focusedInput"></label>
										<div class="controls">
											 <input type = "hidden" id = "gdlx" value = "" />
										</div>
									  </div>
									  
									 </fieldset>
								</form>
					     </div>
					   </div>
				 </div>
			</div>
			
			
			
		<div id="content" style = "display:none;">  
            
          <div id="tab_bar">   
    		  
			 <ul  style="list-style:none;overflow: hidden;">
				
				<li id = "tab1" name = "tab_li" class="well top-block f-left textPadding">
					<a  onclick = "showBjList(1,1)" href="javascript:void(0);" id = "showPtbj">
					<i class="ptbj"></i> 结构组件</a>
				</li>
				
				<li id = "tab2" name = "tab_li" class="well top-block f-left textPadding">
					<a onclick = "showBjList(1,2)" href="javascript:void(0);" id = "showZhbj">
					<i class="zhbj"></i> 承重组件</a>
				</li>
				<li id = "tab3" name = "tab_li" class="well top-block f-left textPadding">
					<a onclick = "showBjList(1,3)" href="javascript:void(0);" id = "showZhbj">
					<i class="zhbj"></i> 抗震组件</a>
				</li>
				<li id = "tab4" name = "tab_li" class="well top-block f-left textPadding">
					<a onclick = "showBjList(1,4)" href="javascript:void(0);" id = "showZhbj">
					<i class="zhbj"></i> 约束组件</a>
				</li>
				
			</ul> 
		</div>
		
		<!-- 结构组件 -->
		<div class="tab_css"  id="tab1_content" style="display: block">
			<ul class="breadcrumb overflow_hidden">
					<%-- <span id = "ptSpan">
						<button class="btn btn-large btn-warning" style = "margin-left: 250px;">普通部件明细选择</button>
					</span>
					
					<span id = "zhSpan">
						<button class="btn btn-large btn-warning" style = "margin-left: 25px;">组合部件明细选择</button>
					</span> --%>
				<li style="float: right;">
					<a style="margin-top:-1px;" id = "zhSpan" onclick="appRegisterZh()" class="btn btn-primary"  href="javascript:void(0);">
					<i class="icon-plus-sign icon-white"></i> 组合部件</a>
				</li>
				<li style="float: right;margin-right: 5px;"><!-- appRegister(); -->
					<a style="margin-top:-1px;" id = "ptSpan" onclick="appRegister()" class="btn btn-primary"  href="javascript:void(0);">
					<i class="icon-plus-sign icon-white"></i> 普通部件</a>
				</li>
				<li id="ss" style="float:right;margin-right: 5px;">
						<input id="input_search0" type="text" style="width:200px;color:#ccc;margin-bottom:-2px;" value="关键字搜索"
						onfocus="if(this.value == '关键字搜索') this.value = ''" onblur="if(this.value =='')  this.value = '关键字搜索'">
						<a style="margin-top:1px;" class="btn" onclick="searchWord();" href="javascript:void(0);">
						<i class="icon-search"></i> 检索</a>
				</li> 
			
			</ul>
			<!-- style="border-collapse: collapse;" -->
			
			<table class="table table-striped table-bordered bootstrap-datatable datatable" id = "t_table1" >
			
				
			</table>
			
			<div id="pageDiv1"></div>
		
		</div>
		
		<!-- 承重组件-->
		<div class="tab_css"  id="tab2_content" style="display: none">
			<ul class="breadcrumb overflow_hidden">
				
				
				<li style="float: right;">
					<a style="margin-top:-1px;" id = "zhSpan" onclick="appRegisterZh()" class="btn btn-primary"  href="javascript:void(0);">
					<i class="icon-plus-sign icon-white"></i> 组合部件</a>
				</li>
				<li style="float: right;margin-right: 5px;"><!-- appRegister(); -->
					<a style="margin-top:-1px;" id = "ptSpan" onclick="appRegister()" class="btn btn-primary"  href="javascript:void(0);">
					<i class="icon-plus-sign icon-white"></i> 普通部件</a>
				</li>
				
				<li id="ss" style="float:right;margin-right: 5px;">
						<input id="input_search1" type="text" style="width:200px;color:#ccc;margin-bottom:-2px;" value="关键字搜索"
						onfocus="if(this.value == '关键字搜索') this.value = ''" onblur="if(this.value =='')  this.value = '关键字搜索'">
						<a class="btn" onclick="searchWord();" href="javascript:void(0);">
						<i class="icon-search"></i> 检索</a>
				</li> 
			</ul>
			
			<table class="table table-striped table-bordered bootstrap-datatable datatable" id = "t_table2" style="border-collapse: collapse;">
			</table>
			
			<div id="pageDiv2"></div>
		
		</div>
		
		
		<!-- 抗震组件-->
		<div class="tab_css"  id="tab3_content" style="display: none">
			<ul class="breadcrumb overflow_hidden">
				
				
				
				<li style="float: right;">
					<a style="margin-top:-1px;" id = "zhSpan" onclick="appRegisterZh()" class="btn btn-primary"  href="javascript:void(0);">
					<i class="icon-plus-sign icon-white"></i> 组合部件</a>
				</li>
				<li style="float: right;margin-right: 5px;"><!-- appRegister(); -->
					<a style="margin-top:-1px;" id = "ptSpan" onclick="appRegister()" class="btn btn-primary"  href="javascript:void(0);">
					<i class="icon-plus-sign icon-white"></i> 普通部件</a>
				</li>
				
				<li id="ss" style="float:right;margin-right: 5px;">
						<input id="input_search2" type="text" style="width:200px;color:#ccc;margin-bottom:-2px;" value="关键字搜索"
						onfocus="if(this.value == '关键字搜索') this.value = ''" onblur="if(this.value =='')  this.value = '关键字搜索'">
						<a class="btn" onclick="searchWord();" href="javascript:void(0);">
						<i class="icon-search"></i> 检索</a>
				</li> 
			</ul>
			
			<table class="table table-striped table-bordered bootstrap-datatable datatable" id = "t_table3" style="border-collapse: collapse;">
			</table>
			
			<div id="pageDiv3"></div>
		
		</div>
		
		
		<!--约束组件 -->
		<div class="tab_css"  id="tab4_content" style="display: none">
			<ul class="breadcrumb overflow_hidden">
				
				
				
				<li style="float: right;">
					<a style="margin-top:-1px;" id = "zhSpan" onclick="appRegisterZh()" class="btn btn-primary"  href="javascript:void(0);">
					<i class="icon-plus-sign icon-white"></i> 组合部件</a>
				</li>
				<li style="float: right;margin-right: 5px;"><!-- appRegister(); -->
					<a style="margin-top:-1px;" id = "ptSpan" onclick="appRegister()" class="btn btn-primary"  href="javascript:void(0);">
					<i class="icon-plus-sign icon-white"></i> 普通部件</a>
				</li>
				
				<li id="ss" style="float:right;margin-right: 5px;">
						<input id="input_search3" type="text" style="width:200px;color:#ccc;margin-bottom:-2px;" value="关键字搜索"
						onfocus="if(this.value == '关键字搜索') this.value = ''" onblur="if(this.value =='')  this.value = '关键字搜索'">
						<a class="btn" onclick="searchWord();" href="javascript:void(0);">
						<i class="icon-search"></i> 检索</a>
				</li> 
			</ul>
			
			<table class="table table-striped table-bordered bootstrap-datatable datatable" id = "t_table4" style="border-collapse: collapse;">
			</table>
			
			<div id="pageDiv4"></div>
		
		</div>
		
	</div><!-- content结束 -->
			
</div>

<!-- 右边内容 end-->
<!-- main -->

		<div class="span9" id="zjCount" style="display: block">
			标签页
			<ul class="nav nav-tabs">
				<li class="active">
					<a href="#tab11" data-toggle="tab" code="5134" flag="0" controlType=""  onclick="setBjfl(5)">
						<i class="icon-home"></i>&nbsp;支架
					</a>
				</li>
			</ul>
			<div class="tab-content">
				<div class="tab-pane active" id="tab11">
					
				</div>
				
			</div>
		</div> 
		<!-- main  end -->
</div>
</div>		
	
	<!--新增普通部件弹出框内容 -->	
	<!-- 普通部件选择框 -->
				
	
	<!-- 弹出框内容 11111111-->	
	<div class="container" style="width: 1050px;height: 550px;margin-top: 10">
		<div id="examplept" class="modal hide fade in windowBoxBig" style="display: none;width: 1050px;height: 450px;left: 560px">
			<div class="modal-header">
				<a class="close" data-dismiss="modal"  >×</a> <h3>普通部件明细</h3>
			</div>
			
			<div class="modal-body select-box" id = "pt-select-box" style="margin-top:10px;width: 900px;margin-left: 60px;height: 300px">
							
				<%-- <div class="select-res1" align="center" style="margin-top1:10px;">
					<span>
						<button class="btn btn-large ">产品明细选择</button>
					</span>
				</div> --%>
				
			
					<!-- 弹出产品明细选择关闭键 -->
					
					<div class="wrap">
						<ul class="first"></ul>
						<ul class="second"></ul>
						<ul class="third"></ul>
						 <ul class="foure"></ul>
						<ul class="five"></ul> 
					</div>
					<!-- <div class="control-group" style="margin-top: 5px">
						
							<div class="controls">
								数量： <input type="text" placeholder="请输部件入数量..." id = "bjsl" value ="1"/>
							</div>
					 </div> -->
				
				
			
		  <!-- 隐藏结果内容 -->		
								
		<!-- 弹出框内容 -->						
			</div>
			
			<div class="bottomBtnBox">
			数量： <input type="text" placeholder="请输部件入数量..." style="width:40px;margin-top: 10px;" id = "bjsl" value ="1"/>
				<a title="确定" href="javaScript:void(0);" class="btn btn-success"  id="appBtnBj" style="margin:14px 0;">
					<i class="icon-ok icon-white"></i> 确定
				</a> 
				<a title="取消" href="javaScript:void(0);" class="btn" data-dismiss="modal" id="closeWin" style="margin:14px;" > 
					<i class="icon-remove"></i> 取消
				</a>
			</div>			
		</div>
		
	</div>
	
	<!-- 弹出框内容 11111111-->	
	<div class="container" style="width: 600px;height: 550px;margin-top: 10">
		<div id="examplezh" class="modal hide fade in windowBoxBig" style="display: none;width: 600px;height: 450px;left: 750px">
			<div class="modal-header">
				<a class="close" data-dismiss="modal"  >×</a> <h3>组合部件明细</h3>
			</div>
			
			<div class="modal-body select-box2" id = "zh-select-box" style="margin-top:10px;width: 400px;margin-left: 90px;height: 300px">
							
				
					<!-- 弹出产品明细选择关闭键 -->
					
					
					<div class="wrap">
						<ul class="zhfirst" style="width:400px"></ul>
						
					</div>
					
				<!-- 	<div class="control-group" style="margin-top: 5px">
						<label class="control-label" for="focusedInput"></label>
							<div class="controls">
								数量：<input type="text" placeholder="请输部件入数量..." id = "zhsl" value ="1"/>
							</div>
					 </div> -->
				
				
			
		  <!-- 隐藏结果内容 -->		
								
		<!-- 弹出框内容 -->						
			</div>
			
			<div class="bottomBtnBox">
				数量：<input type="text" placeholder="请输部件入数量..." id = "zhsl" value ="1" style="width:40px;margin-top: 10px;"/>
				<a title="确定" href="javaScript:void(0);" class="btn btn-success"  id="appBtnZh" style="margin:14px 0;">
					<i class="icon-ok icon-white"></i> 确定
				</a> 
				<a title="取消" href="javaScript:void(0);" class="btn" data-dismiss="modal" id="closeWin1" style="margin:14px;" > 
					<i class="icon-remove"></i> 取消
				</a>
			</div>			
		</div>
		
	</div>
	
	
	
	
	
	<input type="hidden" id = "zjlxsqu" value="1">
	<input type="hidden" id = "zjqu" >
	<input type="hidden" id = "type" >
	<input type="hidden" id = "bjfl" >
	<!-- //<input type="hidden" id = "zjsq" > -->
	<!--新增组合部件弹出框内容 -->	
	<div class="container" style="width: 600px;height: 550px;margin-top: 10">
		 <div id="zhexample" class="modal hide fade in windowBoxBig" style="display: none;width: 550px;height: 450px;left: 70%">
			<div class="modal-header">
				<a class="close" data-dismiss="modal">×</a> <h3 id="zhuserTitleName">部件明细</h3>
			</div>
			
			<div class="modal-body" id = "modal-body">
			    <div id="hideConBox" style="display1:none;">				
					   <div class="span5">
					     	<div class="box-content">
								
									<fieldset>								 
									
									   <div class="control-group">
										<label class="control-label" for="focusedInput">部件名称：</label>
										<div class="controls">
											 <input type="text" class="disabled" id="bjmc" disabled/>
										</div>
									  </div>
									  
									   <div class="control-group" id = "forCPZMMC">
										<label class="control-label" for="focusedInput">部件类型：</label>
										<div class="controls">
											 <input type="text" class="disabled" id="bjlx" disabled/>
										</div>
									  </div>
									 
									  <div class="control-group">
										<label class="control-label" for="focusedInput">单位</label>
										<div class="controls">
											 <input type="text" class="disabled" id="bjdw"  disabled/>
										</div>
									  </div>
									  <div class="control-group">
										<label class="control-label" for="focusedInput">数量</label>
										<div class="controls">
											 <input type="text" class="disabled" id="bjlxsl"/>
										</div>
									  </div>
								 	
									 </fieldset>
								
					     </div>
					   </div>
<!-- end span5 -->				
				</div>
			</div>
			
			 <div class="bottomBtnBox">
				<a title="确定" href="javaScript:void(0);" class="btn btn-success" id="zhappBtn" style="margin:14px 0;">
					<i class="icon-ok icon-white"></i> 确定
				</a> 
				<a title="取消" href="javaScript:void(0);" class="btn" data-dismiss="modal" id="closeWin" style="margin:14px;"> 
					<i class="icon-remove"></i> 取消
				</a>
			</div>	
								
		<!-- 弹出框内容 -->						
			</div>
			
					
	</div> 


	<script type="text/javascript" src="resource/js/kzzjMxpz/kzzjMxpz.js"></script>
	<script type="text/javascript" src="resource/js/scriptbreaker-multiple-accordion-1.js"></script>
  <script type="text/javascript" src="resource/js/kzzjMxpz/kzzjPtbjxz.js"></script> 
  <script type="text/javascript" src="resource/js/kzzjMxpz/kzzjZhbjxz.js"></script>

</body>
</html>
