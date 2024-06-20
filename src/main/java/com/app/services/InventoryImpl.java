package com.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.exceptions.InventoryException;
import com.app.model.Inventory;
import com.app.repository.InventoryRepo;

@Service
public class InventoryImpl implements InventoryService{
	@Autowired
	private InventoryRepo iRepo;
	

	@Override
	public List<Inventory> viewAllInventoriesItems() throws InventoryException {
		List<Inventory>inventories = iRepo.findAll();
		if (inventories.size()>0) {
			return inventories;
			
		}else {
			throw new InventoryException("Inventory Empty");
		}
	}

	@Override
	public Inventory addInventory(Inventory inventory) throws InventoryException {
		Inventory addInventory=iRepo.save(inventory);
		return addInventory;
	}

	@Override
	public Inventory updaInventory(Inventory inventory) throws InventoryException {
		Optional<Inventory>optional = iRepo.findById(inventory.getItemId());
		if (optional.isPresent()) {
			return iRepo.save(inventory);
		}else {
			throw new InventoryException("Invalid Details");
		}
	}

	@Override
	public Inventory viewInventoryItem(Integer id) throws InventoryException {
		Optional<Inventory> optional = iRepo.findById(id);
		if (optional.isPresent()) {
			return optional.get();
			
		}else {
			throw new InventoryException("Invalid id");
		}
		
	}

	@Override
	public List<Inventory> viewInventryByCategory(String cname) throws InventoryException {
		
		  List<Inventory> inventories = iRepo.viewByCategoryName(cname);
		  if(inventories.size()>0) {
			  return inventories; 
			  }else {
				  throw new
				  	InventoryException("No items of this category"); 
				  }
		 
		
	}

	@Override
	public Inventory removInventory(Integer Iid) throws InventoryException {
		Inventory existingInventory = iRepo.findById(Iid).orElseThrow(()->new InventoryException("Item does not exist with id :"+Iid));
		
		iRepo.delete(existingInventory);
		return existingInventory;
	}
	

}
