package com.ecommerce.util;

import lombok.Getter;

import java.util.Set;

@Getter
public enum Role {

    SUPERUSER(Set.of(Permission.SUPER_CREATE, Permission.SUPER_DELETE, Permission.SUPER_UPDATE)),

    USER(Set.of(Permission.CREATE_OWN, Permission.DELETE_OWN, Permission.UPDATE_OWN)),

    GUEST(Set.of(Permission.READ_ONLY));


    private Set<Permission> permission;

    Role(Set<Permission> permission){this.permission = permission;}

    public void setPermission(Set<Permission> permission) {
        this.permission = permission;
    }
}
