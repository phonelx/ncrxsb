<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!--session1-->
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String path = request.getContextPath();
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
%>
<html>
<head>
<base href="<%=basePath%>" target="_self" />
<%
    response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setHeader("Cache-Control", "no-store");
			response.setDateHeader("Expires", 0);
			response.flushBuffer();
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport"
	content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />

<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="resource/images/splash/splash-icon.png" />
<link rel="apple-touch-startup-image"
	href="resource/images/splash/splash-screen.png"
	media="screen and (max-device-width: 320px)" />
<link rel="apple-touch-startup-image"
	href="resource/images/splash/splash-screen_402x.png"
	media="(max-device-width: 480px) and (-webkit-min-device-pixel-ratio: 2)" />
<link rel="apple-touch-startup-image" sizes="640x1096"
	href="resource/images/splash/splash-screen_403x.png" />
<link rel="apple-touch-startup-image" sizes="1024x748"
	href="resource/images/splash/splash-screen-ipad-landscape"
	media="screen and (min-device-width : 481px) and (max-device-width : 1024px) and (orientation : landscape)" />
<link rel="apple-touch-startup-image" sizes="768x1004"
	href="resource/images/splash/splash-screen-ipad-portrait.png"
	media="screen and (min-device-width : 481px) and (max-device-width : 1024px) and (orientation : portrait)" />
<link rel="apple-touch-startup-image" sizes="1536x2008"
	href="resource/images/splash/splash-screen-ipad-portrait-retina.png"
	media="(device-width: 768px)	and (orientation: portrait)	and (-webkit-device-pixel-ratio: 2)" />
<link rel="apple-touch-startup-image" sizes="1496x2048"
	href="resource/images/splash/splash-screen-ipad-landscape-retina.png"
	media="(device-width: 768px)	and (orientation: landscape)	and (-webkit-device-pixel-ratio: 2)" />

<title>样式css3</title>
<link href="resource/styles/style.css" 			rel="stylesheet" type="text/css" />
<link href="resource/styles/framework.css"		rel="stylesheet" type="text/css" />
<link href="resource/styles/owl.carousel.css" 	rel="stylesheet" type="text/css" />
<link href="resource/styles/owl.theme.css"  	rel="stylesheet" type="text/css" />
<link href="resource/styles/swipebox.css" 		rel="stylesheet" type="text/css" />
<link href="resource/styles/colorbox.css" 		rel="stylesheet" type="text/css" />
<link href="resource/css/type.css" 				rel="stylesheet" type="text/css" />

<script type="text/javascript" src="resource/scripts/jquery.js"></script>
<script type="text/javascript" src="resource/scripts/jqueryui.js"></script>
<script type="text/javascript" src="resource/scripts/owl.carousel.min.js"></script>
<script type="text/javascript" src="resource/scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="resource/scripts/jquery.swipebox.js"></script>
<script type="text/javascript" src="resource/scripts/colorbox.js"></script>
<script type="text/javascript" src="resource/scripts/snap.js"></script>
<script type="text/javascript" src="resource/scripts/contact.js"></script>
<script type="text/javascript" src="resource/scripts/custom.js"></script>
<script type="text/javascript" src="resource/scripts/framework.js"></script>
<script type="text/javascript" src="resource/scripts/framework.launcher.js"></script>
<script type="text/javascript" src="resource/new/bootstrap/js/bootstrap-alert.js"></script>
<script type="text/javascript" src="resource/new/qingqiuxiezuo/js/jquery-1.8.3.js" language="javascript"></script>
<script type="text/javascript" src="resource/js/addapplication/addapplication.js"></script>
<script type="text/javascript">
	//初始化
	$(document).ready(function(){
				//点击关闭事件
				$("#selectItemClose").click(function() {
					$("#selectItem2").hide();
				});

				//单选
				$('#test').find('input[type=checkbox]').bind(
						'click',
						function() {
							$('#test').find('input[type=checkbox]').not(this)
									.attr("checked", false);
						});
				//点击显示
				$("#in").click(function() {
					$("#selectItem2").show();
				});
				//清理静态记录数
				clearVariable();
				//获取焦点弹出输入件
				$("#idcard").focus(function(){
				gbclose();
				closeing();
				$("#showIDCON").show();
				});
				//及时更新人员类型 的选择提醒
				$("#rylxselected").change(function(){
				var myselect=document.getElementById("rylxselected");
				var index=myselect.selectedIndex ; 
	  			var rylx = myselect.options[index].value;
	  			if(("0"==rylx)||("1"==rylx)||("2"==rylx)||("3"==rylx))
				$("#rylxselected").css("border","1px solid #cebfbf");
				});
				//及时更新审批人的选择提醒
				$("#selectl").change(function(){
				var lmyselect=document.getElementById("selectl");
				var index=lmyselect.selectedIndex ; 
	  			var spr = lmyselect.options[index].value;
	  			if("tolerant" != spr){
	  				$("#selectl").css("border","1px solid #e0d7d7");
	  			}
				});
				//点击关闭图片关闭弹出框
				$("#gbclose").click(function(){
				//清空下拉框的选项
				$("#rylxselected").empty();
				gbclose();
				if("20"==($("#hide").val())){
				reloadOwn();
				}
				});
				
			});
				// 样式 style="ime-mode:disabled" 禁止中文输入 
				//禁止键盘输入
				function noPermitInput(e){ 
       				var evt = window.event || e ;    
       			 if(isIE()){    
           			 evt.returnValue=false; //ie 禁止键盘输入    
       			 }else{    
            		evt.preventDefault(); //fire fox 禁止键盘输入    
        			}       
				}    
				function isIE() {    
    			if (window.navigator.userAgent.toLowerCase().indexOf("msie") >= 1)    
        		return true;    
   		 		else    
        		return false;    
				}    

				function showHide() {
				$(".checkbox").removeClass('radio-one-checked');
				}
	
