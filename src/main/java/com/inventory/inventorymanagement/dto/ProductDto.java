package com.inventory.inventorymanagement.dto;

import com.inventory.inventorymanagement.model.Category;

import lombok.Data;

@Data
public class ProductDto {
	private String productName;
	private String productDescription;
	private String productBrand;
	private long productPrice;
	private Category category;
}
