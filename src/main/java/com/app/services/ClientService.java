package com.app.services;

import java.util.List;



import com.app.exceptions.ClientException;

import com.app.model.Client;


public interface ClientService {

	public List<Client>viewAllClient() throws ClientException;
	public Client addClient(Client client) throws ClientException;
	public Client removeClient(Integer clientId) throws ClientException;
	
}
