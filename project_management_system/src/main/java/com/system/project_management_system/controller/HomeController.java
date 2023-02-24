package com.system.project_management_system.controller;


import com.system.project_management_system.entity.User;
import com.system.project_management_system.pojo.UserPojo;
import com.system.project_management_system.services.HomeService;
import com.system.project_management_system.services.UserService;
import com.system.project_management_system.services.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.Principal;
import java.util.Base64;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final HomeService homeService;
    private final UserService userService;
    @GetMapping("/")
    public String Home(Principal principal, Model model){
//        System.out.println("Yaha Aako xa");
//        model.addAttribute("usereds",userService.findbyUsername(principal.getName()));

        List<User> users = userService.findbyUsername(principal.getName());
        model.addAttribute("userList", users.stream().map(user ->
                User.builder()
                        .id(user.getId())
                        .imageBase64(getImageBase64(user.getUserimage()))
                        .email(user.getEmail())
                        .username(user.getUsername())
                        .build()

        ));

        return "index";
    }

    public String getImageBase64(String fileName) {
        String filePath = System.getProperty("user.dir") + "/projectmgmt/";
        File file = new File(filePath + fileName);
        byte[] bytes = new byte[0];
        try {
            bytes = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        String base64 = Base64.getEncoder().encodeToString(bytes);
        return base64;
    }
}
