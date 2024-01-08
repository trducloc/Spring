package com.example.mini.test.utils;

import com.example.mini.test.model.Product;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Primary
@Component
public class CSVFileReader implements IFileReader  {
    @Override
    public List<Product> readFile(String filePath) {
        List<Product> products = new ArrayList<>();

        Path path = Paths.get(filePath);
        try {
            FileReader filereader = new FileReader(path.toFile());
            CSVReader csvReader = new CSVReaderBuilder(filereader)
                    .withSkipLines(1)
                    .build();

            List<String[]> allData = csvReader.readAll();
            for (String[] row : allData) {
                Product product = new Product();
                product.setId(Integer.valueOf(row[0]));
                product.setName(row[1]);
                product.setDescription(row[2]);
                product.setThumbnail(row[3]);
                product.setPrince(Integer.parseInt(row[4]));
                product.setRating(Double.parseDouble(row[5]));
                product.setPriceDiscount(Integer.parseInt(row[6]));


                products.add(product);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return products;
    }
}
