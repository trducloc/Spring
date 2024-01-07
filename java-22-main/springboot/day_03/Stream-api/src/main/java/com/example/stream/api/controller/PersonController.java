package com.example.stream.api.controller;



import com.example.stream.api.model.Person;
import com.example.stream.api.service.PersonService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/person")
@NoArgsConstructor
public class PersonController {
    @Autowired
    private PersonService personService;
    @GetMapping("/")
    public String getHome (){
        return "index";
    }
    @GetMapping("/getAll") //http://localhost:8080/person/getAll
    public String getAll(Model model){
        model.addAttribute("getAll" ,personService.getAll());
        return "getAll";
    }
    @GetMapping("/longestName") //http://localhost:8080/person/longestName
    public String longestName (Model model){
        model.addAttribute("longestName",personService.longestName());
        return "longestName";
    }

    @GetMapping("/aboveAverageSalary") //http://localhost:8080/person/aboveAverageSalary
    public String aboveAverageSalary (Model model){
        model.addAttribute("aboveAverageSalary",personService.aboveAverageSalary());
        return "aboveAverageSalary";
    }

    @GetMapping("/groupPeopleByCity") //http://localhost:8080/person/groupPeopleByCity
    public String groupPeopleByCity (Model model){
        model.addAttribute("groupPeopleByCity",personService.groupPeopleByCity());
        return "groupPeopleByCity";
    }

    @GetMapping("/groupJobByCount") //http://localhost:8080/person/groupJobByCount
    public String groupJobByCount (Model model){
        model.addAttribute("groupJobByCount",personService.groupJobByCount());
        return "groupJobByCount";
    }

    @GetMapping("/sortPeopleByFullName")
    public String sortPeopleByFullName (Model model){
        model.addAttribute("namePerson" , personService.sortPeopleByFullName());
        return "sortPeopleByFullName";
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
