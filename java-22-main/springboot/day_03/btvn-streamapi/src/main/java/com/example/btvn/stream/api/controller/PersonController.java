package com.example.btvn.stream.api.controller;

import com.example.btvn.stream.api.model.Person;
import com.example.btvn.stream.api.service.PersonService;
import com.example.btvn.stream.api.utils.CSVFileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private PersonService personService;
    private final CSVFileReader csvFileReader;
    @Autowired
    public PersonController(CSVFileReader csvFileReader) {
        this.csvFileReader = csvFileReader;
    }
    @GetMapping("/import/person.csv")
    public List<Person> importPersonFromCSV(@PathVariable String filePath) throws RuntimeException {
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


    @GetMapping(path = {"", "/getAllPerson"})
    public ResponseEntity<List<Person>> getPerson() {
        return new ResponseEntity<>(personService.getAllPerson(), HttpStatus.CREATED);
    }

    // http://localhost:8080/books/1
    // http://localhost:8080/books/2
    @GetMapping("/{id}")
    public ResponseEntity<?> getPersonById(@PathVariable String id) {
        return ResponseEntity.ok(personService.getPersonById(id));
    }
}
