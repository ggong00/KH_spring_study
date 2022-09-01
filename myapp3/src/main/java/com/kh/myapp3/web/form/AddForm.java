package com.kh.myapp3.web.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@AllArgsConstructor
public class AddForm {
    private String pname;
    private Long quantity;
    private Long price;
}
