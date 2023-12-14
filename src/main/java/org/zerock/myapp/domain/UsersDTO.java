package org.zerock.myapp.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Log4j2
@NoArgsConstructor


@Data
public class UsersDTO {
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
