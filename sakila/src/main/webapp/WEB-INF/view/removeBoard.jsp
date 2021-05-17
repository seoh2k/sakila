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
			console.log('btn click!'); // �������� �ֿܼ��� Ȯ�� ����
			// �� ��ȿ�� �˻� �ڵ� �߰�
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
		<button type="button" id="btn">����</button>
	</form>
</body>
</html>