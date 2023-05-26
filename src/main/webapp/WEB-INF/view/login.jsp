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
<div class="container justify-content-center" style="height:100vh;">
    <div class="row ">
        <div class="col-md-4 offset-md-4" style="border: 5px var(--bs-success-border-subtle) solid">
            <div class="text-center mb-3 mt-3">
            <h2>로그인하기</h2>
			</div>
            <form action="/farm/login" method="post" >
                <div class="form-group mb-3">
                    <input type="email" id="email" name="email" class="form-control" placeholder= "이메일을 입력하세요" required >
                </div>        
                <div class="form-group mb-3">
                    <input type="password" id="password" name="password" class="form-control" placeholder= "비밀번호를 입력하세요" required>
                </div>
				<div class="col-md-4 offset-md-4 mb-3">
					<div class="d-flex justify-content-center">
						<button type="submit" class="btn btn-primary" style="background-color: var(--bs-success-bg-subtle); color:#9C736D ">로그인</button>
					</div>
				</div>
            </form>
        </div>
    </div>
	<br>
	<div class="col-md-4 offset-md-4 mb-3">
		<div class="d-flex justify-content-center">
			 <a href="/farm/register" style=" color:#9C736D; text-decoration: none;">회원가입</a>
		</div>
	</div>
</div>
</body>
</html>