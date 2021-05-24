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
</head>
<body>
<div class="container">
    <h1>getFilmList</h1>
    
    <table class="table table-striped">
        <thead>
            <tr>
                <th>filmId</th>
                <th>title</th>
                <th>categoryName</th>
                <th>description</th>
                <th>releaseYear</th>
                <th>languageId</th>
                <th>rentalDuration</th>
                <th>rentalRate</th>
                <th>LENGTH</th>
                <th>replacementCost</th>
                <th>rating</th>
                <th>specialFeatures</th>
                <th>lastUpdate</th>
                <th>actors</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="f" items="${filmList}">
                <tr>
                    <td class="col-sm-1 text-center">${f.filmId}</td>
                    <td class="col-sm-2 text-center">
                    	<a href="${pageContext.request.contextPath}/admin/getFilmOne?filmId=${f.filmId}">${f.title}</a>
                    </td>
                    <td class="col-sm-2 text-center">${f.categoryName}</td>
                    <td class="col-sm-1 text-center">${f.description}</td>
                    <td class="col-sm-1 text-center">${f.releaseYear}</td>
                    <td class="col-sm-1 text-center">${f.languageId}</td>
                    <td class="col-sm-1 text-center">${f.rentalDuration}</td>
                    <td class="col-sm-1 text-center">${f.rentalRate}</td>
                    <td class="col-sm-2 text-center">${f.LENGTH}</td>
                    <td class="col-sm-1 text-center">${f.replacementCost}</td>
                    <td class="col-sm-1 text-center">${f.rating}</td>
                    <td class="col-sm-1 text-center">${f.specialFeatures}</td>
                    <td class="col-sm-1 text-center">${f.lastUpdate}</td>
                    <td class="col-sm-1 text-center">${f.actors}</td>
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