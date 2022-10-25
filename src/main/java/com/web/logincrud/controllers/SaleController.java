package com.web.logincrud.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sale")
public class SaleController {
    @GetMapping("/list")
    public String index() {
        return "/sale/index";
    }
}
