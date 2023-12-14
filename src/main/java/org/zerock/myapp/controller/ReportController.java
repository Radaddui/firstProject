package org.zerock.myapp.controller;


import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.zerock.myapp.domain.Report;
import org.zerock.myapp.domain.ReportDTO;
import org.zerock.myapp.domain.SearchCommentDTO;
import org.zerock.myapp.domain.SearchResultDTO;
import org.zerock.myapp.service.ReportService;
import org.zerock.myapp.service.ReportServiceImpl;

import java.security.Principal;
import java.util.List;
import java.util.Optional;


@Log4j2
@NoArgsConstructor


@RequestMapping("/help/*")
@Controller
public class ReportController {

    @Setter(onMethod_ = @Autowired)
    private ReportService reportService;



    @GetMapping("/help")
    void goHelp(){
        log.trace("goHelp() invoked.");

    } // goHelp

    // 신고목록
    @GetMapping("/report/report")
    public String listReports(Model model) {
        List<Report> reports = reportService.getFindAllByOrderByCreateDateDesc();

        model.addAttribute("reports",reports);

        return "/help/report/report";
    } // listReports


    //레시피 신고 작성 페이지로 이동
    @GetMapping("/report/register/recipe")
    public String registerReportForRecipe(Model model) {
        model.addAttribute("reportDTO", new ReportDTO());

        return "help/report/register/recipe"; // 레시피 신고 작성 페이지
    } // registerReportForRecipe


    // 레시피 신고 생성 처리
    @PostMapping("/report/register/createReportRecipe")
    public String createReportForRecipe(@ModelAttribute("reportDTO") ReportDTO reportDTO, Principal principal) {

        if(principal != null) {
            String loggedInUserId = principal.getName();
            reportService.reportWrite(reportDTO, loggedInUserId);
        } // if


        return "redirect:/help/report/report";
    } // createReportForRecipe


    // 레시피 검색 엔드포인트
    @GetMapping("search/recipes")
    @ResponseBody
    public ResponseEntity<?> searchRecipes(@RequestParam String keyword) {
        List<SearchResultDTO> searchResults = reportService.searchRecipeByKeyword(keyword);

        return ResponseEntity.ok(searchResults);
    } // searchRecipes



    //댓글 신고 작성 페이지로 이동
    @GetMapping("report/register/comment")
    public String registerReportForComment(Model model) {
        model.addAttribute("reportDTO", new ReportDTO());

        return "help/report/register/comment";
    } // registerReportForComment

    //댓글 신고 생성 처리
    @PostMapping("report/register/createReportComment")
    public String createReportForComment(@ModelAttribute("reportDTO") ReportDTO reportDTO, Principal principal) {

        if(principal != null) {
            String loggedInUserId = principal.getName();
            reportService.reportWrite(reportDTO, loggedInUserId);
        } // if



        return "redirect:/help/report/report";
    } // createReportForComment


    //댓글 검색 엔드포인트
    @GetMapping("/search/comments")
    @ResponseBody
    public ResponseEntity<List<SearchCommentDTO>> searchComments(@RequestParam String query) {
        List<SearchCommentDTO> searchResults = reportService.searchCommentsByKeyword(query);

        return ResponseEntity.ok(searchResults);
    } // searchComments


    //신고글 상세보기
    @GetMapping("/report/detail/{num}")
    public String reportDetail(@PathVariable Long num, Model model) {
        Report report = this.reportService.getReportByNum(num);
        model.addAttribute("report", report);

        return "help/report/detail";
    } // reportDetail




} // end class
