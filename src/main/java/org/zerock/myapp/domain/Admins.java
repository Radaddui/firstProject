package org.zerock.myapp.domain;


import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data

@ToString(exclude = "noticeListAdmins")

@Table(name = "admins")
@Entity
public class Admins {

    @Id
    @Column(name = "adminNum")
    @GeneratedValue(generator = "adminNum")
    @SequenceGenerator(name = "adminNum", initialValue = 1, allocationSize = 1)
    private Long num;

    @Column(nullable = false, unique = true)
    private String id;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true, name = "ukNickname")
    private String nickName;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Date createDate; // format

    @Column(nullable = false)
    @UpdateTimestamp
    private Date alterDate;

    @Enumerated(EnumType.STRING)
    private Role role = Role.ROLE_ADMIN;

    private Integer enabled = 1;


    @OneToMany(
            targetEntity = Notice.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "fkAdmins"
    )
    private List<Notice> noticeListAdmins = new ArrayList<>();



} // end class
