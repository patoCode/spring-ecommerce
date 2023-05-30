package com.posgrado.ecommerce.exception;

public class RoleNameAlreadyExists extends RuntimeException{

    private static final String ERROR_MESSAGE = "Role with name %s is already exists";
    public RoleNameAlreadyExists(String name) {
        super(String.format(ERROR_MESSAGE, name));
    }

}