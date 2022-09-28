package com.kh.product.web;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;

@Data
public class EditForm {
    private Long productId;

    @NotBlank
    private String pname;

    @NotNull
    @Range(min = 1, max = 100)
    private Integer count;

    @NotNull
    @Positive
    private Integer price;
}
