<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>CustomerOne</title>
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
	<h1>CustomerOne</h1>
	
	<div>
    	<a href="${pageContext.request.contextPath}/admin/getBoardList">BoardList</a>
        <a href="${pageContext.request.contextPath}/admin/getStaffList">StaffList</a>
    	<a href="${pageContext.request.contextPath}/admin/getFilmList">FilmList</a>
    	<a href="${pageContext.request.contextPath}/admin/getActorList">ActorList</a>
    	<a href="${pageContext.request.contextPath}/admin/getCustomerList">CustomerList</a>
    	<a href="${pageContext.request.contextPath}/admin/getInventoryList">InventoryList</a>
    	<a href="${pageContext.request.contextPath}/admin/getSalesList">SalesList</a>
    </div>
    
		<table class="table">
			<tbody>
				<tr>
					<td>ID :</td>
					<td>${customerOne.ID}</td>
				</tr>
	            <tr>
	            	<td>name :</td>
	            	<td>${customerOne.name}</td>
	            </tr>
	            <tr>
	            	<td>address :</td>
	            	<td>${customerOne.address}</td>
	            </tr>
	            <tr>
	            	<td>zipCode :</td>
	            	<td>${customerOne.zipCode}</td>
	            </tr>
	            <tr>
	            	<td>phone :</td>
	            	<td>${customerOne.phone}</td>
	            </tr>
	            <tr>
	            	<td>city :</td>
	            	<td>${customerOne.city}</td>
	            </tr>
	            <tr>
	            	<td>country :</td>
	            	<td>${customerOne.country}</td>
	            </tr>
	            <tr>
	            	<td>notes :</td>
	            	<td>${customerOne.notes}</td>
	            </tr>
	            <tr>
					<td>SID :</td>
					<td>${customerOne.SID}</td>
				</tr>
			</tbody>
		</table>
		
	<h3>rentalList</h3>
		<table class="table table-striped">
			<thead>
				<tr>
					<td>rentalId</td>
					<td>rentalDate</td>
					<td>inventoryId</td>
					<td>title</td>
					<td>returnDate</td>
					<td>staffId</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="r" items="${rentalList}">
	                <tr>
	                	<td>${r.rentalId}</td>
	                	<td>${r.rentalDate}</td>
	                	<td>${r.inventoryId}</td>
	                	<td>${r.title}</td>
	                	<td>${r.returnDate}</td>
	                	<td>${r.staffId}</td>
	                </tr>
	            </c:forEach>
			</tbody>
		</table>
		
		<!-- 검색어 입력창 -->
	    <div class="text-center">
		    <form action="${pageContext.request.contextPath}/admin/getCustomerOne?ID=${ID}" method="get">
		        <label for="searchWord">검색어(제목) :</label> 
		        <input name="searchWord" type="text">
		        <button type="submit">검색</button>
		    </form>
		</div>
	    
	    <ul class="pager">
	        <c:if test="${currentPage > 1}">
	            <li class="previous"><a href="${pageContext.request.contextPath}/admin/getCustomerOne?ID=${ID}&currentPage=${currentPage-1}&searchWord=${searchWord}">이전</a></li>
	        </c:if>
	        <c:if test="${currentPage < lastPage}">
	            <li class="next"><a href="${pageContext.request.contextPath}/admin/getCustomerOne?ID=${ID}&currentPage=${currentPage+1}&searchWord=${searchWord}">다음</a></li>
	        </c:if>
	    </ul>
		
	<div>
        <a class="btn btn-default" href="${pageContext.request.contextPath}/admin/addRental?customerId=${map.ID}">영화 대여</a>
    </div>
</div>   
</body>
</html>