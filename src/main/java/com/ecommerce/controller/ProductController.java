package com.ecommerce.controller;

import com.ecommerce.dto.Pagination;
import com.ecommerce.dto.ProductDTO;
import com.ecommerce.dto.ProductRequest;
import com.ecommerce.entity.ProductEntity;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductServiceImpl productService;


    private final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    @PostMapping("/get-all")
    public ResponseEntity<List<ProductDTO>> loadAllProducts(@RequestBody Pagination pagination) {

        return ResponseEntity.ok(productService.getAllProducts(pagination, authentication));
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadProduct(@RequestBody ProductRequest product) {

        productService.createProduct(product, authentication);
        return ResponseEntity.ok("product uploaded");
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductDTO>> searchByName(@RequestParam(name = "productName") String productName){
        return ResponseEntity.ok(productService.searchProductsByTitle(productName));
    }

    /*@DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@RequestParam Long id){
        productService.deleteProduct();
    }*/

}

