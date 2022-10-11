package com.kh.myapp3.domain.admin;

import com.kh.myapp3.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class AdminMemberDAOImplTest {

    @Autowired
    private AdminMemberDAO adminMemberDAO;

    @Test
    @DisplayName("회원가입")
    void insert() {
        Member member = new Member();
        member.setMemberId(100L);
        member.setNickname("홍길동");
        member.setEmail("test2@test2.com");
        member.setPw("1234");
        adminMemberDAO.insert(member);

    }
}