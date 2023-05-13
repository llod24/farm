<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</head>
<body>
<table class="table" border="1">
  <thead>
    <tr>
      <th>ID</th>
      <th>이름</th>
      <th>이메일</th>
      <th>권한</th>
      <th>권한 수정</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="member" items="${dailyFarmWorks}">
      <tr>
        <td>${member.id}</td>
        <td>${member.username}</td>
        <td>${member.email}</td>
        <td>${member.role}</td>
        <td> <button class="btn btn-primary edit-btn" data-toggle="modal" data-target="#myModal" data-id="${member.id}"> 수정 </button></td>
      </tr>
    </c:forEach>
  </tbody>
</table>

<div id="myModal" class="modal fade" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">회원 정보 수정</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <!-- 수정 폼 -->
      </div>
    </div>
  </div>
</div>
<script>
$(document).on("click", ".edit-btn", function () {
	  var memberId = $(this).data('id');
	  $.ajax({
	    url: "/farm/manage/" + memberId,
	    type: "GET",
	    dataType: "json",
	    success: function (data) {
	      // 서버에서 받은 회원 정보를 이용해 수정 폼을 만듭니다.
	      var editForm = createEditForm(data);
	      $('#myModal .modal-body').html(editForm);
	    },
	    error: function (xhr, status, error) {
	      alert('회원 정보를 가져오는데 실패했습니다.');
	    }
	  });
	});

function createEditForm(member) {
	  var form = document.createElement("form");
	  form.id = "edit-form-" + member.id;
	  form.method = "post";
	  form.action = "/farm/manage";

	  var usernameLabel = document.createElement("label");
	  usernameLabel.innerHTML = "Username: " + member.username;
	  form.appendChild(usernameLabel);

	  var emailLabel = document.createElement("label");
	  emailLabel.innerHTML = "Email: " + member.email;
	  form.appendChild(emailLabel);

	  var roleLabel = document.createElement("label");
	  roleLabel.innerHTML = "Role: ";
	  var roleSelect = document.createElement("select");
	  roleSelect.name = "role";
	  var roles = ["admin", "worker", "권한없음"];
	  for (var i = 0; i < roles.length; i++) {
	    var roleOption = document.createElement("option");
	    roleOption.value = roles[i];
	    roleOption.text = roles[i];
	    if (roles[i] == member.role) {
	      roleOption.selected = true;
	    }
	    roleSelect.appendChild(roleOption);
	  }
	  form.appendChild(roleLabel);
	  form.appendChild(roleSelect);
	  
	  var originalRole = document.createElement("input");
	  originalRole.type = "hidden";
	  originalRole.name = "originalRole";
	  originalRole.value = member.role;
	  form.appendChild(originalRole);
	  
	  var memberId = document.createElement("input");
	  memberId.type = "hidden";
	  memberId.name = "memberId";
	  memberId.value = member.id;
	  form.appendChild(memberId);
	  
	  var submitButton = document.createElement("input");
	  submitButton.type = "submit";
	  submitButton.value = "Save";
	  submitButton.disabled = true;
	  form.appendChild(submitButton);

	  var cancelButton = document.createElement("button");
	  cancelButton.type = "button";
	  cancelButton.innerHTML = "Cancel";
	  cancelButton.onclick = function() {
	    closeModal();
	  };
	  form.appendChild(cancelButton);
	
	  roleSelect.addEventListener("change", function() {
		  if (roleSelect.value == member.role) {
		    submitButton.disabled = true;
		  } else {
		    submitButton.disabled = false;
		  }
	  });
	  
	  return form;
	}
</script>

</body>
</html>