package com.inventory.inventorymanagement.exception;

import lombok.Data;

@Data
public class ProductNotFoundException extends RuntimeException{

	public ProductNotFoundException(String message) {
		super(message);
	}

}
