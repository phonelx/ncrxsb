<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/pages/head.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<base href="<%=basePath%>">

	<title>项目配置页面</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="resource/css/addStyle.css">
	<style>
	.form-horizontal .control-group{
		    margin-bottom: 12px;
	}
	.input_box {
	    padding-right: 10px;
	    border: 1px solid #ccc;
	    border-radius : 5px;
	}
	legend{
		margin-bottom: 0px;
	}
 	.control-label{
		width: 100px !important;
	} 
	.controls {
    margin-left: 100px !important;
    }
	</style>
</head>
  
<body>
	<ul class="breadcrumb" style="list-style:none;">
		<li id="titleName" style="color: #369BD7;">
			当前页面：项目配置管理&nbsp;&gt;
			<span id="home"> 
				<a id="head_title" href="javascript:void(0)"> 
					项目基本配置
				</a>
			</span>
		</li>
		
		<li style="float: right;">
			<a style="margin-top:-3px;" title="新增" class="btn btn-primary" onclick="showAddModal();" href="javascript:void(0);">
			<i class="icon-plus-sign icon-white"></i> 新增</a>
		</li>
		<li id="ss" style="float:right;margin-right: 5px;">
			<input id="input_search" type="text" class="inputText" value="关键字搜索" onfocus="if(this.value == '关键字搜索') this.value = ''" onblur="if(this.value =='')  this.value = '关键字搜索'"/>
			&nbsp;&nbsp; 
			<a style="margin-top:-10px;" class="btn" onclick="doSearch();" href="javascript:void(0);">
			<i class="icon-search"></i> 检索</a>
		</li>
	</ul>
	
	<div style="padding:0 30px;text-align: center;">
		<div id="projectGrid"></div>
	</div>
	
	<!-- 新增或者修改模态框 -->
	<div class="container">
		<form method="post" id="objectForm" class="form-horizontal" enctype="multipart/form-data">
			<div id="objectModal" class="modal hide fade in windowBoxBig" style="display: none;height: 600px;width: 880px;">
				<div class="modal-header">
					<a class="close" data-dismiss="modal" id='clBtn'>×</a> 
					<h3 id="htmlTitle">新增项目</h3>
				</div>
				<div class="modal-body">
				
					<div class="container-fluid" style="width: 800px;">
						 <div class="row-fluid tooltip-demo" style="margin-left:-5px;width: 800px;">
							 <input type="hidden" id="squ" name="squ" placeholder="id" value=""/>
						     <!--左边内容-->
						     <div class="span5">
						     	<div class="box-content" style="width: 360px;">
										<fieldset>
											<div class="input_box" style="height: 300px;">
												<div style="height: 300px;width: 20px;float: left;border-right: 1px solid #ccc;text-align: center;background-color: #6CCAC9;">
												工程信息
												</div>
												<div style="width: 300px;float: left;margin-top: 10px;">
													<div class="control-group">
														<label class="control-label" for="focusedInput">工程名称：</label>
														<div class="controls">
														   <input type="text" id="gcmc" class="input" name="gcmc" placeholder=""
																datatype="s1-18" errormsg="工程名称至少1个字符,最多18个字符！" required="true"/>
														</div>
													</div>
													<div class="control-group">
														<label class="control-label" for="focusedInput">建筑类别：</label>
														<div class="controls">
															<select id="jzlb" class="input" name="jzlb" required="true">
															<!-- 	<option value="">—— ——请选择—— ——</option>
																<option value="甲类建筑">甲类建筑</option>
																<option value="乙类建筑">乙类建筑</option>
																<option value="丙类建筑">丙类建筑</option> -->
															</select>
														<!--    <input type="text" id="jzlb" class="input" name="jzlb" placeholder=""
																datatype="s1-18" errormsg="工程名称至少1个字符,最多18个字符！" required="true"/> -->
														</div>
													</div>
													<div class="control-group">
														<label class="control-label" >地区设防烈度：</label>
														<div class="controls">
															<select id="dqsfld" class="input" name="dqsfld" required="true">
															<!-- 	<option value="">—— ——请选择—— ——</option>
																<option value="6">6度</option>
																<option value="7">7度</option>
																<option value="8">8度</option>
																<option value="9">9度</option>
																<option value="其他">其他</option> -->
															</select>
														    <!-- <input type="text" id="dqsfld" class="input" name="dqsfld" placeholder="" required="true"
															datatype="/^-?[1-9]+(\.\d+)?$|^-?0(\.\d+)?$|^-?[1-9]+[0-9]*(\.\d+)?$/"  errormsg="地区设防烈度请输入数字！"/> -->
														</div>
													</div>
													
													<div class="control-group">
														<label class="control-label" for="focusedInput">地震类型：</label>
														<div class="controls">
															<select id="dzlx" class="input" name="dzlx" required="true">
															<!-- 	<option value="">—— ——请选择—— ——</option>
																<option value="多遇地震">多遇地震</option>
																<option value="罕遇地震">罕遇地震</option> -->
															</select>
													<!-- 	  <input type="text" id="dzlx" class="input" name="dzlx" placeholder="" required="true"> -->
														</div>
													</div>
													
													<div class="control-group">
														<label class="control-label" for="focusedInput">工程地址：</label>
														<div class="controls">
														   <input type="text" id="gcdz" class="input" name="gcdz" placeholder="" 
															 required="true"/>
														</div>
													</div>
													
													<div class="control-group">
														<label class="control-label" for="focusedInput">建筑高度：</label>
														<div class="controls">
														   <input type="text" id="jzgd" class="input" name="jzgd" placeholder=""
								 							datatype="/^-?[1-9]+(\.\d+)?$|^-?0(\.\d+)?$|^-?[1-9]+[0-9]*(\.\d+)?$/"  errormsg="建筑高度请输入数字！" required="true">
														</div>
													</div>
													
													<div class="control-group">
														<label class="control-label" for="focusedInput">地震加速度：</label>
														<div class="controls">
														   <input type="text" id="dzjsd" class="input" name="dzjsd" placeholder="" required="true"
															datatype="/^-?[1-9]+(\.\d+)?$|^-?0(\.\d+)?$|^-?[1-9]+[0-9]*(\.\d+)?$/"  errormsg="地震加速度请输入数字！"/>
														</div>
													</div>
												</div>
											</div>
											
											<div class="input_box" style="margin-top: 15px;height: 130px;">
												<div style="height: 130px;width: 20px;float: left;border-right: 1px solid #ccc;text-align: center;background-color: #6CCAC9;">
												管道信息
												</div>
												<div style="width: 300px;float: left;margin-top: 10px;">
												<div class="control-group">
													<label class="control-label" for="focusedInput">管道材质：</label>
													<div class="controls">
													   <input type="text" id="gdcz" class="input" name="gdcz" placeholder="" required="true">
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label" for="focusedInput">管道类型：</label>
													<div class="controls">
													<!--    <input type="text" id="gdlx" class="input" name="gdlx" placeholder="" required="true"/> -->
													   <select id="gdlx" class="input" name="gdlx" required="true"></select>
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label" for="focusedInput">管道规格：</label>
													<div class="controls">
													 <input type="text" id="gdgg" class="input" name="gdgg" placeholder="" required="true"/>
													</div>
												</div>
												</div>
											</div>
						
										</fieldset>
								</div>
						     </div>
						     <!--左边内容-->
						     
						      <!--右边内容-->
						     <div class="span5">
						     	<div class="box-content" style="margin-left: 50px;">
										<fieldset>
											<div class="input_box" style="width: 350px;height: 450px;">
											<div style="height: 450px;width: 20px;float: left;border-right: 1px solid #ccc;text-align: center;background-color: #6CCAC9;">
												支架信息
												</div>
												<div style="width: 300px;float: left;margin-top: 10px;">
												<div class="control-group">
													<label class="control-label" for="focusedInput">安装部位：</label>
													<div class="controls">
													  <input type="text" id="azbw" class="input" name="azbw" placeholder="" required="true">
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label" for="focusedInput">服务系统：</label>
													<div class="controls">
													  <input type="text" id="fwxt" class="input" name="fwxt" placeholder="" required="true">
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label" for="focusedInput">结构连接方式：</label>
													<div class="controls">
													 <input type="text" id="jgljfs" class="input" name="jgljfs" placeholder="" required="true"/>
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label" for="focusedInput">设置状态：</label>
													<div class="controls">
													 <input type="text" id="szzt" class="input" name="szzt" placeholder="" required="true"/>
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label" for="focusedInput">型号：</label>
													<div class="controls">
													 <input type="text" id="xh" class="input" name="xh" placeholder="" required="true"/>
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label" for="focusedInput">安装标高：</label>
													<div class="controls">
													   <input type="text" id="azbg" class="input" name="azbg" placeholder="" required="true"
														datatype="/^-?[1-9]+(\.\d+)?$|^-?0(\.\d+)?$|^-?[1-9]+[0-9]*(\.\d+)?$/"  errormsg="安装标高请输入数字！"/>
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label" for="focusedInput">编号：</label>
													<div class="controls">
													   <input type="text" id="bh" class="input" name="bh" placeholder="" required="true">
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label" for="focusedInput">抗震支撑数量：</label>
													<div class="controls">
													   <input type="text" id="kzzcsl" class="input" name="kzzcsl" placeholder="" required="true"
														datatype="n1-10"  errormsg="抗震支撑数量请输入数字！"/>
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label" for="focusedInput">斜撑角度：</label>
													<div class="controls">
													  <input type="text" id="xcjd" class="input" name="xcjd" placeholder="" required="true"
							                            datatype="/^-?[1-9]+(\.\d+)?$|^-?0(\.\d+)?$|^-?[1-9]+[0-9]*(\.\d+)?$/"  errormsg="斜撑角度请输入数字！"/>
													</div>
												</div>
												
												<div class="control-group">
													<label class="control-label" for="focusedInput">约束方式规格：</label>
													<div class="controls">
													 <input type="text" id="ysfsjgg" class="input" name="ysfsjgg" placeholder="" required="true"/>
													</div>
												</div>
											</div>
											</div>
										</fieldset>
								</div>
							</div>
						</div>
					</div>
			</div>
			 <div class="bottomBtnBox">
				<span id="msg" style="margin-left:30px;"></span>
				<input type="submit" value="确定" class="btn btn-success" id="subBtn" style="display: none;"/>
				<a title="确定" href="javaScript:void(0);" class="btn btn-success" onclick="subBtnClick()" style="margin:14px 0;">
					<i class="icon-ok icon-white"></i> 确定
				</a> 
				<a title="取消" href="javaScript:void(0);" class="btn" data-dismiss="modal" id="closeWin" style="margin:14px;"> 
					<i class="icon-remove"></i> 取消
				</a>
			</div> 	 
			 
		</form>
	</div>
</body>
<script type="text/javascript" src="resource/js/projectConfig/projectConfigIndex.js"></script>
</html>