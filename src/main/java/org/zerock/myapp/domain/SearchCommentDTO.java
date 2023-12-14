package org.zerock.myapp.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor

@Data
public class SearchCommentDTO {
    private Long num;
    private String title;
    private String comments;
    private String type;

} // SearchCommentDTO
