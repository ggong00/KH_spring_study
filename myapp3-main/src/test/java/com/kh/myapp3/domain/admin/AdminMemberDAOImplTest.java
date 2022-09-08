package com.kh.myapp3.domain.admin;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AdminMemberDAOImplTest {

    @Autowired
    private AdminMemberDAO adminMemberDAO;

    @Test
    @DisplayName("이메을중복체크")
    void dupChkOfMemberEmail() {
        Boolean isExist = adminMemberDAO.dupChkOfMemberEmail("ggo0415@naver.com");
        Assertions.assertThat(isExist).isTrue();

        isExist = adminMemberDAO.dupChkOfMemberEmail("ggo444@naver.com");
        Assertions.assertThat(isExist).isFalse();
    }
}