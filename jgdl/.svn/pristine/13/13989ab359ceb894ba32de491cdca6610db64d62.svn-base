<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<ul class="oneUlMenu">
	<c:forEach var="mainBean" items="${moduleMainDtoList}">
	<li class="oneLiMenu" onmousemove="oneLiMenuMove(this);" onmouseout="oneLiMenuOut(this);">
	 	<h3 class="h3Menu"><a target="_blank" href="javascript:void(0);">${mainBean.name}</a></h3>
 		<ul class="twoUlMenu" style="display:none;">
			<c:forEach var="subBean" items="${mainBean.subModuleList}">
				<li class="twoLiMenu" onclick="javascript:gotoModule('${subBean.entryUrl}',
				'${subBean.name}','${mainBean.name}');" >${subBean.name}</li>
			</c:forEach>
		</ul>
	</li>
	</c:forEach>
</ul>
