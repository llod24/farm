<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<title>날짜별 작업정보 조회</title>
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<c:import url="nav.jsp"/>
 <div class="container mt-3">        
    <h2>작업 조회</h2>
	<form method="get" action="/farm/work">
	  <div class="form-group">
	    <label for="queryDate">날짜를 선택하세요:</label>
	    <div class="input-group">
	      <input type="date" id="queryDate" name="queryDate" value="${queryDate}" class="form-control"/>
	      <button type="submit" class="btn btn-primary">조회</button>
	    </div>
	  </div>
	</form>
	
	<div class="mt-4">
	  <c:if test="${not empty successMessage}">
	    <div class="alert alert-success">${successMessage}</div>
	  </c:if>
	  
	  <c:if test="${not empty totalFarmWork}">
	    <h3>총 작업량 ${param.queryDate}: ${totalFarmWork}</h3>
	  </c:if>
	  
	  <c:if test="${empty dailyFarmWorks}">
	    <p>데이터 없음</p>
	  </c:if>
	</div>
       
    <c:if test="${not empty dailyFarmWorks}">
      <div class= "text-center">
      <table class="table table-bordered mx-auto">
		  <thead class="table-primary">
		    <tr>
		      <th>일련번호</th>
		      <th>작물이름</th>
		      <th>작업량</th>
		      <th>작업날짜</th>
		      <th>등록한 사람</th>
		      <th>등록 날짜, 시간</th>
		      <th>수정</th>
		      <th>삭제</th>
		    </tr>
		  </thead>
		  <tbody>
		    <c:forEach var="dailyFarmWork" items="${dailyFarmWorks}">
		      <tr>
		        <td>${dailyFarmWork.workId}</td>
		        <td>${dailyFarmWork.cropName}</td>
		        <td>${dailyFarmWork.workload}</td>
		        <td>${dailyFarmWork.date}</td>
		        <td>${dailyFarmWork.username}</td>
		        <td>${dailyFarmWork.updated_at}</td>
		        <td>
		          <c:choose>
		            <c:when test="${sessionScope.authorities == '[admin]' || 
		                (dailyFarmWork.memberId == sessionScope.id && sessionScope.authorities != '[temp]')}">
		              <button class="btn btn-primary btn-sm edit-btn" 
		                      data-bs-toggle="modal" data-bs-target="#myModal" 
		                      data-id="${dailyFarmWork.workId}">수정</button>
		            </c:when>
		          </c:choose>
		        </td>
		        <td>  
		          <c:choose>
		            <c:when test="${sessionScope.authorities == '[admin]' || 
		                (dailyFarmWork.memberId == sessionScope.id && sessionScope.authorities != '[temp]')}">
		              <button class="btn btn-danger btn-sm delete-btn" 
		                      onclick="showModal(${dailyFarmWork.workId})" 
		                      data-bs-toggle="modal" data-bs-target="#myModal" 
		                      data-id="${dailyFarmWork.workId}">삭제</button>
		            </c:when>
		          </c:choose>
		        </td>
		      </tr>
		    </c:forEach>
		  </tbody>
		</table>
	</div>
	<div id="myModal" class="modal fade" tabindex="-1" role="dialog">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title">작업 정보 수정 / 삭제</h5>
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
	
	</c:if>
 </div>
 <script>
$(document).on("click", ".edit-btn", function () {
	//데이터 id로 설정한 workId를 받아옴
	  var workId = $(this).data('id');
	  console.log(workId);
	  $.ajax({
	    url: "/farm/work/" + workId,
	    type: "GET",
	    dataType: "json",
	    success: function (data) {
	      var editForm = createEditForm(data);
	      $('#myModal .modal-body').html(editForm);
	    },
	    error: function (xhr, status, error) {
	      alert('회원 정보를 가져오는데 실패했습니다.');
	    }
	  });
	});

