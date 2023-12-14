package org.zerock.myapp.domain;


import lombok.Data;
import lombok.Value;

import java.util.Date;


@Value
public class AdminsVO {
    private Long num;
    private String id;
    private String password;
    private String nickName;
    private Date createDate; // format
    private Date alterDate;
    private Role role = Role.ROLE_ADMIN;
    private Integer enabled = 1;

} // end class
