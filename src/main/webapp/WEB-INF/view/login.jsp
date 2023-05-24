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
	<div class="container justify-content-center" style="height:100vh;">
    <div class="row ">
        <div class="col-md-4 offset-md-4 bg-secondary">
            <h2>로그인하기</h2>
            <form action="/farm/login" method="post" class="container">
                <div class="form-group mb-3">
                    <input type="email" id="email" name="email" class="form-control" placeholder= "이메일을 입력하세요" required >
                </div>        
                <div class="form-group mb-3">
                    <input type="password" id="password" name="password" class="form-control" placeholder= "비밀번호를 입력하세요" required>
                </div>
				<div class="form-group col-md-4 offset-md-4 ">
					<button type="submit" class="btn btn-primary">로그인</button>
				</div>
            </form>
        </div>
    </div>
    <div class="row">
        <div class="col-md-4 offset-md-4 border bg-success " >
            <a href="/farm/register">회원가입</a>
        </div>
    </div>
</div>
	</div>
</body>
</html>