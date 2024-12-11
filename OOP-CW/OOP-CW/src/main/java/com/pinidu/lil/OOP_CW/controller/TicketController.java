package com.pinidu.lil.OOP_CW.controller;

import com.pinidu.lil.OOP_CW.model.Ticket;
import com.pinidu.lil.OOP_CW.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticket")
@CrossOrigin
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @PostMapping("/add")
    public String add(@RequestBody Ticket ticket) {
        ticketService.saveTicket(ticket);
        return "Ticket added";
    }
    @GetMapping("/getAll")
    public List<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }


}
