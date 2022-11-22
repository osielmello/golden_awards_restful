package com.awards.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GoldenAwardsController {

    @GetMapping("/test")
    public String teste() { // TODO OSIEL REMOVER
        return "HELLO WORLD";
    }
}
