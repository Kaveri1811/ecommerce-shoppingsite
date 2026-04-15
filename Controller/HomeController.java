package com.example.ecommerce.controller;

import com.example.ecommerce.entity.Admin;
import com.example.ecommerce.entity.Message;
import com.example.ecommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private ProductService productService;

    @GetMapping({"/","/home"})
    public String homePage(){
        return "HomePage";
    }

    @GetMapping("/contactUs")
    public String contactPage(Model model){
        model.addAttribute("message", new Message());
        return "ContactUs";
    }

    @GetMapping("/products")
    public String productPage(Model model){
        model.addAttribute("productList", productService.getAllProduct());

        return "Products";
    }

    @GetMapping("/aboutUs")
    public String aboutUsPage(){
        return "AboutUs";
    }

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("admin", new Admin());
        return "LoginPage";
    }
}
