package com.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.exceptions.TicketException;
import com.app.model.Ticket;
import com.app.services.TicketService;


import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/api/ticket")
public class TicketController {
	
	@Autowired
	private TicketService ticketService;
	
	@PostMapping("/addTicket")
	public ResponseEntity<Ticket>addTicketEntity(@RequestBody Ticket ticket)throws TicketException{
		
		Ticket addTicket = ticketService.addTicket(ticket);
		
		return new ResponseEntity<Ticket>(addTicket, HttpStatus.CREATED);
	}
	
	@PutMapping("/updateTicket")
	public ResponseEntity<Ticket>updateTicketEntity(@RequestBody Ticket ticket) throws TicketException {
		Ticket ticket2 = ticketService.updateTicket(ticket);
		return new ResponseEntity<Ticket>(ticket2, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/removeTicket")
	public ResponseEntity<Ticket>deleteTicketEntity(@RequestParam Integer id) throws TicketException{
		Ticket deleteTicket = ticketService.removeTicket(id);
		return new ResponseEntity<Ticket>(deleteTicket, HttpStatus.OK);
	}
	@GetMapping("/getAllTicket")
	public ResponseEntity<List<Ticket>> getAllTicketEntity() throws TicketException{
		List<Ticket>list = ticketService.viewAllTicket();
		return new ResponseEntity<List<Ticket>>(list, HttpStatus.OK);
	}
	@GetMapping("/details/{id}")
	public ResponseEntity<Ticket> getDetailsEntity(@PathVariable("id")Integer id) throws TicketException{
		Ticket ticket = ticketService.getTicketById(id);
		return new ResponseEntity<Ticket>(ticket, HttpStatus.OK);
	}
	@GetMapping("/count")
	public ResponseEntity<Map<String, Long>> getCount() {
		try {
			long totalTickets = ticketService.getTotalTickets();
			long voucherTickets = ticketService.getVoucherTickets();
			long partnerTickets = ticketService.getPartnerTickets();
			long airlineTickets = ticketService.getAirlineTickets();

			Map<String, Long> response = new HashMap<>();
			response.put("totalTickets", totalTickets);
			response.put("voucherTickets", voucherTickets);
			response.put("partnerTickets", partnerTickets);
			response.put("airlineTickets", airlineTickets);

			return ResponseEntity.ok(response);
		} catch (TicketException e) {
			return ResponseEntity.status(500).body(null);
		}
	}
	
}
