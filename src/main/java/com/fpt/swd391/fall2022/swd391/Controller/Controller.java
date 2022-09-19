package com.fpt.swd391.fall2022.swd391.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("Category")
public class Controller {
    @GetMapping
    public String demo() {
        return "HelooWorld";
    }
}