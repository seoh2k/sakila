<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Board List</title>
      <!-- Meta -->
      <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
      <meta http-equiv="X-UA-Compatible" content="IE=edge" />
      <meta name="description" content="Mega Able Bootstrap admin template made using Bootstrap 4 and it has huge amount of ready made feature, UI components, pages which completely fulfills any dashboard needs." />
      <meta name="keywords" content="bootstrap, bootstrap admin template, admin theme, admin dashboard, dashboard template, admin template, responsive" />
      <meta name="author" content="codedthemes" />
      <!-- Favicon icon -->
      <link rel="icon" href="${pageContext.request.contextPath}/assets/images/favicon.ico" type="image/x-icon">
      <!-- Google font-->     <link href="https://fonts.googleapis.com/css?family=Roboto:400,500" rel="stylesheet">
      <!-- waves.css -->
      <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/pages/waves/css/waves.min.css" type="text/css" media="all">
      <!-- Required Fremwork -->
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/bootstrap/css/bootstrap.min.css">
      <!-- waves.css -->
      <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/pages/waves/css/waves.min.css" type="text/css" media="all">
      <!-- themify-icons line icon -->
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/icon/themify-icons/themify-icons.css">
      <!-- Font Awesome -->
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/icon/font-awesome/css/font-awesome.min.css">
      <!-- ico font -->
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/icon/icofont/css/icofont.css">
      <!-- Style.css -->
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/style.css">
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/jquery.mCustomScrollbar.css">
</head>
<body>
	<!-- Header -->
    <jsp:include page="/WEB-INF/view/header.jsp"></jsp:include>	
    
<div class="container">
    
    <h1>BoardList</h1>
    <div>
    	<a href="${pageContext.request.contextPath}/admin/getBoardList">BoardList</a>
        <a href="${pageContext.request.contextPath}/admin/getStaffList">StaffList</a>
    	<a href="${pageContext.request.contextPath}/admin/getFilmList">FilmList</a>
    	<a href="${pageContext.request.contextPath}/admin/getActorList">ActorList</a>
    	<a href="${pageContext.request.contextPath}/admin/getCustomerList">CustomerList</a>
    	<a href="${pageContext.request.contextPath}/admin/getInventoryList">InventoryList</a>
    	<a href="${pageContext.request.contextPath}/admin/getSalesList">SalesList</a>
    </div>
        
    <table class="table table-striped">
        <thead>
            <tr>
                <th>boardId</th>
                <th>boardTitle</th>
                <th>boardDate</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="b" items="${boardList}">
                <tr>
                	<td>${b.boardId}</td>
                    <td><a href="${pageContext.request.contextPath}/admin/getBoardOne?boardId=${b.boardId}">${b.boardTitle}</a></td>
                    <td>${b.insertDate}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    
    <!-- 검색어 입력창 -->
    <form action="/getBoardList" method="get">
        <label for="searchWord">검색어(제목) :</label> 
        <input name="searchWord" type="text">
        <button type="submit">검색</button>
    </form>
    
    <ul class="pager">
        <c:if test="${currentPage > 1}">
            <li class="previous"><a href="${pageContext.request.contextPath}/admin/getBoardList?currentPage=${currentPage-1}&searchWord=${searchWord}">이전</a></li>
        </c:if>
        <c:if test="${currentPage < lastPage}">
            <li class="next"><a href="${pageContext.request.contextPath}/admin/getBoardList?currentPage=${currentPage+1}&searchWord=${searchWord}">다음</a></li>
        </c:if>
    </ul>
    <div>
        <a class="btn btn-default" href="${pageContext.request.contextPath}/admin/addBoard">게시글 입력</a>
    </div>
    
    <!-- Required Jquery -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery-ui/jquery-ui.min.js "></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/popper.js/popper.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/bootstrap/js/bootstrap.min.js "></script>
    <!-- waves js -->
    <script src="${pageContext.request.contextPath}/assets/pages/waves/js/waves.min.js"></script>
    <!-- jquery slimscroll js -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery-slimscroll/jquery.slimscroll.js "></script>
    <!-- waves js -->
    <script src="${pageContext.request.contextPath}/assets/pages/waves/js/waves.min.js"></script>
    <!-- modernizr js -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/modernizr/modernizr.js "></script>
    <!-- Custom js -->
    <script src="${pageContext.request.contextPath}/assets/js/pcoded.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/vertical-layout.min.js "></script>
    <script src="${pageContext.request.contextPath}/assets/js/jquery.mCustomScrollbar.concat.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/script.js"></script>
</div>
</body>
</html>