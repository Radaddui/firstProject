package org.zerock.myapp.controller;


import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Log4j2
@NoArgsConstructor


@RequestMapping("/login")
@Controller
public class LoginController {


    @GetMapping("/login")
    public String login(){
        log.trace("login() invoked.");

        return "login/login";
    } // goLogin

    @PostMapping("/loginSuccess")
    String loginInfo(){
        log.trace("loginSuccess() Invoked.");

        return "main";
    }

    @GetMapping("/admin")
    public String adminLogin(){
        log.trace("adminLogin() invoked.");

        return "login/admin";
    } // adminLogin

    @PostMapping("/admin")
    public String adminLoginSuccess(){
        log.trace("adminLoginSuccess() invoked.");

        return "admin/main";
    }

} // end class
