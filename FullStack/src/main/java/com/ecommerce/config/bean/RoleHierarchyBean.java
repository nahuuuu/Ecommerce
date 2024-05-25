package com.ecommerce.config.bean;

// TODO: 27/4/2024
/*Probablemente deba crear una clase global que
maneje to-do tipo beans, para no crear clases por beans
*/

import org.springframework.context.annotation.Bean;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.stereotype.Component;

@Component
public class RoleHierarchyBean {

    //Roles - setea los permisos de otros roles segun su jerarquia (de izq a der)
    @Bean
    public RoleHierarchy hierarchy(){
        RoleHierarchyImpl hierarchy = new RoleHierarchyImpl();
        hierarchy.setHierarchy("ROLE_SUPERUSER > ROLE_USER > ROLE_GUEST");
        return hierarchy;
    }

}
