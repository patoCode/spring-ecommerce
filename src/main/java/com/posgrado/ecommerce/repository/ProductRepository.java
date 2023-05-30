package com.posgrado.ecommerce.repository;

import com.posgrado.ecommerce.entity.Product;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

  Page<Product> findByPriceBetween(Double minPrice, Double maxPrice, Pageable pageable);

  @Query("SELECT p FROM Product p WHERE p.category.id = :id")
  Page<Product> findByCategory(@Param("id") UUID id, Pageable pageable);
}