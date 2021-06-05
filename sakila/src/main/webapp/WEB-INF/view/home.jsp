<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
$(document).ready(function(){
	console.log('ready...');
	$('#btn').click(function(){
		console.log('click...');
		$('#loginForm').submit();
	});
});
</script>
<title>Home</title>
</head>
<body>
	<h1>Home</h1>
	<a href="${pageContext.request.contextPath}/admin/getBoardList">게시판</a>
	<!-- 로그 오프 시 -->
	<c:if test="${loginStaff == null}">
		<form id="loginForm" action="${pageContext.request.contextPath}/login" method="post">
			<div>email: </div>
			<div><input type="text" id="email" name="email"></div>
			<div>password: </div>
			<div><input type="password" id="password" name="password"></div>
			<div>
				<button id="btn" type="button">로그인</button>
			</div>
		</form>
	</c:if>
	
	<!-- 로그 온 시 -->
	<c:if test="${loginStaff != null}">
		<div>
    	<a href="${pageContext.request.contextPath}/admin/getBoardList">BoardList</a>
        <a href="${pageContext.request.contextPath}/admin/getStaffList">StaffList</a>
    	<a href="${pageContext.request.contextPath}/admin/getFilmList">FilmList</a>
    	<a href="${pageContext.request.contextPath}/admin/getActorList">ActorList</a>
    	<a href="${pageContext.request.contextPath}/admin/getCustomerList">CustomerList</a>
    	<a href="${pageContext.request.contextPath}/admin/getInventoryList">InventoryList</a>
    	<a href="${pageContext.request.contextPath}/admin/getSalesList">SalesList</a>
    </div>
	</c:if>
	
	
</body>
</html>