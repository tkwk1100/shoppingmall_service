package com.shopping.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {
    @GetMapping("/login")
    public String getLogin(){
        return "/member/login";
    }
    @GetMapping("/join")
    public String getjoin(){
        return "/member/join";
    }
    @GetMapping("/member/logout")
    public String getLogout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
