package com.example.mini.test.database;

import com.example.mini.test.model.Product;
import com.example.mini.test.utils.IFileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.io.FileNotFoundException;

@Configuration
public class InitDB implements CommandLineRunner {
    @Autowired
    private IFileReader CSVFileReader;

    @Override
    public void run(String... args) throws FileNotFoundException {
        System.out.println("Đọc dữ liệu từ file");

        ProductDB.productList = CSVFileReader.readFile("product.csv");
        System.out.println("Số lượng sản phẩm trong database: " + ProductDB.productList.size());

        for(Product product : ProductDB.productList) {
            System.out.println(product);
        }
    }
}
