package com.example.demo2.dto;

import lombok.Getter;
import lombok.Setter;

/**
 *   ftypeNm varchar2(20),
 *   faciNm varchar2(20),
 *   faciTel varchar2(20)
 */

@Getter @Setter
public class SportsLocationDto {
    private String ftypeNm; //시설분류
    private String faciNm; //시설명
    private String faciTel; //번호

    public SportsLocationDto() {
    }

    public SportsLocationDto(String ftypeNm, String faciNm, String faciTel) {
        this.ftypeNm = ftypeNm;
        this.faciNm = faciNm;
        this.faciTel = faciTel;
    }
}
