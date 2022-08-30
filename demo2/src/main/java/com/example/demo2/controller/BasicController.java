package com.example.demo2.controller;

import com.example.demo2.domain.User;
import com.example.demo2.dta.BasicDta;
import com.example.demo2.dto.SportsLocationDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BasicController {

    private final BasicDta basicDta;

    @GetMapping("/")
    public String testForm() {
        log.info("testFrom 통과");

        return "test/listform";
    }

    @PostMapping("/user")
    public String userInfo(@ModelAttribute User user , Model model) {
        model.addAttribute("user", user);
        log.info("user 통과");
        return "test/userinfo";
    }

    @PostMapping("/search")
    public String search(@ModelAttribute SportsLocationDto sportsLocationDto , Model model) {
        List<SportsLocationDto> findedData = basicDta.find(sportsLocationDto.getFaciNm());
        model.addAttribute("list", findedData);
        log.info("데이터 확인{}",findedData);
        return "test/list";
    }


}
