package com.kh.myapp3.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Slf4j
@Controller
@RequestMapping("/thyme")
public class ThymeleafController {

    @GetMapping("/text")
    public String text(Model model) {
        Person person1 = new Person("홍길남", 40);
        Person person2 = new Person("홍길북", 50);

        model.addAttribute("person1", person1);
        model.addAttribute("person2", person2);

        model.addAttribute("hello", "<b>반갑습니다</b>");
        model.addAttribute("uhello", "<b>반갑습니다</b>");

        List<Person> personList = new ArrayList<>();
        personList.add(person1);
        personList.add(person2);

        Map<String, Person> personMap = new LinkedHashMap<>();
        personMap.put("1", person1);
        personMap.put("2", person2);

        Set<Person> personSet = new HashSet<>();
        personSet.add(person1);
        personSet.add(person2);

        model.addAttribute("personList", personList);
        model.addAttribute("personMap", personMap);
        model.addAttribute("personSet", personSet);
        return "thyme/text";
    }

    @AllArgsConstructor
    @Data
    static class Person {
        private String name;
        private int age;
    }

}
