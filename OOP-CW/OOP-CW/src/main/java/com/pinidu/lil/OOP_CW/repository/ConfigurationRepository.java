package com.pinidu.lil.OOP_CW.repository;

import com.pinidu.lil.OOP_CW.model.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigurationRepository extends JpaRepository<Configuration, Long> {
}
