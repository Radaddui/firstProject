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
@ToString(exclude = {"fkUsers", "fkCategories", "commentsListRecipe"}) // "ReportListRecipe"
@Log4j2


@Entity
@Table(name = "recipe")
public class Recipe {

    @Id
    @Column(name = "recipeNum")
    @GeneratedValue(generator = "recipeNum")
    @SequenceGenerator(name = "recipeNum", initialValue = 1, allocationSize = 1)
    private Long num;

    @Transient
    private Long writer;


    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    @CreationTimestamp
    private Date createDate;

    @Column(nullable = false)
    @UpdateTimestamp
    private Date updateDate;


    @Transient
    private Categories Categories;

    @UpdateTimestamp
    @Column(updatable = true, insertable = true)
    private Date processedDate;

    @Enumerated(EnumType.STRING)
    private ReportStatus reportStatus = ReportStatus.REPORT_NOTRECEIVED;

    @Column
    private Integer enabled = 1;


    private Integer cnt = 0;



//    ====================================================================


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

    public void setFkUsers(Users fkUsers){
        log.trace("Recipe_setFkUsers({}) Invoked.", fkUsers);
        this.fkUsers = fkUsers;

        this.fkUsers.getRecipeListUsers().add(this);
    }


    @ManyToOne(
            targetEntity = Categories.class,
            fetch = FetchType.EAGER,
            optional = false
    )
    @JoinColumn(
            name = "Categories_Seq",
            nullable = false,
            referencedColumnName = "Categories_Seq"
    )
    private Categories fkCategories;

    public void setFkCategories(Categories fkCategories) {
        log.trace("setFkCategories({}) Invoked.", fkCategories);
        this.fkCategories = fkCategories;
    } // setCategories

//    ====================================================================

    @OneToMany(
            targetEntity = Comments.class,
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            mappedBy = "fkRecipe"
    )
    private List<Comments> commentsListRecipe = new ArrayList<>();






//    @OneToMany(
//            targetEntity = Report.class,
//            fetch = FetchType.EAGER,
//            cascade = CascadeType.ALL,
//            mappedBy = "fkRecipe"
//    )
//    private List<Report> ReportListRecipe = new ArrayList<>();





//
//
//    @Column(nullable = false)
//    private




} // end class
