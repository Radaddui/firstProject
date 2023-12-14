package org.zerock.myapp.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Log4j2
@ToString(exclude = {"recipeListUsers", "commentsListUsers", "ReportListUsers"})


@Entity
@Table(name = "users")
public class Users {

    @Id
    @Column(name = "usersNum")
    @SequenceGenerator(name = "usersNum", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "usersNum")
    private Long num;

    @Column(name = "ukId", nullable = false, unique = true)
    private String userId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true, name = "ukNickname")
    private String nickName;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Date createDate;

    @Column(nullable = false)
    @UpdateTimestamp
    private Date alterDate;

    @Enumerated(EnumType.STRING)
    private Role role = Role.ROLE_USER;

    @Column(nullable = false)
    private Integer enabled = 1;

    @Column(nullable = false, unique = true, name = "ukEmail")
    private String email;

    @Column(nullable = false)
    private LocalDate birthdate;


    @OneToMany(
            targetEntity = Recipe.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "fkUsers"
    )
    private List<Recipe> recipeListUsers = new ArrayList<>();


    @OneToMany(
            targetEntity = Comments.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "fkUsers"
    )
    private List<Comments> commentsListUsers = new ArrayList<>();


    @OneToMany(
            targetEntity = Report.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "fkUsers"
    )
    private List<Report> ReportListUsers = new ArrayList<>();


    @OneToMany(
            targetEntity = Question.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "fkUsers"
    )
    private List<Question> QuestionListUsers = new ArrayList<>();



} // end class
