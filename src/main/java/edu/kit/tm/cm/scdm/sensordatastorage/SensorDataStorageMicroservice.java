package edu.kit.tm.cm.scdm.sensordatastorage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SensorDataStorageMicroservice extends SpringBootServletInitializer {

    /**
     * Initializes and starts the SpringBoot application
     *
     * @param args CLI Parameter
     */
    public static void main(String[] args) {
        SpringApplication.run(SensorDataStorageMicroservice.class, args);
    }
}
