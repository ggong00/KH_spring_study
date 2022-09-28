package com.kh.myapp3;

import com.kh.myapp3.domain.Member;
import com.kh.myapp3.domain.svc.MemberSVC;
import com.kh.myapp3.web.form.LoginForm;
import com.kh.myapp3.web.session.LoginMember;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class HomeController {

  private final MemberSVC memberSVC;

  @GetMapping
  public String home(){
    //세션이 존재하면 로그인 후 화면





    //세션이 없으면 로그인 전 화면
    return "before-login";
  }

  //로그인 화면
  @GetMapping("/login")
  public String loginForm(
          @ModelAttribute("form") LoginForm loginForm,
          Model model
  ) {

    model.addAttribute("form", new LoginForm());
    return "login";
  }

  //로그인 처리
  @PostMapping("login")
  public String login(
          HttpServletRequest request,
          @Valid @ModelAttribute("form")LoginForm loginForm,
          BindingResult bindingResult
          ) {

    //기본 체크
    if (bindingResult.hasErrors()) {
      return "login";
    }

    //회원유무 체크
    Optional<Member> member = memberSVC.login(loginForm.getEmail(), loginForm.getPw());

    //회원이 아닌경우
    if (member.isEmpty()) {
      bindingResult.reject("loginForm.login","회원정보가 올바르지 않습니다.");
      return "login";
    }

    //회원인경우
    Member fountMember = member.get();
    LoginMember loginMember = new LoginMember(fountMember.getEmail(), fountMember.getPw());

    //세션에 회원정보 저장
    //세션정보가 있으면 가져오고 없으면 세션을 만듬
    HttpSession session = request.getSession(true);
    session.setAttribute("LoginMember", loginMember);

      return "after-login";
  }

  //로그아웃
  @GetMapping("/logout")
  public String logout(HttpServletRequest request) {
    //세션정보가 있으면 가져오고 없으면 세션을 만들지 않음
    HttpSession session = request.getSession(false);
    if (session != null) {
      session.invalidate();
    }

    return "redirect:/";
  }
}
