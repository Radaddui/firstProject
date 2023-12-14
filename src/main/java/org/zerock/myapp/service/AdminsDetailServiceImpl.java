package org.zerock.myapp.service;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.zerock.myapp.config.SecurityAdmin;
import org.zerock.myapp.domain.Admins;
import org.zerock.myapp.repository.AdminsRepository;


@Log4j2
@NoArgsConstructor

@Service
public class AdminsDetailServiceImpl implements UserDetailsService {
    @Setter(onMethod_ = @Autowired)
    private AdminsRepository adminsRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.trace("loadUserByUsername({}) invoked.", username);
        Admins findAdmins = this.adminsRepo.findById(username).orElseThrow(
            () -> new UsernameNotFoundException(String.format("No %s Found.",username))

        );
        return new SecurityAdmin(findAdmins);
    }
}
