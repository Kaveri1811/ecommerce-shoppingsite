package com.example.ecommerce.controller;

import com.example.ecommerce.entity.Admin;
import com.example.ecommerce.entity.Order;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.services.AdminService;
import com.example.ecommerce.services.OrderService;
import com.example.ecommerce.services.ProductService;
import com.example.ecommerce.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    private User user;

    @GetMapping("/admin/verify/credentials")
    public String verifyCredentials(@ModelAttribute("admin")Admin admin, Model model){
        if(adminService.verifyCredentials(admin.getEmail(),admin.getPassword())){
            model.addAttribute("admin",new Admin());
            model.addAttribute("user",new User());
            model.addAttribute("product",new Product());
            return "redirect:/admin/home";
        }
        model.addAttribute("error","Invalid email or password");
        return "LoginPage";
    }

    @GetMapping("/admin/home")
    public String adminHomePage(Model model){
        model.addAttribute("adminList", adminService.getAllAdmin());
        model.addAttribute("userList",userService.getAllUser());
        model.addAttribute("orderList", orderService.getAllOrder());
        model.addAttribute("productList", productService.getAllProduct());

        return "AdminHomePage";
    }


    @PostMapping("/add/admin")
    public String createAdmin(Admin admin){
        adminService.createAdmin(admin);

        return "redirect:/admin/home";
    }


    @PostMapping("/update/admin")
    public String updateAdmin(Admin admin){
        adminService.updateAdmin(admin);

        return "redirect:/admin/home";
    }

    @PostMapping("/delete/admin")
    public String deleteAdmin(@RequestParam Long id){
        adminService.deleteAdmin(id);

        return "redirect:/admin/home";
    }

    @PostMapping("/user/login")
    public String userLogin(User user, Model model){
        if(userService.verifyCredentials(user.getEmail(),user.getPassword())) {
            user = userService.findUserByEmail(user.getEmail());
            model.addAttribute("ordersList",orderService.findOrdersByUser(user));
            model.addAttribute("userId",user.getId());
            return "BuyProductPage";
        }
        model.addAttribute("error","Invalid email or password");
        return "LoginPage";
        }

    @PostMapping("/product/search")
    public String productSearch(String name,Long userId, Model model){
        Product product= productService.findProductByName(name);
        User user = userService.getUserById(userId);
        model.addAttribute("ordersList",orderService.findOrdersByUser(user));
        if(product!=null){
            model.addAttribute("product", product);
        }else {
            model.addAttribute("messageError", "Sorry, product is not found");
        }
        model.addAttribute("userId", userId);
        return "BuyProductPage";
    }

    @PostMapping("/place/order")
    public String placeOrder(Order order, Long userId, Model model){
        
        double totalAmount =order.getPrice() * order.getQuantity();
        order.setAmount(totalAmount);
        order.setDate(new Date());

        User user = userService.getUserById(userId);
        order.setUser(user);

        orderService.createOrder(order);
        
        model.addAttribute("messageSuccess", "The order has been placed.");
        return "BuyProductPage";
    }
}
