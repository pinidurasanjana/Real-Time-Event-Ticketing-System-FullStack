package com.pinidu.lil.OOP_CW.service;

import com.pinidu.lil.OOP_CW.model.Ticket;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoggingService {

    private final List<String> logs = new ArrayList<>();

    public synchronized void addLog(String log) {
        logs.add(log);
    }

    public synchronized void addCustomerLog(String customerId, int availableSize) {
        Ticket ticket = new Ticket();
        String logEntry = customerId + " " + "has bought from the Ticket Pool. Now available size is " + availableSize + ". Ticket is " + ticket;
        addLog(logEntry);
    }
    public synchronized void addVendorLog(String vendorId, int availableSize) {
        String logEntry = vendorId + " has added tickets in the Ticket Pool. Now available size is " + availableSize;
        addLog(logEntry);
    }

    // Get logs
    public synchronized List<String> getLogs() {
        return new ArrayList<>(logs);
    }
}
