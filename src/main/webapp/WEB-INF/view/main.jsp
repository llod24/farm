<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
<script src='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.7/index.global.min.js'></script>
<script>

      document.addEventListener('DOMContentLoaded', function() {
        var calendarEl = document.getElementById('calendar');
        var calendar = new FullCalendar.Calendar(calendarEl, {
          initialView: 'dayGridMonth'
        });
        calendar.render();
      });

</script>
</head>
<body>
<nav class="navbar navbar-expand-lg" style="background-color: var(--bs-success-bg-subtle);">
  <div class="container-fluid">
    <a class="navbar-brand" href="/farm">Home</a>
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
    </div>
  </div>
</nav>	
	
<form action="/farm/logout" method="post">
  <label> ${sessionScope.name}님 안녕하세요</label>
  <button type="submit">로그아웃</button>
</form>
	  
	  
<div id='calendar'></div>
</body>
</html>