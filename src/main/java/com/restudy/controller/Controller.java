package com.restudy.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@org.springframework.stereotype.Controller
public class Controller {
    @GetMapping("/hi")
    public String niceToMeetYou(Model model){
        model.addAttribute("username","홍팍");
        return "greetings";
    }
}
