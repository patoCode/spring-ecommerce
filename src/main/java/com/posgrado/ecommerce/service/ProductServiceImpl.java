package com.posgrado.ecommerce.service;

import com.posgrado.ecommerce.dto.PageDto;
import com.posgrado.ecommerce.dto.ProductDto;
import com.posgrado.ecommerce.entity.Category;
import com.posgrado.ecommerce.entity.Product;
import com.posgrado.ecommerce.exception.EntityNotFoundException;
import com.posgrado.ecommerce.mapper.ProductMapper;
import com.posgrado.ecommerce.repository.ProductRepository;
import java.util.UUID;

import com.posgrado.ecommerce.util.SortType;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

  private ProductRepository productRepository;
  private CategoryService categoryService;

  private ProductMapper productMapper;

  @Override
  public Product create(ProductDto dto) {
    Category category = categoryService.getById(dto.getCategoryId());
    Product product = productMapper.fromDto(dto);
    product.setCategory(category);
    return productRepository.save(product);
  }

  @Override
  public Product getById(UUID id) {
    return productRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Product", id));
  }

  @Override
  public Page<Product> getProducts(Pageable pageable) {
    return productRepository.findAll(pageable);
  }

  @Override
  public PageDto<Product> getFilteredProducts(Double minPrice, Double maxPrice, Pageable pageable) {
    Page<Product> page = productRepository.findByPriceBetween(minPrice, maxPrice, pageable);
    return productMapper.fromEntity(page);
  }

  @Override
  public Product update(ProductDto dto, UUID id) {
    Product _productDB = productRepository.findById(id)
                          .orElseThrow(() -> new EntityNotFoundException("Product", id));
    _productDB.setName(dto.getName());
    _productDB.setDescription(dto.getDescription().trim());
    _productDB.setPrice(dto.getPrice());
    _productDB.setStock(dto.getStock());
    return productRepository.save(_productDB);
  }

  @Override
  public PageDto<Product> getByCategory(Integer page_number, Integer size, SortType sort, UUID id) {
    PageRequest pageRequest = null;
    switch (sort){
      case NONE -> pageRequest = PageRequest.of(page_number, size);
      case DESC -> pageRequest = PageRequest.of(page_number, size, Sort.by(FIELD_BY_SORT_CATEGORY).ascending());
      case ASC -> pageRequest = PageRequest.of(page_number, size, Sort.by(FIELD_BY_SORT_CATEGORY).descending());
    }
    Page<Product> page = productRepository.findByCategory(id, pageRequest);
    return productMapper.fromEntity(page);
  }
}