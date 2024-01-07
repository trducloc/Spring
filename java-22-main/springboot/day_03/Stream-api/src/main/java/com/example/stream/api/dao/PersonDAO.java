package com.example.stream.api.dao;

import com.example.stream.api.database.PersonDB;
import com.example.stream.api.model.Person;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository

public class PersonDAO implements PersonRepositoryInterface {

    private List<Person> persons;

    @Override
    public void printListPeople(List<Person> persons) {
        persons.forEach(System.out::println);
    }

    @Override
    public List<Person> getAll() {
        return PersonDB.personList.stream().toList();
    }

    @Override
    public List<Person> sortPeopleByFullName() {
        return getAll().stream()
                .sorted((person1, person2) -> person1.getFullname()
                        .compareToIgnoreCase(person2.getFullname()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Person> sortPeopleByFullNameReversed() {
        return getAll().stream()
                .sorted((p1, p2) -> p2.getFullname().compareTo(p1.getFullname()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getSortedJobs() {
        return getAll().stream()
                .map(Person::getJob)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getSortedCities() {
        return getAll().stream()
                .map(Person::getCity)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public List<String> femaleNames() {
        return getAll().stream()
                .filter(person -> person.getGender().equals("Female"))
                .map(Person::getFullname)
                .collect(Collectors.toList());
    }

    @Override
    public Person highestEarner() {
        return getAll().stream()
                .max(Comparator.comparingDouble(Person::getSalary))
                .orElse(null);
    }

    @Override
    public List<Person> bornAfter1990() {
        LocalDate dateThreshold = LocalDate.of(1990, 1, 1);
        return getAll().stream()
                .filter(person -> person.getBirthday().isAfter(dateThreshold))
                .collect(Collectors.toList());
    }

    @Override
    public double averageSalary() {
        return getAll().stream()
                .mapToDouble(Person::getSalary)
                .average()
                .orElse(0.0);
    }

    @Override
    public double averageAge() {
        return (double) getAll().stream()
                .map(person -> LocalDate.now().getYear() - person.getBirthday().getYear())
                .reduce(0, Integer::sum, Integer::sum) / PersonDB.personList.size();
    }


    @Override
    public List<Person> nameContains(String keyword) {
        return getAll().stream()
                .filter(person -> person.getFullname().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Person> sortedByAgeForMale() {
        return getAll().stream()
                .filter(person -> person.getGender().equalsIgnoreCase("Male"))
                .sorted(Comparator.comparingInt(person -> person.getBirthday().getYear()))
                .collect(Collectors.toList());
    }

    @Override
    public Person longestName() {
        return getAll().stream()
                .max(Comparator.comparingInt(person -> person.getFullname().length()))
                .orElse(null);
    }

    @Override
    public List<Person> aboveAverageSalary() {
        double averageSalary = averageSalary();
        return getAll().stream()
                .filter(person -> person.getSalary() > averageSalary)
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, List<Person>> groupPeopleByCity() {
        return PersonDB.personList.stream()
                .collect(Collectors.groupingBy(Person::getCity));
    }

    @Override
    public Map<String, Integer> groupJobByCount() {
        return getAll().stream()
                .collect(Collectors.groupingBy(Person::getJob, Collectors.collectingAndThen(Collectors.counting(), Long::intValue)));
    }

    @Override
    public List<Person> inSalaryRange(int start, int end) {
        return getAll().stream()
                .filter(person -> person.getSalary() >= start && person.getSalary() <= end)
                .collect(Collectors.toList());
    }

    @Override
    public List<Person> startsWith(String prefix) {
        return getAll().stream()
                .filter(person -> person.getFullname().startsWith(prefix))
                .collect(Collectors.toList());
    }

    @Override
    public List<Person> sortByBirthYearDescending() {
        return getAll().stream()
                .sorted(Comparator.comparing(Person::getBirthday).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<Person> top5HighestPaid() {
        return getAll().stream()
                .sorted(Comparator.comparingDouble(Person::getSalary).reversed())
                .limit(5)
                .collect(Collectors.toList());
    }

    @Override
    public List<Person> inAgeRange(int start, int end) {
        return null;
    }
}