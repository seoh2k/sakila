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
    <h1>FilmList</h1>
	<!--  
		1. 카테고리별
		2. 가격별
		3. 제목 검색
		4. 등급별
		5. 페이징
		6. 배우 검색
		7. 타이틀 클릭시 상세보기
	 -->
	<div>
    	<a href="${pageContext.request.contextPath}/admin/getBoardList">BoardList</a>
        <a href="${pageContext.request.contextPath}/admin/getStaffList">StaffList</a>
    	<a href="${pageContext.request.contextPath}/admin/getFilmList">FilmList</a>
    	<a href="${pageContext.request.contextPath}/admin/getActorList">ActorList</a>
    	<a href="${pageContext.request.contextPath}/admin/getCustomerList">CustomerList</a>
    	<a href="${pageContext.request.contextPath}/admin/getInventoryList">InventoryList</a>
    	<a href="${pageContext.request.contextPath}/admin/getSalesList">SalesList</a>
    </div>
	 
	<form id="filmForm" action="${pageContext.request.contextPath}/admin/getFilmList" method="get">
		category:
		<select name="categoryName">
			<option value="">카테고리 선택</option>
			<c:forEach var="c" items="${categoryList}">
				<c:if test="${name == categoryName}">
					<option value="${c.name}" selected="selected">${c.name}</option>
				</c:if>
				<c:if test="${c.name != categoryName}">
					<option value="${c.name}">${c.name}</option>
				</c:if>
			</c:forEach>
		</select>
		
		price:
		<select name="price">
			<option value="0">가격 선택</option>
			<c:if test="${price == 0.99}">
				<option value="0.99" selected="selected">0.99</option>
			</c:if>
			<c:if test="${price != 0.99}">
	        	<option value="0.99">0.99</option>
	        </c:if>
	        
	        <c:if test="${price == 2.99}">
	            <option value="2.99" selected="selected">2.99</option>
	        </c:if>
	        <c:if test="${price != 2.99}">
	            <option value="2.99">2.99</option>
	        </c:if>
	        
	        <c:if test="${price == 4.99}">
	            <option value="4.99" selected="selected">4.99</option>
	        </c:if>
	        <c:if test="${price != 4.99}">
	            <option value="4.99">4.99</option>
	        </c:if>
		</select>
		
		title:
		<input type="text" id="title" name="title" value="${title}">
		
		rating:
		<select name="rating">
			<option value="">가격 선택</option>
			<c:if test="${rating == 'PG'}">
				<option value="PG" selected="selected">PG</option>
			</c:if>
			<c:if test="${rating != 'PG'}">
	        	<option value="PG">PG</option>
	        </c:if>
	        
	        <c:if test="${rating == 'NC-17'}">
				<option value="NC-17" selected="selected">NC-17</option>
			</c:if>
			<c:if test="${rating != 'NC-17'}">
	        	<option value="NC-17">NC-17</option>
	        </c:if>
	        
	        <c:if test="${rating == 'R'}">
				<option value="R" selected="selected">R</option>
			</c:if>
			<c:if test="${rating != 'R'}">
	        	<option value="R">R</option>
	        </c:if>
	        
	        <c:if test="${rating == 'PG-13'}">
				<option value="PG-13" selected="selected">PG-13</option>
			</c:if>
			<c:if test="${rating != 'PG-13'}">
	        	<option value="PG-13">PG-13</option>
	        </c:if>
	        
	        <c:if test="${rating == 'G'}">
				<option value="G" selected="selected">G</option>
			</c:if>
			<c:if test="${rating != 'G'}">
	        	<option value="G">G</option>
	        </c:if>
		</select>
		
		actor:
		<input type="text" id="actors" name="actors" value="${actors}">
		
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
					 <td class="col-sm-1 text-center"><a href="${pageContext.request.contextPath}/admin/getFilmOne?FID=${m.FID}">${m.title}</a></td>
					 <td class="col-sm-1 text-center">${m.category}</td>
					 <td class="col-sm-1 text-center">${m.price}</td>
					 <td class="col-sm-1 text-center">${m.rating}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
    
    <ul class="pager">
        <c:if test="${currentPage > 1}">
            <li class="previous"><a href="${pageContext.request.contextPath}/admin/getFilmList?currentPage=${currentPage-1}&categoryName=${categoryName}&price=${price}&title=${title}&rating=${rating}&actors=${actors}">이전</a></li>
        </c:if>
        <c:if test="${currentPage < lastPage}">
            <li class="next"><a href="${pageContext.request.contextPath}/admin/getFilmList?currentPage=${currentPage+1}&categoryName=${categoryName}&price=${price}&title=${title}&rating=${rating}&actors=${actors}">다음</a></li>
        </c:if>
    </ul>
    <div>
        <a class="btn btn-default" href="${pageContext.request.contextPath}/admin/addFilm">영화 입력</a>
    </div>
</div>
</body>
</html>