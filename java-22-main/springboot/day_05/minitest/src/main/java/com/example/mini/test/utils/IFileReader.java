package com.example.mini.test.utils;

import com.example.mini.test.model.Product;

import java.io.FileNotFoundException;
import java.util.List;

public interface IFileReader {
    List<Product> readFile(String filePath) throws FileNotFoundException;
}
