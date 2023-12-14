package org.zerock.myapp.service;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.myapp.domain.Users;
import org.zerock.myapp.domain.UsersDTO;
import org.zerock.myapp.repository.UsersRepository;

import java.util.Optional;

@Log4j2

@Transactional
@Service
public class MypageServiceImpl implements MypageService{
    @Setter(onMethod_ = @Autowired)
    private UsersRepository usersRepo;

    @Setter(onMethod_ = @Autowired)
    private PasswordEncoder passwordEncoder;

    @Override
    public void updateInfo(UsersDTO usersDTO) {
        log.info("☆★프로필업데이트 진입☆★");
        log.info("\t☆★ [유저객체] : {} ★☆", usersDTO);

        // Null 체크
        if (usersDTO == null || usersDTO.getUserId() == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }

        Optional<Users> existingUserOptional = usersRepo.findByUserId(usersDTO.getUserId());

        // Optional 사용
        existingUserOptional.ifPresent(existingUser -> {
            // 사용자 정보를 업데이트
            if (usersDTO.getPassword() != null) {
                String rawPassword = usersDTO.getPassword();
                String encodedPassword = passwordEncoder.encode(rawPassword);
                existingUser.setPassword(encodedPassword);
            } else {
                existingUser.setNickName(usersDTO.getNickName());
                existingUser.setBirthdate(usersDTO.getBirthdate());
                existingUser.setEmail(usersDTO.getEmail());
            }
            // 업데이트된 사용자 엔터티 저장
            usersRepo.save(existingUser);
        });

    } // updateInfo

} // end class