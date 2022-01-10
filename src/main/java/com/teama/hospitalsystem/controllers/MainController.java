package com.teama.hospitalsystem.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController implements ErrorController {

    @RequestMapping("/error")
    public String redirectToIndexHTML() {
        return "forward:index.html";
    }
}
