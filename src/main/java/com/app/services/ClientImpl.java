package com.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.exceptions.ClientException;
import com.app.exceptions.InventoryException;
import com.app.model.Client;
import com.app.model.Inventory;
import com.app.repository.ClientRepo;

@Service
public class ClientImpl implements ClientService{

	@Autowired
	private ClientRepo clientRepo;
	
	@Override
	public List<Client> viewAllClient() throws ClientException {
		List<Client>list = clientRepo.findAll();
		
		if (list.size()>0) {
			return list;
		}else {
			throw new ClientException("No Clients Found");
		}	
	}

	@Override
	public Client addClient(Client client) throws ClientException {
		Client client2 = clientRepo.save(client);
		return client2;
	}

	@Override
	public Client removeClient(Integer clientId) throws ClientException {
		Client client = clientRepo.findById(clientId).orElseThrow(()->new ClientException("Client does not exist with id :"+clientId));
		
		clientRepo.delete(client);
		return client;	
	}

}
