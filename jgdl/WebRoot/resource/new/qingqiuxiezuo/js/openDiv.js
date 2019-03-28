	var openDiv_width = 400;
	var openDiv_height = 300;

	
	window.onresize = function(){
		initDiv(openDiv_width,openDiv_height);
	};
	function bindDiv(){
		$(".titletip_div_close").bind("click",function(){
			closeDiv();
		});
		$(".openDiv").hide();
	}
	
	 function openDiv(obj ){
	 	$("#titletip_div_temp_content").html(  "<div class='"+ $("#"+obj.id).attr("class") +"' >"+$("#"+obj.id).html() +"</div>" );
	 	openDiv_width = obj.width;
	 	openDiv_height = obj.height;
	 	initDiv(openDiv_width,openDiv_height);
	 	openDivShow();
	 }
	 
	 function openIframe(obj ){
	 	$("#titletip_div_temp_content").html(  "<iframe frameborder='0' id='titletip_div_iframe' name='titletip_div_iframe' > </iframe>" );
	 	openDiv_width = obj.width;
	 	openDiv_height = obj.height;
	 	
	 	initDiv(obj.width , obj.height );
	 	$("#titletip_div_iframe").attr("src",obj.url);
	 	openDivShow();
	 	return $("#titletip_div_iframe");
	 }
	 
	 function openDivShow(){
	 	$("#titletip_div_temp_modal").show();
	 	$("#titletip_div_temp").show();
	 }
	 function closeDiv(){
	 	$("#titletip_div_temp_modal").hide();
	 	$("#titletip_div_temp").hide();
	 	$("#overDiv").css("display","none");
	 }
	 
	 
	 /**
	  * 参数 { msg : ""  ,width: 0  ,height: 0 }
	 * @param obj
	 */
	function openMsg(obj){
		$("#titletip_div_temp_content").html("<div style='text-align:center; line-height:"+obj.height+"px ;' >"+obj.msg+"</div>");
		openDiv_width = obj.width;
	 	openDiv_height = obj.height;
	 	
	 	initDiv(obj.width , obj.height );
		openDivShow();
	 }
	
	 function initDiv(w ,h){
	 		var width = document.documentElement.clientWidth;
			var height = document.documentElement.clientHeight;
			var top = document.body.scrollTop == 0 ? document.documentElement.scrollTop : document.body.scrollTop;
			var allHight = document.documentElement.scrollHeight;
			
			var t= h > height ? 0 : (height-h)/2 ;
			
			$("#titletip_div_temp").css({"width":w+"px","left": (width-w)/2 +"px","top":t+"px","height":h+"px"});
			$("#titletip_div_iframe").css({"width":w+"px","height":h+"px"});
			
			//$("#titletip_div_temp_ifr").css({"height":height-180+"px"});
			$("#titletip_div_temp_modal").css({"height":(allHight<height ? height: allHight)+"px"});
	 }