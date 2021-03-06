<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/pages/head.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>" />
<link rel="shortcut icon" href="resource/new/bootstrap/img/favicon.ico" />
<link rel="stylesheet" type="text/css" href="resource/css/addStyle.css" />
<script type="text/javascript" src="resource/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="resource/js/user/user.js"></script>
<link rel="stylesheet" type="text/css" href="resource/ztree/css/zTreeStyle.css" />
<script type="text/javascript" src="resource/ztree/jquery.ztree-2.6.min.js"></script>
<style type="text/css">

#table-body-userGrid tr td{
	text-align: center;
}
#uiGrid-userGrid tr th{
	text-align: center;
}
textarea{outline:none;resize:none;}
</style>
</head>
<body>
	<ul class="breadcrumb" id="roleSearchBox" style="list-style:none;">
		<li id="titleName1" style="color: #369BD7;">
			当前页面：用户权限管理&nbsp;&gt;
			<span> 
				<a href="javascript:void(0)"> 
					部门管理
				</a>
			</span>
		</li>
	</ul>
	
	<div class="container-fluid">
		<div class="row-fluid">
			<!-- 左菜单开始 -->
			<div id="listContent" class="span3 leftSideBar" >
			   <div id="dept_list" >
   			      <ul id="moduleTree" class="tree"></ul>
   		       </div>
			</div>
			<!-- 左菜单结束 -->
	        <div id="content" class="span9">
				<div>
						<ul class="breadcrumb" id="userSearchBox" style="list-style:none;">
		<li id="titleName" style="color: #369BD7;">
		首页&nbsp;&gt;
			<span> 
				<a href="javascript:void(0)"> 
					用户管理
				</a>
			</span>
		</li>
		
		<li style="float: right;">
			<a style="margin-top:-3px;" title="新增" class="btn btn-primary" onclick="showAddUser();" href="javascript:void(0);">
			<i class="icon-plus-sign icon-white"></i> 新增</a>
		</li>
		<li style="float:right;margin-right: 5px;">
			<input id="input_user_search" type="text" class="inputText" style="display:none;" value="关键字搜索"
				onfocus="if(this.value == '关键字搜索') this.value = ''" onblur="if(this.value =='') this.value = '关键字搜索'"/>
			<a class="btn" onclick="showUsersList(1);" href="javascript:void(0);" style="display:none;">
				<i class="icon-search"></i> 检索
			</a>
		</li>
	</ul>
			</div>
				
				<div id="userGrid"></div>
			</div>
			<input type="hidden" id="typeId"/>
		</div>
	</div>
	

	<div id="userDiv" class="modal hide fade in windowBox" style="display:none;">
		<div class="modal-header">
			<a class="close" data-dismiss="modal">×</a>
			<h3 id="userTitleName">添加用户</h3>
		</div>
		
		<div class="modal-body">
			<form action="" method="post" id="userForm">
				<input type="hidden" name="squ" id="squ" value="-1" />
				<table border="0" align="center" cellpadding="0" cellspacing="0" style="width:100%;margin:10px 0px;">
					<tr>
						<td>
							<table border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#99bbe8" id="inputForm" style="margin:0 auto;">
								<tr>
									<td width="30%" class="leftTd" align="right">用户名称：&nbsp;</td>
									<td width="220px">&nbsp;
										<input type="text" name="userTitle" id="userTitle" class="easyui-validatebox"
											validType="dbsNameValid[200]" required="true"/>
									</td>
								</tr>
								<tr>
									<td class="leftTd" align="right">登录帐户：&nbsp;</td>
									<td>&nbsp; 
										<input type="text" name="username" id="username" class="easyui-validatebox" 
											validType="dbsNameValid[200]" required="true"/>
									</td>
								</tr>
								<tr>
									<td class="leftTd" align="right">登录密码：&nbsp;</td>
									<td>&nbsp; 
										<input type="password" name="pwd" id="pwd" class="easyui-validatebox" 
											validType="pwdValid['']" required="true"/>
									</td>
								</tr>
								<tr>
									<td class="leftTd" align="right">确认密码：&nbsp;</td>
									<td>&nbsp; 
										<input type="password" name="repeatPwd" id="repeatPwd" class="easyui-validatebox"
											validType="pwdValid['']" required="true"/>
									</td>
								</tr>
								<tr>
									<td class="leftTd" align="right">是否启用：&nbsp;</td>
									<td>&nbsp; 
										<select name="isEnabled" id="isEnabled">
											<option value="1">启用</option>
											<option value="0">禁用</option>
										</select>
									</td>
								</tr>
								<tr>
									<td class="leftTd" align="right">选择角色：&nbsp;</td>
									<td>&nbsp; 
										<select id="roleSquSelect" name="roleSqu">
											<option value="1">启用</option>
										</select>
									</td>
								</tr>
								<tr>
									<td class="leftTd" align="right">员工工号：&nbsp;</td>
									<td>&nbsp;
										<input type="text" name="sfzhm" id="sfzhm" class="easyui-validatebox" 
											validType="" required="true"/>
									</td>
								</tr>
								<tr>
									<td class="leftTd" align="right">所属部门：&nbsp;</td>
									<td>&nbsp;
										<input type="text" id="dept" class="easyui-validatebox" 
											validType="" required="true" readonly="readonly"/>
										<input type="text" name="deptID" id="deptID" class="easyui-validatebox" 
											validType="" required="true" style="display: none;"/>
									</td>
								</tr>
								<tr>
									<td class="leftTd" align="right">有效时间：&nbsp;</td>
									<td>&nbsp;
										<input type="text" name="validTime" id="validTime" class="easyui-validatebox" 
											validType="" required="true" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
									</td>
								</tr>
								<tr>
									<td class="leftTd" align="right">
										描述：&nbsp;</td>
									<td>&nbsp; 
										<textarea name="descb" id="descb" rows="4" cols="20" id="descb"  class="easyui-validatebox" validType="descbValid[2000]"  resize:none></textarea>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				
				<div class="bottomBtnBox">
					<input type="reset" id="clearForm" name="reset" style="display:none;"/>
					<a title="确定" class="btn btn-success" id="userBtn" style="margin:14px 0;">
						<i class="icon-ok icon-white"></i> 确定
					</a>
					<a title="取消" class="btn" id="closeUserBox" data-dismiss="modal" style="margin:14px;">
						<i class="icon-remove"></i> 取消
					</a>
				</div>
			</form>
		</div>
	</div>
	
	
	<div id="deptModal" class="modal hide fade in windowBox" style="display:none;width: 650px;">
		<div class="modal-header">
			<a class="close" data-dismiss="modal">×</a>
			<h3>部门选择</h3>
		</div>
		<div class="modal-body">
			<div id="deptList"></div>
			<div class="bottomBtnBox">
				<a title="确定" class="btn btn-success" id="deptBtn" style="margin:14px 0;">
					<i class="icon-ok icon-white"></i> 确定
				</a>
				<a title="取消" class="btn" id="closeDeptBox" data-dismiss="modal" style="margin:14px;">
					<i class="icon-remove"></i> 取消
				</a>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		showUsersList(1);
	</script>
</body>
</html>
