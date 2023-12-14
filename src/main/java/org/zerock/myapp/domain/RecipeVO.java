package org.zerock.myapp.domain;

import lombok.Value;

import java.util.Date;


@Value
public class RecipeVO {
    private Long num;
    private Long writer;
    private String title;
    private String content;
    private Date createDate;
    private Date alterDate;
    private Long Categories;
    private Date processedDate;
    private ReportStatus reportStatus = ReportStatus.REPORT_NOTRECEIVED;
    private Integer enabled;
    private Integer cnt;


} // end class
