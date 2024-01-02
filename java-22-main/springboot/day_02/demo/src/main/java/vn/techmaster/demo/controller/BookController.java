package vn.techmaster.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import vn.techmaster.demo.model.Book;
import vn.techmaster.demo.service.BookService;
import vn.techmaster.demo.utils.CSVFileReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 * @Controller : Trả về View Template, Ngoài ra cũng có thể trả về JSON, XML
 * @RestController : Trả về JSON, XML
 * HTTP Method: GET, POST, PUT, DELETE
 * @RestController = @Controller + @ResponseBody
 * Class ResponseEntity<T>: Đại diện cho một HTTP response, bao gồm: status code, headers, và body.
 * */
//@RestController

@Controller
@RequestMapping("/books") //URL: http://localhost:8080/books, URL: http://localhost:8080/books/getAllBooks,
public class BookController {
    @Autowired
    private BookService bookService;
    private final CSVFileReader csvFileReader;
    @Autowired
    public BookController(CSVFileReader csvFileReader) {
        this.csvFileReader = csvFileReader;
    }
    @GetMapping("/import/MOCK_DATA.csv")
    public List<Book> importBooksFromCSV(@PathVariable String filePath) throws RuntimeException {
        return csvFileReader.readFile(filePath);
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

//    @GetMapping(path = {"", "/getAllBooks"})
//    // @RequestMapping(method = RequestMethod.GET)
//    @ResponseBody
//    @ResponseStatus(HttpStatus.CREATED) // 201
//    public List<Book> getBooks() {
//        return books;
//    }


    @GetMapping(path = {"", "/getAllBooks"})
    public ResponseEntity<List<Book>> getBooks() {
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.CREATED);
    }

    // http://localhost:8080/books/1
    // http://localhost:8080/books/2
    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable String id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }
}