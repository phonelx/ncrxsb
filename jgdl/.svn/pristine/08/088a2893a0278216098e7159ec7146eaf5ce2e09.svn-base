<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
	<head>
		<base href="<%=basePath%>" target="_self"/>
	<%
		response.setHeader("Pragma","No-cache");          
        response.setHeader("Cache-Control","no-cache");   
        response.setHeader("Cache-Control", "no-store");
        response.addHeader("x-ua-compatible", "IE=9,chrome=1");   
        response.setDateHeader("Expires",0);
 	%>
 		<meta http-equiv="x-ua-compatible" content="IE=9"> 
 		<meta http-equiv="pragma" content="no-cache"/>
        <meta http-equiv="cache-control" content="no-cache"/>
        <meta http-equiv="cache-control" content="no-store"/>
        <meta http-equiv="expires" content="0"/>
		<title>意维高抗震支架检验计算系统</title>
		
		<style type="text/css">
	  body {
		padding-bottom: 40px;
	  }
	  .sidebar-nav {
		padding: 9px 0;
	  }
	</style>

	<!-- The styles -->
	<link id="bs-css" href="resource/new/bootstrap/css/bootstrap-cerulean.css" rel="stylesheet"/>
	<link href="resource/new/bootstrap/css/bootstrap-responsive.css" rel="stylesheet"/> 
	<link href="resource/new/bootstrap/css/jquery-ui-1.8.21.custom.css" rel="stylesheet"/>
	<link href="resource/new/bootstrap/css/opa-icons.css" rel="stylesheet"/>
	<link href="resource/new/bootstrap/css/charisma-app.css" rel="stylesheet"/>
	<link href="resource/new/bootstrap/css/jquery.iphone.toggle.css" rel="stylesheet"/>
	<link href="resource/new/bootstrap/css/jquery.cleditor.css" rel="stylesheet"/>
	<link href="resource/new/css/style.css" rel="stylesheet"/>
<!-- The fav icon-->
<link rel="shortcut icon" href="resource/new/bootstrap/img/favicon.ico" />
<!-- jQuery -->
	<script src="resource/new/bootstrap/js/jquery-1.7.2.min.js"></script>
	<!-- jQuery UI -->
	<script src="resource/new/bootstrap/js/jquery-ui-1.8.21.custom.min.js"></script>
	<script src="resource/js/jquery.nicescroll.js"></script>
<script type="text/javascript" src="resource/extjs/ext-base.js"></script>
<script type="text/javascript" src="resource/extjs/ext-all.js"></script>
<script type="text/javascript" src="resource/extjs/ext-lang-zh_CN.js"></script>
<script type="text/javascript" src="resource/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="resource/js/common/common.js"></script>
<script type="text/javascript" src="resource/js/common/dictionaryMap.js"></script>
	<script src="resource/js/alert.js"></script>
	<!-- transition / effect library -->
	<script src="resource/new/bootstrap/js/bootstrap-transition.js"></script>
	<!-- alert enhancer library -->
	<script src="resource/new/bootstrap/js/bootstrap-alert.js"></script>
	<!-- modal / dialog library -->
	<script src="resource/new/bootstrap/js/bootstrap-modal.js"></script>
	<!-- custom dropdown library -->
	<script src="resource/new/bootstrap/js/bootstrap-dropdown.js"></script>
	<!-- scrolspy library -->
	<script src="resource/new/bootstrap/js/bootstrap-scrollspy.js"></script>
	<!-- library for creating tabs -->
	<script src="resource/new/bootstrap/js/bootstrap-tab.js"></script>
	<!-- library for advanced tooltip -->
	<script src="resource/new/bootstrap/js/bootstrap-tooltip.js"></script>
	<!-- popover effect library -->
	<script src="resource/new/bootstrap/js/bootstrap-popover.js"></script>
	<!-- button enhancer library -->
	<script src="resource/new/bootstrap/js/bootstrap-button.js"></script>
	<!-- accordion library (optional, not used in demo) -->
	<script src="resource/new/bootstrap/js/bootstrap-collapse.js"></script>
	<!-- carousel slideshow library (optional, not used in demo) -->
	<script src="resource/new/bootstrap/js/bootstrap-carousel.js"></script>
	<!-- autocomplete library -->
	<script src="resource/new/bootstrap/js/bootstrap-typeahead.js"></script>
	<!-- tour library -->
	<script src="resource/new/bootstrap/js/bootstrap-tour.js"></script>
	<!-- calander plugin -->
	<script src='resource/new/bootstrap/js/fullcalendar.min.js'></script>
	<!-- data table plugin -->
	<script src='resource/new/bootstrap/js/jquery.dataTables.min.js'></script>
	<script src='resource/new/js/Validform_v5.3.2_min.js'></script>

	<!-- select or dropdown enhancer -->
	<script src="resource/new/bootstrap/js/jquery.chosen.min.js"></script>
	<!-- checkbox, radio, and file input styler -->
	<script src="resource/new/bootstrap/js/jquery.uniform.min.js"></script>
	<!-- plugin for gallery image view -->
	<script src="resource/new/bootstrap/js/jquery.colorbox.min.js"></script>
	<!-- rich text editor library -->
	<script src="resource/new/bootstrap/js/jquery.cleditor.min.js"></script>
	<!-- notification plugin -->
	<script src="resource/new/bootstrap/js/jquery.noty.js"></script>
	<!-- file manager library -->
	<script src="resource/new/bootstrap/js/jquery.elfinder.min.js"></script>
	<!-- star rating plugin -->
	<script src="resource/new/bootstrap/js/jquery.raty.min.js"></script>
	<!-- for iOS style toggle switch -->
	<script src="resource/new/bootstrap/js/jquery.iphone.toggle.js"></script>
	<!-- autogrowing textarea plugin -->
	<script src="resource/new/bootstrap/js/jquery.autogrow-textarea.js"></script>
	<!-- multiple file upload plugin -->
	<script src="resource/new/bootstrap/js/jquery.uploadify-3.1.min.js"></script>
	<!-- history.js for cross-browser state change on ajax -->
	<script src="resource/new/bootstrap/js/jquery.history.js"></script>
	
	<!-- application script for Charisma demo
	<script src="resource/new/bootstrap/js/charisma.js"></script> -->
	<script type="text/javascript" src='resource/js/bk/uiGrid.js'></script>
	<script type="text/javascript" src='resource/new/js/html5.js'></script>
	<%-- <!-- dataGrant -->
	--%>
	
	
</head>
<body>
	 <!-- system modal start -->
    <div id="ycf-alert" style="display:none; position: fixed;top: 65%;left: 60%;width:300px;z-index:1600" class="modal">
      <div class="modal-dialog modal-sm">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span></button>
            <h5 class="modal-title"><i class="fa fa-exclamation-circle"></i> [Title]</h5>
          </div>
          <div class="modal-body small">
            <p>[Message]</p>
          </div>
          <div class="modal-footer" >
            <button type="button" class="btn btn-primary ok" data-dismiss="modal">[BtnOk]</button>
            <button type="button" class="btn btn-default cancel" data-dismiss="modal">[BtnCancel]</button>
          </div>
        </div>
      </div>
    </div>
  <!-- system modal end -->
</body>
</html>
