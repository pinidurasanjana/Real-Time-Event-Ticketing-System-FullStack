package com.pinidu.lil.OOP_CW.model;

import com.pinidu.lil.OOP_CW.service.LoggingService;
import java.math.BigDecimal;

public class Vendor implements Runnable {
    private final TicketPool ticketPool;
    private final int totalTickets;
    private final double ticketReleaseRate;
    private LoggingService loggingService;

    public Vendor(TicketPool ticketPool, int totalTickets, double ticketReleaseRate, LoggingService loggingService) {
        this.ticketPool = ticketPool;
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.loggingService = loggingService;
    }
    @Override
    public void run() {
        for (int i = 1; i <= totalTickets; i++) {
            Ticket ticket = new Ticket(i, (00+i), ("Event " + i), new BigDecimal(1000 * i));
            synchronized (ticketPool) {
                ticketPool.addTicket(ticket);
//                loggingService.addVendorLog(Thread.currentThread().getName(), ticketPool.getAvailableSize());
//                System.out.println("Vendor " + Thread.currentThread().getName() + " added ticket: " + ticket);
            }

            loggingService.addVendorLog(Thread.currentThread().getName(), ticketPool.getAvailableSize());

            try {
                Thread.sleep((long) (ticketReleaseRate * 1000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
