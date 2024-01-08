package com.example.mini.test.service;

import com.example.mini.test.dao.ProductDAO;
import com.example.mini.test.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductService {
    @Autowired
    private ProductDAO productDAO;
    public List<Product> getAll() {
        return productDAO.getAll();
    }
    public List<Product> getProductById(int id) {
        return productDAO.getProductById(id);
    }
}
