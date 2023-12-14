package org.zerock.myapp.service;

import org.zerock.myapp.domain.Users;
import org.zerock.myapp.domain.UsersDTO;

import java.util.List;

public interface UsersService {
    public abstract void register_user(UsersDTO usersDTO);// register_user

    public abstract Users getUserById(String userId);

    // 회원정보 수정시 회원정보객체 들고오는 기능
    public abstract UsersDTO getUserDTOByUserId(String username);

    // 회원가입 중복검사
    public abstract boolean existsByUserInfo(String value, String keyWord);

    // 사용자 검색 메서드
    List<Users> searchUsersById(String userId);

} // end interface
