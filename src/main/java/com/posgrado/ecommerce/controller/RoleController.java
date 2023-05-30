package com.posgrado.ecommerce.controller;

import com.posgrado.ecommerce.dto.ProductDto;
import com.posgrado.ecommerce.dto.RoleDto;
import com.posgrado.ecommerce.entity.Role;
import com.posgrado.ecommerce.exception.RoleNameAlreadyExists;
import com.posgrado.ecommerce.service.RoleService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/roles")
public class RoleController {

  private RoleService roleService;

  @GetMapping("/name/{name}")
  public ResponseEntity<Role> getByName(@PathVariable String name) {
    Role roleFound = roleService.getByName(name);
    return ResponseEntity.status(HttpStatus.OK).body(roleFound);
  }

  @GetMapping
  public ResponseEntity<List<Role>> getAll() {
    List<Role> roles = roleService.getAll();
    return ResponseEntity.ok(roles);
  }


/*
  US-03
  Create an endpoint to save a new role
*/
  @PostMapping
  public ResponseEntity create(@RequestBody RoleDto dto){
    boolean existName = roleService.existName(dto.getName());
    if ( existName ) {
      throw new RoleNameAlreadyExists(dto.getName());
    }
    Role _role = roleService.create(dto);
    return ResponseEntity.status(HttpStatus.CREATED).body(_role);
  }

}