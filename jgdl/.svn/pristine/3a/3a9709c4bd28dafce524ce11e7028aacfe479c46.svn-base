<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/pages/head.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    
    <title>共享服务系统</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style type="text/css">
		body,div,input,a,table,li,ul{margin:0px;padding:0px;}
		#userSearchBox{padding:10px 0px;text-align:right;}
		#entity_add input{width:230px;}
		#entity_add select{width:240px;border-color:#CCCCCC;border-radius:3px;}
		#entity_add textarea{width:230px;}
		.boxBottom{padding:10px 0px;text-align:right;}
		#btn_entity_ok,#closeEntityBox{width:90px !important;}
		
		
		#field_add input{width:230px;}
		#field_add select{width:240px;border-color:#CCCCCC;border-radius:3px;}
		#field_add textarea{width:230px;}
		.boxBottom{padding:10px 0px;text-align:right;}
		#btn_f_ok,#closeFieldBox{width:90px !important;}

	
		#fieldDiv tr{background: ffffff;height:27;}
		#entityDiv tr{background: ffffff;height:27;}
		.tdright{padding-left:10px;}
		#field_add td{height:30px;}
	</style>
	<link rel="stylesheet" type="text/css" href="resource/ztree/css/zTreeStyle.css" />
	<script type="text/javascript" src="resource/ztree/jquery.ztree-2.6.min.js"></script>
	<script type="text/javascript" src="resource/js/main.js"></script>
	<script type="text/javascript" src="resource/js/scriptbreaker-multiple-accordion-1.js"></script>
	<script type="text/javascript" src="resource/js/registry/registry-entity.js"></script>
	<script type="text/javascript" src="resource/js/registry/registry-field.js"></script>
