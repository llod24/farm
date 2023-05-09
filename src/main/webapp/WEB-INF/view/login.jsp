<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
	<h2>로그인하기</h2>
	<c:if test="${not empty error}">
        <p style="color:red">${error}</p>
    </c:if>
    <c:if test="${not empty success}">
        <p style="color:blue">${success}</p>
    </c:if>
    <form action="/farm/login" method="post">
        <div>
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>
        </div>        
        <div>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
        </div>
        <button type="submit">로그인</button>
    </form>
</body>
</html>