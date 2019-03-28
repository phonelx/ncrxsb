$().ready(function(){
	foldingWindow();
});

/**
  * foldingWindow:(折叠窗)
  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
  * @author: 黄月
  * @dateTime: 2017-5-4 上午10:37:49
  * @param: 
  * @return: void
 */
function foldingWindow(){
	$('.module_link').click(function(){
		//下拉列表改变
		if ($(this).next('.child_list').hasClass("on")) {
			$(this).find('.icon').removeClass('icon-triangle-n');
			$(this).find('.icon').addClass('icon-triangle-s');
			$(this).next(".child_list").slideUp(300,function(){
                $(this).removeClass("on");
            });
		} else {
			$(this).parents("ul").find('.icon').removeClass('icon-triangle-n');
			$(this).parents("ul").find('.icon').addClass('icon-triangle-s');
            $(this).parents("ul").find(".child_list").slideUp(300,function(){
                $(this).parents("ul").find(".child_list").removeClass("on");
            });
            $(this).find('.icon').removeClass('icon-triangle-s');
            $(this).find('.icon').addClass('icon-triangle-n');
            $(this).next(".child_list").slideDown(300,function(){
                $(this).addClass("on");
            });
        }
	});
}