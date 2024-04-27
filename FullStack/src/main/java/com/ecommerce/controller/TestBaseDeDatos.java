package com.ecommerce.controller;


import com.ecommerce.entity.UserEntity;
import com.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173/")
public class TestBaseDeDatos {

    @Autowired
    private UserRepository userRepository;


    @PostMapping("/post")
    public ResponseEntity<UserEntity> crearUsuario(@RequestBody UserEntity user){
        userRepository.save(user);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/get")
    public ResponseEntity<List<UserEntity>> listarUsuarios(){
        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping("/a")
    public String s(){
        return "hola";
    }
}
