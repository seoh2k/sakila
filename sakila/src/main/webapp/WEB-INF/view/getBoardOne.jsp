<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
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
 
</head>
<body>
<div class="container">
    <h1>BOARD VIEW</h1>
     <table class="table">
         <tbody>
             <tr>
                <td>board_id :</td>
                <td>${map.boardId}</td>
               </tr>
            <tr>
                   <td>board_title :</td>
                   <td>${map.boardTitle}</td>
            </tr>
            <tr>
                   <td>board_content :</td>
                   <td>${map.boardContent}</td>
            </tr>
            <tr>
                   <td>username :</td>
                   <td>${map.username}</td>
            </tr>
            <tr>
                   <td>insert_date :</td>
                   <td>${map.insertDate}</td>
            </tr>
        </tbody>
    </table>
    <a class="btn btn-default" href="${pageContext.request.contextPath}/modifyBoard?boardId=${map.boardId}">수정</a>
    <a class="btn btn-default" href="${pageContext.request.contextPath}/removeBoard?boardId=${map.boardId}">삭제</a>
    <a class="btn btn-default" href="${pageContext.request.contextPath}/getBoardList">글목록</a>
</div>
</body>
</html>