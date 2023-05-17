<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<title>월별 작업정보 조회</title>
</head>
<body>     
<h2>작업 조회</h2>
<form method="get" action="/farm/work/month">
    <div class="form-group">
        <label for="queryDate">년도, 월을 선택하세요:</label>
        <input type="month" id="queryMonth" name="queryMonth" value="${queryMonth}"/>
    	<button type="submit">조회</button>
    </div>
</form>
<div>
	<c:if test="${not empty successMessage}">
		<div class="success-message">${successMessage}</div>
	</c:if>
	<c:if test="${not empty totalFarmWork}">
		<h3>총 작업량 ${param.queryDate}: ${totalFarmWork}</h3>
	</c:if>
</div>
<c:if test="${empty monthlyFarmWorks}">
	<p> 데이터 없음 </p>
</c:if>
      
<c:if test="${not empty monthlyFarmWorks}">
<table class="table" border="1">
  <thead>
    <tr>
      <th>일련번호</th>
      <th>작물이름</th>
      <th>작업량</th>
      <th>작업날짜</th>
      <th>등록한 사람</th>
      <th>등록 날짜, 시간</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="monthlyFarmWork" items="${monthlyFarmWorks}">
      <tr>
        <td>${monthlyFarmWork.workId}</td>
        <td>${monthlyFarmWork.cropName}</td>
        <td>${monthlyFarmWork.workload}</td>
        <td>${monthlyFarmWork.date}</td>
        <td>${monthlyFarmWork.username}</td>
        <td>${monthlyFarmWork.updated_at}</td>
      </tr>
    </c:forEach>
  </tbody>
</table>
</c:if>
</body>
</html>