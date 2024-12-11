package com.pinidu.lil.OOP_CW.model;


import com.pinidu.lil.OOP_CW.service.LoggingService;

public class Customer implements Runnable{
    private TicketPool ticketPool;
    private double customerRetrievalRate;
    private int quantity;
    private LoggingService loggingService;

    public Customer(TicketPool ticketPool, double customerRetrievalRate, int quantity, LoggingService loggingService) {
        this.ticketPool = ticketPool;
        this.customerRetrievalRate = customerRetrievalRate;
        this.quantity = quantity;
        this.loggingService = loggingService;
    }

    @Override
    public void run() {
        for (int j = 0; j < quantity; j++) {
            Ticket ticket = ticketPool.removeTicket();
            if (ticket != null) {
                loggingService.addCustomerLog(Thread.currentThread().getName(), ticketPool.getAvailableSize(), ticket);
//                System.out.println("Customer " + Thread.currentThread().getName() + " bought ticket: " + ticket);
            } else {
                System.out.println("Customer " + Thread.currentThread().getName() + " found no tickets available.");
            }
            try {
                Thread.sleep((long) (customerRetrievalRate * 1000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
