package com.example.alfa.project.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/content")
@CrossOrigin
public class TestController {

    @GetMapping("")
    public List<String> findAll(){
        return List.of("Hello", "World");
    }
}
