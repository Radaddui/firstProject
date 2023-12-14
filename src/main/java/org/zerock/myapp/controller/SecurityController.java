package org.zerock.myapp.controller;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Log4j2
@NoArgsConstructor

@Controller
public class SecurityController {


    @GetMapping("/accessDenied")
    void accessDenied(){
        log.trace("accessDenied() Invoked.");
    }  // loginSuccess


} // end class
