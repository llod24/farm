<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
 <div class="container">        
        <h2>Total Workload</h2>
        <form method="get" action="/farm/work">
            <div class="form-group">
                <label for="queryDate">Query Date:</label>
                <input type="date" id="queryDate" name="queryDate">
            </div>
            <button type="submit">Query</button>
        </form>
        <div>
            <% if (request.getAttribute("totalFarmWork") != null) { %>
                <h3>Total Workload for <%= request.getParameter("queryDate") %>: <%= request.getAttribute("totalFarmWork") %> </h3>
            <% } %>
        </div>
        <c:if test="${empty dailyFarmWorks}">
        	<p> 데이터 없음 </p>
		</c:if>
        
        <c:if test="${not empty dailyFarmWorks}">
        <table class="table" border="1">
		  <thead>
		    <tr>
		      <th>작물이름</th>
		      <th>작업량</th>
		      <th>작업날짜</th>
		      <th>등록한 사람</th>
		    </tr>
		  </thead>
		  <tbody>
		    <c:forEach var="dailyFarmWork" items="${dailyFarmWorks}">
		      <tr>
		        <td>${dailyFarmWork.cropName}</td>
		        <td>${dailyFarmWork.workload}</td>
		        <td>${dailyFarmWork.date}</td>
		      </tr>
		    </c:forEach>
		  </tbody>
		</table>
		</c:if>
    </div>
</body>
</html>