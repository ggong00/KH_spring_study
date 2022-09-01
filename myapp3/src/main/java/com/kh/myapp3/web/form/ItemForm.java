package com.kh.myapp3.web.form;

import lombok.*;

@Getter @Setter @ToString
@AllArgsConstructor
@NoArgsConstructor
public class ItemForm {
    private Long productId;
    private String pname;
    private Long quantity;
    private Long price;
}
