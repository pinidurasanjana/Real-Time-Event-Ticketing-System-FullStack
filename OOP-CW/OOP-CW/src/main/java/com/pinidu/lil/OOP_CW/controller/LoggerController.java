package com.pinidu.lil.OOP_CW.controller;
import com.pinidu.lil.OOP_CW.service.LoggingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class LoggerController {

    @Autowired
    private LoggingService loggingService;

    @GetMapping("/api/logs")
    public List<String> getLogs() {
        return loggingService.getLogs();
    }
}
