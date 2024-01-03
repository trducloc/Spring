package com.example.stream.api.controller;


import com.example.stream.api.model.Person;
import com.example.stream.api.service.PersonService;
import com.example.stream.api.utils.CSVFileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

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
    @GetMapping("")
    public void printListPeople() {
        personService.printListPeople();
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Person>> getAll() {
        return new ResponseEntity<>(personService.getAll(), HttpStatus.CREATED);
    }

    @GetMapping("/sortPeopleByFullName")
    public ResponseEntity<List<Person>> sortPeopleByFullName() {
        return new ResponseEntity<>(personService.sortPeopleByFullName(), HttpStatus.CREATED);
    }

    @GetMapping("/sortPeopleByFullNameReversed")
    public ResponseEntity<List<Person>> sortPeopleByFullNameReversed() {
        return new ResponseEntity<>(personService.sortPeopleByFullNameReversed(), HttpStatus.CREATED);
    }

    @GetMapping("/getSortedJobs")
    public ResponseEntity<List<String>> getSortedJobs() {
        return new ResponseEntity<>(personService.getSortedJobs(), HttpStatus.CREATED);
    }

    @GetMapping("/getSortedCities")
    public ResponseEntity<List<String>> getSortedCities() {
        return new ResponseEntity<>(personService.getSortedCities(), HttpStatus.CREATED);
    }

    @GetMapping("/femaleNames")
    public ResponseEntity<List<String>> femaleNames() {
        return new ResponseEntity<>(personService.femaleNames(), HttpStatus.CREATED);
    }

    @GetMapping("/highestEarner")
    public ResponseEntity<Person> highestEarner() {
        return new ResponseEntity<>(personService.highestEarner(), HttpStatus.CREATED);
    }

    @GetMapping("/bornAfter1990")
    public ResponseEntity<List<Person>> bornAfter1990() {
        return new ResponseEntity<>(personService.bornAfter1990(), HttpStatus.CREATED);
    }

    @GetMapping("/averageSalary")
    public ResponseEntity<Double> averageSalary() {
        return new ResponseEntity<>(personService.averageSalary(), HttpStatus.CREATED);
    }

    @GetMapping("/averageAge")
    public ResponseEntity<Double> averageAge() {
        return new ResponseEntity<>(personService.averageAge(), HttpStatus.CREATED);
    }

    @GetMapping("/nameContains/{keyword}")
    public ResponseEntity<List<Person>> nameContains(@PathVariable String keyword) {
        return new ResponseEntity<>(personService.nameContains(keyword), HttpStatus.CREATED);
    }

    @GetMapping("/sortedByAgeForMale")
    public ResponseEntity<List<Person>> sortedByAgeForMale() {
        return new ResponseEntity<>(personService.sortedByAgeForMale(), HttpStatus.CREATED);
    }

    @GetMapping("/longestName")
    public ResponseEntity<Person> longestName() {
        return new ResponseEntity<>(personService.longestName(), HttpStatus.CREATED);
    }

    @GetMapping("/aboveAverageSalary")
    public ResponseEntity<List<Person>> aboveAverageSalary() {
        return new ResponseEntity<>(personService.aboveAverageSalary(), HttpStatus.CREATED);
    }

    @GetMapping("/groupPeopleByCity")
    public ResponseEntity<Map<String, List<Person>>> groupPeopleByCity() {
        return new ResponseEntity<>(personService.groupPeopleByCity(), HttpStatus.CREATED);
    }

    @GetMapping("/groupJobByCount")
    public ResponseEntity<Map<String, Integer>> groupJobByCount() {
        return new ResponseEntity<>(personService.groupJobByCount(), HttpStatus.CREATED);
    }

    @GetMapping("/inSalaryRange")
    public ResponseEntity<List<Person>> inSalaryRange(@RequestParam int start, @RequestParam int end) {
        return new ResponseEntity<>(personService.inSalaryRange(start, end), HttpStatus.CREATED);
    }

    @GetMapping("/startsWith/{prefix}")
    public ResponseEntity<List<Person>> startsWith(@PathVariable String prefix) {
        return new ResponseEntity<>(personService.startsWith(prefix), HttpStatus.CREATED);
    }

    @GetMapping("/sortByBirthYearDescending")
    public ResponseEntity<List<Person>> sortByBirthYearDescending() {
        return new ResponseEntity<>(personService.sortByBirthYearDescending(), HttpStatus.CREATED);
    }

    @GetMapping("/top5HighestPaid")
    public ResponseEntity<List<Person>> top5HighestPaid() {
        return new ResponseEntity<>(personService.top5HighestPaid(), HttpStatus.CREATED);
    }

    @GetMapping("/inAgeRange")
    public ResponseEntity<List<Person>> inAgeRange(@RequestParam int start, @RequestParam int end) {
        return new ResponseEntity<>(personService.inAgeRange(start, end), HttpStatus.CREATED);
    }


}