</head>
<body>
	<div id="entityMgr" style="height:100%;padding-top:10px;" fit="true">
		<div split="true" style="width:200px;display:none;" title="已注册资源">
			<div id="objects" class="easyui-tree"></div>
		</div>
		
		<div class="span2 main-menu-span" style="width:20%;margin-right:10px;">
			<div class="well nav-collapse sidebar-nav">
				<!--/.nav-collapse -->
				<ul class="nav nav-tabs nav-stacked main-menu">
					<li class="nav-header hidden-tablet">
						<a class="ajax-link" href="javascript:void(0);" id="0" title="所有分类" style="cursor:context-menu;">
							<font size="2">目录分类</font>
						</a>
					</li>
					
					<li>
						<ul class="topnav" style="list-style: none;">
							<li>
								<a href="javascript:void(0);" flag="1" id="sourceRigistry" title='数据源' tag='' isEnd="0" onclick="parentload(this);">
									<i class="icon-darkgray icon-calendar"></i>&nbsp;数据源
								</a>
								<ul class="topnav"></ul>
							</li>
						</ul>
					</li>
				</ul>
			</div>
		</div>
		
		<div style="width:75%;display:table;margin-left:0px;">
			<div id="entityTable" style="height:100%" fit="true">
				<div id="registed" title="已注册实体"></div>
				<div id="notreg" title="未注册实体"></div>
			</div>
			<div id="fieldMgr" style="height:100%;display:none;" fit="true">
				<div style="padding: 10px 0px;text-align: right;">
					<input type="button" value="返回上一级" class="btn" onclick="goBackDiv();">
				</div>
				<div id="fieldReged" title="已注册字段"></div>
				<div id="fieldNotReg" title="未注册字段"></div>
   			 </div>
		</div>
  	</div>
  	
  	<!-- 注册实体表 BOX ----------- start -->
  	<div id="entity_add" class="modal hide fade in" style="display:none;width:600px;height:400px;top:40%;left:50%;">
  		<div class="modal-header">
			<a class="close" data-dismiss="modal">×</a>
			<h3 id="entityTitleName">注册实体表</h3>
		</div>
		
		<div class="modal-body">
	  		<form action="">
		  		<div style="margin: 10px 0px;" id="entityDiv">
		  			<table cellpadding="0" cellspacing="1" bgcolor="#99BBEB" width="95%" height="200px">
		  				<tr bgcolor="#ffffff">
		  					<td align="right" width="30%">名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称：</td>
		  					<td class="tdright">
		  						<input type="text" id="ename" disabled="disabled"/>
		  					</td>
		  				</tr>
		  				<tr bgcolor="#ffffff">
		  					<td align="right">标&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;题：</td>
		  					<td class="tdright">
		  					<input type="text" id="etitle" class="easyui-validatebox" required="true" onblur="sameNameJudge('SSP_TREGENTITY','TITLE',this.value,'EntityNameOldValue','etitle','EntityNameOldValue')"/>
		  					<input type="hidden" id="EntityNameSameJudge" value="true"/>
		  					<input type="hidden" id="EntityNameOldValue"/>
		  					<input type="hidden" id="querydbID"/>
		  					</td>
		  				</tr>
		  				<tr bgcolor="#ffffff">
		  					<td align="right">描&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;述：</td>
		  					<td class="tdright">
		  						<textarea  rows="5" id="edescb" class="easyui-validatebox"  validType="descbValid[2000]"></textarea>
		  					</td>
		  				</tr>
		  			</table>
		  			
		  			<div class="boxBottom">
						<input type="reset" id="clearEntityForm" name="reset" style="display:none;"/>
						<input type="button" value="确定" class="btn" id="btn_entity_ok"/> &nbsp;
		  				<input type="button" value="取消" class="btn" id="closeEntityBox" data-dismiss="modal"/>
					</div>
		  		</div>
	  		</form>
  		</div>
  	</div>
  	<!-- 注册实体表 BOX ----------- end -->

	<!-- 注册字段 BOX ----------- start -->
  	<div id="field_add" class="modal hide fade in" style="display:none;width:600px;height:480px;top:50%;left:50%;">
  		<div class="modal-header">
			<a class="close" data-dismiss="modal">×</a>
			<h3 id="fieldTitleName">注册字段</h3>
		</div>
		
  		<div class="modal-body">
		    <form action="">
		    	<div style="margin: 10px 0px;" id="fieldDiv">
		  			<table cellpadding="0" cellspacing="1" bgcolor="#99BBEB" width="95%" >
			    		<tr bgcolor="#ffffff">
			    			<td align="right" width="35%">名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称：</td>
			    			<td class="tdright">
			    				<input id="fname" type="text" disabled="disabled"/>
			    			</td>
			    		</tr>
			    		<tr bgcolor="#ffffff">
			    			<td align="right">标&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;题：</td>
			    			<td class="tdright">
			    			<input id="ftitle" type="text" class="easyui-validatebox" required="true"
								validType="dbsNameValid[200]" 
								onblur="sameNameJudge('SSP_TREGFIELD','TITLE',this.value,'FiledNameOldValue','ftitle','FiledNameOldValue')"/>
			    			<input type="hidden" id="FiledNameSameJudge" value="true"/>
		  					<input type="hidden" id="FiledNameOldValue"/>
		  					<input type="hidden" id="tabID"/>
		  					</td>
			    		</tr>
			    		<tr bgcolor="#ffffff">
			    			<td align="right">显示优先级(<font color="red">1最低</font>)：</td>
			    			<td class="tdright">
			    				<select id="displayPrio">
			    					<option value="1">1</option>
			    					<option value="2">2</option>
			    					<option value="3">3</option>
			    					<option value="4">4</option>
			    					<option value="5">5</option>
			    					<option value="6">6</option>
			    					<option value="7">7</option>
			    					<option value="8">8</option>
			    					<option value="9">9</option>
			    					<option value="10">10</option>
			    				</select>
			    			</td>
			    		</tr>
			    		<tr bgcolor="#ffffff">
		  					<td align="right">是否为检索字段：</td>
		  					<td class="tdright">
		  						<input type="radio" value="0" name="fisquery" style="width:25px;"/>是
		  						<input type="radio" value="1" name="fisquery" style="width:25px;" checked="checked"/>否
		  					</td>
		  				</tr>
		  			
		  			
		  				<tr bgcolor="#ffffff" style="display: none;" class="bkinshow">
		  					<td align="right">请选择匹配的字段：</td>
		  					<td class="tdright">
		  						<input id="bkgetFiled" />
		  						<input type="hidden" class="bkindexof"/>
		  					</td>	
		  				</tr>
			    		<tr bgcolor="#ffffff">
			    			<td align="right">描&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;述：</td>
			    			<td class="tdright">
			    			 	 <textarea id="fdescb"  rows="5" class="easyui-validatebox"  validType="descbValid[2000]"></textarea>
			    			 </td>
			    		</tr>
					</table>
			    	 
			    	<div class="boxBottom">
						<input type="reset" id="clearFieldForm" name="reset" style="display:none;"/>
						<input type="button" value="确定" class="btn" id="btn_f_ok"/> &nbsp;
		  				<input type="button" value="取消" class="btn" id="closeFieldBox" data-dismiss="modal"/>
					</div>
		    	</div>
		    </form>
		</div>
  	</div>
  	<!-- 注册字段 BOX ----------- end -->
  	
  	<script type="text/javascript">
		showTree();

		$(".topnav").accordion({
			accordion:true,
			speed: 500,
			closedSign: '[+]',
			openedSign: '[-]'
		});
		
		var dicTypeDatas = "<%=(String)request.getAttribute("dicTypes")%>";
		// 生成字典combobox ext
		var proxy = new Ext.data.MemoryProxy(eval("("+dicTypeDatas+")"));
		var Dic = Ext.data.Record.create([
			{name:"bmlb",type:"string",mapping:0},
			{name:"bmmc",type:"string",mapping:1}
		]);
		var reader = new Ext.data.ArrayReader({},[
			{name:"bmlb",type:"string",mapping:0},
			{name:"bmmc",type:"string",mapping:1}
		]);
		var store = new Ext.data.Store({
			proxy:proxy,
			reader:reader,
			autoLoad:true
		});
		var comboboxDic =  new Ext.form.ComboBox({
			renderTo:Ext.get('dictype'),
			triggerAction:"all",
			store:store,
			width:202,
			maxHeight:120, 
			listWidth:500,
			shadow:false,
			displayField:"bmmc",
			valueField:"bmlb",
			mode:"local",
			grow:true,
			emptyText:"-----请选择数据字典-----",
			listeners:{
				 'beforequery':function(e){
					var combo = e.combo ;
					if(!e.forceAll){
						var value = e.query ;
						combo.store.filterBy(function(record,id){
							var val = record.get(combo.valueField);
							var text = record.get(combo.displayField);
							return (val.split('@')[1]).indexOf(value.toLowerCase()) != -1 || text.indexOf(value) != -1;
						});
						combo.expand();
						return false ;
					}
				}
		    }
		});
	 </script>
</body>
</html>
