$(function(){
	findTheme();
	
});

function findTheme() {
				$.ajax({
					type : "post",
					url : "findThemeCatalog.do",
					timeout : 1321231232131213123,
					dataType:"json",
					success : function(data) {
							var src = data.list[0].PHYURL;
							parent.myLib.desktop.wallpaper.init(src);
							$("#wallpapers").find("img").attr("src",src);
					},
					error : function() {
						alert("用户背景图加载出错！");
					}
				});
			}	