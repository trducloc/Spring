package com.example.mini.test.dao;

import com.example.mini.test.model.Product;

import java.util.List;

public interface ProductRespositoryInterface {

    List<Product> getAll(); //Liệt kê danh sách tất cả

    List<Product> getProductById(int id);
}
