package pl.dreilt.basicspringapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.dreilt.basicspringapp.dto.AppUserRegistrationDto;
import pl.dreilt.basicspringapp.service.AppUserService;

@Controller
public class AppUserController {

    private final AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login-form";
    }

    @GetMapping("/register")
    public String registrationForm(Model model) {
        AppUserRegistrationDto user = new AppUserRegistrationDto();
        model.addAttribute("user", user);
        return "registration-form";
    }

    @PostMapping("/register")
    public String register(AppUserRegistrationDto appUserRegistrationDto) {
        appUserService.register(appUserRegistrationDto);
        return "redirect:/confirmation";
    }

    @GetMapping("/confirmation")
    public String registrationConfirmation() {
        return "registration-confirmation";
    }
}
