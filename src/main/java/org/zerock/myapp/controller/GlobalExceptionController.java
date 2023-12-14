package org.zerock.myapp.controller;


import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.myapp.exception.UserNotLoggedInException;

@Log4j2
@NoArgsConstructor

@ControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(UserNotLoggedInException.class)
    public String handleUserNotLoggedInException(UserNotLoggedInException e, RedirectAttributes attributes) {

        attributes.addFlashAttribute("errorMessage", e.getMessage());

        return "redirect:/login/login";
    } // handleUserNotLoggedInException

} // end class
