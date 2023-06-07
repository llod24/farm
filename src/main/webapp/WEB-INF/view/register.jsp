<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>회원가입</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<div class="container justify-content-center" style="height:100vh;">
    <div class="row">
        <div class="col-md-4 offset-md-4" style="border: 5px var(--bs-success-border-subtle) solid">
            <div class="text-center mb-3 mt-3">
				<h2>회원가입</h2>
			</div>
            <form action="/farm/register" method="post" class="needs-validation" novalidate>
			    <div class="mb-3">
			        <label for="username" class="form-label">Username:</label>
			        <input type="text" id="username" name="username" class="form-control" placeholder= "이름을 입력하세요" required>
			    </div>
			    <div class="mb-3">
			        <label for="email" class="form-label">Email:</label>
			        <input type="email" id="email" name="email" class="form-control" placeholder= "이메일을 입력하세요" required>
			    </div>
			    <div class="mb-3">
			        <label for="password" class="form-label">Password:</label>
			        <input type="password" id="password" name="password" class="form-control" placeholder= "비밀번호를 입력하세요" required>
			    </div>
			    
			    <div class="col-md-4 offset-md-4 mb-3">
					<div class="d-flex justify-content-center">
						<button type="submit" class="btn btn-primary" style="background-color: var(--bs-success-bg-subtle); color:#9C736D ">가입하기</button>
					</div>
				</div>
			</form>
        </div>
    </div>
</div>
</body>
</html>