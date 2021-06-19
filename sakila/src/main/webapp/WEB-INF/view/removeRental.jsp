<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>removeRental</title>
<!-- bootstrap을 사용하기 위한 CDN주소 -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<!-- jquery를 사용하기위한 CDN주소 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
$(document).ready(function(){
	$('#customerId').change(function(){
		console.log('rentalId 목록');
		$.ajax({
			type:'get',
			url:'/getRentalIdList',
			data:{filmId : $('#customerId').val()},
			success: function(jsonData) {
				$('#rentalId').empty();
				$(jsonData).each(function(index, item) {
					$('#rentalId').append(
						'<option value="'+item.rental_id+'">'+item.rental_id+'</option>'
					);
				});
			}
		});
	});
	
	$('#btn').click(function(){
		console.log('btn click!'); // 웹브라우저 콘솔에서 확인 가능
		// 폼 유효성 검사 코드 추가
		$('#removeForm').submit();
	});
});
</script>
</head>
<body>
<div class="container">
      <h1>removeRental</h1>
      
      <div>
    	<a href="${pageContext.request.contextPath}/admin/getBoardList">BoardList</a>
        <a href="${pageContext.request.contextPath}/admin/getStaffList">StaffList</a>
    	<a href="${pageContext.request.contextPath}/admin/getFilmList">FilmList</a>
    	<a href="${pageContext.request.contextPath}/admin/getActorList">ActorList</a>
    	<a href="${pageContext.request.contextPath}/admin/getCustomerList">CustomerList</a>
    	<a href="${pageContext.request.contextPath}/admin/getInventoryList">InventoryList</a>
    	<a href="${pageContext.request.contextPath}/admin/getSalesList">SalesList</a>
    </div>
    
      <form id="removeForm" method="post" action="${pageContext.request.contextPath}/admin/removeRental">
         <table class="table table-hover">
         	<tr>
               <td>customerId</td>
               <td>
               	 <input type="text" name="customerId" id="customerId" value="${customerId}" readonly="readonly" class="form-control">
               </td>
            </tr>
            <tr>
               <td>filmTitle</td>
               <td>
                  <select name="title" id ="rentalId" class="form-control"></select>
               </td>
            </tr>
         </table>
         <button id="btn" class="btn btn-secondary">반납</button>
      </form>
</div>
</body>
</html>