package com.kh.myapp3.domain.member.svc;

import com.kh.myapp3.domain.member.Member;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class MemberSVCImplTest {

    @Autowired
    private MemberSVC memberSVC;
    private static Member member;

    @Order(1)
    @Test
    void insert() {
        Member member1 = new Member("test@test10", "test12347", "10");
        member = memberSVC.insert(member1);

        assertThat(member1.getEmail()).isEqualTo(member.getEmail());
        assertThat(member1.getPw()).isEqualTo(member.getPw());
        assertThat(member1.getNickname()).isEqualTo(member.getNickname());
    }

    @Order(2)
    @Test
    void findById() {
        Member findedMember = memberSVC.findById(member.getMemberId());
        assertThat(findedMember).isEqualTo(member);
    }

    @Order(3)
    @Test
    void update() {
        Member m = new Member();
        m.setPw("9999");
        m.setNickname("별칭111");

        memberSVC.update(member.getMemberId(),m);

        Member findedMember = memberSVC.findById(member.getMemberId());

        assertThat(findedMember.getPw()).isEqualTo(m.getPw());
        assertThat(findedMember.getNickname()).isEqualTo(m.getNickname());
    }

    @Order(4)
    @Test
    void all() {
        List<Member> members = memberSVC.all();
        Member findedMember = memberSVC.findById(member.getMemberId());
        log.info("멤버 {}",member);
        log.info("finded리스트 {}",members);
        assertThat(members).containsAnyOf(findedMember);
    }

    @Order(5)
    @Test
    void del() {
        memberSVC.del(member.getMemberId(),member.getPw());

        Member findedMember = memberSVC.findById(member.getMemberId());
        assertThat(findedMember).isNull();
    }
}