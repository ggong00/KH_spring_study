package com.kh.myapp3.web;

import com.kh.myapp3.domain.Product;
import com.kh.myapp3.domain.svc.ProductSVC;
import com.kh.myapp3.web.form.AddForm;
import com.kh.myapp3.web.form.EditForm;
import com.kh.myapp3.web.form.ItemForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Slf4j
@RequestMapping("/products")
@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductSVC productSVC;

    //등록양식
    @GetMapping("/add")
    public String saveForm() {
        return "product/addForm"; //상품등록 view
    }

    //등록처리
    @PostMapping("/add")
    public String save(AddForm saveForm, RedirectAttributes redirectAttributes) {
        Product product = new Product();
        product.setPname(saveForm.getPname());
        product.setQuantity(saveForm.getQuantity());
        product.setPrice(saveForm.getPrice());
        Product product1 = productSVC.save(product);

        redirectAttributes.addAttribute("pid", product1.getProductId());

        return "redirect:/products/{pid}/detail";   //상품상세 view
    }

    //상품개별조회
    @GetMapping("/{pid}/detail")
    public String findByProductId(@PathVariable("pid") Long pid, Model model) {
        //db에서 상품조회
        Product findedProduct = productSVC.findById(pid);

        ItemForm itemForm = new ItemForm();
        itemForm.setProductId(findedProduct.getProductId());
        itemForm.setPname(findedProduct.getPname());
        itemForm.setQuantity(findedProduct.getQuantity());
        itemForm.setPrice(findedProduct.getPrice());

        model.addAttribute("itemForm",itemForm);

        return "product/itemForm";    //상품상세 view
    }

    //수정양식
    @GetMapping("/{pid}/edit")
    public String updateForm(@PathVariable("pid") Long pid, Model model) {
        Product findedProduct = productSVC.findById(pid);

        //Product => EditForm
        EditForm editForm = new EditForm();
        editForm.setProductId(findedProduct.getProductId());
        editForm.setPname(findedProduct.getPname());
        editForm.setQuantity(findedProduct.getQuantity());
        editForm.setPrice(findedProduct.getPrice());

        model.addAttribute("editForm", editForm);
        return "product/editForm";    //상품수정 view
    }

    //수정처리
    @PostMapping("/{pid}/edit")
    public String update(@PathVariable("pid") Long pid, EditForm editForm, RedirectAttributes redirectAttributes) {

        //EditForm => Product
        Product product = new Product();
        product.setProductId(pid);
        product.setPrice(editForm.getPrice());
        product.setQuantity(editForm.getQuantity());
        product.setPname(editForm.getPname());
        productSVC.update(product);

        redirectAttributes.addAttribute("pid", pid);
        return "redirect:/products/{pid}/detail";   //상품상세 view
    }

    //삭제처리
    @GetMapping("/{pid}/del")
    public String delete(@PathVariable("pid") Long pid) {
        productSVC.delete(pid);
        return "redirect:/products";     //전체목록 view
    }

    //목록화면
    @GetMapping
    public String list(Model model) {
        List<Product> list = productSVC.findALl();

        model.addAttribute("list",list);
        return "product/all";   //전체목록 view
    }

}
