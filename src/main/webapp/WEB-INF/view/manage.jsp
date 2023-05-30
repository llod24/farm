<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script></head>
<body>
<c:import url="nav.jsp"/>
<div class="container mt-3">
<div class="text-center">
    <table class="table table-bordered mx-auto">
    <thead class="table-primary">
    <tr>
      <th>ID</th>
      <th>이름</th>
      <th>이메일</th>
      <th>권한</th>
      <th>권한 수정</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="member" items="${members}">
      <tr>
        <td>${member.id}</td>
        <td>${member.username}</td>
        <td>${member.email}</td>
        <td>${member.role}</td>
        <td> 
        
        <button class="btn btn-primary edit-btn" 
        data-bs-toggle="modal" data-bs-target="#myModal" 
        data-id="${member.id}"> 수정 </button></td>
      </tr>
    </c:forEach>
  </tbody>
</table>
</div>
</div>
<div id="myModal" class="modal fade" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">회원 정보 수정</h5>
        <button type="button" class="close" data-bs-dismiss="modal" aria-label="Close">
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
	  console.log(memberId);
	  $.ajax({
	    url: "/farm/manage/" + memberId,
	    type: "GET",
	    dataType: "json",
	    success: function (data) {
	      var editForm = createEditForm(data);
	      console.log(data);
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
	  form.classList.add("form");

	  var usernameLabel = document.createElement("label");
	  usernameLabel.innerHTML = "사용자이름: " + member.username;
	  usernameLabel.classList.add("form-label");
	  form.appendChild(usernameLabel);

	  var emailLabel = document.createElement("label");
	  emailLabel.innerHTML = " / 이메일: " + member.email;
	  emailLabel.classList.add("form-label");
	  form.appendChild(emailLabel);

	  var roleLabel = document.createElement("label");
	  roleLabel.innerHTML = " / 권한: " + member.role;
	  roleLabel.classList.add("form-label");
	  form.appendChild(roleLabel);
	  
	  var editInputGroup = document.createElement("div");
	  editInputGroup.classList.add("input-group", "mb-3");

	  var editLabel = document.createElement("label");
	  editLabel.innerHTML = "변경할 권한: ";
	  editLabel.classList.add("input-group-text");
	  editInputGroup.appendChild(editLabel);

	  var roleSelect = document.createElement("select");
	  roleSelect.name = "role";
	  roleSelect.classList.add("form-select");
	  var roles =  ${options}; 
	  for (var i = 0; i < roles.length; i++) {
	    var roleOption = document.createElement("option");
	    roleOption.value = roles[i];
	    roleOption.text = roles[i];
	    if (roles[i] == member.role) {
	      roleOption.selected = true;
	    }
	    roleSelect.appendChild(roleOption);
	  }
	  editInputGroup.appendChild(roleSelect);

	  form.appendChild(editInputGroup);

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
	  submitButton.classList.add("btn", "btn-primary");
	  form.appendChild(submitButton);

	  var cancelButton = document.createElement("button");
	  cancelButton.type = "button";
	  cancelButton.innerHTML = "Cancel";
	  cancelButton.classList.add("btn", "btn-secondary");
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