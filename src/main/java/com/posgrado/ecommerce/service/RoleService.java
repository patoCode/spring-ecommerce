package com.posgrado.ecommerce.service;

import com.posgrado.ecommerce.dto.ProductDto;
import com.posgrado.ecommerce.dto.RoleDto;
import com.posgrado.ecommerce.entity.Product;
import com.posgrado.ecommerce.entity.Role;
import java.util.List;

public interface RoleService {

  Role create(RoleDto dto);

  Role getByName(String name);

  List<Role> getAll();

  boolean existName(String name);
}