package com.kh.myapp3.web.admin.form.member;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class AddForm {
  @NotBlank
  @Email(regexp = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$" ,message = "이메일형식x")
  private String email;       //이메일

  @NotBlank
  @Size(min = 0, max = 10)
  private String pw;          //비밀번호

  @Size(min = 2, max = 10)
  private String nickname;    //별칭
}
