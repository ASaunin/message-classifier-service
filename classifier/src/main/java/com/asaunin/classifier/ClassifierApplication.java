package com.asaunin.classifier;

import com.asaunin.classifier.domain.SubCategory;
import com.asaunin.classifier.service.CategoryService;
import com.asaunin.classifier.service.DataProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Objects;

@Log4j2
@SpringBootApplication
@RequiredArgsConstructor
public class ClassifierApplication implements ApplicationRunner {

    private final DataProvider dataProvider;
    private final CategoryService categoryService;

    public static void main(String[] args) {
        SpringApplication.run(ClassifierApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        final boolean loadSucceed = dataProvider.load();
        if (loadSucceed) {
            log.info("Data loaded successfully");
        } else {
            log.error("Failed to load initial data. Application stopped");
            System.exit(0);
        }

        final SubCategory defaultSubCategory = categoryService.getDefaultSubCategory();
        if (Objects.isNull(defaultSubCategory)) {
            log.error("Failed to detect the default subCategory. Application stopped");
            System.exit(0);
        }
    }

}
