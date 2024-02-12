package com.inventory.inventorymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventory.inventorymanagement.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

}
