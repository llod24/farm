<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<div class="align-middle"> 
	<h2>로그인하기</h2>
	<c:if test="${not empty error}">
	    <p style="color:red">${error}</p>
	</c:if>
	<c:if test="${not empty success}">
	    <p style="color:blue">${success}</p>
	</c:if>
	<form action="/farm/login" method="post" class="container">
	    <div class="form-group">
	        <label for="email">Email:</label>
	        <input type="email" id="email" name="email" class="form-control" required>
	    </div>        
	    <div class="form-group">
	        <label for="password">Password:</label>
	        <input type="password" id="password" name="password" class="form-control" required>
	    </div>
	    <button type="submit" class="btn btn-primary">로그인</button>
	</form>
	<a href="/farm/register">회원가입</a>
	</div>
</body>
</html>