package com.ecommerce.controller;

import com.ecommerce.dto.OrderDetailRequest;
import com.ecommerce.dto.ProductDTO;
import com.ecommerce.entity.CartEntity;
import com.ecommerce.entity.OrderDetailEntity;
import com.ecommerce.entity.ProductEntity;
import com.ecommerce.entity.UserEntity;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.repository.CartRepository;
import com.ecommerce.repository.OrderDetailRepository;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cart")
public class CartController {

@Autowired
private UserRepository userRepository;

@Autowired
private CartRepository cartRepository;

@Autowired
private OrderDetailRepository orderDetailRepository;

@Autowired
private ProductRepository productRepository;

/*
    @GetMapping("/get")
    public ResponseEntity<CartEntity> getCart(Authentication authentication){
        buscar y comparar id de usuario autenticado con la del pathvariable
       UserDto currentUser = Mappers.userToUserDto(userRepository.findByUsername(authentication.getName()).orElseThrow(
               () -> new ResourceNotFoundException("No user was found with username" + authentication.getName())));
        UserEntity currentUser = userRepository.findByUsername(authentication.getName()).orElseThrow(
                () -> new ResourceNotFoundException("No user was found with username" + authentication.getName()));
        System.out.println("details");
        System.out.println(authentication.getDetails());
        System.out.println("id");
        System.out.println(currentUser.getId());

        return null;
    }
*/
    @PostMapping("/post")
    public ResponseEntity<String> postCart(Authentication auth, @RequestBody List<OrderDetailRequest> product){
        UserEntity currentUser = userRepository.findByUsername(auth.getName()).orElseThrow(
                ()-> new ResourceNotFoundException("notfound")
        );

        List<Long> p = product.stream().map(a -> a.productId()).collect(Collectors.toList());

        List<ProductEntity> products = productRepository.findAllById(p);

        CartEntity cart = new CartEntity();
        cart.setTotalPrice(new BigDecimal("12.34"));
        cart.setCartProducts(products);
        cart.setCartOwner(currentUser);
        currentUser.setCart(cart);


        userRepository.save(currentUser);

        return ResponseEntity.ok("Carrito creado con exito");
    }
}
