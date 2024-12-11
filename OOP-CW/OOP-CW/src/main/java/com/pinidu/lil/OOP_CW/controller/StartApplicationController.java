package com.pinidu.lil.OOP_CW.controller;

import com.pinidu.lil.OOP_CW.OopCwApplication;
import com.pinidu.lil.OOP_CW.model.Configuration;
import com.pinidu.lil.OOP_CW.model.TicketPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class StartApplicationController {

    @Autowired
    private OopCwApplication oopCwApplication;

    @PostMapping("/api/start")
    public String startApplication() {
        try {
            oopCwApplication.startApplication();
            return "Application started successfully!";
        } catch (Exception e) {
            return "Error starting application: " + e.getMessage();
        }
    }

    @PostMapping("/api/stop")
    public String stopApplication() {
        try {
            oopCwApplication.stopApplication();
            return "Application stopped successfully!";
        } catch (Exception e) {
            return "Error stopping application: " + e.getMessage();
        }
    }
}
