package com.inventory.inventorymanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.inventory.inventorymanagement.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	@Query(value = "SELECT * FROM product WHERE category_category_id = :categoryId", nativeQuery = true)
	List<Product> findByCategoryId(@Param("categoryId") long categoryId);
}
