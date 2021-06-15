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
		console.log('btn click!'); // �������� �ֿܼ��� Ȯ�� ����
		// �� ��ȿ�� �˻� �ڵ� �߰�
		$('#addForm').submit();
	});
});
</script>
<title>addBoardfile</title>
</head>
<body>
	<h1>�����߰�</h1>
	
	<div>
    	<a href="${pageContext.request.contextPath}/admin/getBoardList">BoardList</a>
        <a href="${pageContext.request.contextPath}/admin/getStaffList">StaffList</a>
    	<a href="${pageContext.request.contextPath}/admin/getFilmList">FilmList</a>
    	<a href="${pageContext.request.contextPath}/admin/getActorList">ActorList</a>
    	<a href="${pageContext.request.contextPath}/admin/getCustomerList">CustomerList</a>
    	<a href="${pageContext.request.contextPath}/admin/getInventoryList">InventoryList</a>
    	<a href="${pageContext.request.contextPath}/admin/getSalesList">SalesList</a>
    </div>
    
	<!-- method: ���� �߰� �� post�� ����Ѵ�. -->
	<!-- enctype: ���� ���ε带 ���� �Ӽ�, �� ������ �����͸� �ѱ� �� ������ ��θ�� ���� ���� ��θ� �����ϱ� ���� ����Ѵ�. -->
	<!-- multipart/form-data: �����̳� �̹����� ������ ������ �� ����Ѵ�. -->
	<!-- action: �ڱ� �������� url, ���� �������̴�. addBoardfile�� jsp �������� �ǹ��Ѵ� -->
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
			<button type="button" id="btn">�����߰�</button>
		</div>
	</form>
</body>
</html>