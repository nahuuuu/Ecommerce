package com.ecommerce.controller.test;

import com.ecommerce.dto.auth.AuthenticationRequest;
import com.ecommerce.entity.UserEntity;
import com.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class BORRAR {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/test")
    public ResponseEntity<UserEntity> obtenerRoles (@RequestBody AuthenticationRequest name){

        System.out.println( userRepository.findByUsername(name.username()).get().getAuthorities());
        return  ResponseEntity.ok(userRepository.findByUsername(name.username()).get());
    }
}
