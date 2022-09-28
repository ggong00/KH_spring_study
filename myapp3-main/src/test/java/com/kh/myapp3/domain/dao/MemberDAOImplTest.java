package com.kh.myapp3.domain.dao;

import com.kh.myapp3.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;

@Slf4j
@SpringBootTest
class MemberDAOImplTest {

    @Autowired
    private MemberDAO memberDAO;

    @Test
    void login() {
        String email = "test1@test1";
        String pw = "1234";
        Optional<Member> login = memberDAO.login(email, pw);

        long count = login.stream().filter(member -> member.getEmail().equals(email)).count();
        Assertions.assertThat(count).isEqualTo(1);

    }

}