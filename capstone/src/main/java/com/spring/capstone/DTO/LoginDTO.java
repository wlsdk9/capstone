package com.spring.capstone.DTO;

public class LoginDTO {
    private int userNo;  // 회원 고유 번호
    private String userId;  // 아이디
    private String userPw;  // 비밀번호
    private String name;  // 회원 이름
    private String email;  // 이메일
    private String confirmPassword;  // 비밀번호 확인

    public LoginDTO() {
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPw() {
        return userPw;
    }

    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}

