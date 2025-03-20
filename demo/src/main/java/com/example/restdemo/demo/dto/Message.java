package com.example.restdemo.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private int id;
    private String title;
    private String text;
    private LocalDateTime time;
}