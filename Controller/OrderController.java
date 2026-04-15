package com.example.ecommerce.controller;

import com.example.ecommerce.entity.Order;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.services.OrderService;
import com.example.ecommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;


    @PostMapping("/add/order")
    public String addProduct(Order order){
        orderService.createOrder(order);

        return "/admin/home";
    }

    @GetMapping("/update/order/{id}")
    public String updateProduct(@PathVariable Long id, Model model){
        model.addAttribute("order",orderService.getOrderById(id));

        return "UpdateProduct";
    }

    @PostMapping("/update/order")
    public String updateProduct(Order order){
        orderService.updateOrder(order);

        return "/admin/home";
    }

    @DeleteMapping("/delete/order/{id}")
    public String deleteProduct(@PathVariable Long id){
        orderService.deleteOrder(id);
        return "/admin/home";
    }


}
