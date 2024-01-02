package com.example.stream.api.utils;

import com.example.stream.api.model.Person;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;


import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Primary
@Component
public class CSVFileReader implements IFileReader {
    @Override
    public List<Person> readFile(String filePath) {
        try (FileReader fileReader = new FileReader(filePath)) {
            ColumnPositionMappingStrategy<Person> strategy = new ColumnPositionMappingStrategy<>();
            strategy.setType(Person.class);
            CsvToBean<Person> csvToBean = new CsvToBeanBuilder<Person>(fileReader)
                    .withType(Person.class)
                    .withMappingStrategy(strategy)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            return csvToBean.parse();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
