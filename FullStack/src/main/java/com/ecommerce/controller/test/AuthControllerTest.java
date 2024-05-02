package com.ecommerce.controller.test;

import com.ecommerce.dto.auth.AuthenticationRequest;
import com.ecommerce.dto.auth.AuthenticationResponse;
import com.ecommerce.entity.UserEntity;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.service.AuthenticationService;
import com.ecommerce.service.Test.MapeoEntidadesTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AuthControllerTest {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MapeoEntidadesTest mapeoEntidadesTest;


    @PostMapping("/api/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authRequest){

        AuthenticationResponse jwt = authenticationService.login(authRequest);

        return ResponseEntity.ok(jwt);
    }

    @PostMapping("/crear-usuario")
    public ResponseEntity<String> crearUsuario(@RequestBody UserEntity user){

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        mapeoEntidadesTest.creacionRole_User(user);
        return ResponseEntity.ok("Usuario creado");
    }

    @GetMapping("/autenticado")
    public ResponseEntity<String> autenticado(){
        System.out.println("Usuario autenticado");
        return ResponseEntity.ok("ola si, esta autenticado");
    }

    @Autowired
    private UserRepository userRepository;


}
