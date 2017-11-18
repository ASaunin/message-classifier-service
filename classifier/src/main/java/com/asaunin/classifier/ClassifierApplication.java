package com.asaunin.classifier;

import com.asaunin.classifier.service.DataProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Log4j2
@SpringBootApplication
@RequiredArgsConstructor
public class ClassifierApplication implements ApplicationRunner {

    private final DataProvider dataProvider;

    public static void main(String[] args) {
        SpringApplication.run(ClassifierApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        try {
            dataProvider.loadData();
            log.info("Data loaded successfully");
        } catch (Exception ex) {
            log.error("Failed to load data. Application stopped: {}", ex.getMessage());
            System.exit(0);
        }
    }

}
