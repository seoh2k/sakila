<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>FilmList</title>
<!-- bootstrap을 사용하기 위한 CDN주소 -->
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
		console.log('btn click!');
		$('#filmForm').submit();
	});
});
</script>
</head>
<body>
<div class="container">
    <h1>getFilmList</h1>
	<!--  
		1. 카테고리별
		2. 가격별
		3. 제목 검색
		4. 등급별
		5. 페이징
		6. 배우 검색
	 -->
	<a href="${pageContext.request.contextPath}/admin/getBoardList">게시판</a>
	 
	<form id="filmForm" action="${pageContext.request.contextPath}/admin/getFilmList" method="get">
		category:
		<select name="categoryName">
			<option value="">카테고리 선택</option>
			<c:forEach var="name" items="${categoryNameList}">
				<c:if test="${name == categoryName}">
					<option value="${name}" selected="selected">${name}</option>
				</c:if>
				<c:if test="${name != categoryName}">
					<option value="${name}">${name}</option>
				</c:if>
			</c:forEach>
		</select>
		<button id="btn" type="button">검색</button>
	</form>
	
	<table class="table table-striped">
		<thead>
			<tr>
				<th>FID</th>
				<th>title</th>
				<th>category</th>
				<th>price</th>
				<th>rating</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="m" items="${filmList}">
				<tr>
					 <td class="col-sm-1 text-center">${m.FID}</td>
					 <td class="col-sm-1 text-center">${m.title}</td>
					 <td class="col-sm-1 text-center">${m.category}</td>
					 <td class="col-sm-1 text-center">${m.price}</td>
					 <td class="col-sm-1 text-center">${m.rating}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
    <!-- 검색어 입력창 -->
    <form action="getFilmList" method="get">
        <label for="searchWord">검색어(제목) :</label> 
        <input name="searchWord" type="text">
        <button type="submit">검색</button>
    </form>
    
    <ul class="pager">
        <c:if test="${currentPage > 1}">
            <li class="previous"><a href="${pageContext.request.contextPath}/admin/getFilmList?currentPage=${currentPage-1}&searchWord=${searchWord}">이전</a></li>
        </c:if>
        <c:if test="${currentPage < lastPage}">
            <li class="next"><a href="${pageContext.request.contextPath}/admin/getFilmList?currentPage=${currentPage+1}&searchWord=${searchWord}">다음</a></li>
        </c:if>
    </ul>
    <div>
        <a class="btn btn-default" href="${pageContext.request.contextPath}/admin/addFilmList">게시글 입력</a>
    </div>
</div>
</body>
</html>