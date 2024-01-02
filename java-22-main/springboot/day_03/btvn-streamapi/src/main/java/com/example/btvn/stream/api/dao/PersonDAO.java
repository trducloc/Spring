package com.example.btvn.stream.api.dao;

import com.example.btvn.stream.api.database.PersonDB;
import com.example.btvn.stream.api.model.Person;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Repository

public class PersonDAO {
    public List<Person> findAll() { // select * from books
        return PersonDB.personList;
    }

    private final Map<String, Person> personMap = new HashMap<>();

    // Assuming PersonDB.personList contains the initial data
    public void PersonRepository(List<Person> personList) {
        for (Person person : personList) {
            personMap.put(String.valueOf(person.getId()), person);
        }
    }

    public Person findById(String id) {
        return personMap.get(id);
    }
}
