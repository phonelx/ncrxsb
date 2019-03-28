/**
 * 资源分类弹出树
 * Author:liangxj
 * date:2012-2-7
 * histroy:
 *    date       opt     editor
 *    2012-2-7   新建    liangxj
 */

/**
 * 
 * @param {Object} domId : 页面DOM对象ID  主要指   input
 * @param {boolean} checkBox : 是否显示 checkBox
 * @param {boolean} leafNodeOnly : 只获取 叶子节点 
 * @memberOf {TypeName} 
 * @return {TypeName} 
 */
function ResourceTypeTree(domId,checkBox,leafNodeOnly){
	//添加弹出树元素到页面上
	$("body").append("<div id='"+domId+"popTreeContainer'" +
	"style='display:none; position:absolute; height:200px;min-width:200px; background-color:white;border:1px solid;overflow-y:auto;overflow-x:auto;'>" +
	"<ul id='"+ domId+ "popTree' class='tree'/></div>");
	
	// 绑定点击事件   当鼠标在  弹出树范围外时  隐藏弹出树
	$("body").bind("mousedown", 
		  function(event){
		   if (!(event.target.id.indexOf('popTree') >= 0 || event.target.id == domId )) {
		  	 hideMenu_();
	  		}
	 });
	
	var resourceTypeTree = null ;// 定义资源弹出树
	// 是否显示Checkbox
	var checkable = false ; 
	if(checkBox == true || checkBox == 'true'){
		checkable = true ;
	}
	// 只获取 叶子节点 
	var  leafNodeFlag = false ;
	if(leafNodeOnly == true || leafNodeOnly == 'true'){
		leafNodeFlag = true ;
	}
	
	// 节点数据
	var resourceTypeTreeNodes = "[{id:'0',name:'资源分类',isParent:'true'}," ;
	// 获取节点数据
	$.ajax({
		type:'post',
		async:false,
		url:'genResourceTypeTree.do',
		success:function(data){
			resourceTypeTreeNodes += data ;
			resourceTypeTreeNodes += "]" ;
		},
		error:function(){
			alert('请求失败！');
		}
	});
	// 树的配置
	
	var setting = {
		isSimpleData: true,
		treeNodeKey: "id",
		treeNodeParentKey: "pId",
		checkable:checkable,
		callback: {
			click: resourceTypeTreeOnClick,
			change: resourceTypeTreeOnChange
		}
	};
	
	// 弹出树的值
	var treeValue = null ;
	// 注册节点点击事件
	function resourceTypeTreeOnClick(event, treeId, treeNode){
		treeValue = "" ;
		//隐藏弹出树
		if(!checkable && treeNode.id != 0){
			// 只获取叶子节点
			if(leafNodeFlag&&treeNode.isParent == true) return ;
			 // 获取除根节点外的任意节点
			treeValue = treeNode.id ;
			$('#'+domId).val(treeNode.name) ;
			hideMenu_();	
		}
	}
	// 注册树的变化事件
	function resourceTypeTreeOnChange(event, treeId, treeNode){
		var names = "";
		treeValue = "" ;
		var selectedNodes = resourceTypeTree.getCheckedNodes(true);// 所有选中的节点
		for(var i=0;i<selectedNodes.length;i++){
			if(selectedNodes[i].id != 0){
				if(leafNodeFlag && selectedNodes[i].isParent == true){
					continue ;
				}
				if(names!="") names+=",";
				names+=selectedNodes[i].name;
				// 选中节点的ID
				if(treeValue!="") treeValue+=",";
				treeValue += selectedNodes[i].id ;
			}
		}
		$('#'+domId).val(names) ;
	}
	
	// 保存所有节点
	this.allNodes = eval("("+resourceTypeTreeNodes+")") ;
	
	// 初始化树
	resourceTypeTree = $("#"+domId+"popTree").zTree(setting,eval("("+resourceTypeTreeNodes+")"));
	// 内部事件    显示弹出树
	this.showMenu = function(){
		showMenu_();
	} 
	// 内部事件     隐藏弹出树
	this.hideMenu = function(){
		hideMenu_() ;
	}
	// 获取值
	this.getValue = function(){
		return treeValue ;
	}
	// 清空
	this.clear = function(){
		$("#"+domId).val("请选择资源类型");
		treeValue = "" ;
		resourceTypeTree.cancelSelectedNode();
		if(checkable){
			resourceTypeTree.checkAllNodes(false);
		}
		resourceTypeTree.expandAll(false);
		
	}
	var tempArr = new Array();
	// 设置初始值
	this.setValue = function(value){
		treeValue = value ;
		var allNodes = eval("("+resourceTypeTreeNodes+")") ;// 获取所有节点
		var values = (value+'').split(',');
		var names = "" ;
		// 初始化输入框
		for(var i=0;i<allNodes.length;i++){
			for(var j=0;j<values.length;j++){
				if(allNodes[i].id == values[j]){
					if(names!='')names+=",";
					names+=allNodes[i].name;
					tempArr.push(allNodes[i]);
					break ;
				}
			}
		}
		// 初始化输入框信息
		$("#"+domId).val(names==""?"请选择资源类型":names);
	}
	
	// 隐藏弹出树
	function hideMenu_(){
		$("#"+domId+"popTreeContainer").hide();
	}
	// 显示弹出树
	function showMenu_(){
		// 输入框对象
		var treeDom = $("#"+domId);
		var treeDomOffset = $("#"+domId).offset();
		// 使弹出的树形菜单紧贴输入框
		$("#"+domId+"popTreeContainer").css({width:(treeDom.width()<200?200:treeDom.width())+"px",left:treeDomOffset.left + "px", top:treeDomOffset.top + treeDom.outerHeight() + "px",zIndex:99999}).slideDown("fast");
		// 设置初始值
		if(tempArr.length>0){
			for(var i=0;i<tempArr.length;i++){
				var node = resourceTypeTree.getNodeByParam("id",tempArr[i].id) ;
				if(checkable == true){
					node.checked = true ;
					resourceTypeTree.updateNode(node,true);
				}else{
					resourceTypeTree.selectNode(node);
				}
			}
			resourceTypeTree.refresh();
			tempArr = new Array() ;
		}
		
	}
}
