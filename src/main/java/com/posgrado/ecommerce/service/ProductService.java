package com.posgrado.ecommerce.service;

import com.posgrado.ecommerce.dto.PageDto;
import com.posgrado.ecommerce.dto.ProductDto;
import com.posgrado.ecommerce.entity.Product;
import java.util.UUID;

import com.posgrado.ecommerce.util.SortType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

  String FIELD_BY_SORT_CATEGORY = "name";

  Product create(ProductDto dto);

  Product getById(UUID id);

  Page<Product> getProducts(Pageable pageable);

  PageDto<Product> getFilteredProducts(Double minPrice, Double maxPrice, Pageable pageable);

  Product update(ProductDto dto, UUID id);

  PageDto<Product> getByCategory(Integer page, Integer size, SortType sort, UUID id);

}