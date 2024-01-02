package com.example.stream.api.database;

import com.example.stream.api.model.Person;
import com.example.stream.api.utils.IFileReader;
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

        PersonDB.personList = CSVFileReader.readFile("person.csv");
        System.out.println("Số lượng người trong database: " + PersonDB.personList.size());

        for(Person person : PersonDB.personList) {
            System.out.println(person);
        }
    }
}
