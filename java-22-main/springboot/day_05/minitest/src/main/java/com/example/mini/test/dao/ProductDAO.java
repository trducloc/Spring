package com.example.mini.test.dao;

import com.example.mini.test.database.ProductDB;
import com.example.mini.test.model.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ProductDAO implements ProductRespositoryInterface {
    private List<Product> products;
    @Override
    public List<Product> getAll() {
        return ProductDB.productList.stream().toList();
    }



    @Override
    public List<Product> getProductById(int id) {
        return ProductDB.productList.stream()
                .filter(product -> product.getId() == id)
                .collect(Collectors.toList());
    }



}
