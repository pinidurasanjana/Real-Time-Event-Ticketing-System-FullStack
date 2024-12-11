package com.pinidu.lil.OOP_CW.controller;

import com.pinidu.lil.OOP_CW.model.Configuration;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class ConfigurationController {

    @PostMapping("/api/configuration")
    public String receiveConfiguration(@RequestBody Configuration configuration) {
        // Store the configuration temporarily
        System.out.println("Received configuration: " + configuration);
        return "Configuration received successfully!";
    }
}
