<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>removeBoard</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
	$(document).ready(function(){
		$('#btn').click(function(){
			console.log('btn click!'); // 웹브라우저 콘솔에서 확인 가능
			// 폼 유효성 검사 코드 추가
			$('#removeForm').submit();
		});
	});
</script>
</head>
<body>
	<h1>removeBoard</h1>
	<form id="removeForm" action="${pageContext.request.contextPath}/removeBoard" method="post">
		<input type="hidden" name="boardId" value="${boardId}">
		<div>
			<label>boardPw: </label>
			<input type="password" id="boardPw" name="boardPw">
		</div>
		<button type="button" id="btn">삭제</button>
	</form>
</body>
</html>