package com.pinidu.lil.OOP_CW.controller;

import com.pinidu.lil.OOP_CW.model.Configuration;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ConfigurationController {

    private Configuration currentConfiguration;  // In-memory storage for configuration

    // Endpoint to receive configuration from the frontend
    @PostMapping("/api/configuration")
    public String receiveConfiguration(@RequestBody Configuration configuration) {
        // Store the configuration in memory
        currentConfiguration = configuration;

        // Log the received configuration for debugging
        System.out.println("Received configuration: " + configuration);
        return "Configuration received successfully!";
    }

    // Endpoint to get the current configuration (optional)
    @GetMapping("/api/configuration")
    public Configuration getConfiguration() {
        return currentConfiguration;  // Return the stored configuration
    }
}
