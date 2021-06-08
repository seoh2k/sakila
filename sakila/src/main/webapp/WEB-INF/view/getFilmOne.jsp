<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>FilmOne</title>
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
					<td>filmId :</td>
					<td>${filmMap.filmId}</td>
				</tr>
	            <tr>
	            	<td>title :</td>
	            	<td>${filmMap.title}</td>
	            </tr>
	            <tr>
	            	<td>description :</td>
	            	<td>${filmMap.description}</td>
	            </tr>
	            <tr>
	            	<td>releaseYear :</td>
	            	<td>${filmMap.releaseYear}</td>
	            </tr>
	            <tr>
	            	<td>language :</td>
	            	<td>${filmMap.language}</td>
	            </tr>
	            <tr>
	            	<td>originalLanguageId :</td>
	            	<td>${filmMap.originalLanguageId}</td>
	            </tr>
	            <tr>
	            	<td>rentalDuration :</td>
	            	<td>${filmMap.rentalDuration}</td>
	            </tr>
	            <tr>
	            	<td>rentalRate :</td>
	            	<td>${filmMap.rentalRate}</td>
	            </tr>
	            <tr>
					<td>length :</td>
					<td>${filmMap.length}</td>
				</tr>
				<tr>
					<td>replacementCost :</td>
					<td>${filmMap.replacementCost}</td>
				</tr>
				<tr>
					<td>rating: </td>
					<td>${filmMap.rating}</td>
				</tr>
				<tr>
					<td>specialFeatures :</td>
					<td>${filmMap.specialFeatures}</td>
				</tr>
				<tr>
					<td>lastUpdate :</td>
					<td>${filmMap.lastUpdate}</td>
				</tr>
				<tr>
					<!-- getFilmActorListByFilm: 컨트롤러 -->
					<td>
						<a href="${pageContext.request.contextPath}/admin/getFilmActorListByFilm?filmId=${filmMap.filmId}">
							<button type="button">출연배우 수정</button>
						</a>actors :
					</td>
					<td>${filmMap.actors}</td>
				</tr>
				<tr>
					<td>category :</td>
					<td>${filmMap.category}</td>
				</tr>
				<tr>
					<td>storeId 1 stock :</td>
					<td>${firstStoreFilmCount}</td>
				</tr>
				<tr>
					<td>storeId 2 stock :</td>
					<td>${secondStoreFilmCount}</td>
				</tr>
			</tbody>
		</table>
	    <a class="btn btn-default" 
	    	href="${pageContext.request.contextPath}/admin/getFilmList">영화목록</a>
</div>   
</body>
</html>