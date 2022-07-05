package pl.dreilt.basicspringapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppUserController {

    @GetMapping("/login")
    public String loginForm() {
        return "login-form";
    }
}