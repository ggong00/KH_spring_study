package com.kh.myapp3.web;

import com.kh.myapp3.domain.Product;
import com.kh.myapp3.domain.svc.ProductSVC;
import com.kh.myapp3.web.form.SaveForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/product")
@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductSVC productSVC;

    //등록양식
    @GetMapping
    public String saveForm() {

        return "/product/saveForm"; //상품등록 view
    }

    //등록처리
    @PostMapping
    public String save(SaveForm saveForm) {
        Product product = new Product();
        product.setPname(saveForm.getPname());
        product.setQuantity(saveForm.getQuantity());
        product.setPrice(saveForm.getPrice());
        Integer productId = productSVC.save(product);

        return "redirect:product/"+productId;   //상품상세 view
    }

    //상품개별조회
    @GetMapping("/{pid}")
    public String findByProductId(@PathVariable("pid") String pid) {
        //db에서 상품조회

        return "product/detailForm";    //상품상세 view
    }

    //수정양식
    @GetMapping("/{pid}/edit")
    public String updateForm(@PathVariable("pid") String pid) {


        return "product/updateForm";    //상품수정 view
    }

    //수정처리
    @PostMapping("/{pid}/edit")
    public String update() {

        return "redirect:/product/1";   //상품상세 view
    }

    //삭제처리
    @GetMapping("/{pid}/del")
    public String delete() {

        return "redirect:/product/all";     //전체목록 view
    }

    //목록화면
    @GetMapping("/all")
    public String list() {

        return "product/all";   //전체목록 view
    }

}
