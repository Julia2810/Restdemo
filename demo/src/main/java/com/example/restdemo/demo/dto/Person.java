package com.example.restdemo.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private int id;
    private String firstname;
    private String surname;
    private String lastname;
    private LocalDate birthday;
}
