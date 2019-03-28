
/**
 * 2017-4-25下午3:03:15
 * TODO 全局变量 获取人员信息json数组
 */
var json = personJson;

$().ready(function(){
	//获取当前布控人员基本信息
	setPersonMsg();
	//获取人员轨迹
	getPersonnelTrajectory();
});

/**
  * setPersonMsg:(获取当前布控人员基本信息)
  * TODO(已部署)
  * @author: 黄月
  * @dateTime: 2017-4-24 下午4:29:46
  * @param: 
  * @return: void
 */
function setPersonMsg(){
	$('#name').html(json.XM);
	$('#mz').html("");
	$('#sfzhm').html(json.GMSFHM);
	$('#address').html(json.HJDZ);
	var ry = "";
	switch(parseInt(json.RYLX)){
	case 0 : ry = "在逃人员";break;
	case 1 : ry = "重点人员";break;
	case 2 : ry = "临控人员";break;
	case 3 : ry = "案件嫌疑人";break;
	default : ry = json.RYLX;
	}
	$('#personType').html(ry);
}

/**
  * getPersonnelTrajectory:(获取人员轨迹)
  * TODO(已部署人员信息)
  * @author: 黄月
  * @dateTime: 2017-4-25 下午2:45:29
  * @param: 
  * @return: void
 */
function getPersonnelTrajectory(){
	$.ajax({
		url : 'selectTrajectory.do',
		type : 'post',
		async : false,
		dataType : 'json',
		data : {
			gmsfhm : json.GMSFHM,
			isnew : json.ISNEW
		},
		success : function(data) {
			if (data.status == "success") {
				var html = '';
				var list = data.message;
				if (list.length > 0) {
					for ( var i = 0; i < list.length; i++) {
						html += '<div class="cd-timeline-block">';
						html += i % 2 == 0 ? '<div class="cd-timeline-img cd-picture"></div>' : '<div class="cd-timeline-img cd-movie"></div>';
						html += '<div class="cd-timeline-content">';
						html += '<h2>' + list[i].TIME1 + '</h2>';
						html += getDetileHTML(list[i].TYPE, list[i].STR);
						html += '<a href="javascript:void(0);" class="cd-read-more" style="float:left;">' + list[i].TYPE + '信息</a>';
						html += '<span class ="icon32 icon-carat-1-s" style="float:right;cursor:pointer;" onclick="tog(this);"></span>';
						html += '</div></div>';
					}
				} else {
					html += '<div class="cd-timeline-block">';
					html += '<div class="cd-timeline-img cd-picture"></div>';
					html += '<div class="cd-timeline-content">';
					html += '<h2>未查询到数据</h2>';
					html += '<p></p>';
					html += '<a href="javascript:void(0);" class="cd-read-more">暂无</a>';
					html += '</div></div>';
				}
				$('#cd-timeline').html(html);
			} else {
				alert("数据查询出错！错误信息：" + data.message);
			}
		},
		error : function () {
			alert("数据加载错误！");
		}
	});
}

/**
  * getDetileHTML:(获取流动详细信息)
  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
  * @author: 黄月
  * @dateTime: 2017-5-18 下午5:52:20
  * @param: @param type 流动类型
  * @param: @param str 详细信息字符串
  * @return: void
 */
function getDetileHTML(type, str){
	var strArr = str.split(";");
	var text = "";
	for ( var i = 0; i < strArr.length; i++) {
		if (i >= 2) {
			text += "<div class ='moreText' style='display:none;'>";
			text += "<p>" + strArr[i] + "</p>";
			text += "</div>";
		} else {
			text += "<p>" + strArr[i] + "</p>";
		}
	}
	return text;
}

function tog(index){
	if ($(index).hasClass("icon-carat-1-s")) {
		$(index).removeClass("icon-carat-1-s");
		$(index).addClass("icon-carat-1-n");
		$(index).attr("title","收起");
	} else {
		$(index).removeClass("icon-carat-1-n");
		$(index).addClass("icon-carat-1-s");
		$(index).attr("title","展开");
	}
	$(index).parents('.cd-timeline-content').find('.moreText').toggle();
}