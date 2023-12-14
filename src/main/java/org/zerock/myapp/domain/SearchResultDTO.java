package org.zerock.myapp.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor

@Data
public class SearchResultDTO {
    private Long num;
    private String title;
    private String content;
    private String type;

} // end class
