package com.kh.myapp3.web.form.member;

import lombok.Data;

@Data
public class JoinForm {
    private String email;
    private String pw;
    private String pwchk;
    private String nickname;
}
