<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>리뷰작성</title>
    <style>
body {
    font-family: Arial, sans-serif;
}

h2 {
    text-align: center;
}

form {
    width: 400px;
    margin: 0 auto;
    border: 1px solid #ccc; /* 테두리 추가 */
    padding: 20px; /* 내부 여백 추가 */
}

label {
    text-align: center;
    display: block;
    margin-bottom: 5px;
    font-weight: bold;
}

input[type="text"] {
    width: 100%;
    padding: 5px;
    margin-bottom: 10px;
    border: none;
    border-radius: 3px;
    resize: none;
    text-align: center;
    font-size: 16px;
}

input[type="titletext"],
textarea {
    width: 100%;
    padding: 5px;
    margin-bottom: 10px;
    border-radius: 3px;
    resize: none;
    text-align: left;
    font-size: 16px;
}

input[type="content"],
textarea {
    width: 100%;
    padding: 5px;
    margin-bottom: 10px;
    border-radius: 3px;
    resize: none;
    text-align: left;
    font-size: 16px;
}

#content {
    height: 200px;
}

#star-rating {
    direction: rtl;
    unicode-bidi: bidi-override;
    text-align: center;
}

#star-rating input[type="radio"] {
    display: none;
}

#star-rating label {
    display: inline-block;
    width: 30px;
    height: 30px;
    margin: 0 2px;
    background-image: url("../resources/image/whitestar.png");
    background-size: cover;
    cursor: pointer;
}

#star-rating input[type="radio"]:checked ~ label {
    background-image: url("../resources/image/blackstar.png");
}

input[type="submit"] {
    display: block;
    width: 100%;
    padding: 10px;
    background-color: #4CAF50;
    color: #fff;
    border: none;
    border-radius: 3px;
    cursor: pointer;
}

input[type="submit"]:hover {
    background-color: #45a049;
}
</style>

    
    <script>
    function checkLogin() {
        var isLoggedIn = true; // 서버에서 받은 로그인 여부 값
        
        if (isLoggedIn) {
            // 입력 필드 값 확인
            var title = document.getElementById("title").value;
            var content = document.getElementById("content").value;
            
            // 공백 여부 확인
            if (title.trim() === "" || content.trim() === "" || rating.trim() === "") {
                alert("제목,내용과 별점을 입력해주세요.");
            } else {
                // 로그인 상태이며, 입력 필드에 공백이 없는 경우 폼을 제출
                document.getElementById("reviewForm").submit();
            }
        } else {
            // 로그인 상태가 아닐 경우 팝업 창을 띄움
            alert("회원이 아닙니다.");
        }
    }

    </script>
</head>
<body>
<h2>리뷰작성</h2>
<form id="reviewForm" action="/capstone/view/goReviewListView" method="post">
    <label for="cfName">대리이름</label><br>
    <input type="text" id="cfName" name="cfName" value="${param.cfName}" readonly><br><br>
    
    <label for="cfNumber">전화번호</label><br>
    <input type="text" id="cfNumber" name="cfNumber" value="${param.cfNumber}" readonly><br><br>
    
    <label for="rating">별점</label><br>
    <div id="star-rating">
        <input type="radio" id="star5" name="rating" value="5" required/>
        <label for="star5" title="5 stars"></label>
        
        <input type="radio" id="star4" name="rating" value="4" />
        <label for="star4" title="4 stars"></label>
        <input type="radio" id="star3" name="rating" value="3" />
        <label for="star3" title="3 stars"></label>
        
        <input type="radio" id="star2" name="rating" value="2" />
        <label for="star2" title="2 stars"></label>
        
        <input type="radio" id="star1" name="rating" value="1" />
        <label for="star1" title="1 star"></label>
    </div>
    <br><br>
    
    <label for="title">제목</label><br>
    <input type="titletext" id="title" name="title" required><br><br>
    
    <label for="content">내용</label><br>
    <textarea type="content" id="content" name="content" required></textarea><br><br>
    
    <input type="submit" value="작성" onclick="checkLogin()">
</form>
</body>
</html>
