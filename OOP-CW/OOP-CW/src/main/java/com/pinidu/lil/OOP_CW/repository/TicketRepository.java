package com.pinidu.lil.OOP_CW.repository;

import com.pinidu.lil.OOP_CW.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

}
