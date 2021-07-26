package com.amber.mycommunity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author amber
 * @date 2021/7/22 21:14
 */
@Controller
public class IndexController {
    @GetMapping("/")
    public  String index(){
        return "index";
    }
}
