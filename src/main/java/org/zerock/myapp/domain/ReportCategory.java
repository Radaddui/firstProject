package org.zerock.myapp.domain;


public enum ReportCategory {

    SPAM("스팸"),
    INAPPROPRIATE_CONTENT("부적절한 내용"),
    COPYRIGHT_INFRINGEMENT("저작권 침해"),
    OTHER("기타");

    private final String description;

    ReportCategory(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

} // end enum
