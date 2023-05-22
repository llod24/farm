<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<title>Workload Chart</title>
<link rel="stylesheet" href="	https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/4.0.1/chart.umd.js"></script>
</head>
<body>
	<form method="get" action="/farm/work/chart">
		<label>작물 이름:</label>
		<select name="cropName">
			<option value="적겨자">적겨자</option>
			<option value="로메인">로메인</option>
			<option value="치콘">치콘</option>
		</select>
		<label for="queryDate">년도, 월을 선택하세요:</label>
        <input type="month" id="queryMonth" name="queryMonth" value="${queryMonth}"/>
    	<button type="submit">조회</button>
	</form>
    <canvas id="workloadChart"></canvas>

    <script>
    	// Chart.js를 사용하여 그래프 생성
        var ctx = document.getElementById('workloadChart').getContext('2d');
        var chart = new Chart(ctx, {
            type: 'line',
            data: {
                labels: ${labels}, // 라벨 데이터
                datasets: [{
                    label: 'Workload',
                    data: ${workloadData}, // 작업량 데이터
                    backgroundColor: 'rgba(0, 123, 255, 0.5)', // 그래프 채우기 색상
                    borderColor: 'rgba(0, 123, 255, 1)', // 그래프 선 색상
                    borderWidth: 1 // 그래프 선 굵기
                }]
            },
            options: {
                responsive: true,
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            }
        });
    </script>
</body>
</html>
