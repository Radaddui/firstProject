package org.zerock.myapp.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Log4j2
@ToString(exclude = {"fkUsers","fkRecipe"})
//"ReportListComments"

@Entity
@Table(name = "comments")
public class Comments {
    @Id
    @Column(name = "commentsNum")
    @GeneratedValue(generator = "commentsNum")
    @SequenceGenerator(name = "commentsNum", initialValue = 1, allocationSize = 1)
    private Long num;

    @Transient
    private Long writer;

    @Transient
    private Long recipe;

    @Column(nullable = true)
    private String title;

    @Column(nullable = true)
    private String content;

    @Column(nullable = true)
    private String comments;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Date createDate;

    @Enumerated(EnumType.STRING)
    private ReportStatus reportStatus = ReportStatus.REPORT_NOTRECEIVED;

    @Column(nullable = false)
    @UpdateTimestamp
    private Date reportUploaded;

    @UpdateTimestamp
    private Date reportProcessed;

    @Column(nullable = false)
    private Integer enabled = 1;

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
        log.trace("Comment_setUsers({}) Invoked.", fkUsers);
        this.fkUsers = fkUsers;

        this.fkUsers.getCommentsListUsers().add(this);
    } // setUsers



    @ManyToOne(
            targetEntity = Recipe.class,
            fetch = FetchType.EAGER,
            optional = false
    )
    @JoinColumn(
            name = "recipeNum",
            nullable = false,
            referencedColumnName = "recipeNum"
    )
    private Recipe fkRecipe;




    public void setRecipe(Recipe fkRecipe){
        log.trace("Comment_setRecipe({}) Invoked.", fkRecipe);
        this.fkRecipe = fkRecipe;

        this.fkRecipe.getCommentsListRecipe().add(this);
    } // setRecipe

//    @OneToMany(
//            targetEntity = Report.class,
//            fetch = FetchType.EAGER,
//            cascade = CascadeType.ALL,
//            mappedBy = "fkComments"
//    )
//    private List<Report> ReportListComments = new ArrayList<>();






} // end class
