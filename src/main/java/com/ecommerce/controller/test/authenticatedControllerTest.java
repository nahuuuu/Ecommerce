package com.ecommerce.controller.test;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class authenticatedControllerTest {

    @GetMapping("/secret")
    public ResponseEntity<String> authenticated(){
        return ResponseEntity.ok("Soy super secreto");
    }
}
