package org.zerock.myapp.domain;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor

@Data
public class ReportDTO {

    private Long num; // Report의 기본 키
    private String content; // 신고 내용
    private ReportCategory reportCategory; // 신고 유형
    private ReportStatus reportStatus; // 신고 상태
    private Date createDate; // 신고한 시간
    private Date reportProcess; // 신고 처리 시간
    private Integer reportNumber; // 신고글 넘버
    
    
    // 관계를 맺고 있는 엔티티의 id 값
    private Long usersNum; // 신고한 사용자의 ID
    private Long recipeNum; // 신고된 레시피의 ID
    private Long commentsNum; // 신고된 코멘트의 ID

} // end class
