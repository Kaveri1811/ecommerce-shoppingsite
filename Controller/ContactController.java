package com.example.ecommerce.controller;

import com.example.ecommerce.entity.Message;
import com.example.ecommerce.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ContactController {

    @Autowired
    private ContactService contactService;
    @PostMapping("/send/message")
    public String sendMessage(Message message, RedirectAttributes redirectAttributes){

        contactService.createMessage(message);

        redirectAttributes.addFlashAttribute("confirmation",
                "Your msg is sent successfully!!");
        return "redirect:/contactUs";
    }

}
