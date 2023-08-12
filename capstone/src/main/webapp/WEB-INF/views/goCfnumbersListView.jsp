<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<link rel="stylesheet" href="../resources/css/board.css">
<html>
<style>
button {
	border: none;
	outline: none;
	background-color: white;
}
</style>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>CF Numbers List</title>
</head>
<body>
	<div class="page-title">
		<div class="container">
			<h3>대리번호</h3>
		</div>
	</div>
	<div class="container">
		<form>
			<label for="region">지역:</label> <select id="cf_area" name="cf_area">
				<option value="">전체</option>
				<option value="충북청주시">충북 청주시</option>
				<option value="충북충주시">충북 충주시</option>
				<option value="충북제천시">충북 제천시</option>
				<option value="충북단양군">충북 단양군</option>
				<option value="충북보은군">충북 보은군</option>
				<option value="충북영동군">충북 영동군</option>
				<option value="충북옥천군">충북 옥천군</option>
				<option value="충북음성군">충북 음성군</option>
				<option value="충북증평군">충북 증평군</option>
				<option value="충북진천군">충북 진천군</option>
			</select>
		</form>
	</div>
	<!-- board list area -->
	<div id="board-list">
		<div class="container">
			<table class="board-table">
				<thead>
					<tr>
						<th scope="col" class="th-num">번호</th>
						<th scope="col" class="th-title">대리이름</th>
						<th scope="col" class="th-date">전화번호</th>
						<th scope="col" class="th-call">전화</th>
						<th scope="col" class="th-call">리뷰작성</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${cfNumbersList}" var="cfNumber">
						<tr data-region="${cfNumber.cfArea}">
							<td>${cfNumber.cfId}</td>
							<td>${cfNumber.cfName}</td>
							<td>${cfNumber.cfNumber}</td>
							<td>
								<button onclick="location.href='tel:${cfNumber.cfNumber}'">
									<img src="../resources/image/CallBT2.png" alt="전화연결 이미지">
								</button>
							</td>
							<td>
								<button
									onclick="location.href='/capstone/view/goReviewWriteView?cfName=${cfNumber.cfName}&cfNumber=${cfNumber.cfNumber}'">
									<img src="../resources/image/ReviewBT.png" alt="리뷰 이미지">
								</button>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

	<script>
		// 지역 선택 이벤트 핸들러
		var regionSelect = document.getElementById("cf_area");
		regionSelect.addEventListener("change", filterByRegion);

		// 지역에 따라 행 필터링
		function filterByRegion() {
			var selectedRegion = regionSelect.value;
			var rows = document
					.querySelectorAll("#board-list table.board-table tbody tr");

			for (var i = 0; i < rows.length; i++) {
				var row = rows[i];
				var region = row.getAttribute("data-region");

				if (selectedRegion === "" || selectedRegion === region) {
					row.style.display = "table-row";
				} else {
					row.style.display = "none";
				}
			}
		}
	</script>
</body>
</html>
