<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <title>대리의민족 로그인</title>


    <style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f2f2f2;
        margin: 0;
        padding: 0;
    }

    .container {
        width: 300px;
        margin: 100px auto;
        background-color: #ffffff;
        padding: 20px;
        border-radius: 5px;
        box-shadow: 0 2px 5px rgba(0, 0, 0, 0.3);
        display: flex;
        flex-direction: column;
        align-items: center;
    }

    h1 {
        margin-bottom: 10px;
    }

    h2 {
        margin-top: 0;
    }

    form {
        width: 100%;
        margin-top: 20px;
    }

    label {
        display: block;
        margin-bottom: 5px;
        color: #555;
    }

    input[type="text"],
    input[type="password"] {
        width: 100%;
        padding: 10px;
        border-radius: 5px;
        border: 1px solid #ccc;
        margin-bottom: 10px;
        box-sizing: border-box;
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

    .signup-link {
        text-align: center;
        margin-top: 20px;
    }

    .error-message {
        color: #ff0000;
        text-align: center;
        margin-top: 10px;
    }
</style>



    <script>
        function showErrorPopup(errorMessage) {
            alert(errorMessage);
        }

        function validateForm() {
            var userId = document.getElementById("userId").value;
            var userPw = document.getElementById("userPw").value;

            if (userId === "" || userPw === "") {
                showErrorPopup("아이디와 비밀번호를 모두 입력해주세요.");
                return false; // 폼 제출 중지
            }

            return true; // 폼 제출
        }
    </script>
</head>
<body>
    <div class="container">
        <h1>대리의민족</h1>
        <h2>로그인</h2>
        <form action="/capstone/view/goLoginView" method="post" onsubmit="return validateForm()">
            <label for="userId">아이디:</label>
            <input type="text" id="userId" name="userId">
            <br>
            <label for="userPw">비밀번호:</label>
            <input type="password" id="userPw" name="userPw">
            <br>
            <input type="submit" value="로그인">
        </form>

        <div class="signup-link">
            <a href="/capstone/view/goJoinView">회원가입</a>
        </div>

        <%-- 서버 측에서 로그인 처리 --%>
        <%@ page import="com.spring.capstone.DAO.LoginDAO" %>
        <%@ page import="com.spring.capstone.DTO.LoginDTO" %>
        <%
            String userIdParam = request.getParameter("userId");
            String userPwParam = request.getParameter("userPw");

            if (userIdParam != null && userPwParam != null) {
                LoginDAO loginDAO = new LoginDAO();
                boolean isValid = loginDAO.validateUser(userIdParam, userPwParam);
                if (isValid) {
                    // 로그인 성공 처리
                    // 세션 등의 로그인 정보 저장
                    HttpSession sessionObj = request.getSession();
                    sessionObj.setAttribute("userId", userIdParam);
                    response.sendRedirect("/capstone/view/goMainView");
                    return;
                } else {
                    // 로그인 실패 처리
                    // 에러 메시지 출력 등
                    out.println("<script>alert('아이디와 비밀번호가 일치하지 않습니다.');</script>");
                }
            }
        %>

        <%-- 로그인 세션 유지를 위한 코드 추가 --%>
        <%@ page import="javax.servlet.http.HttpSession" %>
        <%
            HttpSession loginSession = request.getSession();
            String userId = (String) loginSession.getAttribute("userId");
            if (userId != null) {
                response.sendRedirect("/capstone/view/goMainView");
                return;
            }
        %>
    </div>
</body>
</html>
