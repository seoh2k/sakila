<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>InventoryList</title>
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
		$('#inventoryForm').submit();
	});
});
</script>
</head>
<body>
<div class="container">
    <h1>CustomerList</h1>
    <div>
        <a class="btn btn-default" href="${pageContext.request.contextPath}/admin/getStaffList">StaffList</a>
    	<a class="btn btn-default" href="${pageContext.request.contextPath}/admin/getFilmList">FilmList</a>
    	<a class="btn btn-default" href="${pageContext.request.contextPath}/admin/getActorList">ActorList</a>
    	<a class="btn btn-default" href="${pageContext.request.contextPath}/admin/getCustomerList">CustomerList</a>
    </div>
	
	<form id="inventoryForm" action="${pageContext.request.contextPath}/admin/getInventoryList" method="get">
		storeId:
		<select name="storeId">
			<option value="0">==</option>
			<c:if test="${storeId == 1}">
				<option value="1" selected="selected">1호점</option>
			</c:if>
			<c:if test="${storeId != 1}">
	        	<option value="1">1호점</option>
	        </c:if>
	        
	        <c:if test="${storeId == 2}">
	            <option value="2" selected="selected">2호점</option>
	        </c:if>
	        <c:if test="${storeId != 2}">
	            <option value="2">2호점</option>
	        </c:if>
		</select>
		
		제목 검색:
		<input type="text" id="searchWord" name="searchWord" value="${searchWord}">
		<button id="btn" type="button">검색</button>
	</form>
	
	<table class="table table-striped">
        <thead>
            <tr>
                <th>storeId</th>
                <th>title</th>
                <th>totalInventory</th>
                <th>rental</th>
                <th>stock</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="il" items="${inventoryList}">
                <tr>
                	<td>${il.storeId}</td>
                	<td>${il.title}</td>
                	<td>${il.totalInventory}</td>
                	<td>${il.rental}</td>
                	<td>${il.stock}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    
    <ul class="pager">
        <c:if test="${currentPage > 1}">
            <li class="previous"><a href="${pageContext.request.contextPath}/admin/getInventoryList?currentPage=${currentPage-1}&searchWord=${searchWord}&storeId=${storeId}">이전</a></li>
        </c:if>
        <c:if test="${currentPage < lastPage}">
            <li class="next"><a href="${pageContext.request.contextPath}/admin/getInventoryList?currentPage=${currentPage+1}&searchWord=${searchWord}&storeId=${storeId}">다음</a></li>
        </c:if>
    </ul>
    <div>
        <a class="btn btn-default" href="${pageContext.request.contextPath}/admin/addInventory">게시글 입력</a>
    </div>
</div>
</body>
</html>