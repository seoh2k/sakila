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
	
	<div>
    	<a href="${pageContext.request.contextPath}/admin/getBoardList">BoardList</a>
        <a href="${pageContext.request.contextPath}/admin/getStaffList">StaffList</a>
    	<a href="${pageContext.request.contextPath}/admin/getFilmList">FilmList</a>
    	<a href="${pageContext.request.contextPath}/admin/getActorList">ActorList</a>
    	<a href="${pageContext.request.contextPath}/admin/getCustomerList">CustomerList</a>
    	<a href="${pageContext.request.contextPath}/admin/getInventoryList">InventoryList</a>
    	<a href="${pageContext.request.contextPath}/admin/getSalesList">SalesList</a>
    </div>
    
	<!-- method: 파일 추가 시 post를 사용한다. -->
	<!-- enctype: 파일 업로드를 위한 속성, 웹 서버로 데이터를 넘길 때 파일의 경로명과 파일 내용 모두를 전송하기 위해 사용한다. -->
	<!-- multipart/form-data: 파일이나 이미지를 서버로 전송할 때 사용한다. -->
	<!-- action: 자기 페이지의 url, 전송 목적지이다. addBoardfile은 jsp 페이지를 의미한다 -->
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