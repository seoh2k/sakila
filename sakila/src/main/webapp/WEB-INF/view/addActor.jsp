<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<!-- bootstrap을 사용하기 위한 CDN주소 -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
    href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
    integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
    crossorigin="anonymous">
<!-- Optional theme -->
<link rel="stylesheet"
    href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
    integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
    crossorigin="anonymous">
 
<!-- jquery를 사용하기위한 CDN주소 -->
<script
    src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
 
<!-- bootstrap javascript소스를 사용하기 위한 CDN주소 -->
<!-- Latest compiled and minified JavaScript -->
<script
    src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
    integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
    crossorigin="anonymous"></script>
<script>
$(document).ready(function() {
	$('#addButton').click(function(){
		console.log('btn click!'); // 웹브라우저 콘솔에서 확인 가능
		// 폼 유효성 검사 코드 추가
		$('#addForm').submit();
	});
})
</script>
<title>addActor</title>
</head>
<body>
	<div class="container">
        <h1>addActor</h1>
        <form id="addForm" 
        		action="${pageContext.request.contextPath}/admin/addActor" 
        		method="post">
        	<div class="form-group">
                <label for="firstName">firstName :</label> <input class="form-control"
                    name="firstName" id="firstName" type="text" />
            </div>
            <div class="form-group">
                <label for="lastName">lastName :</label> <input
                    class="form-control" name="lastName" id="lastName" type="text" />
            </div>
            <div>
                <input class="btn btn-default" id="addButton" type="button"
                    value="글입력" /> <input class="btn btn-default" type="reset"
                    value="초기화" /> <a class="btn btn-default"
                    href="${pageContext.request.contextPath}/admin/getActorList">글목록</a>
            </div>
        </form>
    </div>
</body>
</html>