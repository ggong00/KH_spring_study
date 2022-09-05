package com.kh.myapp3.domain.member.dao;

import com.kh.myapp3.domain.member.Member;

import java.util.List;

public interface MemberDAO {

    /**
     * 가입
     * @param member    가입정보
     * @return 가입건수
     */
    int insert(Member member);

    /**
     * 조회
     * @param memberId  아이디
     * @return 회원
     */
    Member findById(Long memberId);

    /**
     * 회원아이디 생성
     * @return 회원아이디
     */
    Long generateMemberId();

    /**
     * 수정
     * @param memberId  아이디
     * @param member    수정할정보
     */
    int update(Long memberId, Member member);

    /**
     * 삭제
     * @param memberId  아이디
     * @param pw        비밀번호
     */
    int del(Long memberId, String pw);

    /**
     * 전체조회
     * @return  회원목록
     */
    List<Member> all();

}
