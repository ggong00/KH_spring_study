package com.kh.myapp3.domain.member.dao;

import com.kh.myapp3.domain.member.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MemberDAOImpl implements MemberDAO{

    //가입
    private final JdbcTemplate jdbcTemplate;

    @Override
    public int insert(Member member) {
        StringBuffer sql = new StringBuffer();
        sql.append("insert into member (member_id,email,pw,nickname) ");
        sql.append("values(?,?,?,?) ");

        return jdbcTemplate.update(
                sql.toString(),
                member.getMemberId(),
                member.getEmail(),
                member.getPw(),
                member.getNickname()
        );
    }

    //신규 회원번호 생성
    public Long generateMemberId() {
        String sql = "select member_member_id_seq.nextval from dual ";

        return jdbcTemplate.queryForObject(sql, Long.class);
    }

    //조회
    @Override
    public Member findById(Long memberId) {
        StringBuffer sql = new StringBuffer();
        sql.append("select member_id,email,pw,nickname,cdate,udate ");
        sql.append("from member ");
        sql.append("where member_id = ? ");

        Member member = null;
        try {
            member = jdbcTemplate.queryForObject(
                    sql.toString(),
                    new BeanPropertyRowMapper<>(Member.class),
                    memberId
            );
        } catch (DataAccessException e) {
            log.info("찾고자하는 회원이 없습니다, = {}",memberId);
        }

        return member;
    }

    //수정
    @Override
    public int update(Long memberId, Member member) {
        StringBuffer sql = new StringBuffer();
        sql.append("update member ");
        sql.append("  set nickname = ?, ");
        sql.append("      udate = systimestamp ");
        sql.append("  where member_id = ? ");
        sql.append("  and pw = ? ");

        return jdbcTemplate.update(
                sql.toString(),
                member.getNickname(),
                memberId,
                member.getPw()
        );
    }

    //탈퇴
    @Override
    public int del(Long memberId, String pw) {
        String sql = "delete from member where member_id = ? and pw = ? ";
        return jdbcTemplate.update(sql, memberId, pw);
    }

    //전체조회
    @Override
    public List<Member> all() {
        String sql = "select member_id,email,pw,nickname,cdate,udate from member ";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Member.class));
    }
}
