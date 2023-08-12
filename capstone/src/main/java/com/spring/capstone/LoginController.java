package com.spring.capstone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.spring.capstone.DTO.LoginDTO;
import com.spring.capstone.Service.LoginService;

@Controller
public class LoginController {
    @Autowired
    private LoginService LoginService;

    // 회원가입 페이지로 이동하는 GET 요청 처리
    @GetMapping("/view/goJoinView")
    public String showRegisterForm() {
        return "goJoinView"; // 회원가입 JSP 페이지로 이동
    }
    
 // 회원가입 폼을 제출하는 POST 요청 처리
    @PostMapping("/view/goJoinView")
    public String registerUser(@RequestParam("userId") String userId,
                               @RequestParam("userPw") String userPw,
                               @RequestParam("confirmPassword") String confirmPassword,
                               @RequestParam("name") String name,
                               @RequestParam("email") String email) {
        boolean success = LoginService.registerUser(userId, userPw, confirmPassword, name, email, LocalDateTime.now());
        if (success) {
            // 회원 가입 성공 처리
            return "redirect:/view/goLoginView"; // 로그인 페이지로 리다이렉트
        } else {
            // 회원 가입 실패 처리
            return "redirect:/view/goJoinView"; // 회원 가입 실패 페이지로 이동
        }
    }

    
       // 로그인 페이지로 이동하는 GET 요청 처리
    @GetMapping("/view/goLoginView")
    public String showLoginForm(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("errorMessage", "아이디 또는 비밀번호가 올바르지 않습니다.");
        }
        return "goLoginView"; // 로그인 JSP 페이지로 이동
    }

    // 로그인을 처리하는 POST 요청 처리
    @PostMapping("/view/goLoginView")
    public String loginUser(LoginDTO loginDTO, Model model, HttpServletRequest request) {
        LoginDTO user = LoginService.loginUser(loginDTO.getUserId());
        if (user == null || !user.getUserPw().equals(loginDTO.getUserPw())) {
            model.addAttribute("errorMessage", "아이디 또는 비밀번호가 올바르지 않습니다.");
            return "goLoginView"; // 로그인 실패 시 로그인 페이지로 이동하고 에러 메시지 표시
        }
        
        // 로그인 성공 처리
        HttpSession sessionObj = request.getSession();
        sessionObj.setAttribute("userId", loginDTO.getUserId());
        
        return "redirect:/view/goMainView"; // 로그인 성공 시 메인 페이지로 이동
    }

    // 메인 페이지로 이동하는 GET 요청 처리
    @GetMapping("/view/goMainView")
    public String showMainPage(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        if (!isUserLoggedIn(session)) {
            return "redirect:/view/goLoginView"; // 로그인되지 않은 경우 로그인 페이지로 이동
        }
        
        String userId = (String) session.getAttribute("userId");
        // 메인 페이지에 필요한 작업 수행
        // ...
        
        return "goMainView"; // 메인 페이지로 이동
    }

    // 세션에서 로그인 상태를 확인하는 메서드
    private boolean isUserLoggedIn(HttpSession session) {
        return session.getAttribute("userId") != null;
    }

    // 로그아웃을 처리하는 요청 처리
    @GetMapping("/view/logout")
    public String logoutUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate(); // 세션 무효화
        
        return "redirect:/view/goLoginView"; // 로그아웃 후 로그인 페이지로 이동
    }
    
    

}
