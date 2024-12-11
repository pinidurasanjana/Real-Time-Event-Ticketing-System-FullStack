package com.pinidu.lil.OOP_CW.model;

import java.util.LinkedList;
import java.util.Queue;

public class TicketPool {
    private final int maximumCapacity;
    private final Queue<Ticket> ticketPool = new LinkedList<>();

    public TicketPool(int maximumCapacity) {
        this.maximumCapacity = maximumCapacity;
    }

    public synchronized void addTicket(Ticket tickets) {
        if (ticketPool.size() < maximumCapacity) {
            ticketPool.add(tickets);
        }
    }

    public synchronized Ticket removeTicket() {
        return ticketPool.isEmpty() ? null : ticketPool.poll();
    }

    public synchronized int getAvailableSize() {
        return ticketPool.size();
    }
}
