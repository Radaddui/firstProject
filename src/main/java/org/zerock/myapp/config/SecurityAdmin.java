package org.zerock.myapp.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.zerock.myapp.domain.Admins;
import org.zerock.myapp.domain.Users;

@Log4j2
public class SecurityAdmin extends User {

    public SecurityAdmin(Admins admins){
        super(
                admins.getId(),
                admins.getPassword(),
                AuthorityUtils.createAuthorityList(admins.getRole().name())
        );

        log.trace("SecurityUser({}) Invoked.", admins);

    } // Constructor

} // end class
