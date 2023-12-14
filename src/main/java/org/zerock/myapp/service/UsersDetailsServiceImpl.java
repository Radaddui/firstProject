package org.zerock.myapp.service;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.zerock.myapp.config.SecurityUser;
import org.zerock.myapp.domain.Users;
import org.zerock.myapp.repository.UsersRepository;

@Log4j2
@NoArgsConstructor

@Service
public class UsersDetailsServiceImpl implements UserDetailsService {

    @Setter(onMethod_ = @Autowired)
    private UsersRepository usersRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.trace("loadUserByUsername({}) Invoked.", username);

        Users foundUsers = this.usersRepo.findByUserId(username).orElseThrow(
                () -> new UsernameNotFoundException(String.format("No %s Found.",username))
        );


        return new SecurityUser(foundUsers);



    } // loadUserByUsername

} // end class
