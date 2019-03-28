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
	<link rel="stylesheet" type="text/css" href="resource/ztree/css/zTreeStyle.css" />
	<link rel="stylesheet" type="text/css" href="pages/ality/css/set.css" />
	<link rel="stylesheet" type="text/css"  href="resource/css/addStyle.css" />
	<script type="text/javascript" src="resource/ztree/jquery.ztree-2.6.min.js"></script>
	
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
	#tree .border-bottom a{
    margin-left: 10px;
    }
    .msg_div{
    	display: none;
    }
    /*安装吊高，部位标高  */
    #azdg,#bwbg{
    	width: 150px;
    }
	</style>
</head>
  
<body>
	<!-- 顶端导航-->
	<ul class="breadcrumb" style="list-style:none;">
		<li id="titleName" style="color: #369BD7;">
			当前页面：项目管理&nbsp;&gt;
			<span id="home"> 
				<span id="head_title"> 
					项目配置
				</span>
			</span>
			<span >项目  <span id="L1SQU"> </span> </span>
			<span > 子项目 <span id="L2SQU"> </span> </span>
			<span > 部位 <span id="L3SQU"> </span> </span>
			<span > 专业 <span id="L4SQU"> </span> </span>
			<span > 支架 <span id="L5SQU"> </span> </span>
			<span >等级：<span id="L">0</span> </span>
		</li>
	</ul>
	<!-- 顶端导航结束 /////////////////////////////////////////////////////////////////////////////////////////////////-->
	
	<!-- 页面内容-->	
	<div class="container-fluid">
		<div class="row-fluid">
			<!-- 树菜单 start-->
			<div class="leftSideBar span3">
				<ul style="width:100%;" class="parNav">
					<li class="nav-header hidden-tablet border-bottom">
						<a class="ajax-link" style="padding-left:10px;" id="treeTop"
							href="javascript:void(0);" onclick="loadTree('',0)"><b>[工程项目目录]</b>
						</a>
					</li>
					<!-- 工程项目分页 -->
					<li class="nav-header hidden-tablet border-bottom">
						<ul class="topnav" id="tree"></ul>
					</li>
					<li>
						<div id="pageInfo"></div>
					</li>
				</ul>
			</div>
			<!-- 树菜单 end//////////////////////////////////////////////////////////////////////////////////////////////////////////////-->
			
			<!-- 右边内容 start-->	
			<div class="span9" style="margin-left: 20px;">
				<!-- 信息展示 -->
				<div>
					<!-- 项目信息 -->
					<div class="msg_div project_msg">
						<div class="projectCon">
							<div class="projectTitle">项目信息</div>
							<div class="row-fluid ">
							</div>
						</div>
					</div>
					
					<!-- 子项目信息 -->
					<div class="msg_div child_msg">
						<div class="projectCon">
							<div class="projectTitle">子项目信息</div>
							<div class="row-fluid"></div>
						</div>
					</div>
					
					<div class="row-fluid">
						<!-- 部位信息 -->
						<div class="msg_div site_msg span6">
							<div class="projectCon">
								<div class="projectTitle">部位信息</div>
								<div class="row-fluid"></div>
							</div>
						</div>
	
						<!-- 支架信息 -->
						<div class="msg_div holder_msg span6">
							<div class="projectCon">
								<div class="projectTitle">支架信息</div>
								<div class="row-fluid"></div>
							</div>
						</div>
					</div>
					
					<!-- 数据统计信息 -->
					<div class="msg_div count_msg">
					</div>
				</div>


				<ul class="breadcrumb" id="listTitle" style="list-style:none;line-height: 27px;height: 27px;">
					<li style="float: right;"><a style="margin-top:-3px;"
						title="新增" class="btn btn-primary" onclick="showAddModal();"
						href="javascript:void(0);"> <i
							class="icon-plus-sign icon-white"></i> 新增
					</a></li>
				</ul>

				<!-- 	<li id="ss" style="float:right;margin-right: 5px;">
					<input id="input_search" type="text" class="inputText" value="关键字搜索"
					onfocus="if(this.value == '关键字搜索') this.value = ''"
					onblur="if(this.value =='')  this.value = '关键字搜索'" /> &nbsp;&nbsp;
					<a style="margin-top:-10px;" class="btn" onclick="doSearch();"
					href="javascript:void(0);">
						<i class="icon-search"></i> 检索
					</a>
				</li> -->
				
				<!-- 项目列表 -->
				<div style="padding:0;text-align: center;" class="list_div project_list">
					<div id="projectGrid"></div>
				</div>
				<!-- 子项目列表 -->
				<div style="padding:0;text-align: center;display: none;" class="list_div child_list">
					<div id="childGrid"></div>
				</div>
				<!-- 部位列表 -->
				<div style="padding:0;text-align: center;display: none;" class="list_div site_list">
					<div id="siteGrid"></div>
				</div>
				<!-- 支架列表 -->
				<div style="padding:0;text-align: center;display: none;" class="list_div holder_list">
					<div id="holderGrid"></div>
					<div id="pageInfo2"></div>
				</div>
				<!-- 部件列表 -->
				<div style="padding:0;text-align: center;display: none;" class="list_div parts_list">
					<div class="tabbable">
							<ul class="nav nav-tabs">
								<li class="active"><a href="#tab1" data-toggle="tab" id="1stTemp" onclick="loadZJListByType(1)">结构组件</a></li>
								<li><a href="#tab2" data-toggle="tab" onclick="loadZJListByType(2)">承重组件</a></li>
								<li><a href="#tab3" data-toggle="tab" onclick="loadZJListByType(3)">抗震组件</a></li>
								<li><a href="#tab4" data-toggle="tab" onclick="loadZJListByType(4)">约束组件</a></li>
							</ul>
						</div>
					<!-- 组件列表 -->
			 		<div id="zjList"></div>
			 		<div id="pageInfo3"></div>
				</div>
			</div>
			<!-- 右边内容 end/////////////////////////////////////////////////////////////////////////////////////////////////////////////-->
			
	  	</div>
	</div>
	<!-- 页面内容/////////////////////////////////////////////////////////////////////////////////////////////////-->	
	
	
	<!-- 模态框 -->
	<div class="container">
		<!-- 项目表单模态框 -->
		<form method="post" id="objectForm" class="form-horizontal" enctype="multipart/form-data">
			<div id="objectModal" class="modal hide fade in"
				style="display: none;height: 500px;width: 440px;">
				<div class="modal-header">
					<a class="close" data-dismiss="modal" id='clBtn'>×</a>
					<h3 id="htmlTitle">新增项目</h3>
				</div>
				<div class="modal-body">
					<div class="container-fluid" style="width: 400px;">
						<div class="row-fluid tooltip-demo" style="margin-left:-5px;width: 400px;">
							<input type="hidden" id="project_squ" name="project_squ" placeholder="id" value="" />
							<!--左边内容-->
							<div class="span5">
								<div class="box-content" style="width: 360px;">
									<fieldset>
										<div class="input_box" style="height: 340px;">
											<div style="width: 300px;float: left;margin-top: 10px;">
												<div class="control-group">
													<label class="control-label" for="focusedInput">项目名称：</label>
													<div class="controls">
														<input type="text" id="xmmc" class="input" name="xmmc"
															placeholder="" datatype="s1-18"
															errormsg="项目名称至少1个字符,最多18个字符！" required="true" />
													</div>
												</div>
												<div class="control-group">
													<label class="control-label" for="focusedInput">项目编号：</label>
													<div class="controls">
														<input type="text" id="xmbh" class="input" name="xmbh"
															placeholder="" datatype="s5-5"
															errormsg="项目编号5位项目简拼！" required="true" />
													</div>
												</div>
												<div class="control-group">
													<label class="control-label" for="focusedInput">工程地区：</label>
													<div class="controls">
														<input type="text" id="xmdz" class="input" name="xmdz"
															placeholder="" datatype="s1-10"
															errormsg="工程地区至少1个字符,最多10个字符！" required="true" />
													</div>
												</div>
												<div class="control-group">
													<label class="control-label" for="focusedInput">项目类型：</label>
													<div class="controls">
														<input type="text" id="xmlx" class="input" name="xmlx"
															placeholder="" required="true" nullmsg />
													</div>
												</div>
												<div class="control-group">
													<label class="control-label" for="focusedInput">项目面积：</label>
													<div class="controls">
														<input type="text" id="xmmj" class="input" name="xmmj"
															placeholder="" required="true" nullmsg />
													</div>
												</div>

												<div class="control-group">
													<label class="control-label" for="dzlx">地震类型：</label>
													<div class="controls">
														<select id="dzlx" class="input" name="dzlx"
															required="true" nullmsg>
														</select>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">地区设防烈度：</label>
													<div class="controls">
														<select id="dzsfld" class="input" name="dzsfld"
															required="true" nullmsg>
														</select>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">地震加速度：</label>
													<div class="controls">
														<!-- 	 <input type="text" id="dzjsd" class="input" name="dzjsd" placeholder="" required="true"
														datatype="/^-?[1-9]+(\.\d+)?$|^-?0(\.\d+)?$|^-?[1-9]+[0-9]*(\.\d+)?$/"  errormsg="地震加速度请输入数字！"/> -->
														<select id="dzjsd" class="input" name="dzjsd"
															placeholder="" required="true" nullmsg>
														</select>
													</div>
												</div>

												<div class="control-group">
													<label class="control-label" for="focusedInput">系数最大值：</label>
													<div class="controls">
														<input type="text" id="alphaMax" class="input"
															name="alphaMax" placeholder="" required="true" nullmsg
															readonly="readonly" />
													</div>
												</div>

											</div>
										</div>
									</fieldset>
								</div>
							</div>
							<!--左边内容-->
						</div>
					</div>
				</div>
				<div class="bottomBtnBox">
					<span id="projectMsg" style="margin-left:30px;"></span> 
					<input type="submit" value="确定" class="btn btn-success" id="projectSubBtn" style="display: none;" /> 
						<a title="确定" href="javaScript:void(0);" class="btn btn-success"
							onclick="subBtnClick('project')" style="margin:14px 0;"> 
						<i class="icon-ok icon-white"></i> 确定
					</a> 
					<a title="取消" href="javaScript:void(0);" class="btn" data-dismiss="modal" style="margin:14px;"> 
						<i class="icon-remove"></i> 取消
					</a>
				</div>
		</form>
	</div>


	<!--//////////////////////////////////////////////////////// 子项目表单模态框 /////////////////////////////////////////////////////-->
	<div class="container">
		<form method="post" id="childForm" class="form-horizontal"
			enctype="multipart/form-data">
			<div id="childModal" class="modal hide fade in"
				style="display: none;height: 300px;width: 440px;">
				<div class="modal-header">
				<a class="close" data-dismiss="modal" id='childclBtn'>×</a>
					<h3 id="childHtmlTitle">新增子项目</h3>
				</div>
				<div class="modal-body">
					<div class="container-fluid" style="width: 400px;">
						<div class="row-fluid tooltip-demo" style="margin-left:-5px;width: 400px;">
							<div class="box-content" style="width: 360px;">
								<fieldset>
									<div class="input_box" style="height: 160px;">
										<div style="width: 300px;float: left;margin-top: 10px;">
											<input type="hidden" id="child_squ" name="child_squ" placeholder="id" value="" />
											<div class="control-group">
												<label class="control-label">子项目名称：</label>
												<div class="controls">
													<input type="text" id="zdwxmmc" class="input"
														name="zdwxmmc" placeholder="" datatype="s2-18"
														errormsg="子项目名称至少2个字符,最多18个字符！" required="true" />
												</div>
											</div>
											<div class="control-group">
												<label class="control-label" for="focusedInput">建筑高度：</label>
												<div class="controls">
													<input type="text" id="jzgd" class="input" name="jzgd"
														placeholder="" required="true" />
												</div>
											</div>
												<div class="control-group">
													<label class="control-label" for="focusedInput">子项目编号：</label>
													<div class="controls">
														<input type="text" id="childNum" class="input" name="childNum"
															placeholder="" datatype="s2-2"
															errormsg="子项目编号2位简拼！" required="true" />
													</div>
												</div>
											<div class="control-group">
												<label class="control-label" for="focusedInput">建筑类别：</label>
												<div class="controls">
												<select  id="jzlb" name="jzlb" style="margin-bottom: 9px;height: 28px;border-radius: 3px;width: 220px;" >
													<option value="甲类建筑">甲类建筑</option>
													<option value="乙类建筑">乙类建筑</option>
												</select>
												</div>
											</div>
										</div>
									</div>
								</fieldset>
							</div>
						</div>
					</div>
				</div>
				<div class="bottomBtnBox">
					<span id="childMsg" style="margin-left:30px;"></span> 
						<input type="submit" value="确定" class="btn btn-success" id="childSubBtn"
						style="display: none;" /> 
						<a title="确定" href="javaScript:void(0);" class="btn btn-success" onclick="subBtnClick('child')"
						style="margin:14px 0;"> <i class="icon-ok icon-white"></i> 确定
					</a> 
					<a title="取消" href="javaScript:void(0);" class="btn" data-dismiss="modal" style="margin:14px;"> 
						<i class="icon-remove"></i> 取消
					</a>
				</div>
		</form>
	</div>
	
	<!-- //////////////////////////////////////////////////////////部位表单模态框 //////////////////////////////////////////////////-->
	<div class="container">
		<form method="post" id="siteForm" class="form-horizontal" enctype="multipart/form-data">
			<div id="siteModal" class="modal hide fade in" style="display: none;height: 370px;width: 440px;">
				<div class="modal-header">
					<a class="close" data-dismiss="modal" id='siteclBtn'>×</a>
					<h3 id="siteHtmlTitle">新增部位</h3>
				</div>
				<div class="modal-body">
					<div class="container-fluid" style="width: 400px;">
						<div class="row-fluid tooltip-demo" style="margin-left:-5px;width: 400px;">
							<div class="box-content" style="width: 360px;">
								<fieldset>
									<div class="input_box" style="height: 210px;">
										<div style="width: 300px;float: left;margin-top: 10px;">
											<input type="hidden" id="site_squ" name="site_squ" placeholder="id" value="" />
											<div class="control-group">
												<label class="control-label">部位名称：</label>
												<div class="controls">
													<input type="text" id="bwmc" class="input"
														name="bwmc" placeholder="" datatype="s2-18"
														errormsg="子项目名称至少2个字符,最多18个字符！" required="true" />
												</div>
											</div>
												<div class="control-group">
													<label class="control-label" for="focusedInput">部位编号：</label>
													<div class="controls">
														<input type="text" id="siteNum" class="input" name="siteNum"
															placeholder="" datatype="s2-2"
															errormsg="部位编号2位简拼！" required="true" />
													</div>
												</div>
											<div class="control-group">
												<label class="control-label">安装吊高：</label>
												<div class="input-append">
													<input type="text" id="azdg" class="input" name="azdg"
														placeholder="" required="true" datatype="/^(?!0{1,4})\d{1,4}|10{4}|0$/"
														 errormsg="安装吊高介于0到10000毫米之间11"/>
														<span class="add-on">mm</span>
												</div>
											</div>
											<div class="control-group">
												<label class="control-label">部位标高：</label>
												<div class="input-append">
													<input type="text" id="bwbg" class="input" name="bwbg"
														placeholder="" required="true"  datatype="/^(?!0{1,4})\d{1,4}|10{4}|0$/"
														 errormsg="部位标高介于0到10000毫米之间"/>
														<span class="add-on">mm</span>
												</div>
											</div>
											<div class="control-group">
												<label class="control-label">改建工程：</label>
												<label class="radio inline"> 
													<input type="radio" name="gjlx" id="gjlx1" value="1" checked> 是
												</label> 
												<label class="radio inline"> 
													<input type="radio" name="gjlx" id="gjlx0" value="0"> 否
												</label> 
											</div>
										</div>
									</div>
								</fieldset>
							</div>
						</div>
					</div>
				</div>
				<div class="bottomBtnBox">
					<span id="siteMsg" style="margin-left:30px;"></span> 
					<input type="submit" value="确定" class="btn btn-success" id="siteSubBtn" style="display: none;" /> 
						<a title="确定" href="javaScript:void(0);" class="btn btn-success"
						onclick="subBtnClick('site')" style="margin:14px 0;"> 
						<i class="icon-ok icon-white"></i> 确定
					</a> 
					<a title="取消" href="javaScript:void(0);" class="btn" data-dismiss="modal" style="margin:14px;"> 
						<i class="icon-remove"></i> 取消
					</a>
				</div>
			</div>
		</form>
	</div>


	<div class="container">
		<from class="form-inline">
		<div id="holderModal" class="modal hide fade in"
			style="display: none;height: 650px;width: 840px;left: 40%;top:40%;">
			<div class="modal-header">
				<a class="close" data-dismiss="modal" id='clBtn'>×</a>
				<h3>支架</h3>
			</div>
			<div class="modal-body">
				<!-- 管线参数选择 -->
				<fieldset>
				<legend style=" margin-bottom:  10px; font-size: 16px; color:  lightskyblue;">管线选择</legend>
					管线类型：
							<select id="gxlx" name="gxlx" style="margin-bottom: 9px;height: 28px;border-radius: 3px;width: 250px;">
							</select>
					管线材质：
							<select id="gxcz" name="gxcz"
								style="margin-bottom: 9px;height: 28px;border-radius: 3px;width: 120px;">
							</select>
					管线规格：
							<select id="gxgg" name="gxgg"
								style="margin-bottom: 9px;height: 28px;border-radius: 3px;width: 120px;">
							</select>
					</fieldset>
				<legend style=" margin-bottom:  10px; font-size: 16px; color:  lightskyblue;">支架选择</legend>
				<div id="holderAll"></div>
			</div>
		</div>
		</from>
	</div>

	
	<!-- 计算报告参数设置 -->
	<div class="container">
		<form method="post" id="reportForm" class="form-horizontal"
			enctype="multipart/form-data">
			<div id="reportModal" class="modal hide fade in"
				style="display: none;height: 650px;width: 840px;top: 40%;left: 35%;">
				<div class="modal-header">
					<a class="close" data-dismiss="modal" id='reportclBtn'>×</a>
					<h3>计算报告参数设置</h3>
				</div>
				<div class="modal-body">
					<div class="container-fluid" style="width: 700px;">
						<div class="row-fluid tooltip-demo"
							style="margin-left:-5px;width: 700px;">
							<div class="box-content" style="width: 780px;">

								<fieldset>
									<div class="input_box" style="height: 490px;">
										<div style="float: left;margin-top: 10px;">
											<input type="hidden" id="zj_squ" name="proZj.squ"
												placeholder="id" value="" />
										<fieldset>
											所属系统：<select id="system" name="proZj.service_system" style="margin-bottom: 9px;height: 28px;border-radius: 3px;width: 200px;">
													</select>

											纵向斜撑数量： <input type="text" class="input" datatype="n" style="width: 50px;margin-bottom: 9px;"
													 name="proZj.vb_num" id="vb_num" required="true" > 
											侧向斜撑数量： <input type="text" datatype="n" style="margin-bottom: 9px;width: 50px;" 
												class="input" id="lb_num" required="true" name="proZj.lb_num" >
											
											管道数量： <input type="text" datatype="n" id="num" style="margin-bottom: 9px;width: 50px;" class="input"
											 required="true" name="proZj.num">
										</fieldset>	
										<div id="imgBox">
											<div id="imgboxLeft" style="float: left;width: 50%;">
												支架形式简图：
														<img  border="0" id="img1" onerror="javascript:this.src='resource/images/mrtp.png'"
															src="resource/images/mrtp.png" style="width: 300px; height: 220px;">

														<input class="input-xlarge focused" id="photo1"
															type="file" name="file1"
															accept="image/gif,image/jpeg,image/jpg,image/png"
															onchange="previewImage(this)"
															style="margin-left: 25px;"/> 
											</div>
											<div id="imgboxRight"  style="float: left;width: 50%;">
												<div id="imgTop">
														侧向受力分析图：
														<img  border="0"  id="img2" onerror="javascript:this.src='resource/images/mrtp.png'"
															src="resource/images/mrtp.png" style="width: 220px; height: 170px;">
														<input class="input-xlarge focused" id="photo2"
															type="file" name="file2"
															accept="image/gif,image/jpeg,image/jpg,image/png"
															onchange="previewImage(this)"
															style="margin-left: 25px;"> 
												</div>
												<div id="imgBottom">
														纵向受力分析图：
														<img  border="0"  id="img3" onerror="javascript:this.src='resource/images/mrtp.png'"
															src="resource/images/mrtp.png" style="width: 220px; height: 170px;">
														<input class="input-xlarge focused" id="photo3" type="file" name="file3"
															accept="image/gif,image/jpeg,image/jpg,image/png"
															onchange="previewImage(this)" style="margin-left: 25px;"/> 
												</div>
											</div>
										
										</div>
											
										</div>
									</div>
								</fieldset>
							</div>
						</div>
					</div>
				</div>
				<div class="bottomBtnBox">
					<span id="reportMsg" style="margin-left:30px;color:red;"></span> 
					<input type="submit" value="确定" class="btn btn-success" id="reportSubBtn" style="display: none;" /> 
						<a title="确定" href="javaScript:void(0);" class="btn btn-success"
						onclick="subBtnClick('report')" style="margin:14px 0;"> 
						<i class="icon-ok icon-white"></i> 提交/修改
					</a> 
					<a title="查看计算报告" href="javaScript:void(0);" class="btn  btn-info" onclick="report_link();"
						 style="margin:14px;display: none;" id="showReportBtn"> 
						<i class="icon-file icon-white"></i> 查看计算报告
					</a>
					<a title="取消" href="javaScript:void(0);" class="btn"
						data-dismiss="modal" style="margin:14px;"> 
						<i class="icon-remove"></i> 取消
					</a>
				</div>
		</form>
	</div>

		<input type="hidden" id="gc">
		 <input type="hidden" id="zgc">
		<input type="hidden" id="bw">
</body>
	
	<!-- 项目公共js -->
	<script type="text/javascript" src="resource/js/projectConfig/projectCommon.js"></script>
	<!-- 项目 -->
	<script type="text/javascript" src="resource/js/projectConfig/project.js"></script>
	<!-- 子项目 -->
	<script type="text/javascript" src="resource/js/projectConfig/projectChild.js"></script>
	<!-- 支架 -->
	<script type="text/javascript" src="resource/js/projectConfig/holder.js"></script>
	<!-- 部位项目 -->
	<script type="text/javascript" src="resource/js/projectConfig/projectChildSite.js"></script>
	<script type="text/javascript" src="resource/js/projectConfig/projectGrid.js"></script>
	<script type="text/javascript" src="resource/js/projectConfig/holderGrid.js"></script>
	<!-- 树菜单依赖 -->
 	<script type="text/javascript" src="resource/js/scriptbreaker-multiple-accordion-1.js"></script>

</html>