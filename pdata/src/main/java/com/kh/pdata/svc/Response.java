package com.kh.pdata.svc;

import lombok.Data;

import java.util.List;

@Data
public class Response {
    private Header header;
    @Data
    static class Header {
        private String resultCode;
        private String resultMsg;
    }

    private Body body;

    @Data
    static class Body {
        private List<Item> items;
        private String numOfRows;
        private String pageNo;
        private String totalCount;

        @Data
        static class Item{
            private String addrAmdNm;
            private String addrCpNm;
            private String addrCpbNm;
            private String addrEmdNm;
            private String atnmChkYn;
            private String baseYmd;
            private String cpNm;
            private String cpYmd;
            private String cpbNm;
            private String faciAddr1;
            private String faciAddr2;
            private String faciGbNm;
            private String faciHomepage;
            private String faciMngType;
            private String faciNm;
            private String faciPointX;
            private String faciPointY;
            private String faciPost;
            private String faciRegYmd;
            private String faciRoadAddr1;
            private String faciRoadAddr2;
            private String faciRoadPost;
            private String faciStat;
            private String faciTel;
            private String fcobNm;
            private String fmngCpNm;
            private String fmngCpbNm;
            private String fmngDeptNm;
            private String fmngTypeGbNm;
            private String fmngUserTel;
            private String ftypeNm;
            private String inoutGbn;
            private String lifeGymNm;
            private String nationYn;
            private String openYn;
            private String regDt;
            private String sdwnYmd;
            private String ssmDsnYn;
            private String standCptPsnCnt;
            private String standSeatCnt;
            private String thYmd;
            private String totFaciArea;
            private String updDt;
            private String useAsctNm;
        }

    }




}
