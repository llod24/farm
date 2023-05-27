<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>농장 작업 입력</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<c:import url="nav.jsp"/>
	<form method="post" action="/farm/add">
	  <input type="hidden" id="inputCount" name="inputCount" value="1">
	  <label>작물 이름:</label>
	  <select name="cropName-1">
	    <option value="적겨자">적겨자</option>
	    <option value="로메인">로메인</option>
	    <option value="치콘">치콘</option>
	  </select>
	  <br>
	  <label>작업량:</label>
	  <input type="number" name="workload-1">
	  <br>
	  <label>작업날짜:</label>
	  <input type="date" name="date-1">
	  <br>
	  <div id="input-container">
	    <!-- 추가할 입력창 -->
	</div>
	<button type="button" id="add-input">+</button>
	<br>
	<button type="submit">작업 기록 추가</button>
  </form>
  
  <script>
  var maxInputCount = 5; // 입력창 최대 개수
  var inputContainer = document.getElementById("input-container");
  var inputCountInput = document.getElementById("inputCount");
  var inputCount = inputCountInput.value;
  function addInput() {
    if (inputCount < maxInputCount) {
      inputCount++;
      inputCountInput.value = inputCount;
      var newInput = document.createElement("div");
      newInput.id = "input-" + inputCount;
      newInput.innerHTML = '<label>작물 이름:</label>' +
      '<select name="cropName-' + inputCount + '">' +
      '<option value="적겨자">적겨자</option>' +
      '<option value="로메인">로메인</option>' +
      '<option value="치콘">치콘</option>' +
      '</select><br>' +
      '<label>작업량:</label>' +
      '<input type="number" name="workload-' + inputCount + '"><br>' +
      '<label>작업날짜:</label>' +
      '<input type="date" name="date-' + inputCount + '"><br>' +
      '<button type="button" onclick="removeInput(' + inputCount + ')">-</button>'; 
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