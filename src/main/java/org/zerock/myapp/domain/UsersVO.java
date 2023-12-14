package org.zerock.myapp.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDate;
import java.util.Date;


@Log4j2

@Value
public class UsersVO {
    private Long num;
    private String userId;
    private String password;
    private String nickName;
    private Date createDate;
    private Date alterDate;
    private Role role = Role.ROLE_USER;
    private Integer enabled = 1;
    private String email;
    private LocalDate birthdate;

} // end class