</script>
</head>
<body>
		<div id="preloader">
			<div id="status">
				<p class="center-text">
					Loading the content... <em>Loading depends on your connection
						speed!</em>
				</p>
			</div>
		</div>

		<div class="top-deco"></div>

		<div class="content">
			<div class="header">
				<a href="javascript:void(0);" class="homepage-logo"> <img
					src="resource/images/misc/logo.jpg" alt="img"></a> <a
					href="index.do" class="go-home">主页</a>
			</div>
			<div class="header">
				<span> 
				<label style="float:left; margin-top:7px;margin-left:10px" class="homepage-logo">身份证：</label>
				<input type="text" id="idcard" /> 
				<!--调用禁止键盘输入时可用这段加载上面的INPUT里   style="ime-mode:disabled" onkeypress="noPermitInput(event)" -->
				<img src="resource/images/create02.png" class="seek" onclick="findbyidcard();" />
				</span>
			</div>
			<div class="decoration"></div>
			<!--弹出层的DIV  -->
			<div id="tipsWindown" align="center">
				<div class="content">
					<div class="navigation navc">
						<div class="navigation-wrapper full">
							<div class="navigation-wrapper" id="poqo">
								<div class="navigation-item" id="selectw">
									<div style="height:43%;"></div>
									请选择审批人:<select id="selectl"></select>
								</div>

								<div class="navigation-item debut">
									<button onclick="deselection()" class="dese">取消</button>
									<button onclick="finished()" class="buttj">完成</button>
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="bottom-deco"></div>
			</div>
			<div id="city" style="display: none;" align="center">
				<div id="showMsg">
				<div id="uppart">
					<img src="resource/images/deindex.png" id="gbclose"/><input id="hide" type="hidden" value="10"/>
					<div class="text">
						姓名:<span class="xm" id="xm"></span>
					</div>
					<div class="text">
						身份证号:<span class="sfzh"></span>
					</div>
					<div class="text">
						民族:<span class="mz"></span>
					</div>
					<div class="text">
						居住地:<span class="jzd"></span>
					</div>
					<div class="text">
						人员类型:<span class="rylx"><select id="rylxselected"></select></span>
					</div>
				</div>
				<div id="form" style="display: block;">
							<input type="button" value="添     加" id="btnadd" onclick="addMeW()" />
				</div>
				</div> 
			</div>
			
			<div class="tablel" align="center">
				<table id="ta" cellpadding="0" cellspacing="0" style="width: 96%;">
					<colgroup>
						<col width="25%" />
						<col width="25%" />
						<col width="25%" />
						<col width="25%" />
					</colgroup>
					<thead>
						<tr>
							<th style="padding-left: 0px;">姓名</th>
							<th>证件号</th>
							<th>人员类型</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody id="tbody">
						<tr id="tryu">
						</tr>
					</tbody>
				</table>
			</div>

			<div id="createb" align="center"
				style="width:100%;height:100%;display: none;">
				<a href="javascript:void(0);" onclick="addf()"><img
					src="resource/images/addpic226.png" width="20" height="20" /></a>
			</div>

			<div style="text-align: center;display: none;" id="nodata">
				<p>未有相关数据显示！</p>

			</div>
			<div id="disappare" style="display: none;">
				<p id="disapparep"></p>
			</div>
			<div id="bb">
				<div id="remindxx" align="center">
					<div class="outside">
					<div class="dhkxx" align="left">
						<p style="padding-top:6px;font-size:18px;color:#2abdea;margin-left:2%;" >对话框</p>
					</div>
					<div id="remind" align="center"style="border-top:5px solied red">
						<p style="position: relative;top:35%;">提示内容</p>
					</div>
					<div id="buttons">
					<button class="sure" onclick="closeing();" id="sure" value="41">确&nbsp;&nbsp;定</button>
					</div>
					</div>
				</div>
			</div>
		<div id="showIDCON">
			<!-- 上部分10% -->
			<div id="resume">
				<div id="dleft"></div>
				<div id="dright"><img src="resource/images/font-8193-2.png" onclick="dclose();" style="width: 99%;height: 99%;"/></div>
			</div>
			<!--下部分90%  -->
			<div id="inputde" align="center">
			<div class="part1">
			<button value="1" onclick="printValue(this);">1</button>
			<button value="2" onclick="printValue(this);">2</button>
			<button value="3" onclick="printValue(this);">3</button>
			<button id="backspaceButton" value="backspace" onclick="printValue(this);"><img class="backspaceimg" src="resource/images/backspace_start.png"/> </button>
			</div>
			<div class="part2" align="center">
			<button value="4" onclick="printValue(this);">4</button>
			<button value="5" onclick="printValue(this);">5</button>
			<button value="6" onclick="printValue(this);">6</button>
			<button value="X" onclick="printValue(this);">X</button>
			</div>
			<div class="part3" align="center">
			<button value="7" onclick="printValue(this);">7</button>
			<button value="8" onclick="printValue(this);">8</button>
			<button value="9" onclick="printValue(this);">9</button>
			<button value="0" onclick="printValue(this);">0</button>
			</div>
			</div>
		</div>
		<script type="text/javascript">
			//查找列出为默认为admin(管理员)的登录用户
			function addf() {
				gbclose();//隐藏信息显示层
				closeing();//隐藏提醒消息层
				dclose();//隐藏身份证输入键层
				$.ajax({
							url : 'listAdminRole.do',
							dataType : 'JSON',
							type : 'post',
							async : true,
							success : function(h) {
								$("#tipsWindown").css("display", "block");
								$(".navigation").show();
								$("#selectw").css("display", "block");
								openMasked();
								$("#selectl").append("<option selected='selected' value='tolerant'>-请选择审批人-</option>");
								for ( var i = 0; i < h.len; i++) {
									$("#selectl").append("<option value=" + (h.mc[i].squ)+ ">" + (h.mc[i].username)+ "</option>");
								}
							},
							error : function() {
								deselection();
								remindMessage('error')
							}
						});
			}

			//添加布控人员的数据
			function finished() {
				var myselect = document.getElementById("selectl");
				var index = myselect.selectedIndex;
				var sprsqu = myselect.options[index].value;
				if("tolerant"==sprsqu){
					$("#selectl").css("border","1px solid #f37a7a");
					return false;
				}
				closeMasked();
				var squs = new Array();
				//返每一个tr下面td的值
				for ( var i = 0; i < ($("#tbody tr").length); i++) {
					squs[i] = $("#tbody tr button").eq(i).val();
				}
				$.ajax({
					url : 'addInforToBkry.do',
					type : 'post',
					dataType : 'JSON',
					traditional : true,//这里设置为true,规定是否使用参数序列化的传统样式
					data : {
						'sprsqu' : sprsqu,
						'squs' : squs,
					},
					success : function(d) {
						var cl = d.result;
						if ('1' == cl) {
							$("#disapparep").text("添加成功！");
							$("#disappare").show().delay(2000).hide(300);
							setTimeout("reRirection()", 2000);
						} else {
							remindMessage("添加异常！");
							$("#disapparep").text("添加异常！");
							$("#disappare").show().delay(2000).hide(300);
							setTimeout("reRirection()", 2000);
						}
					},
					error : function() {
						remindMessage('error')
					}
				})
				deselection();
			}

			//取消和隐藏
			function deselection() {
				$("#selectl").empty();//只清空里面的追加内容
				$(".navigation").hide();
				$("#tipsWindown").css("display", "none");
				closeMasked();
			}
			//重定向到页面
			function reRirection() {
				window.location = "type.do";
			}
		</script>
</body>
</html>