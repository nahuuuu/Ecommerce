package com.ecommerce.service.auth;

import com.ecommerce.dto.auth.AuthenticationRequest;
import com.ecommerce.dto.auth.RegisterRequest;
import com.ecommerce.dto.auth.AuthenticationResponse;
import com.ecommerce.entity.RoleEntity;
import com.ecommerce.entity.UserEntity;
import com.ecommerce.repository.RoleRepository;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.service.impl.UserServiceImpl;
import com.ecommerce.util.Role;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private JwtService jwtService;

    public AuthenticationResponse register(RegisterRequest request){

        String userEmail = userService.createUser(request);

        String jwtToken = jwtService.generateToken(userEmail);

        return new AuthenticationResponse(jwtToken);

    }

    public AuthenticationResponse authenticate(AuthenticationRequest request){

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                request.email(), request.password()
        );

        authenticationManager.authenticate(authToken);

        String jwtToken = jwtService.generateToken(request.email());

        return new AuthenticationResponse(jwtToken);
    }

    public void obtainAuth(){
         Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        System.out.println(principal);
        System.out.println(SecurityContextHolder.getContext().getAuthentication());
        if (principal instanceof UserEntity){
            System.out.println("es user entity");
        }else {
            System.out.println("no es user entity");
        }

            }



}
