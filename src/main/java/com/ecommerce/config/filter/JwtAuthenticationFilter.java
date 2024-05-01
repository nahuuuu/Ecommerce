package com.ecommerce.config.filter;

import com.ecommerce.entity.UserEntity;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.service.Test.JwtService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // Obtener header del jwt
        String authHeader = request.getHeader("Authorization"); //Bearer jwt

        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }

        // Obtener jwt del header
        String jwt = authHeader.split(" ")[1];

        // Obtener subject/username del jwt
        Claims username = jwtService.getPayload(jwt);

        // Setear un Authentication dentro del SecurityContext

        UserEntity user = userRepository.findByUsername(username.getSubject()).get();

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                username.getSubject(), null, user.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(authToken);

        // Ejecutar el resto de filtros
        filterChain.doFilter(request, response);

    }
}
