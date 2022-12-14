package com.penjualan.demo.mvccontroller;

import com.penjualan.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/loginForm")
    public String loginForm(Model model) {
        return "login/login-form";
    }
}
