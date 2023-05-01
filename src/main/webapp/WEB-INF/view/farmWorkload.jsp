<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
 <div class="container">
        
        <h2>Total Workload</h2>
        <form method="get" action="/farm/work">
            <div class="form-group">
                <label for="queryDate">Query Date:</label>
                <input type="date"  id="queryDate" name="queryDate">
            </div>
            <button type="submit" >Query</button>
        </form>
        <div>
            <% if (request.getAttribute("workload") != null) { %>
                <h3>Total Workload for <%= request.getParameter("queryDate") %>: <%= request.getAttribute("workload") %> </h3>
            <% } %>
        </div>
    </div>
</body>
</html>