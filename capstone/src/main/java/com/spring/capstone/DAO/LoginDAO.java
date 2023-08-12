package com.spring.capstone.DAO;

import com.spring.capstone.DTO.LoginDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;

@Repository
public class LoginDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/cju_capstone?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Cjucapstone23";

    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    private static final String SELECT_USER_BY_LOGIN_ID = "SELECT * FROM users WHERE user_id = ?";
    private static final String SELECT_USER_BY_ID_AND_PW = "SELECT * FROM users WHERE user_id = ? AND user_pw = ?";
    private static final String SELECT_USER_BY_USER_ID = "SELECT * FROM users WHERE user_id = ?";
    private static final String SELECT_USER_BY_EMAIL = "SELECT * FROM users WHERE email = ?";
    private static final String INSERT_USER = "INSERT INTO users (user_id, user_pw, name, email, created_at) VALUES (?, ?, ?, ?, ?)";

    public LoginDTO getUserByLoginId(String loginId) {
        LoginDTO user = null;
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            pstmt = conn.prepareStatement(SELECT_USER_BY_LOGIN_ID);
            pstmt.setString(1, loginId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                user = new LoginDTO();
                user.setUserNo(rs.getInt("user_no"));
                user.setUserId(rs.getString("user_id"));
                user.setUserPw(rs.getString("user_pw"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return user;
    }

    public boolean validateUser(String userId, String userPw) {
        boolean isValid = false;
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            pstmt = conn.prepareStatement(SELECT_USER_BY_ID_AND_PW);
            pstmt.setString(1, userId);
            pstmt.setString(2, userPw);
            rs = pstmt.executeQuery();
            isValid = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return isValid;
    }

    public boolean registerUser(String userId, String userPw, String name, String email, LocalDateTime createdAt) {
        boolean success = false;
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            pstmt = conn.prepareStatement(INSERT_USER);
            pstmt.setString(1, userId);
            pstmt.setString(2, userPw);
            pstmt.setString(3, name);
            pstmt.setString(4, email);
            pstmt.setTimestamp(5, Timestamp.valueOf(createdAt));


            int rowsAffected = pstmt.executeUpdate();
            success = rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return success;
    }

    private void closeResources() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
