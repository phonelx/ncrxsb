
	var l1 = 0,
	l2 = 0;
	var cname1, cname2, cname3;
	var cid1, cid2, cid3;
	var canClick1 = !0;
	var canClose1 = !1;
	var azfssqu = "";
	
	$(function(){
		bjInfo();
	});
	
	
	
	/*$('.select-box1').css({
		left: $('.select-res1').offset().left,
		top: $('.select-res').offset().top + $('.select-res').outerHeight(true)
		top: $('.select-res1').offset().top + $('.select-res1')
		});
	*/
	function bjInfo(){
		
		 clernData4();
			
			$('.select-box1').show();
				if (canClick1) {
					
					$('ul', $('.select-box1')).html('');
					var ty = "1"
					var squ ="-1" 	
					fillData2(ty,squ);	
					
					canClick1 = !1;
				}
	}
	/*$('span', $('.select-res1')).on('click', function() {
		
	});*/
		
		
	$('span', $('.select-box1')).on("click", function() {
		//canClose1 ? $('.select-box1').hide() : alert('请选择下级品类！');
		$('.select-box1').hide();
		canClick1 = !0;
	});
	
	
	$('.select-res1').on('click', 'a', function() {
		$(this).parent().remove();
		$('.select-box1').css({
			/*top: $('.select-res').offset().top + $('.select-res').outerHeight(true)*/
			top: $('.select-res1').offset().top + $('.select-res1')
		});
	})
	
