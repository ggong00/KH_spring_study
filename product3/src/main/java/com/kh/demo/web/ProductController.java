package com.kh.demo.web;

import com.kh.demo.domain.product.Product;
import com.kh.demo.domain.product.ProductSVC;
import com.kh.demo.web.form.DetailForm;
import com.kh.demo.web.form.SaveForm;
import com.kh.demo.web.form.UpdateForm;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {

  private final ProductSVC productSVC;

  //등록양식
  @GetMapping("/add")
  public String saveForm(Model model) {
    model.addAttribute("form", new SaveForm());
    return "product/saveForm";
  }

  //등록
  @PostMapping("/add")
  public String saveV2(@Valid @ModelAttribute("form") SaveForm saveForm,
                     BindingResult bindingResult,
                     RedirectAttributes redirectAttributes) throws IOException {

    //파일
    if(!saveForm.getFile().isEmpty()){
      String originalFilename = saveForm.getFile().getOriginalFilename();
      String filename = storeFilename(originalFilename);
      saveForm.getFile().transferTo(new File("d:/tmp/"+filename));
    }

    //이미지파일들
    if(!saveForm.getFiles().isEmpty()){
      List<MultipartFile> files = saveForm.getFiles();
      files.stream().forEach(file -> {
        try {
          String originalFilename = file.getOriginalFilename();
          String filename = storeFilename(originalFilename);
          file.transferTo(new File("d:/tmp/" + filename));
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      });
    }

    return "redirect:/products/{id}/detail";
  }

  private String storeFilename(String originalFilename) {
    String ext = originalFilename.substring(originalFilename.indexOf('.') + 1);
    String uuid = UUID.randomUUID().toString();
    String storeFilename = uuid + "." + ext;
    return storeFilename;
  }

  //등록
  //@PostMapping("/add")
  public String save(@Valid @ModelAttribute("form") SaveForm saveForm,
                     BindingResult bindingResult,
                     RedirectAttributes redirectAttributes) {

    //기본검증
    if(bindingResult.hasErrors()){
      log.info("bindingResult={}", bindingResult);
      return "product/saveForm";
    }

    //필드검증
    //상품수량은 100초과 금지
    if(saveForm.getQuantity() > 100){
      bindingResult.rejectValue("quantity","product.quantity",new Integer[]{100},"상품수량 초과");
      log.info("bindingResult={}", bindingResult);
      return "product/saveForm";
    }

    //오브젝트검증
    //총액(상품수량*단가) 1000만원 초과금지
    if(saveForm.getQuantity() * saveForm.getPrice() > 10_000_000L){
      bindingResult.reject("product.totalPrice",new Integer[]{1000},"총액 초과!");
      log.info("bindingResult={}", bindingResult);
      return "product/saveForm";
    }

    Product product = new Product();
    BeanUtils.copyProperties(saveForm, product);
    Long productId = productSVC.save(product);

    redirectAttributes.addAttribute("id", productId);
    return "redirect:/products/{id}/detail";
  }

  //조회
  @GetMapping("/{id}/detail")
  public String findByProductId(@PathVariable("id") Long productId,
                                Model model) {

    Optional<Product> findedProduct = productSVC.findByProductId(productId);
    DetailForm detailForm = new DetailForm();
    if(!findedProduct.isEmpty()) {
      BeanUtils.copyProperties(findedProduct.get(), detailForm);
    }

    model.addAttribute("form", detailForm);
    return "product/detailForm";
  }

  //수정양식
  @GetMapping("/{id}/edit")
  public String updateForm(@PathVariable("id") Long productId,
                           Model model) {

    Optional<Product> findedProduct = productSVC.findByProductId(productId);
    UpdateForm updateForm = new UpdateForm();
    if(!findedProduct.isEmpty()) {
      BeanUtils.copyProperties(findedProduct.get(), updateForm);
    }
    model.addAttribute("form", updateForm);

    return "product/updateForm";
  }

  //수정
  @PostMapping("/{id}/edit")
  public String update(@PathVariable("id") Long productId,
                       @Valid @ModelAttribute("form") UpdateForm updateForm,
                       BindingResult bindingResult,
                       RedirectAttributes redirectAttributes) {

    if (bindingResult.hasErrors()) {
      log.info("bindingResult={}", bindingResult);
      return "product/updateForm";
    }

    //필드검증
    //상품수량은 100초과 금지
    if(updateForm.getQuantity() > 100){
      bindingResult.rejectValue("quantity","product.quantity",new Integer[]{100},"상품수량 초과");
      log.info("bindingResult={}", bindingResult);
      return "product/saveForm";
    }

    //오브젝트검증
    //총액(상품수량*단가) 1000만원 초과금지
    if(updateForm.getQuantity() * updateForm.getPrice() > 10_000_000L){
      bindingResult.reject("product.totalPrice",new Integer[]{1000},"총액 초과!");
      log.info("bindingResult={}", bindingResult);
      return "product/saveForm";
    }

    Product product = new Product();
    BeanUtils.copyProperties(updateForm, product);
    productSVC.update(productId, product);

    redirectAttributes.addAttribute("id", productId);
    return "redirect:/products/{id}/detail";
  }

  //삭제
  @GetMapping("/{id}/del")
  public String deleteById(@PathVariable("id") Long productId) {

    productSVC.deleteByProductId(productId);

    return "redirect:/products/all";  //항시 절대경로로
  }

  //목록
  @GetMapping
  public String findAll(Model model) {
    List<Product> products = productSVC.findAll();

    List<Product> list = new ArrayList<>();
    products.stream().forEach(product->{
      BeanUtils.copyProperties(product,new DetailForm());
      list.add(product);
    });
    model.addAttribute("list", list);
    return "product/all";
  }

}
