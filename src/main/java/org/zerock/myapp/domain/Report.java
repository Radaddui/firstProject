package org.zerock.myapp.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Log4j2
@Data
@ToString(exclude = {"fkUsers"})
//"fkComments"
//"fkRecipe"
@Entity
@Table(name = "report")
public class Report {

    @Id
    @Column(name = "reportNum")
    @GeneratedValue(generator = "reportNum")
    @SequenceGenerator(name = "reportNum", initialValue = 1, allocationSize = 1)
    private Long num;


    @Transient
    private Long writer;

//    @Transient
//    private Long comments;
//
//    @Transient
//    private Long recipe;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ReportCategory  reportCategory;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ReportStatus reportStatus = ReportStatus.REPORT_RECEIVED;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Date createDate;

    @Column(nullable = false)
    @UpdateTimestamp
    private Date report_process;

    @Column(name = "REPORT_NUMBER")
    private Integer reportNumber;




    @ManyToOne(
            targetEntity = Users.class,
            fetch = FetchType.EAGER,
            optional = false
    )
    @JoinColumn(
            name = "usersNum",
            nullable = false,
            referencedColumnName = "usersNum"
    )
    private Users fkUsers;

    public void setUsers(Users fkUsers){
        log.trace("Report_setUsers({}) Invoked.", fkUsers);
        this.fkUsers = fkUsers;

        this.fkUsers.getReportListUsers().add(this);
    } // setUsers




//    @ManyToOne(
//            targetEntity = Recipe.class,
//            fetch = FetchType.EAGER,
//            optional = false
//    )
//    @JoinColumn(
//            name = "recipeNum",
//            nullable = true,
//            referencedColumnName = "recipeNum"
//    )
//    private Recipe fkRecipe;
//
//    public void setRecipe(Recipe fkRecipe){
//        log.trace("Report_setRecipe({}) Invoked.", fkRecipe);
//        this.fkRecipe = fkRecipe;
//
//        this.fkRecipe.getReportListRecipe().add(this);
//    } // setRecipe
//
//    @ManyToOne(
//            targetEntity = Comments.class,
//            fetch = FetchType.EAGER,
//            optional = false
//    )
//    @JoinColumn(
//            name = "commentsNum",
//            nullable = true,
//            referencedColumnName = "commentsNum"
//    )
//    private Comments fkComments;
//
//    public void setComments(Comments fkComments){
//        log.trace("Comment_setRecipe({}) Invoked.", fkComments);
//        this.fkComments = fkComments;
//
//        this.fkComments.getReportListComments().add(this);
//    } // setRecipe


} // end class
