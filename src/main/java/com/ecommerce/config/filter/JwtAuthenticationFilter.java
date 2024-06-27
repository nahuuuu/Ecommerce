package com.ecommerce.config.filter;

import com.ecommerce.entity.UserEntity;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.service.auth.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        List<String> publicEndpoints = Arrays.asList("/api/get-products");




            // Obtener header del jwt
            String authHeader = request.getHeader("Authorization"); //Bearer jwt

            /*if(authHeader == null || !authHeader.startsWith("Bearer ")){
                filterChain.doFilter(request, response);
                return;
            }*/

        if (authHeader != null && authHeader.startsWith("Bearer ")){


            // Obtener jwt del header
            String jwt = authHeader.split(" ")[1];

            try {

            // Obtener verificar validez del token - retornar jwt - subject/username del jwt
                Claims payload = jwtService.getPayload(jwt);

                UserEntity user = userRepository.findByEmail(payload.getSubject()).get();

                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        payload.getSubject(), null, user.getAuthorities()
                );

                // Setear un Authentication dentro del SecurityContext
                SecurityContextHolder.getContext().setAuthentication(authToken);

                if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated()){
                    System.out.println("Esta autenticado" + SecurityContextHolder.getContext().getAuthentication());
                }

                // Ejecutar el resto de filtros
                filterChain.doFilter(request, response);

            }catch (JwtException e){
                if (publicEndpoints.contains(request.getRequestURI())){
                    filterChain.doFilter(request, response);
                    System.out.println("token invalido pero damos el acceso como anonimo al tratarse de un endpoint publico");
                    return;
                }
                System.out.println(e);
            }

        }else{
            filterChain.doFilter(request, response);
            //Authorization del header vacio o no comienza con bearer, si el endpoint es public devuelve recursos si no, no
            System.out.println("toy en el else del jwtfilter");
        }


    }
}
