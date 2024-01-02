package vn.techmaster.demo.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import vn.techmaster.demo.model.Book;
import vn.techmaster.demo.utils.IFileReader;

import java.io.FileNotFoundException;

@Configuration
public class InitDB implements CommandLineRunner { // interface này sẽ được chạy khi Spring Boot khởi động xong
    @Autowired
    private IFileReader CSVFileReader;

    @Override
    public void run(String... args) throws FileNotFoundException {
        System.out.println("Đọc dữ liệu từ file");

        BookDB.bookList = CSVFileReader.readFile("MOCK_DATA.csv");
        System.out.println("Số lượng sách trong database: " + BookDB.bookList.size());

        for(Book book : BookDB.bookList) {
            System.out.println(book);
        }
    }
}
