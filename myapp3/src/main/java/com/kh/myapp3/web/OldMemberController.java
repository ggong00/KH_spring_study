package com.kh.myapp3.web;

import com.kh.myapp3.domain.member.Member;
import com.kh.myapp3.domain.member.svc.MemberSVC;
import com.kh.myapp3.web.form.member.JoinForm;
import com.kh.myapp3.web.form.member.MemberEditForm;
import com.kh.myapp3.web.form.member.MemberForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/old/members")
@Controller
@Slf4j
public class OldMemberController {

    private final MemberSVC memberSVC;

    @GetMapping("/add")
    public String joinForm() {

        return "/member/joinForm";
    }

    @PostMapping("/add")
    public String join(JoinForm joinForm, Model model, RedirectAttributes redirectAttributes) {
        Member member = new Member();
        member.setNickname(joinForm.getNickname());
        member.setEmail(joinForm.getEmail());
        member.setPw(joinForm.getPw());

        Member insertedMember = memberSVC.insert(member);
        model.addAttribute("member", insertedMember);
        redirectAttributes.addAttribute("id", insertedMember.getMemberId());

        return "redirect:/old/members/{id}";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long memberId, Model model) {
        Member foundMember = memberSVC.findById(memberId);

        MemberForm memberForm = new MemberForm();
        memberForm.setMemberId(memberId);
        memberForm.setEmail(foundMember.getEmail());
        memberForm.setNickname(foundMember.getNickname());
        memberForm.setPw(foundMember.getPw());
        memberForm.setCdate(foundMember.getCdate());
        memberForm.setUdate(foundMember.getUdate());

        model.addAttribute("member", memberForm);

        return "/member/memberForm";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable("id") Long memberId) {

        return "/member/editForm";
    }

    @PostMapping("/{id}/edit")
    public String edit(@PathVariable("id") Long memberId, MemberEditForm memberEditForm, Model model, RedirectAttributes redirectAttributes) {
        Member member = new Member();
        member.setPw(memberEditForm.getPw());
        member.setNickname(memberEditForm.getNickname());

        memberSVC.update(memberId, member);
        redirectAttributes.addAttribute("id", memberId);

        return "redirect:/old/members/{id}";
    }

    @GetMapping("/{id}/del")
    public String del(@PathVariable("id") Long memberId,@RequestParam("pw") String pw, RedirectAttributes redirectAttributes) {
        memberSVC.del(memberId,pw);

        return "redirect:/old/members";
    }

    @GetMapping
    public String all(Model model) {
        List<Member> memberList = memberSVC.all();
        model.addAttribute("members", memberList);

        return "/member/all";
    }
}
