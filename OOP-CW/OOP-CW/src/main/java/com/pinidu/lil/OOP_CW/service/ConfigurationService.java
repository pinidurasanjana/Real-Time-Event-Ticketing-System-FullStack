package com.pinidu.lil.OOP_CW.service;

import com.pinidu.lil.OOP_CW.model.Configuration;
import com.pinidu.lil.OOP_CW.repository.ConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfigurationService {

    @Autowired
    private ConfigurationRepository configurationRepository;

    // Save configuration to database
    public Configuration saveConfiguration(Configuration configuration) {
        return configurationRepository.save(configuration);
    }
}
