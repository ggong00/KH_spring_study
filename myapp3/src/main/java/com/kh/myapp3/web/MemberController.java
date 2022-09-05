package com.kh.myapp3.web;

import com.kh.myapp3.domain.member.Member;
import com.kh.myapp3.domain.member.svc.MemberSVC;
import com.kh.myapp3.web.form.member.JoinForm;
import com.kh.myapp3.web.form.member.MemberEditForm;
import com.kh.myapp3.web.form.product.AddForm;
import com.kh.myapp3.web.form.product.EditForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberSVC memberSVC;

    //가입화면
    @GetMapping("/add")
    public String addForm() {

        return "member/joinForm"; //가입화면
    }

    //가입처리
    @PostMapping("/add")
    public String add(JoinForm joinForm) {

        log.info("joinForm  {}", joinForm);

        //검증
        Member member = new Member();
        member.setEmail(joinForm.getEmail());
        member.setPw(joinForm.getPw());
        member.setNickname(joinForm.getNickname());
        memberSVC.insert(member);

        return "login/loginForm"; //로그인 화면
    }

    //조회화면
    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {

        Member member = memberSVC.findById(id);
        model.addAttribute("member", member);
        return "member/memberForm"; //회원 상세화면
    }

    //수정화면
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable("id") Long id, Model model) {

        Member member = memberSVC.findById(id);
        model.addAttribute("member", member);
        return "member/editForm"; //회원 수정화면
    }

    //수정처리
    @PostMapping("/{id}/edit")
    public String edit(@PathVariable Long id, MemberEditForm memberEditForm, RedirectAttributes redirectAttributes) {
        Member member = new Member();
        member.setPw(memberEditForm.getPw());
        member.setNickname(memberEditForm.getNickname());

        int updatedRow = memberSVC.update(id, member);
        log.info("updateRow : {}", updatedRow);

        if (updatedRow == 0) {
            return "member/editForm";
        }

        redirectAttributes.addAttribute("id", id);
        return "redirect:/members/{id}"; //회원 상세화면
    }

    //탈퇴화면
    @GetMapping("/{id}/del")
    public String delForm() {

        return "member/delForm";
    }

    //탈퇴처리
    @PostMapping("/{id}/del")
    public String del(@PathVariable("id") Long id, @RequestParam("pw") String pw) {
        int deletedRow = memberSVC.del(id, pw);
        if (deletedRow == 0) {
            return "member/delForm";
        }

        return "redirect:/";
    }

    //목록화면
    @GetMapping("/all")
    public String all() {

        return "member/all";
    }
}
