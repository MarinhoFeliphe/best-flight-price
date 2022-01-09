package com.src.bestflightprice.companies.resources;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/index")
    public String getIndex() {
        return "index";
    }
}