function createEditForm(work) {
	  var form = document.createElement("form");
	  form.id = "edit-form-" + work.id;
	  form.method = "post";
	  form.action = "/farm/work";
	  form.classList.add("form");

	  var originaldata = document.createElement("label");
	  originaldata.innerHTML = "작물이름: " + work.cropName + " 작업량: " + work.workload + " 작업날짜: " + work.date;
	  originaldata.classList.add("form-label");
	  form.appendChild(originaldata);
	  form.appendChild(document.createElement("br"));
	  
	  var divider = document.createElement("hr");
	  form.appendChild(divider);
	  
	  var changeLabel = document.createElement("label");
	  changeLabel.innerHTML = "수정사항을 입력하세요: ";
	  changeLabel.classList.add("form-label");

	  var cropNameSelect = document.createElement("select");
	  cropNameSelect.name = "cropName";
	  cropNameSelect.classList.add("form-select", "mt-3");
	  var cropNames = ["적겨자", "로메인", "치콘"];
	  for (var i = 0; i < cropNames.length; i++) {
	    var cropNameOption = document.createElement("option");
	    cropNameOption.value = cropNames[i];
	    cropNameOption.text = cropNames[i];
	    cropNameSelect.appendChild(cropNameOption);
	  }
	  form.appendChild(changeLabel);
	  form.appendChild(cropNameSelect);

	  var workload = document.createElement("input");
	  workload.type = "text";
	  workload.name = "workload";
	  workload.classList.add("form-control", "mt-3");
	  form.appendChild(workload);

	  var date = document.createElement("input");
	  date.type = "date";
	  date.name = "date";
	  date.classList.add("form-control", "mt-3", "mb-3");
	  form.appendChild(date);

	  var workId = document.createElement("input");
	  workId.type = "hidden";
	  workId.name = "workId";
	  workId.value = work.workId;
	  form.appendChild(workId);

	  var submitButton = document.createElement("input");
	  submitButton.type = "submit";
	  submitButton.value = "수정";
	  submitButton.classList.add("btn", "btn-primary");
	  form.appendChild(submitButton);

	  var cancelButton = document.createElement("button");
	  cancelButton.type = "button";
	  cancelButton.innerHTML = "취소";
	  cancelButton.classList.add("btn", "btn-secondary");
	  cancelButton.onclick = function () {
	    closeModal();
	  };

	  form.appendChild(cancelButton);
	  return form;
	}


$(document).on("click", ".edit-btn", function () {
	//데이터 id로 설정한 workId를 받아옴
	  var workId = $(this).data('id');
	  console.log(workId);
	  $.ajax({
	    url: "/farm/work/" + workId,
	    type: "GET",
	    dataType: "json",
	    success: function (data) {
	      var editForm = createEditForm(data);
	      $('#myModal .modal-body').html(editForm);
	    },
	    error: function (xhr, status, error) {
	      alert('회원 정보를 가져오는데 실패했습니다.');
	    }
	  });
	});

function showModal(workId) {
	var form = document.createElement("form");
	form.id = "delete-form-" + workId;
	form.method = "post";
	form.action = "/farm/work/delete";
	form.classList.add("form");

	var deleteString = document.createElement("label");
	deleteString.innerHTML = "삭제하시겠습니까?<br><br>";
	deleteString.classList.add("form-label");
	form.appendChild(deleteString);

	var workIdInput = document.createElement("input");
	workIdInput.type = "hidden";
	workIdInput.name = "workId";
	workIdInput.value = workId;
	form.appendChild(workIdInput);

	var cancelButton = document.createElement("button");
	cancelButton.type = "button";
	cancelButton.innerHTML = "취소";
	cancelButton.classList.add("btn", "btn-secondary", "me-2");
	cancelButton.onclick = function () {
	closeModal();
	};


	var submitButton = document.createElement("input");
	submitButton.type = "submit";
	submitButton.value = "삭제";
	submitButton.classList.add("btn", "btn-danger");
	
	var buttonWrapper = document.createElement("div");
	buttonWrapper.classList.add("text-center");
	buttonWrapper.appendChild(cancelButton);
	buttonWrapper.appendChild(submitButton);
	
	form.appendChild(buttonWrapper);
	
	$('#myModal .modal-body').html(form);
	$('#myModal').modal('show');
	} 

function closeModal() {
	$('#myModal').modal('hide');
	}
 </script>   
 
</body>
</html>