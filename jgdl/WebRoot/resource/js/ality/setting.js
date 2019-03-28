var root = $("#root").attr("value");
function gotoMag(num){
	if(num==1){
		window.location.href=root+"gotoSystemSetIndex.do";
	}
	
}

function borderIcon(id){
	var n = $(".desktop_icon img");
	for(var i=0; i<n.length; i++){
		$(".desktop_icon img:eq("+i+")").css({"background-color":""});
	}
	 $("#"+id).css("background-color","#369bd7");	
}