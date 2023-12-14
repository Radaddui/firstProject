package org.zerock.myapp.controller;


import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.zerock.myapp.domain.Question;
import org.zerock.myapp.domain.QuestionDTO;
import org.zerock.myapp.service.QuestionService;

import java.security.Principal;
import java.util.List;


@Log4j2
@NoArgsConstructor


@RequestMapping("/help/*")
@Controller
public class QuestionController {

    @Setter(onMethod_ = @Autowired)
    private QuestionService questionService;


    @GetMapping("/question/question")
    public String questionList(Model model) {

        List<Question> questions = questionService.getFindAllByOrderByCreateDateDesc();
        model.addAttribute("questions", questions);

        return "help/question/question";
    } // questionList


    @GetMapping("/question/register")
    public String write(Model model) {

        model.addAttribute("questionDTO", new QuestionDTO());

        return "help/question/register";
    } // write

    @PostMapping("/question/createQuestion")
    public String createQuestion(@ModelAttribute("questionDTO") QuestionDTO questionDTO, Principal principal) {

        if(principal != null) {
            String loggedInUserId = principal.getName();
            questionService.questionWrite(questionDTO, loggedInUserId);
        } // if

        return "redirect:/help/question/question";
    } // createQuestion

    @GetMapping("/question/detail/{num}")
    public String questionDetail(@PathVariable Long num, Model model) {
        Question question = this.questionService.getQuestionByNum(num);
        model.addAttribute("question", question);

        return "help/question/detail";
    } // questionDetail



} // end class
