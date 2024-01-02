package com.example.stream.api.service;

import com.example.stream.api.dao.PersonDAO;
import com.example.stream.api.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    @Autowired
    private PersonDAO personDAO;

    public List<Person> getAllPerson() {
        return personDAO.findAll();
    }

    public Person getPersonById(String id) {
//         C1: Sử dụng trực tiếp
        return personDAO.findById(id);

//        // C2: Sử dụng gián tiếp
//        for (Person person : personDAO.findAll()) {
//            if (person.getId().equals(id)) {
//                return person;
//            }
//        }
//        return null;
    }
}