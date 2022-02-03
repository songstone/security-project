package com.example.securityproject.controller;

import com.example.securityproject.dto.UserRegisterDto;
import com.example.securityproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/signup")
@Controller
public class SignUpController {

    private final UserService userService;

    @GetMapping
    public String signUp(){
        return "signup";
    }

    @PostMapping
    public String singUp(@ModelAttribute UserRegisterDto userDto){
        userService.signUp(userDto.getUserName(), userDto.getPassword());

        return "redirect:login";
    }
}
