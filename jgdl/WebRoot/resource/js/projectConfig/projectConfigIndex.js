





/**
  * getSelectOfObject:(加载下拉列表)
  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
  * @author: 黄月
  * @dateTime: 2017-11-22 下午5:25:13
  * @param: 
  * @return: void
 */
function getSelectOfObject(selectID){
	$.ajax({
		type : 'post',
		url : 'getSelectOfObject.do',
		timeout : 1321231232131213123,
		dataType :'json',
		data : {
			type : selectID
		},
		success : function(data) {
			if (data.length > 0) {
				var html = "<option value=''>—— ——请选择—— ——</option>";
				for ( var i = 0; i < data.length; i++) {
					html += "<option value='" + data[i] + "'>" + data[i] + "</option>";
				}
				$('#' + selectID + "").html(html);
			}
		}
	});
}



/**
  * doSearch:(执行检索)
  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
  * @author: 黄月
  * @dateTime: 2017-11-13 上午9:57:04
  * @param: 
  * @return: void
 */
function doSearch(){
	//获取输入的查询
	var searchWord = $('#input_search').val();
	if (searchWord == "关键字搜索") {
		searchWord = "";
	}
	
	listProject(searchWord);
}

