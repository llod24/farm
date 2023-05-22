<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container-fluid">
    <a class="navbar-brand" href="/farm">Home</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="/farm/manage">관리</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/farm/add">작업추가</a>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Dropdown
          </a>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="/farm/work">조회</a></li>
            <li><a class="dropdown-item" href="/farm/work/month">월간</a></li>
            <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item" href="#">Something else here</a></li>
          </ul>
        </li>
      </ul>
    </div>
  </div>
</nav>
	<a href="/farm/manage">manage</a>
	  <a href="/farm/add"> add </a>
	  <a href="/farm/work"> load </a>
	  <a href="/farm/work/month"> month </a>
	  <a href="/farm/work/chart"> chart </a>
	<c:if test="${empty sessionScope.user}">
	  <!-- 로그인 되어 있지 않은 경우 -->
	  <a href="/farm/register"> register </a>
	  <a href="/farm/login">login</a>
	</c:if>
	<c:if test="${not empty sessionScope.user}">
	  <!-- 로그인 되어 있는 경우 -->
	  <br>
	  <form action="/farm/logout" method="post">
	    <button type="submit">로그아웃</button>
	  </form>
	</c:if>
</body>
</html>