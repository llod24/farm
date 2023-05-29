<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>농장 작업 입력</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css">
</head>
<body>
<c:import url="nav.jsp"/>
<div class="container mt-3" style="border: 2px var(--bs-success-border-subtle) solid">
  <c:if test="${not empty error}">
	    <div class="alert alert-danger mt-3">${error}</div>
  </c:if>
  <form method="post" action="/farm/add">
    <input type="hidden" id="inputCount" name="inputCount" value="1">
    <div class="mb-3 mt-3">
      <label for="cropName-1" class="form-label">작물 이름:</label>
      <select name="cropName-1" class="form-select">
        <option value="적겨자">적겨자</option>
        <option value="로메인">로메인</option>
        <option value="치콘">치콘</option>
      </select>
    </div>
    <div class="mb-3">
      <label for="workload-1" class="form-label">작업량:</label>
      <input type="number" name="workload-1" class="form-control">
    </div>
    <div class="mb-3">
      <label for="date-1" class="form-label">작업날짜:</label>
      <input type="date" name="date-1" class="form-control">
    </div>
    <div id="input-container">
      <!-- 추가할 입력창 -->
    </div>
    <div>
       <button type="button" id="add-input" class="btn btn-success">작업 추가</button>
     </div>
     <div class="text-end mb-3">
       <button type="submit" class="btn btn-success">등록</button>
    </div>
  </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
<script>
var maxInputCount = 5; // 입력창 최대 개수
var inputContainer = document.getElementById("input-container");
var inputCountInput = document.getElementById("inputCount");
var inputCount = inputCountInput.value;
console.log(inputCount);

function addInput() {
  if (inputCount < maxInputCount) {
    inputCount++;
    inputCountInput.value = inputCount;

    var newInput = document.createElement("div");
    newInput.id = "input-" + inputCount;
    newInput.classList.add("mb-3", "input-row");

    newInput.innerHTML = '<div class="mb-3">' +
    '<label for="cropName-' + inputCount + '" class="form-label">작물 이름:</label>' +
    '<select name="cropName-' + inputCount + '" class="form-select">' +
    '<option value="적겨자">적겨자</option>' +
    '<option value="로메인">로메인</option>' +
    '<option value="치콘">치콘</option>' +
    '</select>' +
    '</div>' +
    '<div class="mb-3">' +
    '<label for="workload-' + inputCount + '" class="form-label">작업량:</label>' +
    '<input type="number" name="workload-' + inputCount + '" class="form-control">' +
    '</div>' +
    '<div class="mb-3">' +
    '<label for="date-' + inputCount + '" class="form-label">작업날짜:</label>' +
    '<input type="date" name="date-' + inputCount + '" class="form-control">' +
    '</div>' +
    '<button type="button" class="btn btn-danger" onclick="removeInput(' + inputCount + ')">-</button>';
    inputContainer.appendChild(newInput);
  }
}

function removeInput(index) {
  if (inputCount > 0) {
    var input = document.getElementById("input-" + index);
    input.remove();
    inputCount--;
    inputCountInput.value = inputCount;
  }
}

document.getElementById("add-input").addEventListener("click", addInput);
</script>
</body>
</html>