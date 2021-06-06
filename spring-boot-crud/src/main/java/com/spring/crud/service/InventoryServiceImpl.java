package com.spring.crud.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.crud.model.dto.InventoryDTO;
import com.spring.crud.model.entity.Inventory;
import com.spring.crud.repository.InventoryRepository;

@Service
public class InventoryServiceImpl implements InventoryService {

	@Autowired
	private InventoryRepository inventoryRepository;

	@Override
	public List<InventoryDTO> getAllInventories() {
		List<Inventory> inventories = inventoryRepository.findAll();
		return inventories.stream().map(empl -> copyInventoryEntityToDto(empl)).collect(Collectors.toList());
	}

	@Override
	public void saveInventory(InventoryDTO inventoryDTO) {
		inventoryRepository.save(copyInventoryDtoToEntity(inventoryDTO));
	}

	@Override
	public InventoryDTO getInventoryById(long id) {
		Optional<Inventory> optional = inventoryRepository.findById(id);
		InventoryDTO inventoryDTO = null;
		if (optional.isPresent()) {
			inventoryDTO = copyInventoryEntityToDto(optional.get());
		} else {
			throw new RuntimeException(" Inventory not found for id :: " + id);
		}
		return inventoryDTO;
	}

	@Override
	public void deleteInventoryById(long id) {
		this.inventoryRepository.deleteById(id);
	}

	public Inventory copyInventoryDtoToEntity(InventoryDTO inventoryDTO) {
		Inventory inventory = new Inventory();
		BeanUtils.copyProperties(inventoryDTO, inventory);
		return inventory;
	}

	public InventoryDTO copyInventoryEntityToDto(Inventory inventory) {
		InventoryDTO inventoryDTO = new InventoryDTO();
		BeanUtils.copyProperties(inventory, inventoryDTO);
		return inventoryDTO;
	}

}
