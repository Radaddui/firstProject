package org.zerock.myapp.domain;


import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.util.Date;

@Data
@Validated
public class QuestionDTO {

    private Long num;
    private String title;
    private Long writer;
    private String content;
    private Date createDate;
    private Integer questionNumber;
} // end class
