package com.kh.myapp3.web;

import com.kh.myapp3.domain.member.Member;
import com.kh.myapp3.domain.member.svc.MemberSVC;
import com.kh.myapp3.web.form.member.JoinForm;
import com.kh.myapp3.web.form.member.MemberEditForm;
import com.kh.myapp3.web.form.member.ResponseMsg;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/old/members-rest")
@RestController
@Slf4j
public class MemberRestController {

    private final MemberSVC memberSVC;

    @PostMapping("/add")
    public ResponseMsg join(JoinForm joinForm) {
        Member member = new Member();
        member.setNickname(joinForm.getNickname());
        member.setEmail(joinForm.getEmail());
        member.setPw(joinForm.getPw());

        Member insertedMember = memberSVC.insert(member);
        HashMap<String, Object> resultData = getStringMemberHashMap(insertedMember);
        ResponseMsg responseMsg = new ResponseMsg("200", "가입성공", resultData);

        return responseMsg;
    }


    @GetMapping("/{id}")
    public ResponseMsg findById(@PathVariable("id") Long memberId) {
        Member foundMember = memberSVC.findById(memberId);

        HashMap<String, Object> resultData = getStringMemberHashMap(foundMember);
        ResponseMsg responseMsg = new ResponseMsg("200", "조회성공", resultData);

        return responseMsg;
    }

    @PostMapping("/{id}/edit")
    public ResponseMsg edit(@PathVariable("id") Long memberId, MemberEditForm memberEditForm) {
        Member member = new Member();
        member.setPw(memberEditForm.getPw());
        member.setNickname(memberEditForm.getNickname());

        int updatedMember = memberSVC.update(memberId, member);
        HashMap<String, Object> resultData = getStringMemberHashMap(updatedMember);

        ResponseMsg responseMsg = new ResponseMsg("200", "수정성공", resultData);

        return responseMsg;
    }

    @GetMapping("/{id}/del")
    public ResponseMsg del(@PathVariable("id") Long memberId,@RequestParam("pw") String pw) {
        int del = memberSVC.del(memberId,pw);

        HashMap<String, Object> resultData = getStringMemberHashMap(del);

        ResponseMsg responseMsg = new ResponseMsg("200", "삭제성공", resultData, del);

        return responseMsg;
    }

    @GetMapping
    public ResponseMsg all() {
        List<Member> memberList = memberSVC.all();

        HashMap<String, Object> resultData = getStringMemberHashMap(memberList);

        ResponseMsg responseMsg = new ResponseMsg("200", "전체조회 성공", resultData);

        return responseMsg;
    }

    private HashMap<String, Object> getStringMemberHashMap(Object data) {
        HashMap<String, Object> memberHashMap = new HashMap<>();
        memberHashMap.put("결과", data);
        return memberHashMap;
    }

}
