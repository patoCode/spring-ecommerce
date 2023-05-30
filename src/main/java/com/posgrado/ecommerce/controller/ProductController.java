package com.posgrado.ecommerce.controller;

import com.posgrado.ecommerce.dto.PageDto;
import com.posgrado.ecommerce.dto.ProductDto;
import com.posgrado.ecommerce.entity.Product;
import com.posgrado.ecommerce.service.ProductService;
import com.posgrado.ecommerce.util.SortType;
import jakarta.validation.Valid;

import java.util.Objects;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {

  private ProductService productService;

  @PostMapping
  public ResponseEntity<Product> create(@Valid @RequestBody ProductDto dto) {
    Product productSaved = productService.create(dto);
    return ResponseEntity.status(HttpStatus.CREATED).body(productSaved);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Product> getById(@PathVariable UUID id) {
    Product productFound = productService.getById(id);
    return ResponseEntity.status(HttpStatus.OK).body(productFound);
  }

  @GetMapping("/pageable")
  public ResponseEntity<Page<Product>> getProducts(@RequestParam int page, @RequestParam int size) {
    Pageable pageable = PageRequest.of(page, size);
    Page<Product> productPage = productService.getProducts(pageable);
    return ResponseEntity.status(HttpStatus.OK).body(productPage);
  }

  @GetMapping
  public ResponseEntity<PageDto<Product>> getFilteredProducts(
      @RequestParam(required = false) Double minPrice,
      @RequestParam(required = false) Double maxPrice,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "20") int size,
      @RequestParam(defaultValue = "id") String sortField,
      @RequestParam(defaultValue = "asc") String sortOrder
  ) {
    if (minPrice == null) {
      minPrice = Double.MIN_VALUE;
    }
    if (maxPrice == null) {
      maxPrice = Double.MAX_VALUE;
    }
    Sort sort = Sort.by(Sort.Direction.fromString(sortOrder), sortField);
    Pageable pageable = PageRequest.of(page, size, sort);
    PageDto<Product> productPage = productService.getFilteredProducts(minPrice, maxPrice, pageable);
    return ResponseEntity.status(HttpStatus.OK).body(productPage);
  }

  /*
    US-01
    Create an endpoint to update a product by ID
  */

  @PutMapping("/{id}")
  public ResponseEntity<Product> update(@RequestBody ProductDto dto, @PathVariable UUID id){
    Product productSaved = productService.update(dto, id);
    return ResponseEntity.status(HttpStatus.OK).body(productSaved);
  }

  /*
    US-02
    Create an endpoint to get products by category id
    Nice to have: The response should retrieve a list of products with pagination.
   */
  @GetMapping("/category/{id}")
  public ResponseEntity<PageDto<Product>> getByCategory(@PathVariable UUID id,
                                                        @RequestParam(defaultValue = "0")  Integer page,
                                                        @RequestParam(defaultValue = "10")  Integer size,
                                                        @RequestHeader SortType sort){
    if(Objects.isNull(sort)) sort = SortType.NONE;
    PageDto<Product> productPage = productService.getByCategory(page, size, sort, id);
    return ResponseEntity.status(HttpStatus.OK).body(productPage);
  }

}