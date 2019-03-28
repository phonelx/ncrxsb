<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/pages/head.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
  <html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<base href="<%=basePath%>" />
	<link rel="stylesheet" type="text/css" href="resource/css/mycss.css"/>
	<link rel="stylesheet" type="text/css" href="resource/ztree/css/zTreeStyle.css" />
	<link rel="stylesheet" type="text/css"  href="resource/css/addStyle.css" />
	<script type="text/javascript" src="resource/js/user/role.js"></script>
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
		<li id="titleName" style="color: #369BD7;">
			当前页面：角色管理&nbsp;&gt;
			<span> 
				<a href="javascript:void(0)"> 
					用户管理
				</a>
			</span>
		</li>
		
		<li style="float: right;">
			<a style="margin-top:-3px;" title="新增" class="btn btn-primary" onclick="showAddRole();" href="javascript:void(0);">
			<i class="icon-plus-sign icon-white"></i> 新增</a>
		</li>
		<li style="float:right;margin-right: 5px;">
			<input id="input_role_search" type="text" class="inputText" style="display:none;" value="关键字搜索"
				onfocus="if(this.value == '关键字搜索') this.value = ''" onblur="if(this.value =='') this.value = '关键字搜索'" />
			<a class="btn" onclick="initRoleGrid(1);" href="javascript:void(0);" style="display:none;">
				<i class="icon-search"></i> 检索
			</a>
		</li>
	</ul>
	
	<!-- <div id="roleSearchBox">
		<div></div>
		<div>
			<input id="input_role_search" type="text" style="display:none;width:200px;color:#666;margin:0px;" value="关键字搜索"
				onfocus="if(this.value == '关键字搜索') this.value = ''" onblur="if(this.value =='') this.value = '关键字搜索'">
			<a class="btn " onclick="initRoleGrid(1);" href="javascript:void(0);" style="display:none;">
				<i class="icon-search"></i> 检索
			</a>
			
			<a title="添加角色" class="btn" onclick="showAddRole();" href="javascript:void(0);" style="margin-right:10px;">
				<i class="icon-plus-sign"></i> 添加
			</a>
		</div>
	</div> -->

	<div id="roleGrid" style="padding: 0px 10px;"></div>
	
	<div id="roleDiv" class="modal hide fade in windowBox" style="display:none;">
		<div class="modal-header">
			<a class="close" data-dismiss="modal">×</a>
			<h3 id="roleTitleName">添加角色</h3>
		</div>
		
		<div class="modal-body">
	   		<form action="" method="post" id="roleForm" name="roleForm">
	   			<input type="hidden" name="roleSqu" id="roleSqu" value=""/>
	   			<div id="roleAddDiv">
		   			<table cellpadding="4" border="0" cellspacing="0" bgcolor="#99BBEB" width="95%" align="center">
		   				<tr>
		   					<td style="text-align:right" width="20%">角色名称：</td>
		   					<td class="tdright">
		   						<input type="text" id="roleName" name="roleName" class="easyui-validatebox"  style="margin-bottom: 0px;" validType="dbsNameValid[200]" required="true"/>
		   					</td>
		   				</tr>
		   				<tr>
		   					<td style="text-align:right">角色类型：</td>
		   					<td class="tdright">
		   						<select name="isAdmin" id="isAdmin" style="margin-bottom: 0px;">
		   							<option value="1" selected="selected">管理角色</option>
		   							<option value="0" >查询角色</option>
		   						</select>
	  						</td>
		   				</tr>
		   				<tr>
		   					<td style="text-align:right">是否是审批人：</td>
		   					<td class="tdright">
		   						<select name="isSpr" id="isSpr" style="margin-bottom: 0px;">
		   							<option value="1" selected="selected">是</option>
		   							<option value="0" >否</option>
		   						</select>
	  						</td>
		   				</tr>
		   				<tr>
		   					<td style="text-align: right">权限授予：</td>
		   					<td class="tdright">
		   						<div id="scrollList" style="height:200px;border: 1px solid #7F9DB9;" >
		   							<div id="moduleTree" class="easyui-tree"></div>
		   						</div>
		   					</td>
		   				</tr> 
		   				<tr>
		   					<td style="text-align: right">描述：</td>
		   					<td class="tdright">
		   						<textarea  rows="5" cols="20" id="descb" name="descb" 
		   							class="easyui-validatebox"  validType="descbValid[2000]">
		   						</textarea>
		   					</td>
		   				</tr>
		   			</table>
		   			
		   			<!-- <div class="boxBottom">
						<input type="reset" id="clearRoleForm" name="reset" style="display:none;"/>
						<input type="button" value="确定" class="btn" id="roleBtn"/>&nbsp;
		   				<input type="button" value="取消" class="btn" id="closeRoleBox" data-dismiss="modal"/>&nbsp;
					</div> -->
					<div class="bottomBtnBox">
						<input type="reset" id="clearRoleForm" name="reset" style="display:none;"/>
						<a title="确定" class="btn btn-success" id="roleBtn" style="margin:14px 0;">
							<i class="icon-ok icon-white"></i> 确定
						</a>
						<a title="取消" class="btn" id="closeRoleBox" data-dismiss="modal" style="margin:14px;">
							<i class="icon-remove"></i> 取消
						</a>
					</div>
					
	   			</div>
	   		</form>
   		</div>
   	</div>
   	
	<script type="text/javascript">
		initRoleGrid(1);
	</script>
</body>
</html>

