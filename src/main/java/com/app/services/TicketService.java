package com.app.services;

import java.util.List;

import com.app.exceptions.TicketException;
import com.app.model.Ticket;

public interface TicketService {
	public List<Ticket>viewAllTicket() throws TicketException;
	public Ticket addTicket(Ticket ticket) throws TicketException;
	public Ticket updateTicket(Ticket ticket) throws TicketException;
	public Ticket removeTicket(Integer ticketId) throws TicketException;
	public Ticket getTicketById(Integer ticketId) throws TicketException;

	public long getTotalTickets() throws TicketException;
	public long getVoucherTickets() throws TicketException;
	public long getPartnerTickets() throws TicketException;
	public long getAirlineTickets() throws TicketException;

}
