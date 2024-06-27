package com.ecommerce.config;

import com.ecommerce.config.filter.JwtAuthenticationFilter;
import com.ecommerce.util.Role;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.Customizer;
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
                    //AUTH
                    // Login
                    authConfig.requestMatchers(HttpMethod.POST, "/api/auth/login").anonymous();
                    // Register
                    authConfig.requestMatchers(HttpMethod.POST, "/api/auth/register").anonymous();
                    //validate Token
                    authConfig.requestMatchers(HttpMethod.POST, "/api/check-token").authenticated();

                    //ENTITIES
                    //products
                    authConfig.requestMatchers(HttpMethod.GET, "/api/product/get-all").permitAll();
                    authConfig.requestMatchers(HttpMethod.POST, "/api/product/upload").hasRole(Role.USER.toString());
                    authConfig.requestMatchers(HttpMethod.PUT,"/api/product/change").authenticated();
                    authConfig.requestMatchers(HttpMethod.DELETE, "/api/product/delete{id}").authenticated();
                    authConfig.requestMatchers(HttpMethod.GET, "api/product/search").permitAll();

                    //TEST
                    authConfig.requestMatchers(HttpMethod.POST, "/test").permitAll();
                    authConfig.requestMatchers(HttpMethod.GET, "/test-2").permitAll();
                    authConfig.requestMatchers(HttpMethod.GET,"/secret").authenticated();
                    //controlador carrito test
                    authConfig.requestMatchers(HttpMethod.GET, "api/cart/get").permitAll();
                    authConfig.requestMatchers(HttpMethod.POST, "api/cart/post").permitAll();

                    //upload imageTest
                    authConfig.requestMatchers(HttpMethod.POST, "/upload-picture").permitAll();



                })
                 //.oauth2Login(Customizer.withDefaults())
                 .anonymous(config -> config

                         .principal("guest")
                         .authorities("ROLE_GUEST"));

         return http.build();

    }
}
