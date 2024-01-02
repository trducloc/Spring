package com.example.stream.api.utils;

import com.example.stream.api.model.Person;

import java.io.FileNotFoundException;
import java.util.List;

public interface IFileReader {
    List<Person> readFile(String filePath) throws FileNotFoundException;
}
