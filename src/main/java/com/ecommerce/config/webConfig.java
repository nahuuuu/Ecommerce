package com.ecommerce.config;

import com.ecommerce.config.filter.JwtAuthenticationFilter;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class webConfig {

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
         http
                .csrf(csrf-> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                 .authenticationProvider(authenticationProvider)
                 .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authConfig -> {
                    authConfig.requestMatchers(HttpMethod.POST, "/post").permitAll();
                    authConfig.requestMatchers(HttpMethod.GET, "/get").permitAll();
                    authConfig.requestMatchers(HttpMethod.GET, "/a").permitAll();
                    authConfig.requestMatchers(HttpMethod.POST, "/test-r-p").permitAll();
                    authConfig.requestMatchers(HttpMethod.GET, "/p-get").permitAll();
                    authConfig.requestMatchers(HttpMethod.POST, "/crear-usuario").permitAll();
                    authConfig.requestMatchers(HttpMethod.POST, "/api/login").permitAll();
                    authConfig.requestMatchers(HttpMethod.POST, "/promote-user").permitAll();
                    //products
                    authConfig.requestMatchers(HttpMethod.GET, "/api/get-products").permitAll();
                    authConfig.requestMatchers(HttpMethod.POST, "/api/post-products").permitAll();
                    authConfig.requestMatchers(HttpMethod.POST, "/api/post-one-products").permitAll();
                    //validate Token
                    authConfig.requestMatchers(HttpMethod.POST, "/api/check-token").authenticated();
                    authConfig.requestMatchers(HttpMethod.GET, "/test").permitAll();

                    //controlador carrito test
                    authConfig.requestMatchers(HttpMethod.GET, "api/cart/get").permitAll();
                    authConfig.requestMatchers(HttpMethod.POST, "api/cart/post").permitAll();
                });

         return http.build();

    }
}
