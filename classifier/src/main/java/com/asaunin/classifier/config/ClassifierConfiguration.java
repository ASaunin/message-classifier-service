package com.asaunin.classifier.config;

import com.asaunin.cache.LoadableItemCache;
import com.asaunin.classifier.CacheFactory;
import com.asaunin.classifier.domain.CustomPattern;
import com.asaunin.classifier.domain.DefaultPattern;
import com.asaunin.classifier.domain.Rule;
import com.asaunin.classifier.domain.SubCategory;
import com.asaunin.classifier.repository.*;
import com.asaunin.classifier.service.CategoryService;
import com.asaunin.classifier.service.CustomPatternService;
import com.asaunin.classifier.service.DefaultPatternService;
import com.asaunin.classifier.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class ClassifierConfiguration {

    @Bean
    public Consumer<CacheFactory.Builder<LoadableItemCache, LoadableRepository>> cacheFactory(
            @Autowired CategoryService categoryService, @Autowired SubCategoryRepository subCategoryRepository,
            @Autowired RuleService ruleService, @Autowired RuleRepository ruleRepository,
            @Autowired DefaultPatternService defaultPatternService, @Autowired DefaultPatternRepository defaultPatternRepository,
            @Autowired CustomPatternService customPatternService, @Autowired CustomPatternRepository customPatternRepository) {

        return builder -> {
            builder.add(SubCategory.class, categoryService, subCategoryRepository);
            builder.add(Rule.class, ruleService, ruleRepository);
            builder.add(DefaultPattern.class, defaultPatternService, defaultPatternRepository);
            builder.add(CustomPattern.class, customPatternService, customPatternRepository);
        };
    }

}
