
	var l1 = 0,l2 = 0;
	var cname1, cname2, cname3;
	var cid1, cid2, cid3;
	var canClick = !0;//true
	var canClose = !0;
	var op = 0;//普通部件
	$(function(){
		ptSpan();
	})
	function ptSpan(){
		
		clernData4();
		//$('#zh-select-box').hide();
		$('#pt-select-box').show();
	
		if (canClick) {
			$('ul', $('#pt-select-box')).html('');
			var ty = "1"
			var squ ="-1" 
			fillData(ty,squ);
			canClick = !1;
		}
		
	}
		/*$('#ptSpan', $('#selectButton')).on('click', function() {
			clernData4();
			$('#zh-select-box').hide();
			$('#pt-select-box').show();
		
		if (canClick) {
			$('ul', $('#pt-select-box')).html('');
			var ty = "1"
			var squ ="-1" 
			fillData(ty,squ);
			canClick = !1;
		}
	});*/
		
		
	/*$('#pt_close', $('#pt-select-box')).on("click", function() {
		
		 $('#pt-select-box').hide() ;
		canClick = !0;
	});*/
	
	/*//选择部件删除方法
	$('.select-res').on('click', 'a', function() {
	$(this).parent().remove();
	$('.select-res').find("p").remove();
	//清除表单
	$("#clearForm").click();
	$("#photoBox").html("<img src='resource/images/picture.png'/>");

	})*/
	
	
	$('ul.first', $('#pt-select-box')).on('click', 'li', function() {
		clernData4();
		$(this).addClass('selected').siblings().removeClass('selected');
		$('ul.third').html('');
		var ty = "2"	
			if($(this).attr("jd")=="1"){
				fillData1(5,$(this).attr("val"));
			}else{
				fillData(ty,$(this).attr("val"));
			}
		
		l1 = $(this).index();
		cname1 = $(this).text();
		cid1 = $(this).attr('val');
		canClose = !0;
		/*$('input.cid', $('.select-res')).val(cid1);
		$('input.cname', $('.select-res')).val(cname1);*/
	});
	
	
	$('ul.second', $('.select-box')).on('click', 'li', function() {
		clernData3();
		$(this).addClass('selected').siblings().removeClass('selected');
		var ty = "3"	
			if($(this).attr("jd")=="1"){
				fillData1(5,$(this).attr("val"));
			}else{
				fillData(ty,$(this).attr("val"));
			}
		//fillData(ty,$(this).attr("val"));
		l2 = $(this).index();
		cname2 = $(this).text();
		cid2 = $(this).attr('val');
		canClose = !0;
		/*$('input.cid', $('.select-res')).val(cid1 + ',' + cid2);
		$('input.cname', $('.select-res')).val(cname1 + ',' + cname2);*/
	});
	
	$('ul.third', $('#pt-select-box')).on('click', 'li', function() {
		clernData2();
		$(this).addClass('selected').siblings().removeClass('selected');
		var ty = "4"		
			if($(this).attr("jd")=="1"){
				fillData1(5,$(this).attr("val"));
			}else{
				fillData(ty,$(this).attr("val"));
			}
		l2 = $(this).index();
		cname2 = $(this).text();
		cid2 = $(this).attr('val');
		canClose = !0;
		/*$('input.cid', $('.select-res')).val(cid1 + ',' + cid2);
		$('input.cname', $('.select-res')).val(cname1 + ',' + cname2);*/
	});
	
	$('ul.foure', $('#pt-select-box')).on('click', 'li', function() {
		clernData1();
		$(this).addClass('selected').siblings().removeClass('selected');
		var ty = "5"		
		fillData1(ty,$(this).attr("val"));
		l2 = $(this).index();
		cname2 = $(this).text();
		cid2 = $(this).attr('val');
		canClose = !0;
		/*$('input.cid', $('.select-res')).val(cid1 + ',' + cid2);
		$('input.cname', $('.select-res')).val(cname1 + ',' + cname2);*/
	});
	
	$('ul.five', $('#pt-select-box')).on('click', 'li', function() {
		$(this).addClass('selected').siblings().removeClass('selected');
		var ty = "6"
			
		var id = $(this).attr('val')
			
			fillData1(ty,id)
			
		
		cname3 = $(this).text();
		cid3 = $(this).attr('val');
		canClose = !0;
	
	});
	
	//填充级联数据
	function fillData(l1,l2) {
	
		var temp_html = "";
		$.ajax({
			type : 'post',
			url : 'searchLbInfo.do',
			timeout : 1321231232131213123,
			dataType:"json",
			//async : false, //是否发送异步请求	
			data:{
				squ:l2,
				
			},
			success:function(data){
				var parentSqu = "";
				if (typeof(data.list) != 'undefined' ) {
					
					$.each(data.list, function(i, pro) {
						temp_html += '<li val="' + pro.SQU + '"jd="'+pro.JD+'">' + pro.LBMC + '</li>';
						
					});
				} 
				if(l1==1){
					$(".select-box .first").html(temp_html);
				}else if(l1==2){
					$(".select-box .second").html(temp_html);
				}else if(l1==3){
					$(".select-box .third").html(temp_html);
				}else if(l1==4){
					$(".select-box .foure").html(temp_html);
				}
				
			}
		});
	}
		//填充级联数据
		function fillData1(l1,l2) {
			
			var azfs = $("#azfs").val();//安装方式
			
			var temp_html = "";
			$.ajax({
				type : 'post',
				url : 'searchBjInfo.do',
				timeout : 1321231232131213123,
				dataType:"json",
				//async : false, //是否发送异步请求	
				data:{
					squ:l2,
					type:l1,					
				},
				success:function(data){
				
				var lis  = data.list
					if (typeof(data.list) != 'undefined' ) {
						
						$.each(data.list, function(i, pro) {
							temp_html += '<li val="' + pro.SQU + '">' + pro.CYMC+"-"+pro.CPBM + '</li>';
							
						});
					} 
					if(l1==5){
						$("#pt-select-box .five").html(temp_html);
					}else if(l1==6){
						
						$('#appBtnBj').unbind();
						 $('#appBtnBj').click(function() {
							
							 var bjsqu = lis[0].SQU;//部件id
							var bjsl = $('#bjsl').val();//部件数量
							var op = 0;
							var dxSqu = $('#zjqu').val();//支架squ 
							var bjfl = $("#bjfl").val();
						
						   $("#bjop").val(0)


									$.ajax({
										type : 'post',
										url : 'addKzzjBj.do',
										timeout : 1321231232131213123,
										data : {
											'dxsqu' :dxSqu,
											'sl':bjsl,
											'bjsqu':bjsqu,
											'bjop':op,
											'bjfl':bjfl
										},
										success : function(data) {
											
											if(data == "hasExit"){
												alert("部件已存在！");
											}else{
											$('#closeWin').click();
											showBjList(1,bjfl);
											$(".select-box .second").html("");										
											$(".select-box .third").html("");										
											$(".select-box .foure").html("");
											$(".select-box .five").html("");
										
											}
										},
										error : function() {
											alert('添加出错！');
										}
									});


							 });
						 
					}
						

				}
			});
			
		}

	
	/*	//填充级联数据
		function fillData1(l1,l2) {
			
			var azfs = $("#azfs").val();//安装方式
			
			var temp_html = "";
			$.ajax({
				type : 'post',
				url : 'searchBjInfo.do',
				timeout : 1321231232131213123,
				dataType:"json",
				//async : false, //是否发送异步请求	
				data:{
					squ:l2,
					type:l1,
					
				},
				success:function(data){
				
				var lis  = data.list
					if (typeof(data.list) != 'undefined' ) {
						
						$.each(data.list, function(i, pro) {
							temp_html += '<li val="' + pro.SQU + '">' + pro.CYMC+"-"+pro.CPBM + '</li>';
							
						});
					} 
					if(l1==5){
						$("#pt-select-box .five").html(temp_html);
					}else if(l1==6){
						alert(l1)
						$("#cpmb").val(lis[0].CPBM)
						$("#cpxh").val(lis[0].BJXH)
						$("#cbdj").val(lis[0].CBDJ)
						$("#cymc").val(lis[0].CYMC)
						$("#jldw").val(lis[0].JLDW)
						$("#edhl").val(lis[0].EDHL)			
						$("#bjsqu").val(lis[0].SQU)	
					   $("#bjop").val(0)//普通部件
						$("#photoBox").html("<img src='/upload/"+lis[0].ZP+"' id = 'photo'/>")
						
						
						
						//普通部件显示内容显示
						document.getElementById("cpbmDiv").style.display = "block";
						document.getElementById("cpxhDiv").style.display = "block";
						document.getElementById("cbdjDiv").style.display = "block";
						document.getElementById("cymcDiv").style.display = "block";
						document.getElementById("jldwDiv").style.display = "block";
						document.getElementById("forEDHL").style.display = "block";
						
						
						
						//组合部件信息隐藏
						document.getElementById("zhgdlxDiv").style.display = "none";
					
						document.getElementById("zhzjxsDiv").style.display = "none";
						document.getElementById("zhbjmcDiv").style.display = "none";
						
						
					}

				}
			});
			
		}*/
		function qxbj(){
			clernData4();
			$('#pt-select-box').hide()
		}
		
		/**
		 * 清除后4级
		  * clernData:(描述)
		  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
		  * @author: 唐青
		  * @dateTime: 2018-3-14 下午1:46:44
		  * @param: 
		  * @return: void
		 */
		function clernData4(){
			
			$(".select-box .second").html("");
		
			$(".select-box .third").html("");
		
			$(".select-box .foure").html("");
			$(".select-box .five").html("");
		}
		/**
		 * 清除后3级
		  * clernData:(描述)
		  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
		  * @author: 唐青
		  * @dateTime: 2018-3-14 下午1:47:07
		  * @param: 
		  * @return: void
		 */
		function clernData3(){
			
		
		
			$(".select-box .third").html("");
		
			$(".select-box .foure").html("");
			$(".select-box .five").html("");
		}
		/**
		 * 清除后2级
		  * clernData:(描述)
		  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
		  * @author: 唐青
		  * @dateTime: 2018-3-14 下午1:47:24
		  * @param: 
		  * @return: void
		 */
		function clernData2(){
	
		
			$(".select-box .foure").html("");
			$(".select-box .five").html("");
		}
		/**
		 * 清除后1级
		  * clernData:(描述)
		  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
		  * @author: 唐青
		  * @dateTime: 2018-3-14 下午1:47:24
		  * @param: 
		  * @return: void
		 */
		function clernData1(){
		
			$(".select-box .five").html("");
		}