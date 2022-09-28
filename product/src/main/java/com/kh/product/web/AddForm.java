package com.kh.product.web;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class AddForm {
    @NotBlank
    private String pname;

    @NotNull
    @Range(min = 1, max = 100)
    @Positive
    private Integer count;

    @NotNull
    @Positive
    private Integer price;
}
