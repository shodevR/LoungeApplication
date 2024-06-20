package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Ticket;
import org.springframework.data.jpa.repository.Query;

public interface TicketRepo extends JpaRepository<Ticket, Integer>{
    @Query("SELECT COUNT(t) FROM Ticket t")
    long countAllTickets();

    @Query("SELECT COUNT(t) FROM Ticket t WHERE t.ticketType = 'voucher'")
    long countVoucherTickets();

    @Query("SELECT COUNT(t) FROM Ticket t WHERE t.airlineName = 'Membership'")
    long countPartnerTickets();

    @Query("SELECT COUNT(t) FROM Ticket t WHERE t.airlineName = 'Airline'")
    long countAirlineTickets();

}
