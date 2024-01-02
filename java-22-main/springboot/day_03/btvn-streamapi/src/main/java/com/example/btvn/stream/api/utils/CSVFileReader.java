package com.example.btvn.stream.api.utils;

import com.example.btvn.stream.api.model.Person;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
@Primary
@Component
public class CSVFileReader implements IFileReader {
    @Override
    public List<Person> readFile(String filePath) {
        try (FileReader fileReader = new FileReader(filePath)) {
            CsvToBean<Person> csvToBean = new CsvToBeanBuilder<Person>(fileReader)
                    .withType(Person.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            return csvToBean.parse();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
