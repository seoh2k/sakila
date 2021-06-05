<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>SalesList</title>
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
		$('#btn').click(function(){
			console.log('btn click!!')
			$('#SIDForm').submit();			
		});
	});
</script>
</head>
<body>
<div class="container">
    <h1>CustomerList</h1>
    <div>
    	<a class="btn btn-default" href="${pageContext.request.contextPath}/admin/getBoardList">BoardList</a>
        <a class="btn btn-default" href="${pageContext.request.contextPath}/admin/getStaffList">StaffList</a>
    	<a class="btn btn-default" href="${pageContext.request.contextPath}/admin/getFilmList">FilmList</a>
    	<a class="btn btn-default" href="${pageContext.request.contextPath}/admin/getActorList">ActorList</a>
    	<a class="btn btn-default" href="${pageContext.request.contextPath}/admin/getCustomerList">CustomerList</a>
    	<a class="btn btn-default" href="${pageContext.request.contextPath}/admin/getInventoryList">InventoryList</a>
    	<a class="btn btn-default" href="${pageContext.request.contextPath}/admin/getSalesList">SalesList</a>
    </div>
    
    <!-- 베스트셀러 -->
    <h3>bestSellerTop10</h3>
    <table class="table table-striped">
		<thead>
            <tr>	
            	<th>filmId</th>
            	<th>title</th>
                <th>cnt</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="b" items="${bestSellerTop10}">
                <tr>
                	<td>${b.filmId}</td>
                	<td>${b.title}</td>
                	<td>${b.cnt}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    
    <!-- 카테고리별 매출 -->
    <h3>salesByCategoryList</h3>
    <table class="table table-striped">
		<thead>
            <tr>
	            <c:forEach var="s" items="${salesByCategoryList}">	
	            	<th>${s.category}</th>
	            </c:forEach>
            </tr>
        </thead>
        <tbody>
			<tr>
				<c:forEach var="s" items="${salesByCategoryList}">
					<td>${s.totalSales}</td>
				</c:forEach>
			</tr>
        </tbody>	
    </table>
    
    <h3>selectMonthlySalesList</h3>
    <!-- 매장별 조회 -->
    <form id="SIDForm" action="${pageContext.request.contextPath}/admin/getSalesList" method="get">
   		<!-- store Id 선택 -->
   		Store:
		<select name="storeId"> <!-- name="storeId" -->
			<option value="0">선택</option>
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
    	<button id="btn" type="button">조회</button>
	</form>
	
	<!-- 월별 매출 -->
    <table class="table table-striped">
		<thead>
            <tr>
            	<th>storeId</th>
                <th>address</th>
                <th>manager</th>
                <th>totalSales</th>
                <th>year</th>
                <th>month</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="m" items="${monthlySalesList}">
                <tr>
                	<td>${m.storeId}</td>
                	<td>${m.address}</td>
                	<td>${m.manager}</td>
                	<td>${m.totalSales}</td>
                	<td>${m.year}</td>
                	<td>${m.month}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>