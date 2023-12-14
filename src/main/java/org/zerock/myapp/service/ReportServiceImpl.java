package org.zerock.myapp.service;


import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.*;
import org.zerock.myapp.repository.ReportRepository;

import java.util.*;
import java.util.stream.Collectors;


@Log4j2
@NoArgsConstructor

@Service
public class ReportServiceImpl implements ReportService{

    @Setter(onMethod_ = @Autowired)
    private ReportRepository reportRepo;

    @Setter(onMethod_ = @Autowired)
    private UsersService usersService;

    @Setter(onMethod_ = @Autowired)
    private RecipeService recipeService;

    @Setter(onMethod_ = @Autowired)
    private CommentsService commentsService;


    @Override
    public void addReport(Report report) {
        Integer highestReportNumber = reportRepo.findHighestReportNumber();

        if(highestReportNumber == null) {
            highestReportNumber = 0;
        } // if

        // 다음 reportNumber 값을 설정
        report.setReportNumber(highestReportNumber + 1);

        //게시글 저장
        reportRepo.save(report);

    } // addReport

    @Override
    public void reportWrite(ReportDTO reportDTO, String LoggedInUserId) {
        Users loggedInUserId = usersService.getUserById(LoggedInUserId);

        if(loggedInUserId == null) {
            ;;
            return;
        } // if

        Report report = new Report();
        report.setContent(reportDTO.getContent());

        //로그인 된 유저 id값을 불러옴
        report.setFkUsers(loggedInUserId);

        //DTO에서 enum 값을 설정
        report.setReportCategory(ReportCategory.valueOf(reportDTO.getReportCategory().name()));

        //DTO에서 enum 값을 저장
        report.setReportStatus(ReportStatus.REPORT_RECEIVED);


        reportRepo.save(report);

    } // reportWrite

    @Override
    public List<Report> getAllReports() {

        return reportRepo.findAll();
    } // getAllReports

    @Override
    public Report getReportByNum(Long num) {

        Optional<Report> result = this.reportRepo.findById(num);

        return result.orElse(null);
    }

    @Override
    public List<Report> getFindAllByOrderByCreateDateDesc() {
        return reportRepo.findAllByOrderByCreateDateDesc();
    } // getFindAllByOrderByCreateDateDesc

    @Override
    public Integer findHighestReportNumber() {
        return reportRepo.findHighestReportNumber();
    } // findHighestReportNumber

//    @Override
//    public void addReportWithCategoryAndReference(ReportDTO reportDTO, String LoggedInUserId, Long recipeNum, Long commentsNum) {
//        // 로그인 된 사용자 정보를 가져옴
//        Users user = usersService.getUserById(LoggedInUserId);
//
//        if(user == null) {
//            // 사용자 정보가 없다면, 예외 처리나 오류 메시지 반환
//            log.error("addReportWithCategoryAndReference - No user found with ID: {}", LoggedInUserId);
//            throw new IllegalStateException("Logged in user not found");
//        } // if
//
//        //Report 객체 생성
//        Report report = new Report();
//        report.setContent(reportDTO.getContent()); // 신고 내용 설정
//        report.setFkUsers(user); // 신고하는 사용자 설정
//        report.setReportStatus(reportDTO.getReportStatus()); // 신고 상태 설정
//        report.setReportCategory(reportDTO.getReportCategory()); // 신고 카테고리 설정
//
//        //레시피 번호가 제공되면, 레시피와 연관지음
//        if(recipeNum != null) {
//            // 레시피 서비스를 사용하여 레시피 정보를 가져옴
//            Recipe recipe = recipeService.getRecipeByNum(recipeNum);
//            if(recipe != null) {
//                report.setFkRecipe(recipe);
//            } // inner if
//
//        } // if
//
//        //댓글 번호가 제공되면, 댓글과 연관지음
//        if(commentsNum != null) {
//            Comments comments = (Comments) commentsService.getCommentById(commentsNum);
//            if(comments != null) {
//                report.setFkComments(comments);
//            } // inner if
//
//        } // if
//
//        reportRepo.save(report);
//
//    } // addReportWithCategoryAndReference

    @Override
    public List<SearchResultDTO> searchRecipeByKeyword(String keyword) {
        List<Recipe> recipes = recipeService.searchRecipesByKeyword(keyword);

        return recipes.stream().
                map(recipe -> new SearchResultDTO(
                        recipe.getNum(),
                        recipe.getTitle(),
                        recipe.getContent(),
                        "recipe"
                )).collect(Collectors.toList());

    } // searchRecipeByKeyword

    @Override
    public List<SearchCommentDTO> searchCommentsByKeyword(String keyword) {
        List<Comments> comments = commentsService.searchCommentsByKeyword(keyword);

        return comments.stream().
                map(comment -> new SearchCommentDTO(
                        comment.getNum(),
                        comment.getFkUsers().getNickName(),
                        comment.getComments(),
                        "comment"
                )).collect(Collectors.toList());

    } // searchCommentsByKeyword




} // end class
