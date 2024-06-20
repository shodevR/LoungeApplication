package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.exceptions.ClientException;
import com.app.model.Client;
import com.app.services.ClientService;

@RestController
@RequestMapping("/api/client")
public class ClientController {

	@Autowired
	private ClientService clientService;
	@PostMapping("/add")
	public ResponseEntity<Client> addClientEntity(@RequestBody Client client) throws ClientException{
		Client addClient = clientService.addClient(client);
		return new ResponseEntity<Client>(addClient, HttpStatus.CREATED);
	}
	@GetMapping("/getAll")
	public ResponseEntity<List<Client>>getAllItemsEntity()throws ClientException{
		List<Client>allClients=clientService.viewAllClient();
		return new ResponseEntity<List<Client>>(allClients,HttpStatus.OK);
	}
}
