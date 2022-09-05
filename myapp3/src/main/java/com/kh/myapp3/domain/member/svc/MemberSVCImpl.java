package com.kh.myapp3.domain.member.svc;

import com.kh.myapp3.domain.member.Member;
import com.kh.myapp3.domain.member.dao.MemberDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberSVCImpl implements MemberSVC{

    private final MemberDAO memberDAO;

    /**
     * 가입
     *
     * @param member 가입정보
     * @return 회원
     */
    @Override
    public Member insert(Member member) {
        //회원아이디 생성
        Long memberId = memberDAO.generateMemberId();
        member.setMemberId(memberId);
        memberDAO.insert(member);
        return memberDAO.findById(memberId);
    }

    /**
     * 조회
     *
     * @param memberId 아이디
     * @return 회원
     */
    @Override
    public Member findById(Long memberId) {
        return memberDAO.findById(memberId);
    }

    /**
     * 수정
     *
     * @param memberId 아이디
     * @param member   수정할정보
     */
    @Override
    public int update(Long memberId, Member member) {
        int updateResultCnt = memberDAO.update(memberId, member);
//        log.info("업데이트 건수 {}", update);

//        Member foundMember = memberDAO.findById(memberId);

        return updateResultCnt;
    }

    /**
     * 삭제
     *
     * @param memberId 아이디
     */
    @Override
    public int del(Long memberId, String pw) {
        int del = memberDAO.del(memberId, pw);
        log.info("삭제 건수 {}", del);

        return del;
    }

    /**
     * 전체조회
     *
     * @return 회원목록
     */
    @Override
    public List<Member> all() {
        return memberDAO.all();
    }
}
