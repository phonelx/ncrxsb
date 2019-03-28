
	var l1 = 0,
	l2 = 0;
	var cname1, cname2, cname3;
	var cid1, cid2, cid3;
	var canClick = !0;
	var canClose = !1;
	var azfssqu = "";
	
	$('.select-box').css({
		left: $('.select-res').offset().left,
		/*top: $('.select-res').offset().top + $('.select-res').outerHeight(true)*/
		top: $('.select-res').offset().top + $('.select-res')
		});
	$('span', $('.select-res')).on('click', function() {
		
	$('.select-box').show();
		if (canClick) {
			
			$('ul', $('.select-box')).html('');
		
			fillAzfs();
			
			canClick = !1;
		}
	});
		
		
	$('span', $('.select-box')).on("click", function() {
		//canClose ? $('.select-box').hide() : alert('请选择下级品类！');
		$('.select-box').hide();
		canClick = !0;
	});
	
	
	$('.select-res').on('click', 'a', function() {
		$(this).parent().remove();
		$('.select-box').css({
			/*top: $('.select-res').offset().top + $('.select-res').outerHeight(true)*/
			top: $('.select-res').offset().top + $('.select-res')
		});
	})
	
	$('ul.fir', $('.select-box')).on('click', 'li', function() {
		$(this).addClass('selected').siblings().removeClass('selected');
		$('ul.first').html('');
		var ty = "1"
		var squ ="-1" 				
		fillData(ty,squ);	
		azfssqu = $(this).attr("val");
		
		l1 = $(this).index();
		cname1 = $(this).text();
		cid1 = $(this).attr('val');
		canClose = !1;
		$('input.cid', $('.select-res')).val(cid1);
		$('input.cname', $('.select-res')).val(cname1);
	});
	
	$('ul.first', $('.select-box')).on('click', 'li', function() {
		$(this).addClass('selected').siblings().removeClass('selected');
		$('ul.third').html('');
		var ty = "2"		
		fillData(ty,$(this).attr("val"));
		l1 = $(this).index();
		cname1 = $(this).text();
		cid1 = $(this).attr('val');
		canClose = !1;
		$('input.cid', $('.select-res')).val(cid1);
		$('input.cname', $('.select-res')).val(cname1);
	});
	
	
	$('ul.second', $('.select-box')).on('click', 'li', function() {
		$(this).addClass('selected').siblings().removeClass('selected');
		var ty = "3"		
		fillData(ty,$(this).attr("val"));
		l2 = $(this).index();
		cname2 = $(this).text();
		cid2 = $(this).attr('val');
		canClose = !1;
		$('input.cid', $('.select-res')).val(cid1 + ',' + cid2);
		$('input.cname', $('.select-res')).val(cname1 + ',' + cname2);
	});
	
	$('ul.third', $('.select-box')).on('click', 'li', function() {
		$(this).addClass('selected').siblings().removeClass('selected');
		var ty = "4"		
		fillData(ty,$(this).attr("val"));
		l2 = $(this).index();
		cname2 = $(this).text();
		cid2 = $(this).attr('val');
		canClose = !1;
		$('input.cid', $('.select-res')).val(cid1 + ',' + cid2);
		$('input.cname', $('.select-res')).val(cname1 + ',' + cname2);
	});
	$('ul.foure', $('.select-box')).on('click', 'li', function() {
		$(this).addClass('selected').siblings().removeClass('selected');
		var ty = "5"
		
			
		fillData1(ty,$(this).attr("val"));
		l2 = $(this).index();
		cname2 = $(this).text();
		cid2 = $(this).attr('val');
		canClose = !1;
		$('input.cid', $('.select-res')).val(cid1 + ',' + cid2);
		$('input.cname', $('.select-res')).val(cname1 + ',' + cname2);
	});
	
	$('ul.five', $('.select-box')).on('click', 'li', function() {
		$(this).addClass('selected').siblings().removeClass('selected');
		var ty = "6"
		var id = $(this).attr('val')
		$('#bjBtn').unbind();
		$("#bjBtn").click(function(){
			fillData1(ty,id)
			$('.select-box').hide()
		});
		cname3 = $(this).text();
		cid3 = $(this).attr('val');
		canClose = !0;
		/*var hasExist = !1;
		$('.select-res').find("p").each(function() {
			if ($(this).text().split(' > ')[2] == cname3) hasExist = !0;
		})
		hasExist ? alert('所选品类已被添加！') : $('.select-res').append('<p>' + cname1 + ' > ' + cname2 + ' > ' + cname3 + '<a title="删除"><i class="icon-remove"></i></a><input type="hidden" value="' + cid1 + ',' + cid2 + ',' + cid3 + '" name="cid[]" /><input type="hidden" value="' + cname1 + ',' + cname2 + ',' + cname3 + '" name="cname[]" /></p>');
		$('.select-box').css({
			top: $('.select-res').offset().top + $('.select-res').outerHeight(true)
		});
		$('input.cid', $('.select-res')).val(cid1 + ',' + cid2 + ',' + cid3);
		$('input.cname', $('.select-res')).val(cname1 + ',' + cname2 + ',' + cname3);*/
	});
	function fillAzfs(){
		var temp_html = "";
		$.ajax( {
			type : 'post',
			url : 'queryAzlxMenu.do',
			dataType : 'json',
			async : false,
			data : {
				
			},
			success:function(data1){
				
				if (typeof(data1.azlxmenu) != 'undefined' ) {
					
					$.each(data1.azlxmenu, function(i, pro) {
						temp_html += '<li val="' + pro.SQU + '">' + pro.MS + '</li>';
						
					});
				} 
				
				$(".select-box .fir").html(temp_html);
			}
		});
	}
	//填充级联数据
	function fillData(l1,l2) {
	
		var temp_html = "";
		$.ajax({
			type : 'post',
			url : 'queryLbInfo.do',
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
						temp_html += '<li val="' + pro.SQU + '">' + pro.LBMC + '</li>';
						
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
			
				var temp_html = "";
				$.ajax({
					type : 'post',
					url : 'queryBjInfo.do',
					timeout : 1321231232131213123,
					dataType:"json",
					//async : false, //是否发送异步请求	
					data:{
						squ:l2,
						type:l1,
						azfs:azfssqu,
					},
					success:function(data){
					
					var lis  = data.list
						if (typeof(data.list) != 'undefined' ) {
							
							$.each(data.list, function(i, pro) {
								temp_html += '<li val="' + pro.SQU + '">' + pro.CYMC+"-"+pro.CPBM + '</li>';
								
							});
						} 
					if(l1==5){
						$(".select-box .five").html(temp_html);
					}else if(l1==6){
						/*$("#cpmb").val(lis[0].CPBM)
						$("#cpxh").val(lis[0].BJXH)
						$("#cbdj").val(lis[0].CBDJ)
						$("#cymc").val(lis[0].CYMC)
						$("#jldw").val(lis[0].JLDW)
						$("#edhl").val(lis[0].EDHL)			
						$(".photoBox").html("<img src='/upload/"+lis[0].ZP+"' />")
						$("#ZBJSQU").val(lis[0].SQU)	*/
					}

					}
				});

			
		}
		
	/*	//填充级联数据
		function fillData2(l1,l2) {

				var temp_html = "";
				$.ajax({
					type : 'post',
					url : 'queryBjInfo.do',
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
							$(".select-box .five").html(temp_html);
						}else if(l1==6){
							$("#cpmb").val(lis[0].CPBM)
							$("#cpxh").val(lis[0].BJXH)
							$("#cbdj").val(lis[0].CBDJ)
							$("#cymc").val(lis[0].CYMC)
							$("#jldw").val(lis[0].JLDW)
							$("#edhl").val(lis[0].EDHL)			
							$(".photoBox").html("<img src='/upload/"+lis[0].ZP+"' />")
							$("#ZBJSQU").val(lis[0].SQU)	
						}
						
						
						
					}
				});
				
	
			
		}*/
		
		function qx(){
			$('.select-box').hide()
		}