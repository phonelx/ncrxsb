
	var l1 = 0,l2 = 0;
	var cname1, cname2, cname3;
	var cid1, cid2, cid3;
	var canClick1 = !0;//true
	var canClose1 = !0;
	var op = 1;//普通部件/组合部件标记

	$(function(){
		zhSpan();
	})
	function zhSpan(){
	
		
		//$('#zh-select-box').hide();
		$('#zh-select-box').show();
	
		if (canClick1) {
			
			$('ul', $('#zh-select-box')).html('');
			var ty = "1"
			var squ ="-1" 
			fillDataZh();
			canClick1 = !1;
		}
		
	}
	
		
	/*	
	$('#zh_close', $('#zh-select-box')).on("click", function() {
		qxzh();
		canClick1 = !0;
		
	});*/
	
	//选择部件删除方法

	
		
		
	//组合部件
	$('ul.zhfirst', $('#zh-select-box')).on('click', 'li', function() {
		
		$(this).addClass('selected').siblings().removeClass('selected');
		var id = $(this).attr('val')
	
				fillDataZh1(id)
			
		cname3 = $(this).text();//DXZHMC
		cid3 = $(this).attr('val');//ZHSQU
		canClose = !0;
	
	});
	
		
		function fillDataZh() {
		
			//var gdlx = $("#gdlx").val();//安装方式

			var temp_html = "";
			$.ajax({
				type : 'post',
				url : 'selectZhbjInfo.do',
				timeout : 1321231232131213123,
				dataType:"json",
				//async : false, //是否发送异步请求	
				data:{
					
					
				},
				success:function(data){
		
					if (typeof(data.list) != 'undefined' ) {
						
						$.each(data.list, function(i, pro) {
							temp_html += '<li val="' + pro.ZHSQU + '">' + pro.DXZHMC + '</li>';
							
						});
					} 
					
					$("#zh-select-box .zhfirst").html(temp_html);
					
				}
			});
		}
		
		
		
		//填充级联数据
		function fillDataZh1(l2) {

			var temp_html = "";
			$.ajax({
				type : 'post',
				url : 'selectZhbjInfoByZhsqu.do',
				timeout : 1321231232131213123,
				dataType:"json",
				//async : false, //是否发送异步请求	
				data:{
					ZHSQU:l2,	
				},
				success:function(data){
				
				var lis  = data.list
					if (typeof(data.list) != 'undefined' ) {
						
						$.each(data.list, function(i, pro) {
							temp_html += '<li val="' + pro.ZHSQU + '">' + pro.DXZHMC+ '</li>';
							
						});
					} 
						
					/*	$("#zhgdlx").val(lis[0].GDLX);
					
						$("#zhzjxs").val(lis[0].ZJXS);
						$("#zhbjmc").val(lis[0].DXZHMC);	
						$("#bjsqu").val(lis[0].ZHSQU);
						$("#cpZP").src = '/upload/'+lis[0].ZP;
						$("#photoBox").html("<img src='/upload/"+lis[0].ZP+"' id = 'photo'/>")
						$("#bjop").val(1)//组合部件
*/						
						$('#appBtnZh').unbind();
						 $('#appBtnZh').click(function() {
						
							 var bjsqu = lis[0].ZHSQU;//部件id
							var bjsl = $('#zhsl').val();//部件数量
							var op = 1;
							var dxSqu = $('#zjqu').val();//支架squ 
							var bjfl = $("#bjfl").val();
							
						


									$.ajax({
										type : 'post',
										url : 'addKzzjBj.do',
										timeout : 1321231232131213123,
										data : {
											'dxsqu' : dxSqu,
											'sl':bjsl,
											'bjsqu':bjsqu,
											'bjop':op,
											'bjfl':bjfl
										},
										success : function(data) {
											
											if(data == "hasExit"){
												alert("部件已存在！");
											}else{
											$('#closeWin1').click();
											showBjList(1,bjfl);
																
											
										
											}
										},
										error : function() {
											alert('添加出错！');
										}
									});


							 });
						
						
						
				}
			});
			
		}
		function qxzh(){
			$('#zh-select-box').hide();
			canClick1 = !0;
		}