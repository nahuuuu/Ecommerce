/*
package com.ecommerce.controller;

import com.ecommerce.dto.Pagination;
import com.ecommerce.dto.UserDto;
import com.ecommerce.entity.UserEntity;
import com.ecommerce.service.impl.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/get-all")
    public ResponseEntity<List<UserDto>> getAllUsers(@Valid Pagination pagination){

        List<UserDto> userList = userService.getAllUsers(pagination);
        return ResponseEntity.ok(userList);
    }
}*/
