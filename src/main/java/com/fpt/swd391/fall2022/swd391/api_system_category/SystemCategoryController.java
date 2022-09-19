package com.fpt.swd391.fall2022.swd391.api_system_category;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/systemCategory")
public class SystemCategoryController {
    @GetMapping
    public String demo(){
        return "HelooWorld";
    }
}
