package com.ecommerce.controller.test;

import com.ecommerce.dto.Pagination;
import com.ecommerce.dto.ProductDTO;
import com.ecommerce.entity.ProductEntity;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class ProductControllerTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductServiceImpl productService;


    @GetMapping("/api/get-products")
    public ResponseEntity<List<ProductDTO>> loadAllProducts(Authentication authentication, @RequestBody Pagination pagination){

        return ResponseEntity.ok(productService.getAllProducts(pagination, authentication));
    }

    @PostMapping("/api/post-products")
    public ResponseEntity<String> uploadProducts(@RequestBody List<ProductEntity> product){
        productRepository.saveAll(product);
        return ResponseEntity.ok("Created");
    }

    @PostMapping("/api/post-one-products")
    public ResponseEntity<String> uploadProduct(@RequestBody ProductEntity product){
        productRepository.save(product);
        return ResponseEntity.ok("Created");
    }

    @GetMapping("/api/get-products-test")
    public ResponseEntity<List<ProductEntity>> test(){
        return ResponseEntity.ok(productRepository.findAll());}
}
