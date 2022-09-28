package com.kh.product.web;

import com.kh.product.domain.Product;
import com.kh.product.domain.ProductSVC;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequestMapping("/products")
@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductSVC productSVC;

    //전체조회
    @GetMapping
    public String all(Model model) {
        List<Product> products = productSVC.findAll();
        List<ProductDto> productDtos = products.stream().map(product -> {
            ProductDto productDto = new ProductDto();
            BeanUtils.copyProperties(product, productDto);
            return productDto;
        }).collect(Collectors.toList());

        model.addAttribute("products", productDtos);

        return "/product/all";
    }

    //등록 폼
    @GetMapping("/add")
    public String addForm(@ModelAttribute("product") AddForm addform, Model model) {

        model.addAttribute("product", addform);
        return "/product/addForm";
    }

    //등록 처리
    @PostMapping("/add")
    public String add(
            @Validated @ModelAttribute("product") AddForm addform,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        log.info("bindingResult {}",bindingResult);

        if (bindingResult.hasErrors()) {

            return "/product/addForm";
        }

        if(addform.getCount()*addform.getPrice() > 10000000) {
            bindingResult.reject("objectError",new String[]{"1000"},"총액이 1000만원을 초과할수 없음");
            return "/product/addForm";
        }

        Product product = new Product();
        BeanUtils.copyProperties(addform, product);
        Long productId = productSVC.add(product);
        redirectAttributes.addAttribute("productId", productId);

        return "redirect:/products/{productId}";
    }

    //조회
    @GetMapping("/{productId}")
    public String detail(@PathVariable Long productId, Model model) {
        Product product = productSVC.findById(productId);
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(product, productDto);
        model.addAttribute("product", productDto);
        return "/product/detail";
    }

    //수정 폼
    @GetMapping("/{productId}/edit")
    public String editForm(@PathVariable("productId") Long productId, Model model) {
        Product product = productSVC.findById(productId);
        EditForm editForm = new EditForm();
        BeanUtils.copyProperties(product, editForm);

        model.addAttribute("product", editForm);
        return "/product/editForm";
    }

    //수정 처리
    @PostMapping("/{productId}/edit")
    public String edit(
            @PathVariable("productId") Long productId,
            @Validated @ModelAttribute("product") EditForm editForm,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            Model model
    ) {
        log.info("bindingResult {}",bindingResult);
        if (bindingResult.hasErrors()) {

            return "/product/editForm";
        }

        if(editForm.getCount()*editForm.getPrice() > 10000000) {
            bindingResult.reject("objectError",new String[]{"1000"},"총액이 1000만원을 초과할수 없음");
            return "/product/editForm";
        }

        Product product = new Product();
        BeanUtils.copyProperties(editForm, product);
        productSVC.update(productId, product);

        redirectAttributes.addAttribute("productId", productId);
        return "redirect:/products/{productId}";
    }

    //상품 삭제 폼
    @GetMapping("/{productId}/del")
    public String delForm(@PathVariable Long productId) {

        return "/product/del";
    }

    //상품 삭제
    @PostMapping("/{productId}/del")
    public String del(@PathVariable Long productId, Model model) {
        Integer resultCount = productSVC.del(productId);
        model.addAttribute("productId", productId);

        return "redirect:/products";
    }
}
