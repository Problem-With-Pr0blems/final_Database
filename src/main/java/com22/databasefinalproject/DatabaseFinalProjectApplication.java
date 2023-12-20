package com22.databasefinalproject;

import com22.databasefinalproject.config.ConnectionConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DatabaseFinalProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(DatabaseFinalProjectApplication.class, args);
        ConnectionConfig.getConnection();
    }

}
