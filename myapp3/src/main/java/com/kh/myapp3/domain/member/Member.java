package com.kh.myapp3.domain.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    private Long memberId;                 //number(8),
    private String email;                   //varchar2(40) not null,
    private String pw;                      //varchar2(10) not null,
    private String nickname;                //VARCHAR2(30),
    private LocalDateTime cdate;            //timestamp default systimestamp,
    private LocalDateTime udate;            //timestamp default systimestamp,

    public Member(String email, String pw, String nickname) {
        this.email = email;
        this.pw = pw;
        this.nickname = nickname;
    }
}
