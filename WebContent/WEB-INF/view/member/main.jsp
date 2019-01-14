<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
<jsp:include page="/WEB-INF/view/home/head.jsp"/>
<body>
<table id="wrapper">
	<tr>
		<td colspan="2">
			<jsp:include page="/WEB-INF/view/home/header.jsp"/>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<jsp:include page="../home/navi-bar.jsp"/>
		</td>
	</tr>
	<tr  style="height: 300px">
		<td>
			<jsp:include page="side-menu.jsp"/>
		</td>
		<td>
			<c:choose>
				<c:when test="${dest eq 'NONE'}">
				
				</c:when>	
				<c:when test="${dest eq 'join-form'}">
				<jsp:include page="join-form.jsp"/>
				</c:when>
				<c:when test="${dest eq 'detail'}">
				<jsp:include page="member-detail.jsp"/>
				</c:when>
				<c:when test="${dest eq 'member-update'}">
				<jsp:include page="member-update.jsp"/>
				</c:when>
			</c:choose>
			
		</td>
	</tr>
</table>
</body>
</html>