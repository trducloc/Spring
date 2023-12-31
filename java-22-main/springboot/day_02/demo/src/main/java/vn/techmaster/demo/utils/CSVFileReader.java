package vn.techmaster.demo.utils;

import com.opencsv.exceptions.CsvException;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import vn.techmaster.demo.model.Book;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Primary
@Component
public class CSVFileReader implements IFileReader {
    @Override
    public List<Book> readFile(String filePath) {
        try (FileReader fileReader = new FileReader(filePath)) {
            CsvToBean<Book> csvToBean = new CsvToBeanBuilder<Book>(fileReader)
                    .withType(Book.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            return csvToBean.parse();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
