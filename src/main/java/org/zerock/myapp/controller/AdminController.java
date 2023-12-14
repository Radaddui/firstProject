package org.zerock.myapp.controller;


import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.myapp.domain.Recipe;
import org.zerock.myapp.service.AdminService;

import java.util.List;


@Log4j2
@NoArgsConstructor

@RequestMapping("/admin")
@Controller
public class AdminController {

    @Setter(onMethod_ = @Autowired)
    private AdminService adminService;

    @GetMapping("/main")
    String main(){
        log.trace("main() invoked.");
        return "/admin/main";
    } // main

    @GetMapping("/userManagement")
    String userManagement(){
        log.trace("userManagement() invoked.");
        return "/admin/user/userManagement";
    } // userManagement

    @GetMapping("/QnAManagement")
    String qnAManagement(){
        log.trace("qnAManagement() invoked.");
        return "/admin/qna/QnAManagement";
    } // qnAManagement


    @GetMapping("/ReportManagement")
    String reportManagement(){
        log.trace("reportManagement() invoked.");
        return "/admin/report/ReportManagement";
    } // reportManagement


    @GetMapping("/RecipeManagement")
//    String recipeManagement(Model model, Pageable pageable){
    String recipeManagement(Model model){
        log.trace("recipeManagement({}) invoked.", model);
        List<Recipe> recipe = this.adminService.getAllRecipesOrderedByRecipeNumberDesc();
        model.addAttribute("recipe", recipe);
        return "/admin/recipe/RecipeManagement";
    } // recipeManagement

} // end class
