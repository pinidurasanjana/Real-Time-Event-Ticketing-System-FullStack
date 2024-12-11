package com.pinidu.lil.OOP_CW;

import com.pinidu.lil.OOP_CW.model.Configuration;
import com.pinidu.lil.OOP_CW.model.Customer;
import com.pinidu.lil.OOP_CW.model.TicketPool;
import com.pinidu.lil.OOP_CW.model.Vendor;
import com.pinidu.lil.OOP_CW.service.LoggingService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OopCwApplication {

	public static void main(String[] args) {
		SpringApplication.run(OopCwApplication.class, args);

		Configuration configuration = new Configuration();
		TicketPool ticketPool = new TicketPool(configuration.getMaxTicketCapacity());
		LoggingService loggingService = new LoggingService();

		Vendor[] vendors = new Vendor[10];
		for (int k = 0; k < vendors.length; k++) {
			vendors[k] = new Vendor(ticketPool, configuration.getTotalTickets(), configuration.getTicketReleaseRate());
			Thread vendorThread = new Thread(vendors[k], "Vendor-" + (k + 1));
			loggingService.addVendorLog(vendorThread.getName(), ticketPool.getAvailableSize());
			vendorThread.start();
		}
		Customer[] customers = new Customer[10];
		for (int k = 0; k < customers.length; k++) {
			customers[k] = new Customer(ticketPool, configuration.getCustomerRetrievalRate(), 5);
			Thread customerThread = new Thread(customers[k], "Customer-" + (k + 1));
			loggingService.addCustomerLog(customerThread.getName(), ticketPool.getAvailableSize());
			customerThread.start();
		}
	}
}

