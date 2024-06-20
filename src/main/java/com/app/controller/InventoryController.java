package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.exceptions.InventoryException;
import com.app.model.Inventory;
import com.app.services.InventoryService;



@RestController
@RequestMapping("/inventory")
public class InventoryController {
	
	@Autowired
	private InventoryService inventoryService;
	
	@PostMapping("/addItem")
	public ResponseEntity<Inventory> addInventoryEntity(@RequestBody Inventory inventory) throws InventoryException{
		Inventory addInventory = inventoryService.addInventory(inventory);
		return new ResponseEntity<Inventory>(addInventory, HttpStatus.CREATED);
	}
	@GetMapping("/getAll")
	public ResponseEntity<List<Inventory>>getAllItemsEntity()throws InventoryException{
		List<Inventory>allInventories=inventoryService.viewAllInventoriesItems();
		return new ResponseEntity<List<Inventory>>(allInventories,HttpStatus.OK);
	}
	@PutMapping("/update")
	public ResponseEntity<Inventory>updateInventoryEntity(@RequestBody Inventory inventory)throws InventoryException{
		Inventory updateInventory = inventoryService.updaInventory(inventory);
		return new ResponseEntity<Inventory>(updateInventory, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/getItem/{itemId}")
	public ResponseEntity<Inventory>getItemEntity(@PathVariable("itemId") Integer itemId)throws InventoryException{
		Inventory existingItemInventory = inventoryService.viewInventoryItem(itemId);
		return new ResponseEntity<Inventory>(existingItemInventory, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteItem")
	public ResponseEntity<Inventory> deleteItem(@RequestParam Integer itemId)throws InventoryException{
		Inventory deleteItemInventory = inventoryService.removInventory(itemId);
		return new ResponseEntity<Inventory>(deleteItemInventory,HttpStatus.OK);
	}
	
	@GetMapping("/category/{categoryName}")
	public ResponseEntity<List<Inventory>> getItemByCategory(@PathVariable("categoryName") String categoryName) throws InventoryException{
		List<Inventory>categoryWiseInventories = inventoryService.viewInventryByCategory(categoryName);
		return new ResponseEntity<List<Inventory>>(categoryWiseInventories, HttpStatus.OK);
	}
}
