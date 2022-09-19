package com.fpt.swd391.fall2022.swd91.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/systemCategory")
public class Controller {
    @GetMapping
    public String demo() {
        return "HelooWorld";
    }
}