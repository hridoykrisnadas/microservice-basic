package com.hridoykrisna.apigateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {
    @GetMapping("/userServiceFallback")
    public String userServiceFallBack(){
        return "User-Service is currently shutdown";
    }
    @GetMapping("/bookServiceFallback")
    public String bookServiceFallBack(){
        return "Book-Service is currently shutdown";
    }
}
