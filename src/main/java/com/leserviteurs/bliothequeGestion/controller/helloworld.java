package com.leserviteurs.bliothequeGestion.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class helloworld {

    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }   
}
