package com.asaunin.classifier.service;

import com.asaunin.classifier.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClassifierService {

    private final CategoryService categoryService;
    private final RuleService ruleService;
    private final DefaultPatternService defaultPatternService;
    private final CustomPatternService customPatternService;

    public ClassifierService(@Autowired CategoryService categoryService,
                             @Autowired RuleService ruleService,
                             @Autowired DefaultPatternService defaultPatternService,
                             @Autowired CustomPatternService customPatternService) {
        this.categoryService = categoryService;
        this.ruleService = ruleService;
        this.defaultPatternService = defaultPatternService;
        this.customPatternService = customPatternService;
    }

    public SubCategory classify(Integer subAccountId, Country country, String sender, String text) {

        // Find custom rules matching
        final Optional<Rule> rule =
                ruleService.findRulesBy(subAccountId, country)
                        .filter(r -> customPatternService
                                .findPatternsBy(r.getId())
                                .filter(pattern -> pattern.matchingBySender(sender))
                                .anyMatch(pattern -> pattern.matchingByText(text)))
                        .findFirst();

        if (rule.isPresent()) {
            final Integer subCategoryId = rule.get().getSubCategoryId();
            return categoryService.findById(subCategoryId);
        }

        // Find default rules matching by country
        Optional<DefaultPattern> patternByCountry = defaultPatternService
                .findPatternsBy(country)
                .filter(pattern -> pattern.matchingBySender(sender))
                .filter(pattern -> pattern.matchingByText(text))
                .findFirst();

        // Find default rules matching by any country
        if (!patternByCountry.isPresent()) {
            patternByCountry = defaultPatternService
                    .findPatternsBy(AnyCountry.getInstance())
                    .filter(pattern -> pattern.matchingBySender(sender))
                    .filter(pattern -> pattern.matchingByText(text))
                    .findFirst();
        }

        // Find category
        if (patternByCountry.isPresent()) {
            final Integer subCategoryId = patternByCountry.get().getSubCategoryId();
            return categoryService.findById(subCategoryId);
        }

        return categoryService.getDefaultSubCategory();
    }

}
