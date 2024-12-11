package com.pinidu.lil.OOP_CW.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int ticketId;
    private int eventId;
    private String eventName;
    private BigDecimal ticketPrice;

    public Ticket() {
    }

    public Ticket(int ticketId, int eventId, String eventName, BigDecimal ticketPrice) {
        this.ticketId = ticketId;
        this.eventId = eventId;
        this.eventName = eventName;
        this.ticketPrice = ticketPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "Ticket Id=" + ticketId +
                ", Event Id=" + eventId +
                ", Event Name='" + eventName + '\'' +
                ", Ticket Price=" + ticketPrice +
                '}';
    }

}
