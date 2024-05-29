package com.logintest.login_signup_test.controller;

import com.logintest.login_signup_test.dto.MemberDto;
import com.logintest.login_signup_test.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    //회원가입 페이지 출력 요청
    @GetMapping("/member/save")
    public String saveForm() {
        return "save";
    }

    @PostMapping("/member/save")
    public ResponseEntity<String> save(@RequestBody MemberDto memberDto) {
        System.out.println("MemberController.save");
        System.out.println("memberDto = " + memberDto);
        memberService.save(memberDto);

        return new ResponseEntity<String>("success", HttpStatus.OK);
    }

    @GetMapping("/member/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/member/login") // session: 로그인 유지
    public ResponseEntity<String> login(@RequestBody MemberDto memberDto, HttpSession session) {
        MemberDto loginResult = memberService.login(memberDto);
        if (loginResult != null) {
            // 로그인 성공
            System.out.println("로그인 성공");
            session.setAttribute("loginEmail", loginResult.getMemberEmail()); // 로그인 성공시 session에 longEmail이라는 변수명으로 정보를 저장
            System.out.println("Session ID: " + session.getId());
            return new ResponseEntity<String>("success", org.springframework.http.HttpStatus.OK);
        } else {
            // 로그인 실패
            System.out.println("로그인 실패");
            return new ResponseEntity<String>("fail", HttpStatus.UNAUTHORIZED);
        }
    }
    // session이란: 로그인 같은 접속 정보를 저장해야 될 때 사용하는데, 다른 페이지로 이동할 때도 로그인 상태 유지를 위해 쓰임
}
