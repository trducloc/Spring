package vn.techmaster.demo.utils;

import vn.techmaster.demo.model.Book;

import java.io.FileNotFoundException;
import java.util.List;

public interface IFileReader {
    List<Book> readFile(String filePath) throws FileNotFoundException;
}
