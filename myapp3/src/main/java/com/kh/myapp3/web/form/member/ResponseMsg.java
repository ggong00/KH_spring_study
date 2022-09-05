package com.kh.myapp3.web.form.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseMsg {
    private String status;
    private String msg;
    private Object data;
    private Integer resultCnt;

    public ResponseMsg(String status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }


}
