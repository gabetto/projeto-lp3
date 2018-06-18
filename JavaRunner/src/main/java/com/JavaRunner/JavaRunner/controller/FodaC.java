package com.JavaRunner.JavaRunner.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Controller
@RequestMapping("foda")
public class FodaC {

    private final Logger logger = LoggerFactory.getLogger(FodaC.class);

    @GetMapping
    public String test() throws IOException {
        try {
            System.out.println(String.valueOf(new ClassPathResource("application.properties").getURL()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("AAAAAAAAAAAAAAAAAAAA");
        return "fodac";
    }
}
