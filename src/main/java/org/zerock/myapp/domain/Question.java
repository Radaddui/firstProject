package org.zerock.myapp.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Log4j2
@Data
@ToString(exclude = {"fkUsers"})

@Entity
@Table(name = "Question")
public class Question {

    @Id
    @Column(name = "questionNum")
    @GeneratedValue(generator = "questionNum")
    @SequenceGenerator(name = "questionNum", initialValue = 1, allocationSize = 1)
    private Long num;

    @Column(nullable = false)
    private String title;

    @Transient
    private Long writer;


    @Column(nullable = false)
    private String content;


    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Date createDate;

    @Column(name = "QUESTION_NUMBER")
    private Integer questionNumber;



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

        this.fkUsers.getQuestionListUsers().add(this);
    } // setUsers




} // end class
