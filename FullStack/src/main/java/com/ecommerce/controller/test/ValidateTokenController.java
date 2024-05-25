package com.ecommerce.controller.test;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ValidateTokenController {

    @PostMapping("/api/check-token")
    public ResponseEntity<?> checkToken(){
        return ResponseEntity.ok().build();
    }
}
