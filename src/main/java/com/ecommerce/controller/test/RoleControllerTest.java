package com.ecommerce.controller.test;

import com.ecommerce.entity.PermissionEntity;
import com.ecommerce.entity.UserEntity;
import com.ecommerce.repository.PermissionRepository;
import com.ecommerce.service.Test.MapeoEntidadesTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class RoleControllerTest {

    @Autowired
    private MapeoEntidadesTest mapeoEntidadesTest;
    @Autowired
    private PermissionRepository permissionRepository;
    @PostMapping("/test-r-p")
    public ResponseEntity<String> permisos(){
        mapeoEntidadesTest.guardarRolesYPermisos();
        return ResponseEntity.ok("exito");
    }

    @GetMapping("/p-get")
    public ResponseEntity<List<PermissionEntity>> permisosget(){
        return ResponseEntity.ok(permissionRepository.findAll());
    }

    @PostMapping("/crear-usuario")
    public ResponseEntity<String> crearUsuario(@RequestBody UserEntity user){
        mapeoEntidadesTest.creacionRole_User(user);
        return ResponseEntity.ok("Usuario creado");
    }
}
