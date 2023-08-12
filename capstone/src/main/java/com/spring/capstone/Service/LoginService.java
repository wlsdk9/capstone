package com.spring.capstone.Service;

import com.spring.capstone.DAO.LoginDAO;
import com.spring.capstone.DTO.LoginDTO;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private LoginDAO loginDAO;

    // 모든 필드가 입력되었는지 확인
    private boolean isAllFieldsFilled(LoginDTO loginDTO) {
        return !loginDTO.getUserId().isEmpty() && !loginDTO.getUserPw().isEmpty()
                && !loginDTO.getConfirmPassword().isEmpty() && !loginDTO.getEmail().isEmpty();
    }

    // LoginID를 기반으로 사용자 로그인
    public LoginDTO loginUser(String LoginId) {
        return loginDAO.getUserByLoginId(LoginId);
    }

    public boolean registerUser(String userId, String userPw, String confirmPassword, String name, String email,
            LocalDateTime createdAt) {
        // 아이디 중복 확인
        if (loginDAO.getUserByLoginId(userId) != null) {
            return false; // 이미 사용 중인 아이디
        }

        // 비밀번호와 비밀번호 확인 일치 확인
        if (!userPw.equals(confirmPassword)) {
            return false; // 비밀번호와 비밀번호 확인이 일치하지 않음
        }

        // 회원 정보 등록
        return loginDAO.registerUser(userId, userPw, name, email, createdAt);
    }
}
