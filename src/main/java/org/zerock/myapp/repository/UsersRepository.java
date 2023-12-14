package org.zerock.myapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.zerock.myapp.domain.Users;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByUserId(String userId);

    //-------- <회원가입> 중복검사 ------------------
    boolean existsByUserId(String inputValue);
    boolean existsByNickName(String inputValue);
    boolean existsByEmail(String inputValue);
    //-----------------------------------------------

    // 사용자 ID를 포함하여 검색하는 메소드
    List<Users> findByUserIdContaining(String userId);

} // end interface
