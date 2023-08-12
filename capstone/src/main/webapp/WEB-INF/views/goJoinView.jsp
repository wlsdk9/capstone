<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입</title>

    <style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f2f2f2;
        margin: 0;
        padding: 0;
    }

    h1 {
        text-align: center;
        color: #333;
    }
    h2 {
        text-align: center;
        color: #333;
    }

    form {
        width: 300px;
        margin: 20px auto;
        background-color: #fff;
        padding: 20px;
        border-radius: 5px;
        box-shadow: 0 2px 5px rgba(0, 0, 0, 0.3);
    }

    label {
        display: block;
        margin-bottom: 5px;
        color: #555;
    }

    input[type="text"],
    input[type="password"],
    input[type="email"] {
        width: 100%;
        padding: 10px;
        border-radius: 5px;
        border: 1px solid #ccc;
        margin-bottom: 10px;
        box-sizing: border-box; /* Include padding and border in the width calculation */
    }

    input[type="submit"] {
        width: 100%;
        padding: 10px;
        background-color: #4CAF50;
        color: #fff;
        border: none;
        border-radius: 5px;
        cursor: pointer;
    }

    input[type="submit"]:hover {
        background-color: #45a049;
    }

    a {
        display: block;
        text-align: center;
        margin-top: 10px;
        color: #333;
    }
</style>


    <script>
        function showErrorPopup(errorMessage) {
            alert(errorMessage);
        }

        function validateForm() {
            var userId = document.getElementById("userId").value;
            var userPw = document.getElementById("userPw").value;
            var confirmPassword = document.getElementById("confirmPassword").value;

            if (userId === "" || userPw === "" || confirmPassword === "") {
                showErrorPopup("모든 필드를 입력해주세요.");
                return false; // 폼 제출 중지
            }

            if (userPw !== confirmPassword) {
                showErrorPopup("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
                return false; // 폼 제출 중지
            }

            return true; // 폼 제출
        }
    </script>
</head>
<body>
    <form action="/capstone/view/goJoinView" method="post" onsubmit="return validateForm()">
    <h1>대리의민족</h1>
    <h2>회원가입</h2>
        <label for="userId">아이디:</label>
        <input type="text" id="userId" name="userId" required>
        <br>
        <label for="userPw">비밀번호:</label>
        <input type="password" id="userPw" name="userPw" required>
        <br>
        <label for="confirmPassword">비밀번호 확인:</label>
        <input type="password" id="confirmPassword" name="confirmPassword" required>
        <br>
        <label for="name">이름:</label>
        <input type="text" id="name" name="name" required>
        <br>
        <label for="email">이메일:</label>
        <input type="email" id="email" name="email" required>
        <br>
        <input type="submit" value="가입하기">
        <a href="/capstone/view/goLoginView">로그인</a>
    </form>

    

    <%-- 서버 측에서 회원가입 처리 --%>
    <%@ page import="com.spring.capstone.DAO.LoginDAO"%>
    <%@ page import="com.spring.capstone.DTO.LoginDTO"%>
    <%
        String userIdParam = request.getParameter("userId");
        String userPwParam = request.getParameter("userPw");
        String nameParam = request.getParameter("name");
        String emailParam = request.getParameter("email");

        if (userIdParam != null && userPwParam != null && nameParam != null && emailParam != null) {
            LoginDAO loginDAO = new LoginDAO();
            boolean success = loginDAO.registerUser(userIdParam, userPwParam, nameParam, emailParam, java.time.LocalDateTime.now());

            if (success) {
                // 회원 가입 성공 처리
                response.sendRedirect("/capstone/view/goLoginView");
                return;
            } else {
                // 회원 가입 실패 처리
                out.println("<script>alert('회원 가입에 실패했습니다.');</script>");
            }
        }
    %>
</body>
</html>
