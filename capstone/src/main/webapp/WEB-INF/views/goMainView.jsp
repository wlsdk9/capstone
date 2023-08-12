<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>대리의 민족</title>
<link href="../resources/css/board.css" rel="stylesheet" />

<style>
button {
	border: none;
	outline: none;
	background-color: white;
}

.button-container {
	display: flex;
	justify-content: center;
	margin-top: 200px; /* 대리의 민족과 버튼 간격 설정 */
	flex-wrap: wrap;
	gap: 20px; /* 버튼 사이의 간격 설정 */
}

.button-container button {
	margin: 10px;
}

.content {
	text-align: center;
	margin-top: 50px; /* 대리의 민족과 버튼 간격 설정 */
}

/* 모바일 화면에서 좌우 여백 설정 */
@media (max-width: 3000px) {
	.button-container {
		margin-left: 20px;
		margin-right: 20px;
	}
}
</style>

</head>
<body>
	<div class="additional-section">
		<p class="additional-text2">
			<span class="icon-wrapper"> <img
				src="../resources/image/menu.png" alt="이미지" onclick="logout()">
			</span>
		</p>
	</div>

	<script>
		function logout() {
			// AJAX 또는 form submit을 사용하여 로그아웃 요청을 처리할 수 있습니다.
			// 여기서는 페이지 이동을 통해 로그아웃을 처리하는 예시를 보여드리겠습니다.
			window.location.href = "/capstone/view/logout";
		}
	</script>


	<h1 class="content">대리의 민족</h1>
	<div class="button-container">
		<button onclick="location.href='/capstone/view/goCfnumbersListView'">
			<img src="../resources/image/CallListBT.png" alt="이미지">
		</button>
		<button onclick="location.href='/capstone/view/goReviewListView'">
			<img src="../resources/image/ReviewListBT.png" alt="이미지">
		</button>
	</div>

	<div class="additional-section">
		<p class="additional-text3">광고</p>
	</div>

</body>
</html>
	