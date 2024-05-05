package com.ecommerce.controller.test;


import com.ecommerce.entity.UserEntity;
import com.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class RegisterControllerTest {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/post")
    public ResponseEntity<UserEntity> crearUsuario(@RequestBody UserEntity user){
        userRepository.save(user);
        return ResponseEntity.ok(user);
    }


}
