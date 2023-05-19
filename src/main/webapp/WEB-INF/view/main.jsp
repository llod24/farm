<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
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