package com.example.restdemo.demo.controller;

import com.example.restdemo.demo.dto.Person;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
public class PersonController {
    private List<Person> persons = new ArrayList<>(Arrays.asList(
            new Person(1, "Ivan", "Ivanovich", "Ivanov", LocalDate.of(1999, 2,3)),
            new Person(2, "Петр", "Петрович", "Петров", LocalDate.of(2002, 2,2)),
            new Person(3, "Евгений", "Васильевич", "Васин", LocalDate.of(2005, 4,8)),
            new Person(4, "Максим", "Яковлевич", "Окопский", LocalDate.of(1978, 6,5))
    ));

    @GetMapping
    public String hello()
    {
        return "Hello, World!";
    }

    @GetMapping("/person")
    public  List<Person> getPersons()
    {
        return persons;
    }

    @GetMapping("/person/{id}")
    public Optional<Person> findPersonById(@PathVariable int id)
    {
        return persons.stream()
                .filter(p -> p.getId() == id)
                .findFirst();
    }

    @PutMapping("/person/{id}")
    public Person updatePerson(@PathVariable int id, @RequestBody Person person) // @RequestBody Person - JSON to Person
    {
        int index = - 1;

        for (Person p: persons)
        {
            if(p.getId() == id)
            {
                index = persons.indexOf(p);
                persons.set(index, person);
            }
        }

        return index == -1 ? addPerson(person) : person;
    }

    @PostMapping("/person")
    public Person addPerson(@RequestBody Person person) // @RequestBody Person - JSON to Person
    {
        persons.add(person);
        return  person;
    }

    @DeleteMapping("/person/{id}")
    public void deletePerson(@PathVariable int id)
    {
        persons.removeIf(p -> p.getId() == id);

    }
}