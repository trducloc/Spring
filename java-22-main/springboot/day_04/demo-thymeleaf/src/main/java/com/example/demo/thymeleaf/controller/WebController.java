package com.example.demo.thymeleaf.controller;

import com.example.demo.thymeleaf.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class WebController {
    private List<Student> students = new ArrayList<>(List.of(
            new Student(1, "Loc", "Tranducloc@gmail.com", 20, 8),
            new Student(2, "An", "an@gmail.com", 22, 5),
            new Student(3, "Binh", "binh@gmail.com", 23, 1),
            new Student(4, "Truong", "Truong@gmail.com", 27, 9)
    ));
    @GetMapping("/")
    public String getHome(Model model, @RequestParam(required = false) String rank){
        // tra ve mot doi tuong
        model.addAttribute("student", students.get(0));

        // tra ve 1 list doi tuong
        model.addAttribute("student", students);
        return "index";
    }
    @GetMapping("/students/{id}")
    public String getStudentDetailPage(@PathVariable int id, Model model){
        Student student = students.stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .orElse(null);
        model.addAttribute("student", student);
        return"student-detail";
    }
}
