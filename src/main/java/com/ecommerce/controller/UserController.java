
package com.ecommerce.controller;

import com.ecommerce.dto.Pagination;
import com.ecommerce.dto.UserDTO;
import com.ecommerce.service.impl.UserServiceImpl;
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
    private UserServiceImpl userService;

    @GetMapping("/get-all")
    public ResponseEntity<List<UserDTO>> getAllUsers(@Valid Pagination pagination){

        List<UserDTO> userList = userService.getAllUsers(pagination);
        return ResponseEntity.ok(userList);
    }
}
