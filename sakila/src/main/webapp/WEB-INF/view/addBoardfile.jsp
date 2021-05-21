<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
$(document).ready(function(){
	$('#btn').click(function(){
		console.log('btn click!'); // 웹브라우저 콘솔에서 확인 가능
		// 폼 유효성 검사 코드 추가
		$('#addForm').submit();
	});
});
</script>
<title>addBoardfile</title>
</head>
<body>
	<h1>파일추가</h1>
	<form id="addForm" 
			method="post"
			enctype="multipart/form-data"
			action="${pageContext.request.contextPath}/admin/addBoardfile">
		<div>
			boardId: 
			<input type="text" id="boardId" name="boardId" value="${boardId}" readonly="readonly"> 
		</div>
		<div>
			<input type="file" id="multipartFile" name="multipartFile">
		</div>
		<div>
			<button type="button" id="btn">파일추가</button>
		</div>
	</form>
</body>
</html>