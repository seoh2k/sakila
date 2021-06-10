<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>removeInventory</title>
<!-- bootstrap�� ����ϱ� ���� CDN�ּ� -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
$(document).ready(function(){
	console.log('title ���');
	$.ajax({
		type:'get',
		url:'/getFilmTitleList',
		success: function(jsonData) {
			$(jsonData).each(function(index, item) {
				$('#filmId').append(
					'<option value="'+item.film_id+'">'+item.title+'</option>'
				);
			});
		}
	}); // ��ȭ ���� ����� �޾ƿͼ� filmId select �±׾ȿ� option�±׸� �߰�
	
	$('#filmId').change(function(){
		console.log('inventoryId ���');
		$.ajax({
			type:'get',
			url:'/getInventoryIdList',
			data:{filmId : $('#filmId').val()},
			success: function(jsonData) {
				$('#inventoryId').empty();
				$(jsonData).each(function(index, item) {
					$('#inventoryId').append(
						'<option value="'+item.inventoryId+'">'+item.inventoryId+'</option>'
					);
				});
			}
		}); // ��� id�� �޾ƿͼ� inventoryId select �±׾ȿ� option�±׸� �߰�
	});
	
	$('#btn').click(function(){
		console.log('btn click!'); // �������� �ֿܼ��� Ȯ�� ����
		// �� ��ȿ�� �˻� �ڵ� �߰�
		$('#removeForm').submit();
	});
});
</script>
</head>
<body>
<div class="container">
	<h1>removeInventory</h1>
	<div>
    	<a href="${pageContext.request.contextPath}/admin/getBoardList">BoardList</a>
        <a href="${pageContext.request.contextPath}/admin/getStaffList">StaffList</a>
    	<a href="${pageContext.request.contextPath}/admin/getFilmList">FilmList</a>
    	<a href="${pageContext.request.contextPath}/admin/getActorList">ActorList</a>
    	<a href="${pageContext.request.contextPath}/admin/getCustomerList">CustomerList</a>
    	<a href="${pageContext.request.contextPath}/admin/getInventoryList">InventoryList</a>
    	<a href="${pageContext.request.contextPath}/admin/getSalesList">SalesList</a>
    </div>
    
	<form id="removeForm" action="${pageContext.request.contextPath}/admin/removeInventory" method="post">
		<table class="table table-hover">
			<tr>
				<td>store: </td>
				<td>
					<select name="storeId" id="storeId">
						<option value='' selected>-- ���� --</option>
						<option value='1'>1ȣ��</option>
						<option value='2'>2ȣ��</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>film: </td>
				<td><select name="filmId" id="filmId"></select></td>
			</tr>
			<tr>
				<td>inventory: </td>
				<td><select name="inventoryId" id="inventoryId"></select></td>
			</tr>
		</table>
		<button type="button" id="btn">����</button>
	</form>
</div>
</body>
</html>