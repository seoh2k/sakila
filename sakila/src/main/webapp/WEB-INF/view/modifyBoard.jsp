<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>BOARD VIEW(spring mvc 방식)</title>
<!-- bootstrap을 사용하기 위한 CDN주소 -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
$(document).ready(function(){
	console.log('document ready')
	$('#btn').click(function(){
		console.log('btn click!'); // 웹브라우저 콘솔에서 확인 가능
		// 폼 유효성 검사 코드 추가
		$('#modifyForm').submit();
	});
});
</script>
</head>
<body>
<div class="container">
    <h1>Board Modify</h1>
    <form id="modifyForm" action="${pageContext.request.contextPath}/modifyBoard" method="post">
		<table class="table">
	         <tbody>
				<tr>
	                <td>board_id :</td>
	                <td><input type="text" id="boardId" name="boardId" value="${boardMap.boardId}" readonly="readonly"></td>
				</tr>
				<tr>
	                   <td>board_pw :</td>
	                   <td><input type="password" id="boardPw" name="boardPw"></td>
	            </tr>
	            <tr>
	                   <td>board_title :</td>
	                   <td><input type="text" id="boardTitle" name="boardTitle" value="${boardMap.boardTitle}"></td>
	            </tr>
	            <tr>
	                   <td>board_content :</td>
	                   <td><textarea id="boardContent" name="boardContent" rows="5" cols="80">${boardMap.boardContent}</textarea></td>
	            </tr>
	            <tr>
	                   <td>username :</td>
	                   <td>${boardMap.username}</td>
	            </tr>
	            <tr>
	                   <td>insert_date :</td>
	                   <td>${boardMap.insertDate}</td>
	            </tr>
	        </tbody>
	    </table>
	    <button type="button" id="btn">수정</button>
	    <a class="btn btn-default" href="${pageContext.request.contextPath}/getBoardList">글목록</a>
    </form>
     
</div>
</body>
</html>