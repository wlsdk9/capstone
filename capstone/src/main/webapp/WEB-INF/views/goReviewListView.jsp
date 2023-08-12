<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Review List</title>
<style>
body {
	font-family: Arial, sans-serif;
}

h1 {
	text-align: center;
	margin-top: 20px;
}

.container {
	max-width: 800px;
	margin: 0 auto;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 20px;
}

th, td {
	padding: 8px;
	text-align: center;
	border-bottom: 1px solid #ddd;
}

tr:hover {
	background-color: #f5f5f5;
}

th {
	background-color: #f2f2f2;
	font-weight: bold;
}

tr:nth-child(even) {
	background-color: #f9f9f9;
}
</style>
</head>
<body>
	<div class="container">
		<h1>리뷰목록</h1>
		<table>
			<tr>
				<th>대리이름</th>
				<th>제목</th>
				<th>별점</th>
				<th>작성자</th>
				<th>작성시간</th>
			</tr>
			<c:forEach var="review" items="${reviews}">
				<tr
					onclick="window.location='/capstone/view/goReviewDetailView?reviewId=${review.reviewId}'"
					style="cursor: pointer;">
					<td>${review.cfName}</td>
					<td>${review.title}</td>
					<td>${review.rating}</td>
					<td>${review.userId}</td>
					<td>${review.createdTime}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>
