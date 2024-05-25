package com.ecommerce.controller.test;

import com.ecommerce.dto.auth.AuthenticationRequest;
import com.ecommerce.dto.auth.AuthenticationResponse;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.service.auth.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AuthControllerTest {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/api/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authRequest){

        AuthenticationResponse jwt = authenticationService.login(authRequest);

        return ResponseEntity.ok(jwt);
    }

    @Autowired
    private UserRepository userRepository;


}
