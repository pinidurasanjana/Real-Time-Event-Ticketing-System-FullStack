package com.pinidu.lil.OOP_CW.service;

import com.pinidu.lil.OOP_CW.model.Ticket;

import java.util.List;

public interface TicketService {
    Ticket saveTicket(Ticket ticket);

    List<Ticket> getAllTickets();


}

