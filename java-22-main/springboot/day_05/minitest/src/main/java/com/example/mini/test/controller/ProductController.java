package com.example.mini.test.controller;

import com.example.mini.test.model.Product;
import com.example.mini.test.service.ProductService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@NoArgsConstructor
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/product/getAll") //http://localhost:8080/product/getAll
    public String getAll(Model model){
        model.addAttribute("getAll" ,productService.getAll());
        return "getAll";
    }
    @GetMapping("/product/{id}")
    public String getProductById(@PathVariable int id, Model model) {
        model.addAttribute("getProductById" ,productService.getProductById(id));
        return "getProductById";
    }
}
