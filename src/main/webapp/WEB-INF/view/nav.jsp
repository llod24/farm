<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-expand-lg" style="background-color: var(--bs-success-bg-subtle);">
  <div class="container-fluid">
    <a class="navbar-brand" href="/farm/main">Home</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
		  <li class="nav-item">
		    <a class="nav-link" href="/farm/add">작업등록</a>
		  </li>
		  <li class="nav-item dropdown">
		    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
		      조회
		    </a>
		    <ul class="dropdown-menu">
		      <li><a class="dropdown-item" href="/farm/work">일간</a></li>
		      <li><a class="dropdown-item" href="/farm/work/month">월간</a></li>
		    </ul>
		  </li>
		  <li class="nav-item">
		    <a class="nav-link active" aria-current="page" href="/farm/work/chart">차트</a>
		  </li>
		  <li class="nav-item">
		    <a class="nav-link active" aria-current="page" href="/farm/manage">관리</a>
		  </li>
		</ul>
		<ul class="navbar-nav">
		  <c:choose>
		    <c:when test="${empty sessionScope.name}">
		      <li class="nav-item">
		        <a class="nav-link active" aria-current="page" href="/farm/login">로그인</a>
		      </li>
		      <li class="nav-item">
		        <a class="nav-link active" aria-current="page" href="/farm/register">회원가입</a>
		      </li>
		    </c:when>
		    <c:otherwise>
		        <li class="nav-item">
			      <span class="nav-link active">${sessionScope.name}님 안녕하세요</span>
			    </li>
		      <li class="nav-item">
		        <form action="/farm/logout" method="post">
		          <button class="btn btn-outline-success" type="submit">로그아웃</button>
		        </form>
		      </li>
		    </c:otherwise>
		  </c:choose>
		</ul>
    </div>
  </div>
</nav>	
