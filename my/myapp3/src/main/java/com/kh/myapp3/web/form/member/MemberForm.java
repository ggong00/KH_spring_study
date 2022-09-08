package com.kh.myapp3.web.form.member;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MemberForm {
  private Long memberId;        //회원아이디
  private String email;         //이메일
  private String pw;            //비밀번호
  private String nickname;      //별칭
  private LocalDateTime cdate;  //가입일
  private LocalDateTime udate;  //수정일
}
