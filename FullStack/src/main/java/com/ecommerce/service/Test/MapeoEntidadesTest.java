package com.ecommerce.service.Test;

import com.ecommerce.dto.UserDto;
import com.ecommerce.entity.PermissionEntity;
import com.ecommerce.entity.RoleEntity;
import com.ecommerce.entity.UserEntity;
import com.ecommerce.repository.PermissionRepository;
import com.ecommerce.repository.RoleRepository;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.util.Permission;
import com.ecommerce.util.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MapeoEntidadesTest {

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    //Logica de mapeo de permisos
    private Set<PermissionEntity> mapPermissionsFromEnum(Set<Permission> permissions){
        return Arrays.stream(Permission.values()).map(
                pEnum -> new PermissionEntity(pEnum)).collect(Collectors.toSet());
    }


    //Seteo de los roles y permisos los cuales solo deben ser cargados una vez
    public void guardarRolesYPermisos(){
        Set<PermissionEntity> permisos = mapPermissionsFromEnum(EnumSet.allOf(Permission.class));

        permissionRepository.saveAll(permisos);

        List<RoleEntity> roles = Arrays.stream(Role.values()).map(rEnum ->{
            RoleEntity role = new RoleEntity(rEnum);
            Set<PermissionEntity> permisosAsignados = rEnum.getPermission().stream()
                .map(pEnum -> permisos.stream()
                        .filter(p -> p.getPermissions() == pEnum)
                        .findFirst()
                        .orElseThrow(() -> new IllegalStateException("Permiso no encontrado: " + pEnum)))
                .collect(Collectors.toSet());

        role.setPermissions(permisosAsignados);
        return role;
    }).toList();
        roleRepository.saveAll(roles);
    }



    //Obtencion de los roles como entidades y asignacion del mismo al usuario
    // otorgado creando asi la tabla intermediaria user_role.
    public void creacionRole_User(UserEntity user){

        RoleEntity role = roleRepository.findByrole(Role.USER);
        user.getRole().add(role);
        userRepository.save(user);

    }

    //Promover / cambiar rol a un usuario

    public String cambiarRol(String username){

        UserEntity user = userRepository.findByUsername(username).orElseThrow(
                () -> new RuntimeException("User Not Found"));

        user.getRole().add(roleRepository.findByrole(Role.SUPERUSER));

        userRepository.save(user);

        //Creacion del dto
        UserDto dto = new UserDto(user.getId(), user.getUsername(), user.getEmail(),
                user.getPassword(), user.getRole());
        return "Rol cambiado a " + dto.getRoles() + " exitosamente";
    }
}
