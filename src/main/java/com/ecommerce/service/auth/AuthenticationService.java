package com.ecommerce.service.auth;

import com.ecommerce.dto.auth.AuthenticationRequest;
import com.ecommerce.dto.auth.AuthenticationResponse;
import com.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;



    @Autowired
    private JwtService jwtService;

    public AuthenticationResponse login(AuthenticationRequest authRequest){

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
          authRequest.username(), authRequest.password()
        );


        authenticationManager.authenticate(authToken);

        String user = authRequest.username();

        String jwt = jwtService.generateToken(user);

        return new AuthenticationResponse(jwt);

    }
}
