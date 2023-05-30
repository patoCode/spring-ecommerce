package com.posgrado.ecommerce.exception;


import lombok.Getter;

import java.util.Collection;

@Getter
public class RoleNameAlreadyExists extends RuntimeException{

    private static final String ERROR_MESSAGE = "Role with name %s is already exists";
    private static final String CUSTOM_MSG = "Role Name Already Exists";
    public RoleNameAlreadyExists(String name) {
        super(String.format(ERROR_MESSAGE, name));
    }

}