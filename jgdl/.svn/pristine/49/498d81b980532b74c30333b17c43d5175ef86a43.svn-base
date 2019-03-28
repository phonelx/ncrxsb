

/**
 * 打印
 */
function print(){
	/*alert("=="+$("#gcmc").val())
	proInReport($("#gcsqu").val());*/
	$(".tab-content").jqprint({
		 debug: false,            
         importCSS: true,         
         printContainer: true,    
         operaSupport: false
	})
	
	
}

/**
 * 
  * proInReport:(计算报告书中的项目信息)
  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
  * @author: 唐青
  * @dateTime: 2018-4-11 下午2:24:12
  * @param: 
  * @return: void
 */
function proInReport(prosqu){
	

	var  xmsqu = 0;
	$.ajax({
		type : 'post',
		url : 'getProjectInfo.do',
		timeout : 1321231232131213123,
		dataType:"json",
		async : false,
		data:{
			prosqu:prosqu,
		},
		success : function(data) {
			
			var list = data.map;
		
			$("#bgmc").html(list.XMMC);
			$("#gcmc").html(list.XMMC)
			$("#gcdz").html(list.XMDZ)
			$("#sfld").html(list.DZSFLD)
			$("#dzjst").html(list.DZJSD)
			$("#dzlx").html(list.DZLX)
	
		},
		/*error : function() {
			Modal.alert({
			    msg: '子项目列表加载出错',
			    title: '消息提示',
			    btnok: '确定',
			    btncl:'取消'
			  });
		}*/
	});
}

/**
 * 
  * childInReport:(子工程项目)
  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
  * @author: 唐青
  * @dateTime: 2018-4-12 上午10:54:06
  * @param: @param prosqu
  * @return: void
 */
function childInReport(childsqu){
	
	//alert()
	//var  xmsqu = 0;
	$.ajax({
		type : 'post',
		url : 'getChildInfo.do',
		timeout : 1321231232131213123,
		dataType:"json",
		async : false,
		data:{
			childsqu:childsqu,
		},
		success : function(data) {
			
			var list = data.map;
		
			$("#jzgd").html(list.JZGD);
			
		
		},
		/*error : function() {
			Modal.alert({
			    msg: '子项目列表加载出错',
			    title: '消息提示',
			    btnok: '确定',
			    btncl:'取消'
			  });
		}*/
	});
}

/**
 * 
  * siteInReport:(部位)
  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
  * @author: 唐青
  * @dateTime: 2018-4-12 上午10:54:56
  * @param: @param sitesqu
  * @return: void
 */
function siteInReport(sitesqu){
	
	//alert()
	//var  xmsqu = 0;
	$.ajax({
		type : 'post',
		url : 'getSiteInfo.do',
		timeout : 1321231232131213123,
		dataType:"json",
		async : false,
		data:{
			sitesqu:sitesqu,
		},
		success : function(data) {
			
			var list = data.map;
			$("#azbw").html(list.BWMC);
			$("#azbg").html(list.BWBG)
		},
		/*error : function() {
			Modal.alert({
			    msg: '子项目列表加载出错',
			    title: '消息提示',
			    btnok: '确定',
			    btncl:'取消'
			  });
		}*/
	});
}

/**
 * 
  * childInReport:(支架)
  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
  * @author: 唐青
  * @dateTime: 2018-4-12 上午10:53:35
  * @param: @param childsqu
  * @return: void
 */
function zjReport(zjsqu){
	
	$.ajax({
		type : 'post',
		url : 'getZjInfo.do',
		timeout : 1321231232131213123,
		dataType:"json",
		async : false,
		data:{
			zjsqu:zjsqu,
		},
		success : function(data) {
			
			var list = data.map;
		
			$("#bgmc").html(list.XMMC);
			//$("#jzlb").val(list.)
			$("#gcdz").val(list.XMDZ)
			$("#sfld").val(list.DZSFLD)
			$("#dzjst").val(list.DZJSD)
			$("#dzlx").val(list.DZLX)
		
		},
		/*error : function() {
			Modal.alert({
			    msg: '子项目列表加载出错',
			    title: '消息提示',
			    btnok: '确定',
			    btncl:'取消'
			  });
		}*/
	});
}