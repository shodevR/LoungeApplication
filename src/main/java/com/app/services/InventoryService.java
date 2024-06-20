package com.app.services;

import java.util.List;

import com.app.exceptions.InventoryException;
import com.app.model.Inventory;

public interface InventoryService {

	public List<Inventory>viewAllInventoriesItems() throws InventoryException;
	public Inventory addInventory(Inventory inventory)throws InventoryException;
	public Inventory updaInventory(Inventory inventory)throws InventoryException;
	public Inventory viewInventoryItem(Integer id)throws InventoryException;
	public List<Inventory>viewInventryByCategory(String cname)throws InventoryException;
	public Inventory removInventory(Integer Iid)throws InventoryException;
	
}
