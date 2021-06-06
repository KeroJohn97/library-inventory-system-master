package com.spring.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.crud.model.dto.InventoryDTO;
import com.spring.crud.model.entity.Inventory;
import com.spring.crud.service.InventoryService;

@Controller
public class InventoryController {

	@Autowired
	private InventoryService inventoryService;

	// display list of inventories
	@GetMapping("/")
	public ModelAndView viewHomePage() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("listInventories", inventoryService.getAllInventories());
		modelAndView.setViewName("index");
		return modelAndView;
	}

	// create model attribute to bind form data
	@GetMapping("/showNewInventoryForm")
	public ModelAndView showNewInventoryForm() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("inventoryDTO", new InventoryDTO());
		modelAndView.setViewName("new_inventory");
		return modelAndView;
	}

	// save inventory to database
	@PostMapping("/saveInventory")
	public String saveInventory(@ModelAttribute("inventoryDTO") InventoryDTO inventoryDTO) {
		inventoryService.saveInventory(inventoryDTO);
		return "redirect:/";
	}

	@GetMapping("/showFormForUpdate/{id}")
	public ModelAndView showFormForUpdate(@PathVariable(value = "id") long id) {
		ModelAndView modelAndView = new ModelAndView();
		// get inventory from the service
		InventoryDTO inventoryDTO = inventoryService.getInventoryById(id);

		// set inventory as a model attribute to pre-populate the form
		modelAndView.addObject("inventoryDTO", inventoryDTO);
		modelAndView.setViewName("update_inventory");
		return modelAndView;
	}

	@GetMapping("/deleteInventory/{id}")
	public String deleteInventory(@PathVariable(value = "id") long id) {

		// call delete inventory method
		this.inventoryService.deleteInventoryById(id);
		return "redirect:/";
	}

}
