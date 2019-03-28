$(function(){
	$('body').niceScroll({ 
	    cursorcolor: "#ccc",//#CC0071 光标颜色 
	    cursoropacitymax: 1, //改变不透明度非常光标处于活动状态（scrollabar“可见”状态），范围从1到0 
	    touchbehavior: false, //使光标拖动滚动像在台式电脑触摸设备 
	    cursorwidth: "5px", //像素光标的宽度 
	    cursorborder: "0", //     游标边框css定义 
	    cursorborderradius: "5px",//以像素为光标边界半径 
	    autohidemode: false //是否隐藏滚动条 
	});
	//表格行，鼠标放上去变色
	$(".tr:odd").css("background", "#FFFCEA");
	$(".tr:odd").each(function(){
		$(this).hover(function(){
			$(this).css("background-color", "#FFE1FF");
		}, function(){
			$(this).css("background-color", "#FFFCEA");
		});
	});
	$(".tr:even").each(function(){
		$(this).hover(function(){
			$(this).css("background-color", "#FFE1FF");
		}, function(){
			$(this).css("background-color", "#fff");
		});
	}); 
	
	
	$.fn.isEmpty = function() {
		if (this.val() == '') {
			return false;
		}
	}

	/*ie6,7下拉框美化
    if ( $.browser.msie){
    	if($.browser.version == '7.0' || $.browser.version == '6.0'){
    		$('.select').each(function(i){
			   $(this).parents('.select_border,.select_containers').width($(this).width()+5); 
			 });
    		
    	}
    } */
    //listNotice();
   // loadLaws();
});
//显示和隐藏的样式
function toggleClassInfo(obj) {
		if(obj==1){
			//删除样式
	$("#2_head").removeClass("active");
	$("#2_content").removeClass("active");
	$("#3_head").removeClass("active");
	$("#3_content").removeClass("active");
	$("#4_head").removeClass("active");
	$("#4_content").removeClass("active");
	$("#3_head").css("display","none");
	//新增样式
	$("#1_head").addClass("active");
	$("#1_content").addClass("active");
			}
		if(obj==2){
			//删除样式
	$("#1_head").removeClass("active");
	$("#1_content").removeClass("active");
	$("#3_head").removeClass("active");
	$("#3_content").removeClass("active");
	$("#4_head").removeClass("active");
	$("#4_content").removeClass("active");
	//新增样式
	$("#2_head").addClass("active");
	$("#2_content").addClass("active");
	$("#3_head").css("display","block");

			}
}
//显示和隐藏的样式
function toggleClassInfo1(obj) {
		if(obj==1){
			//删除样式
	$("#2_head_1").removeClass("active");
	$("#2_content_1").removeClass("active");
	//新增样式
	$("#1_head_1").addClass("active");
	$("#1_content_1").addClass("active");
			}
		if(obj==2){
			//删除样式
	$("#1_head_1").removeClass("active");
	$("#1_content_1").removeClass("active");
	       //删除样式
	$("#2_head_1").addClass("active");
	$("#2_content_1").addClass("active");
			}
}
/**
 * 取消事件冒泡
 * @param e
 */
function stopBubble(e){
	  //一般用在鼠标或键盘事件上
	  if(e && e.stopPropagation){
	  //W3C取消冒泡事件
	  e.stopPropagation();
	  }else{
	  //IE取消冒泡事件
	  window.event.cancelBubble = true;
	  }
	  };

function colseWindow(){
	$("#view").css("display","none");
}

