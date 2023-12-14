package org.zerock.myapp.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.myapp.domain.UsersDTO;
import org.zerock.myapp.service.MypageService;
import org.zerock.myapp.service.UsersService;

import java.security.Principal;

@Log4j2
@NoArgsConstructor

@Controller
@RequestMapping("/mypage/*")
public class MypageController {
    @Setter(onMethod_ = @Autowired)
    private MypageService mypageService;

    @Setter(onMethod_ = @Autowired)
    private UsersService usersService;

    private String userId;

    @GetMapping({"/editProfile", "/resetPassword"})
    public String editInfo(Model model, Principal principal, HttpServletRequest request) {
        log.trace("editProfile() Invoked.");

        this.userId = principal.getName();
        log.trace("\t☆★ username ☆★ : {}", this.userId);

        UsersDTO usersDTO = usersService.getUserDTOByUserId(this.userId);
        log.trace("\t☆★ getUserByUsername 수행 후 usersDTO ☆★ : {}", usersDTO);

        model.addAttribute("users", usersDTO);

        // 매핑을 뭘로 받았는지에 따라 구분해서 처리페이지 실행
        if (request.getServletPath().equals("/mypage/editProfile")) {
            log.trace("\t☆★ editProfile !!!!!!!!!!!!! ★☆");
            return "/mypage/editProfile";
        } else if (request.getServletPath().equals("/mypage/resetPassword")) {
            log.trace("\t☆★ resetPassword !!!!!!!!!!!!! ★☆");
            return "/mypage/resetPassword";
        } else {
            log.trace("\t☆★ 에러!!!!!!!!!!!!! ★☆");
            // 다른 매핑에 대한 처리
            return "error";
        } // if-else
    } // editInfo

    @PostMapping({"/updateProfile" ,"/updatePassword"})
    public String updateinfo(@ModelAttribute("usersDTO") UsersDTO usersDTO) {
        log.trace("\t☆★ 다시 받아온 usersDTO ☆★ : {}", usersDTO);
        log.trace("\t☆★ 현재 아이디 ☆★ : {}", this.userId);

        usersDTO.setUserId(this.userId);

        mypageService.updateInfo(usersDTO);

        log.trace("업데이트완료");
        return "redirect:/mypage";
    } // updateinfo

//    @PostMapping("/updatePassword")
//    public String updatePassword(@ModelAttribute("usersDTO") UsersDTO usersDTO) {
//        log.trace("\t☆★ 다시 받아온 usersDTO ☆★ : {}", usersDTO);
//        log.trace("\t☆★ 현재 아이디 ☆★ : {}", this.userId);
//
//        usersDTO.setUserId(this.userId);
//
//        mypageService.updateInfo(usersDTO);
//
//        log.trace("업데이트완료");
//        return "redirect:/mypage";
//    } // updateProfile


} // end class
