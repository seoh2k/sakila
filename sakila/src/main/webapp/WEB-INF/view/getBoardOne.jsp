<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>BOARD VIEW(spring mvc ���)</title>
<!-- bootstrap�� ����ϱ� ���� CDN�ּ� -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
$(document).ready(function(){
	console.log('document ready')
	$('#btn').click(function(){
		console.log('btn click!'); // �������� �ֿܼ��� Ȯ�� ����
		// �� ��ȿ�� �˻� �ڵ� �߰�
		$('#commentForm').submit();
	});
});
</script>
</head>
<body>
<div class="container">
    <h1>BOARD VIEW</h1>
     <table class="table">
         <tbody>
             <tr>
                <td>board_id :</td>
                <td>${boardMap.boardId}</td>
               </tr>
            <tr>
                <td>board_title :</td>
                <td>${boardMap.boardTitle}</td>
            </tr>
            <tr>
                <td>board_content :</td>
                <td>${boardMap.boardContent}</td>
            </tr>
            <tr>
                <td>username :</td>
                <td>${boardMap.username}</td>
            </tr>
            <tr>
                <td>insert_date :</td>
                <td>${boardMap.insertDate}</td>
            </tr>
            <tr>
                <td>boardfile :</td>
                <td>
                	<div>
                		<a href="${pageContext.request.contextPath}/admin/addBoardfile?boardId=${boardMap.boardId}"><button type="button">�����߰�</button></a>
                	</div>
					<!-- ���������� ����ϴ� �ݺ��� �ڵ� ���� -->
					<c:forEach var="f" items="${boardfileList}">
						<div>
							<a href="${pageContext.request.contextPath}/resource/${f.boardfileName}">
								${f.boardfileName}
							</a>
							<a href="${pageContext.request.contextPath}/admin/removeBoardfile?boardfileId=${f.boardfileId}&boardId=${f.boardId}&boardfileName=${f.boardfileName}">
								<button type="button">���ϻ���</button>
							</a>
						</div>
					</c:forEach>
				</td>
            </tr>
        </tbody>
    </table>
    <a class="btn btn-default" 
    	href="${pageContext.request.contextPath}/admin/modifyBoard?boardId=${boardMap.boardId}">����</a>
    <a class="btn btn-default" 
    	href="${pageContext.request.contextPath}/admin/removeBoard?boardId=${boardMap.boardId}">����</a>
    <a class="btn btn-default" 
    	href="${pageContext.request.contextPath}/admin/getBoardList">�۸��</a>
    
    <!-- ��� ��� -->
    <div>
   		<form id="commentForm" action="${pageContext.request.contextPath}/admin/addComment" method="post">
   			<input type="hidden" name="boardId" value="${boardMap.boardId}">
   			<div>User Name: <input type="text" id="username" name="username"></div>
    		<div>Comment: <textarea name="commentContent" rows="5" cols="100"></textarea></div>
    		<div><button id="btn" type="button">����߰�</button></div>
   		</form>
    	<table class="table"> 
    		<c:forEach var="c" items="${commentList}">
    			<tr>
    				<td>${c.commentContent}</td>
    				<td>${c.username}</td>
    				<td>${c.insertDate}</td>
    				<td><a href="${pageContext.request.contextPath}/admin/removeComment?commentId=${c.commentId}&boardId=${c.boardId}"><button type="button">����</button></a></td>
    			</tr>
    		</c:forEach>
    	</table>
    </div>
</div>
</body>
</html>