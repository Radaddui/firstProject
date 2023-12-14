package org.zerock.myapp.service;


import org.zerock.myapp.domain.Report;
import org.zerock.myapp.domain.ReportDTO;
import org.zerock.myapp.domain.SearchCommentDTO;
import org.zerock.myapp.domain.SearchResultDTO;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ReportService {

    public abstract void addReport(Report report);

    public abstract void reportWrite(ReportDTO reportDTO, String LoggedInUserId);

    public abstract List<Report> getAllReports();

    public abstract Report getReportByNum(Long num);

    public abstract List<Report> getFindAllByOrderByCreateDateDesc();

    public abstract Integer findHighestReportNumber();


    List<SearchResultDTO> searchRecipeByKeyword(String keyword);

    List<SearchCommentDTO> searchCommentsByKeyword(String keyword);



} // end interface
