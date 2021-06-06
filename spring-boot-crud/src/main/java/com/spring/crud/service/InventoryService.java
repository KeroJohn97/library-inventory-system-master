package com.spring.crud.service;

import java.util.List;

import com.spring.crud.model.dto.InventoryDTO;

public interface InventoryService {
	
	List<InventoryDTO> getAllInventories();

	void saveInventory(InventoryDTO inventoryDTO);

	InventoryDTO getInventoryById(long id);

	void deleteInventoryById(long id);
}
