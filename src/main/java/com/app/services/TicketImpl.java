package com.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.exceptions.TicketException;
import com.app.model.Ticket;
import com.app.repository.TicketRepo;


@Service
public class TicketImpl implements TicketService{

	@Autowired
	private TicketRepo ticketRepo;
	@Override
	public List<Ticket> viewAllTicket() throws TicketException {
		List<Ticket>list = ticketRepo.findAll();
		if (list.size()>0) {
			return list;
		}
		throw new TicketException("No tickets to view");
	}

	@Override
	public Ticket addTicket(Ticket ticket) throws TicketException {

		Ticket ticket2 = ticketRepo.save(ticket);
		
		return ticket2;
	}

	@Override
	public Ticket updateTicket(Ticket ticket) throws TicketException {
		Optional<Ticket>optional = ticketRepo.findById(ticket.getTicketId());
		if (optional.isPresent()) {
			return ticketRepo.save(ticket);
			
			
		}else {
			throw new TicketException("ticket not found");
		}
		
	}

	@Override
	public Ticket removeTicket(Integer ticketId) throws TicketException {

		Ticket ticket = ticketRepo.findById(ticketId).orElseThrow(()->new TicketException("Ticket not found"));
		ticketRepo.delete(ticket);
		
		return ticket;
	}

	@Override
	public Ticket getTicketById(Integer ticketId) throws TicketException {
		Ticket ticket = ticketRepo.findById(ticketId).orElseThrow(()-> new TicketException("No ticket with id "+ ticketId));
		
		return ticket;
	}
	@Override
	public long getTotalTickets() throws TicketException {
		return ticketRepo.countAllTickets();
	}

	@Override
	public long getVoucherTickets() throws TicketException {
		return ticketRepo.countVoucherTickets();
	}

	@Override
	public long getPartnerTickets() throws TicketException {
		return ticketRepo.countPartnerTickets();
	}

	@Override
	public long getAirlineTickets() throws TicketException {
		return ticketRepo.countAirlineTickets();
	}

}
