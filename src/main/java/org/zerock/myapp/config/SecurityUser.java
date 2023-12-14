package org.zerock.myapp.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.zerock.myapp.domain.Users;

@Log4j2
public class SecurityUser extends User {

    public  SecurityUser(Users users){
        super(
                users.getUserId(),
                users.getPassword(),
                AuthorityUtils.createAuthorityList(users.getRole().name())
        );

        log.trace("SecurityUser({}) Invoked.", users);

    } // Constructor

} // end class
