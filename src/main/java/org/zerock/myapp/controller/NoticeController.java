package org.zerock.myapp.controller;


import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Log4j2
@NoArgsConstructor


@RequestMapping("/notice/*")
@Controller
public class NoticeController {


    @GetMapping("/allboard")
    void noticeAllBoard(){
        log.trace("noticeAllBoard() invoked.");
    
    } // noticeAllBoard

    @GetMapping("/board")
    void noticeBoard(){
        log.trace("noticeBoard() invoked.");

    } // noticeBoard

    @GetMapping("/event")
    void noticeEvent(){
        log.trace("noticeEvent() invoked.");
    } // noticeEvent


    @GetMapping("/write")
    public void write(){
        log.trace("write() Invoked.");

    } // write


    @PostMapping("/writenotice")
    public String wirteNotice(){
        log.trace("wirteNotice() Invoked.");

        return "redirect:/notice/write";
    } // writeNotice



} // end class