/*	$('ul.fir1', $('.select-box1')).on('click', 'li', function() {
		$(this).addClass('selected').siblings().removeClass('selected');
		$('ul.first1').html('');
		var ty = "1"
		var squ ="-1" 				
		fillData2(ty,squ);	
		azfssqu = $(this).attr("val");
		
		l1 = $(this).index();
		cname1 = $(this).text();
		cid1 = $(this).attr('val');
		canClose1 = !1;
		$('input.cid', $('.select-res1')).val(cid1);
		$('input.cname', $('.select-res1')).val(cname1);
	});*/
	$('ul.first1', $('.select-box1')).on('click', 'li', function() {
		 clernData4();
		$(this).addClass('selected').siblings().removeClass('selected');
		$('ul.third1').html('');
		var ty = "2"		
	
		if($(this).attr("jd")=="1"){
			fillData3(5,$(this).attr("val"))
		}else{
			fillData2(ty,$(this).attr("val"));
		}
		l1 = $(this).index();
		cname1 = $(this).text();
		cid1 = $(this).attr('val');
		canClose1 = !1;
		$('input.cid', $('.select-res1')).val(cid1);
		$('input.cname', $('.select-res1')).val(cname1);
		$('.second1').show();
	});
	
	
	$('ul.second1', $('.select-box1')).on('click', 'li', function() {
		 clernData3();
		$(this).addClass('selected').siblings().removeClass('selected');
		var ty = "3"	
			
		if($(this).attr("jd")=="1"){
			fillData3(5,$(this).attr("val"))
		}else{
			fillData2(ty,$(this).attr("val"));
		}	
		l2 = $(this).index();
		cname2 = $(this).text();
		cid2 = $(this).attr('val');
		canClose1 = !1;
	/*	$('input.cid1', $('.select-res1')).val(cid1 + ',' + cid2);
		$('input.cname', $('.select-res1')).val(cname1 + ',' + cname2);*/
		$('.third1').show();
	});
	
	$('ul.third1', $('.select-box1')).on('click', 'li', function() {
		 clernData2();
		$(this).addClass('selected').siblings().removeClass('selected');
		var ty = "4"
		
		/*fillData2(ty,$(this).attr("val"));*/
		if($(this).attr("jd")=="1"){
			fillData3(5,$(this).attr("val"))
		}else{
			fillData2(ty,$(this).attr("val"));
		}
		l2 = $(this).index();
		cname2 = $(this).text();
		cid2 = $(this).attr('val');
		canClose1 = !1;
		/*$('input.cid1', $('.select-res1')).val(cid1 + ',' + cid2);
		$('input.cname', $('.select-res1')).val(cname1 + ',' + cname2);*/
		$('.foure1').show();
	});
	

	$('ul.foure1', $('.select-box1')).on('click', 'li', function() {
		 clernData1();
		$(this).addClass('selected').siblings().removeClass('selected');
		var ty = "5"		
		fillData3($(this).attr("val"),1);
		l2 = $(this).index();
		cname2 = $(this).text();
		cid2 = $(this).attr('val');
		canClose1 = !1;
	/*	$('input.cid', $('.select-res1')).val(cid1 + ',' + cid2);
		$('input.cname', $('.select-res1')).val(cname1 + ',' + cname2);*/
		//$('.five1').show();
	});
	
	
	//填充级联数据
	function fillData2(l1,l2) {
		
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
						temp_html += '<li val="' + pro.SQU + '" jd="'+pro.JD+'">' + pro.LBMC + '</li>';
						
					});
				} 
				if(l1==1){
					
					$(".select-box1 .first1").html(temp_html);
				}else if(l1==2){
					$(".select-box1 .second1").html(temp_html);
				}else if(l1==3){
					$(".select-box1 .third1").html(temp_html);
				}else if(l1==4){
					$(".select-box1 .foure1").html(temp_html);
				}
				
			}
		});
	}
	var records=0;
	var currentPage=0;
	var total=0;
	var pageShowPage=5;//显示多少个人分页标签
	var pageRowNum=2;//每页显示记录数
	var pager=1;
		//填充级联数据
		function fillData3(l2,pager) {
		//	azfssqu = $("#fsdm").val();
	
			var temp_html = "";
			$.ajax({
				type : 'post',
				url : 'queryBjInfo.do',
				timeout : 1321231232131213123,
				dataType:"json",
				//async : false, //是否发送异步请求	
				data:{
					squ:l2,
					type:5,
					rows:pageRowNum,
					page:pager,
				},
				success:function(data){
			
				var lis  = data.rows
				records=data.total;
				currentPage=data.pageNumber;
				total = data.pageCnt;
				pageRowNum=data.pageSize;
				var html = ""; 
				 
				html +="<thead><tr><th>部件名称</th><th>部件型号</th><th>力学参数</th><th>数量</th></tr></thead><tbody> "
				for(var i=0,len=lis.length;i<len;i++){
					html +="<tr><input type='hidden' class='input' value='"+lis[i].SQU+"'/><td>"+lis[i].CYMC+"</td><td>"+lis[i].BJXH+"</td>"
					html +="<td>"+lis[i].LXCS+"</td><td><input type='text' onblur='inputOnBlur(this)'/></td></tr>"
				}
				html +="</tbody>";
				//alert(html)
			$("#bj_body").html(html);
			pageDivbj(records,total,currentPage);
					
					
				}
			});
			
		}
		
		function pageDivbj(records,total,currentPage){
			var pageTotal=total;// 总共多少页
			var pageCurrentPage=currentPage;//当前页
			var pageRecords=records;//总记录数
			var countt="";
			var outstr="";
			pageTotal=parseInt(pageRecords/pageRowNum);
			if(pageRecords%pageRowNum>0){
				pageTotal=pageTotal+1;
			}
		        if(pageTotal<=pageShowPage){
		                for (var count=1;count<=pageTotal;count++){
		                        if (count<10) {countt ="0"+count+"";}else{countt =""+count+"";}
		                        if(count!=pageCurrentPage){
		                             outstr = outstr + "<li tag='"+count+"' class='pageCk'><a href='javascript:void(0);'>"+countt+"</a></li>";
		                        }else{
		                        	 outstr = outstr + "<li class='active'><a href='javascript:void(0);'>"+countt+"</a></li>";
		                        }
		                }
		        }
		        if(pageTotal>pageShowPage){
		                if(parseInt((pageCurrentPage-1)/pageShowPage) == 0){
		                        outstr = outstr + "<li><a><<</a></li> ";
		                        for (var count=1;count<=pageShowPage;count++){
		                                if (count<10) {countt ="0"+count+"";}else{countt =""+count+"";}
		                                if(count!=pageCurrentPage){
		                                	outstr = outstr + "<li tag='"+count+"' class='pageCk'><a  href='javascript:void(0);'>"+countt+"</a></li>";
		                                	
		                                }else{
		                                	 outstr = outstr + "<li class='active'><a href='javascript:void(0);'>"+countt+"</a></li>";
		                                }
		                        }
		                        outstr = outstr+"<li tag='"+count+"' class='pagePk'><a href='javascript:void(0)'>>></a></li>";
		                }else if(parseInt((pageCurrentPage-1)/pageShowPage) == parseInt(pageTotal/pageShowPage)){
		                        outstr = outstr + "<li tag='"+(parseInt((pageCurrentPage-1)/pageShowPage)*pageShowPage)+"' class='pagePk'><a href='javascript:void(0)'><<</a></li> ";
		                        for (var count=parseInt(pageTotal/pageShowPage)*pageShowPage+1;count<=pageTotal;count++){
		                                if (count<10) {countt ="0"+count+"";}else{countt =""+count+"";}
		                                if(count!=pageCurrentPage){
		                                	outstr = outstr + "<li  tag='"+count+"' class='pageCk'><a href='javascript:void(0);'>"+countt+"</a></li>";
		                                }else{
		                                	 outstr = outstr + "<li class='active'><a href='javascript:void(0);'>"+countt+"</a></li>";
		                                }
		                        }
		                        outstr = outstr + "<li><a href='javascript:void(0)'>>></a></li>";
		                }else{
		                        outstr = outstr + "<li tag='"+(parseInt((pageCurrentPage-1)/pageShowPage)*pageShowPage)+"' class='pagePk'><a href='javascript:void(0)'><<</a></li> ";
		                        for (var count=parseInt((pageCurrentPage-1)/pageShowPage)*pageShowPage+1;count<=parseInt((pageCurrentPage-1)/pageShowPage)*pageShowPage+pageShowPage;count++){
		                                if (count<10) {countt ="0"+count+"";}else{countt =""+count+"";}
		                                if(count!=pageCurrentPage){
		                                	outstr = outstr + "<li tag='"+count+"' class='pageCk'><a href='javascript:void(0);'>"+countt+"</a></li>";
		                                }else{
		                                	
		                                        outstr = outstr + "<li class='active'><a href='javascript:void(0);'>"+countt+"</a></li>";
		                                }
		                        }
		                        outstr = outstr + "<li  tag='"+count+"' class='pageCk'><a href='javascript:void(0);'>>></a></li>";
		                }
		    }    
		        //共"+pageTotal+"页|"+pageRecords+"条数据|第"+pageCurrentPage+"页 每页" + pageRowNum +"个
		    
		        	 $("#pageDivbj").html("<div class='pagination pagination-centered'  align='right'><ul >" + outstr + "<a href='javascript:void(0);'>共"+pageTotal+"页/"+pageRecords+"条数据</a></li></ul></div>");
		        	// alert(type+"==="+$("#pageDiv").html());
		       
		  
		    outstr = "";
		    $(".pageCk").unbind();
		    $(".pageCk").bind('click',function(){
		    	
		    	fillData3(cid2,$(this).attr("tag"));
		    	//$("#pd").val($("#pd").val())
			});
		    $(".pagePk").unbind();
		    $(".pagePk").bind('click',function(){
		    
		    	fillData3(cid2,$(this).attr("tag"));
		    	//$("#pd").val($("#pd").val())
			});
		}	
		
		
		
		function pageDivw(records,total,currentPage){
			
			var pageTotal=total;// 总共多少页
			var pageCurrentPage=currentPage;//当前页
			var pageRecords=records;//总记录数
			var countt="";
			var outstr="";
			pageTotal=parseInt(pageRecords/pageRowNum);
			if(pageRecords%pageRowNum>0){
				pageTotal=pageTotal+1;
			}
		        if(pageTotal<=pageShowPage){
		                for (var count=1;count<=pageTotal;count++){
		                        if (count<10) {countt ="0"+count+"";}else{countt =""+count+"";}
		                        if(count!=pageCurrentPage){
		                             outstr = outstr + "<li tag='"+count+"' class='pageCk'><a href='javascript:void(0);'>"+countt+"</a></li>";
		                        }else{
		                        	 outstr = outstr + "<li class='active'><a href='javascript:void(0);'>"+countt+"</a></li>";
		                        }
		                }
		        }
		        if(pageTotal>pageShowPage){
		                if(parseInt((pageCurrentPage-1)/pageShowPage) == 0){
		                        outstr = outstr + "<li><a><<</a></li> ";
		                        for (var count=1;count<=pageShowPage;count++){
		                                if (count<10) {countt ="0"+count+"";}else{countt =""+count+"";}
		                                if(count!=pageCurrentPage){
		                                	outstr = outstr + "<li tag='"+count+"' class='pageCk'><a  href='javascript:void(0);'>"+countt+"</a></li>";
		                                	
		                                }else{
		                                	 outstr = outstr + "<li class='active'><a href='javascript:void(0);'>"+countt+"</a></li>";
		                                }
		                        }
		                        outstr = outstr+"<li tag='"+count+"' class='pagePk'><a href='javascript:void(0)'>>></a></li>";
		                }else if(parseInt((pageCurrentPage-1)/pageShowPage) == parseInt(pageTotal/pageShowPage)){
		                        outstr = outstr + "<li tag='"+(parseInt((pageCurrentPage-1)/pageShowPage)*pageShowPage)+"' class='pagePk'><a href='javascript:void(0)'><<</a></li> ";
		                        for (var count=parseInt(pageTotal/pageShowPage)*pageShowPage+1;count<=pageTotal;count++){
		                                if (count<10) {countt ="0"+count+"";}else{countt =""+count+"";}
		                                if(count!=pageCurrentPage){
		                                	outstr = outstr + "<li  tag='"+count+"' class='pageCk'><a href='javascript:void(0);'>"+countt+"</a></li>";
		                                }else{
		                                	 outstr = outstr + "<li class='active'><a href='javascript:void(0);'>"+countt+"</a></li>";
		                                }
		                        }
		                        outstr = outstr + "<li><a href='javascript:void(0)'>>></a></li>";
		                }else{
		                        outstr = outstr + "<li tag='"+(parseInt((pageCurrentPage-1)/pageShowPage)*pageShowPage)+"' class='pagePk'><a href='javascript:void(0)'><<</a></li> ";
		                        for (var count=parseInt((pageCurrentPage-1)/pageShowPage)*pageShowPage+1;count<=parseInt((pageCurrentPage-1)/pageShowPage)*pageShowPage+pageShowPage;count++){
		                                if (count<10) {countt ="0"+count+"";}else{countt =""+count+"";}
		                                if(count!=pageCurrentPage){
		                                	outstr = outstr + "<li tag='"+count+"' class='pageCk'><a href='javascript:void(0);'>"+countt+"</a></li>";
		                                }else{
		                                	
		                                        outstr = outstr + "<li class='active'><a href='javascript:void(0);'>"+countt+"</a></li>";
		                                }
		                        }
		                        outstr = outstr + "<li  tag='"+count+"' class='pageCk'><a href='javascript:void(0);'>>></a></li>";
		                }
		    }    
		        //共"+pageTotal+"页|"+pageRecords+"条数据|第"+pageCurrentPage+"页 每页" + pageRowNum +"个　
		   $("#pageDivbj").html("<div class='pagination pagination-centered'  align='right'><ul>" + outstr + "<a href='javascript:void(0);'>共"+pageTotal+"页/"+pageRecords+"条数据</a></li></ul></div>");
		    outstr = "";
		    $(".pageCk").unbind();
		    $(".pageCk").bind('click',function(){
		    	
		    	fillData3(cid2,$(this).attr("tag"));
		    	//$("#pd").val($("#pd").val())
			});
		    $(".pagePk").unbind();
		    $(".pagePk").bind('click',function(){
		    
		    	fillData3(cid2,$(this).attr("tag"));
		    	//$("#pd").val($("#pd").val())
			});
		}	
		//填充级联数据
		/*function fillData4(l1,l2) {
		//	azfssqu = $("#fsdm").val();
		
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
					//azfs:azfssqu,
				},
				success:function(data){
				
				var lis  = data.list
					
				
						
						$("#cpmb1").val(lis[0].CPBM)
						$("#cpxh1").val(lis[0].BJXH)
						$("#cbdj1").val(lis[0].CBDJ)
						$("#cymc1").val(lis[0].CYMC)
						$("#jldw1").val(lis[0].JLDW)							
						$("#photoBox1").html("<img src='/upload/"+lis[0].ZP+"' />")
						$("#ZBJSQU1").val(lis[0].SQU)	
					
					
					
					
				}
			});
			
		}*/
		function qx1(){
			 clernData4();
			$('.select-box1').hide()
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
		
			$(".select-box1 .second1").html("");
		
			$(".select-box1 .third1").html("");
		
			$(".select-box1 .foure1").html("");
			$(".select-box1 .five1").html("");
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
			
		
		
			$(".select-box1 .third1").html("");
		
			$(".select-box1 .foure1").html("");
			$(".select-box1 .five1").html("");
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
	
		
			$(".select-box1 .foure1").html("");
			$(".select-box1 .five1").html("");
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
		
			$(".select-box1 .five1").html("");
		}