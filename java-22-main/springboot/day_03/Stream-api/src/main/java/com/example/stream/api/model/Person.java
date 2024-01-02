package com.example.stream.api.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Person {
    int id;
    String fullname;
    String job;
    String gender;
    String city;
    int salary;
    LocalDate birthday;

    public Person(int id, String fullname, String job, String gender, String city, int salary, LocalDate birthday) {
        this.id = id;
        this.fullname = fullname;
        this.job = job;
        this.gender = gender;
        this.city = city;
        this.salary = salary;
        this.birthday = birthday;
    }
}