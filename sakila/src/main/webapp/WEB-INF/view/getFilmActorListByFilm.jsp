<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>getFilmActorListByFilm</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
$(document).ready(function(){
	console.log('document ready')
	$('#btn').click(function(){
		console.log('btn click!'); // 웹브라우저 콘솔에서 확인 가능
		// 폼 유효성 검사 코드 추가
		$('#filmActorListForm').submit();
	});
});
</script>
</head>
<body>
	<h1>영화(film) 출연자(actor) 수정</h1>
	<form id="filmActorListForm" method="post" action="${pageContext.request.contextPath}/admin/modifyFilmActor">
		<input type="hidden" id="filmId" name="filmId" value="${filmId}">
		<c:forEach var="m" items="${filmActorList}">
			<c:if test="${m.filmId == null}">
				<input type="checkbox" id="ck" name="ck" value="${m.actorId}">
			</c:if>
			<c:if test="${m.filmId != null}">
				<input type="checkbox" id="ck" name="ck" checked="checked" value="${m.actorId}">
			</c:if>
			<span style="color:red;">${m.name.substring(0,1)}</span>${m.name.substring(1)}&nbsp;
		</c:forEach>
		<div>
			<button id="btn" type="button">출연배우 수정</button>
		</div>
	</form>
</body>
</html>