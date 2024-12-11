package com.pinidu.lil.OOP_CW;

import com.pinidu.lil.OOP_CW.model.*;
import com.pinidu.lil.OOP_CW.service.LoggingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
@Component
public class OopCwApplication {

	@Autowired
	private LoggingService loggingService;

	@Autowired
	private Configuration configuration;

	private boolean isRunning = false;

	public static void main(String[] args) {
		SpringApplication.run(OopCwApplication.class, args);
	}

	public void startApplication() {
		if (isRunning) {
			System.out.println("Application is already running");
			return;
		}
		isRunning = true;
		TicketPool ticketPool = new TicketPool(configuration.getMaxTicketCapacity());

		Vendor[] vendors = new Vendor[10];
		for (int k = 0; k < vendors.length; k++) {
			vendors[k] = new Vendor(ticketPool, configuration.getTotalTickets(), configuration.getTicketReleaseRate(), loggingService);
			Thread vendorThread = new Thread(vendors[k], String.valueOf((k + 1)));
			vendorThread.start();
			loggingService.addVendorLog(vendorThread.getName(), ticketPool.getAvailableSize());
//			System.out.println("Started vendor thread: " + vendorThread.getName());
		}

		Customer[] customers = new Customer[10];
		for (int k = 0; k < customers.length; k++) {
			Ticket ticket = new Ticket();
			customers[k] = new Customer(ticketPool, configuration.getCustomerRetrievalRate(), 5, loggingService);
			Thread customerThread = new Thread(customers[k], String.valueOf (k + 1));
			customerThread.start();
			loggingService.addCustomerLog(customerThread.getName(), ticketPool.getAvailableSize(), ticket);
//			System.out.println("Started customer thread: " + customerThread.getName());
		}
		new Thread(() -> {
			while (isRunning) {
				try {
					Thread.sleep(1000);  // Wait for 1 second before fetching logs
//					loggingService.addLog(String.valueOf(loggingService.getLogs()));
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		}).start();
	}

	public void stopApplication() {
		isRunning = false;  // Set running state to false
		System.out.println("Application stopped!");
	}

	public boolean isRunning() {
		return isRunning;
	}

}


