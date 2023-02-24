package com.system.project_management_system.controller;

import com.system.project_management_system.entity.User;
import com.system.project_management_system.exception.AppException;
import com.system.project_management_system.pojo.UserPojo;
import com.system.project_management_system.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/registerusers")
    public String SaveUser(@Valid UserPojo userPojo,RedirectAttributes redirectAttributes){
        System.out.println("HEREEEE");
        try {
            userService.saveUser(userPojo);
            redirectAttributes.addFlashAttribute("successMsg","User Saved SucessFully");
        }catch (AppException appException){
            redirectAttributes.addFlashAttribute("errorMsg",appException.getMessage());
        }
        finally {
            return "redirect:/";
        }

    }




    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user",new UserPojo());
        return "register";
    }

    @GetMapping("/login")
    public String login(){
        System.out.println("YAha Kina aairako ho tha bhayena");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return "/login";
        }
        return "redirect:/";

    }
}
